package generic;

import java.io.FileInputStream;
import java.util.LinkedHashMap;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Excel {

	public static String getData(String Path,String sheet, int row, int colIndex) {
		String value = "";
		try {
			Workbook wb = WorkbookFactory.create(new FileInputStream(Path));
			Sheet sh = wb.getSheet(sheet);
			value = sh.getRow(row).getCell(colIndex).getStringCellValue();
		}
		catch(Exception e) {
			
		}
		return value;
	}
	
	public static String getData(String Path,String sheet,int row, String colName) 
	{
		LinkedHashMap<String, String> map=new LinkedHashMap<String, String>();
		try 
		{
			Workbook wb = WorkbookFactory.create(new FileInputStream(Path));
			int cc = wb.getSheet(sheet).getRow(0).getLastCellNum();
			for(int i=0;i<cc;i++) {
				String k = wb.getSheet(sheet).getRow(0).getCell(i).toString();
				String v = wb.getSheet(sheet).getRow(row).getCell(i).toString();
				map.put(k, v);
			}
			
			wb.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return map.get(colName);
	}
	
}
