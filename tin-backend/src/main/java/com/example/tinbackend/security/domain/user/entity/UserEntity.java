package com.example.tinbackend.security.domain.user.entity;

import com.example.tinbackend.security.domain.chatmessage.entity.ChatMessageEntity;
import com.example.tinbackend.security.domain.role.entity.RoleEntity;
import com.example.tinbackend.security.domain.travelgroup.entity.TravelGroupEntity;
import com.example.tinbackend.security.domain.usertravelgroup.entity.UserTravelGroupEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "USERS")
public class UserEntity {


    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    @Id
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "USERNAME", unique = true)
    private String username;

    @Column(name = "PASSWORD")
    private String password;


    @Column(name = "EMAIL", unique = true)
    private String email;

    @Column(name = "ACTIVE")
    private Boolean active;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ROLE",
    joinColumns = {@JoinColumn(name = "USER_ID")},
    inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")})
    private List<RoleEntity> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ChatMessageEntity> chatMessages;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserTravelGroupEntity> groupParticipation;

}
