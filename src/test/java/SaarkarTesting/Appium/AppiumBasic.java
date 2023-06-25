package SaarkarTesting.Appium;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumBasic extends BaseTest {
	@Test
	public void AppiumTest1() throws MalformedURLException {

		// xpat, id,classname, {accessibility id, AndroidUIautomator}
		driver.findElement(AppiumBy.accessibilityId("Preference")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']")).click();
		driver.findElement(By.id("android:id/checkbox")).click();
		driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
		String getTEXT = driver.findElement(AppiumBy.className("android.widget.TextView")).getText();
		System.out.println(getTEXT);
		Assert.assertEquals(getTEXT, "WiFi settings");
		driver.findElement(By.className("android.widget.EditText")).sendKeys("senurczsaqwefcxzcadqeczaqrfcarwr");
		driver.findElement(By.id("android:id/button1")).click();

	}

}
