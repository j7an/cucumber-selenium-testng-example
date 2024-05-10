package steps;

import io.cucumber.java.en.*;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;
import static org.testng.Assert.*;
import pages.LoginPage;
import testutils.TestBase;

public class LoginSteps {
    private TestBase testBase;
    private LoginPage loginPage;

    public LoginSteps(TestBase testBase) {
        this.testBase = testBase;
    }

    @Before
    public void setUp() {
        testBase.initializeDriver("chrome");
        loginPage = new LoginPage(testBase.getDriver());
    }

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        loginPage.navigateTo("https://www.saucedemo.com/");
    }

    @When("the user enters username {string} and password {string}")
    public void the_user_enters_username_and_password(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @And("the user clicks on the login button")
    public void the_user_clicks_on_the_login_button() {
        loginPage.clickLoginButton();
    }

    @Then("the user should be redirected to the dashboard page")
    public void the_user_should_be_redirected_to_the_dashboard_page() {
        assertEquals(loginPage.getPageURL(), "https://www.saucedemo.com/inventory.html");
    }

    @After
    public void tearDown() {
        testBase.quit();
    }
}
