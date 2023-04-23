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

public class PatientMedicationClient {
  private static MedicationManagementGrpc.MedicationManagementBlockingStub blockingStub;
  private static MedicationManagementGrpc.MedicationManagementStub asyncStub;
  // create service specific ServiceInfo, allows for discovery of grpc services and generate client code specific to the server
  private static ServiceInfo medicationManagementServiceInfo;
  private ManagedChannel channel;

  // constructor
  public PatientMedicationClient() {}

  // constructor accepting a ManagedChannel obj as parameter to create grpc connection
  public PatientMedicationClient(ManagedChannel channel) {
    this.channel = channel;
    // create stubs - these provide grpc methods on server
    blockingStub = MedicationManagementGrpc.newBlockingStub(channel);
    asyncStub = MedicationManagementGrpc.newStub(channel);
  }
  //shutdown method to close channel properly when called
  public void shutdown() throws InterruptedException {
    channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
  }

  public static void main(String[] args) throws InterruptedException {
    PatientMedicationClient patientMedicationClient = new PatientMedicationClient();

    // define service type and call discovery service to find it
    String medicationManagement_service_type = "_patient_medication._tcp.local.";
    patientMedicationClient.discoverMedicationManagementService(medicationManagement_service_type);

    // create a ManagedChannel using host+port info taken from discovered service
    String host = medicationManagementServiceInfo.getHostAddresses()[0];
    int port = medicationManagementServiceInfo.getPort();
    final ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();

    //blocking grpc stub is used when client is waiting for serv to response before continuing
    // asynchronous grpc stub when no wait is needed for response. Server responds and a callback handles the response (streaming)
    blockingStub = MedicationManagementGrpc.newBlockingStub(channel);
    asyncStub = MedicationManagementGrpc.newStub(channel);

    channel.shutdown();
    try {
        if (!channel.awaitTermination(5, TimeUnit.SECONDS)) {
            System.err.println("Channel did not terminate in the given time.");
            channel.shutdownNow();

            if (!channel.awaitTermination(5, TimeUnit.SECONDS)) {
                System.err.println("Channel did not forcibly terminate.");
            }
        }
    } catch (InterruptedException e) {
    	channel.shutdownNow();
        Thread.currentThread().interrupt();
    }
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
      // close jmDNS service, service info already obtained and stored plus exception handling + messages
      jmdns.close();
    } catch (UnknownHostException e) {
      System.out.println(e.getMessage());
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  
  
  
  /* 
   * Unary gRPC method - addMedication Client sends single AddMedicationRequest to server. receives single AddMedicationResponse back
   */

  // addMedication takes in four Strings as params then uses .build() to create request obj
  public String addMedication(String patientId, String medicationName, String dosage, String sideEffects) {
    // message contains info needed to add a new medication to a patient
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
      return successMessage;
      // failure message
    } else {
      String failureMessage = "Failed to add medicine for patient ID: " + patientId;
      return failureMessage;
    }
  }

  
  
  
  /*
   * Server Streaming gRPC method - confirmMedication. Client sending single request, receiving streamed response
   * ConfirmMedicationRequest received multiple ConfirmMedicationResponse
   */

  // create callback interface to receive output events
  public interface MedicationConfirmationOutputListener {
    void onNewConfirmationEvent(String message);
  }

  public void confirmMedication(String name, String dosage, MedicationConfirmationOutputListener callback) {
    // build a new request using user input for the name and dose 
    ConfirmMedicationRequest request = ConfirmMedicationRequest.newBuilder()
      .setMedicationName(name)
      .setDosage(dosage)
      .build();

    StreamObserver < ConfirmMedicationRequest > requestObserver = asyncStub.confirmMedication(new StreamObserver < ConfirmMedicationResponse > () {
      @Override
      // take the confirmation, contraindications and administration instructions from the response message
      public void onNext(ConfirmMedicationResponse response) {
    	    String outputMessage = response.getMessage();
    	    // call on the callback method with the message - pass the received data back to the called via callback object
    	    callback.onNewConfirmationEvent(outputMessage);
      }
      // error handling plus message
      @Override
      public void onError(Throwable t) {
        System.err.println("Error in confirmMedication: " + t.getMessage());
      }
      // completion confirmation message
      @Override
      public void onCompleted() {
        System.out.println("Confirm medication completed.");
      }
    });
    // Send medication request and confirm that requests are finished sending to server
    requestObserver.onNext(request);
    requestObserver.onCompleted();
  }

  
  
  
  /*
   * Bi-directional gRPC adjustDosage method -  client sends multiple adjust dosage requests to server
   * server processes and multipled adjusted dosage responses, one for each request.
   */

  // create callback interface to receive output events
  public interface AdjustDosageOutputListener {
    void onNewEvent(String message);
  }

  // method takes in initialBloodSugar as input and the callback listener
  public void adjustDosage(float initialBloodSugarLevel, AdjustDosageOutputListener callback) {
    // create stream observer to handle server communication
    StreamObserver < AdjustDosageRequest > requestObserver = asyncStub.adjustDosage(new StreamObserver < AdjustDosageResponse > () {
      @Override
      // when new response received from server, take in adjusted blood sugar/insulin values
      public void onNext(AdjustDosageResponse response) {
        float adjustedInsulin = response.getAdjustedInsulin();
        float adjustedBloodSugar = response.getAdjustedBloodSugar();
        // simple output message with new adjusted values
        String adjustedInsulinText = "Adjusted insulin value: " + adjustedInsulin;
        String adjustedBloodSugarText = "Adjusted blood sugar value: " + adjustedBloodSugar;
        String message = adjustedInsulinText + "\n" + adjustedBloodSugarText + "\n";
        // call on the callback method with the message - pass the received data back to the called via callback object
        callback.onNewEvent(message);
      }

      // error handling plus message
      @Override
      public void onError(Throwable t) {
        System.err.println("Error in dynamic adjustDosage method: " + t.getMessage());
      }
      // confirmnation message
      @Override
      public void onCompleted() {
        System.out.println("Dynamic dosage adjustment completed!");
      }
    });
    // iterate 10 times
    float bloodSugarLevel = initialBloodSugarLevel;
    for (int i = 0; i < 10; i++) {
      // math.random plus logic to give random blood sugar results based off initial input
      bloodSugarLevel += (Math.random() * 3) - 1.5;
      requestObserver.onNext(AdjustDosageRequest.newBuilder()
        .setBloodSugarLevel(bloodSugarLevel)
        .build());
    }
    // notify server that requests complete
    requestObserver.onCompleted();
  }
}