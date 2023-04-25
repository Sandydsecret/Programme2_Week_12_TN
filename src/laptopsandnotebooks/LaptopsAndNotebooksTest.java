package laptopsandnotebooks;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LaptopsAndNotebooksTest extends Utility {

    String baseUrl = "http://tutorialsninja.com/demo/index.php?";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyProductsPriceDisplayHighToLowSuccessfully() throws InterruptedException{
    //1.1 Mouse hover on Laptops & Notebooks Tab and click
        mouseHoverToElement(By.xpath("//a[text()='Laptops & Notebooks']"));
    //1.2 Click on “Show All Laptops & Notebooks”
        selectMenu("Show AllLaptops & Notebooks");
    //1.3 Select Sort By "Price (High > Low)"
        selectByVisibleTextFromDropdown(By.id("input-sort"),"Price (High > Low)");
    //1.4 Verify the Product price will arrange in High to Low order.
        By productPrice = By.xpath("//ul[@class='product-price']");
        Thread.sleep(2000);
            List<WebElement> originalList = driver.findElements(productPrice);
            List<Double> beforeFilterPriceList = new ArrayList<>();
            for (WebElement price : originalList) {
                beforeFilterPriceList.add(Double.valueOf(price.getText().replace("$", "")));
            }
            System.out.println("Before Sorting: " + beforeFilterPriceList);
            Collections.sort(beforeFilterPriceList, Collections.reverseOrder());

            List<WebElement> afterSortingList = driver.findElements(productPrice);
            List<Double> afterSortingPriceList = new ArrayList<>();
            for (WebElement price : afterSortingList) {
                afterSortingPriceList.add(Double.valueOf(price.getText().replace("$", "")));
            }
            System.out.println("After Sorting: " + afterSortingPriceList);
            Assert.assertEquals(beforeFilterPriceList, afterSortingPriceList);
}
    @Test
    public void verifyThatUserPlaceOrderSuccessfully(){
        //2.1 Mouse hover on Laptops & Notebooks Tab and click
        mouseHoverToElement(By.xpath("//a[text()='Laptops & Notebooks']"));
        //2.2 Click on “Show All Laptops & Notebooks”
        selectMenu("Show AllLaptops & Notebooks");
        //2.3 Select Sort By "Price (High > Low)"
        selectByVisibleTextFromDropdown(By.id("input-sort"),"Price (High > Low)");
        //2.4 Select Product “MacBook”
        findElementFromWebPage(By.xpath("//a[text()='MacBook']")).click();
        //2.5 Verify the text “MacBook”
        verifyElements("MacBook doesn't display as requirement ","MacBook" , By.xpath("//h1[text()='MacBook']"));
        //2.6 Click on ‘Add To Cart’ button
        clickOnElement(By.cssSelector("#button-cart"));
        //2.7 Verify the message “Success: You have added MacBook to your shopping cart!”
        verifyElements("Message is not displaying per requirement", "Success: You have added MacBook to your shopping cart!\n" +
                "×", By.cssSelector(" .alert.alert-success.alert-dismissible"));
        //2.8 Click on link “shopping cart” display into success message
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        clickOnElement(By.cssSelector(".alert.alert-success.alert-dismissible a:nth-of-type(2)"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        //2.9 Verify the text "Shopping Cart"
        verifyElements("Message is not displaying per requirement", "Shopping Cart  (0.00kg)", By.cssSelector("#content>h1"));
        //2.10 Verify the Product name "MacBook"
        verifyElements("Message is not displaying per requirement", "MacBook", By.xpath("//*[@id=\"content\"]/form/div/table/tbody/tr/td[2]/a"));
        //2.11 Change Quantity "2"
        WebElement quantityTextBox=findElementFromWebPage(By.cssSelector("div.input-group.btn-block>input"));
        quantityTextBox.click();
        quantityTextBox.sendKeys(Keys.BACK_SPACE);
        quantityTextBox.sendKeys("2");
        //2.12 Click on “Update” Tab
        clickOnElement(By.cssSelector(".fa.fa-refresh"));
        //2.13 Verify the message “Success: You have modified your shopping cart!”
        verifyElements("Message is not displaying per requirement", "Success: You have modified your shopping cart!\n" +
                "×", By.cssSelector(".alert.alert-success.alert-dismissible"));
        //2.14 Verify the Total £737.45
        verifyElements("Message is not displaying per requirement", "$1,204.00", By.cssSelector("#content > div.row > div > table > tbody > tr:nth-child(4) > td:nth-child(2)"));
        //2.15 Click on “Checkout” button
        clickOnElement(By.cssSelector("a.btn.btn-primary"));
        //2.16 Verify the text “Checkout”
        verifyElements("Message is not displaying per requirement", "Checkout", By.xpath("//h1[text()='Checkout']"));
        //2.17 Verify the Text “New Customer”
        //verifyElements("Message is not displaying per requirement", "New Customer", By.xpath("#collapse-checkout-option > div > div > div:nth-child(1) > h2"));
        //2.18 Click on “Guest Checkout” radio button
        clickOnElement(By.cssSelector("input[value='guest']"));
        //2.19 Click on “Continue” tab
        clickOnElement(By.cssSelector("#button-account"));
        //2.20 Fill the mandatory fields
        sendTextToElement(By.cssSelector("#input-payment-firstname"), "Ramesh");
        sendTextToElement(By.cssSelector("#input-payment-lastname"), "Patel");
        sendTextToElement(By.cssSelector("#input-payment-email"), "rameshpatel@gmail.com");
        sendTextToElement(By.cssSelector("#input-payment-telephone"), "76895433452");
        sendTextToElement(By.cssSelector("#input-payment-address-1"), "Kings Street");
        sendTextToElement(By.cssSelector("#input-payment-city"), "London");
        sendTextToElement(By.cssSelector("#input-payment-postcode"), "WC11BC");
        selectByVisibleTextFromDropdown(By.cssSelector("#input-payment-zone"),"Berkshire");
        //2.21 Click on “Continue” Button
        clickOnElement(By.cssSelector("#button-guest"));
        //2.22 Add Comments About your order into text area
        sendTextToElement(By.cssSelector("textarea[name='comment']"),"Thank you for the order");
        //2.23 Check the Terms & Conditions check box
        clickOnElement(By.cssSelector("input[type='checkbox']"));
        //2.24 Click on “Continue” button
        clickOnElement(By.cssSelector("#button-payment-method"));
        //2.25 Verify the message “Warning: Payment method required!”
        verifyElements("Message is not displaying per requirement", "Warning: You must agree to the Terms & Conditions!\n" +
                        "×", By.cssSelector(".alert.alert-danger.alert-dismissible"));
    }

    @After
    public void tearDown() {
        //closeBrowser();
    }
}
