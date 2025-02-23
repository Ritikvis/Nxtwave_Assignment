package com.Nxtwavw.Assignment.Controller;

import com.Nxtwavw.Assignment.Models.Manager;
import com.Nxtwavw.Assignment.Service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/managers")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @PostMapping("/create")
    public Manager createManager(@RequestBody Manager manager) {
        return managerService.createManager(manager.getFullName(), manager.isActive());
    }
}
