package com.sjkim.springbootjpa.repository;

import com.sjkim.springbootjpa.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByName(String name);

    @Query("select distinct u from User u join fetch u.userHistories")
    List<User> findByDistinctUserWithFetchJoin();

    @EntityGraph(attributePaths = {"userHistories"})
    List<User> findAll();

}
