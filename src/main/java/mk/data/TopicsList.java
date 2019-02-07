package mk.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class TopicsList implements Serializable{

	
	
	String id;
public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

List<String> topics;
	
	public TopicsList() {
		topics = new ArrayList<String>();
	}

	public List<String> getTopics() {
		return topics;
	}

	public void setTopics(List<String> topics) {
		this.topics = topics;
	}

	public void addTopic(String topic)
	{
		
		System.out.println("addTopic "+topic);
		topics.add(topic);
		
	}
	
	
}
