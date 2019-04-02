package xyz.mhmm.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@PropertySource("classpath:application.properties")
public class MailConfig {

	@Value("{email.host}")
	private String host;

	@Value("{email.username}")
	private String username;

	@Value("{email.pw}")
	private String pw;

	@Value("{email.port}")
	private int port;

	@Bean
	public JavaMailSenderImpl mailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(host);
		mailSender.setUsername(username);
		mailSender.setPassword(pw);
		mailSender.setPort(port);
		Properties prop = new Properties();
		prop.setProperty("mail.smtp.auth", "true");
		prop.setProperty("mail.smtp.starttls.enable", "true");
		mailSender.setJavaMailProperties(prop);
		return mailSender;
	}
}
