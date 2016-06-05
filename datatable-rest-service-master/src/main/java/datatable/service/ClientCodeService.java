package datatable.service;

import java.util.List;
import java.util.Map;

import datatable.model.ClientCodeDTO;

public interface ClientCodeService {

	Map<String, List<ClientCodeDTO>> generic(String clientKey);

	Map<String, List<ClientCodeDTO>> generic(String clientKey, String groupCd);

}
