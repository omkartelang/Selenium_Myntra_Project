package com.myntra.qa.pages;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.myntra.qa.base.TestBase;
import com.myntra.qa.util.TestUtil;

public class ProductItemListPage extends TestBase {

	@FindBy(xpath="//*[@class='results-base']/li//child::a/div/*[@class='product-product']")
	//@CacheLookup
	List<WebElement> productName;
	
	@FindBy(xpath="//*[@class='results-base']/li//child::div//child::span/*[contains(.,'Add to bag')]")
	//@FindBy(xpath="//*[@class='results-base']/li//child::div//child::span[@class='product-actionsButton product-addToBag']")
	List<WebElement> productAddToBagOpt;
	
	@FindBy(xpath="//*[@class='results-base']/li//child::div//child::div/button[1]")
	List<WebElement> productSize;
		
	public ProductItemListPage()
	{
		try{
			//PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
			PageFactory.initElements(driver, this);
			
		}catch(StaleElementReferenceException e)
		{
			//PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
			PageFactory.initElements(driver, this);
		}
	}
	
	//select and add prodcut in wishlist..........
	public WishListPage selectAndAddProdInWishList(String prodName)
	{
		logger.info("prodName is :" + prodName);
		
		String[] prodArr = prodName.split(",");
		for(String strProd:prodArr)
		{
			logger.info("Each prod name is :" + strProd);
			Iterator<WebElement> productNameItr = productName.iterator();
			Iterator<WebElement> productAddToBagOptItr = productAddToBagOpt.iterator();
			//Iterator<WebElement> productSizeItr = productSizeOpt.iterator();
			Iterator<WebElement> productSizeItr = productSize.iterator();
			
			//actions class
			//Actions action = new Actions(driver);
			
			while(productNameItr.hasNext() && productAddToBagOptItr.hasNext() && productSizeItr.hasNext())
			//while(productNameItr.hasNext())
			{
				WebElement prodNameEle 		= productNameItr.next();
				WebElement prodAddToBagEle 	= productAddToBagOptItr.next();
				WebElement prodSizeOpt			= productSizeItr.next();
				
				String getProdName = prodNameEle.getText();
				
				logger.info("get product out side if condition is :" + getProdName);
				
				//verify product name......
				if(getProdName.equals(strProd))
				{
					logger.info("Product got : " + getProdName);
					
					//mouse hover product name....
					//action.moveToElement(productNameItr.next()).click(productAddToBagOptItr.next()).build().perform();
					
					TestUtil.mouseHoverJScript(driver, prodNameEle);
					
					//click on Add To Bag option.............
					TestUtil.mouseClickJScript(driver, prodAddToBagEle);
					
					//click on Add To Bag option.............
					//WebDriverWait wait = new WebDriverWait(driver,20);
					//wait.until(ExpectedConditions.visibilityOf(productAddToBagOptItr.next()));
					//action.moveToElement(productAddToBagOptItr.next()).click();
					
					//select size of product......
					TestUtil.mouseClickJScript(driver, prodSizeOpt);
					//action.moveToElement(productSizeItr.next()).click();
					//productSizeItr.next().click();
					
				}
			}
		}
		return new WishListPage();
	}
}
