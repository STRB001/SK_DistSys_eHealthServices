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
        System.out.println("Patient Medication server started, listening on port " + port);
        
 
        // register the server with jmDNS
        JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
        // create serviceInfo obj, give it a type (grpc.tcp.loc) and a name (patientmedicationserv) then use the server (above) port and path are params
        ServiceInfo serviceInfo = ServiceInfo.create("_patient_medication._tcp.local.", "PatientMedicationServer", port, "path=/");
        jmdns.registerService(serviceInfo);
        // println to show it was created correctly
        System.out.println("jmDNS registration complete with type" + serviceInfo.getType() + " and name " + serviceInfo.getName());

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
        public StreamObserver<AdjustDosageRequest> adjustDosage(StreamObserver<AdjustDosageResponse> responseObserver) {
            return new StreamObserver<AdjustDosageRequest>() {
                @Override
                public void onNext(AdjustDosageRequest request) {
                    float bloodSugarLevel = request.getBloodSugarLevel();
                 // using a made up formula to adjust insulin dosage based off of the input for blood sugar level (just using 10% value)
                    float adjustedDosage = bloodSugarLevel * 0.1f;
                    // logic for adjusting blood sugar .random() is a decimal between 0-1, random()*3 is decimal between 0-3
                    // .random() * 3) - 1.5 shifts the range between -1.5 and 1.5 instead. now bloodsugar can be +/- 1.5 off of the user input value
                    float adjustedBloodSugar = bloodSugarLevel + (float) ((Math.random() * 3) - 1.5);

                    AdjustDosageResponse response = AdjustDosageResponse.newBuilder()
                            .setAdjustedInsulin(adjustedDosage)
                            .setAdjustedBloodSugar(adjustedBloodSugar)
                            .build();
                    responseObserver.onNext(response);
                }

                @Override
                public void onError(Throwable t) {
                    System.err.println("Error in adjustDosage: " + t.getMessage());
                }

                @Override
                public void onCompleted() {
                    responseObserver.onCompleted();
                }
            };
        }
    
        
        
        @Override
        public StreamObserver<ConfirmMedicationRequest> confirmMedication(StreamObserver<ConfirmMedicationResponse> responseObserver) {
            List<ConfirmMedicationRequest> medicationRequests = new ArrayList<>();

            return new StreamObserver<ConfirmMedicationRequest>() {
                @Override
                public void onNext(ConfirmMedicationRequest request) {
                    medicationRequests.add(request);
                }

                @Override
                public void onError(Throwable t) {
                    System.err.println("Error in confirmMedication: " + t.getMessage());
                }

                @Override
                public void onCompleted() {
                    // Process medication requests, check contraindications and provide administration instructions
                    List<String> contraindicationsList = new ArrayList<>();
                    List<String> administrationInstructionsList = new ArrayList<>();

                    for (ConfirmMedicationRequest request : medicationRequests) {
                        String medicationName = request.getMedicationName();
                        String dosage = request.getDosage();

                        // Add contraindications and administration instructions for each medication
                        contraindicationsList.add("For " + medicationName + ": Example contraindications.");
                        administrationInstructionsList.add("For " + medicationName + ": Example administration instructions.");
                    }

                    String contraindications = String.join("\n", contraindicationsList);
                    String administrationInstructions = String.join("\n", administrationInstructionsList);

                    ConfirmMedicationResponse response = ConfirmMedicationResponse.newBuilder()
                            .setMessage("Medication confirmation completed.")
                            .setContraindications(contraindications)
                            .setAdministrationInstructions(administrationInstructions)
                            .build();

                    responseObserver.onNext(response);
                    responseObserver.onCompleted();
                }
            };
        }
    }
}

