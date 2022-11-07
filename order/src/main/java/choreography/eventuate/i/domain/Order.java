package choreography.eventuate.i.domain;

import static choreography.eventuate.i.OrderApplication.applicationContext;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;
import java.util.Collections;

import io.eventuate.tram.events.publisher.DomainEventPublisher;


@Entity
@Table(name="Order_table")
@Data

public class Order  {

    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    
    private Long id;
    
    
    
    
    
    private String currencyId;
    
    
    
    
    
    private Double amount;
    
    
    
    
    
    private String status;
    
    
    
    
    
    private String holderId;

    @PostPersist
    public void onPostPersist(){


        OrderCreated orderCreated = new OrderCreated(this);

        publisher().publish(getClass(), getId(), Collections.singletonList(orderCreated));



        OrderPlaced orderPlaced = new OrderPlaced(this);

        publisher().publish(getClass(), getId(), Collections.singletonList(orderPlaced));



        OrderRejected orderRejected = new OrderRejected(this);

        publisher().publish(getClass(), getId(), Collections.singletonList(orderRejected));

    }

    public static OrderRepository repository(){
        OrderRepository orderRepository = applicationContext.getBean(OrderRepository.class);
        return orderRepository;
    }

    static DomainEventPublisher publisher(){
        return applicationContext.getBean(
            DomainEventPublisher.class
        );
    }




    public static void reject(ExchangeFailed exchangeFailed){

        /** Example 1:  new item 
        Order order = new Order();
        repository().save(order);

        OrderRejected orderRejected = new OrderRejected(order);
        publisher().publish(orderRejected.getClass(), getId(), Collections.singletonList(orderRejected));

        */

        /** Example 2:  finding and process
        
        repository().findById(exchangeFailed.get???()).ifPresent(order->{
            
            order // do something
            repository().save(order);

            OrderRejected orderRejected = new OrderRejected(order);


         });
        */

        
    }
    public static void reject(DeadlineReached deadlineReached){

        /** Example 1:  new item 
        Order order = new Order();
        repository().save(order);

        OrderRejected orderRejected = new OrderRejected(order);
        publisher().publish(orderRejected.getClass(), getId(), Collections.singletonList(orderRejected));

        */

        /** Example 2:  finding and process
        
        repository().findById(deadlineReached.get???()).ifPresent(order->{
            
            order // do something
            repository().save(order);

            OrderRejected orderRejected = new OrderRejected(order);


         });
        */

        
    }
    public static void reject(PointUseFailed pointUseFailed){

        /** Example 1:  new item 
        Order order = new Order();
        repository().save(order);

        OrderRejected orderRejected = new OrderRejected(order);
        publisher().publish(orderRejected.getClass(), getId(), Collections.singletonList(orderRejected));

        */

        /** Example 2:  finding and process
        
        repository().findById(pointUseFailed.get???()).ifPresent(order->{
            
            order // do something
            repository().save(order);

            OrderRejected orderRejected = new OrderRejected(order);


         });
        */

        
    }
    public static void approve(PointUsed pointUsed){

        /** Example 1:  new item 
        Order order = new Order();
        repository().save(order);

        OrderPlaced orderPlaced = new OrderPlaced(order);
        publisher().publish(orderPlaced.getClass(), getId(), Collections.singletonList(orderPlaced));

        */

        /** Example 2:  finding and process
        
        repository().findById(pointUsed.get???()).ifPresent(order->{
            
            order // do something
            repository().save(order);

            OrderPlaced orderPlaced = new OrderPlaced(order);


         });
        */

        
    }


}
