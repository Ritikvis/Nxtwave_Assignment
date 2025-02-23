package com.Nxtwavw.Assignment.Repository;

import com.Nxtwavw.Assignment.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    List<User> findByUserId(String userId);
    List<User> findByMobNum(String mobNum);
    List<User> findByManagerManagerId(String managerId);
    boolean existsByMobNum(String mobNum);
    boolean existsByPanNum(String panNum);
}