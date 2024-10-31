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

public class SelectDropDownPage extends BasePage {
	private WebDriver driver;
	private String parent;
	// Locator for Email Address

	@FindBy(xpath = "//a[text()='Dropdown']")
	@CacheLookup
	private WebElement dropDownButton;

	@FindBy(xpath = "//select[@id='dropdown']")
	@CacheLookup
	private WebElement selectDropDown;

	@FindBy(xpath = "//p[text()='Close']/parent::div")
	@CacheLookup
	private WebElement closeButton;

	@FindBy(xpath = "//a[text()='Entry Ad']")
	@CacheLookup
	private WebElement AddPopUp;

	public SelectDropDownPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickOn() throws Exception {
		click(dropDownButton);
	}

	public void selectDropDownValue(String value) throws Exception {
		selectDropDown(selectDropDown, value);
	}


	public void clickAddPopup() throws Exception {
		click(AddPopUp);
	}


}

