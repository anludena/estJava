package com.hacom.order.api.grpc;

import com.hacom.order.api.grpc.generate.proto.OrderRequest;
import com.hacom.order.api.grpc.generate.proto.OrderResponse;

import io.grpc.stub.StreamObserver;

public class GrpcOrderMessage {
    public final OrderRequest request;
    public final StreamObserver<OrderResponse> responseObserver;

    public GrpcOrderMessage(OrderRequest request, StreamObserver<OrderResponse> responseObserver) {
        this.request = request;
        this.responseObserver = responseObserver;
    }
}

