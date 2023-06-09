package com.example.eHealthRecords.grpc;

import java.io.IOException;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

//create the main class and declare server for reference later to server methods
public class EHRManagementServer {

  private Server server;
  // create an instance of EHRManagementServer and call start method
  public static void main(String[] args) throws IOException, InterruptedException {
    EHRManagementServer server = new EHRManagementServer();
    server.start();
  }

  
  // start() initialises the server, starts the jmDNS discovery service, and block main thread until server is shut down
  private void start() throws IOException {
    try {
      int port = 50052;
      server = ServerBuilder.forPort(port)
        .addService(new EHRManagementImpl())
        .build()
        .start();
      System.out.println("EHR Management server started, listening on port " + port);

      // jmDNS registration - create instance of jmDNS, allows for registration of services
      JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
      // create instance of ServiceInfo class which handles info for registration (port, type, path as params)
      ServiceInfo serviceInfo = ServiceInfo.create("_ehr_management._tcp.local.", "EHRManagementServer", port, "");
      jmdns.registerService(serviceInfo);
      System.out.println("jmDNS registration complete with type: " + serviceInfo.getType() + " and name: " + serviceInfo.getName());

      // blocking call that blocks the main() from executing and closing down server until it is closed elsewhere
      server.awaitTermination();
      //catch block throws either in-built exception
    } catch (IOException | InterruptedException e) {
      System.err.println("Error while running the server: " + e.getMessage());
    }
  }

  
  
  // logic handling  methods for eHealthRecords management service
  static class EHRManagementImpl extends EHRManagementGrpc.EHRManagementImplBase {
    //ArrayList to store patient records for search and update patient record methods
    List < SearchPatientRecordResponse > patientRecords = new ArrayList < > ();

    public EHRManagementImpl() {

      // add two mock patients for use in search and update methods
      patientRecords.add(SearchPatientRecordResponse.newBuilder()
        .setPatientId("AB001")
        .setPatientName("Frodo Baggins")
        .setDepartment("Emergency Department")
        .setDiagnosis("Hemmorhage")
        .setMedication("Ibuprofen")
        .setScheduledOperation("Blood Transfusion")
        .build());

      patientRecords.add(SearchPatientRecordResponse.newBuilder()
        .setPatientId("AB002")
        .setPatientName("Gandalf Grey")
        .setDepartment("Trauma and Orthopedic")
        .setDiagnosis("Burns")
        .setMedication("Antibiotics")
        .setScheduledOperation("Skin Graft")
        .build());
    }
    
    
    /*
     * Unary gRPC method - searchPatientRecords - handles request from client, searches patient in patientRecords list
     * sends response to client with patient info if found / empty response if not
     */

    // called by server when client requests to searchPatientRecord, called in GUI
    @Override
    // initialize SearchPatientRecordResponse object to store responses
    public void searchPatientRecord(SearchPatientRecordRequest request, StreamObserver <SearchPatientRecordResponse> responseObserver) {
      SearchPatientRecordResponse response = null;
      // for-each loop iterates through patientRecords and if patientID matches set the response to matched record
      for (SearchPatientRecordResponse record: patientRecords) {
        if (record.getPatientId().equals(request.getPatientId())) {
          response = record;

          // two simple printlines in terminal to confirm if patient was found or not
          System.out.println("Patient ID " + request.getPatientId() + " was found!");
        }
      }

      if (response == null) {
        System.out.println("Patient ID " + request.getPatientId() + " was searched for but no match was found.");
        response = SearchPatientRecordResponse.getDefaultInstance();
      }
      // send response back to client and inform server that request is complete
      responseObserver.onNext(response);
      responseObserver.onCompleted();
    }

    
    
    
    /*
     * Unary update patient method - single request and response.
     * take UpdatePatientRecordRequest as input, send back UpdatePatientRecordResponse using streamObserver
     */
    @Override
    // take update patient record request and use response observer to send response back to the client
    public void updatePatientRecord(UpdatePatientRecordRequest request, StreamObserver <UpdatePatientRecordResponse> responseObserver) {

    	// response object created using .newbuilder()
      UpdatePatientRecordResponse response = UpdatePatientRecordResponse.newBuilder()
        .setSuccess(true)
        .setMessage("Patient record updated successfully.")
        .build();

      // sends response back to client then inform client server is done processing request
      responseObserver.onNext(response);
      responseObserver.onCompleted();
    };

    
    
    /*
     * Client-streaming gRPC method - sharePatientRecords - accepts int value for # of records to be shared and sharePatientListener as callback
     * client sends multiple patient records to server, server side onNext() returns accumulates requests and returns single response of records shared
     */
  
 // takes in two StreamObserver objs for request and response
    @Override
    public StreamObserver <SharePatientRecordRequest> sharePatientRecord(StreamObserver <SharePatientRecordResponse> responseObserver) {
      // return Obj which will define onNext, onError and onComplete methods
      return new StreamObserver <SharePatientRecordRequest> () {
        // onNext is called when the client sends a new request, do nothing in this case.
        @Override
        public void onNext(SharePatientRecordRequest request) {
        }
        
        // error handling
        @Override
        public void onError(Throwable t) {
          System.err.println("Error while receiving patient record: " + t.getMessage());
        }
        
        // server has received all requests, now build single response and return
        @Override
        public void onCompleted() {
            SharePatientRecordResponse response = SharePatientRecordResponse.newBuilder()
                    .setSuccess(true)
                    .setMessage("Patient record received and stored successfully.")
                    .build();
          responseObserver.onNext(response);
          responseObserver.onCompleted();
        }
      };
    }
  }
}