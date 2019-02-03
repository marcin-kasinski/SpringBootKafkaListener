package mk;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WorkUnit {

    private  String id;
    private  String definition;
    private  String spanTraceId;
    private  String parentId;
    
	public void setId(String id) {
		this.id = id;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	public void setSpanTraceId(String spanTraceId) {
		this.spanTraceId = spanTraceId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentId() {
		return parentId;
	}

	@JsonCreator
    public WorkUnit(
    				@JsonProperty("parentId") String parentId,
                    @JsonProperty("spanTraceId") String spanTraceId,
                    @JsonProperty("id") String id,
                    @JsonProperty("definition") String definition
                    ) {

        this.parentId = parentId;
        this.spanTraceId = spanTraceId;
        this.id = id;
        this.definition = definition;
    }

    public String getId() {
        return id;
    }


    public String getDefinition() {
        return definition;
    }

    public String getSpanTraceId() {
		return spanTraceId;
	}

	@Override
	public String toString() {
		return "WorkUnit [id=" + id + ", definition=" + definition + ", spanTraceId=" + spanTraceId + ", parentId="
				+ parentId + "]";
	}

	
    
}