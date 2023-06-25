package SaarkarTesting.Appium;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class eCommerce_TC_4 extends BaseTest {
	@Test
	public void formFill() throws InterruptedException {
		driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
		driver.findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Bhutan\"));"));
		driver.findElement(By.xpath("//android.widget.TextView[@text='Bhutan']")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Deru");
		// need to hide the keyboard
		driver.hideKeyboard();

		driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		Thread.sleep(3000);
		driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();

		Thread.sleep(2000);
		driver.findElement(AppiumBy
				.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Jordan Lift Off\"));"));
		Thread.sleep(1000);
		List<WebElement> productList = driver.findElements(
				By.xpath("//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productName']"));
		System.out.println(productList.size());
		for (int i = 0; i < productList.size(); i++) {
			String productnmae = driver
					.findElements(By.xpath(
							"//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productName']"))
					.get(i).getText();
			if (productnmae.equalsIgnoreCase("Jordan Lift Off")) {
				driver.findElements(By.xpath(
						"//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productAddCart']"))
						.get(i).click();
			} else {
				driver.findElements(By.xpath(
						"//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productAddCart']"))
						.get(i).click();
			}
		}
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		Thread.sleep(5000);
		WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(10));
		wt.until(ExpectedConditions.attributeContains(
				driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));
//		String lstPrdctName= driver.findElement(By.id("com.androidsample.generalstore:id/productName")).getText();
//		Assert.assertEquals(lstPrdctName, "Jordan Lift Off");
		boolean canScrollMore;
		List<WebElement> cartList= driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
		do {
			canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap
					.of("left", 100, "top", 100, "width", 200, "height", 800, "direction", "down", "percent",100.0));
			cartList = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
			Thread.sleep(3000);
		} while (canScrollMore);
		
		System.out.println("cartlist size "+ cartList.size());
		double sum = 0;
		Thread.sleep(2000);
		for (int i = 0; i < cartList.size(); i++) {
			String cartPrice = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(i)
					.getText();
			Double cartValueDouble = formatStringDouble(cartPrice);
			sum = sum + cartValueDouble;

		}
		System.out.println(sum);
		String checkoutString = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
		Double checkoutDouble = formatStringDouble(checkoutString);
		System.out.println(checkoutDouble);
		Assert.assertEquals(checkoutDouble, sum);
		WebElement termsnCond = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
		gestureLongPress(termsnCond);
		//clicking close on terms and condition 
		driver.findElement(By.id("android:id/button1")).click();
		//clcicking check out and navigating to google page.
		driver.findElement(AppiumBy.className("android.widget.Button")).click();
		Thread.sleep(10000);
		Set<String> contexts= driver.getContextHandles();
		for(String context: contexts) {
			System.out.println(context);
		}
		driver.context("WEBVIEW_com.androidsample.generalstore");
		driver.findElement(By.name("q")).sendKeys("linkedin");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		Thread.sleep(3000);
		driver.context("NATIVE_APP");
		Thread.sleep(3000);
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		Thread.sleep(3000);
		
		

	}

}
