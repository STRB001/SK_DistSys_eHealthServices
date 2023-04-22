package clientGUI;


import java.awt.event.ActionEvent;



import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.example.MedicationManagement.*;
import com.example.MedicationManagement.grpc.AdjustDosageResponse;
import com.example.MedicationManagement.grpc.ConfirmMedicationResponse;
import com.example.MedicationManagement.grpc.PatientMedicationClient;
import com.example.MedicationManagement.grpc.PatientMedicationClient.MedicationConfirmationOutputListener;
import com.example.MedicationManagement.grpc.PatientMedicationClient.MedicationConfirmationOutputListener;
import com.example.MedicationManagement.grpc.PatientMedicationServer;
import com.example.RealTimeMonitoring.grpc.PatientMonitoringClient;
import com.example.RealTimeMonitoring.grpc.PatientMonitoringClient.MedicalAlertListener;
import com.example.RealTimeMonitoring.grpc.PatientMonitoringClient.PatientInfoListener;
import com.example.eHealthRecords.grpc.EHRManagementClient;
import com.example.eHealthRecords.grpc.EHRManagementClient.sharePatientListener;
import com.example.eHealthRecords.grpc.SearchPatientRecordResponse;

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
import javax.swing.JComboBox;
import javax.swing.JScrollBar;


public class eHealthGUI extends JFrame {

	private JPanel contentPane;
	private JTextField addMedicinePatientIdTF;
	private JTextField addMedicineMedicationNameTF;
	private JTextField addMedicineMedicationDosageTF;
	private JTextField addMedicineMedicationSideEffectsTF;
	

	private PatientMedicationClient patientMedicationClient;
	private PatientMonitoringClient patientMonitorClient;
	private EHRManagementClient EHRManagementClient;
	
