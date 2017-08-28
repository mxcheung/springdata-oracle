package com.maxcheung.company.greeting;

import java.util.List;

public class CompanyInfoDTO {
    
    public CompanyInfoDTO(){
    }
  
  
    private List<CompanyDTO> companyList;

    public List<CompanyDTO> getCompanyList() {
        return companyList;
    }

    public void setCompanyList(List<CompanyDTO> companyList) {
        this.companyList = companyList;
    }
    
    

}