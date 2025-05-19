package com.hacom.order.aplication.actor;

import com.hacom.order.api.grpc.generate.proto.OrderResponse;
import com.hacom.order.aplication.dto.OrderCommand;
import com.hacom.order.aplication.dto.OrderCommandMessage;
import com.hacom.order.aplication.port.in.OrderPortIn;
import com.hacom.order.domain.model.Order;

import akka.actor.AbstractActor;


public class OrderActor extends AbstractActor {
	
	private final OrderPortIn orderPortIn;
	
	public OrderActor(OrderPortIn orderPortIn){
		this.orderPortIn = orderPortIn;
	}
	
	/*public OrderActor(){
		//this.orderPortIn = new OrderPortIn();
	}*/
	
	@Override
    public Receive createReceive() {
        return receiveBuilder()
            .match(OrderCommandMessage.class, msg -> {
                OrderCommand cmd = msg.getCommand();
                
                Order order = new Order( //Resetar los datos al domain
                    cmd.getOrderId(),
                    cmd.getCustomerId(),
                    cmd.getCustomerPhoneNumber(),
                    "PENDING",
                    cmd.getItems(),
                    cmd.getTs()
                );
                
                orderPortIn.generateOrder(order); //Procesar hacia la interface in
                 
                msg.getReplyTo().onNext( //Respuestar hacia el servicio gRPC (PENDING: responde con la respuesta del método de la interface)
                    OrderResponse.newBuilder()
                        .setOrderId(cmd.getOrderId())
                        .setStatus("SUCCESS")
                        .build()
                );
                
                msg.getReplyTo().onCompleted();
            })
            .build();
    }
}
