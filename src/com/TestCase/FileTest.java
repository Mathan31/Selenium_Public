package com.TestCase;


import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FileTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		readData();

	}
	
	public static void readData() throws Exception{
		Scanner oScan = new Scanner(new File("./Data/Data_File.txt"));
		int i=0;
		List<BigInteger> oList = new ArrayList<>();

		while(oScan.hasNextLine()){
			String sText = oScan.nextLine();
			//System.out.println(sText);
			BigInteger mInt = null;
			if(i%2==1){
				System.out.println(sText);
				 StringBuilder sb = new StringBuilder();
				    for (char c : sText.toCharArray())
				    sb.append((int)c);
				    mInt = new BigInteger(sb.toString());
				    System.out.println(mInt);
				    oList.add(mInt); 		   
				}
				
				//int x = Integer.valueOf(sText);
			i++;
			}
		System.out.println(oList);
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\LENOVO\\Desktop\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("https://the-internet.herokuapp.com/iframe");
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		WebElement oFrame,oPara,oCount;
		oFrame = driver.findElement(By.xpath("//*[@id='mce_0_ifr']"));
		driver.switchTo().frame(oFrame);
		Thread.sleep(20000);
		oPara = driver.findElement(By.xpath("//*[@id='tinymce']/p"));
		int d=1;
		for(BigInteger b:oList){
		oPara.sendKeys(String.valueOf(b));
		
		if(d==5){
			break;
		}
		oPara.sendKeys(Keys.ENTER);
			System.out.println(b);
			d++;
		}
			List<WebElement> oElement = driver.findElements(By.xpath("//*[@id='tinymce']/p"));
			int iElement = oElement.size();
			if(iElement==5){
				System.out.println("Pass");
			}
		}
	
}

