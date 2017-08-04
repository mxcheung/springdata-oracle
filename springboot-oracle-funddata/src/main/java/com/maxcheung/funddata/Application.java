package com.maxcheung.funddata;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public CommandLineRunner demo(FundDataRepository repository) {
		return (args) -> {

			String inputDate = "12/07/2017";
			Reader reader = new FileReader("src/main/resources/data.txt");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			Date inputDt = sdf.parse(inputDate);

			try (BufferedReader bufferedReader = new BufferedReader(reader)) {
				Map<Date, TreeSet<FundData>> fundMap = new HashMap<Date, TreeSet<FundData>>();
				String line = bufferedReader.readLine();
				while (line != null) {
					// do something with line
					System.out.println(line);
					FundData fundData = parseFundData(line);
					addToFundMap(fundMap, fundData);
					line = bufferedReader.readLine();
				}
				reportFundByDate(inputDt, fundMap);
				TreeSet<FundData> treeset = fundMap.get(inputDt);
				repository.save(treeset);
			}
		};
	}

	private static void addToFundMap(Map<Date, TreeSet<FundData>> fundMap, FundData fundData) {
		TreeSet<FundData> treeset = fundMap.get(fundData.getFundDate());
		if (treeset == null) {
			treeset = new TreeSet<FundData>();
		}
		treeset.add(fundData);
		fundMap.put(fundData.getFundDate(), treeset);

	}

	private static void reportFundByDate(Date inputDt, Map<Date, TreeSet<FundData>> fundMap) {
		TreeSet<FundData> treeset = fundMap.get(inputDt);
		for (FundData fundData : treeset) {
			System.out.println(fundData);
		}
	}

	private static FundData parseFundData(String line) throws ParseException {
		String[] data = line.split(",");
		String fundCode = data[0];
		String fundDate = data[1];
		String fundPrice = data[2];
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date fundDt = sdf.parse(fundDate);
		FundData fundData = new FundData();
		fundData.setFundCode(fundCode);
		fundData.setFundDate(fundDt);
		fundData.setFundPrice(fundPrice);
		return fundData;
	}

}
