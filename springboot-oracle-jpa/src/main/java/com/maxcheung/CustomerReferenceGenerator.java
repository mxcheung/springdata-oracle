package com.maxcheung;

import org.apache.commons.validator.routines.checkdigit.CheckDigitException;

public interface CustomerReferenceGenerator   {


	public long generateCRN() throws CheckDigitException;

	
}
