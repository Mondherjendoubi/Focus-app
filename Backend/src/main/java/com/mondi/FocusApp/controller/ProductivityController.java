package com.mondi.FocusApp.controller;

import com.mondi.FocusApp.model.DailyProductivity;
import com.mondi.FocusApp.model.MonthlyProductivity;
import com.mondi.FocusApp.model.WeeklyProductivity;
import com.mondi.FocusApp.serivce.ProductivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/productivity")
public class ProductivityController {

    @Autowired
    private ProductivityService productivityService;

    @GetMapping("/daily/{userId}")
    public DailyProductivity getDailyProductivity(@PathVariable Long userId) {
        return productivityService.calculateDailyProductivity(userId);
    }

    @GetMapping("/weekly/{userId}")
    public WeeklyProductivity getWeeklyProductivity(@PathVariable Long userId) {
        return productivityService.calculateWeeklyProductivity(userId);
    }

    @GetMapping("/monthly/{userId}")
    public MonthlyProductivity getMonthlyProductivity(@PathVariable Long userId) {
        return productivityService.calculateMonthlyProductivity(userId);
    }

    @PostMapping("/daily")
    public ResponseEntity<DailyProductivity> createDailyProductivity(@RequestBody DailyProductivity dailyProductivity) {
        DailyProductivity savedProductivity = productivityService.createDailyProductivity(dailyProductivity);
        return new ResponseEntity<>(savedProductivity, HttpStatus.CREATED);
    }

}
