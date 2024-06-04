package testbase;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class TestBase 
{

	public WebDriver driver;
	public Properties prop;
	public WebDriverWait wait;
	
	@BeforeTest
	public void setUp() throws IOException
	{
		FileReader file =new FileReader("./src/test/resources/config.properties");
		prop=new Properties();
		prop.load(file);
		
		
		driver=new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.get(prop.getProperty("url"));
		//driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    wait=new WebDriverWait(driver,Duration.ofSeconds(30));
	}
	
	@AfterTest
	public void tearDown() throws InterruptedException
	{   Thread.sleep(10000);
		driver.quit();
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
