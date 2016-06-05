package com.maxcheung;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ClientCodeRepository extends CrudRepository<ClientCode, Long> {

    List<ClientCode> findByGroupKey(String groupKey);
}
