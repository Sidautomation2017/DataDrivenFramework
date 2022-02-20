package com.edusol.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	
// 	object [][] = {{[key=value],[key=value],...... },{2,3},{},{}}; 

	static String homepath = System.getProperty("user.dir");
	static XSSFSheet sheet;

	public static Object[][] getExcelData() {
		HashMap<String, String> map=null;   // key value 

		File file = new File(homepath + "//src//test//resources//Data//TestData.xlsx");
		Object[][] exceldata=null;
		try {
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			int noOfSheets = workbook.getNumberOfSheets();
			sheet = workbook.getSheet("Sheet1");
			int rowsCount = sheet.getPhysicalNumberOfRows();
			int cellCount = sheet.getRow(0).getPhysicalNumberOfCells();
			System.out.println("row count is -" + rowsCount);
			System.out.println("cell count is -" + cellCount);

			 exceldata= new Object[rowsCount - 1][cellCount]; // 2*2
			for (int rowNum = 1; rowNum < rowsCount; rowNum++) {
				new HashMap<String, String>();
					for (int colNum = 0; colNum < cellCount; colNum++) {
					String data = getvalueFromCell(rowNum, colNum);
					map.put(getvalueFromCell(0,colNum), data);
						}
					exceldata[rowNum-1][0]=map;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exceldata;

	}

	public static String getvalueFromCell(int row, int col) {
		Cell cell = sheet.getRow(row).getCell(col);
		String value = "";
		if (cell.getCellType().equals(CellType.STRING)) {
			value = cell.getStringCellValue();
		} else {
			Double nvalue = cell.getNumericCellValue();
			value = String.valueOf(nvalue);
		}

		return value;

	}


}
