package com.example.book.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.example.book.handler.WebSocketHandler;

@Configuration
@EnableWebSocket// 웹소켓 활성화
public class WebSocketConfig implements WebSocketConfigurer{
	@Autowired
//	private DevLogWebSocketHandler devLogWebSocketHandler;
	private WebSocketHandler webSocketHandler;
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		// WebSocketHandler를 추가
//		registry.addHandler(devLogWebSocketHandler, "/chating");
		registry.addHandler(webSocketHandler, "/chating")
				.setAllowedOriginPatterns("http://localhost:3000");;
	}
}
