package com.maxcheung.funddata;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.maxcheung.funddata.domain.FundData;
import com.maxcheung.funddata.domain.UserData;
import com.maxcheung.funddata.repository.UserRepository;
import com.maxcheung.funddata.service.FundService;

@SpringBootApplication
@EnableTransactionManagement
public class Application extends SpringBootServletInitializer {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	private static Class<Application> applicationClass = Application.class;

	private static final AtomicLong counter = new AtomicLong();

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	FundService fundService;

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	@PostConstruct
	public void init() {
		// init code goes here
		populateDummyUsers();
		fundService.populateFundData();
	}

	private List<UserData> populateDummyUsers() {
		List<UserData> users = new ArrayList<UserData>();
		users.add(new UserData(counter.incrementAndGet(), "Sam", 30, 70000));
		users.add(new UserData(counter.incrementAndGet(), "Tom", 40, 50000));
		users.add(new UserData(counter.incrementAndGet(), "Jerome", 45, 30000));
		users.add(new UserData(counter.incrementAndGet(), "Silvia", 50, 40000));

		for (UserData user : users) {
			userRepository.save(user);
		}

		return users;
	}

	// @Bean
	// public CommandLineRunner demo(FundDataRepository repository) {
	// return (args) -> {
	//
	// String inputDate = "12/07/2017";
	// Reader reader = new FileReader("src/main/resources/data.txt");
	// SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	//
	// Date inputDt = sdf.parse(inputDate);
	//
	// try (BufferedReader bufferedReader = new BufferedReader(reader)) {
	// Map<Date, TreeSet<FundData>> fundMap = new HashMap<Date,
	// TreeSet<FundData>>();
	// String line = bufferedReader.readLine();
	// while (line != null) {
	// // do something with line
	// System.out.println(line);
	// FundData fundData = parseFundData(line);
	// addToFundMap(fundMap, fundData);
	// line = bufferedReader.readLine();
	// }
	// reportFundByDate(inputDt, fundMap);
	// TreeSet<FundData> treeset = fundMap.get(inputDt);
	// repository.save(treeset);
	// }
	// };
	// }

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
