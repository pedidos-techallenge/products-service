package br.com.fiap.techchallange;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "br.com.fiap.techchallange.steps",
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        tags = "not @Ignore"
)
public class RunCucumberTests {
}
