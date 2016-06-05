package datatable.model;

import java.util.ArrayList;
import java.util.List;

import datatable.model.ClientCodeDTO;

public class ClientGroupDTO {

	private List<ClientCodeDTO> addressCodeList = new  ArrayList<ClientCodeDTO>();
    private List<ClientCodeDTO> countryCodeList  = new  ArrayList<ClientCodeDTO>();
    private List<ClientCodeDTO> suffixList  = new  ArrayList<ClientCodeDTO>();
    private List<ClientCodeDTO> stateList  = new  ArrayList<ClientCodeDTO>();
    private List<ClientCodeDTO> genderList  = new  ArrayList<ClientCodeDTO>();
    private List<ClientCodeDTO> occupationList  = new  ArrayList<ClientCodeDTO>();
	public List<ClientCodeDTO> getAddressCodeList() {
		return addressCodeList;
	}
	public void setAddressCodeList(List<ClientCodeDTO> addressCodeList) {
		this.addressCodeList = addressCodeList;
	}
	public List<ClientCodeDTO> getCountryCodeList() {
		return countryCodeList;
	}
	public void setCountryCodeList(List<ClientCodeDTO> countryCodeList) {
		this.countryCodeList = countryCodeList;
	}
	public List<ClientCodeDTO> getSuffixList() {
		return suffixList;
	}
	public void setSuffixList(List<ClientCodeDTO> suffixList) {
		this.suffixList = suffixList;
	}
	public List<ClientCodeDTO> getStateList() {
		return stateList;
	}
	public void setStateList(List<ClientCodeDTO> stateList) {
		this.stateList = stateList;
	}
	public List<ClientCodeDTO> getGenderList() {
		return genderList;
	}
	public void setGenderList(List<ClientCodeDTO> genderList) {
		this.genderList = genderList;
	}
	public List<ClientCodeDTO> getOccupationList() {
		return occupationList;
	}
	public void setOccupationList(List<ClientCodeDTO> occupationList) {
		this.occupationList = occupationList;
	}
    

	
	
    


}

