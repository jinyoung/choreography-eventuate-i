package choreography.eventuate.i.domain;

import choreography.eventuate.i.domain.*;
import java.util.*;
import lombok.*;
import io.eventuate.tram.events.common.DomainEvent;
import org.springframework.beans.BeanUtils;


@Data
@ToString
public class OrderRejected implements DomainEvent {

    private Long id;
    private String currencyId;
    private Double amount;
    private String holderId;

    public OrderRejected(Order aggregate){
        BeanUtils.copyProperties(aggregate, this);
    }
    public OrderRejected(){
        super();
    }
}
