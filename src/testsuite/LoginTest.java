package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {

    /**
     * Write down the following test into ‘LoginTest’
     * class
     * 1.UserSholdLoginSuccessfullyWithValidCredentials
     * Enter “tomsmith” username
     * Enter “SuperSecretPassword!” password
     * Click on ‘LOGIN’ button
     * Verify the text “Secure Area”
     */

    String baseUrl = "http://the-internet.herokuapp.com/login";

    @Before
    public void setUp() {
        openBrowser(baseUrl);

    }

    @Test
    public void UserShouldLoginSuccessfullyWithValidCredentials() {
        WebElement enterUserName = driver.findElement(By.id("username"));
        enterUserName.sendKeys("tomsmith");

        WebElement enterPassWord = driver.findElement(By.id("password"));
        enterPassWord.sendKeys("SuperSecretPassword!");

        WebElement clickLoginButton = driver.findElement(By.cssSelector("i.fa.fa-2x.fa-sign-in"));
        clickLoginButton.click();

        //Actual
        WebElement actualText = driver.findElement(By.cssSelector("h4.subheader"));
        String actual = actualText.getText();

        //Expected
        String expected = "Welcome to the Secure Area. When you are done click logout below.";

        //Assert using JUnit
        Assert.assertEquals("Text message is not displayed", expected, actual);
    }

    /**
     * verifyTheUsernameErrorMessage
     * Enter “tomsmith1” username
     * Enter “SuperSecretPassword!” password
     * Click on ‘LOGIN’ button
     * Verify the error message “Your username
     * is invalid!”
     */
    @Test
    public void verifyTheUsernameErrorMessage() {

        WebElement enterUserName = driver.findElement(By.id("username"));
        enterUserName.sendKeys("tomsmith1");

        WebElement enterPassWord = driver.findElement(By.id("password"));
        enterPassWord.sendKeys("SuperSecretPassword!");

        WebElement clickLoginButton = driver.findElement(By.cssSelector("i.fa.fa-2x.fa-sign-in"));
        clickLoginButton.click();

        //Actual
        WebElement actualText = driver.findElement(By.id("flash-messages"));
        // WebElement actualText = driver.findElement(By.xpath("div[@class='large-12 columns']//div[text()='flash-messages']"));
        //System.out.println("Actual element is: " + actualText);
        String actual = actualText.getText();

        //Expected
        String expected = "Your username is invalid!";

        //Assert
        boolean trueMessage = actual.contains("is invalid");// true or false
        Assert.assertTrue(trueMessage);

    }

    /**
     * verifyThePasswordErrorMessage
     * Enter “tomsmith” username
     * Enter “SuperSecretPassword” password
     * Click on ‘LOGIN’ button
     * Verify the error message “Your password
     * is invalid!”
     */
    @Test
    public void verifyThePasswordErrorMessage() {

        WebElement enterUserName = driver.findElement(By.id("username"));
        enterUserName.sendKeys("tomsmith");

        WebElement enterPassWord = driver.findElement(By.id("password"));
        enterPassWord.sendKeys("SuperSecretPassword");

        WebElement clickLoginButton = driver.findElement(By.cssSelector("i.fa.fa-2x.fa-sign-in"));
        clickLoginButton.click();

        //Actual
        WebElement actualText = driver.findElement(By.cssSelector("div.flash.error"));
        String actual = actualText.getText();

        //Expected
        String expected = "Your password is invalid!";

        //Assert
        // Assert.assertEquals("Error message is not visible", expected, actual);
        boolean trueMessage = actual.contains(expected);
        Assert.assertTrue(trueMessage);

    }

    @After
    public void tearDown() {
        closeBrowser();
    }


}
