package homepage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities.Utility;

public class TopMenuTest extends Utility {

    String baseUrl = "http://tutorialsninja.com/demo/index.php?";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }


//     public void selectMenu(String menu){
//        List<WebElement> topMenuNames = findElementsFromWebPage(By.cssSelector("#menu .see-all"));
//        for (WebElement names : topMenuNames) {
//            //System.out.println(names.getText());
//            if (names.getText().equalsIgnoreCase(menu)) {
//                names.click();
//                break;
//            }
//        }
//    }

    @Test
    public void verifyUserShouldNavigateToDesktopsPageSuccessfully(){
        //1.1 Mouse hover on “Desktops” Tab and click
        mouseHoverToElement(By.xpath("//a[text()='Desktops']"));
        //1.2 call selectMenu method and pass the menu = “Show All Desktops”
        selectMenu("Show AllDesktops");
        //1.3 Verify the text ‘Desktops’
        verifyElements("Desktop not displayed", "Desktops", By.xpath("//h2"));
    }

    @Test
    public void verifyUserShouldNavigateToLaptopsAndNotebooksPageSuccessfully(){
        //2.1 Mouse hover on “Laptops & Notebooks” Tab and click
        mouseHoverToElement(By.xpath("//a[text()='Laptops & Notebooks']"));
        //2.2 call selectMenu method and pass the menu = “Show All Laptops & Notebooks”
        selectMenu("Show AllLaptops & Notebooks");
        //2.3 Verify the text ‘Laptops & Notebooks’
        verifyElements("Laptops & Notebooks not displayed","Laptops & Notebooks",By.cssSelector("#content h2"));
    }

    @Test
    public void verifyUserShouldNavigateToComponentsPageSuccessfully(){
        //3.1 Mouse hover on “Components” Tab and click
        mouseHoverToElement(By.xpath("//a[text()='Components']"));
        //3.2 call selectMenu method and pass the menu = “Show All Components”
        selectMenu("Show AllComponents");
        //3.3 Verify the text ‘Components’
        verifyElements("Components not displayed","Components",By.cssSelector("#content h2"));
    }

    @After
    public void tearDown() {
        //closeBrowser();
    }
}
