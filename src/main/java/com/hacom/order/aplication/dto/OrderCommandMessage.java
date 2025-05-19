package com.hacom.order.aplication.dto;

import com.hacom.order.api.grpc.generate.proto.OrderResponse;

import io.grpc.stub.StreamObserver;

public class OrderCommandMessage {
    
    private final OrderCommand command;
    private final StreamObserver<OrderResponse> replyTo;

    public OrderCommandMessage(OrderCommand command, StreamObserver<OrderResponse> replyTo) {
        this.command = command;
        this.replyTo = replyTo;
    }

    public OrderCommand getCommand() {
        return command;
    }

    public StreamObserver<OrderResponse> getReplyTo() {
        return replyTo;
    }
}
