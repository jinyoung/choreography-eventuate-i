package choreography.eventuate.i.domain;

import static choreography.eventuate.i.PointApplication.applicationContext;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;
import java.util.Collections;

import io.eventuate.tram.events.publisher.DomainEventPublisher;


@Entity
@Table(name="Point_table")
@Data

public class Point  {

    
    
    
    
    
    private Double point;
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    
    private String userId;

    @PostPersist
    public void onPostPersist(){


        PointUsed pointUsed = new PointUsed(this);

        publisher().publish(getClass(), getId(), Collections.singletonList(pointUsed));



        PointUseFailed pointUseFailed = new PointUseFailed(this);

        publisher().publish(getClass(), getId(), Collections.singletonList(pointUseFailed));

    }

    public static PointRepository repository(){
        PointRepository pointRepository = applicationContext.getBean(PointRepository.class);
        return pointRepository;
    }

    static DomainEventPublisher publisher(){
        return applicationContext.getBean(
            DomainEventPublisher.class
        );
    }




    public static void usePoint(ExchangeSucceeded exchangeSucceeded){

        /** Example 1:  new item 
        Point point = new Point();
        repository().save(point);

        PointUsed pointUsed = new PointUsed(point);
        publisher().publish(pointUsed.getClass(), getId(), Collections.singletonList(pointUsed));

        PointUseFailed pointUseFailed = new PointUseFailed(point);
        publisher().publish(pointUseFailed.getClass(), getId(), Collections.singletonList(pointUseFailed));

        */

        /** Example 2:  finding and process
        
        repository().findById(exchangeSucceeded.get???()).ifPresent(point->{
            
            point // do something
            repository().save(point);

            PointUsed pointUsed = new PointUsed(point);

            PointUseFailed pointUseFailed = new PointUseFailed(point);


         });
        */

        
    }
    public static void compensate(OrderRejected orderRejected){

        /** Example 1:  new item 
        Point point = new Point();
        repository().save(point);

        */

        /** Example 2:  finding and process
        
        repository().findById(orderRejected.get???()).ifPresent(point->{
            
            point // do something
            repository().save(point);


         });
        */

        
    }


}
