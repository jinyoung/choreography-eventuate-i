package choreography.eventuate.i.infra;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import io.eventuate.tram.events.subscriber.DomainEventDispatcher;
import io.eventuate.tram.events.subscriber.DomainEventDispatcherFactory;
import io.eventuate.tram.events.subscriber.DomainEventEnvelope;
import io.eventuate.tram.events.subscriber.DomainEventHandlersBuilder;

import choreography.eventuate.i.domain.*;


@Service
@Transactional
@Configuration
public class PolicyHandler{



    @Bean
    public DomainEventDispatcher exchangeEventDispatcher(DomainEventDispatcherFactory domainEventDispatcherFactory) {
      return domainEventDispatcherFactory.make("ExchangeEvents", DomainEventHandlersBuilder
      .forAggregateType(".domain.Exchange")
      .onEvent(ExchangeSucceeded.class, PolicyHandler::wheneverExchangeSucceeded_UsePoint)
      .build());
    }


    @Bean
    public DomainEventDispatcher orderEventDispatcher(DomainEventDispatcherFactory domainEventDispatcherFactory) {
      return domainEventDispatcherFactory.make("OrderEvents", DomainEventHandlersBuilder
      .forAggregateType(".domain.Order")
      .onEvent(OrderRejected.class, PolicyHandler::wheneverOrderRejected_Compensate)
      .build());
    }



    @Autowired PointRepository pointRepository;
    



    public static void wheneverExchangeSucceeded_UsePoint(DomainEventEnvelope<ExchangeSucceeded> exchangeSucceededEnvelope){

        ExchangeSucceeded event = exchangeSucceededEnvelope.getEvent();
        System.out.println("\n\n##### listener UsePoint : " + event + "\n\n");


        

        // Sample Logic //
        Point.usePoint(event);
        

        

    }




    public static void wheneverOrderRejected_Compensate(DomainEventEnvelope<OrderRejected> orderRejectedEnvelope){

        OrderRejected event = orderRejectedEnvelope.getEvent();
        System.out.println("\n\n##### listener Compensate : " + event + "\n\n");


        

        // Sample Logic //
        Point.compensate(event);
        

        

    }

}


