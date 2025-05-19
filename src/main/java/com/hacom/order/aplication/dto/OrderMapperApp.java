package com.hacom.order.aplication.dto;

import org.mapstruct.Mapper;

import com.hacom.order.domain.model.Order;

@Mapper(componentModel = "spring")
public interface OrderMapperApp {

	OrderCommand toDto(Order domain);

    Order toDomain(OrderCommand dto);

}
