package com.hacom.order.infrastructure.persistence;

import java.time.OffsetDateTime;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "orders")
public class OrderEntity {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private ObjectId _id;
	
	private String orderId;
	private String customerId;
	private String customerPhoneNumber;
	private String status;
	private List<String> items;
	private OffsetDateTime ts;
}
