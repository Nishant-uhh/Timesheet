package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class fileoutput {
	
	
    
	public static File writeFile = new File(System.getProperty("user.dir") + "\\testResult\\TestResultsA.xls");
	static Sheet sheet1 = null;
	
	
	
	
   public void writingFunction(int i,int j,String data) throws IOException {
	   
	   HSSFWorkbook workbook=new HSSFWorkbook();
	   
	   
	   
   if(writeFile.exists()==true) {
	
	   writeexcel(i,j,data);
//	   System.out.println("Its already exists");
   }
   
   else {
	   
	   sheet1=workbook.createSheet("testResult");
	   Row row=sheet1.createRow(i);
   	   Cell cell=row.createCell(j);
   	   cell.setCellValue(data);
   	   FileOutputStream outputStream = new FileOutputStream(writeFile);
   	   workbook.write(outputStream);
   	    outputStream.close();
	   
   }
   	
   
    	
    
  
   }
   
   
   
   public static void writeexcel(int i,int j,String data) throws IOException {
	   
	   
	   FileInputStream input=new FileInputStream(writeFile);
	   HSSFWorkbook workbooks=new HSSFWorkbook(input);
	   sheet1=workbooks.getSheet("testResult");
	 	Row row=sheet1.createRow(i);
    	Cell cell=row.createCell(j);
    	cell.setCellValue(data);
    	FileOutputStream outputStream = new FileOutputStream(writeFile);
    	workbooks.write(outputStream);
    	workbooks.close();
    	outputStream.close();
    	
    	
   }
}
