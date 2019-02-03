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
import org.springframework.web.bind.annotation.RequestParam;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
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
	    public Flux<ServerSentEvent<WorkUnit>> getEvents(@RequestParam(value = "id", defaultValue = ".") String id)
	    {
	    	log.info("wuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu executing /events");

	    	List<ServerSentEvent> wus = new ArrayList<>();

	    	Flux<ServerSentEvent<WorkUnit>>   obj= eventConsumer.get();
	    	log.info("obj "+obj.toString());
	    	
	    	obj.subscribe(wus::add);

	    	log.info("wus.size() "+wus.size());

	    	
//	    	if (wus.size()==0) return Flux.empty();
//	    	if (wus.size()==0) return null;
	    	
	    	Mono<Long> count=obj.count();
	    	
	    	ServerSentEvent<WorkUnit> readsse=wus.get(0);
	    	WorkUnit wu=readsse.data();
	    	
	    	//WorkUnit wu=wus.get(0);
	    	//wu.setDefinition("mkdefinition");
	    	//wu.setId("mkid");
	    	//wu.setParentId("mkparentId");
	    	//wu.setSpanTraceId("mkspanTraceId");

	    	log.info("wuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu "+wu);
	    	log.info("wuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu count "+count);
	    	
//	    	if (count.)
	    	
	    	//return Flux.just(ServerSentEvent.builder(wu).id(UUID.randomUUID().toString()).build())
	    	//  .log();
	    	
	    	
	    	
	    	//eventConsumer.get().flatMap(mapper)
	    	//WorkUnit wu=  	eventConsumer.get();
	       // return eventConsumer.get();
	    	return obj.filter(
	    			  s -> s.data().getSpanTraceId().equals(id)
	    			
	    			
	    			);
	}
}
