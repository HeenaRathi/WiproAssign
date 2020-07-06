package Helper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	public ArrayList<String> getData(String sheetName, String colum) throws InvalidFormatException, IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
		// TODO Auto-generated method stub

		ArrayList<String> list = new ArrayList<String>();
		String data="";
		File file = new File("D:\\Workspace\\Appium\\AppiumAssignment\\src\\main\\java\\resource\\TestData.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		int totalSheets = workbook.getNumberOfSheets();
		int k = 0;
		int column = 0;
		for (int i = 0; i < totalSheets; i++) {
			// XSSFSheet sheet=workbook.getSheetAt(i);
			if (workbook.getSheetName(i).equalsIgnoreCase(sheetName)) {
				XSSFSheet sheet = workbook.getSheetAt(i);
				Iterator<Row> rows = sheet.iterator();
				while (rows.hasNext()) {
					Row r1 = rows.next();
					if (r1.getCell(column).getStringCellValue().contains(colum))
					{
						Row r2=rows.next();
						Iterator<Cell> cc = r2.cellIterator();
						while (cc.hasNext()) {
							Cell c= cc.next();
							if(c.getCellType()== CellType.STRING)
							{
								list.add(c.getStringCellValue());
															}
							else
							{
								String num=NumberToTextConverter.toText(c.getNumericCellValue());
								System.out.println(num);
								list.add(num);
							}
							
						}
					}
				}

			}
		}
		return list;

	}
}
