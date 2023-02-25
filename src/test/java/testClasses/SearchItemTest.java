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

public class SearchItemTest extends BaseTest {
    public AndroidDriver mobileDriver;

    @BeforeMethod
    public void initialize() {
        System.out.println("Before Method");
        mobileDriver = initializeDriver();
    }

    @Test
    public void verifySortedBySalesTest() throws IOException {
        ExtentTest extentTest = extentReports.createTest("[SearchItemTest] verifySortedBySalesTest",
                "To verify Search Item function and sorted by Top Sales <br />" +
                "1. Verify all of displayed search tips are started with keywords <br />" +
                "2. Verify the arrow of search tips will append into search input box after it is clicked <br />" +
                "3. Verify labels from Search Result page are displayed as expected <br />" +
                "4. Verify Items from Search Result list are sorted by sales count"
        );

        try {
            SelectYourCountryPage scp = new SelectYourCountryPage(mobileDriver);

            SoftAssert softAssert = new SoftAssert();
            scp.verifyLanguageText(softAssert);
            scp.selectCountryName("Bangladesh");

            IntroPage intoPage = scp.selectLanguage("ENGLISH");

            HomePage homePage = intoPage.clickIntroSkipButton();
            homePage.clickNoToSetLocation();
            homePage.clickPopUpCloseBtn();

            SearchPage searchPage = homePage.clickSearchInputBox();
            searchPage.enterSearchBtn("Nike");
            searchPage.verifySearchResultList(softAssert, "Nike");
            searchPage.verifySearchResultListArrow(softAssert);

            SearchResultPage searchResultPage = searchPage.clickSearchBtn();
            searchResultPage.verifyTabTextList(softAssert);
            searchResultPage.verifyConfigTextList(softAssert);
            searchResultPage.verifyFilterItemTextList(softAssert);
            searchResultPage.verifyPriceSortedBySales(softAssert);

            softAssert.assertAll();
        }
        catch (Exception e){
            e.printStackTrace();
            extentTest.log(Status.FAIL, e.getMessage());
            extentTest.addScreenCaptureFromBase64String(takeScreenshot());
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void verifySortedByPriceTest() throws IOException {
        ExtentTest extentTest = extentReports.createTest("[SearchItemTest] verifySortedByPriceTest",
                "To verify Search Item function and sorted by Price <br />" +
                        "1. Verify all of displayed search tips are started with keywords <br />" +
                        "2. Verify the arrow of search tips will append into search input box after it is clicked <br />" +
                        "3. Verify labels from Search Result page are displayed as expected <br />" +
                        "4. Verify Items from Search Result list are sorted by Price"
        );

        try {
            SelectYourCountryPage scp = new SelectYourCountryPage(mobileDriver);

            SoftAssert softAssert = new SoftAssert();
            scp.verifyLanguageText(softAssert);
            scp.selectCountryName("Bangladesh");

            IntroPage intoPage = scp.selectLanguage("ENGLISH");

            HomePage homePage = intoPage.clickIntroSkipButton();
            homePage.clickNoToSetLocation();
            homePage.clickPopUpCloseBtn();

            SearchPage searchPage = homePage.clickSearchInputBox();
            searchPage.enterSearchBtn("Nike");
            searchPage.verifySearchResultList(softAssert, "Nike");
            searchPage.verifySearchResultListArrow(softAssert);

            SearchResultPage searchResultPage = searchPage.clickSearchBtn();
            searchResultPage.verifyTabTextList(softAssert);
            searchResultPage.verifyConfigTextList(softAssert);
            searchResultPage.verifyFilterItemTextList(softAssert);
            searchResultPage.verifyPriceSortedByPrices(softAssert);

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
//        this.mobileDriver.quit();
    }
}
