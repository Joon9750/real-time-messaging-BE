package com.example.real_chat.service.global;

import com.example.real_chat.entity.room.ChatRoom;
import com.example.real_chat.entity.rootclient.RootClient;
import com.example.real_chat.entity.user.User;
import com.example.real_chat.entity.userchatroom.UserChatRoom;
import com.example.real_chat.repository.RoomRepository;
import com.example.real_chat.repository.RootClientRepository;
import com.example.real_chat.repository.UserChatRoomRepository;
import com.example.real_chat.repository.UserRepository;
import com.example.real_chat.service.builder.ServiceTestDataBuilder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public abstract class ServiceTest {

    @Mock protected RoomRepository roomRepository;
    @Mock protected UserRepository userRepository;
    @Mock protected RootClientRepository rootClientRepository;
    @Mock protected UserChatRoomRepository userChatRoomRepository;

    protected RootClient rootClient = ServiceTestDataBuilder.createRootClient();
    protected ChatRoom chatRoom = ServiceTestDataBuilder.createChatRoom(rootClient);
    protected User user = ServiceTestDataBuilder.createUser(rootClient);
    protected UserChatRoom userChatRoom = ServiceTestDataBuilder.createUserChatRoom(user, chatRoom);
    protected List<UserChatRoom> userChatRoomList = ServiceTestDataBuilder.createUserChatRooms();
}