	private ManagedChannel ehrManagementChannel;
	private ManagedChannel patientMonitorChannel;
	private ManagedChannel patientMedicationChannel;
	
	
	   public static ServiceInfo ehrManagementServiceInfo;
	   public static ServiceInfo patientMedicationServiceInfo;
	   public static ServiceInfo patientMonitoringServiceInfo;
	   
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
	   private JTextField updatePatientNameTF;
	   private JTextField updateDepartmentTF;
	   private JTextField updateDiagnosisTF;
	   private JTextField updateMedicationTF;
	   private JTextField updateOperationTF;
	   private JTextField sharePatientNumRecordsTF;
	
	
	   public static void main(String[] args) {
		    EventQueue.invokeLater(new Runnable() {
		        public void run() {
		            eHealthGUI frame = null;
					try {
						frame = new eHealthGUI();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		            frame.setVisible(true);
		        }
		    });
		}
	   
	   // Discovery of Patient Medication Management Service using jmDNS
	   private void discoverPatientMedicationService(String service_type) throws IOException {

		        //Get local host address
		        InetAddress localHost = InetAddress.getLocalHost();

		        //create a JmDNS instance
		        JmDNS jmdns = JmDNS.create(localHost);

		        //create ServiceListener
		        ServiceListener listener = new ServiceListener() {
		            @Override
		            public void serviceResolved(ServiceEvent event) {
		                patientMedicationServiceInfo = event.getInfo();

		                int port = patientMedicationServiceInfo.getPort();
		                String host = patientMedicationServiceInfo.getHostAddresses()[0];

		                // Create the PatientMedicationClient instance with the host and port
		                ManagedChannel patientMedicationChannel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
		                patientMedicationClient = new PatientMedicationClient(patientMedicationChannel);
		            }

		            @Override
		            public void serviceAdded(ServiceEvent event) {
		                jmdns.requestServiceInfo(event.getType(), event.getName(), 1000);
		            }

		            @Override
		            public void serviceRemoved(ServiceEvent event) {
		            }              
		        };
		        // Register the listener and start service discovery
		        jmdns.addServiceListener(service_type, listener);      
		}

	// Discovery of Real-Time Monitoring Service using jmDNS
	   private void discoverRealTimeMonitoringService(String service_type) throws IOException {
		   
	       // get the local host address
	       InetAddress localHost = InetAddress.getLocalHost();

	       // create a JmDNS instance
	       JmDNS jmdns = JmDNS.create(localHost);

	       // create a ServiceListener
	       ServiceListener listener = new ServiceListener() {
	           @Override
	           public void serviceResolved(ServiceEvent event) {
	               patientMonitoringServiceInfo = event.getInfo();

	               int port = patientMonitoringServiceInfo.getPort();
	               String host = patientMonitoringServiceInfo.getHostAddresses()[0];

	               //create PatientMonitoringClient instance with the host and port
	               ManagedChannel patientMonitorChannel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
	               patientMonitorClient = new PatientMonitoringClient(patientMonitorChannel);
	           }

	           @Override
	           public void serviceAdded(ServiceEvent event) {
	               jmdns.requestServiceInfo(event.getType(), event.getName(), 1000);
	           }
	           
	           @Override
	           public void serviceRemoved(ServiceEvent event) {
	           }
	       };
	       // register the listener and start service discovery
	       jmdns.addServiceListener(service_type, listener);
	   }


	// Discover eHealth Record Management Service
	   private void discoverEHRManagementService(String service_type) throws IOException {

	       // Get the local host address
	       InetAddress localHost = InetAddress.getLocalHost();

	       // Create a JmDNS instance
	       JmDNS jmdns = JmDNS.create(localHost);

	       // Create a ServiceListener
	       ServiceListener listener = new ServiceListener() {
	           @Override
	           public void serviceResolved(ServiceEvent event) {
	               ehrManagementServiceInfo = event.getInfo();

	               int port = ehrManagementServiceInfo.getPort();
	               String host = ehrManagementServiceInfo.getHostAddresses()[0];

	               // Create the EHRManagementClient instance with the host and port
	               ManagedChannel ehrManagementChannel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
	               EHRManagementClient = new EHRManagementClient(ehrManagementChannel);
	           }

	           @Override
	           public void serviceAdded(ServiceEvent event) {
	               jmdns.requestServiceInfo(event.getType(), event.getName(), 1000);
	           }

	           @Override
	           public void serviceRemoved(ServiceEvent event) {
	               // Add code here if needed
	           }
	       };

	       // Register the listener and start the service discovery
	       jmdns.addServiceListener(service_type, listener);
	   }
		
	   public void shutdownPatientMonitorChannel() {
		    if (patientMonitorChannel != null) {
		    	patientMonitorChannel.shutdown();
		    }
		}
	   
	   public void shutdownPatientMedicationChannel() {
		    if (patientMedicationChannel != null) {
		        patientMedicationChannel.shutdown();
		    }
		}

	   public void shutdownPatienEHRChannel() {
		    if (ehrManagementChannel != null) {
		    	ehrManagementChannel.shutdown();
		    }
		}


    // creatE GUI
	public eHealthGUI() throws IOException {
		
		
		addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent e) {
		    	shutdownPatientMonitorChannel();
		        shutdownPatientMedicationChannel();
		        shutdownPatienEHRChannel();
		        System.out.println("Shutdown all channels");
		    }
		});
		
		
	    discoverPatientMedicationService("_patient_medication._tcp.local.");
	    discoverRealTimeMonitoringService("_realtime_monitoring._tcp.local.");
	    discoverEHRManagementService("_ehr_management._tcp.local.");
		
		
		
		setTitle("eHealthServices Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 912, 457);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 14, 878, 393);
		contentPane.add(tabbedPane);
		
		/*
		* Patient monitoring manager tab w/ three methods - add patient, real time patient info, medical alert service
		* Unary, Server-streaming, Server-streaming respectively
		*/
		JPanel patientMonitoringManager = new JPanel();
		tabbedPane.addTab("Patient Monitoring Manager", null, patientMonitoringManager, null);
		patientMonitoringManager.setLayout(null);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(0, 11, 845, 343);
		patientMonitoringManager.add(tabbedPane_1);
		
		JPanel addPatientService = new JPanel();
		tabbedPane_1.addTab("Add Patient", null, addPatientService, null);
		addPatientService.setLayout(null);
		
		JLabel patientNameLb = new JLabel("Patient Name:");
		patientNameLb.setBounds(10, 35, 113, 14);
		addPatientService.add(patientNameLb);
		
		JLabel patientAgeLb = new JLabel("Patient Age:");
		patientAgeLb.setBounds(10, 88, 99, 14);
		addPatientService.add(patientAgeLb);
		
		JLabel patientIdLb = new JLabel("Patient ID:");
		patientIdLb.setBounds(10, 145, 113, 14);
		addPatientService.add(patientIdLb);
		
		patientNameTF = new JTextField();
		patientNameTF.setBounds(10, 50, 161, 20);
		addPatientService.add(patientNameTF);
		patientNameTF.setColumns(10);
		
		patientAgeTF = new JTextField();
		patientAgeTF.setColumns(10);
		patientAgeTF.setBounds(10, 105, 161, 20);
		addPatientService.add(patientAgeTF);
		
		patientIdTF = new JTextField();
		patientIdTF.setColumns(10);
		patientIdTF.setBounds(10, 162, 161, 20);
		addPatientService.add(patientIdTF);
		
		JButton addPatientBt = new JButton("Add Patient");
		addPatientBt.setBounds(25, 214, 140, 35);
		addPatientService.add(addPatientBt);
		
		JLabel addPatientOutputLb = new JLabel("Output:");
		addPatientOutputLb.setBounds(192, 11, 54, 20);
		addPatientService.add(addPatientOutputLb);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(192, 33, 614, 271);
		addPatientService.add(scrollPane_2);
		
		JTextArea addPatientOutputTA = new JTextArea();
		scrollPane_2.setViewportView(addPatientOutputTA);
		
		JPanel realTimePatientInfo = new JPanel();
		tabbedPane_1.addTab("Real Time Patient Info", null, realTimePatientInfo, null);
		realTimePatientInfo.setLayout(null);
		
		JLabel realTimePatientIdLb = new JLabel("Patient ID:");
		realTimePatientIdLb.setBounds(10, 11, 93, 14);
		realTimePatientInfo.add(realTimePatientIdLb);
		
		realTimePatientIdTF = new JTextField();
		realTimePatientIdTF.setBounds(10, 33, 162, 20);
		realTimePatientInfo.add(realTimePatientIdTF);
		realTimePatientIdTF.setColumns(10);
		
		JButton realTimeInfoBt = new JButton("Real Time Monitor");
		realTimeInfoBt.setBounds(21, 78, 147, 37);
		realTimePatientInfo.add(realTimeInfoBt);

		JLabel realTimeOutputLb = new JLabel("Output:");
		realTimeOutputLb.setBounds(192, 11, 54, 20);
		realTimePatientInfo.add(realTimeOutputLb);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(192, 33, 614, 271);
		realTimePatientInfo.add(scrollPane);
			
		realTimeOutputTA = new JTextArea();
		scrollPane.setViewportView(realTimeOutputTA);
		realTimeOutputTA.setColumns(10);
		
		JPanel medicalStaffAlert = new JPanel();
		tabbedPane_1.addTab("Medical Alert Service", null, medicalStaffAlert, null);
		medicalStaffAlert.setLayout(null);
		
		JLabel medicalAlertPatientIdLb = new JLabel("Patient ID:");
		medicalAlertPatientIdLb.setBounds(10, 11, 83, 14);
		medicalStaffAlert.add(medicalAlertPatientIdLb);
		
		medicalAlertPatientIdTF = new JTextField();
		medicalAlertPatientIdTF.setBounds(10, 33, 161, 20);
		medicalStaffAlert.add(medicalAlertPatientIdTF);
		medicalAlertPatientIdTF.setColumns(10);
		
		JLabel medicalAlertOutputLb = new JLabel("Output:");
		medicalAlertOutputLb.setBounds(192, 11, 54, 20);
		medicalStaffAlert.add(medicalAlertOutputLb);
		
		JButton medicalAlertStreamBt = new JButton("Begin Alert System");
		medicalAlertStreamBt.setBounds(21, 78, 147, 37);
		medicalStaffAlert.add(medicalAlertStreamBt);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(192, 33, 614, 271);
		medicalStaffAlert.add(scrollPane_1);
		
		JTextArea medicalAlertOutputTA = new JTextArea();
		scrollPane_1.setViewportView(medicalAlertOutputTA);
		
		
		// add patient method
		// enter patient name, age, patientID, call method with add patient button
		addPatientBt.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        String patientName = patientNameTF.getText();
		        int patientAge = Integer.parseInt(patientAgeTF.getText());
		        String patientId = patientIdTF.getText();
		        String responseMessage = patientMonitorClient.addPatient(patientName, patientAge, patientId);
		        addPatientOutputTA.append(responseMessage);
		    }
		});
		
		
		
		// real time patient info method
		// enter PatientID, call method for real time stream using button
		realTimeInfoBt.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        String patientId = realTimePatientIdTF.getText();
		        patientMonitorClient.streamPatientInfo(patientId, new PatientInfoListener() {
		            @Override
		            public void onNewPatientInfo(String message) {
		                realTimeOutputTA.append(message);
		            }
		        });
		    }
		});
		
		
		
		// medical alert method
		// enter PatientID, call method for real time stream using button
		medicalAlertStreamBt.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        String patientId = medicalAlertPatientIdTF.getText();
		        try {
					patientMonitorClient.streamMedicalAlerts(patientId, new MedicalAlertListener() {
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
		
		
		
		/*
		 * eHealth Records SERVICE 3 METHODS - searchPatient, updatePatient, sharePatientRecords
		 * Unary, Unary, Client-streaming respectively
		 */
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
		searchPatientOutputLb.setBounds(206, 11, 46, 14);
		searchPatientRecord.add(searchPatientOutputLb);
		
		searchPatientIdTF = new JTextField();
		searchPatientIdTF.setBounds(10, 52, 124, 20);
		searchPatientRecord.add(searchPatientIdTF);
		searchPatientIdTF.setColumns(10);
		
		JButton searchPatientBt = new JButton("Search Patient ID");
		searchPatientBt.setBounds(32, 106, 124, 33);
		searchPatientRecord.add(searchPatientBt);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(206, 34, 590, 270);
		searchPatientRecord.add(scrollPane_3);
		
		JTextArea searchPatientOutputTA = new JTextArea();
		scrollPane_3.setViewportView(searchPatientOutputTA);
		
		JPanel updatePatientRecord = new JPanel();
		tabbedPane_3.addTab("Update Patient Record", null, updatePatientRecord, null);
		updatePatientRecord.setLayout(null);
		
		JLabel updatePatientNameLb = new JLabel("Update Patient Name:");
		updatePatientNameLb.setBounds(11, 53, 149, 14);
		updatePatientRecord.add(updatePatientNameLb);
		
		JLabel updatePatientDepartmentLb = new JLabel("Update Department:");
		updatePatientDepartmentLb.setBounds(11, 95, 149, 14);
		updatePatientRecord.add(updatePatientDepartmentLb);
		
		updatePatientNameTF = new JTextField();
		updatePatientNameTF.setBounds(11, 66, 149, 20);
		updatePatientRecord.add(updatePatientNameTF);
		updatePatientNameTF.setColumns(10);
		
		updateDepartmentTF = new JTextField();
		updateDepartmentTF.setColumns(10);
		updateDepartmentTF.setBounds(11, 108, 149, 20);
		updatePatientRecord.add(updateDepartmentTF);
		
		JLabel updatePatientDiagnosisLb = new JLabel("Update Diagnosis:");
		updatePatientDiagnosisLb.setBounds(11, 137, 149, 14);
		updatePatientRecord.add(updatePatientDiagnosisLb);
		
		updateDiagnosisTF = new JTextField();
		updateDiagnosisTF.setColumns(10);
		updateDiagnosisTF.setBounds(11, 151, 149, 20);
		updatePatientRecord.add(updateDiagnosisTF);
		
		JLabel updatePatientMedicationLb = new JLabel("Update Medication:");
		updatePatientMedicationLb.setBounds(11, 179, 149, 14);
		updatePatientRecord.add(updatePatientMedicationLb);
		
		updateMedicationTF = new JTextField();
		updateMedicationTF.setColumns(10);
		updateMedicationTF.setBounds(11, 192, 149, 20);
		updatePatientRecord.add(updateMedicationTF);
		
		JLabel updatePatientScheduledOpLb = new JLabel("Update Operation:");
		updatePatientScheduledOpLb.setBounds(11, 221, 149, 14);
		updatePatientRecord.add(updatePatientScheduledOpLb);
		
		updateOperationTF = new JTextField();
		updateOperationTF.setColumns(10);
		updateOperationTF.setBounds(11, 235, 149, 20);
		updatePatientRecord.add(updateOperationTF);
		
		JLabel UpdatePatientIdLb = new JLabel("Update Patient ID:");
		UpdatePatientIdLb.setBounds(11, 11, 149, 14);
		updatePatientRecord.add(UpdatePatientIdLb);
		
		JButton updatePatientBt = new JButton("Update Patient");
		updatePatientBt.setBounds(10, 264, 150, 29);
		updatePatientRecord.add(updatePatientBt);
	
		JLabel updatePatientOutputLb = new JLabel("Output:");
		updatePatientOutputLb.setBounds(206, 11, 46, 14);
		updatePatientRecord.add(updatePatientOutputLb);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(206, 34, 590, 270);
		updatePatientRecord.add(scrollPane_4);
		
		JTextArea updatePatientTA = new JTextArea();
		scrollPane_4.setViewportView(updatePatientTA);
		
		JPanel sharePatientRecord = new JPanel();
		tabbedPane_3.addTab("Share Patient Record", null, sharePatientRecord, null);
		sharePatientRecord.setLayout(null);
		
		JLabel sharePatientnNumRecords = new JLabel("Number of records to share:");
		sharePatientnNumRecords.setBounds(10, 34, 180, 14);
		sharePatientRecord.add(sharePatientnNumRecords);
		
		sharePatientNumRecordsTF = new JTextField();
		sharePatientNumRecordsTF.setBounds(10, 55, 138, 20);
		sharePatientRecord.add(sharePatientNumRecordsTF);
		sharePatientNumRecordsTF.setColumns(10);
		
		JLabel sharePatientTargetLb = new JLabel("Target Server:");
		sharePatientTargetLb.setBounds(10, 95, 113, 14);
		sharePatientRecord.add(sharePatientTargetLb);
		
		JComboBox sharePatientTargetCB = new JComboBox();
		sharePatientTargetCB.setBounds(10, 115, 180, 22);
		sharePatientRecord.add(sharePatientTargetCB);
		
		sharePatientTargetCB.addItem("St. Patricks Hospital");
		sharePatientTargetCB.addItem("Bon Secours Dublin");
		sharePatientTargetCB.addItem("Cloud Service");
		
		JButton sharePatientBt = new JButton("Share Records");
		sharePatientBt.setBounds(20, 169, 149, 39);
		sharePatientRecord.add(sharePatientBt);
	
		JLabel sharePatientOutputLb = new JLabel("Output:");
		sharePatientOutputLb.setBounds(206, 11, 46, 14);
		sharePatientRecord.add(sharePatientOutputLb);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(206, 34, 590, 270);
		sharePatientRecord.add(scrollPane_5);
		
		JTextArea sharePatientOutputTA = new JTextArea();
		scrollPane_5.setViewportView(sharePatientOutputTA);

		JComboBox updatePatientIdCB = new JComboBox();
		updatePatientIdCB.setEditable(true);
		updatePatientIdCB.setToolTipText("");
		updatePatientIdCB.setBounds(11, 23, 149, 22);
		updatePatientRecord.add(updatePatientIdCB);
		
		
		
		// Unary method addPatient - enter patientID, press Add Patient button, output to TextArea
		searchPatientBt.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String patientId = searchPatientIdTF.getText();
		        String result = EHRManagementClient.searchPatientRecord(patientId);
		        searchPatientOutputTA.setText(result);
		    }
		});
		
		
		
	 // Unary method updatePatient - listener on combo box for when user selects a patient ID, calls actionPerformed method
	 //  fetches patient info and update text fields with the patient data
		final String[] patientIds = {"AB001", "AB002"};
		// for loop to traverse list of patient ID's (only two hard coded) and add them to the combo box
		for (String patientId : patientIds) {
		    updatePatientIdCB.addItem(patientId);
		}
		// triggered if user selected from patient ID list, updates fields with info
		updatePatientIdCB.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String selectedPatientId = (String) updatePatientIdCB.getSelectedItem();
		        if (selectedPatientId != null) {
		            SearchPatientRecordResponse patientInfo = EHRManagementClient.getPatientRecord(selectedPatientId);
		            if (patientInfo != null) {
		                updatePatientNameTF.setText(patientInfo.getPatientName());
		                updateDepartmentTF.setText(patientInfo.getDepartment());
		                updateDiagnosisTF.setText(patientInfo.getDiagnosis());
		                updateMedicationTF.setText(patientInfo.getMedication());
		                updateOperationTF.setText(patientInfo.getScheduledOperation());
		        }
		        }
		    }
		});
		
		updatePatientBt.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String patientId = (String) updatePatientIdCB.getSelectedItem();
		        String name = updatePatientNameTF.getText();
		        String department = updateDepartmentTF.getText();
		        String diagnosis = updateDiagnosisTF.getText();
		        String medication = updateMedicationTF.getText();
		        String operation = updateOperationTF.getText();
		        
		        EHRManagementClient.updatePatientRecord(patientId, name, department, diagnosis, medication, operation);
		        updatePatientTA.append("Patient has been updated!\n");
		        updatePatientTA.append("Updated patient info:\n");
		        updatePatientTA.append("Patient ID: " + patientId + "\n");
		        updatePatientTA.append("Name: " + name + "\n");
		        updatePatientTA.append("Department: " + department + "\n");
		        updatePatientTA.append("Diagnosis: " + diagnosis + "\n");
		        updatePatientTA.append("Medication: " + medication + "\n");
		        updatePatientTA.append("Operation: " + operation + "\n");
		        updatePatientTA.append("\n");
		    }
		});
		
		
		
		// Client streaming gRPC method - sharePatientRecords
		// enter the number of records to share and then choose the mock location they would be shared/stored, button calls method to print in TA
		sharePatientBt.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int numRecords = Integer.parseInt(sharePatientNumRecordsTF.getText());
		        sharePatientOutputTA.setText(""); 
		        EHRManagementClient.sharePatientRecord(numRecords, new sharePatientListener() {
		            @Override
		            public void onSharePatientEvent(String recordContent) {
		                // Update the output text area with the received record content
		                sharePatientOutputTA.append(recordContent);
		                sharePatientOutputTA.append("\n"); 
		            }
		        });
		    }
		});
		
		
		
		
		
		/*
		 * MEDICATION MANAGER SERVICE 3 METHODS - addMedicine, confirmMedicine, adjustDosage
		 * Unary, Server-streaming, Bi-directional, respectively
		 */
		JPanel MedicationManager = new JPanel();
		tabbedPane.addTab("Medication Manager", null, MedicationManager, null);
		MedicationManager.setLayout(null);

		JTabbedPane medicationManagerTabsContainer = new JTabbedPane(JTabbedPane.TOP);
		medicationManagerTabsContainer.setBounds(10, 11, 811, 343);
		MedicationManager.add(medicationManagerTabsContainer);

		// ***************************** COMPONENTS OF ADD MEDICINE TAB *****************************
		JPanel addMedicineTabbedPane = new JPanel();
		medicationManagerTabsContainer.addTab("Add Medication", null, addMedicineTabbedPane, null);
		addMedicineTabbedPane.setLayout(null);

		JLabel addMedicineMedicationSideEffectsLb = new JLabel("Medication Side Effects:");
		addMedicineMedicationSideEffectsLb.setBounds(10, 179, 154, 24);
		addMedicineTabbedPane.add(addMedicineMedicationSideEffectsLb);

		JLabel addMedicineMedicationNameLb = new JLabel("Medication Name:");
		addMedicineMedicationNameLb.setBounds(10, 69, 154, 24);
		addMedicineTabbedPane.add(addMedicineMedicationNameLb);

		JLabel addMedicineMedicationDosageLb = new JLabel("Medication Dosage:");
		addMedicineMedicationDosageLb.setBounds(10, 128, 154, 14);
		addMedicineTabbedPane.add(addMedicineMedicationDosageLb);

		JLabel addMedicinePatientIdLb = new JLabel("Patient ID:");
		addMedicinePatientIdLb.setBounds(10, 22, 136, 12);
		addMedicineTabbedPane.add(addMedicinePatientIdLb);

		addMedicinePatientIdTF = new JTextField();
		addMedicinePatientIdTF.setBounds(10, 34, 154, 24);
		addMedicineTabbedPane.add(addMedicinePatientIdTF);
		addMedicinePatientIdTF.setColumns(10);

		addMedicineMedicationNameTF = new JTextField();
		addMedicineMedicationNameTF.setColumns(10);
		addMedicineMedicationNameTF.setBounds(10, 87, 154, 24);
		addMedicineTabbedPane.add(addMedicineMedicationNameTF);

		addMedicineMedicationDosageTF = new JTextField();
		addMedicineMedicationDosageTF.setColumns(10);
		addMedicineMedicationDosageTF.setBounds(10, 142, 154, 24);
		addMedicineTabbedPane.add(addMedicineMedicationDosageTF);

		addMedicineMedicationSideEffectsTF = new JTextField();
		addMedicineMedicationSideEffectsTF.setColumns(10);
		addMedicineMedicationSideEffectsTF.setBounds(10, 197, 154, 24);
		addMedicineTabbedPane.add(addMedicineMedicationSideEffectsTF);

		JButton addMedicineBt = new JButton("Add Medicine");
		addMedicineBt.setBounds(23, 243, 123, 34);
		addMedicineTabbedPane.add(addMedicineBt);

		JLabel addMedicineOutputLb = new JLabel("Output:");
		addMedicineOutputLb.setBounds(191, 21, 79, 14);
		addMedicineTabbedPane.add(addMedicineOutputLb);

		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(191, 36, 605, 268);
		addMedicineTabbedPane.add(scrollPane_6);

		JTextArea addMedicineOutputTA = new JTextArea();
		scrollPane_6.setViewportView(addMedicineOutputTA);

		JPanel confirmMedication = new JPanel();
		medicationManagerTabsContainer.addTab("Confirm Medication", null, confirmMedication, null);
		confirmMedication.setLayout(null);

		JLabel medicationName1Lb = new JLabel("Medication Name 1:");
		medicationName1Lb.setBounds(10, 11, 134, 14);
		confirmMedication.add(medicationName1Lb);

		JLabel medicationName2Lb = new JLabel("Medication Name 2:");
		medicationName2Lb.setBounds(10, 97, 134, 14);
		confirmMedication.add(medicationName2Lb);

		JLabel medicationName3Lb = new JLabel("Medication Name 3:");
		medicationName3Lb.setBounds(10, 182, 134, 14);
		confirmMedication.add(medicationName3Lb);

		medicationName1TF = new JTextField();
		medicationName1TF.setBounds(10, 26, 134, 20);
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
		medicationDosage1Lb.setBounds(10, 50, 134, 14);
		confirmMedication.add(medicationDosage1Lb);

		JLabel medicationDosage3Lb = new JLabel("Medication Dosage 3:");
		medicationDosage3Lb.setBounds(10, 222, 134, 14);
		confirmMedication.add(medicationDosage3Lb);

		JLabel medicationDosage2Lb = new JLabel("Medication Dosage 2:");
		medicationDosage2Lb.setBounds(10, 134, 134, 14);
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
		confirmMedicationOutputLb.setBounds(191, 21, 60, 14);
		confirmMedication.add(confirmMedicationOutputLb);

		JButton confirmMedicationBt = new JButton("Confirm Medication");
		confirmMedicationBt.setBounds(20, 270, 153, 34);
		confirmMedication.add(confirmMedicationBt);

		JScrollPane scrollPane_7 = new JScrollPane();
		scrollPane_7.setBounds(191, 36, 605, 268);
		confirmMedication.add(scrollPane_7);

		JTextArea confirmMedicationOutputTA = new JTextArea();
		scrollPane_7.setViewportView(confirmMedicationOutputTA);

		JPanel adjustDosage = new JPanel();
		medicationManagerTabsContainer.addTab("Adjust Dosage Service", null, adjustDosage, null);
		adjustDosage.setLayout(null);

		JLabel adjustDosageOutputLb = new JLabel("Output:");
		adjustDosageOutputLb.setBounds(191, 21, 47, 14);
		adjustDosage.add(adjustDosageOutputLb);

		JLabel bloodSugarLb = new JLabel("Enter initial Blood Sugar value:");
		bloodSugarLb.setBounds(10, 38, 191, 22);
		adjustDosage.add(bloodSugarLb);

		bloodSugarTF = new JTextField();
		bloodSugarTF.setBounds(10, 60, 86, 20);
		adjustDosage.add(bloodSugarTF);
		bloodSugarTF.setColumns(10);

		JLabel bloodSugarUnitLb = new JLabel("mmol/L");
		bloodSugarUnitLb.setBounds(98, 67, 46, 14);
		adjustDosage.add(bloodSugarUnitLb);

		JButton adjustDosageStreamBt = new JButton("Stream Dynamic Dose");
		adjustDosageStreamBt.setBounds(20, 105, 146, 30);
		adjustDosage.add(adjustDosageStreamBt);

		JScrollPane scrollPane_8 = new JScrollPane();
		scrollPane_8.setBounds(191, 36, 605, 268);
		adjustDosage.add(scrollPane_8);

		JTextArea adjustDosageOutputTA = new JTextArea();
		scrollPane_8.setViewportView(adjustDosageOutputTA);

		// add medicine unary method
		// user enters patient ID, Name, Dosage, and side effects, press add medicine and receive output to text field
		addMedicineBt.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
		    String patientId = addMedicinePatientIdTF.getText();
		    String medicationName = addMedicineMedicationNameTF.getText();
		    String medicationDosage = addMedicineMedicationDosageTF.getText();
		    String medicationSideEffects = addMedicineMedicationSideEffectsTF.getText();

		    try {
		      String addMedicineMessage = patientMedicationClient.addMedication(patientId, medicationName, medicationDosage, medicationSideEffects);
		      addMedicineOutputTA.append(addMedicineMessage);
		    } catch (Exception ex) {
		      JOptionPane.showMessageDialog(null, "Error while adding medicine. Please check the input values.");
		    }
		  }
		});

		// confirm medication unary method
		// enter medication name and dosage for 1-3 medicines, press add medicine, receive output in text area
		confirmMedicationBt.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
		    try {
		      String medicationName1 = medicationName1TF.getText();
		      String medicationDosage1 = medicationDosage1TF.getText();
		      String medicationName2 = medicationName2TF.getText();
		      String medicationDosage2 = medicationDosage2TF.getText();
		      String medicationName3 = medicationName3TF.getText();
		      String medicationDosage3 = medicationDosage3TF.getText();

		      MedicationConfirmationOutputListener callback = new MedicationConfirmationOutputListener() {
		        @Override
		        public void onNewConfirmationEvent(String message) {
		          confirmMedicationOutputTA.append(message);
		        }
		      };

		      patientMedicationClient.confirmMedication(medicationName1, medicationDosage1, callback);
		      patientMedicationClient.confirmMedication(medicationName2, medicationDosage2, callback);
		      patientMedicationClient.confirmMedication(medicationName3, medicationDosage3, callback);

		    } catch (Exception ex) {
		      JOptionPane.showMessageDialog(null, "Error while confirming medication. Please check the input values.");
		    }
		  }
		});

		// Adjust dosage bi-directional stream using callback
		adjustDosageStreamBt.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
		    try {
		      float bloodSugarLevel = Float.parseFloat(bloodSugarTF.getText());
		      patientMedicationClient.adjustDosage(bloodSugarLevel, message -> {
		        adjustDosageOutputTA.append(message);
		      });
		    } catch (NumberFormatException ex) {
		      JOptionPane.showMessageDialog(null, "Invalid blood sugar level value. Please enter a valid number.");
		    }
		  }
		});

		
		
		// shutdown
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
		  public void run() {
		    try {
		      patientMedicationClient.shutdown();
		      patientMonitorClient.shutdown();
		      EHRManagementClient.shutdown();
		    } catch (InterruptedException e) {
		      e.printStackTrace();
		    }
		  }
		}));
		}
		}
