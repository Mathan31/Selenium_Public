package com.Amazom.pages;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.security.UserAndPassword;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

import utilites.Wrapper;

public class Scenario1 {
	
	By frameLink = By.xpath("//*[text()='Frames']");
	By iFrameLink = By.xpath("//*[text()='iFrame']");
	By iFrame_Frame = By.xpath("//*[@id='mce_0_ifr']");
	By textContent = By.xpath("//*[@id='tinymce']/p");
	By oBold_Button = By.xpath("//*[@role='presentation']/i[@class='mce-ico mce-i-bold']");
	By oFile_Menu = By.xpath("//*[text()='File']");
	By oNew = By.xpath("//*[text()='New document']");
	
	//Scenario 2
	By oDisapperLink = By.xpath("//*[text()='Disappearing Elements']");
	
	//Scenario 3
		By ohoversLink = By.xpath("//*[text()='Hovers']");
		By oImage1 = By.xpath("(//*[@class='figure'])[1]");
		By oElementLink = By.xpath("//a[text()='Elemental Selenium']");
		By oText_Mouse = By.xpath("//*[text()='name: user1']");
	
	private WebDriver driver;
	static Wrapper oWrapper;
	ExtentReports logger;
		public Scenario1(WebDriver driver) {
		this.driver = driver;
		oWrapper = new Wrapper(driver);
		logger = new ExtentReports();
		logger.init("./Report/EMSI.html", false);
	}
	
public void Home_Page_Title() { 
		
	logger.startTest("Scenario 1", "Validating the Scenario 1");
		
		String sTitle = oWrapper.getPageTitle();
		System.out.println("Output : " + sTitle);
		if(sTitle.contains("Internet")){
			System.out.println("User Reaches to the Internet Home Page");
			logger.log(LogStatus.PASS,"User Reaches to the Internet Home Page");
		}else{
			System.out.println("Internet home page is not loaded");
			logger.log(LogStatus.FAIL,"User Not Reaches to the Internet Home Page");
		}
	
	}

public void clickFrameLink(){
	
	oWrapper.waituntilVisible(frameLink, 30);
	boolean frame_Link = oWrapper.verifyElementpresent(frameLink);
	
	
	if(frame_Link==true){
		logger.log(LogStatus.PASS,"Frame Link is presence in Internet Home Page");
		oWrapper.clickElement(frameLink);
	}else{
		logger.log(LogStatus.FAIL,"Frame Link is not presence in Internet Home Page");
	}
	oWrapper.waitforPageLoad(30);
	
}


public void clickiFrameLink(){
	
	oWrapper.waituntilVisible(iFrameLink, 30);
	boolean iframe_Link = oWrapper.verifyElementpresent(iFrameLink);
	
	
	if(iframe_Link==true){
		logger.log(LogStatus.PASS,"iFrame Link is presence in Internet Home Page");
		oWrapper.clickElement(iFrameLink);
	}else{
		logger.log(LogStatus.FAIL,"iFrame Link is not presence in Internet Home Page");
	}
	oWrapper.waitforPageLoad(30);
	
}

public void deleteAndaddContent() throws Exception{
	
	WebElement iFrame,oText,oBold;
	iFrame = driver.findElement(iFrame_Frame);
	driver.switchTo().frame(iFrame);
	logger.log(LogStatus.INFO,"Switched to the iFrame presence in the page");
	oText = driver.findElement(textContent);
	oText.clear();
	logger.log(LogStatus.PASS,"Deleted the Existing Content Presence in the Page");
	driver.switchTo().defaultContent();
	logger.log(LogStatus.INFO,"Switched to the Out of iFrame in the page");
	oWrapper.clickElement(oBold_Button);
	logger.log(LogStatus.PASS,"Bold Button is clicked from the Page");
	logger.log(LogStatus.INFO,"Switched to the iFrame presence in the page");
	driver.switchTo().frame(iFrame);
	oWrapper.waituntilVisible(textContent, 30);
	oWrapper.MouseOver(textContent);
	oWrapper.clickElement(textContent);
	JavascriptExecutor js = (JavascriptExecutor)driver;
	js.executeScript("document.getElementsByTagName('strong')[0].innerHTML ='Lorem ipsum dolor sit amet'");
	logger.log(LogStatus.PASS,"Provided Text is Typed Successfully");
	driver.switchTo().defaultContent();
	logger.log(LogStatus.INFO,"Switched to the Out of iFrame in the page");
	oWrapper.waitUntilVisible(oFile_Menu, 30);
	oWrapper.clickElement(oFile_Menu);
	logger.log(LogStatus.PASS,"clicked File Menu from the Page");
	oWrapper.waitUntilVisible(oNew, 30);
	oWrapper.clickElement(oNew);
	logger.log(LogStatus.PASS,"clicked New Doocument Menu from the Page");
	boolean oText_Avail = oWrapper.Iselementdisplayed(By.xpath("//*[@id='tinymce']/p//strong"));
	if(oText_Avail==true){
		logger.log(LogStatus.FAIL,"New Document is not created");
	}else{
		logger.log(LogStatus.PASS,"New Document is created");
	}
	logger.endTest();
}

public void disappearingLink() throws Exception{
	logger.startTest("Scenario 2", "Validating the Disappearing Scenario");
	oWrapper.waituntilVisible(oDisapperLink, 30);
	boolean disapperLink = oWrapper.verifyElementpresent(oDisapperLink);
	
	
	if(disapperLink==true){
		logger.log(LogStatus.PASS,"Disappearing Elements Link is presence in Internet Home Page");
		oWrapper.clickElement(oDisapperLink);
	}else{
		logger.log(LogStatus.FAIL,"Disappearing Elements Link is not presence in Internet Home Page");
	}
	oWrapper.waitforPageLoad(30);
	Thread.sleep(5000);
	List<WebElement> oList_Link = driver.findElements(By.xpath("//li/a"));
	int iSize = oList_Link.size();
	logger.log(LogStatus.PASS,"Total Link Presence in DisapperLink Page is : "+iSize);
	//driver.navigate().refresh();
	//driver.navigate().refresh();
	driver.manage().deleteAllCookies();
	driver.navigate().to("https://the-internet.herokuapp.com/disappearing_elements");
	//driver.navigate().to(driver.getCurrentUrl());
	JavascriptExecutor js = (JavascriptExecutor)driver;
	js.executeScript("return document.readyState");
	//driver.navigate().refresh();
	oWrapper.waitforPageLoad(30);
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	Thread.sleep(10000);
	List<WebElement> oList_LinkAfter = driver.findElements(By.xpath("//li/a"));
	int iSize_Refresh = oList_LinkAfter.size();
	System.out.println("After Refreash : "+iSize_Refresh);
	logger.log(LogStatus.PASS,"Total Link Presence in DisapperLink after Refresh is : "+iSize_Refresh);
	if(iSize_Refresh>iSize){
		logger.log(LogStatus.PASS,"Total Link Presence in DisapperLink is Increased After Page Refreash");
	}else{
		logger.log(LogStatus.FAIL,"Total Link Presence in DisapperLink is Increased After Page Refreash");
	}
	logger.endTest();
}

public void hoversLink() throws Exception{
	logger.startTest("Scenario 3", "Validating the Hovers Scenario");
	oWrapper.waituntilVisible(ohoversLink, 30);
	boolean hoversLink = oWrapper.verifyElementpresent(ohoversLink);
	
	
	if(hoversLink==true){
		logger.log(LogStatus.PASS,"Hovers Elements Link is presence in Hovers Home Page");
		oWrapper.clickElement(ohoversLink);
	}else{
		logger.log(LogStatus.FAIL,"Hovers Elements Link is not presence in Hovers Home Page");
	}
	oWrapper.waitforPageLoad(30);
	Thread.sleep(5000);
	WebElement oEle_Link;
	oEle_Link = driver.findElement(oElementLink);
	int x = oEle_Link.getLocation().x;
	int y = oEle_Link.getLocation().y;
	System.out.println("X-Coordinate is : "+x+" Y-Coordinate is : "+y);	
	oWrapper.waituntilVisible(oImage1, 30);
	oWrapper.MouseOver(oImage1);
	Thread.sleep(5000);
	logger.log(LogStatus.PASS,"MouseOver to Hovers Elements ");
	int x1 = oEle_Link.getLocation().x;
	int y1 = oEle_Link.getLocation().y;
	System.out.println("X-Coordinate is : "+x1+" Y-Coordinate is : "+y1);	
	if(x<=x1&&y<y1){
		logger.log(LogStatus.PASS,"Location of Elemental Selenium is Changed, So MouseOver Text is visible ");
		String sText = oWrapper.getText(oText_Mouse);
		if(sText.contains("user1")){
			logger.log(LogStatus.PASS,"MouseOver Text Looks Good, Since Location of Elemental Selenium is Changed ");
		}else{
			logger.log(LogStatus.FAIL,"MouseOver Text Not Looks Good");
		}
	}else{
		logger.log(LogStatus.FAIL,"Location of Elemental Selenium is Not Changed, So MouseOver Text is visible ");
	}
	logger.endTest();
}

public void fileTextDocument() throws Exception{
	logger.startTest("Scenario 5", "Validating with Text Document");
	Scanner oScan = new Scanner(new File("./Data/Data_File.txt"));
	int i=0;
	List<String> oList = new ArrayList<>();

	while(oScan.hasNextLine()){
		String sText = oScan.nextLine();
		//System.out.println(sText);
		BigInteger mInt = null;
		if(i%2==1){
			System.out.println(sText);
			 
			    oList.add(sText); 		   
			}
			
			//int x = Integer.valueOf(sText);
		i++;
		}
	System.out.println(oList);

	clickFrameLink();
	clickiFrameLink();
	WebElement iFrame,oText,oBold;
	iFrame = driver.findElement(iFrame_Frame);
	driver.switchTo().frame(iFrame);
	logger.log(LogStatus.INFO,"Switched to the iFrame presence in the page");
	oText = driver.findElement(textContent);
	oText.clear();
	logger.log(LogStatus.PASS,"Deleted the Existing Content Presence in the Page");
	driver.switchTo().defaultContent();
	oWrapper.waitUntilVisible(oFile_Menu, 30);
	oWrapper.clickElement(oFile_Menu);
	logger.log(LogStatus.PASS,"clicked File Menu from the Page");
	oWrapper.waitUntilVisible(oNew, 30);
	oWrapper.clickElement(oNew);
	logger.log(LogStatus.PASS,"clicked New Document Menu from the Page");
	//driver.get("https://the-internet.herokuapp.com/iframe");
	//oWrapper.waitforPageLoad(30);
	driver.switchTo().frame(iFrame);
	Thread.sleep(10000);
	logger.log(LogStatus.INFO,"Switched to the iFrame presence in the page");
	WebElement oPara;
	oPara = driver.findElement(By.xpath("//*[@id='tinymce']/p"));
	int d=1;
	for(String s:oList){
	//JavascriptExecutor js = (JavascriptExecutor)driver;
	//js.executeScript("document.getElementsByTagName('p')[0].innerHTML ='"+s+"");
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("document.getElementsByTagName('p')[0].innerHTML ='"+s+"'");
		
	//oPara.sendKeys(s);
//	js.executeScript("keyDown(Keys.CONTROL).keyDown(Keys.ENTER).click(arguments[0]).keyUp(Keys.ENTER));", oPara);
		//oWrapper.MouseOver(By.xpath("//*[@id='tinymce']/p"));
	//oPara.sendKeys("Mathan");
		
	logger.log(LogStatus.PASS,d+" Line Written in Content");
	if(d==5){
		break;
	}
//	oPara.sendKeys(Keys.ENTER);
//		System.out.println(b);
		d++;
	}
		List<WebElement> oElement = driver.findElements(By.xpath("//*[@id='tinymce']/p"));
		int iElement = oElement.size();
		if(iElement==5){
			logger.log(LogStatus.PASS,"5 Lines are presence in the File Content");
		}else{
			logger.log(LogStatus.FAIL,"5 Lines are not presence in the File Content");
		}
		logger.endTest();
		}

	
public void preLoader() throws Exception{
	By oParent_Frame = By.xpath("//iframe[@class='sourceView']");
	By oChild_Frame = By.xpath("//iframe[@id='iframe1']");
	By oTab = By.xpath("//*[text()='Tab 2']");
	
	logger.startTest("Scenario 6", "Validating the PreLoader");
	WebElement oTab2,oLoading,oChildFrame,oParentFrame,oTab1;
	oParentFrame = driver.findElement(By.xpath("//iframe[@class='sourceView']"));
	oWrapper.waitUntilVisible(oParent_Frame, 30);
	driver.switchTo().frame(oParentFrame);
	logger.log(LogStatus.INFO,"User Navigated to Parent Frame");
	oChildFrame = driver.findElement(By.xpath("//iframe[@id='iframe1']"));
	
	boolean oResult = oChildFrame.isDisplayed();
	oWrapper.waitUntilVisible(oChild_Frame, 30);
	driver.switchTo().frame(oChildFrame);
	logger.log(LogStatus.INFO,"User Navigated to Child Frame");
	oTab2 = driver.findElement(By.xpath("//*[text()='Tab 2']"));
	oWrapper.waitUntilVisible(oTab, 30);
	oTab2.click();
	logger.log(LogStatus.INFO,"Clicked on Tab2 Button");
	oLoading = driver.findElement(By.xpath("//*[text()='Loading']"));
	WebDriverWait oWait = new WebDriverWait(driver, 30);
	oWait.until(ExpectedConditions.visibilityOf(oLoading));
	if(oLoading.isDisplayed()) {
		System.out.println("Loading....");
		logger.log(LogStatus.PASS,"Loading Icon is Displaying");
		Thread.sleep(10000);
	}
	oTab1 = driver.findElement(By.xpath("//*[text()='Tab 1']"));
	oTab1.click();
	logger.log(LogStatus.INFO,"Clicked on Tab1 Button");
	Thread.sleep(10000);	
	List<WebElement> oList = driver.findElements(By.xpath("//*[@id='tabs-1']/ul/li"));
	int iSize = oList.size();
	for(WebElement s:oList){
		System.out.println(s.getText());
		logger.log(LogStatus.PASS,"List in Tab1 is : "+s.getText());
	}
	logger.endTest();
}







public void authentication() throws Exception{
	logger.startTest("Scenario 4", "Validating the Authentication");
	//driver.navigate().to("https://the-internet.herokuapp.com/basic_auth");
	//oWrapper.waitforPageLoad(30);
	driver.get("https://the-internet.herokuapp.com/basic_auth");
	//WebDriverWait oWait = new WebDriverWait(driver,30);
	//Alert oAlert = null;// = oWait.until(ExpectedConditions.alertIsPresent());
	//driver.switchTo().alert().sendKeys("Mathan");
	//oAlert.authenticateUsing(new UserAndPassword("Mathan", "Testing"));
	driver.switchTo().alert();
	Robot rb = new Robot();
	rb.keyPress(KeyEvent.VK_D);
	rb.keyRelease(KeyEvent.VK_D);
	Thread.sleep(2000);
	rb.keyPress(KeyEvent.VK_SHIFT);
	rb.keyPress(KeyEvent.VK_SEMICOLON);
	rb.keyRelease(KeyEvent.VK_SEMICOLON);
	rb.keyRelease(KeyEvent.VK_SHIFT);
	rb.keyPress(KeyEvent.VK_BACK_SLASH);
	rb.keyRelease(KeyEvent.VK_BACK_SLASH);
	Thread.sleep(2000);
	rb.keyPress(KeyEvent.VK_P);
	rb.keyRelease(KeyEvent.VK_P);
	rb.keyPress(KeyEvent.VK_I);
	rb.keyRelease(KeyEvent.VK_I);
	rb.keyPress(KeyEvent.VK_C);
	rb.keyRelease(KeyEvent.VK_C);
	Thread.sleep(2000);
	rb.keyPress(KeyEvent.VK_ENTER);
	rb.keyRelease(KeyEvent.VK_ENTER);
	Thread.sleep(2000);


	logger.endTest();
}
}