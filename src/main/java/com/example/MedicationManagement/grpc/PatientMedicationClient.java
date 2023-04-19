package com.example.MedicationManagement.grpc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;

public class PatientMedicationClient {
    private static MedicationManagementGrpc.MedicationManagementBlockingStub blockingStub;
    private static MedicationManagementGrpc.MedicationManagementStub asyncStub;
    private static ServiceInfo medicationManagementServiceInfo;

    public PatientMedicationClient() {
    }

    public PatientMedicationClient(ManagedChannel channel) {
        blockingStub = MedicationManagementGrpc.newBlockingStub(channel);
        asyncStub = MedicationManagementGrpc.newStub(channel);
    }



    public static void main(String[] args) throws InterruptedException {

        PatientMedicationClient client = new PatientMedicationClient();
        
        String medicationManagement_service_type = "_patient_medication._tcp.local.";
        client.discoverMedicationManagementService(medicationManagement_service_type);

        String host = medicationManagementServiceInfo.getHostAddresses()[0];
        int port = medicationManagementServiceInfo.getPort();

        // managed channel is created using the host and port info taken from the medmanagementServiceInfo
        final ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();

        // stubs created using the already generated medmgmtGrpc class
        // blocking stub sends request to client and waits for response until proceeding
        blockingStub = MedicationManagementGrpc.newBlockingStub(channel);
        // async sends a request to server but still executes regardless of response
        asyncStub = MedicationManagementGrpc.newStub(channel);

        Scanner myInput = new Scanner(System.in);
        
        // take in user input to assign a new medicine to a patient
        System.out.println("Please enter patient ID:");
        String patientId = myInput.nextLine();
        System.out.println("Please enter medication name:");
        String medicationName = myInput.nextLine();
        System.out.println("Please enter medication dosage:");
        String dosage = myInput.nextLine();
        System.out.println("Please enter medication side effects:");
        String sideEffects = myInput.nextLine();
        client.addMedication(patientId, medicationName, dosage, sideEffects);
        
    
        // Take user input for blood sugar level readings and call adjustDosage method
        System.out.println("Please enter the patient initial blood sugar level:");
        int bloodSugarLevelReadings = myInput.nextInt();
        myInput.nextLine(); // To consume the newline character
        client.adjustDosage(bloodSugarLevelReadings);

        
 
      client.confirmMedication(medicationName, dosage);

        channel.shutdown().awaitTermination(30, TimeUnit.SECONDS);
    
    }

    // creates the jmDNS instance  
    public void discoverMedicationManagementService(String service_type) {
        try {
            JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
            // create a 'Service Listener' which listens for service events (service is added removed or resolved)
            // also takes info about the connection
            jmdns.addServiceListener(service_type, new ServiceListener() {
            	// service revolved event presents all of the info below describing the discovered service
            	@Override
            	public void serviceResolved(ServiceEvent event) {
     

            	    medicationManagementServiceInfo = event.getInfo();

            	    int port = medicationManagementServiceInfo.getPort();
            	    String host = medicationManagementServiceInfo.getHostAddresses()[0];

            	    // Create the PatientMedicationClient instance with the host and port

            	    System.out.println("Resolving with properties:");
            	    System.out.println("Port: " + port);
            	    System.out.println("Type: " + event.getType());
            	    System.out.println("Name: " + event.getName());
            	    System.out.println("Host: " + host);
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

    public String addMedication(String patientId, String medicationName, String dosage, String sideEffects) {
        AddMedicationRequest request = AddMedicationRequest.newBuilder()
                .setPatientId(patientId)
                .setMedicationName(medicationName)
                .setDosage(dosage)
                .setSideEffects(sideEffects)
                .build();

        AddMedicationResponse response = blockingStub.addMedication(request);

        if (response.getSuccess()) {
            String successMessage = "PatientID " + patientId + " has had medicine " + medicationName + " added to their prescription. \n";
            System.out.println(successMessage);
            return successMessage;
        } else {
            String failureMessage = "Failed to add medicine for patient ID: " + patientId;
            System.out.println(failureMessage);
            return failureMessage;
        }
    }

    
    public List<String> adjustDosage(float initialBloodSugarLevel) {
        List<String> outputMessages = new ArrayList<>();

        StreamObserver<AdjustDosageRequest> requestObserver = asyncStub.adjustDosage(new StreamObserver<AdjustDosageResponse>() {
            @Override
            public void onNext(AdjustDosageResponse response) {
                float adjustedInsulin = response.getAdjustedInsulin();
                float adjustedBloodSugar = response.getAdjustedBloodSugar();
                String adjustedInsulinText = "Adjusted insulin value: " + adjustedInsulin;
                String adjustedBloodSugarText = "Adjusted blood sugar value: " + adjustedBloodSugar;
                outputMessages.add(adjustedInsulinText + "\n" + adjustedBloodSugarText + "\n");
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("Error in dynamic adjustDosage method: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("Dynamic dosage adjustment completed!");
            }
        });
     // iterate 110 times
        float bloodSugarLevel = initialBloodSugarLevel;
        for (int i = 0; i < 10; i++) { 
        	// math.random plus logic to give random blood sugar results based off initial input
        	// made up values, patient would be very dead in real life
            bloodSugarLevel += (Math.random() * 3) - 1.5; 
            requestObserver.onNext(AdjustDosageRequest.newBuilder()
                    .setBloodSugarLevel(bloodSugarLevel)
                    .build());

            try {
            	// short delay between requests
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        requestObserver.onCompleted();
        return outputMessages;
    }

    
    public List<String> confirmMedication(String name, String dosage) {
        List<String> outputMessages = new ArrayList<>();

        ConfirmMedicationRequest request = ConfirmMedicationRequest.newBuilder()
                .setMedicationName(name)
                .setDosage(dosage)
                .build();

        StreamObserver<ConfirmMedicationRequest> requestObserver = asyncStub.confirmMedication(new StreamObserver<ConfirmMedicationResponse>() {
            @Override
            public void onNext(ConfirmMedicationResponse response) {
                outputMessages.add("Confirm medication response: " + response.getMessage() + "\n");
                outputMessages.add("Contraindications: " + response.getContraindications() + "\n");
                outputMessages.add("Administration instructions: " + response.getAdministrationInstructions() + "\n");
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("Error in confirmMedication: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("Confirm medication completed.");
            }
        });

        // Send medication request to the server
        requestObserver.onNext(request);
        requestObserver.onCompleted();

        // Wait for the server to send back the response
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return outputMessages;
    }
    }
    

