package choreography.eventuate.i.domain;

import choreography.eventuate.i.domain.*;
import lombok.*;
import java.util.*;
import io.eventuate.tram.events.common.DomainEvent;


@Data
@ToString
public class DeadlineReached implements DomainEvent {

    private Long id;
    private Date deadline;
    private Long orderId;
    private Date startedTime;
}


