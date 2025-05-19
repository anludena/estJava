package com.hacom.order.aplication.port.in;

import java.time.OffsetDateTime;
import java.util.List;

import org.bson.types.ObjectId;

import com.hacom.order.domain.model.Order;

public interface OrderPortIn {
	
	public Order generateOrder(Order order);
	public List<Order> generateOrders(List<Order> orders);
	public Order searchOrder(ObjectId _id);
	public List<Order>searchOrder_RangeDate(OffsetDateTime dateStart, OffsetDateTime dateEnd);

}
