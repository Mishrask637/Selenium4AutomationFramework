package com.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
    features = "src/test/resources/feature/AmazonSearch.feature",
    glue = {"com.stepdefinition","com.hooks"},
    dryRun = false,
    monochrome = true,
    plugin =  {"pretty","json:reports/json/cucumberReports.json","html:reports/html/cucumberReports.html"}
)

public class HomePageTestRunner extends AbstractTestNGCucumberTests {}
