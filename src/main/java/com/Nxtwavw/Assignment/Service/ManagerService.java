package com.Nxtwavw.Assignment.Service;

import com.Nxtwavw.Assignment.Models.Manager;
import com.Nxtwavw.Assignment.Repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    public Manager createManager(String fullName, boolean isActive) {
        String managerId = generateUUID();
        Manager manager = new Manager(managerId, fullName, isActive);
        return managerRepository.save(manager);
    }


    public String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
