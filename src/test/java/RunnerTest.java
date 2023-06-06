import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features/1_EndpointFunctionality.feature",
        glue = "StepDefs"
)

public class RunnerTest {
    // This is just a placeholder class
}
