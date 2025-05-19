package com.hacom.order.infrastructure.repository;

import com.hacom.order.infrastructure.persistence.OrderEntity;

import reactor.core.publisher.Flux;

import java.time.OffsetDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MongoOrderRepository extends ReactiveMongoRepository <OrderEntity, ObjectId> {
	
	Flux<OrderEntity> findByTsBetween(OffsetDateTime from, OffsetDateTime to);

}
