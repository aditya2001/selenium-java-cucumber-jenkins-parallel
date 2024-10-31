package pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public abstract class BasePage {
	private WebDriver driver;
	private WebDriverWait wait;

	public BasePage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}

	protected void waitUntilElementVisible(WebElement element) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
		}
		catch(ElementNotInteractableException e){
			System.out.println("Element is hidden");
		}
	}


	public void click(WebElement element) throws Exception {
		try {
			element.click();
		}
		catch(ElementNotInteractableException e){
			System.out.println("Exception not clickable");
		}

	}


	public void selectDropDown(WebElement element, String valueToSelect) throws Exception {
		Select select= new Select(element);
		try {
			select.selectByVisibleText(valueToSelect);
		}
		catch(ElementNotInteractableException e){
			System.out.println("Element not selectable");
		}
	}

	public void selectDropDown(WebElement element, int index) throws Exception {
		Select select= new Select(element);
		try {
			select.selectByIndex(index);
		}
		catch (ElementNotInteractableException e){
			System.out.println("Element not selectable");
		}
	}

	public String getWebTableCellValue(String tableXpath, int rowIndex, int columnIndex) throws Exception {
		String finalXpath = tableXpath + rowIndex + "]/td[" + columnIndex + "]";
		WebElement element = driver.findElement(By.xpath(finalXpath));
		return element.getText();
	}


	public List<String> getWebTableColumnNames(String tableXpath) throws Exception {
		String xpathInitial = tableXpath + "/tbody/tr/th";
		List<WebElement> elements = driver.findElements(By.xpath(xpathInitial));
		List<String> columnNames = new ArrayList<>();
		for(int i=1; i<=elements.size(); i++){
			String xpath = tableXpath + "/tbody/tr[1]/th[" + i + "]";
			WebElement colElement = driver.findElement(By.xpath(xpath));
			columnNames.add(colElement.getText());
		}
		return columnNames;

	}

	public String getPageTitle(){
		System.out.println("Returning Title");
		return driver.getTitle();
	}
}
