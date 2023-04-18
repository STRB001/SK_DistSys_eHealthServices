package clientGUI;


import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;


import com.example.MedicationManagement.*;
import com.example.MedicationManagement.grpc.AdjustDosageResponse;
import com.example.MedicationManagement.grpc.ConfirmMedicationResponse;
import com.example.MedicationManagement.grpc.PatientMedicationClient;
import com.example.MedicationManagement.grpc.PatientMedicationServer;
import com.example.RealTimeMonitoring.grpc.PatientMonitoringClient;
import com.example.RealTimeMonitoring.grpc.PatientMonitoringClient.MedicalAlertCallback;
import com.example.RealTimeMonitoring.grpc.PatientMonitoringClient.PatientInfoCallback;
import com.example.eHealthRecords.grpc.EHRManagementClient;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

public class eHealthGUI extends JFrame {

	private JPanel contentPane;
	private JTextField addMedicinePatientIdTF;
	private JTextField addMedicineMedicationNameTF;
	private JTextField addMedicineMedicationDosageTF;
	private JTextField addMedicineMedicationSideEffectsTF;
	

	private PatientMedicationClient client;
	private PatientMonitoringClient patientMonitorClient;
	private EHRManagementClient EHRManagementClient;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					eHealthGUI frame = new eHealthGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			
		});
			
	}
	   public static ServiceInfo ehrManagementServiceInfo;
	   public static ServiceInfo medicationManagementServiceInfo;
	   private JTextField bloodSugarTF;
	   private JTextField medicationName1TF;
	   private JTextField medicationName2TF;
	   private JTextField medicationName3TF;
	   private JTextField medicationDosage1TF;
	   private JTextField medicationDosage2TF;
	   private JTextField medicationDosage3TF;
	   private JTextField patientNameTF;
	   private JTextField patientAgeTF;
	   private JTextField patientIdTF;
	   
	   private JTextField realTimePatientIdTF;
	   private JTextArea realTimeOutputTA;
	   private JTextField medicalAlertPatientIdTF;
	   private JTextField searchPatientIdTF;

	
	// Discover Patient Medication jmDNS
	   private void discoverPatientMedicationService(String service_type) {
		    try {
		        // Create a JmDNS instance
		        JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

		        ServiceListener listener = new ServiceListener() {
		            @Override
		            public void serviceResolved(ServiceEvent event) {
		            	
		                medicationManagementServiceInfo = event.getInfo();
		                ehrManagementServiceInfo = event.getInfo();

		                int port = medicationManagementServiceInfo.getPort();
		                String host = medicationManagementServiceInfo.getHostAddresses()[0];
		                
	                    int ehrport = ehrManagementServiceInfo.getPort();
	                    String ehrhost = ehrManagementServiceInfo.getHostAddresses()[0];

		                // Create the PatientMedicationClient instance with the host and port
		                ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
		                client = new PatientMedicationClient(channel);
		                
		                
		                // Create the AddPatientClient instance with the host and port
		                ManagedChannel patientMonitorChannel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
		                patientMonitorClient = new PatientMonitoringClient(patientMonitorChannel);


		                // Create the EHRManagementClient instance with the host and port
		                ManagedChannel ehrManagementChannel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
		                EHRManagementClient = new EHRManagementClient(ehrManagementChannel);
		            }

		            @Override
		            public void serviceRemoved(ServiceEvent event) {
		                System.out.println("Patient Medication Service removed");
		            }

		            @Override
		            public void serviceAdded(ServiceEvent event) {
		                System.out.println("Patient Medication Service added!");
		                jmdns.requestServiceInfo(event.getType(), event.getName());
		            }
		        };

		        jmdns.addServiceListener(service_type, listener);

		        // Print out service information once when the service is resolved
		        Thread.sleep(500);
		        System.out.println("Resolving Patient Medication jmDNS with properties:");
		        System.out.println("Port: " + medicationManagementServiceInfo.getPort());
		        System.out.println("Type: " + medicationManagementServiceInfo.getType());
		        System.out.println("Name: " + medicationManagementServiceInfo.getName());
		        System.out.println("Host: " + medicationManagementServiceInfo.getHostAddresses()[0]);

		        jmdns.removeServiceListener(service_type, listener);
		        jmdns.close();
		    } catch (UnknownHostException e) {
		        System.out.println(e.getMessage());
		    } catch (IOException e) {
		        System.out.println(e.getMessage());
		    } catch (InterruptedException e) {
		        System.out.println(e.getMessage());
		    }
		}
		
    // create gui and title it
	public eHealthGUI() {
		

		
	    // Discover Patient Medication jmDNS Service
	    String serviceType = "_grpc._tcp.local.";
	    discoverPatientMedicationService(serviceType);
		
		
		setTitle("eHealthServices Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 979, 455);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		// add in 1st tabbed pane to hold the three different services
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 14, 836, 393);
		contentPane.add(tabbedPane);
		
		
		// first tab patient monitoring manager w/ three methods - add patient, real time patient info, medical alert service
		JPanel patientMonitoringManager = new JPanel();
		tabbedPane.addTab("Patient Monitoring Manager", null, patientMonitoringManager, null);
		patientMonitoringManager.setLayout(null);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(0, 11, 821, 343);
		patientMonitoringManager.add(tabbedPane_1);
		
		JPanel addPatientService = new JPanel();
		tabbedPane_1.addTab("Add Patient", null, addPatientService, null);
		addPatientService.setLayout(null);
		
		JLabel patientNameLb = new JLabel("Patient Name:");
		patientNameLb.setBounds(10, 32, 88, 14);
		addPatientService.add(patientNameLb);
		
		JLabel patientAgeLb = new JLabel("Patient Age:");
		patientAgeLb.setBounds(10, 101, 88, 14);
		addPatientService.add(patientAgeLb);
		
		JLabel patientIdLb = new JLabel("Patient ID:");
		patientIdLb.setBounds(10, 176, 88, 14);
		addPatientService.add(patientIdLb);
		
		patientNameTF = new JTextField();
		patientNameTF.setBounds(10, 48, 167, 20);
		addPatientService.add(patientNameTF);
		patientNameTF.setColumns(10);
		
		patientAgeTF = new JTextField();
		patientAgeTF.setColumns(10);
		patientAgeTF.setBounds(10, 118, 167, 20);
		addPatientService.add(patientAgeTF);
		
		patientIdTF = new JTextField();
		patientIdTF.setColumns(10);
		patientIdTF.setBounds(10, 193, 167, 20);
		addPatientService.add(patientIdTF);
		
		
		// ADD PATIENT BUTTON
		JButton addPatientBt = new JButton("Add Patient");
		addPatientBt.setBounds(10, 246, 141, 23);
		addPatientService.add(addPatientBt);
		
		
		
		JTextArea addPatientOutputTA = new JTextArea();
		addPatientOutputTA.setBounds(231, 57, 591, 258);
		addPatientService.add(addPatientOutputTA);
		
		JLabel addPatientOutputLb = new JLabel("Output:");
		addPatientOutputLb.setBounds(221, 32, 46, 14);
		addPatientService.add(addPatientOutputLb);
		
		
		
		JPanel realTimePatientInfo = new JPanel();
		tabbedPane_1.addTab("Real Time Patient Info", null, realTimePatientInfo, null);
		realTimePatientInfo.setLayout(null);
		
		JLabel realTimePatientIdLb = new JLabel("Patient ID:");
		realTimePatientIdLb.setBounds(10, 21, 93, 14);
		realTimePatientInfo.add(realTimePatientIdLb);
		
		realTimePatientIdTF = new JTextField();
		realTimePatientIdTF.setBounds(10, 40, 162, 20);
		realTimePatientInfo.add(realTimePatientIdTF);
		realTimePatientIdTF.setColumns(10);
		
		JButton realTimeInfoBt = new JButton("Begin Real Time Monitor");
		realTimeInfoBt.setBounds(10, 122, 163, 44);
		realTimePatientInfo.add(realTimeInfoBt);
	// REAL TIME BUTTON	
		JLabel realTimeOutputLb = new JLabel("Output:");
		realTimeOutputLb.setBounds(204, 21, 46, 14);
		realTimePatientInfo.add(realTimeOutputLb);
		
		
		realTimeInfoBt.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        String patientId = realTimePatientIdTF.getText();
		        patientMonitorClient.streamPatientInfo(patientId, new PatientInfoCallback() {
		            @Override
		            public void onNewMessage(String message) {
		                realTimeOutputTA.append(message);
		            }
		        });
		    }
		});

		
		realTimeOutputTA = new JTextArea();
		realTimeOutputTA.setBounds(204, 40, 602, 264);
		realTimePatientInfo.add(realTimeOutputTA);
		realTimeOutputTA.setColumns(10);
		
		JPanel medicalStaffAlert = new JPanel();
		tabbedPane_1.addTab("Medical Alert Service", null, medicalStaffAlert, null);
		medicalStaffAlert.setLayout(null);
		
		JLabel medicalAlertPatientIdLb = new JLabel("Patient ID:");
		medicalAlertPatientIdLb.setBounds(7, 22, 83, 14);
		medicalStaffAlert.add(medicalAlertPatientIdLb);
		
		medicalAlertPatientIdTF = new JTextField();
		medicalAlertPatientIdTF.setBounds(7, 39, 161, 20);
		medicalStaffAlert.add(medicalAlertPatientIdTF);
		medicalAlertPatientIdTF.setColumns(10);
		
		JLabel medicalAlertOutputLb = new JLabel("Output:");
		medicalAlertOutputLb.setBounds(206, 22, 46, 14);
		medicalStaffAlert.add(medicalAlertOutputLb);
		
		JTextArea medicalAlertOutputTA = new JTextArea();
		medicalAlertOutputTA.setBounds(206, 37, 600, 267);
		medicalStaffAlert.add(medicalAlertOutputTA);
		
		JButton medicalAlertStreamBt = new JButton("Begin Medical Alert System");
		medicalAlertStreamBt.setBounds(10, 122, 163, 44);
		medicalStaffAlert.add(medicalAlertStreamBt);
		
		medicalAlertStreamBt.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        String patientId = medicalAlertPatientIdTF.getText();
		        try {
					patientMonitorClient.streamMedicalAlerts(patientId, new MedicalAlertCallback() {
					    @Override
					    public void onNewAlert(String message) {
					        medicalAlertOutputTA.append(message + "\n");
					    }
					});
				} catch (InterruptedException | TimeoutException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    }
		});
		
		
		
		// second tab Employee health record manager w/ three methods - search patient, update patient, share patient record
		JPanel EHRManager = new JPanel();
		tabbedPane.addTab("eHealthRecord Manager", null, EHRManager, null);
		EHRManager.setLayout(null);
		
		JTabbedPane tabbedPane_3 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_3.setBounds(10, 11, 811, 343);
		EHRManager.add(tabbedPane_3);
		
		JPanel searchPatientRecord = new JPanel();
		tabbedPane_3.addTab("Search Patient Record", null, searchPatientRecord, null);
		searchPatientRecord.setLayout(null);
		
		JLabel searchPatientIDLb = new JLabel("Patient ID:");
		searchPatientIDLb.setBounds(10, 34, 76, 14);
		searchPatientRecord.add(searchPatientIDLb);
		
		JLabel searchPatientOutputLb = new JLabel("Output:");
		searchPatientOutputLb.setBounds(169, 34, 46, 14);
		searchPatientRecord.add(searchPatientOutputLb);
		
		JTextArea searchPatientOutputTA = new JTextArea();
		searchPatientOutputTA.setBounds(169, 54, 627, 250);
		searchPatientRecord.add(searchPatientOutputTA);
		
		searchPatientIdTF = new JTextField();
		searchPatientIdTF.setBounds(10, 56, 124, 20);
		searchPatientRecord.add(searchPatientIdTF);
		searchPatientIdTF.setColumns(10);
		
		JButton searchPatientBt = new JButton("Search Patient ID");
		searchPatientBt.setBounds(10, 181, 124, 23);
		searchPatientRecord.add(searchPatientBt);
		
		
		
		searchPatientBt.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String patientId = searchPatientIdTF.getText();
		        String result = EHRManagementClient.searchPatientRecord(patientId);
		        searchPatientOutputTA.setText(result);
		    }
		});
		
		JPanel updatePatientRecord = new JPanel();
		tabbedPane_3.addTab("Update Patient Record", null, updatePatientRecord, null);
		
		JPanel sharePatientRecord = new JPanel();
		tabbedPane_3.addTab("Share Patient Record", null, sharePatientRecord, null);
		
		JPanel MedicationManager = new JPanel();
		tabbedPane.addTab("Medication Manager", null, MedicationManager, null);
		MedicationManager.setLayout(null);
		
		// third tab Medication Manager w/ three methods - add medicine, medicine schedule, and confirm medicine
		JTabbedPane medicationManagerTabsContainer = new JTabbedPane(JTabbedPane.TOP);
		medicationManagerTabsContainer.setBounds(10, 11, 811, 343);
		MedicationManager.add(medicationManagerTabsContainer);
		
		JPanel addMedicineTabbedPane = new JPanel();
		medicationManagerTabsContainer.addTab("Add Medication", null, addMedicineTabbedPane, null);
		addMedicineTabbedPane.setLayout(null);
		
		JLabel addMedicineMedicationSideEffectsLb = new JLabel("Medication Side Effects:");
		addMedicineMedicationSideEffectsLb.setBounds(10, 188, 115, 24);
		addMedicineTabbedPane.add(addMedicineMedicationSideEffectsLb);
		
		JLabel addMedicineMedicationNameLb = new JLabel("Medication Name:");
		addMedicineMedicationNameLb.setBounds(10, 69, 136, 24);
		addMedicineTabbedPane.add(addMedicineMedicationNameLb);
		
		JLabel addMedicineMedicationDosageLb = new JLabel("Medication Dosage:");
		addMedicineMedicationDosageLb.setBounds(10, 128, 136, 24);
		addMedicineTabbedPane.add(addMedicineMedicationDosageLb);
		
		JLabel addMedicinePatientIdLb = new JLabel("Patient ID:");
		addMedicinePatientIdLb.setBounds(10, 11, 136, 24);
		addMedicineTabbedPane.add(addMedicinePatientIdLb);
		
		addMedicinePatientIdTF = new JTextField();
		addMedicinePatientIdTF.setBounds(10, 34, 183, 24);
		addMedicineTabbedPane.add(addMedicinePatientIdTF);
		addMedicinePatientIdTF.setColumns(10);
		
		addMedicineMedicationNameTF = new JTextField();
		addMedicineMedicationNameTF.setColumns(10);
		addMedicineMedicationNameTF.setBounds(10, 93, 183, 24);
		addMedicineTabbedPane.add(addMedicineMedicationNameTF);
		
		addMedicineMedicationDosageTF = new JTextField();
		addMedicineMedicationDosageTF.setColumns(10);
		addMedicineMedicationDosageTF.setBounds(10, 153, 183, 24);
		addMedicineTabbedPane.add(addMedicineMedicationDosageTF);
		
		addMedicineMedicationSideEffectsTF = new JTextField();
		addMedicineMedicationSideEffectsTF.setColumns(10);
		addMedicineMedicationSideEffectsTF.setBounds(10, 214, 183, 24);
		addMedicineTabbedPane.add(addMedicineMedicationSideEffectsTF);
		
		// add medicine button
		JButton addMedicineBt = new JButton("Add Medicine");
		addMedicineBt.setBounds(10, 266, 136, 23);
		addMedicineTabbedPane.add(addMedicineBt);
		
		JLabel addMedicineOutputLb = new JLabel("Output:");
		addMedicineOutputLb.setBounds(199, 11, 79, 14);
		addMedicineTabbedPane.add(addMedicineOutputLb);
		
		JTextArea addMedicineOutputTA = new JTextArea();
		addMedicineOutputTA.setBounds(199, 26, 597, 278);
		addMedicineTabbedPane.add(addMedicineOutputTA);
		
		
		// add medicine unary method
		addPatientBt.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String patientName = patientNameTF.getText();
		        int patientAge = Integer.parseInt(patientAgeTF.getText());
		        String patientId = patientIdTF.getText();

		        try {
		            String addPatientMessage = patientMonitorClient.addPatient(patientName, patientAge, patientId);
		            addPatientOutputTA.append(addPatientMessage);
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(null, "Error while adding patient. Please check the input values.");
		        }
		    }
		});
		

		JPanel confirmMedication = new JPanel();
		medicationManagerTabsContainer.addTab("Confirm Medication", null, confirmMedication, null);
		confirmMedication.setLayout(null);
		
		JLabel medicationName1Lb = new JLabel("Medication Name 1:");
		medicationName1Lb.setBounds(10, 11, 106, 14);
		confirmMedication.add(medicationName1Lb);
		
		JLabel medicationName2Lb = new JLabel("Medication Name 2:");
		medicationName2Lb.setBounds(10, 97, 106, 14);
		confirmMedication.add(medicationName2Lb);
		
		JLabel medicationName3Lb = new JLabel("Medication Name 3:");
		medicationName3Lb.setBounds(10, 182, 106, 14);
		confirmMedication.add(medicationName3Lb);
		
		medicationName1TF = new JTextField();
		medicationName1TF.setBounds(10, 25, 134, 20);
		confirmMedication.add(medicationName1TF);
		medicationName1TF.setColumns(10);
		
		medicationName2TF = new JTextField();
		medicationName2TF.setColumns(10);
		medicationName2TF.setBounds(10, 111, 134, 20);
		confirmMedication.add(medicationName2TF);
		
		medicationName3TF = new JTextField();
		medicationName3TF.setColumns(10);
		medicationName3TF.setBounds(10, 197, 134, 20);
		confirmMedication.add(medicationName3TF);
		
		JLabel medicationDosage1Lb = new JLabel("Medication Dosage 1:");
		medicationDosage1Lb.setBounds(10, 50, 106, 14);
		confirmMedication.add(medicationDosage1Lb);
		
		JLabel medicationDosage3Lb = new JLabel("Medication Dosage 3:");
		medicationDosage3Lb.setBounds(10, 222, 106, 14);
		confirmMedication.add(medicationDosage3Lb);
		
		JLabel medicationDosage2Lb = new JLabel("Medication Dosage 2:");
		medicationDosage2Lb.setBounds(10, 134, 106, 14);
		confirmMedication.add(medicationDosage2Lb);
		
		medicationDosage1TF = new JTextField();
		medicationDosage1TF.setBounds(10, 66, 134, 20);
		confirmMedication.add(medicationDosage1TF);
		medicationDosage1TF.setColumns(10);
		
		medicationDosage2TF = new JTextField();
		medicationDosage2TF.setColumns(10);
		medicationDosage2TF.setBounds(10, 151, 134, 20);
		confirmMedication.add(medicationDosage2TF);
		
		medicationDosage3TF = new JTextField();
		medicationDosage3TF.setColumns(10);
		medicationDosage3TF.setBounds(10, 239, 134, 20);
		confirmMedication.add(medicationDosage3TF);
		
		JLabel confirmMedicationOutputLb = new JLabel("Output:");
		confirmMedicationOutputLb.setBounds(199, 11, 46, 14);
		confirmMedication.add(confirmMedicationOutputLb);
		
		JTextArea confirmMedicationOutputTA = new JTextArea();
		confirmMedicationOutputTA.setBounds(199, 26, 597, 278);
		confirmMedication.add(confirmMedicationOutputTA);
		
		JButton confirmMedicationBt = new JButton("Confirm Medication");
		confirmMedicationBt.setBounds(10, 270, 179, 23);
		confirmMedication.add(confirmMedicationBt);
		
		confirmMedicationBt.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            String medicationName1 = medicationName1TF.getText();
		            String medicationDosage1 = medicationDosage1TF.getText();
		            String medicationName2 = medicationName2TF.getText();
		            String medicationDosage2 = medicationDosage2TF.getText();
		            String medicationName3 = medicationName3TF.getText();
		            String medicationDosage3 = medicationDosage3TF.getText();

		            List<String> outputMessages1 = client.confirmMedication(medicationName1, medicationDosage1);
		            List<String> outputMessages2 = client.confirmMedication(medicationName2, medicationDosage2);
		            List<String> outputMessages3 = client.confirmMedication(medicationName3, medicationDosage3);

		            List<String> allOutputMessages = new ArrayList<>();
		            allOutputMessages.addAll(outputMessages1);
		            allOutputMessages.addAll(outputMessages2);
		            allOutputMessages.addAll(outputMessages3);

		            for (String outputMessage : allOutputMessages) {
		                confirmMedicationOutputTA.append(outputMessage);
		            }
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(null, "Error while confirming medication. Please check the input values.");
		        }
		    }
		});
		
		
		
		// ------------------ COMPONENTS OF MEDICINE MANAGER TAB -----------------------------------------
		JPanel adjustDosage = new JPanel();
		medicationManagerTabsContainer.addTab("Adjust Dosage Service", null, adjustDosage, null);
		adjustDosage.setLayout(null);
		
		JLabel adjustDosageOutputLb = new JLabel("Output:");
		adjustDosageOutputLb.setBounds(199, 11, 47, 14);
		adjustDosage.add(adjustDosageOutputLb);
		
		JLabel bloodSugarLb = new JLabel("Please enter initial Blood Sugar value:");
		bloodSugarLb.setBounds(10, 65, 191, 22);
		adjustDosage.add(bloodSugarLb);
		
		bloodSugarTF = new JTextField();
		bloodSugarTF.setBounds(10, 91, 86, 20);
		adjustDosage.add(bloodSugarTF);
		bloodSugarTF.setColumns(10);
		
		JLabel bloodSugarUnitLb = new JLabel("mmol/L");
		bloodSugarUnitLb.setBounds(98, 98, 46, 14);
		adjustDosage.add(bloodSugarUnitLb);
		
		JButton adjustDosageStreamBt = new JButton("Stream Dynamic Dose");
		adjustDosageStreamBt.setBounds(10, 145, 154, 23);
		adjustDosage.add(adjustDosageStreamBt);
		
		JTextArea adjustDosageOutputTA = new JTextArea();
		adjustDosageOutputTA.setBounds(199, 26, 597, 278);
		adjustDosage.add(adjustDosageOutputTA);
		
		
// Adjust dosage bi-directional stream
		adjustDosageStreamBt.addActionListener(new ActionListener() {
		    
		    public void actionPerformed(ActionEvent e) {
		        try {
		            float bloodSugarLevel = Float.parseFloat(bloodSugarTF.getText());
		            List<String> outputMessages = client.adjustDosage(bloodSugarLevel);

		            for (String outputMessage : outputMessages) {
		                adjustDosageOutputTA.append(outputMessage);
		            }
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, "Invalid blood sugar level value. Please enter a valid number.");
		        }
		    }
		});
		


	}
}
