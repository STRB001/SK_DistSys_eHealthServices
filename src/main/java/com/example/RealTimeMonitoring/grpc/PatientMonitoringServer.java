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
 
    }

    private void start() throws IOException {
    	
    	try {
        int port = 50051;
        server = ServerBuilder.forPort(port)
                .addService(new PatientMonitoringServiceImpl())
                .build()
                .start();
        System.out.println("Patient Monitoring Server started, listening on " + port);


        // registering  the server with jmDNS
        JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
        ServiceInfo serviceInfo = ServiceInfo.create("_realtime_monitoring._tcp.local.", "PatientMonitoringServer", port, "path=/");
        jmdns.registerService(serviceInfo);
        System.out.println("jmDNS registration complete with type" + serviceInfo.getType() + " and " + serviceInfo.getName());

        // Blocking until shutdown
        server.awaitTermination();
    } catch (IOException | InterruptedException e) {
        System.err.println("Error while running the server: " + e.getMessage());
    }
        }
    

    private class PatientMonitoringServiceImpl extends PatientMonitoringGrpc.PatientMonitoringImplBase {

        @Override
        public void addPatient(AddPatientRequest request, StreamObserver<AddPatientResponse> responseObserver) {
            Patient patient = new Patient(request.getPatientName(), request.getPatientAge(), request.getPatientId());
            patients.add(patient);

            AddPatientResponse response = AddPatientResponse.newBuilder()
                    .setSuccess(true)
                    .setMessage("Patient " + request.getPatientName() + ", " + request.getPatientAge() + ", " + request.getPatientId() + " added to Patients successfully.")
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

        @Override
        public void streamPatientInfo(StreamPatientInfoRequest request, StreamObserver<StreamPatientInfoResponse> responseObserver) {
            for (int i = 0; i < 15; i++) {
                int heartRateRandom = (int) (75.0 + (Math.random() * 4 - 2));
                int oxygenSaturationRandom = (int) (98.0 + (Math.random() * 4 - 2));
                int bloodPressureRandom = (int) (120.0 + (Math.random() * 4 - 2));

                StreamPatientInfoResponse response = StreamPatientInfoResponse.newBuilder()
                        .setHeartRate(heartRateRandom)
                        .setOxygenSaturation(oxygenSaturationRandom)
                        .setBloodPressure(bloodPressureRandom)
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
            for (int i = 0; i < 15; i++) {
                StreamMedicalAlertsResponse response;

                if (i < 10) {
                    response = StreamMedicalAlertsResponse.newBuilder()
                            .setDiagnosis("No medical alert")
                            .setTreatment("N/A - Patient is stable")
                            .build();
                } else {
                    response = StreamMedicalAlertsResponse.newBuilder()
                            .setDiagnosis("ALERT: Patient heart rate failure!")
                            .setTreatment("Deliver Adrenaline ASAP!")
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