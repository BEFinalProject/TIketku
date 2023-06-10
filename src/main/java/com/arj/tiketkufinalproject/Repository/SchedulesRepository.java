package com.arj.tiketkufinalproject.Repository;

import com.arj.tiketkufinalproject.Model.SchedulesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface SchedulesRepository extends JpaRepository<SchedulesEntity, UUID> {
}
