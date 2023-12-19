package BaseTest;



import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/FeatureFiles",
        glue = "StepDefinations"
)
public class BaseTest extends AbstractTestNGCucumberTests {
}
