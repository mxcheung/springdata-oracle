package com.maxcheung.funddata.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maxcheung.funddata.domain.FundData;
import com.maxcheung.funddata.repository.FundDataRepository;

@Service("fundService")
@Transactional
public class FundServiceImpl implements FundService {

	@Autowired
	FundDataRepository fundDataRepository;

	private static final AtomicLong counter = new AtomicLong();

	@Override
	public void populateFundData() {
		 String inputDate = "12/07/2017";
		 Reader reader;
		try {
			reader = new FileReader("src/main/resources/data.txt");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			Date inputDt = sdf.parse(inputDate);
			
			try (BufferedReader bufferedReader = new BufferedReader(reader)) {
				
				Map<Date, TreeSet<FundData>> fundMap = new HashMap<Date,
						TreeSet<FundData>>();
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
					 fundDataRepository.save(treeset);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
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