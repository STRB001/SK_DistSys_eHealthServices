// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: PatientMedicationManagement.proto

package com.example.MedicationManagement.grpc;

public interface AdjustDosageRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:PatientMedicationManagement.AdjustDosageRequest)
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
   * <code>float blood_sugar_level = 2;</code>
   */
  float getBloodSugarLevel();
}
