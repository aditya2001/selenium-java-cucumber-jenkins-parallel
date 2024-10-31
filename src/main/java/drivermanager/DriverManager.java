package drivermanager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import params.GlobalParams;

public final class DriverManager {

    private DriverManager() {
    }
   // This is a thread-safe singleton class and we have made global access method synchronized so that only one thread can execute this method at a time.
    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    /**
     * this is used to get the driver with ThreadLocal
     *
     * @return
     */

    public static synchronized WebDriver getInstance() {
        if (tlDriver.get() == null) {
            throw new RuntimeException("Exception occurred in getting webdriver instance");
        }
        return tlDriver.get();
    }

    public static void setDriver(WebDriver driver2){
        tlDriver.set(driver2);
    }
    public static void initializeDriver() {

        System.out.println("Please pass the correct browser value: " );
        WebDriver driver = null;
        GlobalParams params = new GlobalParams();
//        System.out.println("browser value is: " + browser);

        if (params.getBrowserName().equals("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
//		    options.addArguments("--headless");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);
//            setDriver(new ChromeDriver(options));
        } else if (params.getBrowserName().equals("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
//            options.addArguments("-headless");
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver(options);
        } else if (params.getBrowserName().equals("safari")) {
            driver = new SafariDriver();
        } else {
            System.out.println("Please pass the correct browser value: " );
        }
        tlDriver.set(driver);
        DriverManager.getInstance().manage().deleteAllCookies();
        DriverManager.getInstance().manage().window().maximize();
    }


    public static void unload() {
        tlDriver.remove();
    }
}