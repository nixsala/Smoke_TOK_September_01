package com.test.qa.utils;


import org.apache.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import utils.MethodBase;
import utils.PageBase;

import java.lang.reflect.Method;
import java.net.MalformedURLException;

public class

TestBase {
	public SoftAssert softAssert;
	
	private static final Logger LOGGER = Logger.getLogger(TestBase.class);
	
	@BeforeTest(alwaysRun = true)
    public void beforeTest() {
        System.out.println("Test Running " + this.getClass().toString());
    }
	
	@BeforeMethod(alwaysRun = true)
	public void loadBrowser() {
		LOGGER.info("Initiate Browser");
		try {
			PageBase.initiateDriver();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		LOGGER.info("Browser Initiated");

		MethodBase.setText_ByID("usernameOrEmail","admin");
		MethodBase.setText_ByID("usernameOrEmail","password");
		MethodBase.click_ByXpath("//button");


		LOGGER.info("successful Login");

		PageBase.staticWait(5);
	}
	
	@BeforeMethod(alwaysRun = true)
    public void beforeMethod() { 
		softAssert = new SoftAssert(); 
	}

		@BeforeMethod(alwaysRun = true)
		public void nameBefore(Method method) {
			LOGGER.info("Test name: " + method.getName());
		}

		@AfterMethod(alwaysRun = true)
		public void afterMethod(Method method, ITestResult result) {

			LOGGER.info("Executed test case name:" + method.getName() + " Execution Results : " + result.toString());

			PageBase.closeDriver();
	}
}
