package pageObjects;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

public class RegisterPage {
    public AndroidDriver mobileDriver;

    @FindBy( id= "com.daraz.android:id/iv_back")
    WebElement backBtn;

    @FindBy( id= "com.daraz.android:id/tv_brand_text")
    WebElement brandText;

    @FindBy( id= "com.daraz.android:id/tv_register_hint")
    WebElement registerHint;

    @FindBy( id= "com.daraz.android:id/iv_country_flag")
    WebElement countryFlag;

    @FindBy( id= "com.daraz.android:id/tv_area_code")
    WebElement countryAreaCode;

    @FindBy(id = "com.daraz.android:id/tv_signup")
    WebElement mobileNumberInputBox;

    @FindBy(id = "com.daraz.android:id/tv_signin")
    WebElement signInWithEmail;

    @FindBy(id = "com.daraz.android:id/tv_social_title")
    WebElement signInWithSocialText;

    @FindBy(id = "com.daraz.android:id/iv_facebook")
    WebElement faceBookIcon;

    @FindBy(id = "com.daraz.android:id/iv_google")
    WebElement googleIcon;

    //Policy Agreement
    @FindBy(id = "com.daraz.android:id/tv_laz_dialog_social_policy_content")
    WebElement policyMessage;

    @FindBy(id = "com.daraz.android:id/tv_cancel")
    WebElement policyCancelBtn;

    @FindBy(id = "com.daraz.android:id/tv_agree")
    WebElement policyAgreeBtn;

    //Choose An Account
    @FindBy(id = "com.google.android.gms:id/account_display_name")
    WebElement accountName;

    @FindBy(id = "com.google.android.gms:id/account_name")
    WebElement accountEmail;

    public RegisterPage(AndroidDriver mobileDriver){
        this.mobileDriver = mobileDriver;
        PageFactory.initElements(mobileDriver, this);
    }

    public HomePage closeRegisterPage() throws InterruptedException {
        Thread.sleep(1000);
        backBtn.click();
        return new HomePage(mobileDriver);
    }

    public void clickGoogleIcon() throws InterruptedException {
        Thread.sleep(1000);
        googleIcon.click();
        System.out.println("[RegisterPage] googleIcon is clicked");
    }

    public void clickFacebookIcon() throws InterruptedException {
        Thread.sleep(1000);
        faceBookIcon.click();
        System.out.println("[RegisterPage] faceBookIcon is clicked");
    }

    public void verifyRegisterPageText(SoftAssert softAssert,
                                       String brandTextExpected, String registerHintExpected, String countryAreaCodeExpected,
                                       String mobileNumberExpected, String emailTextExpected, String socialTextExpected
    ) throws InterruptedException {
        Thread.sleep(2000);
        softAssert.assertEquals(brandText.getText(),
                brandTextExpected);
        softAssert.assertEquals(registerHint.getText(), registerHintExpected);
        softAssert.assertEquals(countryAreaCode.getText(), countryAreaCodeExpected);
        softAssert.assertEquals(mobileNumberInputBox.getText(), mobileNumberExpected);
        softAssert.assertEquals(signInWithEmail.getText(), emailTextExpected);
        softAssert.assertEquals(signInWithSocialText.getText(), socialTextExpected);
        softAssert.assertTrue(faceBookIcon.isDisplayed());
        softAssert.assertTrue(googleIcon.isDisplayed());
    }

    public void agreePolicy(SoftAssert softAssert, String policyMessageExpected) throws InterruptedException {
        Thread.sleep(1000);
        softAssert.assertEquals(policyMessage.getText(), policyMessageExpected);
        policyAgreeBtn.click();
    }

    public AccountPage selectAccount(SoftAssert softAssert, String accountNameExpected, String accountEmailExpected) throws InterruptedException {
        Thread.sleep(1000);
        softAssert.assertEquals(accountName.getText(), accountNameExpected);
        softAssert.assertEquals(accountEmail.getText(), accountEmailExpected);
        accountName.click();
        return new AccountPage(mobileDriver);
    }
}
