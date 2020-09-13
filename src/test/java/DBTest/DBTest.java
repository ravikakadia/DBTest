package DBTest;

import java.util.List;

import org.apache.commons.logging.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.common.base.Splitter;

import org.apache.log4j.Logger;

import java.io.IOException;




public class DBTest {

	private WebDriver driver;
	static Logger log = Logger.getLogger(DBTest.class.getName());
	String text;
	
	private static String userName = "kakadiaravi";
	@BeforeMethod
	public void openBrowser() {
		System.setProperty("webdriver.gecko.driver","C:\\Users\\rakakadi\\Downloads\\geckodriver-v0.24.0-win64\\geckodriver.exe");
		driver = new FirefoxDriver();
	}
	
	@Test
	public void twitterTest() throws Exception {
		driver.manage().window().maximize();
		log.info("Opening Twitter login page");
		driver.get("https://twitter.com/login");
		log.info("enter User Name");
		WebDriverWait wait=new WebDriverWait(driver,5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='session[username_or_email]']")));
		driver.findElement(By.xpath("//input[@name='session[username_or_email]']")).sendKeys(userName);
		log.info("enter Password");
		driver.findElement(By.xpath("//input[@name='session[password]']")).sendKeys("Testuser123$");
		log.info("Click Log In Button");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Log in']")));
		WebElement logIn = driver.findElement(By.xpath("//span[text()='Log in']"));
		logIn.click();
		log.info("Verify that user has logged in");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Ravi Kakadia')]")));
		driver.findElement(By.xpath("//span[contains(text(),'Ravi Kakadia')]"));
		log.info("Enter User Profile");
		driver.get("https://twitter.com/"+userName);
		driver.findElement(By.xpath("//span[contains(text(),'Edit profile')]")).click();
		log.info("Update Profile fields using Edit Profile modal");
		driver.findElement(By.xpath("//textarea[@placeholder='Add your bio']")).clear();
		driver.findElement(By.xpath("//textarea[@placeholder='Add your bio']")).sendKeys("Selenium Automation user");
		driver.findElement(By.xpath("//input[@placeholder='Add your location']")).clear();
		driver.findElement(By.xpath("//input[@placeholder='Add your location']")).sendKeys("Pune");
		driver.findElement(By.xpath("//input[@placeholder='Add your website']")).clear();
		driver.findElement(By.xpath("//input[@placeholder='Add your website']")).sendKeys("twitter.com");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Save')]")));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Save')]")));
		log.info("Verifying the profile fields on profile page after save");
		driver.findElement(By.xpath("//span[contains(text(),'Save')]")).click();
		String bio = driver.findElement(By.xpath("//span[contains(text(),'Selenium Automation user')]")).getText();
		Assert.assertEquals(bio, "Selenium Automation user");
		String location = driver.findElement(By.xpath("//span[contains(text(),'Pune')]")).getText();
		Assert.assertEquals(location, "Pune");
		String webSite = driver.findElement(By.xpath("//a[contains(text(),'twitter.com')]")).getText();
		Assert.assertEquals(webSite, "twitter.com");
		log.info("Search for Time of India");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search Twitter']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Search Twitter']")));
		WebElement search = driver.findElement(By.xpath("//input[@placeholder='Search Twitter']"));
		Actions action = new Actions(driver);
		action.moveToElement(search).click().perform();
		driver.findElement(By.xpath("//input[@placeholder='Search Twitter']")).sendKeys("Times Of India");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'The Times Of India')]")));
		WebElement searchResult = driver.findElement(By.xpath("//span[contains(text(),'The Times Of India')]"));
		searchResult.click();
		action.moveToElement(searchResult).click().perform();
		
		log.info("Read Tweets on timeline which are not older than 2 Hrs and if the length is > 50 characters split it");
		driver.navigate().refresh();
		Thread.sleep(5000);
		while (true) {
			
			List<WebElement> time = driver.findElements(By.xpath("//time"));
			for(WebElement t : time) {
				if(!t.getText().contains("2h")) {
					text = driver.findElement(By.xpath("//body/div[@id='react-root']/div[@class='css-1dbjc4n r-13awgt0 r-12vffkv']/div[@class='css-1dbjc4n r-13awgt0 r-12vffkv']/div[@class='css-1dbjc4n r-18u37iz r-13qz1uu r-417010']/main[@class='css-1dbjc4n r-1habvwh r-16y2uox r-1wbh5a2']/div[@class='css-1dbjc4n r-150rngu r-16y2uox r-1wbh5a2 r-rthrr5']/div[@class='css-1dbjc4n r-aqfbo4 r-16y2uox']/div[@class='css-1dbjc4n r-1oszu61 r-1niwhzg r-18u37iz r-16y2uox r-1wtj0ep r-2llsf r-13qz1uu']/div[@class='css-1dbjc4n r-14lw9ot r-1tlfku8 r-1ljd8xs r-13l2t4g r-1phboty r-1jgb5lz r-11wrixw r-61z16t r-1ye8kvj r-13qz1uu r-184en5c']/div[@class='css-1dbjc4n']/div[@class='css-1dbjc4n']/div[@class='css-1dbjc4n r-16y2uox']/div[@class='css-1dbjc4n r-1jgb5lz r-1ye8kvj r-13qz1uu']/div[@class='css-1dbjc4n']/section[@class='css-1dbjc4n']/div[@class='css-1dbjc4n']/div/div[1]/div[1]/div[1]/article[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]")).getText();
					if (text.length()>50) {
						Iterable<String> pieces = Splitter.fixedLength(50).split(text);
						System.out.println(pieces);
					}
				}
			}
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		
		}
		
		
	}
	
	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}
}

