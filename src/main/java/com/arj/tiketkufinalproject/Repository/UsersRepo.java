package com.arj.tiketkufinalproject.Repository;

import com.arj.tiketkufinalproject.Entity.UsersEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsersRepo extends JpaRepository<UsersEntity, UUID> {
    Page<UsersEntity> findAll(Pageable pageable);

    Optional<UsersEntity> findByEmail(String email);
}
