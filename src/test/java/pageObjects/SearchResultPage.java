package pageObjects;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

import static testClasses.BaseTest.logger;

public class SearchResultPage {
    public AndroidDriver mobileDriver;

    @FindBy(id = "com.daraz.android:id/srp_search_bar_nav_back")
    WebElement backBtn;

    @FindBy(id = "com.daraz.android:id/srp_search_input_box")
    WebElement searchInputBox;

    @FindBy(id = "com.daraz.android:id/iv_cart")
    WebElement cartIcon;

    @FindBys({
            @FindBy(id = "com.daraz.android:id/tab_text")
    })
    List<WebElement> tabTextList;

    //Config Section
    //Best Match, Top Sales, Price, New
    @FindBys({
            @FindBy(id = "com.daraz.android:id/config_text")
    })
    List<WebElement> configTextList;

    @FindBy(id = "com.daraz.android:id/filter_button")
    WebElement filterBtn;

    //Filter Section
    //Brand, Color Family, Fulfilled By Daraz, Service
    @FindBys({
            @FindBy(id = "com.daraz.android:id/top_filter_item_text")
    })
    List<WebElement> filterItemTextList;

    //Result Item List
    @FindBys({
            @FindBy(id = "com.daraz.android:id/sold_reviews_count")
    })
    List<WebElement> soldCountList;

    @FindBys({
            @FindBy(id = "com.daraz.android:id/product_display_price")
    })
    List<WebElement> productsPriceList;


    public SearchResultPage(AndroidDriver mobileDriver) {
        this.mobileDriver = mobileDriver;
        PageFactory.initElements(mobileDriver, this);
    }

    public HomePage clickBackBtn() throws InterruptedException {
        backBtn.click();
        logger.info("[SearchPage] Back Button is clicked");
        Thread.sleep(2000);
        return new HomePage(mobileDriver);
    }

    public void enterSearchInputBox(String input) throws InterruptedException {
        Thread.sleep(1000);
        searchInputBox.sendKeys(input);
        logger.info("[SearchPage] " + input + " is entered");
    }

    public void verifyTabTextList(SoftAssert softAssert){
        for (int i = 0; i < tabTextList.size(); i++) {
            switch (i){
                case 0: softAssert.assertEquals(tabTextList.get(i).getText(), "All");break;
                case 1: softAssert.assertEquals(tabTextList.get(i).getText(), "Mall");break;
                case 2: softAssert.assertEquals(tabTextList.get(i).getText(), "Free Shipping");break;
                default:
                    logger.info("[SearchResultPage] " + tabTextList.get(i).getText());
            }
        }
    }

    public void verifyConfigTextList(SoftAssert softAssert){
        for (int i = 0; i < configTextList.size(); i++) {
            switch (i){
                case 0: softAssert.assertEquals(configTextList.get(i).getText(), "Best Match");break;
                case 1: softAssert.assertEquals(configTextList.get(i).getText(), "Top Sales");break;
                case 2: softAssert.assertEquals(configTextList.get(i).getText(), "Price");break;
                case 3: softAssert.assertEquals(configTextList.get(i).getText(), "New");break;
                default:
                    logger.info("[SearchResultPage] " + configTextList.get(i).getText());
            }

        }
    }

    public void verifyFilterItemTextList(SoftAssert softAssert){
        for (int i = 0; i < filterItemTextList.size(); i++) {
            switch (i){
                case 0: softAssert.assertEquals(filterItemTextList.get(i).getText(), "Brand");break;
                case 1: softAssert.assertEquals(filterItemTextList.get(i).getText(), "Color Family");break;
                case 2: softAssert.assertEquals(filterItemTextList.get(i).getText(), "Fulfilled By Daraz");break;
                case 3: softAssert.assertEquals(filterItemTextList.get(i).getText(), "Service");break;
                default:
                    logger.info("[SearchResultPage] " + filterItemTextList.get(i).getText());
            }

        }
    }

    public void verifyPriceSortedBySales(SoftAssert softAssert) throws InterruptedException {
        logger.info("[SearchResultPage] " + configTextList.get(1).getText() + "is clicked");
        configTextList.get(1).click();
        Thread.sleep(1000);
        ArrayList<Integer> soldCountResultList = new ArrayList<>();
        for (int i = 0; i < soldCountList.size(); i++) {
            String item = soldCountList.get(i).getText().split(" ")[0];
            if (item.endsWith("K")){
                item = item.replace("K", "000");
            }
            soldCountResultList.add(Integer.parseInt(item));
        }
        logger.info("[SearchResultPage] soldCountResultList : " + soldCountResultList);

        boolean isSortedBySoldCount = true;
        for (int i = 1; i < soldCountResultList.size(); i++) {
            if (soldCountResultList.get(i) > soldCountResultList.get(i-1)){
                isSortedBySoldCount = false;
            }
        }
        softAssert.assertTrue(isSortedBySoldCount);
    }

    public void verifyPriceSortedByPrices(SoftAssert softAssert) throws InterruptedException {
        logger.info("[SearchResultPage] " + configTextList.get(2).getText() + " is clicked");
        configTextList.get(2).click();
        Thread.sleep(1000);
        ArrayList<Integer> productsPriceResultList = new ArrayList<>();
        String temp = "";
        for (int i = 0; i < productsPriceList.size(); i++) {
            if (i == 0 || i ==1 ) {
                String item = productsPriceList.get(i).getText().split(" ")[1].replace(",", "");
                productsPriceResultList.add(Integer.parseInt(item));
                logger.info("[SearchResultPage] productsPriceResultList : " + productsPriceResultList);
            }
            else {
                if (i % 2 == 0) {
                    temp = productsPriceList.get(i).getText().split(" ")[1].replace(",", "");
                } else {
                    String item = productsPriceList.get(i).getText().split(" ")[1].replace(",", "");
                    productsPriceResultList.add(Integer.parseInt(item));
                    productsPriceResultList.add(Integer.parseInt(temp));
                    logger.info("[SearchResultPage] productsPriceResultList : " + productsPriceResultList);
                }
            }
        }

        boolean isSortedByPriceAsc = true;
        for (int i = 1; i < productsPriceResultList.size(); i++) {
            if (productsPriceResultList.get(i) < productsPriceResultList.get(i-1)){
                isSortedByPriceAsc = false;
            }
        }
        softAssert.assertTrue(isSortedByPriceAsc);
    }
}
