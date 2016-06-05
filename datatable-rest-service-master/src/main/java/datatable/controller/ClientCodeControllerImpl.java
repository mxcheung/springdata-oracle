package datatable.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import datatable.controller.ClientCodeController;
import datatable.model.ClientCodeDTO;
import datatable.service.ClientCodeServiceImpl;

@RestController
@RequestMapping(value = "/v6/datatable")
public class ClientCodeControllerImpl implements ClientCodeController {

	@Autowired
	ClientCodeServiceImpl clientCodeService;  


    @Override
	public Map<String, List<ClientCodeDTO>> getCodesAll(@RequestParam(value = "name", defaultValue = "World") String name,final HttpServletRequest request,
            final HttpServletResponse response) {
		return clientCodeService.generic("ClientCd1");
	}

    @Override
	public Map<String, List<ClientCodeDTO>> getCodesByType(@PathVariable  String name, @PathVariable  String groupCd,
			final HttpServletRequest request,
            final HttpServletResponse response) {
		return clientCodeService.generic("ClientCd1", groupCd);
	}

}
