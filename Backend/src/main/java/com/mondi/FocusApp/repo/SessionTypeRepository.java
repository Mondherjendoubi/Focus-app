package com.mondi.FocusApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mondi.FocusApp.model.SessionType;

@Repository
public interface SessionTypeRepository extends JpaRepository<SessionType, Long> {

}
