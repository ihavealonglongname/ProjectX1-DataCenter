package ctrl;

import java.util.concurrent.atomic.AtomicLong;

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
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
}
