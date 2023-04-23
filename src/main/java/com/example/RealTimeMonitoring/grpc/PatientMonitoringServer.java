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

//create the main class and declare server for reference later to server methods
public class PatientMonitoringServer {

  private Server server;
  // arraylist of Patient objects to 'store' patients in the addPatient method
  private List <Patient> patients = new ArrayList < > ();

  // create an instance of PatientMonitoringServer and call start method
  public static void main(String[] args) throws IOException, InterruptedException {
    final PatientMonitoringServer server = new PatientMonitoringServer();
    server.start();
  }

  
  // start() initialises the server, starts the jmDNS discovery service, and block main thread until server is shut down
  private void start() throws IOException {
    try {
      int port = 50051;
      server = ServerBuilder.forPort(port)
        // adds PMS interface to the server so that when the client sends request to the server for a method defined in PMS
        // the server can then use PMSImpl to handle the request and give a response 
        // (PatientMonitoringServiceImpl contains the actual logic for grpc request handling)
        .addService(new PatientMonitoringServiceImpl())
        .build()
        .start();
      System.out.println("Patient Monitoring Server started, listening on " + port);

      // jmDNS registration - create instance of jmDNS, allows for registration of services
      JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
      // create instance of ServiceInfo class which handles info for registration (port, type, path as params)
      ServiceInfo serviceInfo = ServiceInfo.create("_realtime_monitoring._tcp.local.", "PatientMonitoringServer", port, "");
      // register previously defined serviceInfo with jmDNS
      jmdns.registerService(serviceInfo);
      System.out.println("jmDNS registration complete with type" + serviceInfo.getType() + " and " + serviceInfo.getName());

      // blocking call that blocks the main() from executing and closing down server until it is closed elsewhere
      server.awaitTermination();
      //catch block throws either in-built exception
    } catch (IOException | InterruptedException e) {
      System.err.println("Error while running the server: " + e.getMessage());
    }
  }
  
  
  
  // logic handling  methods for patient monitoring service
  private class PatientMonitoringServiceImpl extends PatientMonitoringGrpc.PatientMonitoringImplBase {


	 /*
	  * Unary gRPC method - addPatient - Single request and single response
	  */  

    @Override
    // take in a request as param, StreamObserver allows for communication back to the client
    public void addPatient(AddPatientRequest request, StreamObserver <AddPatientResponse> responseObserver) {
      // using info from the request, create a new Patient obj
      Patient patient = new Patient(request.getPatientName(), request.getPatientAge(), request.getPatientId());
      // add the new patient to List of patients
      patients.add(patient);
      //response message to confirm patient built and added successfully
      AddPatientResponse response = AddPatientResponse.newBuilder()
        .setSuccess(true)
        .setMessage("Patient " + request.getPatientName() + ", " + request.getPatientAge() + ", " + request.getPatientId() + " added to Patients successfully.")
        .build();
      // return that response back to the client with onNext and inform server that request is complete with onCompleted()
      responseObserver.onNext(response);
      responseObserver.onCompleted();
      System.out.println("Patient has been added successfully");
    }

    
    
    /*
     *  Server Streaming gRPC method - streamPatientInfo - client sends a single request to server, receives stream in return
     */
    
    @Override
    // called by server when client requests to streamPatientInfo, responseObserver handles messages asynchronously 
    public void streamPatientInfo(StreamPatientInfoRequest request, StreamObserver < StreamPatientInfoResponse > responseObserver) {
      // 15 iterations of streamed patient health info using Math.Random to simulate minor changes in vital signs
      for (int i = 0; i < 15; i++) {
        // math.random() gives a random # between 0-1, *4 generates between 0-4, math.random()*4-2 takes two from 0 and 4    	  
        // so now getting random numbers between -2 and +2 of the hard coded values below
        int heartRateRandom = (int) (75.0 + (Math.random() * 4 - 2));
        int oxygenSaturationRandom = (int)(98.0 + (Math.random() * 4 - 2));
        int bloodPressureRandom = (int)(120.0 + (Math.random() * 4 - 2));

        // response will contain all of the patient health info
        StreamPatientInfoResponse response = StreamPatientInfoResponse.newBuilder()
          .setHeartRate(heartRateRandom)
          .setOxygenSaturation(oxygenSaturationRandom)
          .setBloodPressure(bloodPressureRandom)
          .build();
     // return response to the client
        responseObserver.onNext(response);
        try {
        	// 1 second sleep between responses to simulate a 'stream' of patient info in real life rather than a text block
          TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
   // request has been completed
      responseObserver.onCompleted();
    }


    
    /*
     * Server Streaming gRPC method - streamMedicalAlerts - client sends single request with patientID
     * server responds with asynchronous alert stream handled by client response observer
     */
    
    @Override
    // called by server when client requests to streamPatientInfo, called in GUI
    public void streamMedicalAlerts(StreamMedicalAlertsRequest request, StreamObserver < StreamMedicalAlertsResponse > responseObserver) {
      // 15 iterations of alerts, but after the first 10, change the message to generate an ALERT! message
      for (int i = 0; i < 15; i++) {
        StreamMedicalAlertsResponse response;
        // first 10 responses
        if (i < 10) {
          response = StreamMedicalAlertsResponse.newBuilder()
            .setDiagnosis("No medical alert")
            .setTreatment("N/A - Patient is stable")
            .build();
        } else {
          // last 5 responses        	
          response = StreamMedicalAlertsResponse.newBuilder()
            .setDiagnosis("ALERT: HEART RATE FAILURE")
            .setTreatment("Deliver Adrenaline ASAP!")
            .build();
        }
        responseObserver.onNext(response);
        // another TimeUnit.sleep to try and generate a 'stream' of data rather than text block
        try {
          TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      // request has been completed
      responseObserver.onCompleted();
    }
  }

  
  // simple patient class declaration with variables defined and constructor allows for patients to be added to List Patients using addPatient()
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

