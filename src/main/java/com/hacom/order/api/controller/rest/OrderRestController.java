package com.hacom.order.api.controller.rest;

import java.time.OffsetDateTime;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

import com.hacom.order.aplication.port.in.OrderPortIn;
import com.hacom.order.domain.model.Order;

@RestController
@RequestMapping("v1/api/order")
public class OrderRestController {
    
    private final OrderPortIn orderPortIn;
	
	public OrderRestController(OrderPortIn orderPortIn){
		this.orderPortIn = orderPortIn;
	}
     
    @GetMapping("/findOrder/{id}")
    public Order findOrder(@PathVariable ObjectId id) {
        return orderPortIn.searchOrder(id);
    }
    
    @GetMapping("/findOrder/{dateStart}/{dateEnd}")
    public List<Order> findOrder(@PathVariable OffsetDateTime dateStart, @PathVariable OffsetDateTime dateEnd) {
        return orderPortIn.searchOrder_RangeDate(dateStart, dateEnd);
    }
	
}
