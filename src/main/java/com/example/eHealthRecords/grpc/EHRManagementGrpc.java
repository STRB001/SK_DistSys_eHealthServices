package com.example.eHealthRecords.grpc;

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
    comments = "Source: EHRManagement.proto")
public final class EHRManagementGrpc {

  private EHRManagementGrpc() {}

  public static final String SERVICE_NAME = "EHRManagement.EHRManagement";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.example.eHealthRecords.grpc.SearchPatientRecordRequest,
      com.example.eHealthRecords.grpc.SearchPatientRecordResponse> getSearchPatientRecordMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SearchPatientRecord",
      requestType = com.example.eHealthRecords.grpc.SearchPatientRecordRequest.class,
      responseType = com.example.eHealthRecords.grpc.SearchPatientRecordResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.eHealthRecords.grpc.SearchPatientRecordRequest,
      com.example.eHealthRecords.grpc.SearchPatientRecordResponse> getSearchPatientRecordMethod() {
    io.grpc.MethodDescriptor<com.example.eHealthRecords.grpc.SearchPatientRecordRequest, com.example.eHealthRecords.grpc.SearchPatientRecordResponse> getSearchPatientRecordMethod;
    if ((getSearchPatientRecordMethod = EHRManagementGrpc.getSearchPatientRecordMethod) == null) {
      synchronized (EHRManagementGrpc.class) {
        if ((getSearchPatientRecordMethod = EHRManagementGrpc.getSearchPatientRecordMethod) == null) {
          EHRManagementGrpc.getSearchPatientRecordMethod = getSearchPatientRecordMethod = 
              io.grpc.MethodDescriptor.<com.example.eHealthRecords.grpc.SearchPatientRecordRequest, com.example.eHealthRecords.grpc.SearchPatientRecordResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "EHRManagement.EHRManagement", "SearchPatientRecord"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.eHealthRecords.grpc.SearchPatientRecordRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.eHealthRecords.grpc.SearchPatientRecordResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new EHRManagementMethodDescriptorSupplier("SearchPatientRecord"))
                  .build();
          }
        }
     }
     return getSearchPatientRecordMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.eHealthRecords.grpc.UpdatePatientRecordRequest,
      com.example.eHealthRecords.grpc.UpdatePatientRecordResponse> getUpdatePatientRecordMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdatePatientRecord",
      requestType = com.example.eHealthRecords.grpc.UpdatePatientRecordRequest.class,
      responseType = com.example.eHealthRecords.grpc.UpdatePatientRecordResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.eHealthRecords.grpc.UpdatePatientRecordRequest,
      com.example.eHealthRecords.grpc.UpdatePatientRecordResponse> getUpdatePatientRecordMethod() {
    io.grpc.MethodDescriptor<com.example.eHealthRecords.grpc.UpdatePatientRecordRequest, com.example.eHealthRecords.grpc.UpdatePatientRecordResponse> getUpdatePatientRecordMethod;
    if ((getUpdatePatientRecordMethod = EHRManagementGrpc.getUpdatePatientRecordMethod) == null) {
      synchronized (EHRManagementGrpc.class) {
        if ((getUpdatePatientRecordMethod = EHRManagementGrpc.getUpdatePatientRecordMethod) == null) {
          EHRManagementGrpc.getUpdatePatientRecordMethod = getUpdatePatientRecordMethod = 
              io.grpc.MethodDescriptor.<com.example.eHealthRecords.grpc.UpdatePatientRecordRequest, com.example.eHealthRecords.grpc.UpdatePatientRecordResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "EHRManagement.EHRManagement", "UpdatePatientRecord"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.eHealthRecords.grpc.UpdatePatientRecordRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.eHealthRecords.grpc.UpdatePatientRecordResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new EHRManagementMethodDescriptorSupplier("UpdatePatientRecord"))
                  .build();
          }
        }
     }
     return getUpdatePatientRecordMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.eHealthRecords.grpc.SharePatientRecordRequest,
      com.example.eHealthRecords.grpc.SharePatientRecordResponse> getSharePatientRecordMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SharePatientRecord",
      requestType = com.example.eHealthRecords.grpc.SharePatientRecordRequest.class,
      responseType = com.example.eHealthRecords.grpc.SharePatientRecordResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<com.example.eHealthRecords.grpc.SharePatientRecordRequest,
      com.example.eHealthRecords.grpc.SharePatientRecordResponse> getSharePatientRecordMethod() {
    io.grpc.MethodDescriptor<com.example.eHealthRecords.grpc.SharePatientRecordRequest, com.example.eHealthRecords.grpc.SharePatientRecordResponse> getSharePatientRecordMethod;
    if ((getSharePatientRecordMethod = EHRManagementGrpc.getSharePatientRecordMethod) == null) {
      synchronized (EHRManagementGrpc.class) {
        if ((getSharePatientRecordMethod = EHRManagementGrpc.getSharePatientRecordMethod) == null) {
          EHRManagementGrpc.getSharePatientRecordMethod = getSharePatientRecordMethod = 
              io.grpc.MethodDescriptor.<com.example.eHealthRecords.grpc.SharePatientRecordRequest, com.example.eHealthRecords.grpc.SharePatientRecordResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "EHRManagement.EHRManagement", "SharePatientRecord"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.eHealthRecords.grpc.SharePatientRecordRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.eHealthRecords.grpc.SharePatientRecordResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new EHRManagementMethodDescriptorSupplier("SharePatientRecord"))
                  .build();
          }
        }
     }
     return getSharePatientRecordMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static EHRManagementStub newStub(io.grpc.Channel channel) {
    return new EHRManagementStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static EHRManagementBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new EHRManagementBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static EHRManagementFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new EHRManagementFutureStub(channel);
  }

