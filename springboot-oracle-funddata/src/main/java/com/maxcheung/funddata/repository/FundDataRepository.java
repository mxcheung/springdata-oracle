package com.maxcheung.funddata.repository;

import org.springframework.data.repository.CrudRepository;

import com.maxcheung.funddata.domain.FundData;

public interface FundDataRepository extends CrudRepository<FundData, Long> {

}
