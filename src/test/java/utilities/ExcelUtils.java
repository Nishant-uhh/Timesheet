package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	public List<String> readExcelData(String excelName) throws IOException {
		List<String> data = new ArrayList<String>();
		File file = new File(excelName);
		file.getAbsolutePath();

		FileInputStream is = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(is);
		XSSFSheet sheet = workbook.getSheetAt(0);

		int totalCells = sheet.getRow(0).getLastCellNum();
		XSSFRow row = sheet.getRow(0);
		XSSFCell cell;

		for (int i = 0; i < totalCells; i++) {
			cell = row.getCell(i);
			data.add(cell.toString());
		}

		workbook.close();
		return data;
	}

	public void writeExcelData(String excelName, String sheetName, String[] data) throws IOException {
		FileOutputStream os = new FileOutputStream(excelName);
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet(sheetName);
		XSSFRow row = sheet.createRow(0);

		for (int i = 0; i < data.length; i++) {
			row.createCell(i).setCellValue(data[i]);
		}

		workbook.write(os);
//		System.out.println("file written!");
		workbook.close();
		os.close();
	}
}
