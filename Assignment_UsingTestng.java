package Assignments;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Assignment_UsingTestng 
{
		WebDriver driver = null;
		int count =1;
		@Test
		public void Flipkart_Login() throws InterruptedException
		{
			WebDriver driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get("https://www.flipkart.com/");
			System.out.println( "Flipkart URL opened successfully");
			Boolean Value = driver.findElement(By.xpath("//input[@type=\"password\"][@class=\"_2zrpKA _3v41xv\"]")).isDisplayed();
			if(Value == true)
			{
				driver.findElement(By.xpath("//input[@type=\"text\"][@class=\"_2zrpKA\"]")).sendKeys("9986642068");
				driver.findElement(By.xpath("//input[@type=\"password\"][@class=\"_2zrpKA _3v41xv\"]")).sendKeys("Flipkart123$");
				driver.findElement(By.xpath("//button[@class=\"_2AkmmA _1LctnI _7UHT_c\"][@type=\"submit\"]")).submit();
				System.out.println("Logged into flipkart successfully");
			}
			else
			{
				driver.findElement(By.xpath("//input[@type=\"text\"][@class=\"_2zrpKA\"]")).sendKeys("9986642068");
				driver.findElement(By.xpath("//span[contains(text(),\"CONTINUE\")]")).submit();
				driver.findElement(By.xpath("//input[@type=\"password\"][@class=\"_2zrpKA _3v41xv\"]")).sendKeys("Flipkart123$");
				driver.findElement(By.xpath("//button[@class=\"_2AkmmA _1LctnI _7UHT_c\"][@type=\"submit\"]")).submit();
				System.out.println("Logged into flipkart successfully");
			}
			driver.manage().window().maximize();
			driver.navigate().refresh();
			WebElement Elements = driver.findElement(By.xpath("//span[@class=\"_1QZ6fC _3Lgyp8\"][contains(text(),\"Electronics\")]"));
			System.out.println("Traversed to Electronics section from the homescreen");
			Thread.sleep(2000);
			Elements.click();
			driver.findElement(By.xpath("//a[@title=\"Apple\"]")).click();
			String var =driver.findElement(By.xpath("//h1[contains(text(),\"Apple Store\")]")).getText();
			System.out.println("The page name is "+ var);
			driver.findElement(By.xpath("//input[@title=\"Search for products, brands and more\"]")).sendKeys("iphone 7");
			driver.findElement(By.xpath("//button[@class=\"vh79eN\"][@type=\"submit\"]")).submit();
			System.out.println("Searching for Iphone 7");
			List<WebElement> list = driver.findElements(By.className("_3wU53n"));
			int size = list.size();
			for(int i =0; i<size; i++)
			{	
				System.out.println(count + " : " + list.get(i).getText());
				count++;
			}
			driver.navigate().refresh();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//span[contains(text(),\"Next\")]")).click();
			List<WebElement> list2 = driver.findElements(By.className("_3wU53n"));
			System.out.println(count + " : " + list2.get(0).getText());
			driver.navigate().back();
			System.out.println("Selecting Apple iPhone 7 (Rose Gold, 32 GB)");
			driver.findElement(By.xpath("//div[contains(text(),\"Apple iPhone 7 (Rose Gold, 32 GB)\")]")).click();
			
			Set<String> ids = driver.getWindowHandles();
		    Iterator<String> it = ids.iterator();
		    String parentId = it.next();
		    String childId = it.next();
		    driver.switchTo().window(childId);
		    Thread.sleep(1000);
		    WebDriverWait wait = new WebDriverWait(driver, 50);
			WebElement AddtoCart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class=\"col col-6-12\"]//button[@class=\"_2AkmmA _2Npkh4 _2MWPVK\"]")));
			AddtoCart.click();	    
			System.out.println("Item added to cart successfully");
			File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			try 
			{
				FileUtils.copyFile(src, new File("C:/selenium/cart_item.png"));
			}
			catch (IOException e)
			{
				System.out.println(e.getMessage()); 
			}
			
			driver.findElement(By.xpath("//span[contains(text(),\"Remove\")]")).click();
			System.out.println("The item is removed from cart successfully");
			WebElement username = driver.findElement(By.xpath("//div[@class=\"_2aUbKa\"][contains(text(),\"Kartik\")]"));
			Actions action = new Actions(driver);
			action.moveToElement(username).build().perform();
			driver.findElement(By.xpath("//div[@class=\"_1Q5BxB\"][contains(text(),\"Logout\")]")).click();	
			System.out.println("Logged out from Flipkart successfully");
			driver.quit();
		}
		
}
	