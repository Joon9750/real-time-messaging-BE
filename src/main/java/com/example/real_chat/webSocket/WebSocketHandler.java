package com.example.real_chat.webSocket;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class WebSocketHandler extends TextWebSocketHandler {

    // sessionMap Key - UserInfo chatRoomId
    private final Map<String, Set<UserInfo>> sessionMap = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        UserInfo userInfo = extractUserInfo(session);

        sessionMap.computeIfAbsent(userInfo.getChatRoomId(), k -> new HashSet<>()).add(userInfo);
        sendMessageToChatRoom(userInfo.getChatRoomId(), userInfo.getName() + "님이 대화방에 들어오셨습니다.");
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {
        super.handleTextMessage(session, textMessage);
        UserInfo userInfo = extractUserInfo(session);

        String textMessagePayload = textMessage.getPayload();
        String message = userInfo.getName() + " : " + textMessagePayload;
        sendMessageToChatRoomExceptSelf(userInfo.getChatRoomId(), message, userInfo);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        UserInfo userInfo = extractUserInfo(session);

        Set<UserInfo> usersInChatRoom = sessionMap.get(userInfo.getChatRoomId());
        if (usersInChatRoom != null) {
            usersInChatRoom.removeIf(user -> user.getUserId().equals(userInfo.getUserId()));

            // 만약 해당 채팅방에 남아있는 유저가 없다면 채팅방 삭제
            if (usersInChatRoom.isEmpty()) {
                sessionMap.remove(userInfo.getChatRoomId());
            }
        }

        sendMessageToChatRoom(userInfo.getChatRoomId(), userInfo.getName() + "님이 대화방을 나가셨습니다.");
    }

    private void sendMessageToChatRoom(String chatRoomId, String message) throws IOException {
        Set<UserInfo> users = sessionMap.get(chatRoomId);

        if (users != null) {
            for (UserInfo user : users) {
                try {
                    user.getSession().sendMessage(new TextMessage(message));
                } catch (IOException e) {
                    // 오류 처리
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private void sendMessageToChatRoomExceptSelf(String chatRoomId, String message, UserInfo userInfo) throws IOException {
        Set<UserInfo> users = sessionMap.get(chatRoomId);
        WebSocketSession session = userInfo.getSession();

        if (users != null) {
            for (UserInfo user : users) {
                try {
                    if(!session.getId().equals(user.getSession().getId())) {
                        user.getSession().sendMessage(new TextMessage(message));
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private UserInfo extractUserInfo(WebSocketSession session) throws IOException {
        try {
            String name = session.getHandshakeHeaders().getFirst("name");
            String clientIdInString = session.getHandshakeHeaders().getFirst("userId");
            String chatRoomId = session.getHandshakeHeaders().getFirst("chatRoomId");

            // 필수 헤더 값이 누락된 경우 예외 처리
            if (name == null || clientIdInString == null || chatRoomId == null) {
                session.sendMessage(new TextMessage("Error: Missing required headers."));
                session.close();
                return null; // 조기 종료
            }

            long clientId;
            try {
                clientId = Long.parseLong(clientIdInString);
            } catch (NumberFormatException e) {
                session.sendMessage(new TextMessage("Error: Invalid userId format: " + clientIdInString));
                session.close();
                return null; // 조기 종료
            }

            return new UserInfo(name, clientId, chatRoomId, session);

        } catch (Exception e) {
            session.sendMessage(new TextMessage("Error: Unexpected server error."));
            session.close();
            throw new RuntimeException("WebSocket header processing failed", e);
        }
    }
}