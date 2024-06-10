package almosafer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class myTestcases {
	
	WebDriver driver= new ChromeDriver();
	String theUrl = "https://www.almosafer.com/en?ncr=1";
	
	@BeforeTest
	
	public void mySetup() {
		
		driver.get(theUrl);
		
	}
	
	
	@Test
	
	public void logIn() {
		
		
		
		
	}

}
