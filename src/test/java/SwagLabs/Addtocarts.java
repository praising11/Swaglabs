package SwagLabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class Addtocarts extends Productpage {
	
	@Test(priority = 2)
	public void ac() {
		
		try {

			WebElement ac=driver.findElement(By.id("shopping_cart_container"));
			boolean acdisplayed = ac.isDisplayed();
			  if (acdisplayed==true) {
				  driver.findElement(By.id("shopping_cart_container")).click();
				  System.out.println("Add to cart button is clicked");
				
			} else {
				System.out.println("Add to cart is not displayed");

			}
			   double Expectedprice = 7.99;
			  WebElement actual=driver.findElement(By.xpath("//div[@class=\"inventory_item_price\" and text()=\"7.99\"]"));
			  String Ogprice = actual.getText();
			  if (Ogprice.equals(Expectedprice)) {
				  System.out.println("Add cart product is:"+Ogprice);
				
			} else {
				System.out.println("product Mismatching"+Ogprice);

			}
			  
			  WebElement checkout=driver.findElement(By.xpath("//a[@class=\"btn_action checkout_button\"]"));
			  boolean enabled = checkout.isEnabled();
			  if (enabled==true) {
				  checkout.click();
				  System.out.println("Checkout button is clicked");
				
			} else {
				System.out.println("Checkout button is not clicked");

			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	
	}
}
