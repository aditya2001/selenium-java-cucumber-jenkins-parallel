package runner;

import drivermanager.DriverManager;
import io.cucumber.testng.*;
import org.testng.annotations.*;
import utils.PropertyUtils;
import params.GlobalParams;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;


@CucumberOptions(
        tags = "not @ignore",
        plugin = {"pretty",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "timeline:test-output-thread/",
                "rerun:target/failedrerun.txt"
        },
        monochrome = true,
        glue = { "stepdefs" },
        features = { "src/test/resources/features" }
)

public class TestRunner extends RunnerBase {

}