package mk;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@RestController
public class EventController {


	KafkaManager kafkaManager;
	
	 private static Logger log = LoggerFactory.getLogger(EventController.class);

	   private KafkaApplicationListener eventConsumer;

	    public EventController(KafkaApplicationListener eventConsumer, KafkaManager kafkaManager)
	    {
	        this.eventConsumer = eventConsumer;
	        this.kafkaManager = kafkaManager;
	    	 kafkaManager.init();
	    }

	    @CrossOrigin(origins = "*")
//	    @GetMapping(name = "/topics", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	    //@GetMapping(name = "/topics")
	    @RequestMapping("/topics")
//	    public Flux<ServerSentEvent<WorkUnit>> getTopics(
   	    public String getTopics(
	    		//@RequestParam(value = "id", defaultValue = ".") String id
	    		) throws InterruptedException, ExecutionException
	    {
	    	log.info("executing /topics");

	    	//eventConsumer.get().flatMap(mapper)
	    	//WorkUnit wu=  	eventConsumer.get();
	       // return eventConsumer.get();
	    	
	    	WorkUnit wu=new WorkUnit("", "", "", "");
	    	wu.setDefinition("mkdefinition");
	    	wu.setId("mkid");
	    	wu.setParentId("mkparentId");
	    	wu.setSpanTraceId("mkspanTraceId");

	    	kafkaManager.listTopics();
	    	kafkaManager.describeTopic("my-topic");

	    	return wu.toString();
	}

		@CrossOrigin(origins = "*")
			    @GetMapping(name = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
			    public Flux<ServerSentEvent<WorkUnit>> getEvents(@RequestParam(value = "id", defaultValue = ".") String id)
			    {
			    	log.info("wuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu executing /events for id "+id);
		/*
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
			    	
			    	
			*/    	
			    	//eventConsumer.get().flatMap(mapper)
			    	//WorkUnit wu=  	eventConsumer.get();
			       // return eventConsumer.get();
			    	return eventConsumer.get().filter(
			    			  s -> s.data().getSpanTraceId().equals(id)
			    			
			    			
			    			);
			}
}
