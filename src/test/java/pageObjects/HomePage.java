package pageObjects;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class HomePage {
    public AndroidDriver mobileDriver;

    //Pop up
    @FindBy(id = "com.daraz.android:id/tv_laz_trade_confirm_dialog_content")
    WebElement setLocationMessage;

    @FindBy(id = "com.daraz.android:id/btn_trade_confirm_dialog_negative")
    WebElement setLocationNoBtn;

    @FindBy(id = "com.daraz.android:id/btn_trade_confirm_dialog_positive")
    WebElement setLocationYesBtn;

    @FindBy(id = "com.daraz.android:id/ftvMessage")
    WebElement setYourDeliveryLocationTipsMessage;

    @FindBy(id = "com.daraz.android:id/emptyBackGround")
    WebElement emptyBackGround;

    @FindBy(id = "com.daraz.android:id/iv_close_btn")
    WebElement popUpCloseBtn;

    //Homepage Toolbar
    @FindBy(id = "com.daraz.android:id/laz_homepage_scan_icon")
    WebElement scanIcon;

    @FindBy(id = "com.daraz.android:id/laz_homepage_scan_text")
    WebElement scanText;

    @FindBy(id = "com.daraz.android:id/laz_homepage_search_view")
    WebElement searchInputBox;

    @FindBy(id = "com.daraz.android:id/laz_homepage_right_coin_icon")
    WebElement coinIcon;

    @FindBy(id = "com.daraz.android:id/laz_homepage_right_coin_text")
    WebElement coinText;

    @FindBy(id = "com.daraz.android:id/laz_homepage_right_wallet_icon")
    WebElement walletIcon;

    @FindBy(id = "com.daraz.android:id/laz_homepage_right_wallet_text")
    WebElement walletText;

    //Bottom PlaceHolder
    @FindBys({
            @FindBy(id = "com.daraz.android:id/title")
    })
    List<WebElement> bottomIcons;

    //Channels
    @FindBys({
            @FindBy(id = "com.daraz.android:id/laz_hpc_channel_top_text")
    })
    List<WebElement> channelsText;

    public HomePage(AndroidDriver mobileDriver) {
        this.mobileDriver = mobileDriver;
        PageFactory.initElements(mobileDriver, this);
    }

    public void clickNoToSetLocation() throws InterruptedException {
        clickPopUpCloseBtn();
        setLocationNoBtn.click();
        System.out.println("No button is click to not set location");
        clickPopUpCloseBtn();
    }

    public void clickPopUpCloseBtn() throws InterruptedException {
        Thread.sleep(3000);
        try {
            popUpCloseBtn.click();
            System.out.println("Close Pop Up button is clicked");
        }
        catch (NoSuchElementException e){
            System.out.println("Close Pop Up button is not existed");
        }
    }

    public void skipSetDeliveryLocationTips() throws InterruptedException {
        Thread.sleep(3000);
        try {
            emptyBackGround.click();
            System.out.println("emptyBackGround is clicked to skip delivery location");
        }
        catch (NoSuchElementException e){
            System.out.println("emptyBackGround is not existed");
        }
    }

    public RegisterPage navigateToMessagePageWithoutLogin() throws InterruptedException {
        Thread.sleep(1000);
        for (int i = 0; i < bottomIcons.size(); i++) {
            if(bottomIcons.get(i).getText().equals("Messages")){
                bottomIcons.get(i).click();
            }
        }
        return new RegisterPage(mobileDriver);
    }

    public RegisterPage navigateToCartPageWithoutLogin() throws InterruptedException {
        Thread.sleep(1000);
        for (int i = 0; i < bottomIcons.size(); i++) {
            if(bottomIcons.get(i).getText().equals("Cart")){
                bottomIcons.get(i).click();
            }
        }
        return new RegisterPage(mobileDriver);
    }

    public AccountPage navigateToAccountPage(){
        for (int i = 0; i < bottomIcons.size(); i++) {
            if(bottomIcons.get(i).getText().equals("Account")){
                bottomIcons.get(i).click();
                System.out.println("[navigateToAccountPage] " + bottomIcons.get(i).getText() + " is clicked");
            }
        }
        return new AccountPage(mobileDriver);
    }

    public SearchPage clickSearchInputBox(){
        searchInputBox.click();
        System.out.println("[HomePage] Search Button is clicked");
        return new SearchPage(mobileDriver);
    }

    public void verifyIconText(SoftAssert softAssert,
                               String scanTextExpected, String coinTextExpected,
                               String messageTextExpected, String cartTextExpected, String accountTextExpected ){
        softAssert.assertEquals(scanText.getText(), scanTextExpected);
        softAssert.assertEquals(coinText.getText(), coinTextExpected);

        for (int i = 0; i < bottomIcons.size(); i++) {
            switch (i){
                case 0 : softAssert.assertEquals(bottomIcons.get(i).getText(), messageTextExpected);break;
                case 1 : softAssert.assertEquals(bottomIcons.get(i).getText(), cartTextExpected);break;
                case 2 : softAssert.assertEquals(bottomIcons.get(i).getText(), accountTextExpected);break;
            }
        }
    }

    public void verifyIconText(SoftAssert softAssert,
                               String scanTextExpected, String coinTextExpected, String walletTextExpected,
                               String messageTextExpected, String cartTextExpected, String accountTextExpected ){
        softAssert.assertEquals(scanText.getText(), scanTextExpected);
        softAssert.assertEquals(coinText.getText(), coinTextExpected);
        softAssert.assertEquals(walletText.getText(), walletTextExpected);

        for (int i = 0; i < bottomIcons.size(); i++) {
            switch (i){
                case 0 : softAssert.assertEquals(bottomIcons.get(i).getText(), messageTextExpected);break;
                case 1 : softAssert.assertEquals(bottomIcons.get(i).getText(), cartTextExpected);break;
                case 2 : softAssert.assertEquals(bottomIcons.get(i).getText(), accountTextExpected);break;
            }
        }
    }

    public void verifyChannelsText(SoftAssert softAssert,
                                   String channelText1, String channelText2,String channelText3,
                                   String channelText4,String channelText5){
        for (int i = 0; i < channelsText.size(); i++) {
            switch (i){
                case 0 : softAssert.assertEquals(channelsText.get(i).getText().trim(),
                        channelText1);
                        break;
                case 1 : softAssert.assertEquals(channelsText.get(i).getText(),
                        channelText2);
                        break;
                case 2 : softAssert.assertEquals(channelsText.get(i).getText(),
                        channelText3);
                        break;
                case 3 : softAssert.assertEquals(channelsText.get(i).getText(),
                        channelText4);
                        break;
                case 4 : softAssert.assertEquals(channelsText.get(i).getText(),
                        channelText5);
                        break;
            }
        }
    }



}
