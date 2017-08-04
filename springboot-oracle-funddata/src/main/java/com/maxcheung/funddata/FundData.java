package com.maxcheung.funddata;

import static javax.persistence.GenerationType.SEQUENCE;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Temporal;


@Entity
public class FundData implements Comparable<FundData> {

    @Id
    @SequenceGenerator(name = "generator", sequenceName = "FundData_PK_ID")
    @GeneratedValue(strategy = SEQUENCE, generator = "generator")
    @Column(name = "ID")
    private long id;
    private String fundCode;
    
    
    @Column(name = "fund_date")
    private Date fundDate;
    
    
    private String fundPrice;
    


    protected FundData() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFundCode() {
		return fundCode;
	}

	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}

	

	public Date getFundDate() {
		return fundDate;
	}

	public void setFundDate(Date fundDate) {
		this.fundDate = fundDate;
	}

	public String getFundPrice() {
		return fundPrice;
	}

	public void setFundPrice(String fundPrice) {
		this.fundPrice = fundPrice;
	}


	
	@Override
	public int compareTo(FundData fundData) {
		return this.fundCode.compareTo(fundData.getFundCode());

	}

	@Override
	public String toString() {
		return fundCode + ',' + fundDate  + ' ' + fundPrice;

	}




}

