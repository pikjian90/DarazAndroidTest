package pageObjects;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;
import testClasses.BaseTest;

import java.util.List;

public class SelectYourCountryPage extends BaseTest {

    public AndroidDriver mobileDriver;

    @FindBys({
            @FindBy(id = "com.daraz.android:id/country_name")
    })
    List<WebElement> countryName;

    @FindBy(id = "com.daraz.android:id/alertTitle")
    WebElement alertTitle;

    @FindBy(id = "android:id/message")
    WebElement alertMessage;

    @FindBys({
            @FindBy(className = "android.widget.Button")
    })
    List<WebElement> alertButtons;

    public SelectYourCountryPage(AndroidDriver mobileDriver) throws InterruptedException {
        Thread.sleep(2000);
        this.mobileDriver = mobileDriver;
        PageFactory.initElements(mobileDriver, this);
    }

    public String getCountryName(int index) {
        return countryName.get(index).getText();
    }

    public void selectCountryName(String country) {
        for (int i = 0; i < countryName.size(); i++) {
            System.out.println("[selectCountryName] :" + countryName.get(i).getText() + ":" + country);
            if (countryName.get(i).getText().equals(country)) {
                String selectCountryName = countryName.get(i).getText();
                countryName.get(i).click();
                System.out.println(selectCountryName + " is clicked");
            }
        }
    }

    public IntroPage selectLanguage(String language) {
        for (int i = 0; i < alertButtons.size(); i++) {
            WebElement languageButton = alertButtons.get(i);
            System.out.println("[selectLanguage] :" + languageButton.getText() + ":" + language);
            if (languageButton.getText().equals(language)) {
                String selectLanguage = languageButton.getText();
                languageButton.click();
                System.out.println(selectLanguage + " is clicked");
            }
        }
        return new IntroPage(mobileDriver);
    }

    public void verifyLanguageText(SoftAssert softAssert) {
        softAssert.assertEquals(getCountryName(0), "Bangladesh");
        softAssert.assertEquals(getCountryName(1), "Sri Lanka");
        softAssert.assertEquals(getCountryName(2), "Nepal");
        softAssert.assertEquals(getCountryName(3), "Pakistan");
    }

}
