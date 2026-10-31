package com.clinic.academicbridgedemo.Controllers;

import com.clinic.academicbridgedemo.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    @Autowired
    private DashboardService dashboardService;
    @GetMapping("/summary")
    private Map<String, Long> summary(){
        return dashboardService.getSummary();
    }
}
