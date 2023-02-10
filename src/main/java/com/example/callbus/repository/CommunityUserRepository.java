package com.example.callbus.repository;

import com.example.callbus.entity.CommunityUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityUserRepository extends JpaRepository<CommunityUser, Long> {
}
