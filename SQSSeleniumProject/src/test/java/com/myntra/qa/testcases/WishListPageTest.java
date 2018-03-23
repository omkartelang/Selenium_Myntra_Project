package com.myntra.qa.testcases;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.myntra.qa.base.TestBase;
import com.myntra.qa.pages.HomePage;
import com.myntra.qa.pages.ProductItemListPage;
import com.myntra.qa.pages.WishListPage;
import com.myntra.qa.util.TestUtil;

public class WishListPageTest extends TestBase{
	HomePage homePage;
	ProductItemListPage prodItemListPage;
	WishListPage wishLishPage;
	
	public WishListPageTest()
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
	public void remove_Product_Item_From_Wishlist(String prodBrand, String prodBrandName,String addToCartProdList,String wishListItem) throws InterruptedException
	{
		//search and select product brand .......
		prodItemListPage = homePage.srchProductBrand(prodBrand,prodBrandName);
		
		//add product item to wishlist...........
		wishLishPage = prodItemListPage.selectAndAddProdInWishList(addToCartProdList);
		
		//click on wishlist icon link............
		homePage.clickWhishListIconLink();
		
		//remove prod item from wishlist.....
		wishLishPage.removeProdItemWishList(wishListItem);
	}
	
	@Test(priority=2,dataProvider = "dpExcelData", dataProviderClass = TestUtil.class)
	public void add_And_Checkout_Product_Items(String prodBrand, String prodBrandName,String addToCartProdList) 
	{
		//search and select product brand .......
		prodItemListPage = homePage.srchProductBrand(prodBrand,prodBrandName);
		
		//add product item to wishlist...........
		wishLishPage = prodItemListPage.selectAndAddProdInWishList(addToCartProdList);
		
		//click on wishlist icon link............
		homePage.clickWhishListIconLink();
		
		//remove prod item from wishlist.....
		wishLishPage.checkOutOrder();
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}

}
