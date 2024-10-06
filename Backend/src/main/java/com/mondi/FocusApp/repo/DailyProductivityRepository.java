package com.mondi.FocusApp.repo;

import com.mondi.FocusApp.model.DailyProductivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DailyProductivityRepository extends JpaRepository<DailyProductivity, Long> {}
