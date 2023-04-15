package clientGUI;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import com.example.MedicationManagement.*;
import com.example.MedicationManagement.grpc.PatientMedicationClient;
import com.example.MedicationManagement.grpc.PatientMedicationServer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class eHealthGUI extends JFrame {

	private JPanel contentPane;
	private JTextField addMedicinePatientIdTF;
	private JTextField addMedicineMedicationNameTF;
	private JTextField addMedicineMedicationDosageTF;
	private JTextField addMedicineMedicationSideEffectsTF;
	private JTextField addMedicineOutputTF;

	private PatientMedicationClient client;
	
	
	
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
	   public static ServiceInfo medicationManagementServiceInfo;
	
	// Discover Patient Medication jmDNS
	private void discoverPatientMedicationService(String service_type) {
	 
		try {
	        // Create a JmDNS instance
	        JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

	        jmdns.addServiceListener(service_type, new ServiceListener() {

	        	@Override
	        	public void serviceResolved(ServiceEvent event) {
	        	    System.out.println("Patient Medication Service resolved: " + event.getInfo());

	        	    medicationManagementServiceInfo = event.getInfo();

	        	    int port = medicationManagementServiceInfo.getPort();
	        	    String host = medicationManagementServiceInfo.getHostAddresses()[0];

	        	    // Create the PatientMedicationClient instance with the host and port
	        	    ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
	        	    client = new PatientMedicationClient(channel);

	        	    System.out.println("Resolving " + service_type + " with properties ...");
	        	    System.out.println("Port: " + port);
	        	    System.out.println("Type: " + event.getType());
	        	    System.out.println("Name: " + event.getName());
	        	    System.out.println("Host: " + host);
	        	}

	            @Override
	            public void serviceRemoved(ServiceEvent event) {
	                System.out.println("Patient Medication Service removed: " + event.getInfo());

	            }

	            @Override
	            public void serviceAdded(ServiceEvent event) {
	                System.out.println("Patient Medication Service added: " + event.getInfo());
	                jmdns.requestServiceInfo(event.getType(), event.getName());

	            }
	        });



	    } catch (UnknownHostException e) {
	        System.out.println(e.getMessage());
	    } catch (IOException e) {
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
		tabbedPane_1.setBounds(0, 11, 481, 343);
		patientMonitoringManager.add(tabbedPane_1);
		
		JPanel addPatientService = new JPanel();
		tabbedPane_1.addTab("Add Patient", null, addPatientService, null);
		
		JPanel realTimePatientInfo = new JPanel();
		tabbedPane_1.addTab("Real Time Patient Info", null, realTimePatientInfo, null);
		
		JPanel medicalStaffAlert = new JPanel();
		tabbedPane_1.addTab("Medical Alert Service", null, medicalStaffAlert, null);
		
		
		// second tab Employee health record manager w/ three methods - search patient, update patient, share patient record
		JPanel EHRManager = new JPanel();
		tabbedPane.addTab("eHealthRecord Manager", null, EHRManager, null);
		EHRManager.setLayout(null);
		
		JTabbedPane tabbedPane_3 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_3.setBounds(10, 11, 471, 343);
		EHRManager.add(tabbedPane_3);
		
		JPanel searchPatientRecord = new JPanel();
		tabbedPane_3.addTab("Search Patient Record", null, searchPatientRecord, null);
		
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
		
		
		// ------------------ COMPONENTS OF MEDICINE MANAGER TAB -----------------------------------------
		JPanel medicationSchedule = new JPanel();
		medicationManagerTabsContainer.addTab("Medication Schedule Service", null, medicationSchedule, null);
		
		JPanel confirmMedication = new JPanel();
		medicationManagerTabsContainer.addTab("Confirm Medication", null, confirmMedication, null);
		
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
		
		
		// output text field
		addMedicineOutputTF = new JTextField();
		addMedicineOutputTF.setBounds(218, 36, 567, 253);
		addMedicineTabbedPane.add(addMedicineOutputTF);
		addMedicineOutputTF.setColumns(10);
		
		JLabel addMedicineOutputLb = new JLabel("Output:");
		addMedicineOutputLb.setBounds(218, 14, 79, 19);
		addMedicineTabbedPane.add(addMedicineOutputLb);
		
		
		// Add ActionListener to the addMedicineBt button
		addMedicineBt.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String patientId = addMedicinePatientIdTF.getText();
		        String medicationName = addMedicineMedicationNameTF.getText();
		        String dosage = addMedicineMedicationDosageTF.getText();
		        String sideEffects = addMedicineMedicationSideEffectsTF.getText();

		        if (client != null) {
		            String result = client.addMedication(patientId, medicationName, dosage, sideEffects);
		            addMedicineOutputTF.setText(result);
		        } else {
		            addMedicineOutputTF.setText("Patient Medication Service not found. Please try again.");
		        }
		    }
		});
		
		
	}
	
	
	
}
