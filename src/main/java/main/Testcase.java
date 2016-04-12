
package main;

import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class Testcase {
	
	public Utilities util;
	public DesiredCapabilities capability;
	public static final Modules module = new Modules();

	@Parameters({"browser"})

	@BeforeClass
	public void setupClass (String browser) throws Exception {

		try {
			//ChromeOptions options = new ChromeOptions();
			//options.addArguments("--start-maximized");
			capability = Capabilities.gridSetUp(browser);
			util = new Utilities(capability);
			util.goTo(module.calURL);
			//util.selectMainWindow();
			util.windowMaximize();
		}
		catch (UnreachableBrowserException ue){
			System.out.println(" ** setupClass catch UnreachableBrowserException");
			//ue.printStackTrace();
			tearDownClass();
		}
		catch (WebDriverException we) {
			System.out.println(" ** setupClass catch WebDriverException");
			//we.printStackTrace();
			tearDownClass();
		}

		//util.windowMaximize();
		util.printLog("Start Suite : " + util.printClassName(this));
	}
	
	@AfterMethod
	 public void afterScreenShot(ITestResult result) throws Exception {
		
		util.captureScreen(result);
	} 
	
	@BeforeMethod(alwaysRun=true)
	 public void beforeScreenShot() throws Exception {
		
		util.captureScreen();
	}
	
	@AfterClass
	public void tearDownClass(){
		
		try {
			util.quit();
		}
		catch (WebDriverException we) {
			System.out.println(" ** tearDownClass catch WebDriverException");
		}
		util.printLog("Quit Suite : " + util.printClassName(this));
		//util.close();
	}
}
