package com.myntra.qa.pages;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.myntra.qa.base.TestBase;
import com.myntra.qa.util.TestUtil;

public class WishListPage extends TestBase{

	@FindBy(xpath="//div[@class='prod-set']//child::div[@class='prod-name']/a")
	List<WebElement> prodWishListName;
	
	@FindBy(xpath="//div[@class='prod-set']//child::span[contains(text(),'REMOVE')]")
	List<WebElement> prodWishListRemoveLink;
	
	@FindBy(xpath="//button[@class='btn primary-btn btn-remove m-button']")
	WebElement confirmRemoveItem;

	@FindBy(xpath="//button[contains(text(),'PLACE ORDER')]")
	WebElement checkOutPlaceOrdrBtn;
	
	
	public WishListPage()
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
	
	//remove product item from wishlist......
	public void removeProdItemWishList(String prodWishListItem) throws InterruptedException
	{
		
		String[] prodWishListItemArr = prodWishListItem.split(",");
		
		for(String prodItemStr:prodWishListItemArr)
		{
			logger.info("prod Item str is :" + prodItemStr);
			
			Iterator <WebElement>prodWishListNameItr = prodWishListName.iterator();
			Iterator <WebElement>prodWishListRemoveLinkItr = prodWishListRemoveLink.iterator();
			
			
			while(prodWishListNameItr.hasNext() && prodWishListRemoveLinkItr.hasNext())
			{
				TestUtil.scrollUpPage(driver);
				WebElement prodWishListItemEle = prodWishListNameItr.next();
				WebElement prodWishListRemoveLinkEle = prodWishListRemoveLinkItr.next(); 
				
				//intitalizing constructor....
				WebDriverWait wait = new WebDriverWait(driver,80);
				wait.until(ExpectedConditions.elementToBeClickable(prodWishListItemEle));
				String getProdItemTxt = prodWishListItemEle.getText();
				
				//logger.info("Get prod item wishlist :" + getProdItemTxt);
				
				
				
				if(getProdItemTxt.contains(prodItemStr))
				{
					
					logger.info("Get prod item wishlist :" + getProdItemTxt);
					wait = new WebDriverWait(driver,80);
					wait.until(ExpectedConditions.elementToBeClickable(prodWishListRemoveLinkEle));
					
					//remove prod item from wishlist...
					prodWishListRemoveLinkEle.click();
					//TestUtil.mouseClickJScript(driver, prodWishListRemoveLinkEle);
					
					//confirm remove prod item from list...
					confirmRemoveItem.click();
					//Thread.sleep(3000);
					//TestUtil.mouseClickJScript(driver, confirmRemoveItem);
					
					
					
				}else if(prodItemStr.equals("RemoveAll"))
				{
					//remove all prod item from wishlist...
					prodWishListRemoveLinkEle.click();
					
					//confirm remove prod item from list...
					confirmRemoveItem.click();
					
				}
				
				
			}
		}
	}
	
	public void checkOutOrder()
	{
		logger.info("checkout order is : ");
		checkOutPlaceOrdrBtn.click();
	}
	
}
