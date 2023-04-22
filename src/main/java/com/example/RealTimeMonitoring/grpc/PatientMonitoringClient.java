package com.example.RealTimeMonitoring.grpc;

import com.example.RealTimeMonitoring.grpc.*;


import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import java.util.concurrent.TimeUnit;

public class PatientMonitoringClient {
  private static PatientMonitoringGrpc.PatientMonitoringBlockingStub blockingStub;
  private static PatientMonitoringGrpc.PatientMonitoringStub asyncStub;
//create service specific ServiceInfo, allows for discovery of grpc services and generate client code specific to the server
  private static ServiceInfo patientMonitoringServiceInfo;
  private ManagedChannel patientMonitorChannel;

  // constructor
  public PatientMonitoringClient() {}

  // constructor accepting a ManagedChannel obj as parameter to create grpc connection
  public PatientMonitoringClient(ManagedChannel patientMonitorChannel) {
    this.patientMonitorChannel = patientMonitorChannel;
 // create stubs - these provide grpc methods on server
    blockingStub = PatientMonitoringGrpc.newBlockingStub(patientMonitorChannel);
    asyncStub = PatientMonitoringGrpc.newStub(patientMonitorChannel);
  }

  //shutdown method to close the channel when called
  public void shutdown() throws InterruptedException {
    patientMonitorChannel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
  }

  public static void main(String[] args) throws InterruptedException, java.util.concurrent.TimeoutException {
    PatientMonitoringClient patientMonitorClient = new PatientMonitoringClient();

    // discover service by service type
    String patientMonitoring_service_type = "_realtime_monitoring._tcp.local.";
    patientMonitorClient.discoverPatientMonitoringService(patientMonitoring_service_type);

    String host = patientMonitoringServiceInfo.getHostAddresses()[0];
    int port = patientMonitoringServiceInfo.getPort();

    // managed channel is created using the host and port obtained using serviceInfo methods
    final ManagedChannel patientMonitorChannel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();

    //blocking grpc stub is used when client is waiting for serv to response before continuing
    // asynchronous grpc stub when no wait is needed for response. Server responds and a callback handles the response (streaming)
    blockingStub = PatientMonitoringGrpc.newBlockingStub(patientMonitorChannel);
    asyncStub = PatientMonitoringGrpc.newStub(patientMonitorChannel);
    // close channel
    patientMonitorChannel.shutdown();
  }

  
  // jmDNS service discovery - method takes service_type as param
  // service type is a string representing type of service to be discovered
  private void discoverPatientMonitoringService(String service_type) {
    try {
      // create instance of jmDNS using host local inetaddress
      JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
      // create a service listener to listen for service_type related events (add, resolve, remove)
      jmdns.addServiceListener(service_type, new ServiceListener() {

        // when service is discovered + resolved, extract port, host, and give brief print out of info of service 
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
          // TODO Auto-generated method stub - No current use needed for this	
        }
        
        @Override
        public void serviceRemoved(ServiceEvent event) {
          // TODO Auto-generated method stub  - No current use needed for this	
        }
      });
      // close jmDNS service, service info already obtained and stored + exception handling
      jmdns.close();
    } catch (UnknownHostException e) {
      System.out.println(e.getMessage());
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  
  
  /*
   * Unary gRPC method - addPatient - Single request and single response
   */
  
  // take patient name, age, ID, build a request, return a response
  public String addPatient(String patientName, int patientAge, String patientId) {
    //create request obj
    AddPatientRequest request = AddPatientRequest.newBuilder()
      .setPatientName(patientName)
      .setPatientAge(patientAge)
      .setPatientId(patientId)
      .build();
    // declare a response obj and pass request to the server using blockingStub to make call, block thread until server response  obtained
    AddPatientResponse response;
    try {
      response = blockingStub.addPatient(request);
      // if successful return formatted response
      return "Add patient response: " + response.getMessage() + "\n";
    } catch (StatusRuntimeException e) {
      // if unsuccessful provide error message
      return "RPC failed: " + e.getStatus() + "\n";
    }
  }
  
  
  
  /*
   * Server Streaming gRPC method - streamPatientInfo - client sends a single request to server, receives stream in return
   */
  
  // define a simple callback interface for new patient information messages
  public interface PatientInfoListener {
    void onNewPatientInfo(String message);
  }
  
  // stream patient info from server taking patientID and callback obj as params
  public void streamPatientInfo(String patientId, PatientInfoListener callback) {
    // create request obj using patientID
    StreamPatientInfoRequest request = StreamPatientInfoRequest.newBuilder()
      .setPatientId(patientId)
      .build();

    // create response observer obj to handle server responses
    StreamObserver < StreamPatientInfoResponse > responseObserver = new StreamObserver < StreamPatientInfoResponse > () {
      @Override
      public void onNext(StreamPatientInfoResponse response) {
        // define format the of String outputMessage using info from the response (oxygen saturation and blood pressure)
        String outputMessage = "Patient info: Heart rate=" + response.getHeartRate() + ", Oxygen saturation=" + response.getOxygenSaturation() +
          ", Blood pressure=" + response.getBloodPressure() + "\n";
        // call onNewPatientInfo() with formatted message
        callback.onNewPatientInfo(outputMessage);
      }
      // error handling
      @Override
      public void onError(Throwable t) {
        String errorMessage = "gRPC method failed: " + t.getMessage() + "\n";
        callback.onNewPatientInfo(errorMessage);
      }
      // completion message formatted plus call to onNewPatientInfo
      @Override
      public void onCompleted() {
        String completedMessage = "Finished receiving medical alerts.\n";
        callback.onNewPatientInfo(completedMessage);
      }
    };
    // async call to the server with request and responseObserver objects
    asyncStub.streamPatientInfo(request, responseObserver);
  }

  
  
  /*
   * Server Streaming gRPC method - streamMedicalAlerts - client sends single request with patientID
   * server responds with asynchronous alert stream handled by client response observer
   */
  
  // define callback interface for medical alerts
  public interface MedicalAlertListener {
    void onNewAlert(String message);
  }

  // method will stream medical alerts for a patient specified by patient ID
  public void streamMedicalAlerts(String patientId, MedicalAlertListener callback) throws InterruptedException, java.util.concurrent.TimeoutException {
    // create the request object for alerts using the ID
    StreamMedicalAlertsRequest request = StreamMedicalAlertsRequest.newBuilder()
      .setPatientId(patientId)
      .build();

    // define a response observer to handle server stream responses
    StreamObserver < StreamMedicalAlertsResponse > responseObserver = new StreamObserver < StreamMedicalAlertsResponse > () {
      @Override
      // method called every time a new response is received
      public void onNext(StreamMedicalAlertsResponse response) {
        // format the the response
        String message = "Medical alert status: " + response.getDiagnosis() + ", Treatment = " + response.getTreatment();
        // call the callback's own method, onNewAlert() with the formatted message above
        callback.onNewAlert(message);
      }
      // error handling if an error occurs during streaming
      @Override
      public void onError(Throwable t) {
        System.err.println("gRPC failed: " + t.getMessage());

      }
      // completion message when stream is complete
      @Override
      public void onCompleted() {
        System.out.println("Finished receiving medical alerts.");
      }
    };
    // initiate the stream with specific request info, and handle server responses using responseObserver
    asyncStub.streamMedicalAlerts(request, responseObserver);

  }

}