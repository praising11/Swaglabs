package SwagLabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Checkout  extends Addtocarts{
	

	@Test(priority = 3)
	public void checkout() {
		
		try {
			String Expectedurl="https://www.saucedemo.com/v1/checkout-step-one.html";
			String currentUrl = driver.getCurrentUrl();
			if (Expectedurl==currentUrl) {
				 WebElement checkoutpage=driver.findElement(By.xpath("//div[text()=\"Checkout: Your Information\"]"));
				 boolean displayed = checkoutpage.isDisplayed();
				 if (displayed==true) {
					 System.out.println("User is on Correct checkout page");
				
				}
			} else {
				System.out.println("Current url of checkout page is:"+driver.getCurrentUrl());

			}
			
			WebElement Firstname,lastname,postalcode;
			Firstname=driver.findElement(By.id("first-name"));
			
				Assert.assertTrue(Firstname.isDisplayed(), "❌ Textbox is not displayed!");
			    Assert.assertTrue(Firstname.isEnabled(), "❌ Textbox is not enabled!");
					Firstname.sendKeys("Praising");
					System.out.println("First name Entered");
		
			lastname=driver.findElement(By.id("last-name"));
			if (lastname.isDisplayed()==true) {
				lastname.sendKeys("M");
				System.out.println("Last name Entered");
				
			}
				postalcode=driver.findElement(By.id("postal-code"));
				String inputValue = postalcode.getAttribute("value");
				if (inputValue.isEmpty()) {
					postalcode.sendKeys("628613");
					System.out.println("Postcode is Entered");
					
				} else {
					 System.out.println("✅ The textbox is not empty, value: " + inputValue);

				}
				
			WebElement continuebtn=driver.findElement(By.xpath("//input[@class=\"btn_primary cart_button\"]"));
			boolean cbtn=continuebtn.isDisplayed();
				if(cbtn==true){
					continuebtn.click();
					System.out.println("Continue button is clicked");
					
				}else {
					System.out.println("Continue button is not clickable");
				}
				
		
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

}
