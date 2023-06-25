package SaarkarTesting.Appium;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class Miscellenious extends BaseTest {
	@SuppressWarnings("deprecation")
	@Test
	public void miscTest() throws MalformedURLException, InterruptedException {

		// xpath, id,classname, {accessibility id, AndroidUIautomator}
		//to start the app from the specific page skipping all navigation page//uncomment the app from the base test
		Activity action= new Activity("io.appium.android.apis","io.appium.android.apis.preference.PreferenceDependencies");
		driver.startActivity(action);
//		driver.findElement(AppiumBy.accessibilityId("Preference")).click();
//		driver.findElement(By.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']")).click();
		driver.findElement(By.id("android:id/checkbox")).click();
//device rotation
		DeviceRotation landScape= new DeviceRotation(0, 0, 90);
//		driver.rotate(landScape);
		
		
		driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
		String getTEXT = driver.findElement(AppiumBy.className("android.widget.TextView")).getText();
		System.out.println(getTEXT);
		Assert.assertEquals(getTEXT, "WiFi settings");
		
		//copyPaste to clipboard and then retrive
		driver.setClipboardText("arkav");
		driver.findElement(AppiumBy.className("android.widget.EditText")).sendKeys(driver.getClipboardText());
		driver.findElement(By.id("android:id/button1")).click();
		Thread.sleep(2000);
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		Thread.sleep(2000);
		driver.pressKey(new KeyEvent(AndroidKey.HOME));
		
		

	}

}
