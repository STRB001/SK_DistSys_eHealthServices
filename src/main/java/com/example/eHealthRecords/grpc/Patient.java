// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: EHRManagement.proto

package com.example.eHealthRecords.grpc;

/**
 * Protobuf type {@code EHRManagement.Patient}
 */
public  final class Patient extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:EHRManagement.Patient)
    PatientOrBuilder {
private static final long serialVersionUID = 0L;
  // Use Patient.newBuilder() to construct.
  private Patient(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private Patient() {
    id_ = "";
    name_ = "";
    department_ = "";
    diagnosis_ = "";
    medication_ = "";
    operation_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private Patient(
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

            id_ = s;
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            name_ = s;
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();

            department_ = s;
            break;
          }
          case 34: {
            java.lang.String s = input.readStringRequireUtf8();

            diagnosis_ = s;
            break;
          }
          case 42: {
            java.lang.String s = input.readStringRequireUtf8();

            medication_ = s;
            break;
          }
          case 50: {
            java.lang.String s = input.readStringRequireUtf8();

            operation_ = s;
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
    return com.example.eHealthRecords.grpc.EHRManagementImpl.internal_static_EHRManagement_Patient_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.example.eHealthRecords.grpc.EHRManagementImpl.internal_static_EHRManagement_Patient_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.example.eHealthRecords.grpc.Patient.class, com.example.eHealthRecords.grpc.Patient.Builder.class);
  }

  public static final int ID_FIELD_NUMBER = 1;
  private volatile java.lang.Object id_;
  /**
   * <code>string id = 1;</code>
   */
  public java.lang.String getId() {
    java.lang.Object ref = id_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      id_ = s;
      return s;
    }
  }
  /**
   * <code>string id = 1;</code>
   */
  public com.google.protobuf.ByteString
      getIdBytes() {
    java.lang.Object ref = id_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      id_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int NAME_FIELD_NUMBER = 2;
  private volatile java.lang.Object name_;
  /**
   * <code>string name = 2;</code>
   */
  public java.lang.String getName() {
    java.lang.Object ref = name_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      name_ = s;
      return s;
    }
  }
  /**
   * <code>string name = 2;</code>
   */
  public com.google.protobuf.ByteString
      getNameBytes() {
    java.lang.Object ref = name_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      name_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int DEPARTMENT_FIELD_NUMBER = 3;
  private volatile java.lang.Object department_;
  /**
   * <code>string department = 3;</code>
   */
  public java.lang.String getDepartment() {
    java.lang.Object ref = department_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      department_ = s;
      return s;
    }
  }
  /**
   * <code>string department = 3;</code>
   */
  public com.google.protobuf.ByteString
      getDepartmentBytes() {
    java.lang.Object ref = department_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      department_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int DIAGNOSIS_FIELD_NUMBER = 4;
  private volatile java.lang.Object diagnosis_;
  /**
   * <code>string diagnosis = 4;</code>
   */
  public java.lang.String getDiagnosis() {
    java.lang.Object ref = diagnosis_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      diagnosis_ = s;
      return s;
    }
  }
  /**
   * <code>string diagnosis = 4;</code>
   */
  public com.google.protobuf.ByteString
      getDiagnosisBytes() {
    java.lang.Object ref = diagnosis_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      diagnosis_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int MEDICATION_FIELD_NUMBER = 5;
  private volatile java.lang.Object medication_;
  /**
   * <code>string medication = 5;</code>
   */
  public java.lang.String getMedication() {
    java.lang.Object ref = medication_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      medication_ = s;
      return s;
    }
  }
  /**
   * <code>string medication = 5;</code>
   */
  public com.google.protobuf.ByteString
      getMedicationBytes() {
    java.lang.Object ref = medication_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      medication_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int OPERATION_FIELD_NUMBER = 6;
  private volatile java.lang.Object operation_;
  /**
   * <code>string operation = 6;</code>
   */
  public java.lang.String getOperation() {
    java.lang.Object ref = operation_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      operation_ = s;
      return s;
    }
  }
  /**
   * <code>string operation = 6;</code>
   */
  public com.google.protobuf.ByteString
      getOperationBytes() {
    java.lang.Object ref = operation_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      operation_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
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
    if (!getIdBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, id_);
    }
    if (!getNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, name_);
    }
    if (!getDepartmentBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, department_);
    }
    if (!getDiagnosisBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 4, diagnosis_);
    }
    if (!getMedicationBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 5, medication_);
    }
    if (!getOperationBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 6, operation_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getIdBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, id_);
    }
    if (!getNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, name_);
    }
    if (!getDepartmentBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, department_);
    }
    if (!getDiagnosisBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, diagnosis_);
    }
    if (!getMedicationBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(5, medication_);
    }
    if (!getOperationBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(6, operation_);
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
    if (!(obj instanceof com.example.eHealthRecords.grpc.Patient)) {
      return super.equals(obj);
    }
    com.example.eHealthRecords.grpc.Patient other = (com.example.eHealthRecords.grpc.Patient) obj;

    boolean result = true;
    result = result && getId()
        .equals(other.getId());
    result = result && getName()
        .equals(other.getName());
    result = result && getDepartment()
        .equals(other.getDepartment());
    result = result && getDiagnosis()
        .equals(other.getDiagnosis());
    result = result && getMedication()
        .equals(other.getMedication());
    result = result && getOperation()
        .equals(other.getOperation());
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
    hash = (37 * hash) + ID_FIELD_NUMBER;
    hash = (53 * hash) + getId().hashCode();
    hash = (37 * hash) + NAME_FIELD_NUMBER;
    hash = (53 * hash) + getName().hashCode();
    hash = (37 * hash) + DEPARTMENT_FIELD_NUMBER;
    hash = (53 * hash) + getDepartment().hashCode();
    hash = (37 * hash) + DIAGNOSIS_FIELD_NUMBER;
    hash = (53 * hash) + getDiagnosis().hashCode();
    hash = (37 * hash) + MEDICATION_FIELD_NUMBER;
    hash = (53 * hash) + getMedication().hashCode();
    hash = (37 * hash) + OPERATION_FIELD_NUMBER;
    hash = (53 * hash) + getOperation().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.example.eHealthRecords.grpc.Patient parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.example.eHealthRecords.grpc.Patient parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.example.eHealthRecords.grpc.Patient parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.example.eHealthRecords.grpc.Patient parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.example.eHealthRecords.grpc.Patient parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.example.eHealthRecords.grpc.Patient parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.example.eHealthRecords.grpc.Patient parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.example.eHealthRecords.grpc.Patient parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.example.eHealthRecords.grpc.Patient parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.example.eHealthRecords.grpc.Patient parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.example.eHealthRecords.grpc.Patient parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.example.eHealthRecords.grpc.Patient parseFrom(
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
  public static Builder newBuilder(com.example.eHealthRecords.grpc.Patient prototype) {
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
   * Protobuf type {@code EHRManagement.Patient}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:EHRManagement.Patient)
      com.example.eHealthRecords.grpc.PatientOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.example.eHealthRecords.grpc.EHRManagementImpl.internal_static_EHRManagement_Patient_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.example.eHealthRecords.grpc.EHRManagementImpl.internal_static_EHRManagement_Patient_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.example.eHealthRecords.grpc.Patient.class, com.example.eHealthRecords.grpc.Patient.Builder.class);
    }

    // Construct using com.example.eHealthRecords.grpc.Patient.newBuilder()
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
      id_ = "";

      name_ = "";

      department_ = "";

      diagnosis_ = "";

      medication_ = "";

      operation_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.example.eHealthRecords.grpc.EHRManagementImpl.internal_static_EHRManagement_Patient_descriptor;
    }

    @java.lang.Override
    public com.example.eHealthRecords.grpc.Patient getDefaultInstanceForType() {
      return com.example.eHealthRecords.grpc.Patient.getDefaultInstance();
    }

    @java.lang.Override
    public com.example.eHealthRecords.grpc.Patient build() {
      com.example.eHealthRecords.grpc.Patient result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.example.eHealthRecords.grpc.Patient buildPartial() {
      com.example.eHealthRecords.grpc.Patient result = new com.example.eHealthRecords.grpc.Patient(this);
      result.id_ = id_;
      result.name_ = name_;
      result.department_ = department_;
      result.diagnosis_ = diagnosis_;
      result.medication_ = medication_;
      result.operation_ = operation_;
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
      if (other instanceof com.example.eHealthRecords.grpc.Patient) {
        return mergeFrom((com.example.eHealthRecords.grpc.Patient)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.example.eHealthRecords.grpc.Patient other) {
      if (other == com.example.eHealthRecords.grpc.Patient.getDefaultInstance()) return this;
      if (!other.getId().isEmpty()) {
        id_ = other.id_;
        onChanged();
      }
      if (!other.getName().isEmpty()) {
        name_ = other.name_;
        onChanged();
      }
      if (!other.getDepartment().isEmpty()) {
        department_ = other.department_;
        onChanged();
      }
      if (!other.getDiagnosis().isEmpty()) {
        diagnosis_ = other.diagnosis_;
        onChanged();
      }
      if (!other.getMedication().isEmpty()) {
        medication_ = other.medication_;
        onChanged();
      }
      if (!other.getOperation().isEmpty()) {
        operation_ = other.operation_;
        onChanged();
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
      com.example.eHealthRecords.grpc.Patient parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.example.eHealthRecords.grpc.Patient) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object id_ = "";
    /**
     * <code>string id = 1;</code>
     */
    public java.lang.String getId() {
      java.lang.Object ref = id_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        id_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string id = 1;</code>
     */
    public com.google.protobuf.ByteString
        getIdBytes() {
      java.lang.Object ref = id_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        id_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string id = 1;</code>
     */
    public Builder setId(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      id_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string id = 1;</code>
     */
    public Builder clearId() {
      
      id_ = getDefaultInstance().getId();
      onChanged();
      return this;
    }
    /**
     * <code>string id = 1;</code>
     */
    public Builder setIdBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      id_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object name_ = "";
    /**
     * <code>string name = 2;</code>
     */
    public java.lang.String getName() {
      java.lang.Object ref = name_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        name_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string name = 2;</code>
     */
    public com.google.protobuf.ByteString
        getNameBytes() {
      java.lang.Object ref = name_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        name_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string name = 2;</code>
     */
    public Builder setName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      name_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string name = 2;</code>
     */
    public Builder clearName() {
      
      name_ = getDefaultInstance().getName();
      onChanged();
      return this;
    }
    /**
     * <code>string name = 2;</code>
     */
    public Builder setNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      name_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object department_ = "";
    /**
     * <code>string department = 3;</code>
     */
    public java.lang.String getDepartment() {
      java.lang.Object ref = department_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        department_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string department = 3;</code>
     */
    public com.google.protobuf.ByteString
        getDepartmentBytes() {
      java.lang.Object ref = department_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        department_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string department = 3;</code>
     */
    public Builder setDepartment(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      department_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string department = 3;</code>
     */
    public Builder clearDepartment() {
      
      department_ = getDefaultInstance().getDepartment();
      onChanged();
      return this;
    }
    /**
     * <code>string department = 3;</code>
     */
    public Builder setDepartmentBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      department_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object diagnosis_ = "";
    /**
     * <code>string diagnosis = 4;</code>
     */
    public java.lang.String getDiagnosis() {
      java.lang.Object ref = diagnosis_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        diagnosis_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string diagnosis = 4;</code>
     */
    public com.google.protobuf.ByteString
        getDiagnosisBytes() {
      java.lang.Object ref = diagnosis_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        diagnosis_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string diagnosis = 4;</code>
     */
    public Builder setDiagnosis(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      diagnosis_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string diagnosis = 4;</code>
     */
    public Builder clearDiagnosis() {
      
      diagnosis_ = getDefaultInstance().getDiagnosis();
      onChanged();
      return this;
    }
    /**
     * <code>string diagnosis = 4;</code>
     */
    public Builder setDiagnosisBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      diagnosis_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object medication_ = "";
    /**
     * <code>string medication = 5;</code>
     */
    public java.lang.String getMedication() {
      java.lang.Object ref = medication_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        medication_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string medication = 5;</code>
     */
    public com.google.protobuf.ByteString
        getMedicationBytes() {
      java.lang.Object ref = medication_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        medication_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string medication = 5;</code>
     */
    public Builder setMedication(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      medication_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string medication = 5;</code>
     */
    public Builder clearMedication() {
      
      medication_ = getDefaultInstance().getMedication();
      onChanged();
      return this;
    }
    /**
     * <code>string medication = 5;</code>
     */
    public Builder setMedicationBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      medication_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object operation_ = "";
    /**
     * <code>string operation = 6;</code>
     */
    public java.lang.String getOperation() {
      java.lang.Object ref = operation_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        operation_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string operation = 6;</code>
     */
    public com.google.protobuf.ByteString
        getOperationBytes() {
      java.lang.Object ref = operation_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        operation_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string operation = 6;</code>
     */
    public Builder setOperation(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      operation_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string operation = 6;</code>
     */
    public Builder clearOperation() {
      
      operation_ = getDefaultInstance().getOperation();
      onChanged();
      return this;
    }
    /**
     * <code>string operation = 6;</code>
     */
    public Builder setOperationBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      operation_ = value;
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


    // @@protoc_insertion_point(builder_scope:EHRManagement.Patient)
  }

  // @@protoc_insertion_point(class_scope:EHRManagement.Patient)
  private static final com.example.eHealthRecords.grpc.Patient DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.example.eHealthRecords.grpc.Patient();
  }

  public static com.example.eHealthRecords.grpc.Patient getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Patient>
      PARSER = new com.google.protobuf.AbstractParser<Patient>() {
    @java.lang.Override
    public Patient parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new Patient(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<Patient> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<Patient> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.example.eHealthRecords.grpc.Patient getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

