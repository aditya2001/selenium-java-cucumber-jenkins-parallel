package runner;

import drivermanager.DriverManager;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import org.testng.annotations.*;
import params.GlobalParams;




public class RunnerBase {
    private static final ThreadLocal<TestNGCucumberRunner> testNGCucumberRunner = new ThreadLocal<>();

    public static TestNGCucumberRunner getRunner(){
        return testNGCucumberRunner.get();
    }

    private static void setRunner(TestNGCucumberRunner testNGCucumberRunner1){
        testNGCucumberRunner.set(testNGCucumberRunner1);
    }

//  @BeforeTest
//    @Override
//    @DataProvider(parallel = true)
//    public Object[][] scenarios() {
//        return super.scenarios();
//    }

    @BeforeClass(alwaysRun = true)
    @Parameters({"browser","env"})
    public void initialize(String browser, String env) throws Exception {
        System.out.println("Please pass the correct browser value: " );
        GlobalParams params = new GlobalParams();
        params.setBrowserName(browser);
        DriverManager.initializeDriver();

        setRunner(new TestNGCucumberRunner(this.getClass()));
    }

    @Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
    public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
        getRunner().runScenario(pickleWrapper.getPickle());
    }


    @DataProvider()
    public Object[][] scenarios() {
        return getRunner().provideScenarios();
    }

//  @BeforeTest
//    @Override
//    @DataProvider(parallel = true)
//    public Object[][] scenarios() {
//        return super.scenarios();
//    }

    @AfterClass(alwaysRun = true)
    public static void quit() {
//        DriverManager driverManager = new DriverManager();
        if (DriverManager.getInstance() != null) {
            DriverManager.getInstance().quit();
            DriverManager.setDriver(null);
        }
        if(testNGCucumberRunner != null){
            getRunner().finish();
        }
    }
//    @BeforeTest
//    @Override
//    @DataProvider(parallel = true)
//    public Object[][] scenarios() {
//        return super.scenarios();
//    }
}