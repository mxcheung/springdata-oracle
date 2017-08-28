package com.maxcheung.survey.greeting;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
    Survey findByCompanyName(String companyName);
}