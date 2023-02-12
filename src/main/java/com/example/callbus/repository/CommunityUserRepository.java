package com.example.callbus.repository;

import com.example.callbus.entity.CommunityUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommunityUserRepository extends JpaRepository<CommunityUser, Long> {
    Optional<CommunityUser> findCommunityUserByAccountId(String accountId);
}
