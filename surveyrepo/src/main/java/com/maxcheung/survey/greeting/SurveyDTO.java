package com.maxcheung.survey.greeting;

public class SurveyDTO {
    private Long id;
    private String companyName;
    private String surveyType;
    private String dtd;
    private String status;
    private String notes;    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getSurveyType() {
		return surveyType;
	}

	public void setSurveyType(String surveyType) {
		this.surveyType = surveyType;
	}

	public String getDtd() {
		return dtd;
	}

	public void setDtd(String dtd) {
		this.dtd = dtd;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}


}