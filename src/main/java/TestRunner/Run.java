package TestRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features=".//FeatureFile/Login.feature",
//		features=".//FeatureFile/Customers.feature",
		glue="StepDefination",
		dryRun=false,
		monochrome=true,
		tags="@smoke",
		plugin= {"html:test-output",
				"pretty"}
		)
public class Run {

	

}
