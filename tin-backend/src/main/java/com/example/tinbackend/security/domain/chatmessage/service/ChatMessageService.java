package com.example.tinbackend.security.domain.chatmessage.service;

import com.example.tinbackend.security.domain.chatmessage.dto.ChatMessageDto;
import com.example.tinbackend.security.domain.chatmessage.dto.SendChatMessageRequest;
import com.example.tinbackend.security.domain.chatmessage.entity.ChatMessageEntity;
import com.example.tinbackend.security.domain.chatmessage.repository.ChatMessageRepository;
import com.example.tinbackend.security.domain.travelgroup.entity.TravelGroupEntity;
import com.example.tinbackend.security.domain.travelgroup.repository.TravelGroupRepository;
import com.example.tinbackend.security.domain.user.entity.UserEntity;
import com.example.tinbackend.security.domain.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatMessageService {


    private final ChatMessageRepository chatMessageRepository;
    private final UserRepository userRepository;
    private final TravelGroupRepository travelGroupRepository;

    public ChatMessageService(ChatMessageRepository chatMessageRepository, UserRepository userRepository, TravelGroupRepository travelGroupRepository) {
        this.chatMessageRepository = chatMessageRepository;
        this.userRepository = userRepository;
        this.travelGroupRepository = travelGroupRepository;
    }

    public List<ChatMessageEntity> findAllByGroupId(Long groupId) {
        return chatMessageRepository.findAllByGroup_Id(groupId);
    }

    public void saveMessage(ChatMessageEntity message) {
        chatMessageRepository.save(message);
    }

    public List<ChatMessageDto> getGroupMessages(Long groupId) {
        return chatMessageRepository.getGroupMessages(groupId);
    }

    public void createChatMessage(SendChatMessageRequest sendChatMessageRequest) {
        TravelGroupEntity travelGroupEntity = travelGroupRepository.findById(sendChatMessageRequest.getGroupId())
                .orElseThrow(() -> new RuntimeException(String.format("Group with id %s not found", sendChatMessageRequest.getGroupId())));
        UserEntity userEntity = userRepository.findByUsername(sendChatMessageRequest.getAuthor())
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username: %s not found", sendChatMessageRequest.getAuthor())));
        ChatMessageEntity message = ChatMessageEntity.builder()
                .group(travelGroupEntity)
                .user(userEntity)
                .message(sendChatMessageRequest.getMessage())
                .time(LocalDateTime.now())
                .build();
        chatMessageRepository.save(message);
    }
}
