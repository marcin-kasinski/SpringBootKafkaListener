package mk;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
//----------------------------------- 2.0.1 -----------------------------------
import brave.Span;
import brave.Tracer;
import brave.internal.HexCodec;
import brave.propagation.TraceContext;


//----------------------------------- 2.0.1 -----------------------------------
*/
//----------------------------------- 1.5.10 -----------------------------------
//import org.springframework.cloud.sleuth.Span;
//import org.springframework.cloud.sleuth.Tracer;
//import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
//----------------------------------- 1.5.10 -----------------------------------
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;



/*

<------------------------ docker ------------------------>


/opt/kafka_2.11-0.10.1.0/bin/kafka-topics.sh --create --zookeeper 127.0.0.1:2181 --replication-factor 1 --partitions 1 --topic logs
/opt/kafka_2.11-0.10.1.0/bin/kafka-topics.sh --list --zookeeper 127.0.0.1:2181
/opt/kafka_2.11-0.10.1.0/bin/kafka-console-consumer.sh --zookeeper 127.0.0.1:2181 --topic my-topic 
/opt/kafka_2.11-0.10.1.0/bin/kafka-console-producer.sh --broker-list 127.0.0.1:9092 --topic my-topic 

<------------------------ docker ------------------------>
{   "parentId" : "ac1ebe5ff1b78e18",   "spanTraceId" : "ac1ebe5ff1b78e18",  "id" : "8 Dec 2017 18:50:21 GMT",  "definition" : "definition"} 
 */


@SpringBootApplication
public class KafkaApplicationListener {

//    @Autowired
//	private Tracer tracer;
    @Autowired
    private RestTemplate restTemplate;
    
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

	 private static Logger log = LoggerFactory.getLogger(KafkaApplicationListener.class);


//	protected Logger logger = Logger.getLogger(Application.class.getName());

	public static void main(String[] args) {


		System.out.println("Version 31.08.2018");
		
		System.out.println("Environment variables");
		

		Map<String, String> env = System.getenv();
		for (String envName : env.keySet()) {
			System.out.format("%s=%s%n", envName, env.get(envName));
		}

	SpringApplication.run(KafkaApplicationListener.class, args);

	}

	 private final EmitterProcessor<ServerSentEvent<WorkUnit>> emitter = EmitterProcessor.create();	
	 //private final EmitterProcessor<WorkUnit> emitter = EmitterProcessor.create();	
	
	 public Flux<ServerSentEvent<WorkUnit>> get()
//	 public Flux<WorkUnit> get()
	    {
	        return emitter.log();
	    }	 
	 
	 @StreamListener(WorkUnitsSink.CHANNEL_NAME)
//		public void processOrder( String orderString ) {

	 public void processOrder( Message<WorkUnit> message, @Headers Map<Object, Object> headers) {
//		 public void processOrder( WorkUnit orderIn, @Headers Map<Object, Object> headers) {

		 
		 
		 WorkUnit orderIn= message.getPayload();
		 
		 log.info("processOrder " + orderIn);
			
		 
		 //od razu sse buduje
		 emitter.onNext(ServerSentEvent.builder(orderIn).id(UUID.randomUUID().toString()).build());
		 //emitter.onNext(orderIn);
		 /*
			log.info("headers: ");

		 for (Map.Entry<Object, Object> entry : headers.entrySet())
		 {
			 log.info(entry.getKey() + "/" + entry.getValue());
		 }

			log.info("headers END: ");
			log.info("orderIn: "+orderIn);
		 */
//		 String orderString = new String(orderData);
		 String orderString = new String("SSS");
		 

//		   log.info("orderString  " + orderString );
			 
		 try {
			Thread.sleep(20);
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		 
		 
//			log.info("before new span");

/*
//----------------------------------- 1.5.10 -----------------------------------
		   Span newSpan= Span.builder()
//					.spanId(1l)
					.spanId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE)
					.traceId(Span.hexToId(orderIn.getSpanTraceId()))
					.parent(Span.hexToId(orderIn.getSpanTraceId()  ))
					.baggage("foo", "bar")
					.name("Kafka Listener")
					.exportable(true).build();
		    
		    //tracer.createSpan(ApplicationListener.class.getName(),newSpan);
		    tracer.continueSpan(newSpan);
			log.info("after new span");

//----------------------------------- 1.5.10 -----------------------------------
*/

		 


//----------------------------------- 2.0.1 -----------------------------------
	 
//		 TraceContext context = TraceContext.newBuilder()
//			      .traceId(  HexCodec.lowerHexToUnsignedLong(orderIn.getSpanTraceId())        )
//			      .spanId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE)
//			      .sampled(true).build();
//		 Span continuedSpan = this.tracer.joinSpan(context);

//----------------------------------- 2.0.1 -----------------------------------

		   // log.info("orderIn " + orderIn);
		   // log.info("orderIn.getId() " + orderIn.getId() );
		   
			
			 Acknowledgment acknowledgment = message.getHeaders().get(KafkaHeaders.ACKNOWLEDGMENT, Acknowledgment.class);
		        if (acknowledgment != null) {
		     //   	log.info("Acknowledgment provided");
		            acknowledgment.acknowledge();
		        }
		        else 	        	log.info("Acknowledgment not provided");

		    
		    
		   if (1==1) return;
		   
		   
//			log.info("SpanTraceId: " + order.getSpanTraceId());
		 ObjectMapper mapper = new ObjectMapper();
		//JSON from String to Object
		 WorkUnit order = null;
		try {
			order = mapper.readValue(orderString, WorkUnit.class);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


	
	 
//		 Span newSpan = this.tracer.createSpan("calculateTax");
		// ...
			// You can tag a span
//			this.tracer.addTag("taxValue", taxValue);
			// ...
			// You can log an event on a span
//			newSpan.logEvent("taxCalculated");

//		    log.info("newSpan.getParents().size(): " + newSpan.getParents().size());
//		    log.info("newSpan.getParents().get(0).longValue(): " + newSpan.getParents().get(0).longValue());
//		    log.info("Span.hexToId(order.getSpanTraceId()): " + Span.hexToId(order.getSpanTraceId()));
//		    log.info("newSpan.getProcessId(): " + newSpan.getProcessId());
					log.info("Read data: " + order.getParentId());
		log.info("Read data: " + order.getSpanTraceId());
		log.info("Read data: " + order.getId());
		log.info("Read data: " + order.getDefinition());

		log.info("Executing microservice");
		
//		restTemplate = new RestTemplate();

		try {
        User user = restTemplate.getForObject("http://springbootmicroservice:8081/api/get-by-email?email=z@fromlistener.com", User .class);
        log.info("Microservice executed: "+user.getName());
		}//try
		catch(Exception e)
		{
	        log.info("Microservice executing exception: "+e.getMessage());
	        log.info("Microservice executing exception: "+e.getLocalizedMessage());
			
		}
		
	
	}
	
	/*
	@Bean
	public AlwaysSampler defaultSampler() {
	  return new AlwaysSampler();
	}
*/	
}
