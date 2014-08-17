package spring.bean.initializing;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class SpringInitializingBean implements InitializingBean {

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("SpringInitializingBean->afterPropertiesSet()");
	}

}
