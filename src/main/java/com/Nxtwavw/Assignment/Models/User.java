package com.Nxtwavw.Assignment.Models;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id", updatable = false, nullable = false)
    private String userId;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "mob_num", nullable = false, unique = true)
    private String mobNum;

    @Column(name = "pan_num", nullable = false, unique = true)
    private String panNum;

    @ManyToOne
    @JoinColumn
    private Manager manager;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    public  User(){};


    public User(String userId, String fullName, String mobNum, String panNum, Manager manager, boolean isActive, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.userId = userId;
        this.fullName = fullName;
        this.mobNum = mobNum;
        this.panNum = panNum;
        this.manager = manager;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobNum() {
        return mobNum;
    }

    public void setMobNum(String mobNum) {
        this.mobNum = mobNum;
    }

    public String getPanNum() {
        return panNum;
    }

    public void setPanNum(String panNum) {
        this.panNum = panNum;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}