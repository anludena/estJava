package com.hacom.order.aplication.actor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hacom.order.aplication.port.in.OrderPortIn;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

@Configuration
public class ActorConfig {

    @Bean
    public ActorSystem actorSystem() {
        return ActorSystem.create("order-actorSystem");
    }

    @Bean
    public ActorRef orderProcessor(ActorSystem system, OrderPortIn orderPortIn) {
        return system.actorOf(Props.create(OrderActor.class, () -> new OrderActor(orderPortIn)), "orderProcessor");
    }
}
