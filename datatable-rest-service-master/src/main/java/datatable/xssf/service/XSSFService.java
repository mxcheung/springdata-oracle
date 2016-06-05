package datatable.xssf.service;

import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;

import datatable.domain.ClientCode;

public interface XSSFService {

	List<ClientCode> extractData(String clientCd, String fileName) throws IOException, OpenXML4JException;

}
