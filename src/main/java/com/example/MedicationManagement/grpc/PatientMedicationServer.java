package com.example.MedicationManagement.grpc;


import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;


public class PatientMedicationServer {
    private Server server;

    public static void main(String[] args) throws IOException, InterruptedException {
        PatientMedicationServer server = new PatientMedicationServer();
        server.start();
        server.blockUntilShutdown();
    }

    	// build server
    public void start() throws IOException {
        int port = 50053;
        server = ServerBuilder.forPort(port)
                .addService(new MedicationManagementImpl())
                .build()
                .start();
        System.out.println("Server started, listening on " + port);
        
 
        // register the server with jmDNS
        JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
        // create serviceInfo obj, give it a type (grpc.tcp.loc) and a name (patientmedicationserv) then use the server (above) port and path are params
        ServiceInfo serviceInfo = ServiceInfo.create("_grpc._tcp.local.", "PatientMedicationServer", port, "path=/");
        jmdns.registerService(serviceInfo);
        // println to show it was created correctly
        System.out.printf("Registered jmDNS service with type" + serviceInfo.getType() + " and name " + serviceInfo.getName());

    }

    private void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    static class MedicationManagementImpl extends MedicationManagementGrpc.MedicationManagementImplBase {
        List<AddMedicationRequest> medications = new ArrayList<>();

        @Override
        public void addMedication(AddMedicationRequest request, StreamObserver<AddMedicationResponse> responseObserver) {
            medications.add(request);
            AddMedicationResponse response = AddMedicationResponse.newBuilder()
                    .setSuccess(true)
                    .setMessage("Medication added successfully.")
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

        @Override
        public void getMedicationSchedule(GetMedicationScheduleRequest request, StreamObserver<GetMedicationScheduleResponse> responseObserver) {
            // made up medication schedule - to be updated later
            String[] times = {"08:00", "10:00", "12:00", "14:00", "16:00", "18:00", "20:00"};
            // for each loop taking the array of times and passing it to responseObserver.onNext
            for (String time : times) {
                GetMedicationScheduleResponse response = GetMedicationScheduleResponse.newBuilder()
                        .setMedicationName("Paracetamol")
                        .setScheduledTime(time)
                        .build();

                responseObserver.onNext(response);
            }

            responseObserver.onCompleted();
        }

        @Override
        public StreamObserver<ConfirmMedicationRequest> confirmMedication(StreamObserver<ConfirmMedicationResponse> responseObserver) {
            return new StreamObserver<ConfirmMedicationRequest>() {
                @Override
                public void onNext(ConfirmMedicationRequest request) {
                    ConfirmMedicationResponse response = ConfirmMedicationResponse.newBuilder()
                            .setMessage("Confirmation for " + request.getMedicationName())
                            .setContraindications("No contraindications")
                            .setAdministrationInstructions("Take with a glass of water and not on an empty stomach")
                            .build();
                    responseObserver.onNext(response);
                }

                @Override
                public void onError(Throwable t) {
                    System.err.println("Error in confirmMedication: " + t.getMessage());
                }

                @Override
                public void onCompleted() {
                    responseObserver.onCompleted();
                }
            };
        }
    }
}