package mk;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBinding(WorkUnitsSink.class)
public class IntegrationConfiguration {}