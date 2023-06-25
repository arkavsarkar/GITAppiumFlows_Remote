package SaarkarTesting.Appium;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest {
	AndroidDriver driver;
	AppiumDriverLocalService service;

	@BeforeClass
	public void serverStart() throws MalformedURLException {
		// starting appium server automativcally
		service = new AppiumServiceBuilder()
				.withAppiumJS(
						new File("C:\\Users\\arkav\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723).build();

		service.start();
//		

		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("ArkavEmulator");
		options.setChromedriverExecutable("C:\\Appium\\driver\\chromedriver_91.4\\chromedriver.exe");
//		options.setApp("C:\\Selenium\\PROG3\\Appium\\src\\test\\java\\resources\\ApiDemos-debug.apk");
	options.setApp("C:\\Selenium\\PROG3\\Appium\\src\\test\\java\\resources\\General-Store.apk");

		driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@AfterClass
	public void serverStop() {
		driver.quit();
		service.stop();
	}

	public void swipeGesture(WebElement firstPic, String direction) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) firstPic).getId(),
//optional		"left", 100, "top", 100, "width", 200, "height", 200,

						"direction", direction, "percent", 0.75));
		Thread.sleep(2000);
	}

	public void scrollGesture(String direction) {
		// No prior data
		// it will scroll until there are elements to be scrolled
		boolean canScrollMore;
		do {
			canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap
					.of("left", 100, "top", 100, "width", 200, "height", 600, "direction", direction, "percent", 50.0));
		} while (canScrollMore);
	}
	public void gestureLongPress(WebElement ele) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) ele).getId(),"duration",2000));
		Thread.sleep(2000);
	}
	public Double formatStringDouble(String cartPrice) {
		String cartValueString= cartPrice.substring(1);
		Double cartValueDouble= Double.parseDouble(cartValueString);
		return cartValueDouble;
	}
}
