package StepDefinations;

import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class StepDefination {

    WebDriver driver;

    @Given("User launches the URL {string}")
    public void user_launches_the_url(String url) {
        System.setProperty("webdriver.chrome.driver", "ChromeDriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(url);
    }

    @Then("User verifies the title of the page")
    public void user_verifies_the_title_of_the_page() {
        String expectedTitle = "The Internet";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @When("User clicks on A\\/B Testing link")
    public void user_clicks_on_ab_testing_link() {
        WebElement abTestingLink = driver.findElement(By.xpath("//a[contains(text(),'A/B Testing')]"));
        abTestingLink.click();
    }

    @Then("User verifies the text on the page as {string}")
    public void user_verifies_the_text_on_the_page_as(String expectedText) {
        WebElement pageTextElement = driver.findElement(By.xpath("//h3[contains(.,'A/B Test Control')]"));
        String actualText = pageTextElement.getText();
        Assert.assertEquals(actualText, expectedText);
    }

    @When("User navigates back to Home page and clicks on dropdown link")
    public void user_navigates_back_to_home_page_and_clicks_on_dropdown_link() {
        driver.navigate().back();
        WebElement dropdownLink = driver.findElement(By.xpath("//a[contains(text(),'Dropdown')]"));
        dropdownLink.click();
    }

    @And("User selects {string} from the dropdown")
    public void user_selects_from_the_dropdown(String option) {
        WebElement dropdown = driver.findElement(By.id("dropdown"));
        dropdown.sendKeys(option);
    }

    @Then("User confirms if {string} is selected")
    public void user_confirms_if_is_selected(String expectedOption) {
        WebElement selectedOption = driver.findElement(By.xpath("//select[@id='dropdown']"));
        Assert.assertEquals(selectedOption.getAttribute("value"), expectedOption);
    }

    @When("User navigates back to Home Page and clicks on Frames")
    public void user_navigates_back_to_home_page_and_clicks_on_frames() {
        driver.navigate().back();
        WebElement framesLink = driver.findElement(By.xpath("//a[contains(@href,'/frames')]"));
        framesLink.click();
    }

    @Then("User verifies the presence of hyperlinks on the Frames Page")
    public void user_verifies_the_presence_of_hyperlinks_on_the_frames_page() {
        WebElement nestedFramesLink = driver.findElement(By.xpath("//a[contains(@href,'/nested_frames')]"));
        WebElement iFrameLink = driver.findElement(By.xpath("//a[contains(@href,'/iframe')]"));
        Assert.assertTrue(nestedFramesLink.isDisplayed());
        Assert.assertTrue(iFrameLink.isDisplayed());
    }

    @And("verifies Nested Frames hyperlink")
    public void verifies_nested_frames_hyperlink() {
        WebElement nestedFramesLink = driver.findElement(By.xpath("//a[contains(@href,'/nested_frames')]"));
        Assert.assertTrue(nestedFramesLink.isDisplayed());
    }

    @And("verifies iFrame hyperlink")
    public void verifies_iframe_hyperlink() {
        WebElement iFrameLink = driver.findElement(By.xpath("//a[contains(@href,'/iframe')]"));
        Assert.assertTrue(iFrameLink.isDisplayed());
    }

    //@After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
