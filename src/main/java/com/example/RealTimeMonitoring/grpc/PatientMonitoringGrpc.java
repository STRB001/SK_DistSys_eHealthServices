package com.example.RealTimeMonitoring.grpc;

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
    comments = "Source: RealTimePatient.proto")
public final class PatientMonitoringGrpc {

  private PatientMonitoringGrpc() {}

  public static final String SERVICE_NAME = "RealTimePatientMonitoring.PatientMonitoring";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.example.RealTimeMonitoring.grpc.AddPatientRequest,
      com.example.RealTimeMonitoring.grpc.AddPatientResponse> getAddPatientMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AddPatient",
      requestType = com.example.RealTimeMonitoring.grpc.AddPatientRequest.class,
      responseType = com.example.RealTimeMonitoring.grpc.AddPatientResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.RealTimeMonitoring.grpc.AddPatientRequest,
      com.example.RealTimeMonitoring.grpc.AddPatientResponse> getAddPatientMethod() {
    io.grpc.MethodDescriptor<com.example.RealTimeMonitoring.grpc.AddPatientRequest, com.example.RealTimeMonitoring.grpc.AddPatientResponse> getAddPatientMethod;
    if ((getAddPatientMethod = PatientMonitoringGrpc.getAddPatientMethod) == null) {
      synchronized (PatientMonitoringGrpc.class) {
        if ((getAddPatientMethod = PatientMonitoringGrpc.getAddPatientMethod) == null) {
          PatientMonitoringGrpc.getAddPatientMethod = getAddPatientMethod = 
              io.grpc.MethodDescriptor.<com.example.RealTimeMonitoring.grpc.AddPatientRequest, com.example.RealTimeMonitoring.grpc.AddPatientResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "RealTimePatientMonitoring.PatientMonitoring", "AddPatient"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.RealTimeMonitoring.grpc.AddPatientRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.RealTimeMonitoring.grpc.AddPatientResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new PatientMonitoringMethodDescriptorSupplier("AddPatient"))
                  .build();
          }
        }
     }
     return getAddPatientMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.RealTimeMonitoring.grpc.StreamPatientInfoRequest,
      com.example.RealTimeMonitoring.grpc.StreamPatientInfoResponse> getStreamPatientInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "StreamPatientInfo",
      requestType = com.example.RealTimeMonitoring.grpc.StreamPatientInfoRequest.class,
      responseType = com.example.RealTimeMonitoring.grpc.StreamPatientInfoResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.example.RealTimeMonitoring.grpc.StreamPatientInfoRequest,
      com.example.RealTimeMonitoring.grpc.StreamPatientInfoResponse> getStreamPatientInfoMethod() {
    io.grpc.MethodDescriptor<com.example.RealTimeMonitoring.grpc.StreamPatientInfoRequest, com.example.RealTimeMonitoring.grpc.StreamPatientInfoResponse> getStreamPatientInfoMethod;
    if ((getStreamPatientInfoMethod = PatientMonitoringGrpc.getStreamPatientInfoMethod) == null) {
      synchronized (PatientMonitoringGrpc.class) {
        if ((getStreamPatientInfoMethod = PatientMonitoringGrpc.getStreamPatientInfoMethod) == null) {
          PatientMonitoringGrpc.getStreamPatientInfoMethod = getStreamPatientInfoMethod = 
              io.grpc.MethodDescriptor.<com.example.RealTimeMonitoring.grpc.StreamPatientInfoRequest, com.example.RealTimeMonitoring.grpc.StreamPatientInfoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "RealTimePatientMonitoring.PatientMonitoring", "StreamPatientInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.RealTimeMonitoring.grpc.StreamPatientInfoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.RealTimeMonitoring.grpc.StreamPatientInfoResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new PatientMonitoringMethodDescriptorSupplier("StreamPatientInfo"))
                  .build();
          }
        }
     }
     return getStreamPatientInfoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.RealTimeMonitoring.grpc.StreamMedicalAlertsRequest,
      com.example.RealTimeMonitoring.grpc.StreamMedicalAlertsResponse> getStreamMedicalAlertsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "StreamMedicalAlerts",
      requestType = com.example.RealTimeMonitoring.grpc.StreamMedicalAlertsRequest.class,
      responseType = com.example.RealTimeMonitoring.grpc.StreamMedicalAlertsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.example.RealTimeMonitoring.grpc.StreamMedicalAlertsRequest,
      com.example.RealTimeMonitoring.grpc.StreamMedicalAlertsResponse> getStreamMedicalAlertsMethod() {
    io.grpc.MethodDescriptor<com.example.RealTimeMonitoring.grpc.StreamMedicalAlertsRequest, com.example.RealTimeMonitoring.grpc.StreamMedicalAlertsResponse> getStreamMedicalAlertsMethod;
    if ((getStreamMedicalAlertsMethod = PatientMonitoringGrpc.getStreamMedicalAlertsMethod) == null) {
      synchronized (PatientMonitoringGrpc.class) {
        if ((getStreamMedicalAlertsMethod = PatientMonitoringGrpc.getStreamMedicalAlertsMethod) == null) {
          PatientMonitoringGrpc.getStreamMedicalAlertsMethod = getStreamMedicalAlertsMethod = 
              io.grpc.MethodDescriptor.<com.example.RealTimeMonitoring.grpc.StreamMedicalAlertsRequest, com.example.RealTimeMonitoring.grpc.StreamMedicalAlertsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "RealTimePatientMonitoring.PatientMonitoring", "StreamMedicalAlerts"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.RealTimeMonitoring.grpc.StreamMedicalAlertsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.RealTimeMonitoring.grpc.StreamMedicalAlertsResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new PatientMonitoringMethodDescriptorSupplier("StreamMedicalAlerts"))
                  .build();
          }
        }
     }
     return getStreamMedicalAlertsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static PatientMonitoringStub newStub(io.grpc.Channel channel) {
    return new PatientMonitoringStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static PatientMonitoringBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new PatientMonitoringBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static PatientMonitoringFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new PatientMonitoringFutureStub(channel);
  }

  /**
   */
  public static abstract class PatientMonitoringImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Unary RPC Method 1 - Add Patient
     * </pre>
     */
    public void addPatient(com.example.RealTimeMonitoring.grpc.AddPatientRequest request,
        io.grpc.stub.StreamObserver<com.example.RealTimeMonitoring.grpc.AddPatientResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAddPatientMethod(), responseObserver);
    }

