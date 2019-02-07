package mk.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import mk.KafkaManager;
import mk.TopicController;
import mk.data.TopicsList;

@EnableScheduling
@Component
public class ScheduledTask {
	   private static final Logger log = LoggerFactory.getLogger(ScheduledTask.class);
	   private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	   @Autowired
	   TopicController topicController;
	   @Autowired
	   KafkaManager kafkaManager;
	   
	public ScheduledTask() {
		// TODO Auto-generated constructor stub
	}

	int lasttopicsListsize=-1; 
	
    @Scheduled(fixedRate = 30000)
    public void reportCurrentTime() throws InterruptedException, ExecutionException {
        log.info("The time is now {}", dateFormat.format(new Date()));
        TopicsList topicsList= kafkaManager.getTopics();
       if (topicsList.getTopics().size()!=lasttopicsListsize) 
    	   {
           log.info("Sending event");
    	   topicController.emitter.onNext(ServerSentEvent.builder(topicsList).id(UUID.randomUUID().toString()).build());
    	   lasttopicsListsize=topicsList.getTopics().size();
    	   }
        
    }
}
