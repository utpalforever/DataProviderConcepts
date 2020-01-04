package testCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testUtilExcelSheet.ExcelSheetUtility;

public class LoginPageTest {

	WebDriver driver;

	@BeforeMethod
	public void setup() {

		System.setProperty("webdriver.chrome.driver",
				"E:\\Shiv@1008\\SeleniumBackEnd\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get("https://www.freecrm.com/");
	}

	@DataProvider
	public Object[][] getTestDataFromExcelSheet() {
		Object data[][] = ExcelSheetUtility.getTestData("loginPage");// sheet name present inside excel sheet
		return data;
	}

	@Test(dataProvider = "getTestDataFromExcelSheet", priority = 1)
	public void loginTest1(String username, String password) {

		driver.findElement(By.xpath("//a[contains(@class, 'btn btn-primary btn-xs-2 btn-shadow')]")).click();

		driver.findElement(By.name("email")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.xpath("//div[text()='Login']")).click();

	}

	@Test(dataProvider = "getTestDataFromExcelSheet")
	public void loginTest2(String username, String password) {

		driver.findElement(By.xpath("//a[contains(@class, 'btn btn-primary btn-xs-2 btn-shadow')]")).click();

		driver.findElement(By.name("email")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();

	}

}
