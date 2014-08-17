package spring.bean.initializingDisposable;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class SpringInitializingDisposableBean implements InitializingBean, DisposableBean {

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println(SpringInitializingDisposableBean.class.getName() + "->InitializingBean->afterPropertiesSet()");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println(SpringInitializingDisposableBean.class.getName() + "->DisposableBean->destroy()");
	}

}
