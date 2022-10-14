package day34;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReaddataFromExcel {
	public static void main(String[] args) throws IOException {
		Workbook wb = WorkbookFactory.create(new FileInputStream("./data/testdata.xlsx"));
		//Go to sheet
		Sheet sh = wb.getSheet("sheet1");
		//Go to 1st row and 1st col, get the value
		String value = sh.getRow(0).getCell(0).getStringCellValue();
		System.out.println("Data... "+value);
		Sheet sh1 = wb.getSheet("sheet2");
		String value1 = sh1.getRow(0).getCell(1).toString(); //we can use toString irrespective of data type in the cell
		System.out.println("value1... "+value1);
		
		//To write into xcel
		wb.getSheet("Sheet1").getRow(0).getCell(0).setCellValue("Renuka");
		wb.getSheet("Sheet1").getRow(0).createCell(3).setCellValue("Renuka");		//If column does not exist, create one before writing
		wb.getSheet("Sheet1").createRow(3).createCell(0).setCellValue("Renuka");	//If row does not exist, create one before writing
		//Save the file
		wb.write(new FileOutputStream("./data/testdata1.xlsx"));
		//close the xlsx file
		wb.close();
	}
}
