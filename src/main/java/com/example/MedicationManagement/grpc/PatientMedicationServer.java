package com.example.MedicationManagement.grpc;

import java.io.IOException;


import java.net.InetAddress;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

//create the main class and declare server for reference later to server methods
public class PatientMedicationServer {

  private Server server;

  // create an instance of PatientMedicationServer and call start method
  public static void main(String[] args) throws IOException, InterruptedException {
    PatientMedicationServer server = new PatientMedicationServer();
    server.start();
  }

  // start() initialises the server, starts the jmDNS discovery service, and block main thread until server is shut down
  public void start() throws IOException {
    try {
      int port = 50053;
      server = ServerBuilder.forPort(port)
        // adds PMS interface to the server so that when the client sends request to the server for a method defined in PMS
        // the server can then use PMSImpl to handle the request and give a response
        // (PatientMedServiceImpl contains the actual logic for grpc request handling)
        .addService(new MedicationManagementImpl())
        .build()
        .start();
      System.out.println("Patient Medication server started, listening on port " + port);

      // jmDNS registration - create instance of jmDNS, allows for registration of services
      JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
      // create instance of ServiceInfo class which handles info for registration (port, type, path as params)
      ServiceInfo serviceInfo = ServiceInfo.create("_patient_medication._tcp.local.", "PatientMedicationServer", port, "");
      // register previously defined serviceInfo with jmDNS
      jmdns.registerService(serviceInfo);
      // println to show it was created correctly
      System.out.println("jmDNS registration complete with type:" + serviceInfo.getType() + " and name: " + serviceInfo.getName());

      // blocking call that blocks the main() from executing and closing down server until it is closed elsewhere
      server.awaitTermination();
      //catch block throws either in-built exception
    } catch (IOException | InterruptedException e) {
      System.err.println("Error while running the server: " + e.getMessage());
    }
  }

  
  // logic handling  methods for patient medication management service
  static class MedicationManagementImpl extends MedicationManagementGrpc.MedicationManagementImplBase {
    //ArrayList to 'store' the medications added to each patient in addMedication method
    List < AddMedicationRequest > medications = new ArrayList < > ();

    
    
    
    /* 
     *  Unary gRPC method - addMedication Client sends single AddMedicationRequest to server. receives single AddMedicationResponse back
     */

    @Override
    // called by server when client requests to addPatient, called in GUI
    // StreamObserver allows for communication back to the client
    public void addMedication(AddMedicationRequest request, StreamObserver < AddMedicationResponse > responseObserver) {
      // add the request object passed as a param to  the medications ArrayList declared above
      medications.add(request);
      // created response using newBuilder()
      AddMedicationResponse response = AddMedicationResponse.newBuilder()
        //when success value is true and medication added successfully small set confirmation message stored in response
        .setSuccess(true)
        .setMessage("Medication added successfully.")
        .build();
      // send response back to client and inform server that request is complete
      responseObserver.onNext(response);
      responseObserver.onCompleted();
    }

    
    
    
    /*
     * Server Streaming gRPC method - confirmMedication. Client sending single request, receiving streamed response
     * ConfirmMedicationRequest received multiple ConfirmMedicationResponse
     */

    @Override
    public StreamObserver < ConfirmMedicationRequest > confirmMedication(StreamObserver < ConfirmMedicationResponse > responseObserver) {
      // return Obj which will define onNext, onError and onComplete methods
      return new StreamObserver < ConfirmMedicationRequest > () {
    	  List<String> medicationInfoList = new ArrayList<>();

        @Override
        // get name and dosage of the medication from request
        public void onNext(ConfirmMedicationRequest request) {
          String medicationName = request.getMedicationName();
          String dosage = request.getDosage();
          // mock contraindication and instructions for medicines
          String contraindications = "For " + medicationName + ": Do not mix with antihistamines!";
          String administrationInstructions = "Take " + dosage + " morning noon and night with food";

       // Add info into the list for response
          medicationInfoList.add("Medication confirmation complete for Medication: " + medicationName +
          "\nContraindications: " + contraindications +
          "\nAdministration Instructions: " + administrationInstructions + "\n\n");
          
        }
        // error handling
        @Override
        public void onError(Throwable t) {
          System.err.println("Error - unable to confirm medication!");
        }

        // build a response using the medicationInfoList of each confirmed medication
        // when onComplete is called list is joined into a single string using .join()
        @Override
        public void onCompleted() {
    	String medicationInfo = String.join("", medicationInfoList);
    	ConfirmMedicationResponse response = ConfirmMedicationResponse.newBuilder()
    		.setMessage(medicationInfo)
          	.setContraindications("")
          	.setAdministrationInstructions("")
          	.build();		
          // send response back to client and inform server that request is complete
          responseObserver.onNext(response);
          responseObserver.onCompleted();
        }
      };
    }

    
    
    
    /*
     * Bi-directional gRPC adjustDosage method -  client sends multiple adjust dosage requests to server
     * server processes and multipled adjusted dosage responses, one for each request.
     */

    @Override
    // define adjustDosage that takes in two StreamObserver objs for request and response
    public StreamObserver < AdjustDosageRequest > adjustDosage(StreamObserver < AdjustDosageResponse > responseObserver) {
      // return Obj which will define onNext, onError and onComplete methods
      return new StreamObserver < AdjustDosageRequest > () {
        @Override
        public void onNext(AdjustDosageRequest request) {

          float bloodSugarLevel = request.getBloodSugarLevel();

          /* using random insulin dosage based off of the input for blood sugar level (just using 10% value)
           * logic for adjusting blood sugar .random() is a decimal between 0-1, random()*3 is decimal between 0-3
           *.random() * 3) - 1.5 shifts the range between -1.5 and 1.5 instead. now bloodsugar can be +/- 1.5 off of the user input value */
          float adjustedDosage = bloodSugarLevel * 0.1f;
          float adjustedBloodSugar = bloodSugarLevel + (float) ((Math.random() * 3) - 1.5);

          // using DecimalFormat import to stop floats having lengthy decimal places - looks bad in GUI
          DecimalFormat shorter = new DecimalFormat("#.###");
          adjustedDosage = Float.parseFloat(shorter.format(adjustedDosage));
          adjustedBloodSugar = Float.parseFloat(shorter.format(adjustedBloodSugar));

          // build response containing the adjusted dosages of insulin relative to blood sugar
          AdjustDosageResponse response = AdjustDosageResponse.newBuilder()
            .setAdjustedInsulin(adjustedDosage)
            .setAdjustedBloodSugar(adjustedBloodSugar)
            .build();
          responseObserver.onNext(response);

          try {
            // 1 second sleep between responses to simulate a 'stream' of patient info in real life rather than a text block
            TimeUnit.SECONDS.sleep(1);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        //  error handling
        @Override
        public void onError(Throwable t) {
          System.err.println("Error in adjustDosage: " + t.getMessage());
        }
        // request has been completed
        @Override
        public void onCompleted() {
          responseObserver.onCompleted();
        }
      };

    }
  }
}