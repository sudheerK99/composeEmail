package runner;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions( tags = {"@compose"}, glue = {"stepdefs"}, features = {"src/main/java/features/ComposeEmailSteps"})

public class ComposeRunner {
	
	@BeforeClass
	public static void before() {
		System.out.println("Before - "+System.currentTimeMillis());
	}
	
	@AfterClass
	public static void after() {
		System.out.println("After - "+System.currentTimeMillis());
	}

}

