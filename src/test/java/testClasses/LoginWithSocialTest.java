package testClasses;

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

import java.io.IOException;

public class LoginWithSocialTest extends BaseTest {
    public AndroidDriver mobileDriver;
    private SelectYourCountryPage scp;
    private IntroPage intoPage;
    private HomePage homePage;
    private AccountPage accountPage;
    private RegisterPage registerPage;

    @BeforeMethod
    public void initialize() {
        logger.info("Before Method");
        mobileDriver = initializeDriver();
    }

    @Test
    public void verifyLoginWithGoogleTest() throws IOException {
        ExtentTest extentTest = extentReports.createTest("[LoginWithSocialTest] verifyLoginWithGoogleTest",
                "To verify login with Social function <br />" +
                        "1. Verify policy Message in pop up and click Agree Button <br />" +
                        "2. Verify account and email displayed as expected in Select Account pop up <br />" +
                        "3. Verify username value in Account Page after login via Google"
        );

        try {
            scp = new SelectYourCountryPage(mobileDriver);

            SoftAssert softAssert = new SoftAssert();
            scp.verifyLanguageText(softAssert);
            scp.selectCountryName("Bangladesh");

            intoPage = scp.selectLanguage("ENGLISH");

            homePage = intoPage.clickIntroSkipButton();
            homePage.clickNoToSetLocation();
            homePage.clickPopUpCloseBtn();

            accountPage = homePage.navigateToAccountPage();

            registerPage = accountPage.clickLoginOrSignUpBtn();
            registerPage.clickGoogleIcon();
            registerPage.agreePolicy(softAssert,
                    "I agree to Daraz's Terms of Usage and for my personal data" +
                            " to be processed according to Daraz Privacy Policy.");

            accountPage = registerPage.selectAccount(softAssert,
                    "Tong Bijian", "tongbijian@gmail.com");
            accountPage.verifyAccountName(softAssert, "Tong Bijian");

            homePage = accountPage.navigateToHomePage();

            softAssert.assertAll();
        } catch (Exception e) {
            e.printStackTrace();
            extentTest.log(Status.FAIL, e.getMessage());
            extentTest.addScreenCaptureFromBase64String(takeScreenshot());
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void verifyLoginWithFacebookTest() throws IOException {
        ExtentTest extentTest = extentReports.createTest("[LoginWithSocialTest] verifyLoginWithFacebookTest",
                "To verify login with Social function <br />" +
                        "1. Verify policy Message in pop up and click Agree Button <br />" +
                        "2. Verify account and email displayed as expected in Select Account pop up <br />" +
                        "3. Verify username value in Account Page after login via Facebook"
        );

        try {
            scp = new SelectYourCountryPage(mobileDriver);

            SoftAssert softAssert = new SoftAssert();
            scp.verifyLanguageText(softAssert);
            scp.selectCountryName("Bangladesh");

            intoPage = scp.selectLanguage("ENGLISH");

            homePage = intoPage.clickIntroSkipButton();
            homePage.clickNoToSetLocation();
            homePage.clickPopUpCloseBtn();

            accountPage = homePage.navigateToAccountPage();

            registerPage = accountPage.clickLoginOrSignUpBtn();
            registerPage.clickFacebookIcon();
            registerPage.agreePolicy(softAssert,
                    "I agree to Daraz's Terms of Usage and for my personal data" +
                            " to be processed according to Daraz Privacy Policy.");

            accountPage.verifyAccountName(softAssert, "Tong BJian");

            homePage = accountPage.navigateToHomePage();

            softAssert.assertAll();
        } catch (Exception e) {
            e.printStackTrace();
            extentTest.log(Status.FAIL, e.getMessage());
            extentTest.addScreenCaptureFromBase64String(takeScreenshot());
            Assert.fail(e.getMessage());
        }
    }


    @AfterMethod
    public void tearDown(ITestResult testResult) {
        logger.info("After Method");
//        this.mobileDriver.quit();
    }
}
