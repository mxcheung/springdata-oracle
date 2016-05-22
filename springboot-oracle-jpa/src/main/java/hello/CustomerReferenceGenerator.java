package hello;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.incrementer.OracleSequenceMaxValueIncrementer;
import org.springframework.stereotype.Component;


@Component
public class CustomerReferenceGenerator extends OracleSequenceMaxValueIncrementer  {

	private static final String CUSTOMER_CRN_SEQ = "CUSTOMER_CRN_SEQ";

	@Autowired
	public CustomerReferenceGenerator(DataSource dataSource) {
		super(dataSource, CUSTOMER_CRN_SEQ);
	}


}
