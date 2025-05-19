package com.hacom.client;

import com.hacom.order.api.grpc.generate.proto.OrderItem;
import com.hacom.order.api.grpc.generate.proto.OrderRequest;
import com.hacom.order.api.grpc.generate.proto.OrderResponse;
import com.hacom.order.api.grpc.generate.service.OrderServiceGrpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class ClientGPRC {
	
	public static void main(String[] args) {
		
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
												        .usePlaintext()
												        .build();
		 
		OrderServiceGrpc.OrderServiceBlockingStub stub = OrderServiceGrpc.newBlockingStub(channel);

		OrderItem item1 = OrderItem.newBuilder()
					                .setProductId("prod1")
					                .setQuantity(2)
					                .setPrice(10.50)
					                .build();

		OrderItem item2 = OrderItem.newBuilder()
					                .setProductId("prod2")
					                .setQuantity(1)
					                .setPrice(25.99)
					                .build();
		
		OrderRequest request = OrderRequest.newBuilder()
		                       			   .setOrderId("123")
					                       .setClientId("c456")
					                       .setClientPhone("999888777")
					                       .addItems(item1)
					                       .addItems(item2)
					                       .build();

		OrderResponse response = stub.createOrder(request);

		System.out.println("Respuesta del servicio: " + response);

		channel.shutdown();

	}

}
