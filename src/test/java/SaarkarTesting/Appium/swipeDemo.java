package SaarkarTesting.Appium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;

public class swipeDemo extends BaseTest {
	@Test
	public void swipeDemoTest() throws InterruptedException {
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElement(AppiumBy.accessibilityId("Gallery")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"1. Photos\"]")).click();
		WebElement firstPic = driver.findElement(By.xpath("(//android.widget.ImageView)[1]"));
		Assert.assertEquals(firstPic.getAttribute("focusable"), "true");
		swipeGesture(firstPic, "left");
		Assert.assertEquals(firstPic.getAttribute("focusable"), "false");
	}
}
