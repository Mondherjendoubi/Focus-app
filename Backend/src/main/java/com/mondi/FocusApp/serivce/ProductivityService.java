package com.mondi.FocusApp.serivce;

import com.mondi.FocusApp.model.DailyProductivity;
import com.mondi.FocusApp.model.MonthlyProductivity;
import com.mondi.FocusApp.model.WeeklyProductivity;
import com.mondi.FocusApp.repo.DailyProductivityRepository;
import com.mondi.FocusApp.repo.MonthlyProductivityRepository;
import com.mondi.FocusApp.repo.WeeklyProductivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductivityService {

    @Autowired
    private DailyProductivityRepository dailyProductivityRepository;

    @Autowired
    private WeeklyProductivityRepository weeklyProductivityRepository;

    @Autowired
    private MonthlyProductivityRepository monthlyProductivityRepository;

    public DailyProductivity calculateDailyProductivity(Long user) {
        // Logic for calculating daily productivity
        return new DailyProductivity(); // Replace with actual logic
    }

    public WeeklyProductivity calculateWeeklyProductivity(Long user) {
        // Logic for calculating weekly productivity
        return new WeeklyProductivity(); // Replace with actual logic
    }

    public MonthlyProductivity calculateMonthlyProductivity(Long user) {
        // Logic for calculating monthly productivity
        return new MonthlyProductivity(); // Replace with actual logic
    }

    public DailyProductivity createDailyProductivity(DailyProductivity dailyProductivity) {
        return dailyProductivityRepository.save(dailyProductivity);
    }

}

