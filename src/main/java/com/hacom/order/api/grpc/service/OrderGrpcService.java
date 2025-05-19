package com.hacom.order.api.grpc.service;

//import com.hacom.order.api.grpc.OrderActor;
//import com.hacom.order.aplication.actor.OrderActor;
import com.hacom.order.aplication.dto.OrderCommandMessage;
import com.hacom.order.api.grpc.generate.proto.OrderItem;
import com.hacom.order.api.grpc.generate.proto.OrderRequest;
import com.hacom.order.api.grpc.generate.proto.OrderResponse;
import com.hacom.order.api.grpc.generate.service.OrderServiceGrpc;
import com.hacom.order.aplication.dto.OrderCommand;

import io.grpc.stub.StreamObserver;

import net.devh.boot.grpc.server.service.GrpcService;
//import akka.actor.AbstractActor;
import akka.actor.ActorRef;
//import akka.actor.ActorSystem;
//import akka.actor.Props;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@GrpcService
public class OrderGrpcService extends OrderServiceGrpc.OrderServiceImplBase {

    private final ActorRef orderProcessor;

    /*public OrderGrpcService(ActorSystem system) {
    	this.orderProcessor = system.actorOf(Props.create(OrderActor.class), "orderProcessor");
    }*/
    
    public OrderGrpcService(ActorRef orderProcessor) {
        this.orderProcessor = orderProcessor;
    }
    
    @Override
    public void createOrder(OrderRequest request, StreamObserver<OrderResponse> responseObserver) {
    	
    	List<String> items = getItemsOrder(request);
    	
        // protp to dto domain
    	OrderCommand orderCommand = new OrderCommand(
							    			request.getOrderId(),
							    	        request.getClientId(),
								            request.getClientPhone(),
								            "SENDING",
								            items,
								            OffsetDateTime.now(),
								            null
    								);
    	
        // Enviar el mensaje interno al actor
        orderProcessor.tell(new OrderCommandMessage(orderCommand, responseObserver), ActorRef.noSender());
    }
    
    //mover a otra clase
    private List<String> getItemsOrder(OrderRequest request) {
    	List<String> items = request.getItemsList().stream()
					    		    .map(OrderItem::getProductId) // o el campo que quieras usar
					    		    .collect(Collectors.toList());
    	return items;
    }
    
}
