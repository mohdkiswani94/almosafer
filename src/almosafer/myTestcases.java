package almosafer;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import java.time.Duration;

import javax.print.DocFlavor.STRING;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class myTestcases {

	WebDriver driver = new ChromeDriver();

	String theUrl = "https://www.almosafer.com/en?ncr=1";

	String ExpectedLanaguge = "en";
	String ExpectedCurrency = "SAR";
	String ExpectedContactNum = "+966554400000";
    boolean ExpectedQitafLogoIsThere = true;

	@BeforeTest

	public void mySetup() {
		driver.manage().window().maximize();

		driver.get(theUrl);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));

		// driver.findElement(By.cssSelector(".sc-jTzLTM.hQpNle.cta__button.cta__saudi.btn.btn-primary")).click();

	}

	@Test

	public void testDefaultLanguage() {

		WebElement htmlTag = driver.findElement(By.tagName("html"));

		String actualLanguageOnWebsite = htmlTag.getAttribute("lang");

		Assert.assertEquals(ExpectedLanaguge, actualLanguageOnWebsite);

	}

	@Test

	public void CheckTheCurrencyIsSAR() {

		String ActualCurrency = driver.findElement(By.xpath("//button[@data-testid='Header__CurrencySelector']"))
				.getText();

		Assert.assertEquals(ActualCurrency, ExpectedCurrency);
	}

	@Test

	public void CheckTheContactnumber() {

		String ActualContactnumber = driver.findElement(By.tagName("strong")).getText();

		Assert.assertEquals(ActualContactnumber, ExpectedContactNum);

	}

	@Test
	public void CheckQitafLogoIfDisplayed() {
		WebElement FooterTag = driver.findElement(By.tagName("footer"));
		boolean ActualQitaflogo = FooterTag.findElement(By.xpath("//svg[@data-testid='Footer__QitafLogo']")).isDisplayed();
			
		org.testng.Assert.assertEquals(ActualQitaflogo, ExpectedQitafLogoIsThere);
	}

}
