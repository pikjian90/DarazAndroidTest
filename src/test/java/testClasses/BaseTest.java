package testClasses;

import Utils.DeviceCapability;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;


public class BaseTest {
    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extentReports;
    public static org.apache.log4j.Logger logger;
    public static final SimpleDateFormat dataFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
    static AndroidDriver mobileDriver;
    public String appiumServer = "127.0.0.1";
    public int appiumPort = 4723;
    URL appiumURL = null;



    @BeforeSuite
    public void beforeSuite(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/Reports/Report_" + dataFormat.format(timestamp) + ".html");
        htmlReporter.config().setDocumentTitle("Daraz Android Testing Report");
        htmlReporter.config().setReportName("Smoke Test");
        htmlReporter.config().setTheme(Theme.DARK);

        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
        extentReports.setSystemInfo("deviceName","SM-N975F/DS");
        extentReports.setSystemInfo("Environment","QA");
        extentReports.setSystemInfo("Tester","QA");

        logger = Logger.getLogger("DarazAndroid"); // added logger
        PropertyConfigurator.configure("src/test/resources/log4j.properties");
        logger.setLevel(Level.INFO);

        logger.info("Before Suite");
    }

    public AndroidDriver initializeDriver() {
        try {
            appiumURL = new URL("http://" + appiumServer + ":" + appiumPort + "/wd/hub");
            mobileDriver = new AndroidDriver(appiumURL, setAppCapabilitesAndroid());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mobileDriver;
    }

    public DesiredCapabilities setAppCapabilitesAndroid() {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("deviceName", DeviceCapability.DEVICE_NAME);
        cap.setCapability("udid", DeviceCapability.UDID);
        cap.setCapability("platformName", DeviceCapability.PLATFORM_NAME);
        cap.setCapability("platformVersion", DeviceCapability.PLATFORM_VERSION);
        cap.setCapability("appPackage", DeviceCapability.APP_PACKAGE);
        cap.setCapability("appActivity", DeviceCapability.APP_ACTIVITY);
        cap.setCapability("appium:ignoreHiddenApiPolicyError", true);
        return cap;
    }

    public String takeScreenshot() {
        String base64ScreenshotCode = ((TakesScreenshot)mobileDriver).getScreenshotAs(OutputType.BASE64);
        return base64ScreenshotCode;
    }

    @AfterSuite
    public void tearDownSuite(){
        extentReports.flush();
        logger.info("After Suite");

    }
}
