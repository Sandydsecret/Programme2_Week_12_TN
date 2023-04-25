package myaccounts;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.List;

public class MyAccountsTest extends Utility {
    String baseUrl = "http://tutorialsninja.com/demo/index.php?";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }


    public void selectMyAccountOptions(String option){
        List<WebElement> topMenuNames = findElementsFromWebPage(By.cssSelector("#top-links a"));
        for (WebElement names : topMenuNames) {
            //System.out.println(names.getText());
            if (names.getText().equalsIgnoreCase(option)) {
                names.click();
                break;
            }
        }

    }

    @Test
    public void verifyUserShouldNavigateToRegisterPageSuccessfully(){
        //1.1 Click on My Account Link.
        clickOnElement(By.xpath("//span[text()='My Account']"));
        //1.2 Call the method “selectMyAccountOptions” method and pass the parameter “Register”
        selectMyAccountOptions("Register");
        //1.3 Verify the text “Register Account”.
        verifyElements("Message don't display as required","Register Account" , By.cssSelector("#content h1"));

    }
    @Test
    public void verifyUserShouldNavigateToLoginPageSuccessfully(){
        //2.1 Click on My Account Link.
        clickOnElement(By.xpath("//span[text()='My Account']"));
        //2.2 Call the method “selectMyAccountOptions” method and pass the parameter “Login”
        selectMyAccountOptions("Login");
        //2.3 Verify the text “Returning Customer”.
        verifyElements("Message don't display as required","Returning Customer" , By.cssSelector("#content > div > div:nth-child(2) > div > h2"));
    }
    @Test
    public void verifyThatUserRegisterAccountSuccessfully(){
        //3.1 Click on My Account Link.
        clickOnElement(By.xpath("//span[text()='My Account']"));
        //3.2 Call the method “selectMyAccountOptions” method and pass the parameter “Register”
        selectMyAccountOptions("Register");
        //3.3 Enter First Name
        sendTextToElement(By.cssSelector("#input-firstname"), "Vina");
        //3.4 Enter Last Name
        sendTextToElement(By.cssSelector("#input-lastname"), "Shah");
        //3.5 Enter Email
        sendTextToElement(By.cssSelector("#input-email"), "vinashah1@gmail.com");
        //3.6 Enter Telephone
        sendTextToElement(By.cssSelector("#input-telephone"), "78945636547");
        //3.7 Enter Password
        sendTextToElement(By.cssSelector("#input-password"), "12as5464");
        //3.8 Enter Password Confirm
        sendTextToElement(By.cssSelector("#input-confirm"), "12as5464");
        //3.9 Select Subscribe Yes radio button
        clickOnElement(By.cssSelector("input[type='radio'][name='newsletter'][value='1']"));
        //3.10 Click on Privacy Policy check box
        clickOnElement(By.cssSelector("input[type='checkbox']"));
        //3.11 Click on Continue button
        clickOnElement(By.cssSelector("input[value='Continue']"));
        //3.12 Verify the message “Your Account Has Been Created!”
        verifyElements("Message don't display as required","Your Account Has Been Created!" , By.xpath("//h1[text()='Your Account Has Been Created!']"));
        //3.13 Click on Continue button
        clickOnElement(By.cssSelector("a[class='btn btn-primary']"));
        //3.14 Click on My Account Link.
        clickOnElement(By.xpath("//span[text()='My Account']"));
        //3.15 Call the method “selectMyAccountOptions” method and pass the parameter “Logout”
        selectMyAccountOptions("Logout");
        //3.16 Verify the text “Account Logout”
        verifyElements("Message don't display as required","Account Logout" , By.xpath("//h1[text()='Account Logout']"));

        //3.17 Click on Continue button
        clickOnElement(By.cssSelector(".btn.btn-primary"));
    }
    @Test
    public void verifyThatUserShouldLoginAndLogoutSuccessfully(){
        //4.1 Click on My Account Link.
        clickOnElement(By.xpath("//span[text()='My Account']"));
        //4.2 Call the method “selectMyAccountOptions” method and pass the parameter “Login”
        selectMyAccountOptions("Login");
        //4.3 Enter Email address
        sendTextToElement(By.cssSelector("#input-email"), "vinashah1@gmail.com");
        //4.5 Enter Password
        sendTextToElement(By.cssSelector("#input-password"), "12as5464");
        //4.6 Click on Login button
        clickOnElement(By.cssSelector("input[value='Login']"));
        //4.7 Verify text “My Account”
        verifyElements("Message don't display as required","My Account" , By.xpath("//h2[text()='My Account']"));
        //4.8 Click on My Account Link.
        clickOnElement(By.xpath("//span[text()='My Account']"));
        //4.9 Call the method “selectMyAccountOptions” method and pass the parameter “Logout”
        selectMyAccountOptions("Logout");
        //4.10 Verify the text “Account Logout”
        verifyElements("Message don't display as required","Account Logout" , By.xpath("//h1[text()='Account Logout']"));
        //4.11 Click on Continue button
        clickOnElement(By.cssSelector(".btn.btn-primary"));
    }
    @After
    public void tearDown() {
        closeBrowser();
    }
}
