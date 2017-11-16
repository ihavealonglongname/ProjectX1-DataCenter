package ctrl;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import test03.test031.Greeting;

@RestController
public class AccountController {
	
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    
	@RequestMapping("/account")
	public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
		System.out.println("AABB");
		
//		String log4jPath = AccountController.class.getClassLoader().getResource("").getPath()+"log4j.properties";
//		System.out.println(log4jPath);
//		PropertyConfigurator.configure("D:/p-wang/2017/workspace/eclipse/ProjectX1-DataCenter/src/main/resources/log4j.properties");
		
		Logger logger  =  LoggerFactory.getLogger(AccountController.class);
		logger.debug("Let's celcebrate12345678--debug");
		logger.info("Let's celcebrate12345678--info");
		logger.warn("Let's celcebrate12345678--warn");
		logger.error("Let's celcebrate12345678--error");
		
        //Application.console(AccountController.class, "123456798");
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}
