package com.arj.tiketkufinalproject.Repository;

import com.arj.tiketkufinalproject.Model.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<PriceEntity, Integer>{
}
