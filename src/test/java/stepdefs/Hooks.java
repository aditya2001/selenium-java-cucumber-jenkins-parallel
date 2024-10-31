package stepdefs;

import drivermanager.DriverManager;
import org.apache.commons.io.FileUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.PropertyUtils;

import java.io.File;
import java.io.IOException;

public class Hooks {

//    private DriverFactory driverFactory;
    private WebDriver driver;

    @Before(order = 0)
    public void initializeDriver() {
//        String browser = PropertyUtils.getBrowserType();
//        driver = DriverFactory.initDriver(browser);

    }

//    @After(order = 0)
//    public void quitBrowser() {
//        DriverManager.getInstance().quit();
////        DriverManager.unload();
//    }

    @After(order = 1)
    public void tearDown(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            // take screenshot:
            String screenshotName = scenario.getName().replaceAll(" ", "_");
            //Use TakesScreenshot method to capture screenshot
            TakesScreenshot screenshot = (TakesScreenshot)driver;
            //Saving the screenshot in desired location
            File source = screenshot.getScreenshotAs(OutputType.FILE);
            //Saving the screenshot in desired location
            FileUtils.copyFile(source, new File("./SeleniumScreenshots/" + screenshotName + ".png"));
            System.out.println("Screenshot is captured");
        }
    }

}
