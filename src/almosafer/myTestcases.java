package almosafer;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.checkerframework.checker.units.qual.Length;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import net.bytebuddy.implementation.bytecode.ByteCodeAppender.Size;

public class myTestcases {

	WebDriver driver = new ChromeDriver();

	String theUrl = "https://www.almosafer.com/en?ncr=1";

	String ExpectedEnLanaguge = "en";
	String ExpectedArLanaguge = "ar";
	String ExpectedCurrency = "SAR";
	String ExpectedContactNum = "+966554400000";
	boolean ExpectedQitafLogoIsThere = true;
	String ExpectedHotelsTap = "false";
	Random rand = new Random();

	@BeforeTest

	public void mySetup() {
		driver.manage().window().maximize();

		driver.get(theUrl);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));

		// driver.findElement(By.cssSelector(".sc-jTzLTM.hQpNle.cta__button.cta__saudi.btn.btn-primary")).click();

	}

	@Test(priority = 1)

	public void testDefaultLanguage() {

		WebElement htmlTag = driver.findElement(By.tagName("html"));

		String actualLanguageOnWebsite = htmlTag.getAttribute("lang");

		Assert.assertEquals(ExpectedEnLanaguge, actualLanguageOnWebsite);

	}

	@Test(priority = 2)

	public void CheckTheCurrencyIsSAR() {

		String ActualCurrency = driver.findElement(By.xpath("//button[@data-testid='Header__CurrencySelector']"))
				.getText();

		Assert.assertEquals(ActualCurrency, ExpectedCurrency);
	}

	@Test(priority = 3)

	public void CheckTheContactnumber() {

		String ActualContactnumber = driver.findElement(By.tagName("strong")).getText();

		Assert.assertEquals(ActualContactnumber, ExpectedContactNum);

	}

	@Test(priority = 4)
	public void CheckQitafLogoIfDisplayed() {
		WebElement FooterTag = driver.findElement(By.tagName("footer"));
		boolean ActualQitaflogo = FooterTag.findElement(By.cssSelector(".sc-fihHvN.eYrDjb"))
				.findElement(By.tagName("svg")).isDisplayed();

		org.testng.Assert.assertEquals(ActualQitaflogo, ExpectedQitafLogoIsThere);
	}

	@Test(priority = 5)

	public void VerifyTheHotelsTapIsNotselected() {

		String ActualValue = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"))
				.getAttribute("aria-selected");

		Assert.assertEquals(ActualValue, ExpectedHotelsTap);

	}

	@Test(priority = 6)

	public void FligtDepartureAndReturneDate() {

		LocalDate today = LocalDate.now();

		int ExpectedDepartureDay = today.plusDays(1).getDayOfMonth();
		int ExpectedReturneDay = today.plusDays(2).getDayOfMonth();

		String ActualDepartureDay = driver
				.findElement(By.cssSelector("div[class='sc-iHhHRJ sc-kqlzXE blwiEW'] span[class='sc-cPuPxo LiroG']"))
				.getText();

		String ActualReturneDay = driver
				.findElement(By.cssSelector("div[class='sc-iHhHRJ sc-OxbzP edzUwL'] span[class='sc-cPuPxo LiroG']"))
				.getText();

		int ActualDepartureDayAsInt = Integer.parseInt(ActualDepartureDay);
		int ActualReturneAsInt = Integer.parseInt(ActualReturneDay);

		Assert.assertEquals(ExpectedDepartureDay, ActualDepartureDayAsInt);
		Assert.assertEquals(ExpectedReturneDay, ActualReturneAsInt);

	}

	@Test(priority = 8)

	public void ChangeLanguageRandom() {

		String[] Wibsites = { "https://www.almosafer.com/en", "https://www.almosafer.com/ar" };

		int RandomIndex = rand.nextInt(Wibsites.length);

		driver.get(Wibsites[RandomIndex]);

		if (driver.getCurrentUrl().contains("en")) {

			WebElement htmlTag = driver.findElement(By.tagName("html"));

			String actualLanguageOnWebsite = htmlTag.getAttribute("lang");

			Assert.assertEquals(ExpectedEnLanaguge, actualLanguageOnWebsite);
		} else if (driver.getCurrentUrl().contains("ar")) {

			WebElement htmlTag = driver.findElement(By.tagName("html"));

			String actualLanguageOnWebsite = htmlTag.getAttribute("lang");

			Assert.assertEquals(ExpectedArLanaguge, actualLanguageOnWebsite);

		}

	}

	@Test(priority = 9)

	public void HotelsTapSelection() {

		WebElement HotelsTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		HotelsTab.click();

		WebElement SearchHotel = driver.findElement(By.cssSelector(".sc-phbroq-2.uQFRS.AutoComplete__Input"));

		if (driver.getCurrentUrl().contains("en")) {

			String[] EnCeties = { "Dubai", "Jeddeh", "Riyadh" };
			int RandomIndx = rand.nextInt(EnCeties.length);
			SearchHotel.sendKeys(EnCeties[RandomIndx]);
		} else if (driver.getCurrentUrl().contains("ar")) {

			String[] Arceties = { "دبي", "جدة" };
			int RandomIndx = rand.nextInt(Arceties.length);
			SearchHotel.sendKeys(Arceties[RandomIndx]);

		}
	}

	@Test(priority = 10)

	public void SelectRandomNumOfPeople() {

		WebElement SelectPeople = driver
				.findElement(By.xpath("//select[@data-testid='HotelSearchBox__ReservationSelect_Select']"));

		Select MySelector = new Select(SelectPeople);

		int RandomIndex = rand.nextInt(2);

		MySelector.selectByIndex(RandomIndex);

		driver.findElement(By.xpath("//button[@data-testid='HotelSearchBox__SearchButton']")).click();

	}

	@Test(priority = 11)

	public void RuseltBar() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));

		WebElement RuseltTap = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//span[@data-testid='HotelSearchResult__resultsFoundCount']")));

		Assert.assertEquals(RuseltTap.getText().contains("found") || RuseltTap.getText().contains("وجدنا"), true);

	}

	@Test(priority = 12)

	public void SortItemsPriceLowToHigh() throws InterruptedException {

		WebElement lowestbtn = driver
				.findElement(By.xpath("//button[@data-testid='HotelSearchResult__sort__LOWEST_PRICE']"));
		lowestbtn.click();

		Thread.sleep(2000);

		WebElement Pricescontainer = driver.findElement(By.cssSelector(".sc-htpNat.KtFsv.col-9"));

		List<WebElement> prices = Pricescontainer.findElements(By.className("Price__Value"));

		String firstprice = prices.get(0).getText();
		String lastprice = prices.get(prices.size() - 1).getText();

		int firstpriceASint = Integer.parseInt(firstprice);
		int lastpriceAsint = Integer.parseInt(lastprice);

		Assert.assertEquals(firstpriceASint < lastpriceAsint, true);

	}

}
