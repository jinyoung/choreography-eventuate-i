package choreography.eventuate.i.domain;

import choreography.eventuate.i.domain.*;
import choreography.eventuate.i.infra.AbstractEvent;
import lombok.*;
import java.util.*;
@Data
@ToString
public class OrderCreated extends AbstractEvent {

    private Long id;
    private String currencyId;
    private Double amount;
    private String holderId;
}


