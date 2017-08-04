package com.maxcheung.funddata.repository;

import org.springframework.data.repository.CrudRepository;

import com.maxcheung.funddata.domain.UserData;

public interface UserRepository extends CrudRepository<UserData, Long> {

}
