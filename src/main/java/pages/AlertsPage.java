package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;


//import utils.BaseDriverClass;
//https://testsigma.com/blog/page-object-model-in-selenium/

public class AlertsPage extends BasePage {
	private WebDriver driver;
	private String parent;
	// Locator for Email Address

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

	public AlertsPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}



	public void clickAlertPopUp() throws Exception {
		click(alertButton);
	}

	public void confirmAlertPopup() throws Exception {
		click(confirmButton);
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	public void clickAddPopup() throws Exception {
		click(AddPopUp);
	}

}

