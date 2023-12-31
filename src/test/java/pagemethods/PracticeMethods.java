package pagemethods;

import java.awt.AWTException;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageobject.PracticeOR;

public class PracticeMethods {

	public static String filePath = "TestData/labodhi.jpeg";

	final static Logger logger = LogManager.getLogger(PracticeMethods.class);

	public static void launchBrowser(WebDriver driver, String url) throws InterruptedException {
		try {
			driver.get(url);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		} catch (TimeoutException e) {
			logger.info("URL is taking time to load", e);

		}

		logger.info("Browser is launched successfully");
	}

	public static void enterFnameLnameEmail(WebDriver driver, String fname, String lname, String email)
			throws Throwable {
		try {
//driver.findElement(RegisterOR.SIGNUP_FIRSTNAME).sendKeys("Supriya");
			driver.findElement(PracticeOR.FIRST_NAME).sendKeys(fname);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			driver.findElement(PracticeOR.LAST_NAME).sendKeys(lname);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			driver.findElement(PracticeOR.EMAIL).sendKeys(email);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		} catch (NoSuchWindowException e) {
			logger.info("Not able to locate first name , last name and email Text box", e);
		} catch (TimeoutException e) {
			logger.info("TimeoutException in first name , last name and email Text box", e);
		}
		logger.info("Values are enetered into first name , last name and email Text box");

	}

	public static void enteraddress(WebDriver driver, String address) throws InterruptedException, AWTException {
		try {
			driver.findElement(PracticeOR.CURRENT_ADDRESS).sendKeys(address);

		} catch (NoSuchElementException e) {
			logger.info("Unable to locate CURRENTADDRESS on registration form", e);
		} catch (TimeoutException e) {

			logger.info("TimeoutException in CURRENTADDRESS on registration form", e);
		}
		logger.info("enteraddress is executed successfully on Student form");
	}

	public static void enterPhone(WebDriver driver, String Phonenumber) throws InterruptedException {
		try {
			driver.findElement(PracticeOR.PHONE).sendKeys(Phonenumber);

		} catch (NoSuchElementException e) {
			System.out.println("Unable to locate element" + e);
		} catch (TimeoutException e) {
			e.printStackTrace();

		}
		logger.info("enterPhone is executed successfully on Student form");
	}

	public static void selectGender(WebDriver driver) throws AWTException {
		try {
			
			WebElement gender = driver.findElement(PracticeOR.GENDER_FEMALE);

			Actions actions = new Actions(driver);
			actions.moveToElement(gender).click().build().perform();

		} catch (NoSuchElementException e) {
			System.out.println("Unable to locate element" + e);
		} catch (TimeoutException e) {
			e.printStackTrace();

		}
	}

	public static void confirmRegister(WebDriver driver) throws InterruptedException {
		try {
			WebElement element = driver.findElement(PracticeOR.CONFIRMATION_POPUP);
			Actions actions = new Actions(driver);
			Thread.sleep(1000);
			actions.moveToElement(element).click().build().perform();
		} catch (NoSuchElementException e) {
			// TODO: handle exception
		}

	}

	public static void enterSubject(WebDriver driver, String sub) throws InterruptedException, AWTException {
		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			// Inside DOM
			WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(PracticeOR.AUTOSUGGESTION));

			ele.sendKeys(sub);
			Thread.sleep(1000); // out of selenium

			driver.findElement(PracticeOR.AUTOSUGGESTION_OPTIONS).click();

		} catch (NoSuchElementException e) {
			System.out.println("Unable to locate element" + e);
		} catch (TimeoutException e) {
			e.printStackTrace();

		}
	}

	public static void selectHobbies(WebDriver driver, String hob) {
		try {
			System.out.println(hob);
//			JavascriptExecutor jse = (JavascriptExecutor) driver;
//			jse.executeScript("window.scrollBy(0,250)");

			// If you dont use the scroldown code , it is opening the google ad
			WebElement element = driver.findElement(PracticeOR.HOBBIES);
			Actions actions = new Actions(driver);
			actions.moveToElement(element).click().build().perform();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		} catch (NoSuchElementException e) {
			System.out.println("Unable to locate element" + e);
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
	}

	public static void selectDate(WebDriver driver) {
		try {
			WebElement element = driver.findElement(PracticeOR.DATEPICKER);
			Actions actions = new Actions(driver);
			actions.moveToElement(element).click().build().perform();

			Select month = new Select(driver.findElement(PracticeOR.DATE_MONTH));
			month.selectByIndex(0);

			Select year = new Select(driver.findElement(PracticeOR.DATE_YEAR));
			year.selectByValue("2023");

			List<WebElement> day = driver.findElements(PracticeOR.DATE);

			for (WebElement myday : day) {
				System.out.println(myday);
				myday.click();
				break;
			}

		} catch (NoSuchElementException e) {
			System.out.println("Unable to locate element" + e);
		} catch (TimeoutException e) {
			e.printStackTrace();

		}
	}

	public static void uploadImage(WebDriver driver) throws InterruptedException {
		try {
			WebElement UploadImg = driver.findElement(PracticeOR.UPLOAD_IMAGE);
			Thread.sleep(3000);
			UploadImg.sendKeys(new java.io.File(filePath).getAbsolutePath());

			Thread.sleep(3000);
		} catch (NoSuchElementException e) {
			System.out.println("Unable to locate element" + e);
		} catch (NoSuchWindowException e) {
			e.printStackTrace();

		}
	}

	public static void selectState(WebDriver driver, String state) {
		try {

			WebElement element = driver.findElement(PracticeOR.STATE);
			Actions actions = new Actions(driver);
			actions.moveToElement(element).click().build().perform();

			WebElement se = driver.findElement(PracticeOR.STATE_VALUE);
			se.click();
			System.out.println("Entered state as " + state);
		} catch (NoSuchElementException e) {
			System.out.println("Unable to locate element" + e);
		} catch (TimeoutException e) {
			e.printStackTrace();

		}
	}

	public static void selectcity(WebDriver driver, String city) {
		try {
			driver.findElement(PracticeOR.CITY).click();
			WebElement se = driver.findElement(PracticeOR.CITY_VALUE);
			se.click();
			System.out.println("Entered city as" + city);
		} catch (NoSuchElementException e) {
			System.out.println("Unable to locate element" + e);
		} catch (TimeoutException e) {
			e.printStackTrace();

		}
	}

	public static void Submit(WebDriver driver) throws InterruptedException {
		try {
			WebElement element = driver.findElement(PracticeOR.SUBMIT);
			Actions actions = new Actions(driver);
			Thread.sleep(1000);
			actions.moveToElement(element).click().build().perform();

		} catch (NoSuchElementException e) {
			System.out.println("Unable to locate Submit button on the Practice form" + e);
		} catch (TimeoutException e) {
			e.printStackTrace();

		}
	}
}
