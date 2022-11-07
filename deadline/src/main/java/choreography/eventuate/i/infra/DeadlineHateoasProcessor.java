package choreography.eventuate.i.infra;
import choreography.eventuate.i.domain.*;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import org.springframework.hateoas.EntityModel;

@Component
public class DeadlineHateoasProcessor implements RepresentationModelProcessor<EntityModel<Deadline>>  {

    @Override
    public EntityModel<Deadline> process(EntityModel<Deadline> model) {

        
        return model;
    }
    
}
