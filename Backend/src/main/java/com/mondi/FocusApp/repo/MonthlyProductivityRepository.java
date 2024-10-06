package com.mondi.FocusApp.repo;


import com.mondi.FocusApp.model.MonthlyProductivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MonthlyProductivityRepository extends JpaRepository<MonthlyProductivity, Long> {}
