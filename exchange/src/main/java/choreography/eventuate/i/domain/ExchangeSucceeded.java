package choreography.eventuate.i.domain;

import choreography.eventuate.i.domain.*;
import java.util.*;
import lombok.*;
import io.eventuate.tram.events.common.DomainEvent;
import org.springframework.beans.BeanUtils;


@Data
@ToString
public class ExchangeSucceeded implements DomainEvent {

    private Long orderId;
    private String userId;
    private Long currencyId;
    private Long amount;
    private Double rate;
    private Double pointUsed;

    public ExchangeSucceeded(Exchange aggregate){
        BeanUtils.copyProperties(aggregate, this);
    }
    public ExchangeSucceeded(){
        super();
    }
}
