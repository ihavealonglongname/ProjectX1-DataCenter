package ctrl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	
	public static void console(Class<?> cls, String info) {
		Logger logger  =  LoggerFactory.getLogger(cls);
		logger.debug("Let's celcebrate !www");
	}
    public static void main(String[] args) {        
        SpringApplication.run(Application.class, args);
    }
}