  /**
   */
  public static abstract class EHRManagementImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Unary RPC Method 1 - Search Patient Record
     * </pre>
     */
    public void searchPatientRecord(com.example.eHealthRecords.grpc.SearchPatientRecordRequest request,
        io.grpc.stub.StreamObserver<com.example.eHealthRecords.grpc.SearchPatientRecordResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSearchPatientRecordMethod(), responseObserver);
    }

    /**
     * <pre>
     * Unary RPC Method 2 - Update Patient Record
     * </pre>
     */
    public void updatePatientRecord(com.example.eHealthRecords.grpc.UpdatePatientRecordRequest request,
        io.grpc.stub.StreamObserver<com.example.eHealthRecords.grpc.UpdatePatientRecordResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdatePatientRecordMethod(), responseObserver);
    }

    /**
     * <pre>
     * Client Streaming RPC Method 3 - Share Patient Record
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.example.eHealthRecords.grpc.SharePatientRecordRequest> sharePatientRecord(
        io.grpc.stub.StreamObserver<com.example.eHealthRecords.grpc.SharePatientRecordResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getSharePatientRecordMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSearchPatientRecordMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.example.eHealthRecords.grpc.SearchPatientRecordRequest,
                com.example.eHealthRecords.grpc.SearchPatientRecordResponse>(
                  this, METHODID_SEARCH_PATIENT_RECORD)))
          .addMethod(
            getUpdatePatientRecordMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.example.eHealthRecords.grpc.UpdatePatientRecordRequest,
                com.example.eHealthRecords.grpc.UpdatePatientRecordResponse>(
                  this, METHODID_UPDATE_PATIENT_RECORD)))
          .addMethod(
            getSharePatientRecordMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                com.example.eHealthRecords.grpc.SharePatientRecordRequest,
                com.example.eHealthRecords.grpc.SharePatientRecordResponse>(
                  this, METHODID_SHARE_PATIENT_RECORD)))
          .build();
    }
  }

  /**
   */
  public static final class EHRManagementStub extends io.grpc.stub.AbstractStub<EHRManagementStub> {
    private EHRManagementStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EHRManagementStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EHRManagementStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EHRManagementStub(channel, callOptions);
    }

    /**
     * <pre>
     * Unary RPC Method 1 - Search Patient Record
     * </pre>
     */
    public void searchPatientRecord(com.example.eHealthRecords.grpc.SearchPatientRecordRequest request,
        io.grpc.stub.StreamObserver<com.example.eHealthRecords.grpc.SearchPatientRecordResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSearchPatientRecordMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Unary RPC Method 2 - Update Patient Record
     * </pre>
     */
    public void updatePatientRecord(com.example.eHealthRecords.grpc.UpdatePatientRecordRequest request,
        io.grpc.stub.StreamObserver<com.example.eHealthRecords.grpc.UpdatePatientRecordResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdatePatientRecordMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Client Streaming RPC Method 3 - Share Patient Record
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.example.eHealthRecords.grpc.SharePatientRecordRequest> sharePatientRecord(
        io.grpc.stub.StreamObserver<com.example.eHealthRecords.grpc.SharePatientRecordResponse> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getSharePatientRecordMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class EHRManagementBlockingStub extends io.grpc.stub.AbstractStub<EHRManagementBlockingStub> {
    private EHRManagementBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EHRManagementBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EHRManagementBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EHRManagementBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Unary RPC Method 1 - Search Patient Record
     * </pre>
     */
    public com.example.eHealthRecords.grpc.SearchPatientRecordResponse searchPatientRecord(com.example.eHealthRecords.grpc.SearchPatientRecordRequest request) {
      return blockingUnaryCall(
          getChannel(), getSearchPatientRecordMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Unary RPC Method 2 - Update Patient Record
     * </pre>
     */
    public com.example.eHealthRecords.grpc.UpdatePatientRecordResponse updatePatientRecord(com.example.eHealthRecords.grpc.UpdatePatientRecordRequest request) {
      return blockingUnaryCall(
          getChannel(), getUpdatePatientRecordMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class EHRManagementFutureStub extends io.grpc.stub.AbstractStub<EHRManagementFutureStub> {
    private EHRManagementFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EHRManagementFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EHRManagementFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EHRManagementFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Unary RPC Method 1 - Search Patient Record
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.eHealthRecords.grpc.SearchPatientRecordResponse> searchPatientRecord(
        com.example.eHealthRecords.grpc.SearchPatientRecordRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSearchPatientRecordMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Unary RPC Method 2 - Update Patient Record
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.eHealthRecords.grpc.UpdatePatientRecordResponse> updatePatientRecord(
        com.example.eHealthRecords.grpc.UpdatePatientRecordRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdatePatientRecordMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SEARCH_PATIENT_RECORD = 0;
  private static final int METHODID_UPDATE_PATIENT_RECORD = 1;
  private static final int METHODID_SHARE_PATIENT_RECORD = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final EHRManagementImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(EHRManagementImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SEARCH_PATIENT_RECORD:
          serviceImpl.searchPatientRecord((com.example.eHealthRecords.grpc.SearchPatientRecordRequest) request,
              (io.grpc.stub.StreamObserver<com.example.eHealthRecords.grpc.SearchPatientRecordResponse>) responseObserver);
          break;
        case METHODID_UPDATE_PATIENT_RECORD:
          serviceImpl.updatePatientRecord((com.example.eHealthRecords.grpc.UpdatePatientRecordRequest) request,
              (io.grpc.stub.StreamObserver<com.example.eHealthRecords.grpc.UpdatePatientRecordResponse>) responseObserver);
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
        case METHODID_SHARE_PATIENT_RECORD:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.sharePatientRecord(
              (io.grpc.stub.StreamObserver<com.example.eHealthRecords.grpc.SharePatientRecordResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class EHRManagementBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    EHRManagementBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.example.eHealthRecords.grpc.EHRManagementImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("EHRManagement");
    }
  }

  private static final class EHRManagementFileDescriptorSupplier
      extends EHRManagementBaseDescriptorSupplier {
    EHRManagementFileDescriptorSupplier() {}
  }

  private static final class EHRManagementMethodDescriptorSupplier
      extends EHRManagementBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    EHRManagementMethodDescriptorSupplier(String methodName) {
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
      synchronized (EHRManagementGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new EHRManagementFileDescriptorSupplier())
              .addMethod(getSearchPatientRecordMethod())
              .addMethod(getUpdatePatientRecordMethod())
              .addMethod(getSharePatientRecordMethod())
              .build();
        }
      }
    }
    return result;
  }
}
