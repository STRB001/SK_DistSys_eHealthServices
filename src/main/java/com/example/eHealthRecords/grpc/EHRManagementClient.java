package com.example.eHealthRecords.grpc;

import java.util.Scanner;

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
    
    
    public EHRManagementClient(){
  	  
    }

    
    public EHRManagementClient (ManagedChannel EHRManagementChannel) {
  	  blockingStub = EHRManagementGrpc.newBlockingStub(EHRManagementChannel);
  	  asyncStub = EHRManagementGrpc.newStub(EHRManagementChannel);
    }
    
    
    public static void main(String[] args) throws InterruptedException {
        EHRManagementClient EHRManagementClient = new EHRManagementClient();

        String ehrManagement_service_type = "_ehr_management._tcp.local.";

        EHRManagementClient.discoverEHRManagementService(ehrManagement_service_type);

        String host = EHRManagementServiceInfo.getHostAddresses()[0];
        int port = EHRManagementServiceInfo.getPort();

        
        final ManagedChannel EHRManagementChannel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();

        blockingStub = EHRManagementGrpc.newBlockingStub(EHRManagementChannel);
        asyncStub = EHRManagementGrpc.newStub(EHRManagementChannel);

        
        System.out.println("Please enter the Patient ID:");
        String patientId = "";
        Scanner myInput = new Scanner(System.in);
        patientId = myInput.next();

        
        EHRManagementClient.searchPatientRecord(patientId);


        EHRManagementChannel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    private void discoverEHRManagementService(String service_type) {
        try {
            JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

            jmdns.addServiceListener(service_type, new ServiceListener() {
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
            Thread.sleep(300);
            jmdns.close();

        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

 
    public String searchPatientRecord(String patientId) {
        SearchPatientRecordRequest request = SearchPatientRecordRequest.newBuilder()
                .setPatientId(patientId)
                .build();
        try {
            SearchPatientRecordResponse response = blockingStub.searchPatientRecord(request);
            if (!response.getPatientId().isEmpty()) {
                return "Success! Patient found:\n"
                        + "Patient ID: " + response.getPatientId() + "\n"
                        + "Name: " + response.getPatientName() + "\n"
                        + "Department: " + response.getDepartment() + "\n"
                        + "Diagnosis: " + response.getDiagnosis() + "\n"
                        + "Medication: " + (response.getMedication().isEmpty() ? "None" : response.getMedication()) + "\n"
                        + "Scheduled Operation: " + (response.getScheduledOperation().isEmpty() ? "None" : response.getScheduledOperation()) + "\n";
            } else {
                return "Patient ID " + patientId + " was searched but no matching ID found.";
            }
        } catch (StatusRuntimeException e) {
            return "RPC failed: " + e.getStatus();
        }
    }

    public void updatePatientRecord(String patientId, String name, String department, String diagnosis, String medication, String operation) {
        UpdatePatientRecordRequest request = UpdatePatientRecordRequest.newBuilder()
                .setPatientId(patientId)
                .setName(name)
                .setDepartment(department)
                .setDiagnosis(diagnosis)
                .setMedication(medication)
                .setOperation(operation)
                .build();
        try {
            UpdatePatientRecordResponse response = blockingStub.updatePatientRecord(request);
            System.out.println("Update patient record result:\n" + response.getMessage());
        } catch (StatusRuntimeException e) {
            System.err.println("RPC failed: " + e.getStatus());
        }
    }
    
    
    
    public SearchPatientRecordResponse getPatientRecord(String patientId) {
        SearchPatientRecordRequest request = SearchPatientRecordRequest.newBuilder()
                .setPatientId(patientId)
                .build();
        try {
            SearchPatientRecordResponse response = blockingStub.searchPatientRecord(request);
            return response;
        } catch (StatusRuntimeException e) {
            System.err.println("RPC failed: " + e.getStatus());
            return null;
        }
    }

    public interface OutputCallback {
	    void onOutput(String output);
	} 
    
    public void sharePatientRecord(int numPatients, OutputCallback outputCallback) {
        StreamObserver<SharePatientRecordResponse> responseObserver = new StreamObserver<SharePatientRecordResponse>() {
            @Override
            public void onNext(SharePatientRecordResponse response) {
                outputCallback.onOutput("Response: " + response.getMessage());
            }

            @Override
            public void onError(Throwable t) {
                outputCallback.onOutput("RPC failed: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                outputCallback.onOutput("Finished sharing patient record.");
            }
        };

        StreamObserver<SharePatientRecordRequest> requestObserver = asyncStub.sharePatientRecord(responseObserver);

        try {
            for (int i = 1; i <= numPatients; i++) {
                String recordContent = "PatientName" + i + "\nPatientAge" + i + "\nPatientAddress" + i;
                requestObserver.onNext(SharePatientRecordRequest.newBuilder()
                        .setPatientId("patient" + i)
                        .setRecordPart("Part " + i)
                        .setRecordContent(recordContent)
                        .build());
                outputCallback.onOutput(recordContent + "\n"); // Call the callback with the record content
            }
        } catch (RuntimeException e) {
            requestObserver.onError(e);
            throw e;
        }
        requestObserver.onCompleted();
}
}
