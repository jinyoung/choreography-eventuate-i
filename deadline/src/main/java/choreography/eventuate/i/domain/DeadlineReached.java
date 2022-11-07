package choreography.eventuate.i.domain;

import choreography.eventuate.i.domain.*;
import choreography.eventuate.i.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class DeadlineReached extends AbstractEvent {

    private Long id;
    private Date deadline;
    private Long orderId;
    private Date startedTime;

    public DeadlineReached(Deadline aggregate){
        super(aggregate);
    }
    public DeadlineReached(){
        super();
    }
}
