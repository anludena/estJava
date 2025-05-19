package com.hacom.order.infrastructure.repository;

import com.hacom.order.domain.model.Order;
import com.hacom.order.infrastructure.persistence.OrderEntity;

public class OrderMapperInfra {
	
	public static Order toDomain(OrderEntity orderEntity) {
		Order order = new Order();
		order.setOrderId(orderEntity.getOrderId());
		order.setCustomerId(orderEntity.getCustomerId());
		order.setCustomerPhoneNumber(orderEntity.getCustomerPhoneNumber());
		order.setStatus(orderEntity.getStatus());
		order.setItems(orderEntity.getItems());
		order.setTs(orderEntity.getTs());
		return order;
    }

   public static OrderEntity toEntity(Order order) {
    	OrderEntity orderEntity = new OrderEntity();
    	orderEntity.set_id(order.get_id());
    	orderEntity.setOrderId(order.getOrderId());
    	orderEntity.setCustomerId(order.getCustomerId());
    	orderEntity.setCustomerPhoneNumber(order.getCustomerPhoneNumber());
    	orderEntity.setStatus(order.getStatus());
    	orderEntity.setItems(order.getItems());
    	orderEntity.setTs(order.getTs());
        
        return orderEntity;
    }

}
