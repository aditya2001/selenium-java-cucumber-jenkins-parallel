package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;


//import utils.BaseDriverClass;
//https://testsigma.com/blog/page-object-model-in-selenium/

public class DynamicWebTablePage extends BasePage {
	private WebDriver driver;
	// Locator for Email Address
	private static final String webTableXpath = "//*[@id='customers']/tbody/tr[";
	private static final String rows = "//*[@id='customers']/tbody/tr";
	private static final String columns = "//*[@id='customers']";


	public DynamicWebTablePage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public List<String> getColumnNames() throws Exception {
		return getWebTableColumnNames(columns);
	}

	public List<WebElement> getWebTableRowCount() {
//		 waitUntilElementVisible(rows);
		return driver.findElements(By.xpath(rows));
	}


	public List<String> getWebTableData() throws Exception {
		List<String> columnCount = getColumnNames();
		System.out.println(columnCount.size());
		List<WebElement> rowCount = getWebTableRowCount();
		System.out.println(rowCount.size());
		List<String> ls = new ArrayList<>();
		for (int i = 2; i <= rowCount.size(); i++) {
			//Used for loop for number of columns.
			for (int j = 1; j <= columnCount.size(); j++) {
				String data = getWebTableCellValue(webTableXpath, i, j);
				ls.add(data);
			}
		}
		return ls;
	}
}

