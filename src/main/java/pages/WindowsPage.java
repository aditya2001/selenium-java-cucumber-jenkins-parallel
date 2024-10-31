package pages;

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

public class WindowsPage extends BasePage {
	private WebDriver driver;
	private String parent;
	// Locator for Email Address

	@FindBy(xpath = "//a[text()='Dropdown']")
	@CacheLookup
	private WebElement dropDownButton;

	@FindBy(xpath = "//select[@id='dropdown']")
	@CacheLookup
	private WebElement selectDropDown;


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

	public WindowsPage(WebDriver driver, WebDriverWait wait) {
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

	public void closeWindowAddPopUp() throws Exception {
		click(closeButton);
	}

	public void clickWindowButton() {
		driver.findElement(By.xpath("//a[text()='Multiple Windows']")).click();


	}

	public void validateNewWindow(){
		driver.findElement(By.xpath("//a[text()='Click Here']")).click();
		parent = driver.getWindowHandle();
		Set<String> s = driver.getWindowHandles();
		for (String childWindow : s) {
			if (!parent.equals(childWindow)) {
				driver.switchTo().window(childWindow);
				System.out.println(driver.getTitle());
				System.out.println(driver.getTitle());
				System.out.println(driver.getTitle());
				System.out.println(driver.getTitle());
				driver.close();
			}
		}
	}

	public void navigateBackToMainWindow() {
		driver.switchTo().window(parent);
	}
}