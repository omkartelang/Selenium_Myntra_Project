package com.myntra.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.DataProvider;

import com.myntra.qa.base.TestBase;

public class TestUtil extends TestBase{
	
	public static long PAGE_LOAD_TIMEOUT = 50;
	public static long IMPLICIT_WAIT = 40;
	private static String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');"+
			"evObj.initEvent('mouseover',true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject){ arguments[0].fireEvent('onmouseover');}";
	
	private static String mouseClickScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');"+
			"evObj.initEvent('click',true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject){ arguments[0].fireEvent('onclick');}";
	
	
	@DataProvider(name = "dpExcelData")
	public static Object[][] getTestData(Method method) {
		
		//TestBase testbase = new TestBase();
		String currentDir = System.getProperty("user.dir");
		//String fNamePath = currentDir + "/screenshots/" +System.currentTimeMillis() + ".png";
		String fName = currentDir + prop.getProperty("filePath")+prop.getProperty("fileName");
		String sheetName = prop.getProperty("shName");
		String testCaseName = method.getName();
		
		logger.info("file name is :" + fName);
		logger.info("sheet name is : " + sheetName);
		logger.info("test case name is : " + testCaseName);
		
		Object[][] data = LoadExcel.getXLTestData(fName, sheetName, testCaseName);
		return data;
	}
	
	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		String fNamePath = currentDir + "/screenshots/" +System.currentTimeMillis() + ".png";
		FileUtils.copyFile(scrFile, new File(fNamePath));
		logger.info("File Name and Path : " + fNamePath);
		//FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" +System.currentTimeMillis() + ".png"));
		
		}
	
	public static void mouseHoverJScript(WebDriver driver, WebElement element)
	{
		logger.info("JS Mouse hovering on web element......");
		((JavascriptExecutor)driver).executeScript(mouseOverScript, element);
	}
	
	public static void mouseClickJScript(WebDriver driver, WebElement element)
	{
		logger.info("JS Mouse Clicking on web element......");
		((JavascriptExecutor)driver).executeScript(mouseClickScript, element);
	}
	
	
	
	public static void scrollUpPage(WebDriver driver)
	{
		logger.info("scrolling up window");
		//((JavascriptExecutor)driver).executeScript("window.ScrollTo(0, document.body.scrollHeight)");
		//((JavascriptExecutor)driver).executeScript("window.ScrollTo(0, 400)");
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
	}

}