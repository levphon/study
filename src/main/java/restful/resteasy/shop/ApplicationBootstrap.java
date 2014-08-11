package restful.resteasy.shop;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;

import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;

@WebListener
public class ApplicationBootstrap implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext context = event.getServletContext();
		ServletRegistration.Dynamic resteasyServlet = context.addServlet("resteasy", HttpServletDispatcher.class);
		resteasyServlet.addMapping("/restful/*");
		resteasyServlet.setInitParameter("javax.ws.rs.Application", "restful.resteasy.shop.ShoppingApplication");
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
	}

}
