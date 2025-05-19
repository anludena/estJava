package com.hacom.order.infrastructure.repository;

import java.time.OffsetDateTime;
import java.util.List;

import org.bson.types.ObjectId;

import com.hacom.order.domain.model.Order;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderRepository {
	/*Mono<Order> searcOrderBy_id(ObjectId _id);
	Flux<Order> searchByRangeDate(OffsetDateTime dateStart, OffsetDateTime dateEnd);
	Flux<Order> createOrder(Order order);
	Flux<Order> createOrders(List<Order> orders);*/
	
	Order searcOrderBy_id(ObjectId _id);
	List<Order> searchByRangeDate(OffsetDateTime dateStart, OffsetDateTime dateEnd);
	Order createOrder(Order order);
	List<Order> createOrders(List<Order> orders);
}