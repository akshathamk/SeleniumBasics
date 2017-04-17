package package1;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertTrue;

public class CollegeConfidential {

	public static void main(String[] args) {
		//System.setProperty("webdriver.gecko.driver","D:\\paths\\geckodriver.exe");
		//WebDriver driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver","D:\\paths\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		String baseUrl = "http://collegeconfidential.com";
		
		driver.get(baseUrl);
		
		//to manage mindtree zscalar security
		System.out.println(System.currentTimeMillis());
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(System.currentTimeMillis());
		
		String actualTitle = driver.getTitle();
		String expectedTitle = "College Admissions, Search";
		
		if(actualTitle.contains(expectedTitle)){
			System.out.println("Test Passed!");
		}
		else {
			System.out.println(driver.getTitle());
			System.out.println("Test Failed!!");
		}
		
		driver.findElement(By.id("find_a_college_main")).click();
		
		System.out.println(driver.getTitle());
		
		//switch to frame element
		//WebElement frame = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("myDynamicElement")));
		WebDriverWait wait = new WebDriverWait(driver, 35);
		WebElement frame_element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("supermatch")));
		driver.switchTo().frame(frame_element);
		
		//click on location filter and select desired location
		driver.findElement(By.id("critHeader0")).click();
		String location = "Ohio";
		String newXpath = String.format(".//*[@id='locationCriteria']/div/div/div[2]//span[.='%s']", location);
		System.out.println(newXpath);
		driver.findElement(By.xpath(newXpath)).click();
		
        //verify desired location is added
		String selectedXpath = String.format(".//div[@class='ui-picklist-option-selected']/span[contains(text(),'%s')]",location);
		driver.findElement(By.xpath(selectedXpath)).isDisplayed();
		
		 //select the majors filter
		driver.findElement(By.id("critHeader1")).click();
		 
		 //select the degree
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String major = "Bachelor";
		String degreeXpath = String.format(".//*[@id='majorCriteria']/label//span[contains(text(),'%s')]", major);
		WebElement majors = driver.findElement(By.xpath(degreeXpath));
		assertTrue("Radio button not displayed",majors.isDisplayed());
		majors.click();
		
		
		//set preference
		String preference = "Computer Software Engineering";
		driver.findElement(By.id("majorSearchText")).sendKeys(preference);
		
		//select the preferred course from the matches
		String prefXpath = String.format(".//*[@id='matchingMajors']//span[contains(text(),'%s')]", preference);
		WebElement match = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(prefXpath)));
	    match.click();
		
		//verify desired degree is added
	    String selectedPrefXpath = String.format( ".//div[@class='ui-picklist-selected-options']/span[contains(text(),'%s')]",preference);
	    //******driver.findElement(By.xpath(selectedPrefXpath)).isDisplayed();
	    
	    //verify college name and link
	    String college = "Miami University-Oxford";
	    String resXpath = String.format(".//a[text()='%s']", college);
	    driver.findElement(By.xpath(resXpath)).isDisplayed();
	    
	    //verify percentage match
	    String complexXpath = String.format(".//a[text()='Miami University-Oxford']/ancestor::node()[contains(@class,'column3')]//preceding::div[contains(@class,'column1')]//div[@class='percentMatch']", college);
	    WebElement result = driver.findElement(By.xpath(complexXpath));
	    assertTrue("Not a 100% match",result.getText().contains("100%"));
		
		//driver.quit();
		driver.close();
		
		System.exit(0);


	}

}
