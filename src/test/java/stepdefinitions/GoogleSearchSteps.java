package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.net.URL;

public class GoogleSearchSteps {
    WebDriver driver;

    @Given("I open Google homepage")
    public void openGoogleHomepage() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");

        // Update the URL below to the correct Docker Grid Hub location.
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        driver.get("https://www.google.com");
    }

    @When("I search for {string}")
    public void searchForKeyword(String keyword) {
        driver.findElement(By.name("q")).sendKeys(keyword);
        driver.findElement(By.name("q")).submit();
    }

    @Then("I should see {string} in the search results")
    public void verifySearchResults(String keyword) {
        Assert.assertTrue(driver.getPageSource().contains(keyword));
        driver.quit();
    }
}
