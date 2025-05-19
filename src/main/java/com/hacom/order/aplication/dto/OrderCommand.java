package com.hacom.order.aplication.dto;

import java.time.OffsetDateTime;
import java.util.List;

import akka.actor.ActorRef;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderCommand {
	 
	private String orderId;
    private String customerId;
    private String customerPhoneNumber;
    private String status;
    private List<String> items;
    private OffsetDateTime ts; // puedes generarlo automáticamente en el entity, en este caso recibiremos el dato
    
    private ActorRef replyTo; // para responder desde el actor
    
    public OrderCommand(String orderId, String customerId, String customerPhoneNumber, 
    					String status, List<String> items, OffsetDateTime ts, ActorRef replyTo) {
    	
        this.orderId = orderId;
        this.customerId = customerId;
        this.customerPhoneNumber = customerPhoneNumber;
        this.items = items;
        this.status = status;
        this.ts = ts;
        this.replyTo = replyTo;
        
    }
    
    public OrderCommand() {} // serialización/deserialización para frameworks
    
}