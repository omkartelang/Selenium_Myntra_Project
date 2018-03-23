package com.myntra.qa.pages;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.myntra.qa.base.TestBase;

public class HomePage extends TestBase {

	@FindBy(xpath="//*[@class='desktop-searchBar']")
	WebElement searchTxtBox;
	
	@FindBy(xpath="//*[contains(@class,'desktop-autoSuggest desktop-showContent')]/ul/li")
	List<WebElement> autoSugTitleList;
	
	@FindBy(xpath="//*[@class='index-infoBig']")
	WebElement getTxtSrchMessageUnknowProduct;
	
	@FindBy(xpath="//*[@class='pdp-error-message']")
	WebElement getTxtSrchMessageForNumeric;
	
	@FindBy(xpath="//*[@class='index-infoBig']")
	WebElement getTxtSrchMessageForSpecialChars;
	
	@FindBy(xpath="//span[@class='myntraweb-sprite desktop-iconBag sprites-bag']")
	WebElement wishListIconLink;
	
	public HomePage()
	{
		//-- this points all class objects (nothing but all web element variables..
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
	}
	
	//srch product brand.....
	public ProductItemListPage srchProductBrand(String prodBrand,String ProdBrandName)
	{
		try{
				//enter product brand in search txt field.....
				searchTxtBox.sendKeys(prodBrand);
				
				//select and click on product brand in autosuggestion list....
				for(WebElement ele:autoSugTitleList)
				{
					String getProdBrand = ele.getText();
					logger.info("getprod Name : " + getProdBrand);
					//check match with given product brand and select it....
					if(getProdBrand.equalsIgnoreCase(ProdBrandName))
					{
						//click on matched product brand.....
						ele.click();
					}
				}
			}catch(StaleElementReferenceException e)
			{
				logger.info("stale element reference exception is caught ");
				PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
				
			}
		
			return new ProductItemListPage();
	}
	
	//srch unknown product brand.....
	public void srchInvalidProductBrand(String prodBrand,String errorType)
	{
		//enter product brand in search txt field.....
		searchTxtBox.sendKeys(prodBrand);
		
		//select and click on product brand in autosuggestion list....
		searchTxtBox.sendKeys(Keys.ENTER);
		switch(errorType)
		{
		case "UnknownProd":
		case "SpecialChars":
			//verify getting error
			String getSrchResultsMsg = getTxtSrchMessageUnknowProduct.getText();
			assertEquals(getSrchResultsMsg.trim(), "We couldn't find any matches!");
			break;
		case "Numeric":
			assertEquals(getTxtSrchMessageForNumeric.getText().trim(), "Oops! Something went wrong. Please try again in some time.");
			break;
		
		}
	}
	
	//click on wishlist icon link.......
	public WishListPage clickWhishListIconLink()
	{
		logger.info("Clicking on wishlist icon link page");
		wishListIconLink.click();
		return new WishListPage();
	}
}
			
		
		
	
