package testClasses.SmokeTest;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.*;
import testClasses.BaseTest;

import java.io.IOException;

public class PakistanEnglishSmokeTest extends BaseTest {
    public AndroidDriver mobileDriver;

    @BeforeMethod
    public void initialize() {
        System.out.println("Before Method");
        mobileDriver = initializeDriver();
    }

    @Test
    public void verifyFirstLaunchTest() throws IOException {
        ExtentTest extentTest = extentReports.createTest("[Smoke Test] Bangladesh | English ",
                "To verify Basic Functions before registration\n" +
                "1. Verify all of 4 languages options are displayed\n" +
                "2. Verify labels from Home page are displayed as expected\n" +
                "3. Verify labels from Register page are displayed as expected\n" +
                "4. Verify labels from Account page are displayed as expected\n"
        );

        try {
            SelectYourCountryPage scp = new SelectYourCountryPage(mobileDriver);

            SoftAssert softAssert = new SoftAssert();
            scp.verifyLanguageText(softAssert);
            scp.selectCountryName("Pakistan");

            IntroPage intoPage = scp.selectLanguage("ENGLISH");

            HomePage homePage = intoPage.clickIntroSkipButton();
            homePage.clickNoToSetLocation();
            homePage.clickPopUpCloseBtn();
            homePage.verifyIconText(softAssert,
                    "Scan", "Coins", "Wallet",
                    "Messages","Cart","Account");
            homePage.verifyChannelsText(softAssert,
                    "Mart", "Fashion", "Beauty",
                    "Home &\n" + "Decor", "Pay");

            RegisterPage registerPage = homePage.navigateToMessagePageWithoutLogin();
            registerPage.verifyRegisterPageText(softAssert,
                    "SHOP OVER 20 \n" + " MILLION PRODUCTS AT \n" + " UNBEATABLE PRICES",
                    "Register or Login with Mobile Number", "+92",
                    "Enter your mobile number", "Login with email",
                    "Or continue with");
            homePage = registerPage.closeRegisterPage();

            registerPage = homePage.navigateToCartPageWithoutLogin();
            registerPage.verifyRegisterPageText(softAssert,
                    "SHOP OVER 20 \n" + " MILLION PRODUCTS AT \n" + " UNBEATABLE PRICES",
                    "Register or Login with Mobile Number", "+92",
                    "Enter your mobile number", "Login with email",
                    "Or continue with");
            homePage = registerPage.closeRegisterPage();

            AccountPage accountPage = homePage.navigateToAccountPage();
            accountPage.verifyAccountPageText(softAssert,
                    "Hello, Welcome to Daraz !", "Login / Sign up",
                    "My Orders", "My Services",
                    "My Returns","My Cancellations");
            homePage = accountPage.navigateToHomePage();

            softAssert.assertAll();
        }
        catch (Exception e){
            e.printStackTrace();
            extentTest.log(Status.FAIL, e.getMessage());
            extentTest.addScreenCaptureFromBase64String(takeScreenshot());
            Assert.fail(e.getMessage());
        }
    }

    @AfterMethod
    public void tearDown(ITestResult testResult){
        System.out.println("After Method");
        this.mobileDriver.quit();
    }
}
