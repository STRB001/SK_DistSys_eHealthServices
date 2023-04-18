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
        server.blockUntilShutdown();
    }
    
    
    private void start() throws IOException {
        int port = 50052;
        server = ServerBuilder.forPort(port)
                .addService(new EHRManagementImpl())
                .build()
                .start();
        System.out.println("EHR Management server started, listening on port " + port);

        // registering server with jmDNS
        JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
        ServiceInfo serviceInfo = ServiceInfo.create("_grpc._tcp.local.", "EHRManagementServer", port, "path=/");
        jmdns.registerService(serviceInfo);
        System.out.println("Registered service with type" + serviceInfo.getType() + " and " + serviceInfo.getName());
    }


    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }



    static class EHRManagementImpl extends EHRManagementGrpc.EHRManagementImplBase {
        List<SearchPatientRecordResponse> patientRecords = new ArrayList<>();

        public EHRManagementImpl() {
        patientRecords.add(SearchPatientRecordResponse.newBuilder()
                .setPatientId("P123")
                .setPatientName("Jon Snow")
                .setDepartment("Emergency Department")
                .setDiagnosis("Internal Bleeding/Hemmorhage")
                .setMedication("Ibuprofen")
                .setScheduledOperation("Blood Transfusion")
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
                System.out.println("Patient ID " + request.getPatientId() + " was searched but no matching patient found.");
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
                    System.out.println("Received patient record: " + receivedData.toString());
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