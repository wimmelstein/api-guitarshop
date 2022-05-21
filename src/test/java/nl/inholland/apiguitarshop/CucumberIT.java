package nl.inholland.apiguitarshop;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources", glue = "nl.inholland.apiguitarshop.steps", plugin = "pretty", publish = true)
public class CucumberIT {
}
