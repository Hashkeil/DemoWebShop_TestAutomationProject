package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test(priority = 1)
    public void TC_007_LoginWithValidCredentials() {
        driver.get("https://demowebshop.tricentis.com/login");

        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterEmail("john.doe@example.com");
        loginPage.enterPassword("Password123");
        loginPage.clickLogin();

        Assert.assertTrue(loginPage.isUserLoggedIn(), "User is not logged in");
        Assert.assertTrue(loginPage.getCurrentUrl().contains("https://demowebshop.tricentis.com/"), "Redirection failed");
    }
}
