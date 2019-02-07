package mk;

import java.time.Duration;
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
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@RestController
public class TopicController {

	KafkaManager kafkaManager;

	 public final EmitterProcessor<ServerSentEvent<TopicsList>> emitter = EmitterProcessor.create();	
		
	 public Flux<ServerSentEvent<TopicsList>> get()
//	 public Flux<WorkUnit> get()
	    {
	        return emitter.log();
	    }	 
	 
	
	 private static Logger log = LoggerFactory.getLogger(TopicController.class);

	    public TopicController(KafkaManager kafkaManager)
	    {

	        this.kafkaManager = kafkaManager;
	    	 kafkaManager.init();
	    }
	    
//		 private ExecutorService nonBlockingService = Executors     .newCachedThreadPool();
		
	    @CrossOrigin(origins = "*")
	    @GetMapping(name = "/topics", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	    //@GetMapping(name = "/topics")
	    //@RequestMapping("/topics")
	    public Flux<ServerSentEvent<TopicsList>> getTopics(
   	    //public TopicsList getTopics(
//   	    	    public SseEmitter  getTopics(
	    		//@RequestParam(value = "id", defaultValue = ".") String id
   	    		@RequestHeader HttpHeaders headers
	    		) throws InterruptedException, ExecutionException
	    {
	    	log.info("executing /topics");
	    	System.out.println("Headers start");
	    	Set<String> keys = headers.keySet();
			for (String key : keys) {

				List<String> value = headers.get(key);

				int size = value.size();
				
				for (int i=0;i<size;i++) 			System.out.println(key + " " +value.get(i));



			}

			System.out.println("Headers end");

			
			return get()
					//.filter(
	    			  //s -> s.data().getSpanTraceId().equals(id)
	    			
	    			
//	    			)
;
	    			
	    			
			/*
	    	//eventConsumer.get().flatMap(mapper)
	    	//WorkUnit wu=  	eventConsumer.get();
	       // return eventConsumer.get();
	    	
	    	WorkUnit wu=new WorkUnit("", "", "", "");
	    	wu.setDefinition("mkdefinition");
	    	wu.setId("mkid");
	    	wu.setParentId("mkparentId");
	    	wu.setSpanTraceId("mkspanTraceId");

	    	TopicsList topicsList= kafkaManager.getTopics();
	    	kafkaManager.describeTopic("my-topic");
//return topicsList.st;

*/
/*
 	    	return Flux
	    			.interval(Duration.ofSeconds(10))
	    			.map(l -> ServerSentEvent
	    					.builder(topicsList)
	    					.comment("bar\nbaz")
	    					.id(Long.toString(l))
	    .build());
*/
	    //	return Flux.just(ServerSentEvent.builder(topicsList).id(UUID.randomUUID().toString()).build());
		    	}
}
