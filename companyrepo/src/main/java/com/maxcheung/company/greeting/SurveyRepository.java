package com.maxcheung.company.greeting;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
    Survey findByCompanyName(String companyName);
}