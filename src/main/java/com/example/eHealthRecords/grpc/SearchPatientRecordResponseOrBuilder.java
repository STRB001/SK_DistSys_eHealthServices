// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: EHRManagement.proto

package com.example.eHealthRecords.grpc;

public interface SearchPatientRecordResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:EHRManagement.SearchPatientRecordResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string patient_id = 1;</code>
   */
  java.lang.String getPatientId();
  /**
   * <code>string patient_id = 1;</code>
   */
  com.google.protobuf.ByteString
      getPatientIdBytes();

  /**
   * <code>string patient_name = 2;</code>
   */
  java.lang.String getPatientName();
  /**
   * <code>string patient_name = 2;</code>
   */
  com.google.protobuf.ByteString
      getPatientNameBytes();

  /**
   * <code>string department = 3;</code>
   */
  java.lang.String getDepartment();
  /**
   * <code>string department = 3;</code>
   */
  com.google.protobuf.ByteString
      getDepartmentBytes();

  /**
   * <code>string diagnosis = 4;</code>
   */
  java.lang.String getDiagnosis();
  /**
   * <code>string diagnosis = 4;</code>
   */
  com.google.protobuf.ByteString
      getDiagnosisBytes();

  /**
   * <code>string medication = 5;</code>
   */
  java.lang.String getMedication();
  /**
   * <code>string medication = 5;</code>
   */
  com.google.protobuf.ByteString
      getMedicationBytes();

  /**
   * <code>string scheduled_operation = 6;</code>
   */
  java.lang.String getScheduledOperation();
  /**
   * <code>string scheduled_operation = 6;</code>
   */
  com.google.protobuf.ByteString
      getScheduledOperationBytes();
}
