package com.myntra.qa.testcases;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.myntra.qa.base.TestBase;
import com.myntra.qa.pages.HomePage;
import com.myntra.qa.util.TestUtil;

public class HomePageTest extends TestBase{
	
	HomePage homePage;
	
	public HomePageTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setUp() throws IOException
	{
		initialization();
		homePage = new HomePage();
	}
	
	@Test(priority=1,dataProvider = "dpExcelData", dataProviderClass = TestUtil.class)
	public void search_ProductItems_by_ProductBrand(String prodBrand, String prodBrandName)
	{
		logger.info("product band is :" + prodBrand);
		logger.info("product band name is :" + prodBrandName);
		//logger.info("name is :" + name);
		homePage.srchProductBrand(prodBrand, prodBrandName);
	}
	
	@Test(priority=1,dataProvider = "dpExcelData", dataProviderClass = TestUtil.class)
	public void search_ProductItems_by_partial_ProductBrandName(String prodBrand, String prodBrandName)
	{
		logger.info("product band is :" + prodBrand);
		logger.info("product band name is :" + prodBrandName);
		//logger.info("name is :" + name);
		homePage.srchProductBrand(prodBrand, prodBrandName);
	}
	
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}

}
