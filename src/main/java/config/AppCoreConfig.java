package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(value = { WebConfig.class } )
@Configuration
public class AppCoreConfig {
    
}