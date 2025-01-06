package com.hooks;

import com.amazon.basetest.DriverFactory;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;

public class cucumberHooks {

    @BeforeAll
    public static void beforeAll(){

    }

    @AfterAll
    public static void afterAll(){
        DriverFactory.getDriver().quit();
    }

}
