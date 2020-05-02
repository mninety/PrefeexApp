package StartPrefeexApp;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;


import java.awt.TrayIcon;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.security.auth.spi.LoginModule;
import javax.swing.SwingUtilities;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import MethodsRepo.MyMethods;
import PrefeexAppActivity.PrefeexApp;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;




public class VariableModule {
	//ActionModule actiondo = new ActionModule();
	static Logger logVariableModule = Logger.getLogger(VariableModule.class.getName());
	
	//log.info("Error in: " + this.getClass.getName() + "at line #"+ this.getClass.getActualLine());
	

	
	//public Static String file_name="E:/Automation/Logs/Output.txt";
	public static String driverlog="D:/WorkSpace/PrefeexApp/Configuration"; // linux=/usr/local/iTelTestAutomation // Windows=D:/WorkSpace/PrefeexAPI/Configuration
	public static String sleepTime=readVariable("sleepTime",1); //in minute
	public static String token="";
	public static String driverselection = readVariable("driverselection",1); //1=firefox, 2=chrome, 3=unitdriver
	public static String isGUI = readVariable("isGUI", 1);
	
	public static String Url = readVariable("Url",1);
	public static String adminURL = readVariable("adminURL",1);
    public static String adminuser = readVariable("adminuser",1);
    public static String adminpass = readVariable("adminpass",1);
    public static String isAllAPITest = readVariable("isAllAPITest", 1);
    
    public static String isEmulator = readVariable("isEmulator",1);
    public static String testingMode = readVariable("testingMode", 1);
    
	public static String EndUser = readVariable("EndUser",1);
	public static String euPassword = readVariable("euPassword",1);
	
	public static String DEVICE_NAME = readVariable("DEVICE_NAME",1);
	public static String PLATFORM_VERSION = readVariable("PLATFORM_VERSION",1);
	public static String appPackage = readVariable("appPackage",1);
	public static String appActivity = readVariable("appActivity",1);
    public static AndroidDriver<WebElement> appiumdriverweb;
    public static AppiumDriver<MobileElement> appiumdrivermob;
    
    public static int sheetno;
    

    
    public static void StartModule()
    {
        	
    	//ActionModule.MysqlConnectOwn(Ownconn);
    	
    	try {
			launchApp();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	public static void launchApp() throws MalformedURLException, InterruptedException {
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		
		if(isEmulator.equals("1"))
		{
			capabilities.setCapability("avd","MyAndroid");
			capabilities.setCapability(MobileCapabilityType.APP,"C:/Users/Lenovo/AppData/Local/Android/android-sdk/platform-tools/app-staging_third.apk");
			// Please change the device name according to your device name
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "MyAndroid");

			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
			
			// Please change the Android version according to your device's Android version
			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, VariableModule.PLATFORM_VERSION);
			
			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
			
			capabilities.setCapability("appPackage", VariableModule.appPackage);
			capabilities.setCapability("appActivity", VariableModule.appActivity);
			
			capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS,true);
		    
			capabilities.setCapability("autoAcceptAlerts","true");
			capabilities.setCapability("autoGrantPermissions", "true");
			
		}
		else
		{
			// Please change the device name according to your device name
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, VariableModule.DEVICE_NAME);

			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
			
			// Please change the Android version according to your device's Android version
			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, VariableModule.PLATFORM_VERSION);
			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
			
			capabilities.setCapability("appPackage", VariableModule.appPackage);
			capabilities.setCapability("appActivity", VariableModule.appActivity);
			
			capabilities.setCapability("autoAcceptAlerts","true");
			capabilities.setCapability("autoGrantPermissions", "true");
		}

		
		URL url = new URL(VariableModule.Url);
        
        appiumdriverweb = new AndroidDriver<WebElement>(url, capabilities);
		//appiumdrivermob = new AndroidDriver<MobileElement>(url, capabilities);
		
        try {
			PrefeexApp.PrefeexAppStart(appiumdriverweb);
			PrefeexApp.OrderfromApp(appiumdriverweb);
			//PrefeexApp.Reservation(appiumdriverweb);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public static String readVariable(String var, int configflag)
	{
		int flag=0;
		int gotvar=0;
		Character ch = new Character('a');
		StringBuilder Test = new StringBuilder("");
		StringBuilder tempvar = new StringBuilder("");
		String path = null;
		int data;
		try {
			if(configflag==1)
			{
				path=VariableModule.driverlog+"/PrefeexApp.txt";
			}
			else
			{
				//path=VariableModule.driverlog+"/WebDriver/Config/API-Config.txt";

			}
			Reader fileReader = new FileReader(path);
			data = fileReader.read();
			while(data != -1) {
				ch=(char)data;
				//System.out.println("String: "+ch+flag+gotvar);
				if(flag==0 && ch!='=') {
					Test.append(ch);
				}
				else if(flag==1 && gotvar==1) {
					if(ch!='=' && ch!=';') {
						tempvar.append(ch);
						
					}
				}
				if(ch=='\n') {
					flag=0;
					Test = new StringBuilder("");
				}
				else if(ch=='='){
					flag=1;
					String Test1=Test.toString();
					//System.out.println("Flag:"+Test1);
					//System.out.println("Actual:"+var);
					if(Test1.equals(var))
					{
						//System.out.println("Match Equal"+Test);
						gotvar=1;
					}
				}
				else if(ch==';' && gotvar==1) {
					//System.out.println("Variable Found:"+tempvar);
					break;
				}

				data = fileReader.read();
			  //System.out.println((char)data);
			}
			//String tempvar1=tempvar.toString();
			
			fileReader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tempvar.toString();
	}
	
	public static WebDriver HtmlUnitDriver()
	{
		WebDriver drive;
		drive = new HtmlUnitDriver();
		return drive;
	}


	public static String RandomStringGen(int len)
	{
		
		final String AB = "abcdefghijklmnopqrstuvwxyz";
		SecureRandom rnd = new SecureRandom();

		
		   StringBuilder sb = new StringBuilder( len );
		   for( int i = 0; i < len; i++ ) 
		      sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
		   
		   return sb.toString();
		
	}
	
	public static String RandomNumberGen(int len)
	{
		
		final String AB = "0123456789";
		SecureRandom rnd = new SecureRandom();

		
		   StringBuilder sb = new StringBuilder( len );
		   for( int i = 0; i < len; i++ ) 
		      sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
		   
		   return sb.toString();
		
	}
	

}
