package stepdefs;

import drivermanager.DriverManager;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.SelectDropDownPage;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class SelectDropDownStepDefinition {
    private WebDriver driver;
    private WebDriverWait wait;

    private SelectDropDownPage selectDropDownPage;

    public SelectDropDownStepDefinition() throws Exception {
        driver = DriverManager.getInstance();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        selectDropDownPage = new SelectDropDownPage(driver, wait);

    }
    @Given("^A web browser is at heroku app$")
    public void loginPage() {
        DriverManager.getInstance().get("https://the-internet.herokuapp.com/");
    }

    @When("user clicks on drop down button$")
    public void userClicksOnDropDownButton() throws Exception {
        selectDropDownPage.clickOn();
    }

    @And("user select drop down value with$")
    public void userSelectDropDownValueWith(DataTable datatable) throws Exception {
        List<Map<String, String>> ffElements = datatable.asMaps(String.class, String.class);
        for(Map<String, String> ffElement : ffElements){
            selectDropDownPage.selectDropDownValue(ffElement.get("value"));
        }
    }

    @Then("validate drop value displayed")
    public void validateDropValueDisplayed() {

    }
}
