// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: EHRManagement.proto

package com.example.eHealthRecords.grpc;

/**
 * Protobuf type {@code EHRManagement.SharePatientRecordRequest}
 */
public  final class SharePatientRecordRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:EHRManagement.SharePatientRecordRequest)
    SharePatientRecordRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use SharePatientRecordRequest.newBuilder() to construct.
  private SharePatientRecordRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private SharePatientRecordRequest() {
    patientId_ = "";
    recordPart_ = "";
    recordContent_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private SharePatientRecordRequest(
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
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            recordPart_ = s;
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();

            recordContent_ = s;
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
    return com.example.eHealthRecords.grpc.EHRManagementImpl.internal_static_EHRManagement_SharePatientRecordRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.example.eHealthRecords.grpc.EHRManagementImpl.internal_static_EHRManagement_SharePatientRecordRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.example.eHealthRecords.grpc.SharePatientRecordRequest.class, com.example.eHealthRecords.grpc.SharePatientRecordRequest.Builder.class);
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

  public static final int RECORD_PART_FIELD_NUMBER = 2;
  private volatile java.lang.Object recordPart_;
  /**
   * <code>string record_part = 2;</code>
   */
  public java.lang.String getRecordPart() {
    java.lang.Object ref = recordPart_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      recordPart_ = s;
      return s;
    }
  }
  /**
   * <code>string record_part = 2;</code>
   */
  public com.google.protobuf.ByteString
      getRecordPartBytes() {
    java.lang.Object ref = recordPart_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      recordPart_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int RECORD_CONTENT_FIELD_NUMBER = 3;
  private volatile java.lang.Object recordContent_;
  /**
   * <code>string record_content = 3;</code>
   */
  public java.lang.String getRecordContent() {
    java.lang.Object ref = recordContent_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      recordContent_ = s;
      return s;
    }
  }
  /**
   * <code>string record_content = 3;</code>
   */
  public com.google.protobuf.ByteString
      getRecordContentBytes() {
    java.lang.Object ref = recordContent_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      recordContent_ = b;
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
    if (!getPatientIdBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, patientId_);
    }
    if (!getRecordPartBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, recordPart_);
    }
    if (!getRecordContentBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, recordContent_);
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
    if (!getRecordPartBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, recordPart_);
    }
    if (!getRecordContentBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, recordContent_);
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
    if (!(obj instanceof com.example.eHealthRecords.grpc.SharePatientRecordRequest)) {
      return super.equals(obj);
    }
    com.example.eHealthRecords.grpc.SharePatientRecordRequest other = (com.example.eHealthRecords.grpc.SharePatientRecordRequest) obj;

    boolean result = true;
    result = result && getPatientId()
        .equals(other.getPatientId());
    result = result && getRecordPart()
        .equals(other.getRecordPart());
    result = result && getRecordContent()
        .equals(other.getRecordContent());
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
    hash = (37 * hash) + RECORD_PART_FIELD_NUMBER;
    hash = (53 * hash) + getRecordPart().hashCode();
    hash = (37 * hash) + RECORD_CONTENT_FIELD_NUMBER;
    hash = (53 * hash) + getRecordContent().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.example.eHealthRecords.grpc.SharePatientRecordRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.example.eHealthRecords.grpc.SharePatientRecordRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.example.eHealthRecords.grpc.SharePatientRecordRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.example.eHealthRecords.grpc.SharePatientRecordRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.example.eHealthRecords.grpc.SharePatientRecordRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.example.eHealthRecords.grpc.SharePatientRecordRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.example.eHealthRecords.grpc.SharePatientRecordRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.example.eHealthRecords.grpc.SharePatientRecordRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.example.eHealthRecords.grpc.SharePatientRecordRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.example.eHealthRecords.grpc.SharePatientRecordRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.example.eHealthRecords.grpc.SharePatientRecordRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.example.eHealthRecords.grpc.SharePatientRecordRequest parseFrom(
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
  public static Builder newBuilder(com.example.eHealthRecords.grpc.SharePatientRecordRequest prototype) {
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
   * Protobuf type {@code EHRManagement.SharePatientRecordRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:EHRManagement.SharePatientRecordRequest)
      com.example.eHealthRecords.grpc.SharePatientRecordRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.example.eHealthRecords.grpc.EHRManagementImpl.internal_static_EHRManagement_SharePatientRecordRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.example.eHealthRecords.grpc.EHRManagementImpl.internal_static_EHRManagement_SharePatientRecordRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.example.eHealthRecords.grpc.SharePatientRecordRequest.class, com.example.eHealthRecords.grpc.SharePatientRecordRequest.Builder.class);
    }

    // Construct using com.example.eHealthRecords.grpc.SharePatientRecordRequest.newBuilder()
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

      recordPart_ = "";

      recordContent_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.example.eHealthRecords.grpc.EHRManagementImpl.internal_static_EHRManagement_SharePatientRecordRequest_descriptor;
    }

    @java.lang.Override
    public com.example.eHealthRecords.grpc.SharePatientRecordRequest getDefaultInstanceForType() {
      return com.example.eHealthRecords.grpc.SharePatientRecordRequest.getDefaultInstance();
    }

    @java.lang.Override
    public com.example.eHealthRecords.grpc.SharePatientRecordRequest build() {
      com.example.eHealthRecords.grpc.SharePatientRecordRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.example.eHealthRecords.grpc.SharePatientRecordRequest buildPartial() {
      com.example.eHealthRecords.grpc.SharePatientRecordRequest result = new com.example.eHealthRecords.grpc.SharePatientRecordRequest(this);
      result.patientId_ = patientId_;
      result.recordPart_ = recordPart_;
      result.recordContent_ = recordContent_;
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
      if (other instanceof com.example.eHealthRecords.grpc.SharePatientRecordRequest) {
        return mergeFrom((com.example.eHealthRecords.grpc.SharePatientRecordRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.example.eHealthRecords.grpc.SharePatientRecordRequest other) {
      if (other == com.example.eHealthRecords.grpc.SharePatientRecordRequest.getDefaultInstance()) return this;
      if (!other.getPatientId().isEmpty()) {
        patientId_ = other.patientId_;
        onChanged();
      }
      if (!other.getRecordPart().isEmpty()) {
        recordPart_ = other.recordPart_;
        onChanged();
      }
      if (!other.getRecordContent().isEmpty()) {
        recordContent_ = other.recordContent_;
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
      com.example.eHealthRecords.grpc.SharePatientRecordRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.example.eHealthRecords.grpc.SharePatientRecordRequest) e.getUnfinishedMessage();
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

    private java.lang.Object recordPart_ = "";
    /**
     * <code>string record_part = 2;</code>
     */
    public java.lang.String getRecordPart() {
      java.lang.Object ref = recordPart_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        recordPart_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string record_part = 2;</code>
     */
    public com.google.protobuf.ByteString
        getRecordPartBytes() {
      java.lang.Object ref = recordPart_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        recordPart_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string record_part = 2;</code>
     */
    public Builder setRecordPart(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      recordPart_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string record_part = 2;</code>
     */
    public Builder clearRecordPart() {
      
      recordPart_ = getDefaultInstance().getRecordPart();
      onChanged();
      return this;
    }
    /**
     * <code>string record_part = 2;</code>
     */
    public Builder setRecordPartBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      recordPart_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object recordContent_ = "";
    /**
     * <code>string record_content = 3;</code>
     */
    public java.lang.String getRecordContent() {
      java.lang.Object ref = recordContent_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        recordContent_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string record_content = 3;</code>
     */
    public com.google.protobuf.ByteString
        getRecordContentBytes() {
      java.lang.Object ref = recordContent_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        recordContent_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string record_content = 3;</code>
     */
    public Builder setRecordContent(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      recordContent_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string record_content = 3;</code>
     */
    public Builder clearRecordContent() {
      
      recordContent_ = getDefaultInstance().getRecordContent();
      onChanged();
      return this;
    }
    /**
     * <code>string record_content = 3;</code>
     */
    public Builder setRecordContentBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      recordContent_ = value;
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


    // @@protoc_insertion_point(builder_scope:EHRManagement.SharePatientRecordRequest)
  }

  // @@protoc_insertion_point(class_scope:EHRManagement.SharePatientRecordRequest)
  private static final com.example.eHealthRecords.grpc.SharePatientRecordRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.example.eHealthRecords.grpc.SharePatientRecordRequest();
  }

  public static com.example.eHealthRecords.grpc.SharePatientRecordRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<SharePatientRecordRequest>
      PARSER = new com.google.protobuf.AbstractParser<SharePatientRecordRequest>() {
    @java.lang.Override
    public SharePatientRecordRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new SharePatientRecordRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<SharePatientRecordRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<SharePatientRecordRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.example.eHealthRecords.grpc.SharePatientRecordRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

