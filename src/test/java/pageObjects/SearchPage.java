package pageObjects;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class SearchPage {
    public AndroidDriver mobileDriver;

    @FindBy(id = "com.daraz.android:id/sap_search_bar_nav_back")
    WebElement backBtn;

    @FindBy(id = "com.daraz.android:id/search_input_box")
    WebElement searchInputBox;

    @FindBy(id = "com.daraz.android:id/search_button")
    WebElement searchBtn;

    @FindBys({
            @FindBy(id = "com.daraz.android:id/item_title_text")
    })
    List<WebElement> searchResultListTitle;

    @FindBys({
            @FindBy(id = "com.daraz.android:id/arrow_image_view")
    })
    List<WebElement> searchResultListArrow;



    public SearchPage(AndroidDriver mobileDriver) {
        this.mobileDriver = mobileDriver;
        PageFactory.initElements(mobileDriver, this);
    }

    public HomePage clickBackBtn() throws InterruptedException {
        backBtn.click();
        System.out.println("[SearchPage] Back Button is clicked");
        Thread.sleep(2000);
        return new HomePage(mobileDriver);
    }

    public void enterSearchBtn(String input) throws InterruptedException {
        Thread.sleep(1000);
        searchInputBox.sendKeys(input);
        System.out.println("[SearchPage] " + input + " is entered");
    }

    public SearchResultPage clickSearchBtn() throws InterruptedException {
        searchBtn.click();
        System.out.println("[SearchPage] Search Button is clicked");
        Thread.sleep(2000);
        return new SearchResultPage(mobileDriver);
    }

    public void verifySearchResultList(SoftAssert softAssert, String input){
        for (int i = 0; i < searchResultListTitle.size(); i++) {
            softAssert.assertTrue(searchResultListTitle.get(i).getText().startsWith(input.toLowerCase()));
        }
    }

    public void verifySearchResultListArrow(SoftAssert softAssert){
        while (searchResultListTitle.size() != 1) {
            String firstSearchResultTitle = searchResultListTitle.get(1).getText();
            searchResultListArrow.get(1).click();
            softAssert.assertEquals(searchInputBox.getText(), firstSearchResultTitle);
        }
    }


}
