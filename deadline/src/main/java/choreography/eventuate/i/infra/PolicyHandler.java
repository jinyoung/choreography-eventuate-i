package choreography.eventuate.i.infra;

import javax.naming.NameParser;

import javax.naming.NameParser;
import javax.transaction.Transactional;

import choreography.eventuate.i.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import choreography.eventuate.i.domain.*;


@Service
@Transactional
public class PolicyHandler{
    @Autowired DeadlineRepository deadlineRepository;
    
    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}

    @StreamListener(value=KafkaProcessor.INPUT, condition="headers['type']=='OrderCreated'")
    public void wheneverOrderCreated_Schedule(@Payload OrderCreated orderCreated){

        OrderCreated event = orderCreated;
        System.out.println("\n\n##### listener Schedule : " + orderCreated + "\n\n");


        

        // Sample Logic //
        Deadline.schedule(event);
        

        

    }

    @StreamListener(value=KafkaProcessor.INPUT, condition="headers['type']=='OrderPlaced'")
    public void wheneverOrderPlaced_RemoveDeadline(@Payload OrderPlaced orderPlaced){

        OrderPlaced event = orderPlaced;
        System.out.println("\n\n##### listener RemoveDeadline : " + orderPlaced + "\n\n");


        

        // Sample Logic //
        Deadline.removeDeadline(event);
        

        

    }

}


