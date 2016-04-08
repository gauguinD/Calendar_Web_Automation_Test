package main;

//import static org.junit.Assert.*;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.RemoteTouchScreen;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import static com.thoughtworks.selenium.SeleneseTestBase.fail;

public class Utilities extends RemoteWebDriver implements TakesScreenshot {
	
	//protected final double WAIT_TICK = 0.5; // by second
	protected final int TIME_OUT_SEC = 20; // by second
	protected final int WAIT_SEC = 1;
	//protected final String WAIT_TIME_OUT = "15000";
	private final int PAGE_LOAD_TIME_OUT = 30;
	private final int MAX_TRY_COUNT = 10;
	
	private final String MAXIMIZE_BROWSER_WINDOW = 
		"if (window.screen) {window.moveTo(0, 0);window.resizeTo(window.screen.availWidth,window.screen.availHeight);};";

	// RemoteWebDriver Hub Address
	//public static String HubAddress = "http://localhost:4444/wd/hub";
	//public static String HubAddress = "http://10.12.61.38:4444/wd/hub";
	public static String HubAddress = "http://10.12.45.246:4444/wd/hub";
	public static RemoteTouchScreen touch;
	
	public String mainWindowHandle = null;
	public String lastWindowHandle = null;
	
	public String beforeFilePath = null;
	public String beforeFilePath2 = null;
	
	public Utilities (DesiredCapabilities capability) throws MalformedURLException {
		this(HubAddress, capability);
		//touch = new RemoteTouchScreen(getExecuteMethod());
	}
	
	public Utilities (String url, DesiredCapabilities capability) throws MalformedURLException { 
		super (new URL (url), capability);
		//touch = new RemoteTouchScreen(getExecuteMethod());
	}
	
	/*public TouchScreen getTouch() {
        return touch;
    }*/
	
	public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
		
		X targetShot = null;
		targetShot = target.convertFromBase64Png(execute(DriverCommand.SCREENSHOT).getValue().toString());
		
