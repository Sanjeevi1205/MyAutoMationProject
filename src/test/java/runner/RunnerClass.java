package runner;

import lombok.extern.java.Log;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@Log
@RunWith(Cucumber.class)
@CucumberOptions(features = {
		"src/test/resources/FeatureFiles/01_Signup.feature"
},
		glue = "stepdefinition",
		tags = "@ResendOTP", dryRun = false,
		plugin = "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:")

public class RunnerClass {

}