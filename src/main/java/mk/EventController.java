package mk;

import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import reactor.core.publisher.Flux;
@Controller
public class EventController {

	
	   private KafkaApplicationListener eventConsumer;

	    public EventController(KafkaApplicationListener eventConsumer)
	    {
	        this.eventConsumer = eventConsumer;
	    }

	    @CrossOrigin(origins = "*")
	    @GetMapping(name = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	    public Flux<ServerSentEvent<WorkUnit>> getEvents()
	    {
	        return eventConsumer.get();
	}
}
