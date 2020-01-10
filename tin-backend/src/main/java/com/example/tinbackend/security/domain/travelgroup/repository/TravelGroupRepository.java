package com.example.tinbackend.security.domain.travelgroup.repository;

import com.example.tinbackend.security.domain.travelgroup.entity.TravelGroupEntity;
import com.example.tinbackend.security.domain.travelgroup.repository.fragment.TravelGroupRepositoryFragment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TravelGroupRepository extends JpaRepository<TravelGroupEntity, Long>, TravelGroupRepositoryFragment {

    List<TravelGroupEntity> findAllById(Long id);

    List<TravelGroupEntity> findAllByEndDate(LocalDate currentDate);
}
