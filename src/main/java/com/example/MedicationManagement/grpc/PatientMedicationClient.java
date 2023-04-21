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
    // create service specific ServiceInfo, allows for discovery of grpc services and generate client code specific to the server
    private static ServiceInfo medicationManagementServiceInfo;
    private ManagedChannel channel;

    // constructor
    public PatientMedicationClient() {
    }
    
 // constructor accepting a ManagedChannel obj as parameter to create grpc connection
    public PatientMedicationClient(ManagedChannel channel) {
    	this.channel = channel;
    	// create stubs - these provide grpc methods on server
        blockingStub = MedicationManagementGrpc.newBlockingStub(channel);
        asyncStub = MedicationManagementGrpc.newStub(channel);
    }
    // shutdown method to close the channel when called
    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public static void main(String[] args) throws InterruptedException {
        PatientMedicationClient client = new PatientMedicationClient();
        
     // discover service by service type
        String medicationManagement_service_type = "_patient_medication._tcp.local.";
        client.discoverMedicationManagementService(medicationManagement_service_type);

        String host = medicationManagementServiceInfo.getHostAddresses()[0];
        int port = medicationManagementServiceInfo.getPort();

     // managed channel is created using the host and port obtained using serviceInfo methods
        final ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();

        //blocking grpc stub is used when client is waiting for serv to response before continuing
        // asynchronous grpc stub when no wait is needed for response. Server responds and a callback handles the response (streaming)
        blockingStub = MedicationManagementGrpc.newBlockingStub(channel);
        asyncStub = MedicationManagementGrpc.newStub(channel);

        // close channel
        channel.shutdown();
    }

    // jmDNS service discovery - method takes service_type as param
    // service type is a string representing type of service to be discovered 
    public void discoverMedicationManagementService(String service_type) {
        try {
        	// create instance of jmDNS using host local inetaddress
            JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
            // create a 'Service Listener' which listens for service events (service is added removed or resolved)
            jmdns.addServiceListener(service_type, new ServiceListener() {
            	
            	// when service is discovered + resolved, extract port, host, and give brief print out of info of service 
            	@Override
            	public void serviceResolved(ServiceEvent event) {
     
            	
            	    medicationManagementServiceInfo = event.getInfo();
            	    int port = medicationManagementServiceInfo.getPort();
            	    String host = medicationManagementServiceInfo.getHostAddresses()[0];

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
         // close jmDNS service, service info already obtained and stored
            jmdns.close();
         // exception handling + messages
        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // addMedication takes in four Strings as params then uses .build() to create request obj
    public String addMedication(String patientId, String medicationName, String dosage, String sideEffects) {
    	// proto buffer message that contains info needed to add a new medication to a patient
        AddMedicationRequest request = AddMedicationRequest.newBuilder()
                .setPatientId(patientId)
                .setMedicationName(medicationName)
                .setDosage(dosage)
                .setSideEffects(sideEffects)
                .build();
        // send the request to server and get 'response' back
        // blockingStub used because we don't want to proceed until response is returned
        AddMedicationResponse response = blockingStub.addMedication(request);

        //  message when patient medication added successfully
        if (response.getSuccess()) {
            String successMessage = "PatientID " + patientId + " has had medicine " + medicationName + " added to their prescription. \n";
            System.out.println(successMessage);
            return successMessage;
            // failure message
        } else {
            String failureMessage = "Failed to add medicine for patient ID: " + patientId;
            System.out.println(failureMessage);
            return failureMessage;
        }
    }

    
    public interface AdjustDosageOutputCallback {
        void onMessageReceived(String message);
    }
    
    public void adjustDosage(float initialBloodSugarLevel, AdjustDosageOutputCallback callback) {
        StreamObserver<AdjustDosageRequest> requestObserver = asyncStub.adjustDosage(new StreamObserver<AdjustDosageResponse>() {
            @Override
            public void onNext(AdjustDosageResponse response) {
                float adjustedInsulin = response.getAdjustedInsulin();
                float adjustedBloodSugar = response.getAdjustedBloodSugar();
                String adjustedInsulinText = "Adjusted insulin value: " + adjustedInsulin;
                String adjustedBloodSugarText = "Adjusted blood sugar value: " + adjustedBloodSugar;
                String message = adjustedInsulinText + "\n" + adjustedBloodSugarText + "\n";
                callback.onMessageReceived(message);
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
     // iterate 10 times
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

    }

    
    
    public interface MedicationConfirmationCallback {
        void onMedicationConfirmation(String message);
    }
    
    
    public void confirmMedication(String name, String dosage, MedicationConfirmationCallback callback) {
        ConfirmMedicationRequest request = ConfirmMedicationRequest.newBuilder()
                .setMedicationName(name)
                .setDosage(dosage)
                .build();

        StreamObserver<ConfirmMedicationRequest> requestObserver = asyncStub.confirmMedication(new StreamObserver<ConfirmMedicationResponse>() {
            @Override
            public void onNext(ConfirmMedicationResponse response) {
                String outputMessage = "Confirm medication response: " + response.getMessage() + "\n";
                outputMessage += "Contraindications: " + response.getContraindications() + "\n";
                outputMessage += "Administration instructions: " + response.getAdministrationInstructions() + "\n\n";
                callback.onMedicationConfirmation(outputMessage);
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
    }
}
