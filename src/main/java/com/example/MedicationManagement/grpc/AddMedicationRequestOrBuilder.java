// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: PatientMedicationManagement.proto

package com.example.MedicationManagement.grpc;

public interface AddMedicationRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:PatientMedicationManagement.AddMedicationRequest)
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
   * <code>string medication_name = 2;</code>
   */
  java.lang.String getMedicationName();
  /**
   * <code>string medication_name = 2;</code>
   */
  com.google.protobuf.ByteString
      getMedicationNameBytes();

  /**
   * <code>string dosage = 3;</code>
   */
  java.lang.String getDosage();
  /**
   * <code>string dosage = 3;</code>
   */
  com.google.protobuf.ByteString
      getDosageBytes();

  /**
   * <code>string side_effects = 4;</code>
   */
  java.lang.String getSideEffects();
  /**
   * <code>string side_effects = 4;</code>
   */
  com.google.protobuf.ByteString
      getSideEffectsBytes();
}
