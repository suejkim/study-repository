package com.sjkim.springbootjpa.repository;

import com.sjkim.springbootjpa.domain.ActionType;
import com.sjkim.springbootjpa.domain.UserHistory;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserHistoryRepository extends PagingAndSortingRepository<UserHistory, Long> {

    @Query("select h from UserHistory h join fetch h.user")
    List<UserHistory> findByUserHistoryWithFetchJoin();

    @EntityGraph(attributePaths = {"user"})
    List<UserHistory> findAll();

    List<UserHistory> findByType(ActionType type);
}
