package com.Nxtwavw.Assignment.Repository;

import com.Nxtwavw.Assignment.Models.Manager;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, String> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO managers (manager_id, full_name, is_active, created_at, updated_at) " +
            "VALUES (:managerId, :fullName, :isActive, NOW(), NOW())", nativeQuery = true)
    void insertManager(@Param("managerId") String managerId,
                       @Param("fullName") String fullName,
                       @Param("isActive") boolean isActive);

    boolean existsByManagerIdAndIsActive(String managerId, boolean isActive);
}
