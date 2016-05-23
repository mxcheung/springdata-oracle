package com.maxcheung;

import javax.sql.DataSource;

import org.apache.commons.validator.routines.checkdigit.CheckDigitException;
import org.apache.commons.validator.routines.checkdigit.LuhnCheckDigit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
import org.springframework.jdbc.support.incrementer.OracleSequenceMaxValueIncrementer;
import org.springframework.stereotype.Component;


@Component
public class CustomerReferenceGeneratorImpl  implements CustomerReferenceGenerator {

	
	private static final String CUSTOMER_CRN_SEQ = "CUSTOMER_CRN_SEQ";
	
	private DataFieldMaxValueIncrementer incrementer;

	@Autowired
	public CustomerReferenceGeneratorImpl(DataSource dataSource) {
		incrementer  = new OracleSequenceMaxValueIncrementer(dataSource, CUSTOMER_CRN_SEQ);  
	}


	@Override
	public long generateCRN() throws CheckDigitException  {
		long empId = incrementer.nextLongValue();
		String checkdigit = "";
		checkdigit = calculateCheckDigit("" + empId);
		return Long.parseLong(empId + checkdigit);
	}

	
	private String calculateCheckDigit(String code) throws CheckDigitException  {
		return LuhnCheckDigit.LUHN_CHECK_DIGIT.calculate(code);
	}

}
