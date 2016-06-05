package datatable.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import datatable.model.ClientCodeDTO;

@SuppressWarnings("unused")
@Controller
@RequestMapping(value = "/v6/datatable")
public interface ClientCodeController {

	@RequestMapping(value = "/generic", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	Map<String, List<ClientCodeDTO>> getCodesAll(String name, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response);

	@RequestMapping(value = "/generic/{name}/{groupCd}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	Map<String, List<ClientCodeDTO>> getCodesByType(String name, String groupCd,javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response);

}
