package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GoogleSearchSteps {
    WebDriver driver;

    @Given("I open Google homepage")
    public void openGoogleHomepage() {
        String gridUrl = "http://localhost:4444/wd/hub";

        if (isUrlReachable(gridUrl)) {
            try {
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability("browserName", "chrome");
                driver = new RemoteWebDriver(new URL(gridUrl), capabilities);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else {
            driver = new ChromeDriver();
        }
        driver.get("https://www.google.com");
    }

    private boolean isUrlReachable(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();
            return (200 <= responseCode && responseCode <= 399);
        } catch (Exception e) {
            return false;
        }
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
