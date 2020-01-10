package com.example.tinbackend.security.domain.chatmessage.repository;

import com.example.tinbackend.security.domain.chatmessage.entity.ChatMessageEntity;
import com.example.tinbackend.security.domain.chatmessage.repository.fragment.ChatMessageRepositoryFragment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessageEntity, Long>, ChatMessageRepositoryFragment {

    List<ChatMessageEntity> findAllByGroup_Id(Long groupId);
}
