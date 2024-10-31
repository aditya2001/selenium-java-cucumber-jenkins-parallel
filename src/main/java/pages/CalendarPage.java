package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;


//import utils.BaseDriverClass;
//https://testsigma.com/blog/page-object-model-in-selenium/

public class CalendarPage extends BasePage {
	private WebDriver driver;
	private String parent;
	// Locator for Email Address

	@FindBy(xpath = "//a[text()='Dropdown']")
	@CacheLookup
	private WebElement dropDownButton;

	@FindBy(xpath = "//span[@data-cy='closeModal']")
	@CacheLookup
	private WebElement closePopUp;

	@FindBy(xpath = "//p[@data-cy='departureDate']")
	@CacheLookup
	private WebElement calendarButton;


	@FindBy(xpath = "//a[text()='JavaScript Alerts']")
	@CacheLookup
	private WebElement alertButton;

	@FindBy(xpath = "//button[text()='Click for JS Confirm']")
	@CacheLookup
	private WebElement confirmButton;

	@FindBy(xpath = "//a[text()='Entry Ad']")
	@CacheLookup
	private WebElement AddPopUp;

	@FindBy(xpath = "//p[text()='Close']/parent::div")
	@CacheLookup
	private WebElement closeButton;

	public CalendarPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickCalenderButton() throws Exception {
		click(calendarButton);
	}

	public void closePopUp() throws Exception {
		waitUntilElementVisible(closePopUp);
		click(closePopUp);
	}

	public void selectDate(String month_year, String day) throws InterruptedException {

		// For selecting month and year
		Thread.sleep(10000);
		List<WebElement> months = driver.findElements(By.xpath("//div[@class='DayPicker-Caption']/div"));
		System.out.println("months count: " + months.size());
		for (int i = 0; i < months.size(); i++) {
			// Select date corresponding to the month
			if (months.get(i).getText().equals(month_year)) {
				List<WebElement> days = driver.findElements(By.xpath("//div[text()='" + months.get(i).getText() + "']//parent::div[@class='DayPicker-Caption']/div//parent::div//following-sibling::div[@class='DayPicker-Body']//div[@class='DayPicker-Day']/div"));
				//System.out.println("days count: " + days.size());
				Thread.sleep(1000);

				for (int j = 0; j < days.size(); j++) {
					if (days.get(j).getText().equals(day)) {
						days.get(j).click();
						break;
					}
				}
			}
		}
	}
}

