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

import java.util.concurrent.TimeUnit;

public class PatientMonitoringClient {
  private static PatientMonitoringGrpc.PatientMonitoringBlockingStub blockingStub;
  private static PatientMonitoringGrpc.PatientMonitoringStub asyncStub;
  private static ServiceInfo patientMonitoringServiceInfo;
  private ManagedChannel patientMonitorChannel;


  
  public PatientMonitoringClient(){  
  }
  
  public PatientMonitoringClient (ManagedChannel patientMonitorChannel) {
	  this.patientMonitorChannel = patientMonitorChannel;
	  blockingStub = PatientMonitoringGrpc.newBlockingStub(patientMonitorChannel);
	  asyncStub = PatientMonitoringGrpc.newStub(patientMonitorChannel);
  }
  
  public void shutdown() throws InterruptedException {
      patientMonitorChannel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
  }
  
  public static void main(String[] args) throws InterruptedException, java.util.concurrent.TimeoutException {
    PatientMonitoringClient patientMonitorClient = new PatientMonitoringClient();
    

    String patientMonitoring_service_type = "_realtime_monitoring._tcp.local.";
    patientMonitorClient.discoverPatientMonitoringService(patientMonitoring_service_type);
    
    String host = patientMonitoringServiceInfo.getHostAddresses()[0];
    int port = patientMonitoringServiceInfo.getPort();
    
    // managed channel is created using the host and port
    final ManagedChannel patientMonitorChannel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();

    // stubs created using the already generated Grpc class
    // blocking stub sends request to client and waits for response until proceeding
    blockingStub = PatientMonitoringGrpc.newBlockingStub(patientMonitorChannel);
    asyncStub = PatientMonitoringGrpc.newStub(patientMonitorChannel);

    
    patientMonitorChannel.shutdown();
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

      Thread.sleep(500);
      jmdns.close();

    } catch (UnknownHostException e) {
      System.out.println(e.getMessage());
    } catch (IOException e) {
      System.out.println(e.getMessage());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  
  
  public String addPatient(String patientName, int patientAge, String patientId) {
	    AddPatientRequest request = AddPatientRequest.newBuilder()
	      .setPatientName(patientName)
	      .setPatientAge(patientAge)
	      .setPatientId(patientId)
	      .build();
	    AddPatientResponse response;
	    try {
	        response = blockingStub.addPatient(request);
	        return "Add patient response: " + response.getMessage() + "\n";
	    } catch (StatusRuntimeException e) {
	        return "RPC failed: " + e.getStatus() + "\n";
	    }
	}
  
  public interface PatientInfoCallback {
	    void onNewMessage(String message);
	}

  public void streamPatientInfo(String patientId, PatientInfoCallback callback) {
      StreamPatientInfoRequest request = StreamPatientInfoRequest.newBuilder()
              .setPatientId(patientId)
              .build();

      StreamObserver<StreamPatientInfoResponse> responseObserver = new StreamObserver<StreamPatientInfoResponse>() {
          @Override
          public void onNext(StreamPatientInfoResponse response) {
              String outputMessage = "Patient info: Heart rate=" + response.getHeartRate() +
                      ", Oxygen saturation=" + response.getOxygenSaturation() +
                      ", Blood pressure=" + response.getBloodPressure() + "\n";
              callback.onNewMessage(outputMessage);
          }

          @Override
          public void onError(Throwable t) {
              String errorMessage = "gRPC failed: " + t.getMessage() + "\n";
              callback.onNewMessage(errorMessage);
          }

          @Override
          public void onCompleted() {
              String completedMessage = "Finished receiving medical alerts.\n";
              callback.onNewMessage(completedMessage);
          }
      };

      asyncStub.streamPatientInfo(request, responseObserver);
  }


  public interface MedicalAlertCallback {
	    void onNewAlert(String message);
	}
  

  public void streamMedicalAlerts(String patientId, MedicalAlertCallback callback) throws InterruptedException, java.util.concurrent.TimeoutException {
    StreamMedicalAlertsRequest request = StreamMedicalAlertsRequest.newBuilder()
      .setPatientId(patientId)
      .build();


    StreamObserver<StreamMedicalAlertsResponse> responseObserver = new StreamObserver<StreamMedicalAlertsResponse>() {
        @Override
        public void onNext(StreamMedicalAlertsResponse response) {
            String message = "Medical alert status: " + response.getDiagnosis() + ", Treatment = " + response.getTreatment();
            callback.onNewAlert(message);
        }

      @Override
      public void onError(Throwable t) {
        System.err.println("gRPC failed: " + t.getMessage());

      }

      @Override
      public void onCompleted() {
        System.out.println("Finished receiving medical alerts.");

      }
    };

    asyncStub.streamMedicalAlerts(request, responseObserver);

  }

}

  

        
    

