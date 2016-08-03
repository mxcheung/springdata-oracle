package datatable.xssf.service;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import datatable.Application;
import datatable.domain.ClientCode;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class XSSFServiceTest {

	private static final String C_TEST_XLSX2 = "E:/TEMP/testdata2.xlsx";

	private static final String XSSF_RESOURCE = "/xssf/testdata2.xlsx";

	private  InputStream excelFileToRead = getClass().getResourceAsStream(XSSF_RESOURCE);

	@Autowired
	XSSFService xSSFServiceImpl;



	@Before
	public void setup() throws Exception {
	}

	@Test
	public void test2() throws  IOException, OpenXML4JException {
		List<ClientCode> clientCodes = xSSFServiceImpl.extractData("clientCd", excelFileToRead);
		assertEquals(3,clientCodes.size());

	}

	
	@Test
	public void test() throws  IOException, OpenXML4JException {
		List<ClientCode> clientCodes = xSSFServiceImpl.extractData("clientCd", C_TEST_XLSX2);
		assertEquals(3,clientCodes.size());

	}


}
