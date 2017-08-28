package com.maxcheung.company.greeting;

public class CompanyDTO {
    private Long id;
    private String companyName;
    
    public CompanyDTO(){
    }
  
    
	public CompanyDTO(Long id, String companyName) {
		super();
		this.id = id;
		this.companyName = companyName;
	}
	
	
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
    


}