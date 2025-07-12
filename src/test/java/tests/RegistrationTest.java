package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RegisterPage;

public class RegistrationTest extends BaseTest {

    @Test(priority = 0)
    public void TC_001_RegisterWithValidData() {
        driver.get("https://demowebshop.tricentis.com/register");

        RegisterPage registerPage = new RegisterPage(driver);

        // ✅ Generate unique email using timestamp
        String uniqueEmail = "testuser_" + System.currentTimeMillis() + "@example.com";

        registerPage.selectGenderMale();
        registerPage.enterFirstName("John");
        registerPage.enterLastName("Doe");
        registerPage.enterEmail(uniqueEmail);
        registerPage.enterPassword("Password123");
        registerPage.enterConfirmPassword("Password123");
        registerPage.clickRegister();

        Assert.assertTrue(registerPage.isRegistrationSuccessful(), "Registration message not displayed");
        Assert.assertEquals(registerPage.getRegistrationResult(), "Your registration completed", "Unexpected registration message");
    }


    @Test(priority = 1)
    public void TC_002_RegisterWithExistingEmail() {
        driver.get("https://demowebshop.tricentis.com/register");

        RegisterPage registerPage = new RegisterPage(driver);

        registerPage.selectGenderMale();
        registerPage.enterFirstName("John");
        registerPage.enterLastName("Doe");

        // Already registered email
        registerPage.enterEmail("existing.user@example.com");
        registerPage.enterPassword("Password123");
        registerPage.enterConfirmPassword("Password123");

        registerPage.clickRegister();

        Assert.assertTrue(registerPage.isEmailAlreadyExistsErrorVisible(), "Error message not displayed");
        Assert.assertEquals(registerPage.getEmailAlreadyExistsError(), "The specified email already exists", "Incorrect error message");
    }


    @Test(priority = 2)
    public void TC_003_RegisterWithInvalidEmailFormat() {
        driver.get("https://demowebshop.tricentis.com/register");

        RegisterPage registerPage = new RegisterPage(driver);

        registerPage.selectGenderMale();
        registerPage.enterFirstName("John");
        registerPage.enterLastName("Doe");
        registerPage.enterEmail("user.com"); // invalid email
        registerPage.enterPassword("Password123");
        registerPage.enterConfirmPassword("Password123");
        registerPage.clickRegister();

        Assert.assertEquals(registerPage.getEmailValidationError(), "Wrong email", "Email validation error mismatch");
    }


    @Test(priority = 3)
    public void TC_004_RegisterWithEmptyMandatoryFields() {
        driver.get("https://demowebshop.tricentis.com/register");

        RegisterPage registerPage = new RegisterPage(driver);

        // Don't fill anything, just submit
        registerPage.clickRegister();

        Assert.assertEquals(registerPage.getFirstNameValidationError(), "First name is required.", "First name validation error missing");
        Assert.assertEquals(registerPage.getLastNameValidationError(), "Last name is required.", "Last name validation error missing");
        Assert.assertEquals(registerPage.getEmailValidationError(), "Email is required.", "Email validation error missing");
        Assert.assertEquals(registerPage.getPasswordValidationError(), "Password is required.", "Password validation error missing");
        Assert.assertEquals(registerPage.getConfirmPasswordValidationError(), "Password is required.", "Confirm password validation error missing");
    }

    @Test(priority = 4)
    public void TC_005_RegisterWithPasswordMismatch() {
        driver.get("https://demowebshop.tricentis.com/register");

        RegisterPage registerPage = new RegisterPage(driver);

        registerPage.selectGenderMale();
        registerPage.enterFirstName("John");
        registerPage.enterLastName("Doe");
        String uniqueEmail = "testuser_" + System.currentTimeMillis() + "@example.com";
        registerPage.enterEmail(uniqueEmail);
        registerPage.enterPassword("Password123");
        registerPage.enterConfirmPassword("Password321"); // mismatch
        registerPage.clickRegister();

        Assert.assertEquals(registerPage.getPasswordMismatchError(), "The password and confirmation password do not match.", "Password mismatch error missing");
    }


    @Test(priority = 5)
    public void TC_006_RegisterWithWeakPassword() {
        driver.get("https://demowebshop.tricentis.com/register");

        RegisterPage registerPage = new RegisterPage(driver);

        registerPage.selectGenderMale();
        registerPage.enterFirstName("John");
        registerPage.enterLastName("Doe");
        String uniqueEmail = "testuser_" + System.currentTimeMillis() + "@example.com";
        registerPage.enterEmail(uniqueEmail);
        registerPage.enterPassword("12345"); // weak password
        registerPage.enterConfirmPassword("12345");
        registerPage.clickRegister();

        Assert.assertEquals(registerPage.getPasswordValidationError(), "The password should have at least 6 characters.", "Password strength validation error missing");
    }

}
