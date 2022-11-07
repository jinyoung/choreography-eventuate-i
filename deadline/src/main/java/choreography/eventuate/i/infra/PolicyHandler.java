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
    public DomainEventDispatcher orderEventDispatcher(DomainEventDispatcherFactory domainEventDispatcherFactory) {
      return domainEventDispatcherFactory.make("OrderEvents", DomainEventHandlersBuilder
      .forAggregateType(".domain.Order")
      .onEvent(OrderCreated.class, PolicyHandler::wheneverOrderCreated_Schedule)
      .onEvent(OrderPlaced.class, PolicyHandler::wheneverOrderPlaced_RemoveDeadline)
      .build());
    }



    @Autowired DeadlineRepository deadlineRepository;
    



    public static void wheneverOrderCreated_Schedule(DomainEventEnvelope<OrderCreated> orderCreatedEnvelope){

        OrderCreated event = orderCreatedEnvelope.getEvent();
        System.out.println("\n\n##### listener Schedule : " + event + "\n\n");


        

        // Sample Logic //
        Deadline.schedule(event);
        

        

    }




    public static void wheneverOrderPlaced_RemoveDeadline(DomainEventEnvelope<OrderPlaced> orderPlacedEnvelope){

        OrderPlaced event = orderPlacedEnvelope.getEvent();
        System.out.println("\n\n##### listener RemoveDeadline : " + event + "\n\n");


        

        // Sample Logic //
        Deadline.removeDeadline(event);
        

        

    }

}


