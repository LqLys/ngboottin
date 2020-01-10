package com.example.tinbackend.security.domain.user.repository;

import com.example.tinbackend.security.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
    UserEntity getById(Long id);

    List<UserEntity> findAllByIdIn(List<Long> debtors);
    Optional<UserEntity> findByUsername(String username);
}
