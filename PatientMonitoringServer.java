package com.example.health.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import com.example.health.grpc.RealTimePatientMonitoring.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
            StreamPatientInfoResponse response = StreamPatientInfoResponse.newBuilder()
                    .setHeartRate(75.0)
                    .setOxygenSaturation(98.0)
                    .setBloodPressure(120.0)
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

        @Override
        public void streamMedicalAlerts(StreamMedicalAlertsRequest request, StreamObserver<StreamMedicalAlertsResponse> responseObserver) {
            StreamMedicalAlertsResponse response = StreamMedicalAlertsResponse.newBuilder()
                    .setDiagnosis("High blood pressure")
                    .setTreatment("Lifestyle changes and medication")
                    .build();
            responseObserver.onNext(response);
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