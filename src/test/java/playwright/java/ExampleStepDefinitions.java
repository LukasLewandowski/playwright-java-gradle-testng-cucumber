package playwright.java;

import com.microsoft.playwright.*;
import io.cucumber.java.en.*;
import io.cucumber.testng.CucumberOptions;

import static org.testng.Assert.*;

@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"com.playwright-java.step.definitions"},
        monochrome = false,
        dryRun = false,
        plugin = {
                "json:build/cucumber-reports/cucumber.json",
                "rerun:build/cucumber-reports/rerun.txt"
        })
public class ExampleStepDefinitions {
    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private Page page;

    @Given("I navigate to {string}")
    public void iNavigateTo(String url) {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
        context = browser.newContext();
        page = context.newPage();
        page.navigate(url);
    }

    @Then("the title should be {string}")
    public void theTitleShouldBe(String expectedTitle) {
        String actualTitle = page.title();
        assertEquals(expectedTitle, actualTitle);
        page.close();
        context.close();
        browser.close();
        playwright.close();
    }

    @Then("wait for {int} seconds")
    public void waitFor(int waitTime) {
        int waitTimeInMilliSeconds = waitTime * 1000;
        page.waitForTimeout(waitTimeInMilliSeconds);
    }
}
