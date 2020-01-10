package com.example.tinbackend.security.domain.chatmessage.repository.fragment;

import com.example.tinbackend.security.domain.chatmessage.dto.ChatMessageDto;

import java.util.List;

public interface ChatMessageRepositoryFragment {
    List<ChatMessageDto> getGroupMessages(Long groupId);
}
