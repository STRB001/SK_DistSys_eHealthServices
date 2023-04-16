package com.example.MedicationManagement.grpc;

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

        String medicationManagement_service_type = "_grpc._tcp.local.";

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
        
    
        // take user input (patientID) to receive the medicine schedule
        System.out.println("Please enter patient ID in order to receive medication schedule");
        patientId = myInput.nextLine();
        client.getMedicationSchedule(patientId, 1);
        

        client.confirmMedication();

        channel.shutdown().awaitTermination(10, TimeUnit.SECONDS);
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
            return "PatientID " + patientId + " has had medicine " + medicationName + " added to their prescription.";
        } else {
            return "Failed to add medicine for patient ID: " + patientId;
        }
    }

    public void getMedicationSchedule(String patientId, int days) {
        GetMedicationScheduleRequest request = GetMedicationScheduleRequest.newBuilder()
                .setPatientId(patientId)
                .setDays(days)
                .build();

        blockingStub.getMedicationSchedule(request).forEachRemaining(response ->
                System.out.println("Medication schedule for Patient " + patientId + ": take " + response.getMedicationName() + " at " + response.getScheduledTime())
        );
    }
    
    public void confirmMedication() {
   
        StreamObserver<ConfirmMedicationRequest> requestObserver = asyncStub.confirmMedication(new StreamObserver<ConfirmMedicationResponse>() {
        	@Override
        	public void onNext(ConfirmMedicationResponse response) {
                System.out.println("Confirm medication response: " + response.getMessage());
                System.out.println("Contraindications: " + response.getContraindications());
                System.out.println("Administration instructions: " + response.getAdministrationInstructions());
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

        // Send sample medications for confirmation - counter i is used in place of medicine names for now, so it's just medicine 1, medicine 2... etc.
        for (int i = 1; i <= 3; i++) {
            requestObserver.onNext(ConfirmMedicationRequest.newBuilder()
                    .setMedicationName("Medication " + i)
                    .setDosage("500mg")
                    .build());
        }
    
        // allow time for the server to process requests and send back responses
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    
        requestObserver.onCompleted();
    }
}
