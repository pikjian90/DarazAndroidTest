package pageObjects;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static testClasses.BaseTest.logger;

public class IntroPage {
    public AndroidDriver mobileDriver;

    @FindBy(id = "com.daraz.android:id/intro_skip_btn")
    WebElement introSkipBtn;

    public IntroPage(AndroidDriver mobileDriver) {
        this.mobileDriver = mobileDriver;
        PageFactory.initElements(mobileDriver, this);
    }

    public HomePage clickIntroSkipButton() throws InterruptedException {
        introSkipBtn.click();
        logger.info("[IntroPage] Skip Intro Button is clicked");
        Thread.sleep(2000);
        return new HomePage(mobileDriver);
    }
}
