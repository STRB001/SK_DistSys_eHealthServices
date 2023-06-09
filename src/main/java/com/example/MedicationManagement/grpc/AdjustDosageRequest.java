// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: PatientMedicationManagement.proto

package com.example.MedicationManagement.grpc;

/**
 * <pre>
 * Streamed request message for AdjustDosage method
 * </pre>
 *
 * Protobuf type {@code PatientMedicationManagement.AdjustDosageRequest}
 */
public  final class AdjustDosageRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:PatientMedicationManagement.AdjustDosageRequest)
    AdjustDosageRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use AdjustDosageRequest.newBuilder() to construct.
  private AdjustDosageRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private AdjustDosageRequest() {
    patientId_ = "";
    bloodSugarLevel_ = 0F;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private AdjustDosageRequest(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            patientId_ = s;
            break;
          }
          case 21: {

            bloodSugarLevel_ = input.readFloat();
            break;
          }
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.example.MedicationManagement.grpc.PatientMedicationImpl.internal_static_PatientMedicationManagement_AdjustDosageRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.example.MedicationManagement.grpc.PatientMedicationImpl.internal_static_PatientMedicationManagement_AdjustDosageRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.example.MedicationManagement.grpc.AdjustDosageRequest.class, com.example.MedicationManagement.grpc.AdjustDosageRequest.Builder.class);
  }

  public static final int PATIENT_ID_FIELD_NUMBER = 1;
  private volatile java.lang.Object patientId_;
  /**
   * <code>string patient_id = 1;</code>
   */
  public java.lang.String getPatientId() {
    java.lang.Object ref = patientId_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      patientId_ = s;
      return s;
    }
  }
  /**
   * <code>string patient_id = 1;</code>
   */
  public com.google.protobuf.ByteString
      getPatientIdBytes() {
    java.lang.Object ref = patientId_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      patientId_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int BLOOD_SUGAR_LEVEL_FIELD_NUMBER = 2;
  private float bloodSugarLevel_;
  /**
   * <code>float blood_sugar_level = 2;</code>
   */
  public float getBloodSugarLevel() {
    return bloodSugarLevel_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getPatientIdBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, patientId_);
    }
    if (bloodSugarLevel_ != 0F) {
      output.writeFloat(2, bloodSugarLevel_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getPatientIdBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, patientId_);
    }
    if (bloodSugarLevel_ != 0F) {
      size += com.google.protobuf.CodedOutputStream
        .computeFloatSize(2, bloodSugarLevel_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.example.MedicationManagement.grpc.AdjustDosageRequest)) {
      return super.equals(obj);
    }
    com.example.MedicationManagement.grpc.AdjustDosageRequest other = (com.example.MedicationManagement.grpc.AdjustDosageRequest) obj;

    boolean result = true;
    result = result && getPatientId()
        .equals(other.getPatientId());
    result = result && (
        java.lang.Float.floatToIntBits(getBloodSugarLevel())
        == java.lang.Float.floatToIntBits(
            other.getBloodSugarLevel()));
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + PATIENT_ID_FIELD_NUMBER;
    hash = (53 * hash) + getPatientId().hashCode();
    hash = (37 * hash) + BLOOD_SUGAR_LEVEL_FIELD_NUMBER;
    hash = (53 * hash) + java.lang.Float.floatToIntBits(
        getBloodSugarLevel());
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.example.MedicationManagement.grpc.AdjustDosageRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.example.MedicationManagement.grpc.AdjustDosageRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.example.MedicationManagement.grpc.AdjustDosageRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.example.MedicationManagement.grpc.AdjustDosageRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.example.MedicationManagement.grpc.AdjustDosageRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.example.MedicationManagement.grpc.AdjustDosageRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.example.MedicationManagement.grpc.AdjustDosageRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.example.MedicationManagement.grpc.AdjustDosageRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.example.MedicationManagement.grpc.AdjustDosageRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.example.MedicationManagement.grpc.AdjustDosageRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.example.MedicationManagement.grpc.AdjustDosageRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.example.MedicationManagement.grpc.AdjustDosageRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.example.MedicationManagement.grpc.AdjustDosageRequest prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * <pre>
   * Streamed request message for AdjustDosage method
   * </pre>
   *
   * Protobuf type {@code PatientMedicationManagement.AdjustDosageRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:PatientMedicationManagement.AdjustDosageRequest)
      com.example.MedicationManagement.grpc.AdjustDosageRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.example.MedicationManagement.grpc.PatientMedicationImpl.internal_static_PatientMedicationManagement_AdjustDosageRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.example.MedicationManagement.grpc.PatientMedicationImpl.internal_static_PatientMedicationManagement_AdjustDosageRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.example.MedicationManagement.grpc.AdjustDosageRequest.class, com.example.MedicationManagement.grpc.AdjustDosageRequest.Builder.class);
    }

    // Construct using com.example.MedicationManagement.grpc.AdjustDosageRequest.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      patientId_ = "";

      bloodSugarLevel_ = 0F;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.example.MedicationManagement.grpc.PatientMedicationImpl.internal_static_PatientMedicationManagement_AdjustDosageRequest_descriptor;
    }

    @java.lang.Override
    public com.example.MedicationManagement.grpc.AdjustDosageRequest getDefaultInstanceForType() {
      return com.example.MedicationManagement.grpc.AdjustDosageRequest.getDefaultInstance();
    }

    @java.lang.Override
    public com.example.MedicationManagement.grpc.AdjustDosageRequest build() {
      com.example.MedicationManagement.grpc.AdjustDosageRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.example.MedicationManagement.grpc.AdjustDosageRequest buildPartial() {
      com.example.MedicationManagement.grpc.AdjustDosageRequest result = new com.example.MedicationManagement.grpc.AdjustDosageRequest(this);
      result.patientId_ = patientId_;
      result.bloodSugarLevel_ = bloodSugarLevel_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return (Builder) super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.example.MedicationManagement.grpc.AdjustDosageRequest) {
        return mergeFrom((com.example.MedicationManagement.grpc.AdjustDosageRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.example.MedicationManagement.grpc.AdjustDosageRequest other) {
      if (other == com.example.MedicationManagement.grpc.AdjustDosageRequest.getDefaultInstance()) return this;
      if (!other.getPatientId().isEmpty()) {
        patientId_ = other.patientId_;
        onChanged();
      }
      if (other.getBloodSugarLevel() != 0F) {
        setBloodSugarLevel(other.getBloodSugarLevel());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.example.MedicationManagement.grpc.AdjustDosageRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.example.MedicationManagement.grpc.AdjustDosageRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object patientId_ = "";
    /**
     * <code>string patient_id = 1;</code>
     */
    public java.lang.String getPatientId() {
      java.lang.Object ref = patientId_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        patientId_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string patient_id = 1;</code>
     */
    public com.google.protobuf.ByteString
        getPatientIdBytes() {
      java.lang.Object ref = patientId_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        patientId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string patient_id = 1;</code>
     */
    public Builder setPatientId(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      patientId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string patient_id = 1;</code>
     */
    public Builder clearPatientId() {
      
      patientId_ = getDefaultInstance().getPatientId();
      onChanged();
      return this;
    }
    /**
     * <code>string patient_id = 1;</code>
     */
    public Builder setPatientIdBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      patientId_ = value;
      onChanged();
      return this;
    }

    private float bloodSugarLevel_ ;
    /**
     * <code>float blood_sugar_level = 2;</code>
     */
    public float getBloodSugarLevel() {
      return bloodSugarLevel_;
    }
    /**
     * <code>float blood_sugar_level = 2;</code>
     */
    public Builder setBloodSugarLevel(float value) {
      
      bloodSugarLevel_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>float blood_sugar_level = 2;</code>
     */
    public Builder clearBloodSugarLevel() {
      
      bloodSugarLevel_ = 0F;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:PatientMedicationManagement.AdjustDosageRequest)
  }

  // @@protoc_insertion_point(class_scope:PatientMedicationManagement.AdjustDosageRequest)
  private static final com.example.MedicationManagement.grpc.AdjustDosageRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.example.MedicationManagement.grpc.AdjustDosageRequest();
  }

  public static com.example.MedicationManagement.grpc.AdjustDosageRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<AdjustDosageRequest>
      PARSER = new com.google.protobuf.AbstractParser<AdjustDosageRequest>() {
    @java.lang.Override
    public AdjustDosageRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new AdjustDosageRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<AdjustDosageRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<AdjustDosageRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.example.MedicationManagement.grpc.AdjustDosageRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

