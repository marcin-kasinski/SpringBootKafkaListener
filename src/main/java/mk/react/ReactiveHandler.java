package mk.react;


import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.fasterxml.jackson.annotation.JsonProperty;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import mk.WorkUnit;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Component
public class ReactiveHandler {

	
	public Mono<ServerResponse> findPerson(ServerRequest req) {
//		String id = req.pathVariable("id");
		
		WorkUnit wu= new WorkUnit("parentId","spanTraceId","id","definition");
    	wu.setDefinition("mkdefinition");
    	wu.setId("mkid");
    	wu.setParentId("mkparentId");
    	wu.setSpanTraceId("mkspanTraceId");
		
		
		return ServerResponse.ok().body(Mono.just(wu),
				WorkUnit.class);
	}

	public Mono<ServerResponse> findAll(ServerRequest req) {
		
		WorkUnit wu1= new WorkUnit("parentId1","spanTraceId1","id1","definition1");
		WorkUnit wu2= new WorkUnit("parentId2","spanTraceId2","id1","definition2");
		WorkUnit wu3= new WorkUnit("parentId3","spanTraceId3","id1","definition3");
		WorkUnit wu4= new WorkUnit("parentId4","spanTraceId4","id1","definition4");
		WorkUnit wu5= new WorkUnit("parentId5","spanTraceId5","id1","definition5");

		
		return ServerResponse.ok().body(
				Flux.just(      wu1,wu2,wu3,wu4,wu5 ), WorkUnit.class);
}
}
