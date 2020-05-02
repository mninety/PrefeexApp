package PrefeexAppActivity;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.text.html.ListView;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import MethodsRepo.MyMethods;
import StartPrefeexApp.VariableModule;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
/*
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.util.LoadLibs;
*/

public class PrefeexApp {

	public static void PrefeexAppStart(AndroidDriver<WebElement> driver) throws Exception {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 30);// 30 sec 

		driver.findElement(By.id("email_login")).click();
		driver.findElement(By.id("email")).sendKeys(VariableModule.EndUser);
		driver.findElement(By.id("password")).sendKeys(VariableModule.euPassword);
		driver.findElement(By.id("login")).click();

	}

	public static void ProfilePage(AndroidDriver<WebElement> driver) throws Exception {

		driver.findElement(By.id("action_bar_right_icon")).click();

		String pageTile= driver.findElement(By.id("action_bar_title")).getText();
		if(pageTile.equals("Profile"))
		{
			System.out.println("Profile Page comes!!!");
		}

	}

	public static String GetPageTitle(AndroidDriver<WebElement> driver) throws Exception {

		String pageTitle= driver.findElement(By.id("action_bar_title")).getText();
		if(pageTitle.equals("Profile"))
		{
			System.out.println("Profile Page comes!!!");
		}
		return pageTitle;
	}
	public static void OrderfromApp(AndroidDriver<WebElement> driver) throws Exception {
		//Thread.sleep(5000);
		ListElement(driver, "CASUAL", "restaurant_category", 1); //Casual Type selection
		//driver.findElement(By.xpath("//androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[@index='1']")).click();
		//select CASUAL
		Thread.sleep(15000);
		//driver.findElement(By.xpath("//androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[@index='0']")).click();
		//select Pan De Asia Restaurent
		ListElement(driver, "Pan De Asia", "restaurant_name", 1); //select Pan De Asia Restaurent
		Thread.sleep(5000);
		driver.findElement(By.id("btn_ok")).click(); //Time selection
		//ListElement(driver, "OK", "btn_ok", 1);
		ListElement(driver, "Hyderabadi biryani", "food_name", 1); //Food Menu selection
		driver.findElement(By.id("rbAddonName")).click(); //Checkbox select

		//AddonTest(driver, "android.widget.RadioButton", 3); //Addon selection
		
		
		driver.findElement(By.id("tvTotalQuantity")).click(); //Add to Cart
		driver.findElement(By.id("btn_view_cart")).click();
		driver.findElement(By.id("btn_left")).click();
		

			String alert=driver.findElement(By.id("alertTitle")).getText();
			System.out.println("Toast: "+alert);
			if(alert.equals("Placing order"))
			{
			MyMethods.ExcelFileWriteAction("Order From Apps", "Order process is ok or not?", "Failed", MyMethods.getCurrentDate(), alert, "Order Input: ", 1);
			driver.quit();
			}
			else
			{
				MyMethods.ExcelFileWriteAction("Order From Apps", "Order process is ok or not?", "Successful", MyMethods.getCurrentDate(), "", "Order Input: ", 1);
			}
		//readToastMessage(driver);
		//String toast=driver.findElement(By.xpath("//android.widget.Toast")).getText();
		//System.out.println("Toast: "+toast);
		
	}

	public static void Reservation(AndroidDriver<WebElement> driver) throws Exception {
		Thread.sleep(15000);
		driver.findElement(By.id("navigation_history")).click();
		Thread.sleep(10000);
		ListElement(driver, "390", "order_id",1);

		
		
	}
	
	public static void ListElement(AndroidDriver<WebElement> driver, String elementText, String elementID, int properties) throws Exception
	{
		List<WebElement> ele = null;

		if(properties==1)
		{
			ele = driver.findElements(By.id(elementID));
		}
		else if(properties==2)
		{
			ele = driver.findElements(By.xpath(elementID));
		}
		else //properties=3
		{
			ele = driver.findElements(By.className(elementID));
		}
	    //System.out.println("Elements: "+ele);
	    System.out.println("Size: "+ele.size());
	    for (int i = 0; i < ele.size(); i++) {

	        String s = ele.get(i).getText();
	        System.out.println("Food Name: "+s);
	        if (s.equals(elementText)) {
	        	ele.get(i).click();
	        	Thread.sleep(5000);
	        	System.out.println("Element Found");
	            break;
	        }

	    }
	}
	
	public static void AddonTest(AndroidDriver<WebElement> driver, String elementID, int properties) throws Exception
	{
		List<WebElement> ele = null;

		if(properties==1)
		{
			ele = driver.findElements(By.id(elementID));
		}
		else if(properties==2)
		{
			ele = driver.findElements(By.xpath(elementID));
		}
		else //properties=3
		{
			ele = driver.findElements(By.className(elementID));
		}
	    //System.out.println("Elements: "+ele);
	    System.out.println("Size: "+ele.size());
	    for (int i = 0; i < ele.size(); i++) {
	    	ele.get(i).click();
	        //String s = ele.get(i).getText();
	        //System.out.println("Food Name: "+s);

	    }
	}
	
	
	
	  static String scrShotDir = "screenshots";
	  File scrFile;
	  static File scrShotDirPath = new java.io.File("./"+ scrShotDir+ "//");
	  String destFile;
	 /*
	 public static String readToastMessage(AndroidDriver<WebElement> driver) throws Exception {
	  String imgName = takeScreenShot(driver, scrShotDir);
	  String result = null;
	  File imageFile = new File(scrShotDirPath, imgName);
	  //System.out.println("Image name is :" + imageFile.toString());
	  
	  ITesseract instance = new Tesseract();

	  File tessDataFolder = LoadLibs.extractTessResources("tessdata"); // Extracts
	                   // Tessdata
	                   // folder
	                   // from
	                   // referenced
	                   // tess4j
	                   // jar
	                   // for
	                   // language
	                   // support
	  instance.setDatapath(tessDataFolder.getAbsolutePath()); // sets tessData
	                // path

	  result = instance.doOCR(imageFile);
	  //System.out.println(result);
	  return result;
	 }
	*/ 
	
	 public static String takeScreenShot(AndroidDriver<WebElement> driver, String scrShotDir) {
		  File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE); 
		  
		  SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
		  new File(scrShotDir).mkdirs(); // Create folder under project with name
		          // "screenshots" if doesn't exist
		  String destFile = dateFormat.format(new Date()) + ".png"; // Set file name
		               // using current
		               // date time.
		  try {
		   FileUtils.copyFile(scrFile, new File(scrShotDir + "/" + destFile)); // Copy
		                    // paste
		                    // file
		                    // at
		                    // destination
		                    // folder
		                    // location
		  } catch (IOException e) {
		   System.out.println("Image not transfered to screenshot folder");
		   e.printStackTrace();
		  }
		  return destFile;
		 }
	 
}
