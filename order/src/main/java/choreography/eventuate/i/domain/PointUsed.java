package choreography.eventuate.i.domain;

import choreography.eventuate.i.domain.*;
import lombok.*;
import java.util.*;
import io.eventuate.tram.events.common.DomainEvent;


@Data
@ToString
public class PointUsed implements DomainEvent {

    private Long id;
    private Long userId;
    private Double point;
    private Long orderId;
}


