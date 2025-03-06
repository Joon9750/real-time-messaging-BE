package com.example.real_chat.webSocket;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;


public class WebSocketHandler extends TextWebSocketHandler {

    private final Map<String, WebSocketSession> sessionMap = new ConcurrentHashMap<>();
    private static StringTokenizer st;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        // 세션의 ID는 서버가 클라이언트와의 WebSocket 연결을 수립할 때 자동으로 생성
        String sessionId = session.getId();

        // 세션 저장
        sessionMap.put(sessionId, session);

        sessionMap.values().forEach(s -> {
            try {
                s.sendMessage(new TextMessage(sessionId + "님이 대화방에 들어오셨습니다."));
            } catch (IOException e) {
                throw new RuntimeException("message 전송 실패!!!");
            }
        });
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {
        String sessionId = session.getId();
        String[] sessionIdStrings = sessionId.split("-");

        // 대화창에 표시할 보낸 사람 아이디
        String senderId = sessionIdStrings[0];

        // 메시지 내용 파싱
        String textMessagePayload = textMessage.getPayload();

        // 대화방 전체에 메시지 전달
        sessionMap.values().forEach(s -> {
            try {
                // 자신을 제외한 참가자들에게 메시지 전달
                if (!sessionId.equals(s.getId())) {
                    s.sendMessage(new TextMessage(senderId + " : " + textMessagePayload));
                }

            } catch (IOException e) {
                throw new RuntimeException("message 전송 실패!!!");
            }
        });
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String sessionId = session.getId();

        sessionMap.remove(sessionId);

        sessionMap.values().forEach(s -> {
            try {
                s.sendMessage(new TextMessage(sessionId + "님이 대화방을 나가셨습니다."));
            } catch (IOException e) {
                throw new RuntimeException("message 전송 실패!!!");
            }
        });
    }
}