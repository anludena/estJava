package com.hacom.order.infrastructure.repository;

//import org.mapstruct.Mapper;

import com.hacom.order.domain.model.Order;
import com.hacom.order.infrastructure.persistence.OrderEntity;

//@Mapper(componentModel = "spring")
public interface OrderMapperInfra2 {
	
	Order toDomain(OrderEntity entity);

	OrderEntity toEntity(Order domain);
}
