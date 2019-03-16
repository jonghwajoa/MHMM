package xyz.mhmm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@EnableWebSocketMessageBroker
@Configuration
public class StompWebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/stomp-chat").setAllowedOrigins("*").withSockJS();
		// handshake와 통신을 담당할 endpoint를 지정한다.
	}

	// Application 내부에서 사용할 path를 지정할 수 있다.
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.setApplicationDestinationPrefixes("/publish"); // client에서 SEND 요청을 처리한다
		registry.enableSimpleBroker("/subscribe");
	}

	/**
	 * /topic : 암시적으로 1:N 전파를 의미한다. /queue : 암시적으로 1:1 전파를 의미한다.
	 */
}