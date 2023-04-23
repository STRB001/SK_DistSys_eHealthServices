package com.example.eHealthRecords.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;

public class EHRManagementClient {
  private static EHRManagementGrpc.EHRManagementBlockingStub blockingStub;
  private static EHRManagementGrpc.EHRManagementStub asyncStub;
  private static ServiceInfo EHRManagementServiceInfo;
  private ManagedChannel EHRManagementChannel;

  // constructor
  public EHRManagementClient() {}

  // constructor taking in ManagedChannel as param and initializing blocking and async Stubs
  public EHRManagementClient(ManagedChannel EHRManagementChannel) {
    this.EHRManagementChannel = EHRManagementChannel;
    blockingStub = EHRManagementGrpc.newBlockingStub(EHRManagementChannel);
    asyncStub = EHRManagementGrpc.newStub(EHRManagementChannel);
  }

  // shutdown method to close channel properly when called
  public void shutdown() throws InterruptedException {
    EHRManagementChannel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
  }

  // main method starts by creating client instance
  public static void main(String[] args) throws InterruptedException {
    EHRManagementClient EHRManagementClient = new EHRManagementClient();

    // define service type and call discovery service to find it
    String ehrManagement_service_type = "_ehr_management._tcp.local.";
    EHRManagementClient.discoverEHRManagementService(ehrManagement_service_type);

    // create a ManagedChannel using host+port info taken from discovered service
    String host = EHRManagementServiceInfo.getHostAddresses()[0];
    int port = EHRManagementServiceInfo.getPort();
    final ManagedChannel EHRManagementChannel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();

    // initialise stubs using ManagedChannel
    blockingStub = EHRManagementGrpc.newBlockingStub(EHRManagementChannel);
    asyncStub = EHRManagementGrpc.newStub(EHRManagementChannel);

    EHRManagementChannel.shutdown();
    try {
        if (!EHRManagementChannel.awaitTermination(5, TimeUnit.SECONDS)) {
            System.err.println("Channel did not terminate in the given time.");
            EHRManagementChannel.shutdownNow();

            if (!EHRManagementChannel.awaitTermination(5, TimeUnit.SECONDS)) {
                System.err.println("Channel did not forcibly terminate.");
            }
        }
    } catch (InterruptedException e) {
    	EHRManagementChannel.shutdownNow();
        Thread.currentThread().interrupt();
    }
  }

  
  // method to discover employee health record server using jmDNS
  private void discoverEHRManagementService(String service_type) {
    // use local host inetaddress to create instance of jmDNS
    try {
      JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
      // create a 'Service Listener' which listens for service events (service is added removed or resolved)
      jmdns.addServiceListener(service_type, new ServiceListener() {

        // when service is discovered + resolved, extract port, host, and give brief print out of info of service 
        @Override
        public void serviceResolved(ServiceEvent event) {
          EHRManagementServiceInfo = event.getInfo();
          int port = EHRManagementServiceInfo.getPort();
          String host = EHRManagementServiceInfo.getHostAddresses()[0];

          System.out.println("resolving " + service_type + " with properties ...");
          System.out.println("port: " + port);
          System.out.println("type:" + event.getType());
          System.out.println("name: " + event.getName());
          System.out.println("host: " + host);
        }
        
        @Override
        public void serviceRemoved(ServiceEvent event) {
          // TODO Auto-generated method stub	
        }
        @Override
        public void serviceAdded(ServiceEvent event) {
          // TODO Auto-generated method stub	
        }
      });
      // close jmDNS service, service info already obtained and stored
      jmdns.close();
      // exception handling + messages
    } catch (UnknownHostException e) {
      System.out.println(e.getMessage());
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  
  
  /*
   * Unary gRPC method - searchPatientRecords - handles request from client, searches patient in patientRecords list
   * sends response to client with patient info if found / empty response if not
   */
  public String searchPatientRecord(String patientId) {
    // create a request using the user entered patientID
    SearchPatientRecordRequest request = SearchPatientRecordRequest.newBuilder()
      .setPatientId(patientId)
      .build();
    try {
      // use blockingstub to send the request and wait to receive response
      SearchPatientRecordResponse response = blockingStub.searchPatientRecord(request);
      // conditional just to make sure ID field isn't empty
      if (!response.getPatientId().isEmpty()) {
        // return patient details if patient found
        return "Success! Patient found:\n" +
          "Patient ID: " + response.getPatientId() + "\n" +
          "Name: " + response.getPatientName() + "\n" +
          "Department: " + response.getDepartment() + "\n" +
          "Diagnosis: " + response.getDiagnosis() + "\n" +
          "Medication: " + (response.getMedication().isEmpty() ? "None" : response.getMedication()) + "\n" +
          "Scheduled Operation: " + (response.getScheduledOperation().isEmpty() ? "None" : response.getScheduledOperation()) + "\n";
        // return message to indicate patient not found if no matching ID
      } else {
        return "Patient ID " + patientId + " was searched but no matching ID found.";
        
      }
      // error handling
    } catch (StatusRuntimeException e) {
      return "RPC failed: " + e.getStatus();
    }
  }

  // returns the response object containing the patient info based on patient ID
  public SearchPatientRecordResponse getPatientRecord(String patientId) {
    // create SearchPatientRecordRequest obj containing patient ID
    SearchPatientRecordRequest request = SearchPatientRecordRequest.newBuilder()
      .setPatientId(patientId)
      .build();
    // use blockingstub to send the request and wait to receive response
    try {
      SearchPatientRecordResponse response = blockingStub.searchPatientRecord(request);
      // return patient info as a response
      return response;
      // error handling
    } catch (StatusRuntimeException e) {
      System.err.println("RPC failed: " + e.getStatus());
      return null;
    }
  }

  
  
  
  /*
   * UNARY UPDATE PATIENT METHOD - Single request sent to server, single response returned
   */
  public void updatePatientRecord(String patientId, String name, String department, String diagnosis, String medication, String operation) {
    // create a request using all of the entered patient details and .newbuilder()
    UpdatePatientRecordRequest request = UpdatePatientRecordRequest.newBuilder()
      .setPatientId(patientId)
      .setName(name)
      .setDepartment(department)
      .setDiagnosis(diagnosis)
      .setMedication(medication)
      .setOperation(operation)
      .build();
    try {
      // send request using blockingstub, wait to receive response
      UpdatePatientRecordResponse response = blockingStub.updatePatientRecord(request);
      // print result to console
      System.out.println("Update patient record result:\n" + response.getMessage());
      // error handling
    } catch (StatusRuntimeException e) {
      System.err.println("RPC failed: " + e.getStatus());
    }
  }

  
  
  
  /*
   * Client-streaming gRPC method - sharePatientRecords - accepts int value for # of records to be shared and sharePatientListener as callback
   * client sends multiple patient records to server, server side onNext() returns accumulates requests and returns single response of records shared
   */

  // interace with method that accepts string as param, used to communicate results of grpc call back to caller
  public interface sharePatientListener {
    void onSharePatientEvent(String output);
  }

  // accepts int for number of patient records to be shared and sharePatientListener instance callback
  public void sharePatientRecord(int numPatients, sharePatientListener outputCallback) {
    // create stream observer to handle server responses
    StreamObserver < SharePatientRecordResponse > responseObserver = new StreamObserver < SharePatientRecordResponse > () {
      // when new response is received from server call callback method with string of response message
      @Override
      public void onNext(SharePatientRecordResponse response) {
        outputCallback.onSharePatientEvent("Response: " + response.getMessage());
      }
      // error handling
      @Override
      public void onError(Throwable t) {
        outputCallback.onSharePatientEvent("RPC failed: " + t.getMessage());
      }
      // called when server finished sending responses
      @Override
      public void onCompleted() {
        outputCallback.onSharePatientEvent("Finished sharing patient record.");
      }
    };

    // create stream observer obj named requestObserver
    StreamObserver < SharePatientRecordRequest > requestObserver = asyncStub.sharePatientRecord(responseObserver);
    // loop  through the number of patients entered and increment +1 to the end of each to simulate the number of patient records shared
    try {
      for (int i = 1; i <= numPatients; i++) {
        String recordContent = "PatientID" + i + "\nPatientName" + i + "\nPatientAge" + i + "\nPatientAddress" + i;
        requestObserver.onNext(SharePatientRecordRequest.newBuilder()
          .setPatientId("patient" + i)
          .setRecordContent(recordContent)
          .build());
        //call the callback with the record content - this updates call with current patient record
        outputCallback.onSharePatientEvent(recordContent + "\n");
      }
      // handle runtime errors
    } catch (RuntimeException e) {
      requestObserver.onError(e);
      throw e;
    }
    // let server know client is finished sending reqquests
    requestObserver.onCompleted();
  }
}