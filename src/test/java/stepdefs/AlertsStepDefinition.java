package stepdefs;

import drivermanager.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.AlertsPage;

import java.time.Duration;

public class AlertsStepDefinition {
    private WebDriver driver;
    private WebDriverWait wait;

    private AlertsPage alertsPage;

    public AlertsStepDefinition() throws Exception {
        driver = DriverManager.getInstance();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        alertsPage = new AlertsPage(driver, wait);

    }
    @Given("^current web browser is at heroku app$")
    public void loginPage() {
        DriverManager.getInstance().get("https://the-internet.herokuapp.com/");
    }


    @When("user clicks on java script alert popups$")
    public void userClicksOnJavaScriptAlertPopup() throws Exception {
        alertsPage.clickAlertPopUp();
    }

    @And("confirm java script alert popups$")
    public void confirmJavaScriptAlertPopup() throws Exception {
        alertsPage.confirmAlertPopup();
    }


}
