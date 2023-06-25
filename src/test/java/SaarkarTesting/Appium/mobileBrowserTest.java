package SaarkarTesting.Appium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

public class mobileBrowserTest extends BrowserBaseTest{
	@Test
	public void chromeDemo() throws InterruptedException {
		
//		driver.get("http://google.com");
//		Thread.sleep(10000);
//		System.out.println(driver.getTitle());
//		driver.findElement(By.name("q")).sendKeys("linkedin");
//		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
//		Thread.sleep(6000);
		
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		Thread.sleep(10000);
		driver.findElement(By.xpath("//span[@class='navbar-toggler-icon']")).click();
		Thread.sleep(3000);		
		driver.findElement(By.cssSelector(".nav-link[routerlink='/products']")).click();
		Thread.sleep(3000);	
		((JavascriptExecutor)driver).executeScript("window.scrollBy(0,1000)", "");
		String actual= driver.findElement(By.xpath("//a[contains(@href,'/products/3')]")).getText();
		Assert.assertEquals(actual, "Devops");
	}

}