		return targetShot;
		/*
		if ((Boolean) getCapabilities().getCapability(CapabilityType.TAKES_SCREENSHOT)) { 
			  return target.convertFromBase64Png(execute(DriverCommand.SCREENSHOT).getValue().toString());
		} 
		return null;
		*/
	}
	
	public void captureScreen (ITestResult result) throws Exception {
		
		Date date = new Date();
		File directory = new File (".");
		DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd__HH_mm_ss");
		
		String FileNamePath = "\\ScreenShots\\"+ getCapabilities().getBrowserName() + getCapabilities().getVersion() + "\\" + dateFormat.format(date)+"_"+ "finish.png";
		String FileNamePath2 = directory.getCanonicalPath()+ FileNamePath;
		
		try {
			File screenshot = getScreenshotAs(OutputType.FILE);  			
			FileUtils.copyFile(screenshot, new File(FileNamePath2));
			
			//filename is the name of file where screenshot is copied 
			Reporter.setCurrentTestResult(result); 
			Reporter.log("<a href=\"" + "..\\..\\ws\\target" + beforeFilePath + "\" target=\"_blank\">Start</a>&nbsp;");  
			Reporter.log("<a href=\"" + "..\\..\\ws\\target" + FileNamePath + "\" target=\"_blank\">Finish</a>");  
		} 
		catch (IOException e1) {
			printLog("After ScreenShot Error : " + FileNamePath2);      
			//e1.printStackTrace();
		} 
		catch (WebDriverException we) {
			printLog("** After ScreenShot catch WebDriver Exception");
			//we.printStackTrace();
		}
		catch (Exception e) {
			printLog ("** After ScreenShot catch Excetion");
			//e.printStackTrace();
		}

		//Reporter.log("<a href=\"" + beforeFilePath + "\">Start</a>&nbsp; ");
		//Reporter.log("<a href=\"" + FileNamePath + "\">Finish</a>");
	}
	
	
	public void captureScreen () throws Exception {

		Date date = new Date();
		File directory = new File (".");
		DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd__HH_mm_ss");
		
		beforeFilePath = "\\ScreenShots\\"+ getCapabilities().getBrowserName() + getCapabilities().getVersion() + "\\" + dateFormat.format(date)+"_"+ "start.png";
		beforeFilePath2 = directory.getCanonicalPath()+ beforeFilePath;
		
		try {
			File screenshot = getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File(beforeFilePath2));
		} 
		catch (IOException e1) {
			printLog("Before ScreenShot Error : " + beforeFilePath2);      
			//e1.printStackTrace();
		} 
		catch (WebDriverException we) {
			printLog("** Before ScreenShot catch WebDriver Exception");
			//we.printStackTrace();
		}
		catch (Exception e) {
			printLog ("** Before ScreenShot catch Excetion");
			//e.printStackTrace();
		}
				
		//String FileNamePath = directory.getCanonicalPath()+ "\\ScreenShots\\"+ getCapabilities().getBrowserName() + getCapabilities().getVersion() + "\\" + dateFormat.format(date)+"_"+ ".png";
		//beforeFilePath = directory.getCanonicalPath()+ "\\ScreenShots\\"+ getCapabilities().getBrowserName() + getCapabilities().getVersion() + "\\" + dateFormat.format(date)+"_"+ ".png";

		//filename is the name of file where screenshot is copied 
		//Reporter.log("<a href=\"" + FileNamePath + "\">Start Screenshot </a>");
	}
	
	/** 
	 * 파라미터로 받은 URL로 오픈 후 wait
	 * @param url 주소
	 * @return void
	 */
	public void openAndWait(String url) {
		navigate().to(url);
		waitForPageToLoad();
	}
	
	/*
	 * "Back" 실행 후 wait
	 * @param null
	 * @return void
	 */
	public void goBackAndWait() {
		navigate().back();
		waitForPageToLoad();
	}
	
	/*
	 * "Forward" 실행 후 wait
	 * @param null
	 * @return void
	 */
	public void goForwardAndWait() {
		navigate().forward();
		waitForPageToLoad();
	}
	
	/*
	 * "Refresh" 실행 후 wait
	 * @param null
	 * @return void
	 */
	public void goRefreshAndWait() {
		navigate().refresh();
		waitForPageToLoad();
	}
	
	/**
	  * 브라우저 사이즈를 최대화 시키는 메소드
	  * @return void
	  */
	/*public void windowMaximize() {
		executeJavascript (this, MAXIMIZE_BROWSER_WINDOW);
	}*/
	
	public void windowMaximize() {
		manage().window().maximize();
	}
	
	
	public static Object executeJavascript(Utilities util, String script) {
		JavascriptExecutor js = (JavascriptExecutor)util;
		return js.executeScript(script);
	}
	
	/*
	  * 로그를 출력하는 메소드
	  * 메소드를 실행하는 system의 정보를 출력 (platform, browser info)
	  * @param 출력할 메시지
	  * @return void
	  */
	public void printLog(String logs) {
		String browserName = getCapabilities().getBrowserName();
		Platform platForm = getCapabilities().getPlatform();
		String verSion = getCapabilities().getVersion();
				
		System.out.println ("[" + platForm + "/" + browserName + "" +  verSion + "]" + " " + logs);
		//Reporter.log("[" + platForm + "/" + browserName + "" +  verSion + "]" + " " + logs + "<br>");
		//System.out.println (logs);
		Reporter.log(logs + "<br>");
	}
	
	/**
	  * 물리적인 keyboard 입력을 처리하는 메소드
	  * @param locator	물리적 처리를 기다리는 element
	  * @param value	Press할 키보드 값
	  * @return void
	  */
	public void pressKeys(By locator, Keys value) throws Exception {
		
		WebElement element = null;
		element = waitForIsElementPresent(locator);
		//WebElement element = locator.findElement((SearchContext) this);
		
		element.sendKeys(value);
	}
	
	/**
	  * context menu를 가져오기 위한 메소드 (right-click)
	  * @param locator	우클릭 실행할 element
	  * @return void
	  */
	public void contextMenu (By locator) throws Exception {
		
		WebElement element = null;
		Actions action = new Actions (this);
		Robot robot = new Robot();
		  
		element = waitForIsElementPresent(locator);
		
		robot.mouseMove(Toolkit.getDefaultToolkit().getScreenSize().width/2, Toolkit.getDefaultToolkit().getScreenSize().height/2);
		action.contextClick(element).perform();
	 }
	
	/**
	  * 내용을 입력하는 메소드
	  * 메소드를 실행하는 system의 정보를 출력 (platform, browser info)
	  * @param locator	내용을 입력받을 element 정보
	  * @param inputText locator에 입력할 내용
	  * @return void
	  */
	public void type (By locator, String inputText) throws Exception {
		
		WebElement element = null;
		element = waitForIsElementPresent(locator);
		element.sendKeys(inputText);
		//locator.findElement((SearchContext) this).sendKeys(inputText);
	}
	
	public void type (By locator, Keys keyValue) throws Exception {
		
		WebElement element = null;
		element = waitForIsElementPresent(locator);
		element.sendKeys(keyValue);
		//locator.findElement((SearchContext) this).sendKeys(keyValue);
		
		waitForPageToLoad();
	}
	
	/**
	  * clear 후 내용을 입력하는 메소드
	  * 메소드를 실행하는 system의 정보를 출력 (platform, browser info)
	  * @param locator	내용을 입력받을 element 정보
	  * @param inputText locator에 입력할 내용
	  * @return void
	  */
	public void clearAndType (By locator, String inputText) throws Exception {
		
		WebElement element = null;
		element = waitForIsElementPresent(locator);
		clear(locator);
		element.sendKeys(inputText);
		//locator.findElement((SearchContext) this).sendKeys(inputText);
	}
	
	public void clear (By locator) throws Exception {
		
		WebElement element = null;
		
		element = waitForIsElementPresent(locator);
		element.clear();
		//locator.findElement((SearchContext) this).clear();
	}
	
	
	public WebElement waitForIsElementPresent (By locator) throws InterruptedException {
		
		int tryCount = 0;
		WebElement element = null;
		WebDriverWait wait = (WebDriverWait) new WebDriverWait(this, PAGE_LOAD_TIME_OUT);
		
		while (tryCount < MAX_TRY_COUNT) {
			try {
				element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
				if (element != null && element.isEnabled() && element.isDisplayed()){ 
					return element;
				}
			}
			catch (StaleElementReferenceException e){
				printLog ("** Catch StaleElement Exception : #" + tryCount );
				Thread.sleep(1000);
				tryCount = tryCount + 1;
			}
		}
		fail(locator + " : 해당 엘리먼트가 " + TIME_OUT_SEC + "초내에 노출되지 않음");
		return null;
	}
	

	/**
	 * Element가 존재하는지 확인하는 메소드
	 * @param locator 존재 확인 할 Element를 지정
	 * @return locator에 존재하는 WebElement (element, null)
	 * @throws Exception - Selenium Exception
	 */
	public WebElement isElementPresent(By locator) {
		
		WebElement result = null;
		
		try {
			
			WebElement element = locator.findElement((SearchContext) this);
			if (element.isDisplayed()) {
				result = element;
			}
			
			/*
			if (locator.findElement((SearchContext) this).isDisplayed()) {
				result = locator.findElement((SearchContext) this);
			}*/
			
		}
		catch (NoSuchElementException e) { 
			return null;
		}
		return result;
	}

	public void switchToFrame(By frameName) throws Exception {
		
		WebElement element = null;
		
		element = waitForIsElementPresent(frameName);
		switchTo().frame(element);
		//switchTo().frame(frameName.findElement((SearchContext) this));
	}
	
	/**
	  * MouseOver을 위한 메소드
	  * @param locator 링크
	  * @throws Exception - Selenium Exception
	  */
	public void waitAndMouseOver (By locator) throws Exception {
		
		WebElement target_element = null;
		int target_width = 0;
		int target_height = 0;
		 
	    Actions action = new Actions (this);
	    
	    target_element = waitForIsElementPresent(locator);
	    
	    target_width = (target_element.getSize().width)/2;
	    target_height = (target_element.getSize().height)/2;
	    
	    action.moveToElement(target_element, target_width, target_height).perform();	    
	    waitForPageToLoad();
	 }
	

	public void waitAndMouseOver (By locator, int offset_X, int offset_Y) throws Exception {
		
		WebElement target_element = null;
		
	    Actions action = new Actions (this);
	    
	    target_element = waitForIsElementPresent(locator);
//	    WebElement target_element = locator.findElement((SearchContext) this);
	
	    action.moveToElement(target_element, offset_X, offset_Y).perform();	    
	    waitForPageToLoad();
	 }

	
	/** 
	 * locator 클릭 후 기다리는 메소드
	 * @param locator 링크
	 * @throws Exception 
	 */
	
	public boolean clickAndWait(By locator) throws Exception {
	    
		int tryCount = 0;
		WebElement element = null;
		WebDriverWait wait = (WebDriverWait) new WebDriverWait(this, PAGE_LOAD_TIME_OUT);
		
		while (tryCount < MAX_TRY_COUNT) {
			try {
				element = wait.until(ExpectedConditions.elementToBeClickable(locator));
				if (element != null && element.isDisplayed() && element.isEnabled()) {
					element.click();
					waitForPageToLoad();
					return true;
				}
			}
			catch (StaleElementReferenceException e){
				printLog ("** Catch StaleElement Exception : #" + tryCount );
				Thread.sleep(1000);
				tryCount = tryCount + 1;
			}
		}
		fail(locator + " : 해당 엘리먼트가 " + TIME_OUT_SEC + "초내에 노출되지 않음");
		return false;
	}
	
	/** 
	 * locator 클릭 후 기다리지 않은 메소드 (wait alert)
	 * @param locator 링크
	 * @throws Exception 
	 */
	public boolean waitAndClick(By locator) throws Exception {
	    
		int tryCount = 0;
		WebElement element = null;
		WebDriverWait wait = (WebDriverWait) new WebDriverWait(this, PAGE_LOAD_TIME_OUT);
		
		while (tryCount < MAX_TRY_COUNT) {
			try {
				element = wait.until(ExpectedConditions.elementToBeClickable(locator));
				if (element != null && element.isDisplayed() && element.isEnabled()) {
					element.click();
					return true;
				}
			}
			catch (StaleElementReferenceException e){
				printLog ("** Catch StaleElement Exception : #" + tryCount );
				Thread.sleep(1000);
				tryCount = tryCount + 1;
			}
		}
		fail(locator + " : 해당 엘리먼트가 " + TIME_OUT_SEC + "초내에 노출되지 않음");
		return false;
	}

	
	/** 
	 * locator select 후 기다리는 메소드
	 * @param selectLocator 링크
	 * @paran byType 값을 선택할 방법 (byValue, byText, byIndex)
	 * @param optionLocator 선택할 값의 링크
	 * @throws Exception 
	 */
	
	public boolean selectAndWait(By selectLocator, String byType, String optionLocator) throws Exception {
		
		boolean result = false;
		WebElement element = null;
		
		element = waitForIsElementPresent(selectLocator);
		
		try {
			if (byType.equals("byText"))
				new Select(selectLocator.findElement((SearchContext) this)).selectByVisibleText(optionLocator);
			
			else if (byType.equals("byIndex"))
				new Select(selectLocator.findElement((SearchContext) this)).selectByIndex(Integer.parseInt(optionLocator));
			
			else if (byType.equals("byValue"))
				new Select(selectLocator.findElement((SearchContext) this)).selectByValue(optionLocator);
			
			else {
				System.err.println("unknown option type");
				return false;
			}
			waitForPageToLoad();
			result = true;
		} 
		catch (NoSuchElementException e) {
			result = false;
			//System.err.println("--- received an exception: " + t);
		}
		return result;
	}
	
	/*
	 * locator submit 후 기다리는 메소드
	 * @param locator 링크
	 * @throws Exception - Selenium Exception
	 */
	public boolean submitAndWait(By formLocator) {
		
		boolean result = false;
	    
		try {
			formLocator.findElement((SearchContext) this).submit();
			waitForPageToLoad();
			
			result = true;
			}
	    catch (NoSuchElementException e) {
	    	result = false;
	    	//System.err.println("--- received an exception: " + t);
	    	}
	    return result;
	}
	
	
	/**
	 * Alert이 나타날 때까지 대기하는 메쏘드
	 * @return Alert 노출 유무
	 * @throws Exception - Selenium Exception
	 */
	public Alert waitForAlert() throws Exception {
		
		Alert alert = null;
		WebDriverWait wait = (WebDriverWait) new WebDriverWait(this, PAGE_LOAD_TIME_OUT);
		alert = wait.until(ExpectedConditions.alertIsPresent());
		return alert;
	}

	public Alert isAlertPresent() { 
		 
		 Alert alert = null;
		
		 try {
			 alert = switchTo().alert();
		 }
		 
		 catch (NoAlertPresentException Ex) {
			 alert = null;
		 }
		
		 return alert;
	 }
			
	/**
	 * Element가 check 될 때까지 기다리는 메소드
	 * @param locator 대기할 Element를 지정
	 * @return Element check 유무
	 * @throws Exception - Selenium Exception
	 */
	public boolean waitForIsChecked(By locator) throws Exception {
		
		WebElement element = null;
		WebDriverWait wait = (WebDriverWait) new WebDriverWait(this, PAGE_LOAD_TIME_OUT);
		element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		
		for (int second = 0; second <= TIME_OUT_SEC; second += WAIT_SEC) {
			
			//element = isElementPresent(locator);
			//if (locator.findElement((SearchContext) this).isSelected())
			
			if (element.isSelected()) {
				return true;
			}
			sleep(WAIT_SEC);
		}

		fail(locator + " : 해당 엘리먼트가 " + TIME_OUT_SEC + "초내에 check 되지 않음");
		
		return false;
	}
	
	public WebElement isChecked (By locator) throws Exception {
		
		WebElement element = null;
		element = isElementPresent(locator);
		
		if (element != null && element.isSelected())
			return element;
		
		return null;
	}
	/**
	 * Element가 select 될 때까지 기다리는 메소드
	 * @param locator 대기할 Element를 지정
	 * @return Element select 유무
	 * @throws Exception - Selenium Exception
	 */
	public boolean waitForIsSelected(By locator) throws Exception {
		
		WebElement element = null;
		WebDriverWait wait = (WebDriverWait) new WebDriverWait(this, PAGE_LOAD_TIME_OUT).ignoring(StaleElementReferenceException.class);
		element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		
		for (int second = 0; second <= TIME_OUT_SEC; second += WAIT_SEC) {

			//element = isElementPresent(locator);
			//if (locator.findElement((SearchContext) this).isSelected())
			if (element.isSelected()){
				//printLog("<" + locator + "> : wait " + second + " seconds");
				return true;
			}
			
			sleep(WAIT_SEC);
		}

		fail(locator + " : 해당 엘리먼트가 " + TIME_OUT_SEC + "초내에 select 되지 않음");
		
		return false;
	}
	
	/**
	 * Element가 visible 될 때까지 기다리는 메소드
	 * @param locator 대기할 Element를 지정
	 * @return Element visible 유무
	 * @throws Exception - Selenium Exception
	 */
	public boolean waitForIsVisible(By locator) throws Exception {
		
		WebElement element = null;
		WebDriverWait wait = (WebDriverWait) new WebDriverWait(this, PAGE_LOAD_TIME_OUT).ignoring(StaleElementReferenceException.class);
		element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		
		for (int second = 0; second <= TIME_OUT_SEC; second += WAIT_SEC) {
			
			if (element.isDisplayed()) 
				return true;

			sleep(WAIT_SEC);
		}

		fail(locator + " : 해당 엘리먼트가 " + TIME_OUT_SEC + "초내에 visible 되지 않음");
		return false;
	}
	
	public boolean waitForIsNotVisible(By locator) throws Exception {
		
		WebElement element = null;
		
		for (int second = 0; second <= TIME_OUT_SEC; second += WAIT_SEC) {
			
			element = isElementPresent(locator);
			
			if (element == null || !element.isDisplayed())
				return true;
						
			sleep(WAIT_SEC);
		}

		fail(locator + " : 해당 엘리먼트가 " + TIME_OUT_SEC + "초내에 사라지지 않음");
		//System.out.println(locator + " : 해당 엘리먼트가 " + TIME_OUT_SEC + "초내에 사라지지 않음");
		return false;
	}
	
	/**
	 * searchText 나타날 때까지 기다리는 메소드
	 * @param searchText 나타날때까지 기다릴 Text
	 * @return Text Present 유무
	 * @throws Exception - Selenium Exception
	 */
	public boolean waitForIsTextPresent(String searchText) throws Exception {
	
		WebElement element = null;
		for (int second = 0; second <= TIME_OUT_SEC; second += WAIT_SEC) {

			//element = findElement(By.xpath("//*[contains(.,'"+searchText+"')]"));
			element = isElementPresent(By.xpath("//*[contains(.,'"+searchText+"')]"));
			
			if (element != null && element.isDisplayed()){
				//printLog("<" + searchText + "> : wait " + second + " seconds");
				return true;
			}
		
			sleep(WAIT_SEC);
		}

		fail(searchText + " : 해당 Text가 " + TIME_OUT_SEC + "초내에 나타나지 않음");
		
		return false;
	}
	
	
	/**
	 * locator가 노출될 때까지 기다렸다 xpath count를 읽어들이는 메소드
	 * @param locator 링크
	 * @return 읽어들인 xpath count (int)
	 * @throws Exception - Selenium Exception
	 */
	public int waitAndGetXpathCount(By locator) throws Exception {
	
		int tryCount = 0;
		List<WebElement> element = null;
		WebDriverWait wait = (WebDriverWait) new WebDriverWait(this, PAGE_LOAD_TIME_OUT);
		
		while (tryCount < MAX_TRY_COUNT) {
			try {
				element = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
				return element.size();
			}
			catch (StaleElementReferenceException e){
				printLog ("** Catch StaleElement Exception : #" + tryCount );
				Thread.sleep(1000);
				tryCount = tryCount + 1;
			}
		}
		fail(locator + " : 해당 엘리먼트가 " + TIME_OUT_SEC + "초내에 노출되지 않음");
		return 0;
	}
	
	/**
	 * locator가 노출될 때까지 기다렸다 text를 읽어들이는 메소드
	 * @param locator 링크
	 * @return 읽어들인 text (string)
	 * @throws Exception - Selenium Exception
	 */
	public String waitAndGetText(By locator) throws Exception {
		
		int tryCount = 0;
		WebElement element = null;
		WebDriverWait wait = (WebDriverWait) new WebDriverWait(this, PAGE_LOAD_TIME_OUT);
		
		while (tryCount < MAX_TRY_COUNT) {
			try {
				element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
				if (element != null && element.isEnabled() && element.isDisplayed()){ 
					return element.getText();
				}
			}
			catch (StaleElementReferenceException e){
				printLog ("** Catch StaleElement Exception : #" + tryCount );
				Thread.sleep(1000);
				tryCount = tryCount + 1;
			}
		}
		fail(locator + " : 해당 엘리먼트가 " + TIME_OUT_SEC + "초내에 노출되지 않음");
		return null;
	}
	
	/**
	 * locator가 노출될 때까지 기다렸다 value를 읽어들이는 메소드
	 * @param locator 링크
	 * @return 읽어들인 value (string)
	 * @throws Exception - Selenium Exception
	 */
	public String waitAndGetValue(By locator) throws Exception {
		
		int tryCount = 0;
		WebElement element = null;
		WebDriverWait wait = (WebDriverWait) new WebDriverWait(this, PAGE_LOAD_TIME_OUT);
		
		while (tryCount < MAX_TRY_COUNT) {
			try {
				element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
				if (element.isEnabled() && element.isDisplayed()){ 
					return element.getAttribute("value");
				}
			}
			catch (StaleElementReferenceException e){
				printLog ("** Catch StaleElement Exception : #" + tryCount );
				Thread.sleep(1000);
				tryCount = tryCount + 1;
			}
		}
		fail(locator + " : 해당 엘리먼트가 " + TIME_OUT_SEC + "초내에 노출되지 않음");
		return null;
	}
	
	public String waitAndGetAttribute(By locator, String attr) throws Exception {
		
		int tryCount = 0;
		WebElement element = null;
		WebDriverWait wait = (WebDriverWait) new WebDriverWait(this, PAGE_LOAD_TIME_OUT);
		
		while (tryCount < MAX_TRY_COUNT) {
			try {
				element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
				if (element.isEnabled() && element.isDisplayed()){ 
					return element.getAttribute(attr);
				}
			}
			catch (StaleElementReferenceException e){
				printLog ("** Catch StaleElement Exception : #" + tryCount );
				Thread.sleep(1000);
				tryCount = tryCount + 1;
			}
		}
		fail(locator + " : 해당 엘리먼트가 " + TIME_OUT_SEC + "초내에 노출되지 않음");
		return null;
	}
	
	/**
	 * 특정 URL로 이동할 때 까지 기다리는 메소드
	 * @param pattern 대기할 특정주소를 지정
	 * @return Element 노출 유무
	 * @throws Exception - Selenium Exception
	 */
	public boolean waitForLocation(String pattern) throws Exception {
		
		for (int second = 0; second <= TIME_OUT_SEC; second += WAIT_SEC) {
		
			if (getCurrentUrl().contains(pattern)) {
				return true;
			}

			sleep(WAIT_SEC);
		}

		fail(pattern + " : 해당 URL을 포함하는 페이지가 " + TIME_OUT_SEC + "초내에 노출되지 않음");
		return false;
	}
	
	/**
	 * 특정 Title을 포함한 페이지로 이동할 때 까지 기다리는 메소드
	 * @param pattern 대기할 특정주소를 지정
	 * @return Element 노출 유무
	 * @throws Exception - Selenium Exception
	 */
	public boolean waitForTitle(String Title) throws Exception {
		
		WebDriverWait wait = new WebDriverWait(this, PAGE_LOAD_TIME_OUT);
		
		if (wait.until(ExpectedConditions.titleContains(Title))) {
			return true;
		}
		fail(Title + " : 해당 Title을 포함하는 페이지가 " + TIME_OUT_SEC + "초내에 노출되지 않음");
		return false;
	}
	
	/**
	 * 특정 새창이 뜰때까지 기다리는 메소드
	 * @return 새창 노출 유무
	 * @throws Exception
	 */
	public boolean waitForNewWindow() throws Exception {

		int getWinHandlesSize = 0;
		String tempHandle = null;
		
		mainWindowHandle = getWindowHandle();
		lastWindowHandle = null;
		//printLog ("main window handle : " + mainWindowHandle);
		
		
		for (int second = 0; second <= TIME_OUT_SEC; second += WAIT_SEC) {
			
			Set<String> getHandles = getWindowHandles();
			getWinHandlesSize = getHandles.size();

			//printLog ("window size : " +getWinHandlesSize );
			//printLog("window handles : " + getHandles);
			if (getWinHandlesSize >= 2) {
		    	
				Iterator<String> winHandles = getHandles.iterator();
				
				while (winHandles.hasNext()) {
					
					tempHandle = winHandles.next();

					if (!tempHandle.equals(mainWindowHandle)) {
						lastWindowHandle = tempHandle;
						//printLog ("new window handle : " + lastWindowHandle);
						switchTo().window(lastWindowHandle);
						waitForPageToLoad();
						
						//printLog("<New Window> : wait " + second + " seconds");
						return true;
					}
				}
		
				fail ("failed to read new window handle");
				return false;
				}
			sleep(WAIT_SEC);
			}
		
		fail ("새창이  " + TIME_OUT_SEC + "초내에 로드되지 않음");
		return false;
	}

	
	public void selectMainWindow() throws Exception {
		
		Set<String> getHandles = getWindowHandles();
		Iterator<String> winHandles = getHandles.iterator();
		
		String mainWindow = winHandles.next();
		switchTo().window(mainWindow);
	}
	
	public void selectWindow(String winHandle) throws Exception {
		
		switchTo().window(winHandle);
	}
	
	public void closeWindow(String winHandle) throws Exception {
		
		//Set<String> getHandles = getWindowHandles();
		//Iterator<String> winHandles = getHandles.iterator();
		//String mainWindow = winHandles.next();
		//switchTo().window(mainWindow).close();
		switchTo().window(winHandle).close();
	}
	
	public boolean closeNewWindow() throws Exception {
		
		int beforeCount = 0;
		int afterCount = 0;
		
		beforeCount = getWindowHandles().size();
		
		switchTo().window(lastWindowHandle).close();
		switchTo().window(mainWindowHandle); 
		
		afterCount = getWindowHandles().size();
		
		if (beforeCount - 1 != afterCount) {
			
			fail ("새창이 정상적으로 close 되지 않음.");
			return false;
		}
		return true;
	}
	
	 /**
	  * DragAndDrop을 위한 메소드
	  * @param sourceLocate 드래그 실행할 element
	  * @param tagetLocate 드랍 실행할 element
	  * @return void
	  */
	public void dragAndDrop (By sourceLocate, By tagetLocate) throws Exception {
		
		WebElement source_element = null;
		WebElement target_element = null;
	    Actions action = new Actions (this);
	    
	    source_element = waitForIsElementPresent (sourceLocate);
	    target_element = waitForIsElementPresent (tagetLocate);
	    
	    //WebElement source_element = sourceLocate.findElement((SearchContext) this);
	    //WebElement target_element = tagetLocate.findElement((SearchContext) this);
	 
	    action.dragAndDrop(source_element, target_element).perform();  
	 }
	 
	/**
	 * sleepSec 만큼 sleep
	 * @param sleepSec	초 단위 대기할 시간 (int, double)
	 * @throws Exception - Exception
	 */
	//public void sleep(int sleepSec) throws Exception {
		//Thread.sleep((long)(sleepSec));
	//	Thread.sleep(sleepSec);
	//}
	
	/**
	 * sleepSec 만큼 sleep
	 * @param sleepSec	초 단위 대기할 시간 (int, double)
	 * @throws Exception - Exception
	 */
	
	public void sleep(int sleepSec) throws Exception {
		Thread.sleep((long)sleepSec * 1000);
	}
	
	
	
	public String getDate() {
		
		Calendar cal = Calendar.getInstance();
		//SimpleDateFormat form = new SimpleDateFormat("yyyy.MM.dd-");
		SimpleDateFormat form = new SimpleDateFormat("[yyyy.MM.dd_HH:mm:ss] ");
		return form.format(cal.getTime());	
	}
	
	public String printClassName(Object obj) {
        return (obj.getClass().getName());
    }
	
	public Method[] printMethodName(Object obj) {
		return (obj.getClass().getMethods());
	}


	public void waitForPageToLoad() {
		try {
			//sleep(WAIT_SEC);
			Thread.sleep(500);
			waitForPageLoaded();
			//waitForAjaxLoaded(this);			
			//waitForAjaxLoaded();
		} catch (Exception e) {
			//
		}		
	}
		
	/*
	public void waitForPageToLoad(int loopCount) {
		for (int count = 0; count < loopCount; count ++) {
			waitForPageToLoad();
		}
	}
	*/
		
	public void waitForPageLoaded() { 
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() { 
        	public Boolean apply(WebDriver driver) { 
        		boolean readyState =  ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
        		return readyState;
        	} 
        };
        
        Wait<WebDriver> wait = new WebDriverWait(this, PAGE_LOAD_TIME_OUT).ignoring(StaleElementReferenceException.class);
        wait.until(expectation);
	} 

	/*
	public void waitForAjaxLoaded (WebDriver driver){
	
		//PageFactory.initElements(new AjaxElementLocatorFactory(driver, PAGE_LOAD_TIME_OUT), this);
		ElementLocatorFactory element = new AjaxElementLocatorFactory(driver, PAGE_LOAD_TIME_OUT);
		PageFactory.initElements(element, this);
	}
*/
 
	public void closeAllOpenedBrowser () {
		
		String command = null;
		String browser = getCapabilities().getBrowserName();
		Runtime rt = Runtime.getRuntime();

		if (browser.equals("firefox"))
			command = "taskkill /im firefox.exe /f";
		
		else if (browser.equals("internet explorer"))
			command = "taskkill /im iexplore.exe /f";
		
		else if (browser.equals("chrome"))
			command = "taskkill /im chrome.exe /f";
		
		else
			printLog ("unknown browser type");		
	
		try {
			printLog ("** exec " + command);
			rt.exec(command);
		}
		catch (Exception e) {
			printLog ("** catch Exception");
			e.printStackTrace();
		}
	}

	/*
	 public ExpectedCondition<WebElement> visibilityOfElementLocated(final By by) {
	        return new ExpectedCondition<WebElement>() {
	          public WebElement apply(WebDriver driver) {
	            WebElement element = driver.findElement(by);
	            return element.isDisplayed() ? element : null;
	          }
	        };
	      }
	      
	      public void performSomeAction() {
	        Wait<WebDriver> wait = new WebDriverWait(this, 20);
	        WebElement element = wait.until(visibilityOfElementLocated(By.tagName("a")));
        
	      }
	      */
	
	/*
	public void waitForAjaxLoaded() { 				// ...update
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() { 
        	public Boolean apply(WebDriver driver) { 
        		boolean activeRequestCount = ((JavascriptExecutor)driver).executeScript("return Ajax.activeRequestCount").equals("0");
        		boolean jQueryActive = ((JavascriptExecutor)driver).executeScript("return jQuery.active").equals("0");
        		return (activeRequestCount && jQueryActive);
        		//return activeRequestCount;
        	} 
        };
	        
        Wait<WebDriver> wait = new WebDriverWait(this, PAGE_LOAD_TIME_OUT); 
    	wait.until(expectation);
    	System.out.println("load wait for ajax ... ");
	} 
	*/
	
	
	public void chooseOkOnNextConfirmation() throws Exception {
		  executeJavascript(this, "window.confirm = function(msg) {return true;}");
		 }
		 
	 public void chooseCancelOnNextConfirmation() throws Exception {
		 executeJavascript(this, "window.confirm = function(msg) {return false;}");
		 }
	 
	 public String getAlert(By locator) throws Exception {		 
		 executeJavascript(this, "window.alert = function(msg) {window.lastAlertMessage = msg;}");
		 findElement(locator).click();		
		 return (String) executeJavascript(this, "return window.lastAlertMessage");				 
	 }
	 
	 public String getConfirm(By locator) throws Exception {
		 executeJavascript(this, "window.confirm = function(msg) {window.lastConfirmMessage = msg;}");
		 findElement(locator).click();		 
		 return (String) executeJavascript(this, "return window.lastConfirmMessage");				 
	 }
	 
	 
	 public void scrollToElement (By locator) throws Exception {
		 
		int cordsX = findElement(locator).getLocation().getX();
		int cordsY = findElement(locator).getLocation().getY();				
		executeJavascript (this, "window.scrollBy(" + cordsX + "," + cordsY + ")");
		 
	 }
	 
	 public String waitAndGetAlert(By locator) throws Exception {
		 
		 String alertText = null;
		 waitForIsElementPresent(locator);
		 
		 for (int i = 0 ; i < 10 ; i ++) {
			 alertText = getAlert(locator);
			 
			 if (alertText != null) {
				 return alertText;
				 }
			 Thread.sleep(500);
			 System.out.println ("Catch Alert Text Retry : no - " + (i + 1));
			 }
		 
		 printLog ("alert text를 읽어들이지 못했습니다.");
		 return null;
		 }

	/**
	 * Element가 존재하는지 확인하는 메소드
	 * @param locator 존재 확인 할 Element를 지정
	 * @return locator에 존재하는 WebElement (element, null)
	 */
	public boolean isElementPresentNotExist(By locator) {

		try {
			findElement(locator);
		}
		catch (Exception e){
			return false;
		}
		return true;
	}

}
