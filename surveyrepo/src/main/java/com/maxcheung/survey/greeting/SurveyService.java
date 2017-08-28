package com.maxcheung.survey.greeting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.maxcheung.company.greeting.CompanyInfoDTO;

@Service
public class SurveyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SurveyService.class);

    private RestTemplate restTemplate;

    private String companyServiceURL = "http://localhost:8090/survey/company/get";

    @Autowired
    public SurveyService() {
        
        this.restTemplate = new RestTemplate();
    }

    private String getURL() {
        return companyServiceURL;
    }

    CompanyInfoDTO getCompany() throws Exception {
        ResponseEntity<CompanyInfoDTO> response = restTemplate.exchange(getURL(), HttpMethod.GET, null, CompanyInfoDTO.class);
        LOGGER.info(response.toString());
        return response.getBody();
    }


        // ResponseEntity<String> response = restTemplate.exchange(getURL,
        // HttpMethod.GET, prepareEmailEntity(user), String.class);
        //
        // if (response != null && !HttpStatus.ACCEPTED.equals(response.getStatusCode())) {
        // throw new EmailServiceException(registerUserEmailConfig.getExceptionMsg());
        // }

    private HttpHeaders getRequestHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
