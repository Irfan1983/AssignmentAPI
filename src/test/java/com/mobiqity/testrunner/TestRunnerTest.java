package com.mobiqity.testrunner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.testng.annotations.BeforeMethod;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features",
        glue = "com.mobiqity.assignment.stepdefinition",
        tags = "@SmokeTesting or @TestFlow",
        plugin = {"pretty", "junit:target/test-runner1-results.xml",
                "json:target/test-runner1-cucumber-reports.json", "html:target/TestRunner1.html"},
        monochrome = true)


public class TestRunnerTest {

}
