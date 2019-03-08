package xyz.mhmm.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
public class WebApplication implements WebApplicationInitializer {

	@Override /* web.xml */
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.setServletContext(servletContext);
		context.register(WebConfig.class);
		context.refresh();

		DispatcherServlet dispatcherServlet = new DispatcherServlet(context);
		ServletRegistration.Dynamic app = servletContext.addServlet("app", dispatcherServlet);
		app.setLoadOnStartup(1);
		app.addMapping("/");
		app.setInitParameter("throwExceptionIfNoHandlerFound", "true");
		app.setAsyncSupported(true); // websocket에서 클라이언트가 2명이상일때 동시에 데이터 전송시 제어하기위함..

		FilterRegistration charEncodingFilterReg = servletContext.addFilter("CharacterEncodingFilter",
				CharacterEncodingFilter.class);
		charEncodingFilterReg.setInitParameter("encoding", "UTF-8");
		charEncodingFilterReg.setInitParameter("forceEncoding", "true");
		charEncodingFilterReg.addMappingForUrlPatterns(null, true, "/*");

	}

}