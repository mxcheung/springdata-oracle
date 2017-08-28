package com.maxcheung.company.greeting;


import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = CompanyController.EMAIL_BASE_CONTEXT)
class CompanyController {

	static final String EMAIL_BASE_CONTEXT = "/survey";

	static final String TEST_MAPPING = "/test";
	static final String SURVEY_MAPPING = "/add";
	static final String SURVEY_GET_MAPPING = "/get";
	static final String COMPANY_GET_MAPPING = "/company/get";

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@Autowired
	UserRepository userRepository;

	@Autowired
	SurveyRepository surveyRepository;

	@RequestMapping(value = TEST_MAPPING, method = GET)
	@ResponseStatus(OK)
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") final String name) {
		User user = new User();
		user.setUsername(name);
		userRepository.save(user);

		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

	@RequestMapping(value = SURVEY_MAPPING, method = POST)
	@ResponseStatus(CREATED)
	public @ResponseBody Survey surveyAdd(@RequestBody final SurveyDTO surveyDTO) {
		Survey survey = new Survey();
		survey.setCompanyName(surveyDTO.getCompanyName());
		survey.setSurveyType(surveyDTO.getSurveyType());
		survey.setDtd(surveyDTO.getDtd());
		survey.setStatus(surveyDTO.getStatus());
		survey.setNotes(surveyDTO.getNotes());
		surveyRepository.save(survey);

		return survey;
	}

	
	@RequestMapping(value = SURVEY_GET_MAPPING, method = GET)
	@ResponseStatus(CREATED)
	public @ResponseBody  ResponseEntity<List<Survey>> listAllSurvey() {
		List<Survey> surveys =  (List<Survey>) surveyRepository.findAll();
		 return new ResponseEntity<List<Survey>>(surveys, OK);
	}
	
	@RequestMapping(value = COMPANY_GET_MAPPING, method = GET)
	@ResponseStatus(OK)
	public @ResponseBody  ResponseEntity<CompanyInfoDTO> listAllCompany() {
	    
	    CompanyInfoDTO companyInfoDTO =  new CompanyInfoDTO();
		List<CompanyDTO> companyDTOs =  new ArrayList<CompanyDTO>();
		companyDTOs.add(new CompanyDTO(1L,"Amp"));
		companyDTOs.add(new CompanyDTO(2L,"BTFinancial"));
		companyDTOs.add(new CompanyDTO(3L,"Perpetual"));
		companyDTOs.add(new CompanyDTO(4L,"Vanguard"));
		companyDTOs.add(new CompanyDTO(5L,"Zurich"));
		companyInfoDTO.setCompanyList(companyDTOs);
		return new ResponseEntity<CompanyInfoDTO>(companyInfoDTO, OK);
	}
	
}