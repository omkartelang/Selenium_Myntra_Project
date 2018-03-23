package com.myntra.qa.testcases;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.myntra.qa.base.TestBase;
import com.myntra.qa.pages.HomePage;
import com.myntra.qa.pages.ProductItemListPage;
import com.myntra.qa.util.TestUtil;

public class ProductListpageTest extends TestBase{
	
	HomePage homePage;
	ProductItemListPage prodItemListPage;
	
	public ProductListpageTest()
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
	public void search_Add_Product_WishList(String prodBrand, String prodBrandName,String addToCartProdList) 
	{
		logger.info("Product get searched");
		prodItemListPage = homePage.srchProductBrand(prodBrand, prodBrandName);
		
		logger.info("select product and add it to wishlist");
		prodItemListPage.selectAndAddProdInWishList(addToCartProdList);
		
	}
	
	@Test(priority=2,dataProvider = "dpExcelData", dataProviderClass = TestUtil.class)
	public void add_Multiple_Product_To_wishList(String prodBrand, String prodBrandName,String addToCartProdList) 
	{
		logger.info("Product get searched");
		prodItemListPage = homePage.srchProductBrand(prodBrand, prodBrandName);
		
		logger.info("select product and add it to wishlist");
		prodItemListPage.selectAndAddProdInWishList(addToCartProdList);
		
	}
	
	@AfterMethod
	public void tearDoen()
	{
		driver.quit();
	}

}
