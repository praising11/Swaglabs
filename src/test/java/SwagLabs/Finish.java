package SwagLabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Finish extends Checkout{

	@Test(priority = 4)
	public void Finish() {
		try {
			String Expecetedurl="https://www.saucedemo.com/v1/checkout-complete.html";

			System.out.println("Currrent Url of Finish page is :"+driver.getCurrentUrl());
			if (driver.getCurrentUrl().equals(Expecetedurl)) {
				System.out.println("User is correct page");
			} else {
				System.out.println("Url is not same in Finish page");

			}

			WebElement Itemtotal;

			Itemtotal=driver.findElement(By.xpath("//div[@class=\"summary_subtotal_label\"]"));
			String itoal = Itemtotal.getText();
			String Actualprice = itoal.substring(1);
			String price = "$7.99";
			String trimmedPrice = price.substring(1); // Removes the first character
			if (Actualprice==trimmedPrice) {
				System.out.println("Both price are equal");
			} else {
				System.out.println("Both price are not equal");

			}
			WebElement Finishbtn;
			Finishbtn=driver.findElement(By.xpath("//a[@class=\"btn_action cart_button\"]"));
			boolean enabled = Finishbtn.isEnabled();
			if (enabled==true) {
				Finishbtn.click();
				System.out.println("Finish button is clicked");
				
			} else {
				System.out.println("Finish button is not clicked");

			}
			WebElement successmsg =driver.findElement(By.xpath("//h2[text()=\"THANK YOU FOR YOUR ORDER\"]"));
			
			String Sucesstext = successmsg.getText();
			String Expected="THANK YOU FOR YOUR ORDER";
			Assert.assertEquals(Sucesstext, Expected);
			System.out.println("Success Message received  :"+Sucesstext);
		
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
