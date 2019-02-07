package mk;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DescribeTopicsResult;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.TopicDescription;
import org.apache.kafka.clients.admin.TopicListing;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.Node;
import org.apache.kafka.common.TopicPartitionInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import mk.data.TopicsList;


@Component
public class KafkaManager {
	
	@Value("${app.kafkaadmin.bootsrtapservers}")	
	String bootsrtapservers;

	 AdminClient adminClient;
		public KafkaManager() {
			super();
		    System.out.println("KafkaManager constructor");
		    System.out.println("bootsrtapservers "+bootsrtapservers);
		}

		public void init() {

		    System.out.println("init -------------------------------------------------------------------------------------------- bootsrtapservers "+bootsrtapservers);
		    
			Map props = new HashMap<>();
			//props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"k8smaster:9092");
			props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootsrtapservers);
			 adminClient = AdminClient.create(props);
		        adminClient.describeCluster();
		}

	public TopicsList getTopics() throws InterruptedException, ExecutionException
	{
        System.out.println("getTopics");

		ListTopicsResult listTopicsResult = adminClient.listTopics();
		Map<String, TopicListing> availableTopics = listTopicsResult.namesToListings().get();
        System.out.println(availableTopics);
        
        TopicsList topicsList= new TopicsList();
        topicsList.setId("mkid");
        
        
        
        
        System.out.println("listing");
        
        for (Entry<String, TopicListing> entry :  listTopicsResult.namesToListings().get().entrySet()    ) {
			///System.out.println("ISR "+ isr);
			
        	TopicListing  topicListing=entry.getValue();
            System.out.println("topicListing "+topicListing);
			topicsList.addTopic(topicListing.name());
		}
        
        return topicsList;
	}
	
	
	public void describeTopic(String topicName) throws InterruptedException, ExecutionException
	{
        System.out.println("describeTopic");
        List<String> topicNames  = new ArrayList<String>();
        
		//Collection<String> topicNames = null;
		topicNames.add(topicName);
		DescribeTopicsResult describeTopicsResult = adminClient.describeTopics(topicNames);
//		Map<String, TopicListing> availableTopics = 
		//				describeTopicsResult.;

		int size=describeTopicsResult.values().keySet().size();

	    System.out.println("keysize  " +size);
		
				for (Entry<String, KafkaFuture<TopicDescription>> entry : describeTopicsResult.values().entrySet())
				{
				    System.out.println(entry.getKey() + "/" + entry.getValue());
				    TopicDescription  topicDescription=entry.getValue().get();
				    //topicDescription.partitions()
				    
				    List<TopicPartitionInfo> partitions = topicDescription.partitions();
				    
				    System.out.println("Partitions");
				    
					for (TopicPartitionInfo topicPartitionInfo : partitions) {
						System.out.println("Partition "+topicPartitionInfo);

					    
						/////////////////////////////////////////////////////////////////
						System.out.println("Replicas");

						for (Node replica : topicPartitionInfo.replicas()) {
							System.out.println("Replica "+ replica);
						}
						/////////////////////////////////////////////////////////////////
					   
						/////////////////////////////////////////////////////////////////
						System.out.println("ISRs");

						for (Node isr : topicPartitionInfo.isr()) {
							System.out.println("ISR "+ isr);
						}
						/////////////////////////////////////////////////////////////////
					   
						
						
					}
				    
				    
				    
				    
				    
				}
				
       // System.out.println(availableTopics);
	}
	
}
