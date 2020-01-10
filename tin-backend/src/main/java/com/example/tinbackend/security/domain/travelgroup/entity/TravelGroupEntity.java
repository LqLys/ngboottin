package com.example.tinbackend.security.domain.travelgroup.entity;


import com.example.tinbackend.security.domain.chatmessage.entity.ChatMessageEntity;
import com.example.tinbackend.security.domain.expense.entity.ExpenseEntity;
import com.example.tinbackend.security.domain.user.entity.UserEntity;
import com.example.tinbackend.security.domain.usertravelgroup.entity.UserTravelGroupEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "TRAVEL_GROUP")
public class TravelGroupEntity {


    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    @Id
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESTINATION")
    private String destination;

    @Column(name = "START_DATE")
    private LocalDate startDate;

    @Column(name = "END_DATE")
    private LocalDate endDate;

    @Column(name = "GROUP_STATUS")
    @Enumerated(EnumType.STRING)
    private GroupStatus groupStatus;

    @OneToMany(mappedBy = "travelGroup", cascade = CascadeType.ALL)
    private List<UserTravelGroupEntity> groupParticipation;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<ChatMessageEntity> chatMessages;

}
