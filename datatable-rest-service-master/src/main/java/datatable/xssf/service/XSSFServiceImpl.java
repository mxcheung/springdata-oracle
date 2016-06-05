package datatable.xssf.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import datatable.domain.ClientCode;
import datatable.domain.ClientCodeValue;

@Component
public class XSSFServiceImpl implements XSSFService {


	@Override
	public List<ClientCode> extractData(String clientCd, String fileName) throws IOException, OpenXML4JException {
		
		List<ClientCode> ret  = new ArrayList<ClientCode>();
		InputStream ExcelFileToRead = new FileInputStream(fileName);
		final OPCPackage pkg = OPCPackage.open(ExcelFileToRead);
		final Workbook workbook = new XSSFWorkbook(pkg);
		for (Sheet sheet : workbook) {
			ClientCode clientCode = createClientCode(clientCd, sheet.getSheetName());
			ret.add(clientCode);
			for (Row row : sheet) {
				String key = getCellValue(row.getCell(0));
				String value = getCellValue(row.getCell(1));
				ClientCodeValue clientCodeValue = createClientCodeValve(clientCode, key, value);
				clientCode.getClientCodeValues().add(clientCodeValue);
			}
		}
		workbook.close();
		return ret;
	}


	private String  getCellValue(Cell cell) {
		String val = null;
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			val = cell.getStringCellValue();
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			boolean cellVal = cell.getBooleanCellValue();
			val = String.valueOf(cellVal);
			break;
		case Cell.CELL_TYPE_NUMERIC:
			Double x = cell.getNumericCellValue();
			val = String.valueOf(x);
			break;
		}
		return val;
	}

	private ClientCode createClientCode(String clientKey, String groupKey) {
		ClientCode clientCode = new ClientCode();
		clientCode.setClientKey(clientKey);
		clientCode.setGroupKey(groupKey);
		clientCode.setClientCodeValues(new ArrayList<ClientCodeValue>());
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