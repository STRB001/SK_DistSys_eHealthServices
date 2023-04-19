// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: EHRManagement.proto

package com.example.eHealthRecords.grpc;

public final class EHRManagementImpl {
  private EHRManagementImpl() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_EHRManagement_Patient_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_EHRManagement_Patient_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_EHRManagement_SearchPatientRecordRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_EHRManagement_SearchPatientRecordRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_EHRManagement_SearchPatientRecordResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_EHRManagement_SearchPatientRecordResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_EHRManagement_UpdatePatientRecordRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_EHRManagement_UpdatePatientRecordRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_EHRManagement_UpdatePatientRecordResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_EHRManagement_UpdatePatientRecordResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_EHRManagement_SharePatientRecordRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_EHRManagement_SharePatientRecordRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_EHRManagement_SharePatientRecordResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_EHRManagement_SharePatientRecordResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\023EHRManagement.proto\022\rEHRManagement\"q\n\007" +
      "Patient\022\n\n\002id\030\001 \001(\t\022\014\n\004name\030\002 \001(\t\022\022\n\ndep" +
      "artment\030\003 \001(\t\022\021\n\tdiagnosis\030\004 \001(\t\022\022\n\nmedi" +
      "cation\030\005 \001(\t\022\021\n\toperation\030\006 \001(\t\"0\n\032Searc" +
      "hPatientRecordRequest\022\022\n\npatient_id\030\001 \001(" +
      "\t\"\237\001\n\033SearchPatientRecordResponse\022\022\n\npat" +
      "ient_id\030\001 \001(\t\022\024\n\014patient_name\030\002 \001(\t\022\022\n\nd" +
      "epartment\030\003 \001(\t\022\021\n\tdiagnosis\030\004 \001(\t\022\022\n\nme" +
      "dication\030\005 \001(\t\022\033\n\023scheduled_operation\030\006 " +
      "\001(\t\"\214\001\n\032UpdatePatientRecordRequest\022\022\n\npa" +
      "tient_id\030\001 \001(\t\022\014\n\004name\030\002 \001(\t\022\022\n\ndepartme" +
      "nt\030\003 \001(\t\022\021\n\tdiagnosis\030\004 \001(\t\022\022\n\nmedicatio" +
      "n\030\005 \001(\t\022\021\n\toperation\030\006 \001(\t\"p\n\033UpdatePati" +
      "entRecordResponse\022\017\n\007success\030\001 \001(\010\022\017\n\007me" +
      "ssage\030\002 \001(\t\022/\n\017updated_patient\030\003 \001(\0132\026.E" +
      "HRManagement.Patient\"\\\n\031SharePatientReco" +
      "rdRequest\022\022\n\npatient_id\030\001 \001(\t\022\023\n\013record_" +
      "part\030\002 \001(\t\022\026\n\016record_content\030\003 \001(\t\">\n\032Sh" +
      "arePatientRecordResponse\022\017\n\007success\030\001 \001(" +
      "\010\022\017\n\007message\030\002 \001(\t2\336\002\n\rEHRManagement\022n\n\023" +
      "SearchPatientRecord\022).EHRManagement.Sear" +
      "chPatientRecordRequest\032*.EHRManagement.S" +
      "earchPatientRecordResponse\"\000\022n\n\023UpdatePa" +
      "tientRecord\022).EHRManagement.UpdatePatien" +
      "tRecordRequest\032*.EHRManagement.UpdatePat" +
      "ientRecordResponse\"\000\022m\n\022SharePatientReco" +
      "rd\022(.EHRManagement.SharePatientRecordReq" +
      "uest\032).EHRManagement.SharePatientRecordR" +
      "esponse\"\000(\001B6\n\037com.example.eHealthRecord" +
      "s.grpcB\021EHRManagementImplP\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_EHRManagement_Patient_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_EHRManagement_Patient_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_EHRManagement_Patient_descriptor,
        new java.lang.String[] { "Id", "Name", "Department", "Diagnosis", "Medication", "Operation", });
    internal_static_EHRManagement_SearchPatientRecordRequest_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_EHRManagement_SearchPatientRecordRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_EHRManagement_SearchPatientRecordRequest_descriptor,
        new java.lang.String[] { "PatientId", });
    internal_static_EHRManagement_SearchPatientRecordResponse_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_EHRManagement_SearchPatientRecordResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_EHRManagement_SearchPatientRecordResponse_descriptor,
        new java.lang.String[] { "PatientId", "PatientName", "Department", "Diagnosis", "Medication", "ScheduledOperation", });
    internal_static_EHRManagement_UpdatePatientRecordRequest_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_EHRManagement_UpdatePatientRecordRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_EHRManagement_UpdatePatientRecordRequest_descriptor,
        new java.lang.String[] { "PatientId", "Name", "Department", "Diagnosis", "Medication", "Operation", });
    internal_static_EHRManagement_UpdatePatientRecordResponse_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_EHRManagement_UpdatePatientRecordResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_EHRManagement_UpdatePatientRecordResponse_descriptor,
        new java.lang.String[] { "Success", "Message", "UpdatedPatient", });
    internal_static_EHRManagement_SharePatientRecordRequest_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_EHRManagement_SharePatientRecordRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_EHRManagement_SharePatientRecordRequest_descriptor,
        new java.lang.String[] { "PatientId", "RecordPart", "RecordContent", });
    internal_static_EHRManagement_SharePatientRecordResponse_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_EHRManagement_SharePatientRecordResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_EHRManagement_SharePatientRecordResponse_descriptor,
        new java.lang.String[] { "Success", "Message", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
