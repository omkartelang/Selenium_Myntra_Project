package com.myntra.qa.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;

public class LoadExcel {
	
	// Initialize Log4j logs
	 
	private static Logger logger = Logger.getLogger(LoadExcel.class.getName());//
	
	private static XSSFWorkbook xlWBook = null;
	private static XSSFSheet xlSheet = null;
	private static XSSFRow xlRow = null;
	private static XSSFCell xlCell = null;
	private static FileInputStream fis = null;
	
	
	//load xl data in object array
	public static Object[][] getXLTestData(String fpName,String sheetName, String testCaseName)
	{
		//load excel file
		loadExcelFile(fpName);
		
		//get sheet......
		//xlWBook.getSheet(sheetName);
		
		//get row count
		int rwCnt = getRowCountUsed();
		
		
		//loop rows and get max cell count used for given test case only
		int tcsColNum = 0;
		int lastColCnt = 0; 
		int matchedTcsRwCnt=0;
		for(int rwNum = 1; rwNum<=rwCnt; rwNum++)
		{
			//get test name from 1st columns
			String tcsName = getCellValue(rwNum,tcsColNum);
			
			if(tcsName.equalsIgnoreCase(testCaseName))
			{
				matchedTcsRwCnt++;
				int cellCnt = getCellUsedCount(rwNum);
				if(cellCnt > lastColCnt)
				{
					lastColCnt = cellCnt;
				}
			}
		}
		
		//maximum column used....
		int colCnt = lastColCnt;
		
		//System.out.println("matched tcs rwcnt :"+matchedTcsRwCnt);
		
		//set size object array....
		Object[][] xlData = new Object[matchedTcsRwCnt][colCnt-1];
		
		//set start row and column index
		int ri = 0, ci=0;
		
		//loop rows and get cell data for given test cases
		for(int rwIndex = 1; rwIndex<=rwCnt; rwIndex++)
		{
			//get test name from 1st columns
			String tcsName = getCellValue(rwIndex,tcsColNum);
			
			if(tcsName.equalsIgnoreCase(testCaseName))
			{
				//int cellCnt = getCellUsedCount(rwIndex);
				ci=0;
				
				for(int cellIndex = 1; cellIndex<colCnt; cellIndex++,ci++)
				{
					String str = getCellValue(rwIndex, cellIndex);
					//System.out.println("count is :" + str);
					//System.out.println("row index is :" + ri + "   colu index is :" + ci);
					xlData[ri][ci] = str;
				}
				ri++;
				
			}
		}
		
		return xlData;
	}
	
	
	//load excel file..............
	public static void loadExcelFile(String fileName)
	{
		
		try {
			
			//open excel file..........
			FileInputStream fis = new FileInputStream(fileName);
			
			//read excel file..............
			xlWBook = new XSSFWorkbook(fis);
			
			//get sheet name.....
			xlSheet = xlWBook.getSheet("Sheet1");
			
		} catch (FileNotFoundException e) {
			
			logger.error("file not found", e);
			e.printStackTrace();
		}
		
		catch (IOException e) {
			logger.error("IO exception XSSFWorkbook", e);
			e.printStackTrace();
		}
		
	}
	
	//get sheet name..............
	public static void setSheetName(String shName)
	{
		//get sheet name.....
		xlSheet = xlWBook.getSheet(shName);
	}
	
	//get rows used count..........
	public static int getRowCountUsed()
	{
		//row used count.....
		int rwCount = xlSheet.getLastRowNum();
		return rwCount;
	}
	
	//get columns used count.............
	public static int getCellUsedCount(int rowNum)
	{
		//cell used count..........
		xlRow = xlSheet.getRow(rowNum);
		int cellUsedCount = xlRow.getLastCellNum();
		return cellUsedCount;
	}
	
	//get cell value..................
	public static String getCellValue(int rownum, int cellNum)
	{
		//get cell value...........
		xlRow = xlSheet.getRow(rownum);
		xlCell = xlRow.getCell(cellNum);
		String cellValue = xlCell.getStringCellValue();
		return cellValue;
	}	
}
