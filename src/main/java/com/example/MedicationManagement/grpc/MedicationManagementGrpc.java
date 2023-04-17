package com.example.MedicationManagement.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: PatientMedicationManagement.proto")
public final class MedicationManagementGrpc {

  private MedicationManagementGrpc() {}

  public static final String SERVICE_NAME = "PatientMedicationManagement.MedicationManagement";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.example.MedicationManagement.grpc.AddMedicationRequest,
      com.example.MedicationManagement.grpc.AddMedicationResponse> getAddMedicationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AddMedication",
      requestType = com.example.MedicationManagement.grpc.AddMedicationRequest.class,
      responseType = com.example.MedicationManagement.grpc.AddMedicationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.MedicationManagement.grpc.AddMedicationRequest,
      com.example.MedicationManagement.grpc.AddMedicationResponse> getAddMedicationMethod() {
    io.grpc.MethodDescriptor<com.example.MedicationManagement.grpc.AddMedicationRequest, com.example.MedicationManagement.grpc.AddMedicationResponse> getAddMedicationMethod;
    if ((getAddMedicationMethod = MedicationManagementGrpc.getAddMedicationMethod) == null) {
      synchronized (MedicationManagementGrpc.class) {
        if ((getAddMedicationMethod = MedicationManagementGrpc.getAddMedicationMethod) == null) {
          MedicationManagementGrpc.getAddMedicationMethod = getAddMedicationMethod = 
              io.grpc.MethodDescriptor.<com.example.MedicationManagement.grpc.AddMedicationRequest, com.example.MedicationManagement.grpc.AddMedicationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "PatientMedicationManagement.MedicationManagement", "AddMedication"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.MedicationManagement.grpc.AddMedicationRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.MedicationManagement.grpc.AddMedicationResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new MedicationManagementMethodDescriptorSupplier("AddMedication"))
                  .build();
          }
        }
     }
     return getAddMedicationMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.MedicationManagement.grpc.AdjustDosageRequest,
      com.example.MedicationManagement.grpc.AdjustDosageResponse> getAdjustDosageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AdjustDosage",
      requestType = com.example.MedicationManagement.grpc.AdjustDosageRequest.class,
      responseType = com.example.MedicationManagement.grpc.AdjustDosageResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<com.example.MedicationManagement.grpc.AdjustDosageRequest,
      com.example.MedicationManagement.grpc.AdjustDosageResponse> getAdjustDosageMethod() {
    io.grpc.MethodDescriptor<com.example.MedicationManagement.grpc.AdjustDosageRequest, com.example.MedicationManagement.grpc.AdjustDosageResponse> getAdjustDosageMethod;
    if ((getAdjustDosageMethod = MedicationManagementGrpc.getAdjustDosageMethod) == null) {
      synchronized (MedicationManagementGrpc.class) {
        if ((getAdjustDosageMethod = MedicationManagementGrpc.getAdjustDosageMethod) == null) {
          MedicationManagementGrpc.getAdjustDosageMethod = getAdjustDosageMethod = 
              io.grpc.MethodDescriptor.<com.example.MedicationManagement.grpc.AdjustDosageRequest, com.example.MedicationManagement.grpc.AdjustDosageResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "PatientMedicationManagement.MedicationManagement", "AdjustDosage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.MedicationManagement.grpc.AdjustDosageRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.MedicationManagement.grpc.AdjustDosageResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new MedicationManagementMethodDescriptorSupplier("AdjustDosage"))
                  .build();
          }
        }
     }
     return getAdjustDosageMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.MedicationManagement.grpc.ConfirmMedicationRequest,
      com.example.MedicationManagement.grpc.ConfirmMedicationResponse> getConfirmMedicationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ConfirmMedication",
      requestType = com.example.MedicationManagement.grpc.ConfirmMedicationRequest.class,
      responseType = com.example.MedicationManagement.grpc.ConfirmMedicationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<com.example.MedicationManagement.grpc.ConfirmMedicationRequest,
      com.example.MedicationManagement.grpc.ConfirmMedicationResponse> getConfirmMedicationMethod() {
    io.grpc.MethodDescriptor<com.example.MedicationManagement.grpc.ConfirmMedicationRequest, com.example.MedicationManagement.grpc.ConfirmMedicationResponse> getConfirmMedicationMethod;
    if ((getConfirmMedicationMethod = MedicationManagementGrpc.getConfirmMedicationMethod) == null) {
      synchronized (MedicationManagementGrpc.class) {
        if ((getConfirmMedicationMethod = MedicationManagementGrpc.getConfirmMedicationMethod) == null) {
          MedicationManagementGrpc.getConfirmMedicationMethod = getConfirmMedicationMethod = 
              io.grpc.MethodDescriptor.<com.example.MedicationManagement.grpc.ConfirmMedicationRequest, com.example.MedicationManagement.grpc.ConfirmMedicationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "PatientMedicationManagement.MedicationManagement", "ConfirmMedication"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.MedicationManagement.grpc.ConfirmMedicationRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.MedicationManagement.grpc.ConfirmMedicationResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new MedicationManagementMethodDescriptorSupplier("ConfirmMedication"))
                  .build();
          }
        }
     }
     return getConfirmMedicationMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MedicationManagementStub newStub(io.grpc.Channel channel) {
    return new MedicationManagementStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MedicationManagementBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new MedicationManagementBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MedicationManagementFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new MedicationManagementFutureStub(channel);
  }

  /**
   */
  public static abstract class MedicationManagementImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Unary RPC Method 1 - Add medication
     * </pre>
     */
    public void addMedication(com.example.MedicationManagement.grpc.AddMedicationRequest request,
        io.grpc.stub.StreamObserver<com.example.MedicationManagement.grpc.AddMedicationResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAddMedicationMethod(), responseObserver);
    }

