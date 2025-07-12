package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {

    private final WebDriver driver;
    private final WebDriverWait wait;


    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ========== Locators ==========
    private final By genderMale        = By.id("gender-male");
    private final By firstName         = By.id("FirstName");
    private final By lastName          = By.id("LastName");
    private final By email             = By.id("Email");
    private final By password          = By.id("Password");
    private final By confirmPassword   = By.id("ConfirmPassword");
    private final By registerButton    = By.id("register-button");

    private By firstNameValidationError = By.cssSelector("span[for='FirstName']");
    private By lastNameValidationError = By.cssSelector("span[for='LastName']");
    private By emailValidationError = By.cssSelector("span[for='Email']");
    private By passwordValidationError = By.cssSelector("span[for='Password']");
    private By confirmPasswordValidationError = By.cssSelector("span[for='ConfirmPassword']");
    private By passwordMismatchError = By.cssSelector("span[for='ConfirmPassword']");
    private final By emailExistsError = By.cssSelector("div.message-error li");
    private final By registrationResult = By.cssSelector("div.result");



    // ========== Actions ==========
    public void selectGenderMale() {driver.findElement(genderMale).click();}
    public void enterFirstName(String first) {driver.findElement(firstName).sendKeys(first);}
    public void enterLastName(String last) {driver.findElement(lastName).sendKeys(last);}
    public void enterEmail(String mail) {driver.findElement(email).sendKeys(mail);}
    public void enterPassword(String pass) {driver.findElement(password).sendKeys(pass);}
    public void enterConfirmPassword(String pass) {driver.findElement(confirmPassword).sendKeys(pass);}
    public void clickRegister() {driver.findElement(registerButton).click();}


    // ========== Verification Methods ==========
    public boolean isRegistrationSuccessful() {
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(
                registrationResult, "Your registration completed"));
    }
    public boolean isEmailAlreadyExistsErrorVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                emailExistsError)).isDisplayed();
    }

    public String getRegistrationResult() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(registrationResult)).getText();
    }
    public String getFirstNameValidationError() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameValidationError)).getText();
    }
    public String getLastNameValidationError() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameValidationError)).getText();
    }
    public String getEmailValidationError() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(emailValidationError)).getText();
    }
    public String getPasswordValidationError() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordValidationError)).getText();
    }
    public String getConfirmPasswordValidationError() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(confirmPasswordValidationError)).getText();
    }
    public String getPasswordMismatchError() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordMismatchError)).getText();
    }
    public String getEmailAlreadyExistsError() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(emailExistsError)).getText();
    }
}

