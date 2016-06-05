package com.maxcheung;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);
	private static final String C_LOOKUP = "E:/TEMP/Lookup Data.xlsx";
	private static final String C_LOOKUP_ABBR = "E:/TEMP/Lookup Data_Abbr.xlsx";
	private static final String C_TEST_XLSX2 = "E:/TEMP/testdata2.xlsx";

	
	@Autowired
	private CustomerReferenceGenerator customerReferenceGenerator;

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public CommandLineRunner demo(ClientCodeRepository repository) {
		return (args) -> {
			
	//		 loadFile(repository, "ClientCd2",C_TEST_XLSX2);
		//	 loadFile(repository, "ClientCd3",C_TEST_XLSX2);
			 loadFile(repository, "ClientCd1",C_LOOKUP_ABBR);
			
			 
//			log.info("Customers found with findByCodeKey():");
//			 List<ClientCode> codes = repository.findByCodeKey("Country");
//			for (ClientCode code : codes) {
//				log.info(code.toString());
//			}
			
			// save a couple of customers
//			repository.save(createClientCode("Client1", "Group1","CodeKey1", "CodeValue1"));
//			repository.save(createClientCode("Client1", "Group1","Chloe", "O'Brian"));
//			repository.save(createClientCode("Client1", "Group1","Kim", "Bauer"));
//			repository.save(createClientCode("Client1", "Group1","David", "Palmer"));
//			repository.save(createClientCode("Client1", "Group1","Michelle", "Dessler"));

			// fetch all customers
//			log.info("Customers found with findAll():");
//			log.info("-------------------------------");
//			for (ClientCode customer : repository.findAll()) {
//				log.info(customer.toString());
//			}
//            log.info("");

//			// fetch an individual customer by ID
//			Customer customer = repository.findOne(1L);
//			log.info("Customer found with findOne(1L):");
//			log.info("--------------------------------");
//			log.info(customer.toString());
//            log.info("");
//
//			// fetch customers by last name
//			log.info("Customer found with findByLastName('Bauer'):");
//			log.info("--------------------------------------------");
//			for (Customer bauer : repository.findByLastName("Bauer")) {
//				log.info(bauer.toString());
//			}
//            log.info("");
		};
	}

	private ReadWriteExcelFile loadFile(ClientCodeRepository repository, String clientCd, String fileName) throws IOException, OpenXML4JException, InterruptedException {
		ReadWriteExcelFile readWriteExcelFile = new ReadWriteExcelFile();
		
		
		List<ClientCode> clientCodes = readWriteExcelFile.testdata(clientCd, fileName);
		
		for (ClientCode clientCode : clientCodes)
		{
			repository.save(clientCode);
		}
		return readWriteExcelFile;
	}


	private ClientCode createClientCode(String clientKey, String groupKey) {
		ClientCode clientCode = new ClientCode();
		clientCode.setClientKey(clientKey);
		clientCode.setGroupKey(groupKey);
		List<ClientCodeValue> clientCodeValues = new ArrayList<ClientCodeValue>();
		return clientCode;
	}

	private ClientCodeValue createClientCodeValve(ClientCode clientCode, String codeKey, String codeValue) {
		ClientCodeValue clientCodeValue = new ClientCodeValue();
		clientCodeValue.setClientCode(clientCode);
		clientCodeValue.setCodeKey(codeKey);
		clientCodeValue.setCodeValue(codeValue);
		return clientCodeValue;
	}



}
