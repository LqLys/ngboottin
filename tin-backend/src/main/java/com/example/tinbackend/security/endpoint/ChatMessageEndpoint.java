package com.example.tinbackend.security.endpoint;

import com.example.tinbackend.security.domain.chatmessage.dto.ChatMessageDto;
import com.example.tinbackend.security.domain.chatmessage.dto.SendChatMessageRequest;
import com.example.tinbackend.security.domain.chatmessage.service.ChatMessageService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/chat-message")
public class ChatMessageEndpoint {


    private final ChatMessageService chatMessageService;

    public ChatMessageEndpoint(ChatMessageService chatMessageService) {
        this.chatMessageService = chatMessageService;
    }


    @GetMapping("/{groupId}")
    public List<ChatMessageDto> getChatMessagesByGroupId(@PathVariable("groupId") Long groupId){
        return chatMessageService.getGroupMessages(groupId);
    }

    @PostMapping("")
    public void createChatMessage(@Valid @RequestBody SendChatMessageRequest sendChatMessageRequest){
        chatMessageService.createChatMessage(sendChatMessageRequest);
    }
}
