package com.example.eHealthRecords.grpc;


import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;

public class EHRManagementClient {
    private static EHRManagementGrpc.EHRManagementBlockingStub blockingStub;
    private static EHRManagementGrpc.EHRManagementStub asyncStub;
    private static ServiceInfo ehrManagementServiceInfo;

    
    
    public static void main(String[] args) throws InterruptedException {
        EHRManagementClient client = new EHRManagementClient();

        String ehrManagement_service_type = "_grpc._tcp.local.";

        client.discoverEHRManagementService(ehrManagement_service_type);

        String host = ehrManagementServiceInfo.getHostAddresses()[0];
        int port = ehrManagementServiceInfo.getPort();

        final ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();

        blockingStub = EHRManagementGrpc.newBlockingStub(channel);
        asyncStub = EHRManagementGrpc.newStub(channel);

        String patientId = "123456";
        client.searchPatientRecord(patientId);
        client.updatePatientRecord(patientId, "Updated diagnosis: Diabetes");
        client.sharePatientRecord(patientId);

        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    private void discoverEHRManagementService(String service_type) {
        try {
            JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

            jmdns.addServiceListener(service_type, new ServiceListener() {
                @Override
                public void serviceResolved(ServiceEvent event) {

                    ehrManagementServiceInfo = event.getInfo();

                    int port = ehrManagementServiceInfo.getPort();
                    String host = ehrManagementServiceInfo.getHostAddresses()[0];

                    System.out.println("resolving " + service_type + " with properties ...");
                    System.out.println("\t port: " + port);
                    System.out.println("\t type:" + event.getType());
                    System.out.println("\t name: " + event.getName());
                    System.out.println("\t host: " + host);
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
         // set to 2000ms to give jmDNS more time to discover. stops random failure of methods sometimes
            Thread.sleep(2000);
            jmdns.close();

        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

 
    public void searchPatientRecord(String patientId) {
        SearchPatientRecordRequest request = SearchPatientRecordRequest.newBuilder()
                .setPatientId(patientId)
                .build();
        try {
            SearchPatientRecordResponse response = blockingStub.searchPatientRecord(request);
            System.out.println("Patient record:\n" + response.toString());
        } catch (StatusRuntimeException e) {
            System.err.println("RPC failed: " + e.getStatus());
        }
    }

    public void updatePatientRecord(String patientId, String updatedInformation) {
        UpdatePatientRecordRequest request = UpdatePatientRecordRequest.newBuilder()
                .setPatientId(patientId)
                .setUpdatedInformation(updatedInformation)
                .build();
        try {
            UpdatePatientRecordResponse response = blockingStub.updatePatientRecord(request);
            System.out.println("Update patient record result:\n" + response.getMessage());
        } catch (StatusRuntimeException e) {
            System.err.println("RPC failed: " + e.getStatus());
        }
    }

    public void sharePatientRecord(String patientId) {
        StreamObserver<SharePatientRecordResponse> responseObserver = new StreamObserver<SharePatientRecordResponse>() {
            @Override
            public void onNext(SharePatientRecordResponse response) {
                System.out.println("Patient record shared:\n" + response.getMessage());
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("RPC failed: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("Finished sharing patient record.");
            }
        };

        StreamObserver<SharePatientRecordRequest> requestObserver = asyncStub.sharePatientRecord(responseObserver);
        try {
            for (int i = 1; i <= 3; i++) {
                requestObserver.onNext(SharePatientRecordRequest.newBuilder()
                        .setPatientId(patientId)
                        .setRecordPart("Part " + i)
                        .setRecordContent("Content " + i)
                        .build());
            }
        } catch (RuntimeException e) {
            requestObserver.onError(e);
            throw e;
        }
        requestObserver.onCompleted();
    }
}
