package SaarkarTesting.Appium;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class eCommerce_TC_3  extends BaseTest{
	@Test
	public void formFill() throws InterruptedException {
		driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
		driver.findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Bhutan\"));"));
		driver.findElement(By.xpath("//android.widget.TextView[@text='Bhutan']")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Deru");
		//need to hide the keyboard
		driver.hideKeyboard();
		
		driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		Thread.sleep(3000);
		driver.findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Jordan Lift Off\"));"));
		Thread.sleep(3000);
		List<WebElement> productList= driver.findElements(By.xpath("//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productName']"));
		System.out.println(productList.size());
		for(int i=0; i<productList.size();i++) {
			String productnmae= driver.findElements(By.xpath("//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productName']")).get(i).getText();
			if(productnmae.equalsIgnoreCase("Jordan Lift Off")) {
				driver.findElements(By.xpath("//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productAddCart']")).get(i).click();
			}	else
			{
				driver.findElements(By.xpath("//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productAddCart']")).get(i).click();
			}
		}
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		Thread.sleep(5000);
		WebDriverWait wt= new WebDriverWait(driver,Duration.ofSeconds(10));
		wt.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));
//		String lstPrdctName= driver.findElement(By.id("com.androidsample.generalstore:id/productName")).getText();
//		Assert.assertEquals(lstPrdctName, "Jordan Lift Off");
		List<WebElement> cartList= driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
		double sum=0;
		for(int i=0; i< cartList.size();i++) {
			String cartPrice= driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(i).getText();
			Double cartValueDouble= formatStringDouble(cartPrice);
			sum= sum+cartValueDouble;
			
		}
		System.out.println(sum);
		String checkoutString= driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
		Double checkoutDouble= formatStringDouble(checkoutString);
		System.out.println(checkoutDouble);
		Assert.assertEquals(checkoutDouble, sum);
	}

}
