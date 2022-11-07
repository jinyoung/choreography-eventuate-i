package choreography.eventuate.i.domain;

import choreography.eventuate.i.domain.*;
import java.util.*;
import lombok.*;
import io.eventuate.tram.events.common.DomainEvent;
import org.springframework.beans.BeanUtils;


@Data
@ToString
public class PointUsed implements DomainEvent {

    private Long id;
    private Long userId;
    private Double point;
    private Long orderId;

    public PointUsed(Point aggregate){
        BeanUtils.copyProperties(aggregate, this);
    }
    public PointUsed(){
        super();
    }
}
