package choreography.eventuate.i.domain;

import choreography.eventuate.i.domain.*;
import java.util.*;
import lombok.*;
import io.eventuate.tram.events.common.DomainEvent;
import org.springframework.beans.BeanUtils;


@Data
@ToString
public class PointUseFailed implements DomainEvent {

    private Long id;
    private String reason;
    private Long orderId;

    public PointUseFailed(Point aggregate){
        BeanUtils.copyProperties(aggregate, this);
    }
    public PointUseFailed(){
        super();
    }
}
