package package1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Basics1 {

	public static void main(String[] args) {
		System.setProperty("webdriver.gecko.driver","D:\\paths\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		//System.setProperty("webdriver.chrome.driver","D:\\paths\\chromedriver.exe");
		//WebDriver driver = new ChromeDriver();
		
		String baseUrl = "http://newtours.demoaut.com";
		
		String expectedTitle = "Welcome: Mercury Tours";
		String actualTitle = "";
		
		driver.get(baseUrl);
		System.out.println(System.currentTimeMillis());
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(System.currentTimeMillis());
		
		actualTitle = driver.getTitle();
		
		if(actualTitle.contentEquals(expectedTitle)){
			System.out.println("Test Passed!");
		}
		else {
			System.out.println(driver.getTitle());
			System.out.println("Test Failed!!");
		}
		driver.quit();
		driver.close();
		
		System.exit(0);

	}

}
