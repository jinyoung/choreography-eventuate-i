package choreography.eventuate.i.domain;

import static choreography.eventuate.i.DeadlineApplication.applicationContext;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;
import java.util.Collections;

import io.eventuate.tram.events.publisher.DomainEventPublisher;


@Entity
@Table(name="Deadline_table")
@Data

public class Deadline  {

    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    
    private Long id;
    
    
    
    
    
    private Date deadline;
    
    
    
    
    
    private Long orderId;
    
    
    
    
    
    private Date startedTime;

    @PostPersist
    public void onPostPersist(){


        DeadlineReached deadlineReached = new DeadlineReached(this);

        publisher().publish(getClass(), getId(), Collections.singletonList(deadlineReached));

    }

    public static DeadlineRepository repository(){
        DeadlineRepository deadlineRepository = applicationContext.getBean(DeadlineRepository.class);
        return deadlineRepository;
    }

    static DomainEventPublisher publisher(){
        return applicationContext.getBean(
            DomainEventPublisher.class
        );
    }




    public static void schedule(OrderCreated orderCreated){

        /** Example 1:  new item 
        Deadline deadline = new Deadline();
        repository().save(deadline);

        */

        /** Example 2:  finding and process
        
        repository().findById(orderCreated.get???()).ifPresent(deadline->{
            
            deadline // do something
            repository().save(deadline);


         });
        */

        
    }
    public static void removeDeadline(OrderPlaced orderPlaced){

        /** Example 1:  new item 
        Deadline deadline = new Deadline();
        repository().save(deadline);

        */

        /** Example 2:  finding and process
        
        repository().findById(orderPlaced.get???()).ifPresent(deadline->{
            
            deadline // do something
            repository().save(deadline);


         });
        */

        
    }


}
