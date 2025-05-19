package com.hacom.order.aplication.service;

import com.hacom.order.aplication.port.in.OrderPortIn;
import com.hacom.order.domain.model.Order;
//import com.hacom.order.infrastructure.repository.MongoOrderRepository;
import com.hacom.order.infrastructure.repository.OrderRepository;
import com.hacom.order.infrastructure.repository.OrderRepositoryImpl;

import lombok.extern.slf4j.Slf4j;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;

//import java.time.OffsetDateTime;
import java.util.List;

//@Service("orderServiceApp")
@Service
@Slf4j
public class OrderServiceAppImpl implements OrderPortIn {
		
	 	private OrderRepository orderRepository;
	 	
	 	public OrderServiceAppImpl(OrderRepository orderRepository) {
	        this.orderRepository = orderRepository;
	    }
	    
	    @Override
		public Order generateOrder(Order order){
	    	log.info("Modulo Order: aplication.OrderServiceAppImpl.generateOrder");
	    	
	    	//order.setTs(OffsetDateTime.now()); //en caso deseo una fecha auto generada
	        order.setStatus("CREATED");
	    	return orderRepository.createOrder(order);
	    }
	    
	    @Override
		public List<Order> generateOrders(List<Order> orders){
	    	log.info("Modulo Order: aplication.OrderServiceAppImpl.generateOrders");
	    	
	    	orders.stream().forEach(o -> o.setStatus("CREATED")); //Cambiamos el valor para crear el registro

	    	return orderRepository.createOrders(orders);
	    }
	    
	    @Override
	    public Order searchOrder(ObjectId _id) {
	    	log.info("Modulo Order: aplication.OrderServiceAppImpl.searchOrder");
	    	
	    	return orderRepository.searcOrderBy_id(_id);
	    }
	    
	    @Override
		public List<Order>searchOrder_RangeDate(OffsetDateTime dateStart, OffsetDateTime dateEnd){
	    	log.info("Modulo Order: aplication.OrderServiceAppImpl.searchOrder_RangeDate");
	    	
			return orderRepository.searchByRangeDate(dateStart, dateEnd);
		}

}