    /**
     * <pre>
     * Bi-directional Streaming RPC Method 2 - Adjust Dosage
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.example.MedicationManagement.grpc.AdjustDosageRequest> adjustDosage(
        io.grpc.stub.StreamObserver<com.example.MedicationManagement.grpc.AdjustDosageResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getAdjustDosageMethod(), responseObserver);
    }

    /**
     * <pre>
     * Bi-directional Streaming RPC Method 3 - Confirm Medication
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.example.MedicationManagement.grpc.ConfirmMedicationRequest> confirmMedication(
        io.grpc.stub.StreamObserver<com.example.MedicationManagement.grpc.ConfirmMedicationResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getConfirmMedicationMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAddMedicationMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.example.MedicationManagement.grpc.AddMedicationRequest,
                com.example.MedicationManagement.grpc.AddMedicationResponse>(
                  this, METHODID_ADD_MEDICATION)))
          .addMethod(
            getAdjustDosageMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                com.example.MedicationManagement.grpc.AdjustDosageRequest,
                com.example.MedicationManagement.grpc.AdjustDosageResponse>(
                  this, METHODID_ADJUST_DOSAGE)))
          .addMethod(
            getConfirmMedicationMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                com.example.MedicationManagement.grpc.ConfirmMedicationRequest,
                com.example.MedicationManagement.grpc.ConfirmMedicationResponse>(
                  this, METHODID_CONFIRM_MEDICATION)))
          .build();
    }
  }

  /**
   */
  public static final class MedicationManagementStub extends io.grpc.stub.AbstractStub<MedicationManagementStub> {
    private MedicationManagementStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MedicationManagementStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MedicationManagementStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MedicationManagementStub(channel, callOptions);
    }

    /**
     * <pre>
     * Unary RPC Method 1 - Add medication
     * </pre>
     */
    public void addMedication(com.example.MedicationManagement.grpc.AddMedicationRequest request,
        io.grpc.stub.StreamObserver<com.example.MedicationManagement.grpc.AddMedicationResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAddMedicationMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Bi-directional Streaming RPC Method 2 - Adjust Dosage
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.example.MedicationManagement.grpc.AdjustDosageRequest> adjustDosage(
        io.grpc.stub.StreamObserver<com.example.MedicationManagement.grpc.AdjustDosageResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getAdjustDosageMethod(), getCallOptions()), responseObserver);
    }

    /**
     * <pre>
     * Bi-directional Streaming RPC Method 3 - Confirm Medication
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.example.MedicationManagement.grpc.ConfirmMedicationRequest> confirmMedication(
        io.grpc.stub.StreamObserver<com.example.MedicationManagement.grpc.ConfirmMedicationResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getConfirmMedicationMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class MedicationManagementBlockingStub extends io.grpc.stub.AbstractStub<MedicationManagementBlockingStub> {
    private MedicationManagementBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MedicationManagementBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MedicationManagementBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MedicationManagementBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Unary RPC Method 1 - Add medication
     * </pre>
     */
    public com.example.MedicationManagement.grpc.AddMedicationResponse addMedication(com.example.MedicationManagement.grpc.AddMedicationRequest request) {
      return blockingUnaryCall(
          getChannel(), getAddMedicationMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class MedicationManagementFutureStub extends io.grpc.stub.AbstractStub<MedicationManagementFutureStub> {
    private MedicationManagementFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MedicationManagementFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MedicationManagementFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MedicationManagementFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Unary RPC Method 1 - Add medication
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.MedicationManagement.grpc.AddMedicationResponse> addMedication(
        com.example.MedicationManagement.grpc.AddMedicationRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAddMedicationMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ADD_MEDICATION = 0;
  private static final int METHODID_ADJUST_DOSAGE = 1;
  private static final int METHODID_CONFIRM_MEDICATION = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final MedicationManagementImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(MedicationManagementImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ADD_MEDICATION:
          serviceImpl.addMedication((com.example.MedicationManagement.grpc.AddMedicationRequest) request,
              (io.grpc.stub.StreamObserver<com.example.MedicationManagement.grpc.AddMedicationResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ADJUST_DOSAGE:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.adjustDosage(
              (io.grpc.stub.StreamObserver<com.example.MedicationManagement.grpc.AdjustDosageResponse>) responseObserver);
        case METHODID_CONFIRM_MEDICATION:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.confirmMedication(
              (io.grpc.stub.StreamObserver<com.example.MedicationManagement.grpc.ConfirmMedicationResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class MedicationManagementBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MedicationManagementBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.example.MedicationManagement.grpc.PatientMedicationImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("MedicationManagement");
    }
  }

  private static final class MedicationManagementFileDescriptorSupplier
      extends MedicationManagementBaseDescriptorSupplier {
    MedicationManagementFileDescriptorSupplier() {}
  }

  private static final class MedicationManagementMethodDescriptorSupplier
      extends MedicationManagementBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    MedicationManagementMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (MedicationManagementGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MedicationManagementFileDescriptorSupplier())
              .addMethod(getAddMedicationMethod())
              .addMethod(getAdjustDosageMethod())
              .addMethod(getConfirmMedicationMethod())
              .build();
        }
      }
    }
    return result;
  }
}
