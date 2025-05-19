package com.hacom.order.infrastructure.repository;

import com.hacom.order.domain.model.Order;
import com.hacom.order.infrastructure.persistence.OrderEntity;
import lombok.extern.slf4j.Slf4j;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
@Repository
public class OrderRepositoryImpl implements OrderRepository {
	
	 
	  private final MongoOrderRepository mongoOrderRepository;
	  //private final OrderMapperInfra orderMapper;
	  
	  /*public OrderRepositoryImpl(MongoOrderRepository mongoOrderRepository, OrderMapperInfra orderMapper) {
		  this.mongoOrderRepository = mongoOrderRepository;
		  this.orderMapper = orderMapper;
	  }*/
	  
	  public OrderRepositoryImpl(MongoOrderRepository mongoOrderRepository) {
		  this.mongoOrderRepository = mongoOrderRepository;
	  }
	  
	  
	  /*@Override
	  public Mono<Order> searcOrderBy_id(ObjectId _id) {
		  return mongoOrderRepository.findById(_id)
				  					  .map(OrderMapperInfra::toDomain);
		  							  //.map(orderMapper::toDomain);
	  }
	  
	  @Override
	  public Flux<Order>searchByRangeDate(OffsetDateTime dateStart, OffsetDateTime dateEnd) {
		    return mongoOrderRepository.findByTsBetween(dateStart, dateEnd)
		    							.map(OrderMapperInfra::toDomain);
                    					//.map(orderMapper::toDomain);
	  }*/
	  
	  @Override
	  public Order searcOrderBy_id(ObjectId _id) {
		  
		  log.info("Modulo: Order, ejecución Repository.searcOrderBy_id");
		  
		  Order orderResult =  mongoOrderRepository.findById(_id)
							  					   .map(OrderMapperInfra::toDomain)
										           .block();
		  return orderResult;
	  }
	  
	  @Override
	  public List<Order>searchByRangeDate(OffsetDateTime dateStart, OffsetDateTime dateEnd) {
		  
		  log.info("Modulo: Order, ejecución Repository.searchByRangeDate");
		  
		  List<Order> orderList = mongoOrderRepository.findByTsBetween(dateStart, dateEnd)
						    							.map(OrderMapperInfra::toDomain)
						    							.collectList()
													    .block();
		    
		    return orderList;
	  }
	  
	  
	  /*@Override
	  public Flux<Order>createOrder(Order order){
		  
		  OrderEntity orderEntity = orderMapper.toEntity(order);
		  
		  List<OrderEntity> listOrderEntity = new ArrayList<>();
		  listOrderEntity.add(orderEntity);
		  
		  return mongoOrderRepository.insert(listOrderEntity)
				  					  .map(orderMapper::toDomain);
	  };*/
	  
	  
	  @Override
	  public Order createOrder(Order order){
		  
		  log.info("Modulo: Order, ejecución Repository.createOrder");
		  
		  //OrderEntity orderEntity = orderMapper.toEntity(order);
		  OrderEntity orderEntity = OrderMapperInfra.toEntity(order);
		  
		  List<OrderEntity> listOrderEntity = new ArrayList<>();
		  listOrderEntity.add(orderEntity);
		  
		  Order orderResult = mongoOrderRepository.insert(listOrderEntity)
				  								  .map(OrderMapperInfra::toDomain)
				  								  //.map(orderMapper::toDomain)
				  								  .next()
			                                      .block();
		  return orderResult;
	  };
	  
	  /*@Override
	  public Flux<Order> createOrders(List<Order> listOrders) {
	      List<OrderEntity> listOrderEntity = listOrders.stream()
	                                         .map(orderMapper::toEntity)
	                                         .collect(Collectors.toList());
	      	 
	      return mongoOrderRepository.insert(listOrderEntity)
	                                 .map(orderMapper::toDomain);
	  }*/
	  
	  @Override
	  public List<Order> createOrders(List<Order> listOrders) {
		  
		  log.info("Modulo: Order, ejecución Repository.createOrders");
		  
		  List<OrderEntity> listOrderEntity = listOrders.stream()
				  										.map(OrderMapperInfra::toEntity)
										                 //.map(orderMapper::toEntity)
										                 .collect(Collectors.toList()); //convertir domain a Entity
		  
		  List<Order> orderList = mongoOrderRepository.insert(listOrderEntity)
													    .parallel()
													    .runOn(Schedulers.parallel())
													    .map(OrderMapperInfra::toDomain)
													    //.map(orderMapper::toDomain)
													    .sequential()
													    .collectList()
													    .block();
		  
		  return orderList;
	  };
	  
}
