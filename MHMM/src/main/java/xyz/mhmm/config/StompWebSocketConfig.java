package xyz.mhmm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class StompWebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/websock").withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker("/publish");
		// 메세지 브로커가 구독하고 있는 클라이언트에게 메시지 전달 주소
		registry.setApplicationDestinationPrefixes("/subscribe");
		// 클라이언트 -> 서버 api주소
	}

	// http://host:port/{path-to-sockjs-endpoint}/{server-id}/{session-id}/{transport}
}