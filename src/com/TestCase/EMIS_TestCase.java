package com.TestCase;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.ApplicationFn.Herokuapp_Application_Fn;


public class EMIS_TestCase {
	
	public static WebDriver driver;
	public Herokuapp_Application_Fn oFun;
	
	@BeforeTest
	public void invokeBrowser(){
		oFun = new Herokuapp_Application_Fn(driver);
		oFun.Initiate_Browser();
	}
	
	 
	@Test(enabled=true,priority=1)
	  public void homePageValidation() throws Exception {
			  
		  oFun.Lunch_Application();
		  oFun.validateScenarioone();
		  oFun.Lunch_Application();
		  oFun.validateScenariotwo();
		  oFun.Lunch_Application();
		  oFun.validateScenariothree();
//		  oFun.Lunch_Application();
//		  oFun.validateScenariofour();
//		  oFun.Lunch_Application();
//		  oFun.validateScenariofive();
		  oFun.Lunch_JQueryApplication();
		  oFun.validateScenariosix();
	  }
	

}
