package mk;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import mk.data.TopicsList;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@RestController
public class EventController {


	//KafkaManager kafkaManager;
	
	 private static Logger log = LoggerFactory.getLogger(EventController.class);

	   private KafkaApplicationListener eventConsumer;

	    public EventController(KafkaApplicationListener eventConsumer)
	    {
	        this.eventConsumer = eventConsumer;
	    }
	    
		 private ExecutorService nonBlockingService = Executors
			      .newCachedThreadPool();
		/*
	    @CrossOrigin(origins = "*")
			    @GetMapping(name = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
			    public Flux<ServerSentEvent<WorkUnit>> getEvents(@RequestParam(value = "id", defaultValue = ".") String id)
			    {
			    	log.info("wuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu executing /events for id "+id);
	
		    	//eventConsumer.get().flatMap(mapper)
			    	//WorkUnit wu=  	eventConsumer.get();
			       // return eventConsumer.get();
			    	return eventConsumer.get().filter(
			    			  s -> s.data().getSpanTraceId().equals(id)
			    			
			    			
			    			);
			    			
			    			
		
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
			    	
			    	
	
			    			
			}

*/
}
