package com.hacom.order.domain.model;

import java.time.OffsetDateTime;
import java.util.List;

import org.bson.types.ObjectId;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/*@Getter
@Setter*/
@Data
public class Order {
	
	private ObjectId _id;
	private String orderId;
	private String customerId;
	private String customerPhoneNumber;
	private String status;
	private List<String> items;
	private OffsetDateTime ts;
	
	public Order () {
		
	};
	
	public Order(String orderId, String customerId, String customerPhoneNumber, 
				 String status, List<String> items, OffsetDateTime ts ) {
		
		this.orderId = orderId;
		this.customerId = customerId;
		this.customerPhoneNumber = customerPhoneNumber;
		this.status = status;
		this.items = items;
		this.ts = ts;
	}

}
