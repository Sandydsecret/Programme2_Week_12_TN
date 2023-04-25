package desktops;

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
import java.util.List;

public class DesktopsTest extends Utility {
    String baseUrl = "http://tutorialsninja.com/demo/index.php?";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyProductArrangeInAlphaBaticalOrder() {
        //1.1 Mouse hover on Desktops Tab and click
        mouseHoverToElement(By.xpath("//a[text()='Desktops']"));
        //1.2 Click on “Show All Desktops”
        selectMenu("Show AllDesktops");
        //1.3 Select Sort By position "Name: Z to A"
        selectByVisibleTextFromDropdown(By.id("input-sort"), "Name (Z - A)");

        //1.4 Verify the Product will arrange in Descending order.

        List<WebElement> elementListDisplay = findElementsFromWebPage(By.cssSelector("h4>a"));
        List<String> originalProductNameList = new ArrayList<>();

        for (WebElement product : elementListDisplay) {
            originalProductNameList.add(product.getText());
        }
        //Collections.reverse(originalProductNameList);
        System.out.println(originalProductNameList);

        List<WebElement> afterSortingElementList = findElementsFromWebPage(By.cssSelector("h4>a"));
        List<String> afterSortingProductName = new ArrayList<>();
        for (WebElement product : afterSortingElementList) {
            afterSortingProductName.add(product.getText());
        }
        System.out.println(afterSortingProductName);
        Assert.assertEquals("Products are not in Descending order", originalProductNameList, afterSortingProductName);
    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() {
        //2.1 Mouse hover on Desktops Tab and click
        mouseHoverToElement(By.xpath("//a[text()='Desktops']"));
        //2.2 Click on “Show All Desktops”
        selectMenu("Show AllDesktops");
        //2.3 Select Sort By position "Name: A to Z"
        selectByVisibleTextFromDropdown(By.id("input-sort"), "Name (A - Z)");
        //2.4 Select product “HP LP3065”
        clickOnElement(By.xpath("//a[text()='HP LP3065']"));
        //2.5 Verify the Text "HP LP3065"
        verifyElements("HP LP3065 is not Displayed", "HP LP3065", By.cssSelector("#content h1"));
        //2.6 Select Delivery Date "2022-11-30"
        WebElement dateTextField=findElementFromWebPage(By.cssSelector("#input-option225"));
        dateTextField.click();
        dateTextField.sendKeys(Keys.CONTROL,"a");
        dateTextField.sendKeys(Keys.DELETE);
        dateTextField.sendKeys("2022-11-30");
        //2.7.Enter Qty "1”
        sendTextToElement(By.cssSelector("#input-quantity"), "1");
        //2.8 Click on “Add to Cart” button
        clickOnElement(By.cssSelector("#button-cart"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        //2.9 Verify the Message “Success: You have added HP LP3065 to your shopping cart!”
        verifyElements("Message is not displaying per requirement", "Success: You have added HP LP3065 to your shopping cart!\n" +
                "×", By.cssSelector(".alert.alert-success.alert-dismissible"));
        //2.10 Click on link “shopping cart” display into success message
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        clickOnElement(By.cssSelector(".alert.alert-success.alert-dismissible a:nth-of-type(2)"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        //2.11 Verify the text "Shopping Cart"
        verifyElements("Message is not displaying per requirement", "Shopping Cart  (11.00kg)", By.cssSelector("#content>h1"));
        //2.12 Verify the Product name "HP LP3065"
        verifyElements("Message is not displaying per requirement", "HP LP3065", By.xpath("//*[@id=\"content\"]/form/div/table/tbody/tr/td[2]/a"));
        //2.13 Verify the Delivery Date "2022-11-30"
        verifyElements("Message is not displaying per requirement", "2022-11-30", By.cssSelector("td[class='text-left']>small:first-of-type"));
        //2.14 Verify the Model "Product21"
        verifyElements("Message is not displaying per requirement", "Product21", By.cssSelector("//td[text()='Product 21']"));
        //2.15 Verify the Total "$122.00"
        verifyElements("Message is not displaying per requirement", "$122.00", By.cssSelector("#content > div.row > div > table > tbody > tr:nth-child(4) > td:nth-child(2)"));

    }

    @After
    public void tearDown() {
       closeBrowser();
    }
}
