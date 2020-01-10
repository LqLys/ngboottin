package com.example.tinbackend.security.domain.chatmessage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendChatMessageRequest {

    @NotNull
    private Long groupId;
    @NotBlank
    private String author;
    @NotBlank
    private String message;
}
