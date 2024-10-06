package com.mondi.FocusApp.repo;

import com.mondi.FocusApp.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findByUserId(Long userId);

    @Query("SELECT s FROM Session s WHERE s.user.id = :userId AND DATE(s.startTime) = :date")
    List<Session> findSessionsByUserIdAndDate(@Param("userId") Long userId, @Param("date") LocalDate date);
}

