package xyz.mhmm.config;

import java.util.Arrays;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import xyz.mhmm.interceptor.AuthInterceptor;
import xyz.mhmm.utils.FileUpload;

@Configuration
@ComponentScan("xyz.mhmm")
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

	private ApplicationContext applicationContext;

	public WebConfig(ApplicationContext applicationContex) {
		this.applicationContext = applicationContex;
	}

	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		// 디스크에 임시 파일을 생성하기 전에 메모리에 보관할 수 있는 최대 바이트 크기
		resolver.setMaxInMemorySize(FileUpload.MAX_IN_MEMORY_SIZE);
		resolver.setMaxUploadSize(FileUpload.MAX_UPLOAD_SIZE);
		resolver.setMaxUploadSizePerFile(FileUpload.MAX_UPLOAD_SIZE_PER_FILE);
		return resolver;
	}

	@Bean
	public SpringResourceTemplateResolver templateResolver() {
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		templateResolver.setApplicationContext(applicationContext);
		templateResolver.setPrefix("/WEB-INF/views/");
		templateResolver.setSuffix(".html");
		templateResolver.setCharacterEncoding("UTF-8");
		return templateResolver;
	}

	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		templateEngine.setEnableSpringELCompiler(true);
		return templateEngine;
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		ThymeleafViewResolver resolver = new ThymeleafViewResolver();
		resolver.setTemplateEngine(templateEngine());
		resolver.setCharacterEncoding("UTF-8");
		resolver.setContentType("text/html;charset=UTF-8");
		registry.viewResolver(resolver);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("/resources/css/").setCachePeriod(31556926);
		registry.addResourceHandler("/js/**").addResourceLocations("/resources/js/").setCachePeriod(31556926);
		registry.addResourceHandler("/fonts/**").addResourceLocations("/resources/fonts/").setCachePeriod(31556926);
		registry.addResourceHandler("/img/**").addResourceLocations("file:c:/gitfolder/mhmm/img/")
				.setCachePeriod(31556926);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/**").excludePathPatterns(Arrays.asList("/",
				"/auth/*", "/api/auth/*", "/js/**", "/css/**", "/img/use/**", "/fonts/**", "/email/**"));
	}
}