    /**
     * <pre>
     * Server Streaming RPC Method 2 - Real-time Patient Info
     * </pre>
     */
    public void streamPatientInfo(com.example.RealTimeMonitoring.grpc.StreamPatientInfoRequest request,
        io.grpc.stub.StreamObserver<com.example.RealTimeMonitoring.grpc.StreamPatientInfoResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getStreamPatientInfoMethod(), responseObserver);
    }

    /**
     * <pre>
     * Server Streaming RPC Method 3 - Medical Staff Alert
     * </pre>
     */
    public void streamMedicalAlerts(com.example.RealTimeMonitoring.grpc.StreamMedicalAlertsRequest request,
        io.grpc.stub.StreamObserver<com.example.RealTimeMonitoring.grpc.StreamMedicalAlertsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getStreamMedicalAlertsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAddPatientMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.example.RealTimeMonitoring.grpc.AddPatientRequest,
                com.example.RealTimeMonitoring.grpc.AddPatientResponse>(
                  this, METHODID_ADD_PATIENT)))
          .addMethod(
            getStreamPatientInfoMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.example.RealTimeMonitoring.grpc.StreamPatientInfoRequest,
                com.example.RealTimeMonitoring.grpc.StreamPatientInfoResponse>(
                  this, METHODID_STREAM_PATIENT_INFO)))
          .addMethod(
            getStreamMedicalAlertsMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.example.RealTimeMonitoring.grpc.StreamMedicalAlertsRequest,
                com.example.RealTimeMonitoring.grpc.StreamMedicalAlertsResponse>(
                  this, METHODID_STREAM_MEDICAL_ALERTS)))
          .build();
    }
  }

  /**
   */
  public static final class PatientMonitoringStub extends io.grpc.stub.AbstractStub<PatientMonitoringStub> {
    private PatientMonitoringStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PatientMonitoringStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PatientMonitoringStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PatientMonitoringStub(channel, callOptions);
    }

    /**
     * <pre>
     * Unary RPC Method 1 - Add Patient
     * </pre>
     */
    public void addPatient(com.example.RealTimeMonitoring.grpc.AddPatientRequest request,
        io.grpc.stub.StreamObserver<com.example.RealTimeMonitoring.grpc.AddPatientResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAddPatientMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Server Streaming RPC Method 2 - Real-time Patient Info
     * </pre>
     */
    public void streamPatientInfo(com.example.RealTimeMonitoring.grpc.StreamPatientInfoRequest request,
        io.grpc.stub.StreamObserver<com.example.RealTimeMonitoring.grpc.StreamPatientInfoResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getStreamPatientInfoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Server Streaming RPC Method 3 - Medical Staff Alert
     * </pre>
     */
    public void streamMedicalAlerts(com.example.RealTimeMonitoring.grpc.StreamMedicalAlertsRequest request,
        io.grpc.stub.StreamObserver<com.example.RealTimeMonitoring.grpc.StreamMedicalAlertsResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getStreamMedicalAlertsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class PatientMonitoringBlockingStub extends io.grpc.stub.AbstractStub<PatientMonitoringBlockingStub> {
    private PatientMonitoringBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PatientMonitoringBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PatientMonitoringBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PatientMonitoringBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Unary RPC Method 1 - Add Patient
     * </pre>
     */
    public com.example.RealTimeMonitoring.grpc.AddPatientResponse addPatient(com.example.RealTimeMonitoring.grpc.AddPatientRequest request) {
      return blockingUnaryCall(
          getChannel(), getAddPatientMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Server Streaming RPC Method 2 - Real-time Patient Info
     * </pre>
     */
    public java.util.Iterator<com.example.RealTimeMonitoring.grpc.StreamPatientInfoResponse> streamPatientInfo(
        com.example.RealTimeMonitoring.grpc.StreamPatientInfoRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getStreamPatientInfoMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Server Streaming RPC Method 3 - Medical Staff Alert
     * </pre>
     */
    public java.util.Iterator<com.example.RealTimeMonitoring.grpc.StreamMedicalAlertsResponse> streamMedicalAlerts(
        com.example.RealTimeMonitoring.grpc.StreamMedicalAlertsRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getStreamMedicalAlertsMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class PatientMonitoringFutureStub extends io.grpc.stub.AbstractStub<PatientMonitoringFutureStub> {
    private PatientMonitoringFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PatientMonitoringFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PatientMonitoringFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PatientMonitoringFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Unary RPC Method 1 - Add Patient
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.RealTimeMonitoring.grpc.AddPatientResponse> addPatient(
        com.example.RealTimeMonitoring.grpc.AddPatientRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAddPatientMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ADD_PATIENT = 0;
  private static final int METHODID_STREAM_PATIENT_INFO = 1;
  private static final int METHODID_STREAM_MEDICAL_ALERTS = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final PatientMonitoringImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(PatientMonitoringImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ADD_PATIENT:
          serviceImpl.addPatient((com.example.RealTimeMonitoring.grpc.AddPatientRequest) request,
              (io.grpc.stub.StreamObserver<com.example.RealTimeMonitoring.grpc.AddPatientResponse>) responseObserver);
          break;
        case METHODID_STREAM_PATIENT_INFO:
          serviceImpl.streamPatientInfo((com.example.RealTimeMonitoring.grpc.StreamPatientInfoRequest) request,
              (io.grpc.stub.StreamObserver<com.example.RealTimeMonitoring.grpc.StreamPatientInfoResponse>) responseObserver);
          break;
        case METHODID_STREAM_MEDICAL_ALERTS:
          serviceImpl.streamMedicalAlerts((com.example.RealTimeMonitoring.grpc.StreamMedicalAlertsRequest) request,
              (io.grpc.stub.StreamObserver<com.example.RealTimeMonitoring.grpc.StreamMedicalAlertsResponse>) responseObserver);
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
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class PatientMonitoringBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    PatientMonitoringBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.example.RealTimeMonitoring.grpc.RealTimePatientImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("PatientMonitoring");
    }
  }

  private static final class PatientMonitoringFileDescriptorSupplier
      extends PatientMonitoringBaseDescriptorSupplier {
    PatientMonitoringFileDescriptorSupplier() {}
  }

  private static final class PatientMonitoringMethodDescriptorSupplier
      extends PatientMonitoringBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    PatientMonitoringMethodDescriptorSupplier(String methodName) {
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
      synchronized (PatientMonitoringGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new PatientMonitoringFileDescriptorSupplier())
              .addMethod(getAddPatientMethod())
              .addMethod(getStreamPatientInfoMethod())
              .addMethod(getStreamMedicalAlertsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
