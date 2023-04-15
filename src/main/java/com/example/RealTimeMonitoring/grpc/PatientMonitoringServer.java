package com.example.RealTimeMonitoring.grpc;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class PatientMonitoringServer {

    private Server server;
    private List<Patient> patients = new ArrayList<>();

    public static void main(String[] args) throws IOException, InterruptedException {
        final PatientMonitoringServer server = new PatientMonitoringServer();
        server.start();
        server.blockUntilShutdown();
    }

    private void start() throws IOException {
        int port = 50051;
        server = ServerBuilder.forPort(port)
                .addService(new PatientMonitoringServiceImpl())
                .build()
                .start();
        System.out.println("Server started, listening on " + port);


        // Register the server with jmDNS
        JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
        ServiceInfo serviceInfo = ServiceInfo.create("_grpc._tcp.local.", "PatientMonitoringServer", port, "path=/");
        jmdns.registerService(serviceInfo);
        System.out.printf("Registered service with type %s and name %s \n", serviceInfo.getType(), serviceInfo.getName());

    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    private class PatientMonitoringServiceImpl extends PatientMonitoringGrpc.PatientMonitoringImplBase {

        @Override
        public void addPatient(AddPatientRequest request, StreamObserver<AddPatientResponse> responseObserver) {
            Patient patient = new Patient(request.getPatientName(), request.getPatientAge(), request.getPatientId());
            patients.add(patient);

            AddPatientResponse response = AddPatientResponse.newBuilder()
                    .setSuccess(true)
                    .setMessage("Patient added successfully.")
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

        @Override
        public void streamPatientInfo(StreamPatientInfoRequest request, StreamObserver<StreamPatientInfoResponse> responseObserver) {
            for (int i = 0; i < 3; i++) {
                StreamPatientInfoResponse response = StreamPatientInfoResponse.newBuilder()
                        .setHeartRate(75.0)
                        .setOxygenSaturation(98.0)
                        .setBloodPressure(120.0)
                        .build();
                responseObserver.onNext(response);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            responseObserver.onCompleted();
        }

        @Override
        public void streamMedicalAlerts(StreamMedicalAlertsRequest request, StreamObserver<StreamMedicalAlertsResponse> responseObserver) {
            for (int i = 0; i < 5; i++) {
                StreamMedicalAlertsResponse response;

                if (i < 3) {
                    response = StreamMedicalAlertsResponse.newBuilder()
                            .setDiagnosis("Currently medical alert")
                            .setTreatment("Patient is stable")
                            .build();
                } else {
                    response = StreamMedicalAlertsResponse.newBuilder()
                            .setDiagnosis("Alert! Diagnosis: Patient heart rate unstable!")
                            .setTreatment("Deliver beta blockers asap!")
                            .build();
                }

                responseObserver.onNext(response);

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            responseObserver.onCompleted();
        }
    }

    private static class Patient {
        private String name;
        private int age;
        private String id;

        public Patient(String name, int age, String id) {
            this.name = name;
            this.age = age;
            this.id = id;
        }
   
    }
}