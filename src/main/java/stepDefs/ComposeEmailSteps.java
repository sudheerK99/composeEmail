package stepDefs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

public class ComposeEmailSteps {
    WebDriver driver;
	String url_GMail = "https://mail.google.com";
	String in_email = "//input[@type='email']";
	String btn_next = "//span[contains(text(),'Next')]";
	String in_pwd = "//input[@type='password']";
	String in_recipient = "//input[@role='combobox']";
	String in_body = "//div[@aria-label='Message Body']";
	String msg_confirm = "//div[contains(text(),'Message sent.')]";
	
	WebDriverWait wait = new WebDriverWait(driver,30);

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(url_GMail);
    }

    @Given("I am logged in to Gmail")
    public void i_am_logged_in_to_Gmail() {
    	WebElement input_email = driver.findElement(By.xpath(in_email));
    	wait.until(ExpectedConditions.visibilityOf(input_email));
    	input_email.sendKeys("abc@gmail.com");
    	WebElement btn_Next = driver.findElement(By.xpath(btn_next));
    	wait.until(ExpectedConditions.elementToBeClickable(btn_Next));
    	btn_Next.click();
    	WebElement input_pwd = driver.findElement(By.xpath(in_pwd));
    	wait.until(ExpectedConditions.visibilityOf(input_pwd));
    	input_email.sendKeys("secretCode");
		btn_Next.click();
    }

    @When("I click on the {string} button")
    public void i_click_on_the_button(String buttonLabel) {
        WebElement button = driver.findElement(By.xpath("//div[text()='" + buttonLabel + "']"));
        wait.until(ExpectedConditions.elementToBeClickable(button));
        button.click();
    }

    @And("I enter the recipient's email address")
    public void i_enter_the_recipient_s_email_address() {
    	WebElement recipient_input = driver.findElement(By.xpath(in_recipient));
    	wait.until(ExpectedConditions.visibilityOf(recipient_input));
    	recipient_input.sendKeys("test@gmail.com");
    }

    @And("I enter {string} in the subject field")
    public void i_enter_in_the_subject_field(String subject) {
        WebElement subjectField = driver.findElement(By.name("subjectbox"));
        wait.until(ExpectedConditions.visibilityOf(subjectField));
        subjectField.sendKeys(subject);
    }

    @And("I enter {string} in the email body")
    public void i_enter_in_the_email_body(String emailBody) {
        WebElement emailBodyField = driver.findElement(By.xpath(in_body));
        wait.until(ExpectedConditions.visibilityOf(emailBodyField));
        emailBodyField.sendKeys(emailBody);
    }

    @And("I click the {string} button")
    public void i_click_the_button(String buttonLabel) {
        WebElement sendButton = driver.findElement(By.xpath("//div[text()='" + buttonLabel + "']"));
        wait.until(ExpectedConditions.elementToBeClickable(sendButton));
        sendButton.click();
    }

    @Then("the email should be sent successfully")
    public void the_email_should_be_sent_successfully() {
        WebElement confirmationMessage = driver.findElement(By.xpath(msg_confirm));
        Assert.assertTrue(confirmationMessage.isDisplayed());
    }

    @Then("I should see an error message for the missing subject")
    public void i_should_see_an_error_message_for_the_missing_subject() {
        WebElement errorMessage = driver.findElement(By.xpath("//div[contains(text(),'You have not entered a subject')]"));
        Assert.assertTrue(errorMessage.isDisplayed());
    }

    @Then("I should see an error message for the missing recipient")
    public void i_should_see_an_error_message_for_the_missing_recipient() {
        WebElement errorMessage = driver.findElement(By.xpath("//div[contains(text(),'Please specify at least one recipient')]"));
        Assert.assertTrue(errorMessage.isDisplayed());
    }

    @Then("I should see an error message for the missing email body")
    public void i_should_see_an_error_message_for_the_missing_email_body() {
        WebElement errorMessage = driver.findElement(By.xpath("//div[contains(text(),'Message body cannot be empty')]"));
        Assert.assertTrue(errorMessage.isDisplayed());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

