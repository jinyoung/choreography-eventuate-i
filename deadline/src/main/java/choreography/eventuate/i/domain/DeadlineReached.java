package choreography.eventuate.i.domain;

import choreography.eventuate.i.domain.*;
import java.util.*;
import lombok.*;
import io.eventuate.tram.events.common.DomainEvent;
import org.springframework.beans.BeanUtils;


@Data
@ToString
public class DeadlineReached implements DomainEvent {

    private Long id;
    private Date deadline;
    private Long orderId;
    private Date startedTime;

    public DeadlineReached(Deadline aggregate){
        BeanUtils.copyProperties(aggregate, this);
    }
    public DeadlineReached(){
        super();
    }
}
