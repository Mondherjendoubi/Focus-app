package com.mondi.FocusApp.repo;

import com.mondi.FocusApp.model.WeeklyProductivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WeeklyProductivityRepository extends JpaRepository<WeeklyProductivity, Long> {}


