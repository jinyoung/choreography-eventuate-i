package choreography.eventuate.i.domain;

import static choreography.eventuate.i.ExchangeApplication.applicationContext;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;
import java.util.Collections;

import io.eventuate.tram.events.publisher.DomainEventPublisher;


@Entity
@Table(name="Exchange_table")
@Data

public class Exchange  {

    
    
    
    
    
    private Long currencyId;
    
    
    
    
    
    private Long amount;
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    
    private Long orderId;
    
    
    
    
    
    private Double rate;
    
    
    
    
    
    private Double pointUsed;

    @PostPersist
    public void onPostPersist(){


        ExchangeSucceeded exchangeSucceeded = new ExchangeSucceeded(this);

        publisher().publish(getClass(), getId(), Collections.singletonList(exchangeSucceeded));



        ExchangeFailed exchangeFailed = new ExchangeFailed(this);

        publisher().publish(getClass(), getId(), Collections.singletonList(exchangeFailed));



        ExchangeCompensated exchangeCompensated = new ExchangeCompensated(this);

        publisher().publish(getClass(), getId(), Collections.singletonList(exchangeCompensated));

    }

    public static ExchangeRepository repository(){
        ExchangeRepository exchangeRepository = applicationContext.getBean(ExchangeRepository.class);
        return exchangeRepository;
    }

    static DomainEventPublisher publisher(){
        return applicationContext.getBean(
            DomainEventPublisher.class
        );
    }

    public void decreaseStock(){
        //
    }



    public static void exchange(OrderCreated orderCreated){

        /** Example 1:  new item 
        Exchange exchange = new Exchange();
        repository().save(exchange);

        ExchangeSucceeded exchangeSucceeded = new ExchangeSucceeded(exchange);
        publisher().publish(exchangeSucceeded.getClass(), getId(), Collections.singletonList(exchangeSucceeded));

        ExchangeFailed exchangeFailed = new ExchangeFailed(exchange);
        publisher().publish(exchangeFailed.getClass(), getId(), Collections.singletonList(exchangeFailed));

        */

        /** Example 2:  finding and process
        
        repository().findById(orderCreated.get???()).ifPresent(exchange->{
            
            exchange // do something
            repository().save(exchange);

            ExchangeSucceeded exchangeSucceeded = new ExchangeSucceeded(exchange);

            ExchangeFailed exchangeFailed = new ExchangeFailed(exchange);


         });
        */

        
    }
    public static void compensate(OrderRejected orderRejected){

        /** Example 1:  new item 
        Exchange exchange = new Exchange();
        repository().save(exchange);

        ExchangeCompensated exchangeCompensated = new ExchangeCompensated(exchange);
        publisher().publish(exchangeCompensated.getClass(), getId(), Collections.singletonList(exchangeCompensated));

        */

        /** Example 2:  finding and process
        
        repository().findById(orderRejected.get???()).ifPresent(exchange->{
            
            exchange // do something
            repository().save(exchange);

            ExchangeCompensated exchangeCompensated = new ExchangeCompensated(exchange);


         });
        */

        
    }


}
