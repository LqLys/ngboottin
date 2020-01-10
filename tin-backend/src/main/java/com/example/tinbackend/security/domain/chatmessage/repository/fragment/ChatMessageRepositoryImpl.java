package com.example.tinbackend.security.domain.chatmessage.repository.fragment;

import com.example.tinbackend.security.domain.chatmessage.dto.ChatMessageDto;
import com.example.tinbackend.security.domain.chatmessage.entity.QChatMessageEntity;
import com.example.tinbackend.security.domain.user.entity.QUserEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;

public class ChatMessageRepositoryImpl implements ChatMessageRepositoryFragment {

    private final JPAQueryFactory jpaQueryFactory;

    public ChatMessageRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<ChatMessageDto> getGroupMessages(Long groupId) {
        QChatMessageEntity message = QChatMessageEntity.chatMessageEntity;
        QUserEntity user = QUserEntity.userEntity;


        return jpaQueryFactory.select(Projections.constructor(ChatMessageDto.class,
                message.message, message.time, user.username))
                .from(message)
                .join(user).on(user.id.eq(message.user.id))
                .where(message.group.id.eq(groupId))
                .fetch();
    }
}
