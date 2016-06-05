package datatable.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import datatable.domain.ClientCode;
import datatable.domain.ClientCodeValue;
import datatable.model.ClientCodeDTO;
import datatable.repository.ClientCodeRepository;

@Service
public class ClientCodeServiceImpl implements ClientCodeService{

	@Autowired
	ClientCodeRepository repository;


	@Override
	public Map<String, List<ClientCodeDTO>> generic(String clientKey) {
		Map<String, List<ClientCodeDTO>> codeMap = new HashMap<String, List<ClientCodeDTO>>();
		List<ClientCode> clientCodes = repository.findByClientKey(clientKey);
		for (ClientCode clientCode : clientCodes) {
			codeMap.put(clientCode.getGroupKey(), convertDTO(clientCode));
		}
		return codeMap;
	}

	@Override
	public Map<String, List<ClientCodeDTO>> generic(String clientKey, String groupCd) {
		Map<String, List<ClientCodeDTO>> codeMap = new HashMap<String, List<ClientCodeDTO>>();
		List<ClientCode> clientCodes = repository.findByClientKeyAndGroupKey(clientKey,groupCd);
		for (ClientCode clientCode : clientCodes) {
			codeMap.put(clientCode.getGroupKey(), convertDTO(clientCode));
		}
		return codeMap;
	}
	

	private List<ClientCodeDTO> convertDTO(ClientCode clientCode) {
		List<ClientCodeDTO> ClientCodeDTOs = new ArrayList<ClientCodeDTO>();
		List<ClientCodeValue> clientCodeValues = clientCode.getClientCodeValues();
		for (ClientCodeValue clientCodeValue : clientCodeValues) {
			ClientCodeDTO clientCodeDTO = new ClientCodeDTO();
			clientCodeDTO.setCode(clientCodeValue.getCodeKey());
			clientCodeDTO.setLabel(clientCodeValue.getCodeValue());
			ClientCodeDTOs.add(clientCodeDTO);
		}
		return ClientCodeDTOs;
	}


	private ClientCode createClientCode(String clientKey, String groupKey) {
		ClientCode clientCode = new ClientCode();
		clientCode.setClientKey(clientKey);
		clientCode.setGroupKey(groupKey);
		List<ClientCodeValue> clientCodeValues = new ArrayList<ClientCodeValue>();
		return clientCode;
	}

	private ClientCodeValue createClientCodeValve(ClientCode clientCode, String codeKey, String codeValue) {
		ClientCodeValue clientCodeValue = new ClientCodeValue();
		clientCodeValue.setClientCode(clientCode);
		clientCodeValue.setCodeKey(codeKey);
		clientCodeValue.setCodeValue(codeValue);
		return clientCodeValue;
	}

}
