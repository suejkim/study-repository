package com.sjkim.springbootjpa.repository;

import com.sjkim.springbootjpa.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
