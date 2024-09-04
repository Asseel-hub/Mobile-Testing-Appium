package mobileTesting;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.internal.reflect.Parameter;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class MyClass  extends  Parameters          {
	
	DesiredCapabilities caps = new DesiredCapabilities();
	AndroidDriver driver ;

	@BeforeTest
	public void MySetUp() {
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "abc");
		File myApplication= new File ("src/myApplications/calculator.apk");
		caps.setCapability(MobileCapabilityType.APP,myApplication.getAbsolutePath());

	}
	
	
	
	
	@Test (enabled = true , priority = 1)
	public void ClickOnAllButtons() throws IOException {
		
		String ActualValue;
		String ExpectedValue="Format error";
		driver=new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),caps);
		List<WebElement>allButtons = driver.findElements(By.className("android.widget.ImageButton"));
		
		for(int i=0;i<allButtons.size();i++)
		{
			
			allButtons.get(i).click();
		}
		WebElement Results=driver.findElement(By.id("com.google.android.calculator:id/result_preview"));
		 ActualValue=Results.getText();
		Assert.assertEquals(ActualValue, ExpectedValue);
		
		takeScreenShotFun(driver);

	}
	
	
	@Test(enabled = true ,priority = 2)
	public void ClickOnNumbersOnly() throws IOException {
		String Actual;
		String Expected="7894561230";
		
		driver=new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),caps);
		List<WebElement>allButtons = driver.findElements(By.className("android.widget.ImageButton"));
		for(int i=0;i<allButtons.size();i++)
		{if(allButtons.get(i).getAttribute("resource-id").contains("digit"))
			
			allButtons.get(i).click();
		}
		
		WebElement formula= driver.findElement(By.id("com.google.android.calculator:id/formula"));
		Actual=formula.getText();
		Assert.assertEquals(Actual,Expected);

		takeScreenShotFun(driver);
		
		
	}
	
	



	@Test (enabled = false)
	public void checkTime () {
		

		Date date = new Date();
		System.out.println(date .toString().replace(":","-"));
		
	}
	
	
	
	@Test(priority = 3)
	public void clickonevenNumbers() throws IOException {
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);

		List<WebElement> allButtons = driver.findElements(By.className("android.widget.ImageButton"));

		for (int i = 0; i < allButtons.size(); i++) {

//			if (allButtons.get(i).getAttribute("resource-id").contains("digit_2")
//					|| allButtons.get(i).getAttribute("resource-id").contains("digit_4")
//					|| allButtons.get(i).getAttribute("resource-id").contains("digit_6")
//					|| allButtons.get(i).getAttribute("resource-id").contains("digit_8")
//					|| allButtons.get(i).getAttribute("resource-id").contains("digit_0")) {
//				allButtons.get(i).click();
//			} else {
//				continue;
//			}

			if (allButtons.get(i).getAttribute("resource-id").contains("digit")) {

				int number = Integer.parseInt(allButtons.get(i).getAttribute("content-desc"));

				if (number % 2 == 0) {
					System.out.println(number);
					allButtons.get(i).click(); 
				}
			}

		}

		takeScreenShotFun(driver);
	}

	
	
	
	
	
}
