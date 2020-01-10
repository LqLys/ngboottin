package com.example.tinbackend.security.domain.usertravelgroup.entity;

import com.example.tinbackend.security.domain.chatmessage.entity.ChatMessageEntity;
import com.example.tinbackend.security.domain.expense.entity.ExpenseEntity;
import com.example.tinbackend.security.domain.travelgroup.entity.TravelGroupEntity;
import com.example.tinbackend.security.domain.user.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "USER_TRAVEL_GROUP")
public class UserTravelGroupEntity {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "TRAVEL_GROUP_ID")
    private TravelGroupEntity travelGroup;

    @OneToMany(mappedBy = "userTravelGroup", cascade = CascadeType.ALL)
    private List<ExpenseEntity> expenses;

    @Column(name = "EXPENSES_PARTICIPATION")
    private BigDecimal expensesParticipation;

    @Column(name = "SETTLED_BALANCE")
    private BigDecimal settledBalance;


}
