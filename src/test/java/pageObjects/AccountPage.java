package pageObjects;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class AccountPage {
    public AndroidDriver mobileDriver;

    //Header Toolbar
    @FindBy(id = "com.daraz.android:id/iv_head")
    WebElement profileIcon;

    @FindBy(id = "com.daraz.android:id/tv_username")
    WebElement usernameText;

    @FindBy(id = "com.daraz.android:id/tv_settings")
    WebElement settingsIcon;

    //Login or Signup Section
    @FindBy(id = "com.daraz.android:id/txt_login_signup")
    WebElement loginOrSignUpBtn;

    //My Orders Section
    @FindBy(id = "com.daraz.android:id/orders_title")
    WebElement myOrderTitleText;

    @FindBy(id = "com.daraz.android:id/view_all")
    WebElement viewAllLink;

    @FindBys({
            @FindBy(id = "com.daraz.android:id/txt_orders")
    })
    List<WebElement> returnOrCancellations;

    @FindBy(id = "com.daraz.android:id/service_title")
    WebElement serviceTitleText;

    @FindBys({
            @FindBy(id = "com.daraz.android:id/txt_down")
    })
    List<WebElement> txtDownText;

    @FindBys({
            @FindBy(id = "com.daraz.android:id/sub_txt_down")
    })
    List<WebElement> subTxtDownText;

    //Bottom PlaceHolder
    @FindBys({
            @FindBy(id = "com.daraz.android:id/title")
    })
    List<WebElement> bottomIcons;

    //Login
    @FindBy(id = "com.daraz.android:id/txt_name")
    WebElement loginUserNameText;


    public AccountPage(AndroidDriver mobileDriver) {
        this.mobileDriver = mobileDriver;
        PageFactory.initElements(mobileDriver, this);
    }

    public RegisterPage navigateToMessagePageWithoutLogin() {
        for (int i = 0; i < bottomIcons.size(); i++) {
            if (bottomIcons.get(i).getText().equals("Messages")) {
                bottomIcons.get(i).click();
            }
        }
        return new RegisterPage(mobileDriver);
    }

    public RegisterPage navigateToCartPageWithoutLogin() {
        for (int i = 0; i < bottomIcons.size(); i++) {
            if (bottomIcons.get(i).getText().equals("Cart")) {
                bottomIcons.get(i).click();
            }
        }
        return new RegisterPage(mobileDriver);
    }

    public HomePage navigateToHomePage(){
        for (int i = 0; i < bottomIcons.size(); i++) {
            if (bottomIcons.get(i).getText().equals("Home")){
                bottomIcons.get(i).click();
            }
        }
        return new HomePage(mobileDriver);
    }

    public RegisterPage clickLoginOrSignUpBtn() throws InterruptedException {
        Thread.sleep(1000);
        loginOrSignUpBtn.click();
        System.out.println("[AccountPage] " + "loginOrSignUpBtn is clicked");
        return new RegisterPage(mobileDriver);
    }


    public void verifyAccountPageText(SoftAssert softAssert,
                                      String usernameTextExpected, String loginOrSignUpBtnTextExpected,
                                      String myOrderTitleTextExpected, String serviceTitleTextExpected,
                                      String returnOrCancellationsExpected1,  String returnOrCancellationsExpected2) {
        softAssert.assertEquals(usernameText.getText(), "Hello, Welcome to Daraz !");
        softAssert.assertEquals(loginOrSignUpBtn.getText(), "Login / Sign up");
        softAssert.assertEquals(myOrderTitleText.getText(), "My Orders");
        softAssert.assertEquals(serviceTitleText.getText(), "My Services");

        for (int i = 0; i < returnOrCancellations.size(); i++) {
            switch (i) {
                case 0:
                    softAssert.assertEquals(returnOrCancellations.get(0).getText(), "My Returns");
                    break;
                case 1:
                    softAssert.assertEquals(returnOrCancellations.get(1).getText(), "My Cancellations");
                    break;
                default:
                    System.out.println();
            }
        }

        for (int i = 0; i < txtDownText.size(); i++) {
            switch (i) {
                case 0:
                    softAssert.assertEquals(txtDownText.get(i).getText(), "To Pay");
                    break;
                case 1:
                    softAssert.assertEquals(txtDownText.get(i).getText(), "To Ship");
                    break;
                case 2:
                    softAssert.assertEquals(txtDownText.get(i).getText(), "To Receive");
                    break;
                case 3:
                    softAssert.assertEquals(txtDownText.get(i).getText(), "To Review");
                    break;
                case 4:
                    softAssert.assertEquals(txtDownText.get(i).getText(), "My Messages");
                    break;
                case 5:
                    softAssert.assertEquals(txtDownText.get(i).getText(), "Payment");
                    break;
                case 6:
                    softAssert.assertEquals(txtDownText.get(i).getText(), "Help Center");
                    break;
                case 7:
                    softAssert.assertEquals(txtDownText.get(i).getText(), "Chat with Us");
                    break;
                case 8:
                    softAssert.assertEquals(txtDownText.get(i).getText(), "My Reviews");
                    break;
                default:
                    System.out.println(txtDownText.get(i).getText());
            }
        }

    }

    public void verifyAccountName(SoftAssert softAssert, String usernameTextExpected) throws InterruptedException {
        Thread.sleep(3000);
        softAssert.assertEquals(loginUserNameText.getText(), usernameTextExpected);
    }

}
