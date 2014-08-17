package spring.bean.jsr250;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

@Component
public class SpringJSR250Bean {

	@PostConstruct
	public void init() {
		System.out.println(SpringJSR250Bean.class.getName() + "->@PostConstruct");
	}

	@PreDestroy
	public void destroy() {
		System.out.println(SpringJSR250Bean.class.getName() + "->@PreDestroy");
	}

}
