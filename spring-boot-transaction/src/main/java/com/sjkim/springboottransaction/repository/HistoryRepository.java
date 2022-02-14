package com.sjkim.springboottransaction.repository;

import com.sjkim.springboottransaction.model.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Long> {
}
