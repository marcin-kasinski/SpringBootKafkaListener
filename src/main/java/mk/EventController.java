package mk;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import reactor.core.publisher.Flux;
@Controller
public class EventController {


	 private static Logger log = LoggerFactory.getLogger(EventController.class);

	
	   private KafkaApplicationListener eventConsumer;

	    public EventController(KafkaApplicationListener eventConsumer)
	    {
	        this.eventConsumer = eventConsumer;
	    }

	    @CrossOrigin(origins = "*")
	    @GetMapping(name = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	    public Flux<ServerSentEvent<WorkUnit>> getEvents()
	    {
	    	System.out.println();

	    	List<WorkUnit> wus = new ArrayList<>();

	    	eventConsumer.get().subscribe(wus::add);

	    	WorkUnit wu=wus.get(0);
	    	wu.setDefinition("mkdefinition");
	    	wu.setId("mkid");
	    	wu.setParentId("mkparentId");
	    	wu.setSpanTraceId("mkspanTraceId");

	    	log.info("wuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu "+wu);
	    	
	    	return Flux.just(ServerSentEvent.builder(wu).id(UUID.randomUUID().toString()).build())
	    	  .log();
	    	
	    	
	    	
	    	//eventConsumer.get().flatMap(mapper)
	    	//WorkUnit wu=  	eventConsumer.get();
	       // return eventConsumer.get();
	}
}
