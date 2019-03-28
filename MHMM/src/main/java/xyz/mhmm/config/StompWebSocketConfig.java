package xyz.mhmm.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@EnableWebSocketMessageBroker
@Configuration
@PropertySource("classpath:application.properties")
public class StompWebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Value("${rabbitmq.host}")
	private String rabbitmqHost;

	@Value("${rabbitmq.port}")
	private int rabbitmqPort;

	@Value("${rabbitmq.id}")
	private String rabbitmqId;

	@Value("${rabbitmq.pw}")
	private String rabbitmqPw;

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/stomp-chat").setAllowedOrigins("*").withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.setApplicationDestinationPrefixes("/publish");

		registry.enableStompBrokerRelay("/app")
			.setRelayHost(rabbitmqHost)
			.setRelayPort(rabbitmqPort)
			.setClientLogin(rabbitmqId).
			setClientPasscode(rabbitmqPw);
	}
}