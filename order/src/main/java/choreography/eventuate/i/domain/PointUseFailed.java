package choreography.eventuate.i.domain;

import choreography.eventuate.i.domain.*;
import lombok.*;
import java.util.*;
import io.eventuate.tram.events.common.DomainEvent;


@Data
@ToString
public class PointUseFailed implements DomainEvent {

    private Long id;
    private String reason;
    private Long orderId;
}


