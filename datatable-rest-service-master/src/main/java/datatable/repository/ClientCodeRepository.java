package datatable.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import datatable.domain.ClientCode;

public interface ClientCodeRepository extends CrudRepository<ClientCode, Long> {

    List<ClientCode> findByClientKey(String clientKey);

    List<ClientCode> findByClientKeyAndGroupKey(String clientKey, String groupKey);

}
