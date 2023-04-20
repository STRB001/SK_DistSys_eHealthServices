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

public class EHRManagementServer {
	
    private Server server;

   public static void main(String[] args) throws IOException, InterruptedException {
        EHRManagementServer server = new EHRManagementServer();
        server.start();

    }
    
    
    private void start() throws IOException {
    	try {
        int port = 50052;
        server = ServerBuilder.forPort(port)
                .addService(new EHRManagementImpl())
                .build()
                .start();
        System.out.println("EHR Management server started, listening on port " + port);

        // registering server with jmDNS
        JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
        ServiceInfo serviceInfo = ServiceInfo.create("_ehr_management._tcp.local.", "EHRManagementServer", port, "path=/");
        jmdns.registerService(serviceInfo);
        System.out.println("jmDNS registration complete with type" + serviceInfo.getType() + " and " + serviceInfo.getName());
    

        // Blocking until shutdown
        server.awaitTermination();
    } catch (IOException | InterruptedException e) {
        System.err.println("Error while running the server: " + e.getMessage());
    }
        }



    static class EHRManagementImpl extends EHRManagementGrpc.EHRManagementImplBase {
        List<SearchPatientRecordResponse> patientRecords = new ArrayList<>();

        public EHRManagementImpl() {
        patientRecords.add(SearchPatientRecordResponse.newBuilder()
                .setPatientId("AB001")
                .setPatientName("Frodo Baggins")
                .setDepartment("Emergency Department")
                .setDiagnosis("Internal Bleeding/Hemmorhage")
                .setMedication("Ibuprofen")
                .setScheduledOperation("Blood Transfusion")
                .build());
        
        // Adding another patient record
        patientRecords.add(SearchPatientRecordResponse.newBuilder()
                .setPatientId("AB002")
                .setPatientName("Gandalf Grey")
                .setDepartment("Trauma and Orthopedic")
                .setDiagnosis("Burns")
                .setMedication("Antibiotics")
                .setScheduledOperation("Skin Graft")
                .build());
    }
        
       
        @Override
        public void searchPatientRecord(SearchPatientRecordRequest request, StreamObserver<SearchPatientRecordResponse> responseObserver) {
            SearchPatientRecordResponse response = null;
            for (SearchPatientRecordResponse record : patientRecords) {
                if (record.getPatientId().equals(request.getPatientId())) {
                    response = record;
                    System.out.println("Searched patient record for patient ID: " + request.getPatientId() + " - Result: ");
                    System.out.println(response.toString());
                    break;
                }
            }

            if (response == null) {
                System.out.println("Patient ID " + request.getPatientId() + " was searched for but no match was found.");
                response = SearchPatientRecordResponse.getDefaultInstance();
            }

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
        

        @Override
        public void updatePatientRecord(UpdatePatientRecordRequest request,
            StreamObserver<UpdatePatientRecordResponse> responseObserver) {
            // Don't have an actual database or anywhere to store all of the records
            // just pretending the update is successful.
            UpdatePatientRecordResponse response = UpdatePatientRecordResponse.newBuilder()
                    .setSuccess(true)
                    .setMessage("Patient record updated successfully.")
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        };

        @Override
        public StreamObserver<SharePatientRecordRequest> sharePatientRecord(
                StreamObserver<SharePatientRecordResponse> responseObserver) {
            return new StreamObserver<SharePatientRecordRequest>() {
                StringBuilder receivedData = new StringBuilder();

                @Override
                public void onNext(SharePatientRecordRequest request) {
                    receivedData.append("Part: ")
                            .append(request.getRecordPart())
                            .append(", Content: ")
                            .append(request.getRecordContent())
                            .append("\n");
                }

                @Override
                public void onError(Throwable t) {
                    System.err.println("Error while receiving patient record: " + t.getMessage());
                }

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