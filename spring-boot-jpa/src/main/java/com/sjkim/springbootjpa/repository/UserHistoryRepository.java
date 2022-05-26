package com.sjkim.springbootjpa.repository;

import com.sjkim.springbootjpa.domain.UserHistory;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserHistoryRepository extends PagingAndSortingRepository<UserHistory, Long> {
}
