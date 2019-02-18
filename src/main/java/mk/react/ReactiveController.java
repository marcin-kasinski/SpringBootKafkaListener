package mk.react;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
//@EnableWebFlux
public class ReactiveController {

@Autowired
	ReactiveHandler handler;


@Bean
public RouterFunction<ServerResponse> routes(ReactiveHandler postController) {
    return route(GET("/findPerson"), postController::findPerson)
    	.andRoute(GET("/findAll"), postController::findAll)
        //.andRoute(POST("/posts"), postController::create)
        //.andRoute(GET("/posts/{id}"), postController::get)
        //.andRoute(PUT("/posts/{id}"), postController::update)
        //.andRoute(DELETE("/posts/{id}"), postController::delete)
        
        
        ;
}



}