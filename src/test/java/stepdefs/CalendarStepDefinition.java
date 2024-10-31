package stepdefs;

import drivermanager.DriverManager;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CalendarPage;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class CalendarStepDefinition {
    private WebDriver driver;
    private WebDriverWait wait;

    private CalendarPage calendarPage;

    public CalendarStepDefinition() throws Exception {
        driver = DriverManager.getInstance();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        calendarPage = new CalendarPage(driver, wait);

    }
    @Given("^A web browser is at make my trip website$")
    public void loginPage() {
        DriverManager.getInstance().get("https://www.makemytrip.com/");
    }


    @When("user closes login popup on make my trip website")
    public void userClosesLoginPopupOnMakeMyTripWebsite() throws Exception {
        calendarPage.closePopUp();
    }

    @And("user clicks on calendar button to open popup")
    public void userClicksOnCalendarButtonToOpenPopup() throws Exception {
        calendarPage.clickCalenderButton();
    }


    @When("user selects date and month with$")
    public void userSelectsDateAndMonthWith(DataTable datatable) throws InterruptedException {
        List<Map<String, String>> ffElements = datatable.asMaps(String.class, String.class);
        for(Map<String, String> ffElement : ffElements){
            calendarPage.selectDate("September 2024", "24");
//            CalendarPage.selectDate(ffElement.get("date"));
        }
    }
}
