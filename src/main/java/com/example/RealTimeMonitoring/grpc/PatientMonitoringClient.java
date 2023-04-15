package com.example.RealTimeMonitoring.grpc;

import com.example.RealTimeMonitoring.grpc.*;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.netty.shaded.io.netty.handler.timeout.TimeoutException;
import io.grpc.stub.StreamObserver;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class PatientMonitoringClient {

  private static PatientMonitoringGrpc.PatientMonitoringBlockingStub blockingStub;
  private static PatientMonitoringGrpc.PatientMonitoringStub asyncStub;
  private static ServiceInfo patientMonitoringServiceInfo;

  public static void main(String[] args) throws InterruptedException, java.util.concurrent.TimeoutException {
    PatientMonitoringClient client = new PatientMonitoringClient();

    String patientMonitoring_service_type = "_grpc._tcp.local.";

    client.discoverPatientMonitoringService(patientMonitoring_service_type);

    String host = patientMonitoringServiceInfo.getHostAddresses()[0];
    int port = patientMonitoringServiceInfo.getPort();

    final ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();

    blockingStub = PatientMonitoringGrpc.newBlockingStub(channel);
    asyncStub = PatientMonitoringGrpc.newStub(channel);

    // create Scanner obj to take in patient info
    Scanner myInput = new Scanner(System.in);

    System.out.println("Please enter patient name:");
    String patientName = myInput.nextLine();
    System.out.println("Please enter patient age:");
    int patientAge = myInput.nextInt();
    myInput.nextLine(); // Consume the newline character
    System.out.println("Enter patient ID:");
    String patientId = myInput.nextLine();

    client.addPatient(patientName, patientAge, patientId);

    // Ask for patientID to receive patient info stream
    System.out.println("Enter patient ID to stream patient info:");
    patientId = myInput.nextLine();
    client.streamPatientInfo(patientId);

    // added a timer to commence after patientinfo stream, so the prompt for medicalAlert stream is at the correct time
    // not very robust, i considered using CompleteableFuture as below but this is much simpler implementation
    // in real world both streams would possibly run side by side but for visual clarity i have left them separate here
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    // enter patientID again to receive medical alerts stream
    System.out.println("Enter patient ID to stream medical alerts:");
    patientId = myInput.nextLine();
    client.streamMedicalAlerts(patientId);

    channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
  }

  private void discoverPatientMonitoringService(String service_type) {
    try {
      JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

      jmdns.addServiceListener(service_type, new ServiceListener() {
        @Override
        public void serviceResolved(ServiceEvent event) {

          patientMonitoringServiceInfo = event.getInfo();

          int port = patientMonitoringServiceInfo.getPort();
          String host = patientMonitoringServiceInfo.getHostAddresses()[0];

          System.out.println("resolving " + service_type + " with properties ...");
          System.out.println("port: " + port);
          System.out.println("type:" + event.getType());
          System.out.println("name: " + event.getName());
          System.out.println("host: " + host);

        }

        @Override
        public void serviceAdded(ServiceEvent event) {
          // TODO Auto-generated method stub	
        }

        @Override
        public void serviceRemoved(ServiceEvent event) {
          // TODO Auto-generated method stub
        }
      });
      // set to 2000ms to give jmDNS more time to discover. stops random failure of methods sometimes
      Thread.sleep(2000);
      jmdns.close();

    } catch (UnknownHostException e) {
      System.out.println(e.getMessage());
    } catch (IOException e) {
      System.out.println(e.getMessage());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  // addPatient taking in 3 parameters passed by Scanner 'myInput'
  public void addPatient(String patientName, int patientAge, String patientId) {
    AddPatientRequest request = AddPatientRequest.newBuilder()
      .setPatientName(patientName)
      .setPatientAge(patientAge)
      .setPatientId(patientId)
      .build();
    AddPatientResponse response;
    try {
      response = blockingStub.addPatient(request);
      System.out.println("Add patient response: " + response.getMessage());
    } catch (StatusRuntimeException e) {
      System.err.println("RPC failed: " + e.getStatus());
    }
  }

  public void streamPatientInfo(String patientId) throws InterruptedException, java.util.concurrent.TimeoutException {
    StreamPatientInfoRequest request = StreamPatientInfoRequest.newBuilder()
      .setPatientId(patientId)
      .build();

    StreamObserver < StreamPatientInfoResponse > responseObserver = new StreamObserver < StreamPatientInfoResponse > () {
      @Override
      public void onNext(StreamPatientInfoResponse response) {
        System.out.println("Patient info: Heart rate=" + response.getHeartRate() +
          ", Oxygen saturation=" + response.getOxygenSaturation() +
          ", Blood pressure=" + response.getBloodPressure());
      }

      @Override
      public void onError(Throwable t) {
        System.err.println("RPC failed: " + t.getMessage());
      }

      @Override
      public void onCompleted() {
        System.out.println("Finished receiving patient info.");
      }
    };

    asyncStub.streamPatientInfo(request, responseObserver);
  }

  public void streamMedicalAlerts(String patientId) throws InterruptedException, java.util.concurrent.TimeoutException {
    StreamMedicalAlertsRequest request = StreamMedicalAlertsRequest.newBuilder()
      .setPatientId(patientId)
      .build();

    // CompletableFuture is from the .concurrent package and allows for asynchronous handling of the methods
    // i tried to run without but both streams were happening at the same time 
    // completable future lets me run one once the other is finished properly and stops server closing immediately after

    CompletableFuture < Void > completionFuture = new CompletableFuture < > ();

    StreamObserver < StreamMedicalAlertsResponse > responseObserver = new StreamObserver < StreamMedicalAlertsResponse > () {
      @Override
      public void onNext(StreamMedicalAlertsResponse response) {
        System.out.println("Medical alert status: " + response.getDiagnosis() + ", Treatment = " + response.getTreatment());
      }

      @Override
      public void onError(Throwable t) {
        System.err.println("gRPC failed: " + t.getMessage());
        completionFuture.completeExceptionally(t);
      }

      @Override
      public void onCompleted() {
        System.out.println("Finished receiving medical alerts.");
        completionFuture.complete(null);
      }
    };

    asyncStub.streamMedicalAlerts(request, responseObserver);

    // keep server open until I want it to close
    try {
      completionFuture.get();
    } catch (ExecutionException e) {
      System.err.println("Error occurred while waiting for stream completion: " + e.getCause().getMessage());
    }
  }

}

  

        
    

