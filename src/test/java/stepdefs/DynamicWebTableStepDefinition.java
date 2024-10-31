package stepdefs;


import drivermanager.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.DynamicWebTablePage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class DynamicWebTableStepDefinition {
	private WebDriver driver;
	private WebDriverWait wait;

	private DynamicWebTablePage dynamicPage;

	public DynamicWebTableStepDefinition() throws Exception {
		driver = DriverManager.getInstance();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		dynamicPage = new DynamicWebTablePage(driver, wait);

	}


	@Given("^A web browser is at Techlistic dynamic web table page$")
	public void AWebBrowserIsAtTechlisticDynamicWebTablePages() {
		DriverManager.getInstance()
				.get("https://www.techlistic.com/2017/02/automate-demo-web-table-with-selenium.html");
	}

	@When("^user gets no of rows and columns from dynamic web table and validates$")
	public void userGetsNoOfRowsAndColumnsFromDynamicWebTableAndValidates() throws Exception {
		List<String> ColumnList = dynamicPage.getColumnNames();

		System.out.println("No of Columns are :" + ColumnList.size());

		//To find no of Rows, first row is heading
		List<WebElement> RowList = dynamicPage.getWebTableRowCount();
		System.out.println("No of Rows are :" + RowList.size());
	}


	@When("^user gets all the values from dynamic web table and validates$")
	public void userGetsAllTheValuesFromDynamicWebTableAndValidates() throws Exception {
           List<String> tableData = new ArrayList<>();
		   tableData = dynamicPage.getWebTableData();
		   for(String str: tableData){
			   System.out.println(str);
		   }

	}
}

