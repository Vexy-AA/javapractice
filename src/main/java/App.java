import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.SpringApplication;

@ComponentScan(basePackageClasses = config.AppCoreConfig.class)
@EnableAutoConfiguration
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);        
    }
}