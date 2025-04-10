package SwagLabs;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Productpage extends Swag_Login {

	@Test(priority = 1)
	public void sorting() {

		String expectedurl="https://www.saucedemo.com/v1/inventory.html";
		if (expectedurl==driver.getCurrentUrl()) {
			System.out.println(driver.getCurrentUrl()+"Product page url validation passed");

		} else {
			System.out.println("\u001B[31m❌ product page url validation failed"+ driver.getCurrentUrl()+ "\u001B[0m");

		}

		WebElement sort=driver.findElement(By.xpath("//select[@class=\"product_sort_container\"]"));
		sort.click();
		Select oselect=new Select(driver.findElement(By.xpath("//select[@class=\"product_sort_container\"]")));
		oselect.selectByValue("lohi");

		List<Double> actualPrices = driver.findElements(By.className("inventory_item_price"))
				.stream()
				.map(e -> Double.parseDouble(e.getText().replace("$", "").trim())) // Remove "$" and convert to Double
				.collect(Collectors.toList());

		// Validate if the actual list is sorted correctly
		boolean isSorted = actualPrices.stream().sorted().collect(Collectors.toList()).equals(actualPrices);

		Assert.assertTrue(isSorted, "❌ Products are NOT sorted correctly!");
		System.out.println("\u001B[32m✅ Products are correctly sorted from Low to High!\u001B[0m");
		try {
			WebElement atc=driver.findElement(By.xpath("(//button[@class=\"btn_primary btn_inventory\"])[1]"));
			boolean  visibilityofstc=atc.isDisplayed();

			if(visibilityofstc==true) {
				WebDriverWait owait=new WebDriverWait(driver, Duration.ofSeconds(maximumwait));
				owait.until(ExpectedConditions.visibilityOf(atc));
				atc.click();
				System.out.println("Add to cart button is clicked");
			}else {
				System.out.println("Add to cart button is not visible");
			}

		}catch (Exception e) {
			System.out.println("Element is not clickable"+e.getMessage());
		}
	}
}
