package mk;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;


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
public class ApplicationListener {

    @Autowired
	private Tracer tracer;
    @Autowired
    private RestTemplate restTemplate;
    
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

	 private static Logger log = LoggerFactory.getLogger(ApplicationListener.class);


//	protected Logger logger = Logger.getLogger(Application.class.getName());

	public static void main(String[] args) {

	SpringApplication.run(ApplicationListener.class, args);

	}

	 @StreamListener(WorkUnitsSink.CHANNEL_NAME)
//		public void processOrder( String orderString ) {
			public void processOrder( WorkUnit orderIn) {

//		 String orderString = new String(orderData);
		 String orderString = new String("SSS");
		 

//		   log.info("orderString  " + orderString );
			 
		 try {
			Thread.sleep(20);
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		 
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
			   log.info("orderIn.getId() " + orderIn.getId() );
		   
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

		    log.info("newSpan.getParents().size(): " + newSpan.getParents().size());
		    log.info("newSpan.getParents().get(0).longValue(): " + newSpan.getParents().get(0).longValue());
		    log.info("Span.hexToId(order.getSpanTraceId()): " + Span.hexToId(order.getSpanTraceId()));
		    log.info("newSpan.getProcessId(): " + newSpan.getProcessId());
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
