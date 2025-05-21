package com.hacom.order.api.grpc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hacom.order.api.grpc.service.OrderGrpcService;

import akka.actor.ActorRef;

//@Configuration
public class GrpcConfig {

    /*@Bean
    public OrderGrpcService orderGrpcService(ActorRef orderProcessor) {
        return new OrderGrpcService(orderProcessor);
    }*/
}
