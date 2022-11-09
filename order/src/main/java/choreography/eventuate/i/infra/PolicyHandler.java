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
      .forAggregateType("choreography.eventuate.i.domain.Exchange")
      .onEvent(ExchangeFailed.class, PolicyHandler::wheneverExchangeFailed_Reject)
      .build());
    }


    @Bean
    public DomainEventDispatcher pointEventDispatcher(DomainEventDispatcherFactory domainEventDispatcherFactory) {
      return domainEventDispatcherFactory.make("PointEvents", DomainEventHandlersBuilder
      .forAggregateType("choreography.eventuate.i.domain.Point")
      .onEvent(PointUsed.class, PolicyHandler::wheneverPointUsed_Approve)
      .build());
    }



    @Autowired OrderRepository orderRepository;
    



    public static void wheneverExchangeFailed_Reject(DomainEventEnvelope<ExchangeFailed> exchangeFailedEnvelope){

        ExchangeFailed event = exchangeFailedEnvelope.getEvent();
        System.out.println("\n\n##### listener Reject : " + event + "\n\n");


        

        // Sample Logic //
        Order.reject(event);
        

        

    }
    public static void wheneverDeadlineReached_Reject(DomainEventEnvelope<DeadlineReached> deadlineReachedEnvelope){

        DeadlineReached event = deadlineReachedEnvelope.getEvent();
        System.out.println("\n\n##### listener Reject : " + event + "\n\n");


        

        // Sample Logic //
        Order.reject(event);
        

        

    }
    public static void wheneverPointUseFailed_Reject(DomainEventEnvelope<PointUseFailed> pointUseFailedEnvelope){

        PointUseFailed event = pointUseFailedEnvelope.getEvent();
        System.out.println("\n\n##### listener Reject : " + event + "\n\n");


        

        // Sample Logic //
        Order.reject(event);
        

        

    }




    public static void wheneverPointUsed_Approve(DomainEventEnvelope<PointUsed> pointUsedEnvelope){

        PointUsed event = pointUsedEnvelope.getEvent();
        System.out.println("\n\n##### listener Approve : " + event + "\n\n");


        

        // Sample Logic //
        Order.approve(event);
        

        

    }

}


