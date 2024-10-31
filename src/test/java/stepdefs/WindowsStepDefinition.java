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
import pages.WindowsPage;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class WindowsStepDefinition {
    private WebDriver driver;
    private WebDriverWait wait;

    private WindowsPage windowsPage;

    public WindowsStepDefinition() throws Exception {
        driver = DriverManager.getInstance();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        windowsPage = new WindowsPage(driver, wait);

    }

    @When("user clicks on Entry Add window popup")
    public void userClicksOnEntryAddWindowPopup() throws Exception {
        windowsPage.clickAddPopup();
    }

    @And("close window add popup")
    public void closeWindowAddPopup() throws Exception {
        windowsPage.closeWindowAddPopUp();
    }

    @Then("click on re-enable button")
    public void clickOnReEnableButton() {
    }

    @When("user clicks on multiple windows button")
    public void userClicksOnMultipleWindowsButton() {
        windowsPage.clickWindowButton();

    }

    @And("user navigates to new window opened and print title")
    public void userNavigatesToNewWindowOpenedAndPrintTitle() {
        windowsPage.validateNewWindow();
    }

    @Then("navigate back to previous window")
    public void navigateBackToPreviousWindow() {
        windowsPage.navigateBackToMainWindow();
    }
}
