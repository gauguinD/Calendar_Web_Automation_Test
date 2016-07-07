package main;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.*;
import org.testng.ITestResult;
import org.testng.Reporter;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.screentaker.ViewportPastingStrategy;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import static org.testng.Assert.fail;
 
public class Utilities extends RemoteWebDriver implements TakesScreenshot { 
	
	//protected final double WAIT_TICK = 0.5; // by second
	protected final int TIME_OUT_SEC = 10; // by second
	protected final int WAIT_SEC = 1;
	//protected final String WAIT_TIME_OUT = "15000";
	private final int PAGE_LOAD_TIME_OUT = 10;
	private final int MAX_TRY_COUNT = 10;
	 
	// RemoteWebDriver Hub Address
	public static String hubAddress = "http://10.12.45.246:4444/wd/hub";
	//public static String hubAddress = "http://localhost:4444/wd/hub";
	 
	public String mainWindowHandle = null;
	public String lastWindowHandle = null;
	
	public String beforeFilePath = null;
	public String beforeFilePath2 = null;
	
	public String type = null;
	
	private long startTime = 0;
	private long endTime = 0;
	private long totalTime = 0;
	
	public Utilities (DesiredCapabilities capability) throws MalformedURLException {
		this(hubAddress, capability);
	}
	
	public Utilities (String url, DesiredCapabilities capability) throws MalformedURLException { 
		super (new URL (url), capability);
	}

	public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {

		  if ((Boolean) getCapabilities().getCapability(CapabilityType.TAKES_SCREENSHOT)) { 
			  
			  return target.convertFromBase64Png(execute(DriverCommand.SCREENSHOT).getValue().toString());
		} 
		  return null;
	}

	public void captureScreen (ITestResult result) throws Exception {
		
		Date date = new Date();
		File directory = new File (".");
		DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd__HH_mm_ss");
		
		File screenshot = getScreenshotAs(OutputType.FILE);
		String FileNamePath = "/ScreenShots/"+ getCapabilities().getBrowserName() + getCapabilities().getVersion() + "/" + dateFormat.format(date)+"_"+ "finish.png";
		String FileNamePath2 = directory.getCanonicalPath()+ FileNamePath;
						
		try {  			
			FileUtils.copyFile(screenshot, new File(FileNamePath2));
			} catch (IOException e1) {
				printLog("After ScreenShot Error : " + FileNamePath2);      
				e1.printStackTrace();
			} catch (Exception e) {
				printLog ("** After ScreenShot catch Excetion");
				//e.printStackTrace();
			}
		//filename is the name of file where screenshot is copied 
		Reporter.setCurrentTestResult(result);
		Reporter.log("<a href=\"" + "..\\ws\\trunk\\CalendarWebAutomation\\target" + beforeFilePath + "\" target=\"_blank\">Start</a>&nbsp;");
		Reporter.log("<a href=\"" + "..\\ws\\trunk\\CalendarWebAutomation\\target" + FileNamePath + "\" target=\"_blank\">Finish</a>");

		//Reporter.log("<a href=\"" + beforeFilePath + "\">Start</a>&nbsp; ");
		//Reporter.log("<a href=\"" + FileNamePath + "\">Finish</a>");
	}
	
	public void captureScreen () throws Exception {
		
		Date date = new Date();
		File directory = new File (".");
		DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd__HH_mm_ss");
				
		File screenshot = getScreenshotAs(OutputType.FILE);
		//String FileNamePath = directory.getCanonicalPath()+ "\\ScreenShots\\"+ getCapabilities().getBrowserName() + getCapabilities().getVersion() + "\\" + dateFormat.format(date)+"_"+ ".png";
		//beforeFilePath = directory.getCanonicalPath()+ "\\ScreenShots\\"+ getCapabilities().getBrowserName() + getCapabilities().getVersion() + "\\" + dateFormat.format(date)+"_"+ ".png";
		beforeFilePath = "/ScreenShots/"+ getCapabilities().getBrowserName() + getCapabilities().getVersion() + "/" + dateFormat.format(date)+"_"+ "start.png";
		beforeFilePath2 = directory.getCanonicalPath()+ beforeFilePath;
								
		try {  			
			FileUtils.copyFile(screenshot, new File(beforeFilePath2));
			} catch (IOException e1) {
				printLog("Before ScreenShot Error : " + beforeFilePath2);
				e1.printStackTrace();
			} catch (Exception e) {
				printLog ("** Before ScreenShot catch Excetion");
				//e.printStackTrace();
			}

		//filename is the name of file where screenshot is copied 
		//Reporter.log("<a href=\"" + FileNamePath + "\">Start Screenshot </a>");
	} 
	
	public void printLocation () throws Exception {
		printLog ("현재 URL : " + getCurrentUrl());
		printLog ("현재 Title : " + getTitle());
	}

	/*
	 * aShot
	 */
	public void captureFullScreen (ITestResult result) throws Exception {
		
		Date date = new Date();
		File directory = new File (".");
		DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		
		String FileNamePath = "/ScreenShots/"+ getCapabilities().getBrowserName() + getCapabilities().getVersion() + "/" + dateFormat.format(date)+"_"+ "finish.png";
		String FileNamePath2 = directory.getCanonicalPath()+ FileNamePath;
						
		aShot (FileNamePath2);
		
		Reporter.setCurrentTestResult(result);
		Reporter.log("<a href=\"" + "..\\ws\\trunk\\CalendarWebAutomation\\target" + beforeFilePath + "\" target=\"_blank\">Start</a>&nbsp;");
		Reporter.log("<a href=\"" + "..\\ws\\trunk\\CalendarWebAutomation\\target" + FileNamePath + "\" target=\"_blank\">Finish</a>");
	}
	
	/* 
	 * aShot
	 */
	public void captureFullScreen () throws Exception {
		
		Date date = new Date();
		File directory = new File (".");
		DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
				
		beforeFilePath = "/ScreenShots/"+ getCapabilities().getBrowserName() + getCapabilities().getVersion() + "/" + dateFormat.format(date)+"_"+ "start.png";
		beforeFilePath2 = directory.getCanonicalPath()+ beforeFilePath;
		
		aShot (beforeFilePath2);
	} 
	
	public void aShot(String path) throws Exception {
		Screenshot screenshot = new AShot().shootingStrategy(
                new ViewportPastingStrategy(1000)).takeScreenshot(this);
		
		ImageIO.write(screenshot.getImage(), "PNG", new File (path));
		
		//final BufferedImage image = screenshot.getImage();
		//new File (path);
		//ImageIO.write(image, "PNG", new File (path));
	}

	/** 
	 * length 크기의 무작위 문자열 생성
	 * @param 문자열 size 
	 */
	public String getRandomString(int length) {

		final String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJLMNOPQRSTUVWXYZ1234567890!@#$%^&*()_+";

		StringBuilder result = new StringBuilder();

		while (length > 0) {
			Random rand = new Random();
			result.append(characters.charAt(rand.nextInt(characters.length())));
			length--;
		}

		return result.toString();
	}

	/** 
	 * 무작위 숫자 생성
	 * @throws Exception 
	 */
	public int getRandomNum() {

		return (int)(Math.random() * 1000 + 1);
	}
	
	public int getRandomNum(int min, int max) {

		//return (int)(Math.random() * max + min);
		return (int)(Math.random() * (max - min)) + min;
	}	
	
	public int getRandomPath (By locator) throws Exception {
	
		int max = this.getXpathCount(locator);
		return (int)(Math.random() * (max - 1)) + 1;
	}
	/** 
	 * url 링크 접속 후 상태 확인
	 * @param url
	 * @throws Exception 
	 */
	public String isLinkBroken(URL url) throws Exception {
		
		this.getCurrentUrl();
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		try {
			connection.connect();
			String response = connection.getResponseMessage();
			connection.disconnect();

			return response;
		}
		catch (Exception exp) {
			return exp.getMessage();
		}
	}
	
	/** 
	 * 모든 링크 검색
	 * @throws Exception 
	 */
	public List<WebElement> findAllLinks() throws Exception {

		List<WebElement> elementList = new ArrayList<WebElement>();
		List<WebElement> finalList = new ArrayList<WebElement>();
		
		elementList = findElements(By.tagName("a"));
		elementList.addAll(findElements(By.tagName("img")));
		for (WebElement element : elementList) {

			String href = element.getAttribute("href");
			if ((href != null) && href.contains("http://")) {
	
				finalList.add(element);
			}
		}
		return finalList;
	}
	
	/** 
	 * 모든 링크 검색
	 * @param 링크를 검색 할 상위 element
	 * @throws Exception 
	 */
	public List<WebElement> findAllLinks(By locator) throws Exception {

		WebElement base = findElement (locator);
		
		List<WebElement> elementList = new ArrayList<WebElement>();
		List<WebElement> finalList = new ArrayList<WebElement>();
		
		elementList = base.findElements(By.tagName("a"));
		elementList.addAll(base.findElements(By.tagName("img")));

		for (WebElement element : elementList) {			
			
			String href = element.getAttribute("href");
			if ((href != null) && href.contains("http://")) {

				finalList.add(element);
			}
		}
		return finalList;
	}
	
	/** 
	 * url 리스트의 정상 여부 확인
	 * @param url list
	 * @throws Exception 
	 */
	public boolean isBrokenLinkExist (List<WebElement> links) throws Exception {
		
		Boolean result = true;
		printLog ("Total Links Count : " + links.size());
		
		for( WebElement element : links) {			 
	    	try {
	    		printLog ("URL: " + element.getAttribute("href")+ " -> " + isLinkBroken(new URL(element.getAttribute("href"))));
	    		//util.printLog("URL: " + element.getAttribute("outerhtml")+ " returned " + isLinkBroken(new URL(element.getAttribute("href"))));
	    	}

	    	catch(Exception exp) {
	    		printLog ("At " + element.getAttribute("innerHTML") + " Exception occured ; " + exp.getMessage());
	    		result = false;
	    	}
		}		
		return result;
	}
	
	/** 
	 * 페이지 전체의 url 링크의 정상 여부 확인
	 * @throws Exception 
	 */
	public boolean isBrokenLinkExist () throws Exception {
	
		Boolean result = true;
		List<WebElement> links = findAllLinks();
		printLog ("Total Links Count : " + links.size());
		
		for( WebElement element : links) {			 
	    	try {
	    		printLog ("URL: " + element.getAttribute("href")+ " -> " + isLinkBroken(new URL(element.getAttribute("href"))));
	    		//util.printLog ("URL: " + element.getAttribute("outerhtml")+ " returned " + isLinkBroken(new URL(element.getAttribute("href"))));
	    	}

	    	catch(Exception exp) {
	    		printLog ("At " + element.getAttribute("innerHTML") + " Exception occured ; " + exp.getMessage());
	    		result = false;
	    	}
		}
		
		return result;
	}
	
	public boolean isBrokenLinkExist (By locator) throws Exception {
		
		Boolean result = true;
		List<WebElement> links = findAllLinks(locator);
		printLog ("Total Links Count : " + links.size());
		
		for( WebElement element : links) {			 
	    	try {
	    		printLog ("URL: " + element.getAttribute("href")+ " -> " + isLinkBroken(new URL(element.getAttribute("href"))));
	    		//util.printLog ("URL: " + element.getAttribute("outerhtml")+ " returned " + isLinkBroken(new URL(element.getAttribute("href"))));
	    	}

	    	catch(Exception exp) {
	    		printLog ("At " + element.getAttribute("innerHTML") + " Exception occured ; " + exp.getMessage());
	    		result = false;
	    	}
		}
		
		return result;
	}
	
	/** 
	 * x box 노출 확인
	 * @param By.TagName ("img")
	 * @throws Exception 
	 */
	public Boolean isImagePresent() throws Exception {
	
		Boolean result = true;
		List<WebElement> elementList = new ArrayList<WebElement>();
		
		elementList = findElements (By.tagName("img"));
		printLog ("All images count : " + elementList.size());
		
		for( WebElement element : elementList) {
			
			if (element.isDisplayed()) {
		    
				try {
		    		Boolean ImagePresent = (Boolean) ((JavascriptExecutor) this).executeScript
					("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",element);
		    		
		    		if (!ImagePresent) {
		    			printLog (element.getAttribute("src") + " --> [Not displayed]");
		    			result = false;	    		
		    		} else {
		    			printLog (element.getAttribute("src") + " --> [Displayed]");
		    		}
		    	}

		    	catch(Exception e) {
		    		printLog (e.getMessage());
		    		result = false;
		    	}
			}
		}		
		return result;
	}
	
	public Boolean isImagePresent(By locator) throws Exception {
		
		Boolean result = true;
		WebElement base = findElement (locator);
		List<WebElement> elementList = new ArrayList<WebElement>();
		
		elementList = base.findElements (By.tagName("img"));
		printLog ("All images count : " + elementList.size());
		
		for( WebElement element : elementList) {
			
			if (element.isDisplayed()) {
		    
				try {
		    		Boolean ImagePresent = (Boolean) ((JavascriptExecutor) this).executeScript
					("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",element);
		    		
		    		if (!ImagePresent) {
		    			printLog (element.getAttribute("src") + " --> [Not displayed]");
		    			result = false;	    		
		    		} else {
		    			printLog (element.getAttribute("src") + " --> [Displayed]");
		    		}
		    	}

		    	catch(Exception e) {
		    		printLog (e.getMessage());
		    		result = false;
		    	}
			}
		}		
		return result;
	}
	
	/** 
	 * 파라미터로 받은 URL로 오픈 후 wait
	 * @param url 주소
	 * @return void
	 */
	public void goTo(String url) {
		navigate().to(url);
		waitForPageToLoad();
	}
	
	/** 
	 * "Back" 실행 후 wait
	 * @param null
	 * @return void
	 */
	public void goBack() {
		navigate().back();
		waitForPageToLoad();
	}
	
	/** 
	 * "Forward" 실행 후 wait
	 * @param null
	 * @return void
	 */
	public void goForward() {
		navigate().forward();
		waitForPageToLoad();
	}
	
	/** 
	 * "Refresh" 실행 후 wait
	 * @param null
	 * @return void
	 */
	public void goRefresh() {
		navigate().refresh();
		waitForPageToLoad();
	}
	
	/**
	  * 브라우저 사이즈를 최대화 시키는 메소드
	  * @return void
	  */
	public void windowMaximize() {
		manage().window().maximize();
	}
	
	public static Object executeJavascript(Utilities util, String script) {
		JavascriptExecutor js = (JavascriptExecutor)util;
		return js.executeScript(script);
	}
	/**
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
		
		WebElement element = waitForIsElementPresent(locator);
		element.sendKeys(value);
	}
	
	/**
	  * context menu를 가져오기 위한 메소드 (right-click)
	  * @param locator	우클릭 실행할 element
	  * @return void
	  */
	public void contextMenu (By locator) throws Exception {
		
		Actions action = new Actions (this);
		Robot robot = new Robot();
		
		WebElement element = waitForIsElementPresent(locator);
		
		robot.mouseMove(Toolkit.getDefaultToolkit().getScreenSize().width/2, Toolkit.getDefaultToolkit().getScreenSize().height/2);
		action.contextClick(element).perform();
	 }
	
	/**
	  * 내용을 입력하는 메소드
	  * @param locator	내용을 입력받을 element 정보
	  * @param inputText locator에 입력할 내용
	  * @return void
	  */
	public void type (By locator, String inputText) throws Exception {
		
		WebElement element = waitForIsElementPresent(locator);
		element.sendKeys(inputText);
	}

	/**
	  * 키보드 key action을 입력하는 메소드
	  * @param locator	내용을 입력받을 element 정보
	  * @param inputText locator에 입력할 key action
	  * @return void
	  */
	public void type (By locator, Keys keyValue) throws Exception {
		
		WebElement element = waitForIsElementPresent(locator);
		element.sendKeys(keyValue);
		
		waitForPageToLoad();
	}
	/**
	  * 해당 위치의 text를 clear 메소드
	  * @param locator element
	  * @return void
	  */
	public void clear (By locator) throws Exception {
		
		WebElement element = waitForIsElementPresent(locator);
		element.clear();
	}

	/**
	  * 해당 위치의 highlight 메소드
	  * @param locator element
	  * @return void
	  */
	public void highlightElement (WebElement element) {
		
		JavascriptExecutor js = (JavascriptExecutor) this;
		//js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: yellow; border: 3px solid yellow;");
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "border: 2px solid yellow;");
		//js.executeScript("arguments[0].setAttribute('style', arguments[1]);",element, "");
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
		}catch (NoSuchElementException e) { 
			return null;
		}
		return result;
	}
	
	/**
	 * Element가 체크되었는지 확인하는 메소드
	 * @param locator 확인 할 Element를 지정
	 * @return locator에 존재하는 WebElement (element, null)
	 * @throws Exception - Selenium Exception
	 */
	public Boolean isChecked(By locator) {
		
		try {		
			WebElement element = locator.findElement((SearchContext) this);
			if (element.isSelected()) {
				return true;
			}

		}catch (NoSuchElementException e) { 
			return false;
		}
		return false;
	}

	/**
	 * Element가 존재할때까지 대기하는 메소드
	 * @param locator 존재 확인 할 Element를 지정
	 * @return locator에 존재하는 WebElement (element, null)
	 * @throws Exception - Selenium Exception
	 */
	public WebElement waitForIsElementPresent(By locator) throws Exception {

		int tryCnt = 0;
		WebDriverWait wait = (WebDriverWait) new WebDriverWait(this,
				PAGE_LOAD_TIME_OUT);

		while (tryCnt < MAX_TRY_COUNT) {
			//System.out.println (locator.toString() + " : " + tryCnt);
			
			try {
				WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));

				if (element != null) {
					//highlightElement(element);
					return element;	
				}
				else {
					printLog ("null element..!!");
					return null;
				}
				
			} catch (StaleElementReferenceException e) {
				printLog("** Catch StaleElement Exception : #" + tryCnt);
				Thread.sleep(1000);
				tryCnt = tryCnt + 1;
				
			} catch (Exception e) {
				printLog(e.getMessage());
				return null;
			}
		}
		fail (locator + " : 해당 엘리먼트가 " + TIME_OUT_SEC
				+ "초내에 노출되지 않음");
		return null;
	} 
	
	/**
	 * Element가 존재할때까지 대기하는 메소드
	 * @param locator 존재 확인 할 Element를 지정
	 * @return locator에 존재하는 WebElement List(element, null)
	 * @throws Exception - Selenium Exception
	 */
	public List<WebElement> waitForIsAllElementsPresent (By locator) throws InterruptedException {
		
		int tryCnt = 0;
		WebDriverWait wait = (WebDriverWait) new WebDriverWait(this, PAGE_LOAD_TIME_OUT);

		while (tryCnt < MAX_TRY_COUNT) {
			try {
				List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
				return elements;

			} catch (StaleElementReferenceException e) {
				printLog("** Catch StaleElement Exception : #" + tryCnt);
				Thread.sleep(1000);
				tryCnt = tryCnt + 1;
				
			} catch (Exception e) {
				printLog (e.getMessage());
				return null;
			}
		}
		fail (locator + " : 해당 엘리먼트가 " + TIME_OUT_SEC + "초내에 노출되지 않음");
		return null;
	}
	
	public void switchToFrame(By frameName) throws Exception {
		
		WebElement element = waitForIsElementPresent(frameName);
		switchTo().frame(element);
	} 
	
	/**
	  * MouseOver을 위한 메소드
	  * @param locator 링크
	  * @throws Exception - Selenium Exception
	  */
	public void mouseOver (By locator) throws Exception {
		
		int target_width = 0;
		int target_height = 0;
		 
	    Actions action = new Actions (this);	    
		WebElement element = waitForIsElementPresent(locator);
	    
	    target_width = (element.getSize().width)/2;
	    target_height = (element.getSize().height)/2;
	    
	    action.moveToElement(element, target_width, target_height).perform();	    
	    waitForPageToLoad();
	 }
	
	/**
	  * MouseOver을 위한 메소드
	  * @param locator 링크
	  * @param locator의 off set 정보 (가로.세로)
	  * @throws Exception - Selenium Exception
	  */
	public void mouseOver (By locator, int offset_X, int offset_Y) throws Exception {
		
	    Actions action = new Actions (this);	    
		WebElement element = waitForIsElementPresent(locator);
	
	    action.moveToElement(element, offset_X, offset_Y).perform();

	    waitForPageToLoad();
	 }
	
	public void selectFile (String filePath) throws Exception {
		
	    StringSelection path = new StringSelection(filePath);
	    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(path, null);

	    Thread.sleep(4000);//Sleep time to detect the window dialog box

	    //Pasting the path in the File name field
	    Robot robot = new Robot();
	    robot.keyPress(KeyEvent.VK_CONTROL);
	    robot.keyPress(KeyEvent.VK_V);
	    robot.keyRelease(KeyEvent.VK_V);
	    robot.keyRelease(KeyEvent.VK_CONTROL);

	    //To click the Open button so as to upload file
	    robot.keyPress(KeyEvent.VK_ENTER);
	    robot.keyRelease(KeyEvent.VK_ENTER);
	}

	/** 
	 * locator 클릭 후 기다리는 메소드
	 * @param locator 링크
	 * @throws Exception 
	 */
	public boolean click(By locator) throws Exception {
	    
		int tryCnt = 0;
		WebElement element = null;
		WebDriverWait wait = (WebDriverWait) new WebDriverWait(this, PAGE_LOAD_TIME_OUT);
		
		while (tryCnt < MAX_TRY_COUNT) {
			try {
				element = wait.until(ExpectedConditions.elementToBeClickable(locator));
				if (element != null && element.isDisplayed() && element.isEnabled()) {
					
					//highlightElement (element);
					element.click();
					waitForPageToLoad();
					
					return true;
				}
			}
			catch (StaleElementReferenceException e){
				printLog ("** Catch StaleElement Exception : #" + tryCnt );
				Thread.sleep(1000);
				tryCnt = tryCnt + 1;
			}
			catch (Exception e) {
				printLog (e.getMessage());
				fail (locator + " : 해당 엘리먼트를 찾지 못함");
				return false;
			}
		}
		fail (locator + " : 해당 엘리먼트가 " + TIME_OUT_SEC + "초내에 노출되지 않음");
		return false;
	}
	
	/** 
	 * locator 클릭 후 기다리는 메소드
	 * @param locator 링크
	 * @throws Exception 
	 */
	public boolean clickAndNoWait(By locator) throws Exception {
	    
		int tryCnt = 0;
		WebElement element = null;
		WebDriverWait wait = (WebDriverWait) new WebDriverWait(this, PAGE_LOAD_TIME_OUT);
		
		while (tryCnt < MAX_TRY_COUNT) {
			try {
				element = wait.until(ExpectedConditions.elementToBeClickable(locator));
				if (element != null && element.isDisplayed() && element.isEnabled()) {
					
					//highlightElement (element);
					element.click();					
					return true;
				}
			}
			catch (StaleElementReferenceException e){
				printLog ("** Catch StaleElement Exception : #" + tryCnt );
				Thread.sleep(1000);
				tryCnt = tryCnt + 1;
			}
			catch (Exception e) {
				printLog (e.getMessage());
				fail (locator + " : 해당 엘리먼트를 찾지 못함");
				return false;
			}
		}
		fail (locator + " : 해당 엘리먼트가 " + TIME_OUT_SEC + "초내에 노출되지 않음");
		return false;
	}
	
	/** 
	 * locator select 후 기다리는 메소드
	 * @param selectLocator 링크
	 * @paran byType 값을 선택할 방법 (byValue, byText, byIndex)
	 * @param optionLocator 선택할 값의 링크
	 * @throws Exception 
	 */
	public boolean select(By selectLocator, String byType, String optionLocator) throws Exception {
		
		boolean result = false;
		WebElement element = waitForIsElementPresent(selectLocator);
		Select select = new Select(element);
		
		try {
			if (byType.equals("text"))
				select.selectByVisibleText(optionLocator);
			
			else if (byType.equals("index"))
				select.selectByIndex(Integer.parseInt(optionLocator));
			
			else if (byType.equals("value"))
				select.selectByValue(optionLocator);
			
			else {
				printLog("unknown option type");
				return false;
			}
			waitForPageToLoad();
			result = true;
		} 
		catch (NoSuchElementException e) {
			result = false;
			//printLog("--- received an exception: " + t);
		}
		return result;
	}
	
	/** 
	 * select element를 기다리는 메소드
	 * @param locator 링크
	 * @throws Exception 
	 */
	public Select select (By locator) throws Exception {
		
		WebElement element = waitForIsElementPresent(locator);	
		Select select = new Select(element);
		
		return select;
	}
	
	/** 
	 * locator submit 후 기다리는 메소드
	 * @param locator 링크
	 * @throws Exception 
	 * @throws Exception - Selenium Exception
	 */
	public void submit(By formLocator) throws Exception {
				
		WebElement element = waitForIsElementPresent(formLocator);
		element.submit();
		waitForPageToLoad();
	}
	
	/**
	 * Alert이 나타날 때까지 대기하는 메쏘드
	 * @return Alert 노출 유무
	 * @throws Exception - Selenium Exception
	 */	
	public Alert getAlert() throws Exception {
		
		WebDriverWait wait = (WebDriverWait) new WebDriverWait(this, PAGE_LOAD_TIME_OUT);
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		printLog ("Alert : " + alert.getText());
		
		return alert;
	}
	
	/**
	 * Click 후 Alert이 나타날 때까지 대기하는 메쏘드
	 * @return Alert 노출 유무
	 * @throws Exception - Selenium Exception
	 */	
	public Alert getAlert(By locator) throws Exception {
		
		WebDriverWait wait = (WebDriverWait) new WebDriverWait(this, PAGE_LOAD_TIME_OUT);

		WebElement element = waitForIsElementPresent (locator);
		element.click();
		
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		printLog ("Alert : " + alert.getText());
		
		return alert;
	}

	/**
	 * Element가 select 될 때까지 기다리는 메소드
	 * @param locator 대기할 Element를 지정
	 * @return Element select 유무
	 * @throws Exception - Selenium Exception
	 */
	public boolean waitForIsSelected (By locator) throws Exception {
		
		WebDriverWait wait = (WebDriverWait) new WebDriverWait(this, PAGE_LOAD_TIME_OUT);
		//WebDriverWait wait = (WebDriverWait) new WebDriverWait(this, PAGE_LOAD_TIME_OUT).ignoring(StaleElementReferenceException.class);

		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			if (wait.until(ExpectedConditions.elementToBeSelected(locator))) { 
				return true;
			}
		}catch (Exception e){
			printLog (e.getMessage());
			return false;
		}
		
		fail (locator + " : 해당 엘리먼트가 " + TIME_OUT_SEC + "초내에 select 되지 않음");
		return false;
	}
	
	/**
	 * Element가 visible 될 때까지 기다리는 메소드
	 * @param locator 대기할 Element를 지정
	 * @return Element visible 유무
	 * @throws Exception - Selenium Exception
	 */
	public boolean waitForIsVisible(By locator) throws Exception {
		
		WebDriverWait wait = (WebDriverWait) new WebDriverWait(this, PAGE_LOAD_TIME_OUT);
		//WebDriverWait wait = (WebDriverWait) new WebDriverWait(this, PAGE_LOAD_TIME_OUT).ignoring(StaleElementReferenceException.class);

		try {
			WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			
			if (element.isDisplayed()) {
				return true;
			}
		}catch (Exception e){
			printLog (e.getMessage());
			return false;
		}
		
		fail (locator + " : 해당 엘리먼트가 " + TIME_OUT_SEC + "초내에 visible 되지 않음");
		return false;
	}
	
	/**
	 * Element가 invisible 될 때까지 기다리는 메소드
	 * @param locator 대기할 Element를 지정
	 * @return Element invisible 유무
	 * @throws Exception - Selenium Exception
	 */
	public boolean waitForIsNotVisible(By locator) throws Exception {
		
		WebDriverWait wait = (WebDriverWait) new WebDriverWait(this, PAGE_LOAD_TIME_OUT);
		//WebDriverWait wait = (WebDriverWait) new WebDriverWait(this, PAGE_LOAD_TIME_OUT).ignoring(StaleElementReferenceException.class);

		try {
			//wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			if (wait.until(ExpectedConditions.invisibilityOfElementLocated(locator))) {
				return true;
			}
		} catch (Exception e){
			printLog (e.getMessage());
			return false;
		}
		
		fail (locator + " : 해당 엘리먼트가 " + TIME_OUT_SEC + "초내에 invisible 되지 않음");
		return false;
	}
	
	/**
	 * searchText 나타날 때까지 기다리는 메소드
	 * @param searchText 나타날때까지 기다릴 Text
	 * @return Text Present 유무
	 * @throws Exception - Selenium Exception
	 */
	public boolean waitForIsTextPresent (String searchText) throws Exception {
		
		if (waitForIsElementPresent(By.xpath("//*[contains(.,'"+searchText+"')]")) != null) {
			return true;
		}

		fail (searchText + " : 해당 Text가 " + TIME_OUT_SEC + "초내에 나타나지 않음");
		return false;
	}
	
	/**
	 * locator가 노출될 때까지 기다렸다 xpath count를 읽어들이는 메소드
	 * @param locator 링크
	 * @return 읽어들인 xpath count (int)
	 * @throws Exception - Selenium Exception
	 */
	public int getXpathCount(By locator) throws Exception {
	
		List<WebElement> elements = waitForIsAllElementsPresent(locator);
		return elements.size();
	}
	
	/**
	 * locator가 노출될 때까지 기다렸다 text를 읽어들이는 메소드
	 * @param locator 링크
	 * @return 읽어들인 text (string)
	 * @throws Exception - Selenium Exception
	 */
	/*
	public String getText(By locator) throws Exception {
		
		WebElement element = waitForIsElementPresent(locator);
		return element.getText();
	}
	*/
	
	public String getText(By locator) throws Exception {
	    
		int tryCnt = 0;
		String text = null;
		WebElement element = null;
		WebDriverWait wait = (WebDriverWait) new WebDriverWait(this, PAGE_LOAD_TIME_OUT);
		
		while (tryCnt < MAX_TRY_COUNT) {
			try {
				element = wait.until(ExpectedConditions.elementToBeClickable(locator));
				if (element != null && element.isDisplayed() && element.isEnabled()) {
					
					//highlightElement (element);
					text = element.getText();
					
					return text;
				}
			}
			catch (StaleElementReferenceException e){
				printLog ("** Catch StaleElement Exception : #" + tryCnt );
				Thread.sleep(1000);
				tryCnt = tryCnt + 1;
			}
			catch (Exception e) {
				printLog (e.getMessage());
				fail (locator + " : 해당 엘리먼트를 찾지 못함");
				return null;
			}
		}
		fail (locator + " : 해당 엘리먼트가 " + TIME_OUT_SEC + "초내에 노출되지 않음");
		return null;
	}
	
	/**
	 * locator가 노출될 때까지 기다렸다 value를 읽어들이는 메소드
	 * @param locator 링크
	 * @return 읽어들인 value (string)
	 * @throws Exception - Selenium Exception
	 */
	public String getValue (By locator) throws Exception {
		
		WebElement element = waitForIsElementPresent(locator);
		return element.getAttribute("value");
	}
	
	/**
	 * locator가 노출될 때까지 기다렸다 attribute를 읽어들이는 메소드
	 * @param locator 링크
	 * @param attr attribute
	 * @return 읽어들인 value (string)
	 * @throws Exception - Selenium Exception
	 */
	public String getAttribute (By locator, String attr) throws Exception {
		
		WebElement element = waitForIsElementPresent(locator);
		return element.getAttribute(attr);
	}
	
	/**
	 * 현재 frame을 return하는 메소드
	 * @return current frame (string)
	 */
	public String getFrame () throws Exception {
		
		JavascriptExecutor jsExecutor = (JavascriptExecutor) this;
		return (String) jsExecutor.executeScript("return self.location.toString()");
	}
	
	/**
	 * 특정 URL로 이동할 때 까지 기다리는 메소드
	 * @param pattern 대기할 특정주소를 지정
	 * @return Element 노출 유무
	 * @throws Exception - Selenium Exception
	 */
	public boolean waitForLocation(String pattern) throws Exception {
		
		String url = null;
		
		for (int second = 0; second <= TIME_OUT_SEC; second += WAIT_SEC) {
		
			url = getCurrentUrl();
			if (url.contains(pattern)) {
				
				printLog ("대상 URL : " + url);
				return true;
			}
			sleep(WAIT_SEC);
		}

		printLog ("대상 URL (fail) : " + url);
		fail (pattern + " : 해당 URL을 포함하는 페이지가 " + TIME_OUT_SEC + "초내에 노출되지 않음");
		return false;
	}
	
	/**
	 * 특정 Title을 포함한 페이지로 이동할 때 까지 기다리는 메소드
	 * @param pattern 대기할 특정주소를 지정
	 * @return Element 노출 유무
	 * @throws Exception - Selenium Exception
	 */
	public boolean waitForTitle(String Title) throws Exception {
		
		String title = null;
		WebDriverWait wait = new WebDriverWait(this, PAGE_LOAD_TIME_OUT);
		
		if (wait.until(ExpectedConditions.titleContains(Title))) {
			title = getTitle();
			printLog ("대상 Title : " + title);
			return true;
		}
		
		title = getTitle();
		printLog ("대상 Title (fail) : " + title);
		fail (Title + " : 해당 Title을 포함하는 페이지가 " + TIME_OUT_SEC + "초내에 노출되지 않음");
		return false;
	}
	
	/**
	 * 특정 새창이 뜰때까지 기다리는 메소드
	 * @throws Exception
	 */
	public boolean waitForNewWindow() throws Exception {

		mainWindowHandle = getWindowHandle();

		for (int second = 0; second <= TIME_OUT_SEC; second += WAIT_SEC) {
		
			Set<String> winHandles = getWindowHandles();
			int winSize = winHandles.size();

			if (winSize >= 2) {

				for (String Handle : winHandles) {

					switchTo().window(Handle);

					if (!getWindowHandle().equals(mainWindowHandle)) {

						lastWindowHandle = getWindowHandle();
						
						waitForPageToLoad();
						Thread.sleep(3000);
						
						printLog ("새창 URL : " + getCurrentUrl());
						printLog ("새창 Title : " + getTitle());
						return true;
					}
				}
			}
			sleep(WAIT_SEC);
		}
		fail("새창이  " + TIME_OUT_SEC + "초내에 로드되지 않음");
		return false;
	}
	
	
	public boolean waitForLastWindow() throws Exception {

		String tempHandle = null;

		for (int second = 0; second <= TIME_OUT_SEC; second += WAIT_SEC) {
			
			Set<String> getHandles = getWindowHandles();
			Iterator<String> winHandles = getHandles.iterator();
			
			int winSize = getHandles.size();
			// System.out.println ("Size : " + winSize);
			
			if (winSize > 2) {
				while (winHandles.hasNext()) {
					tempHandle = winHandles.next();
					switchTo().window(tempHandle);
					// util.printLog("check : " + util.getTitle());
					// util.printLog("check : " + util.getCurrentUrl());
				}
				return true;
			}
		}
		fail("마지막 창이  " + TIME_OUT_SEC + "초내에 로드되지 않음");
		return false;
	}

	/**
	 * 메인윈도우로 포커스 전환 메소드
	 * @throws Exception
	 */
	public void selectMainWindow() throws Exception {
		
		Set<String> getHandles = getWindowHandles();
		Iterator<String> winHandles = getHandles.iterator();
		
		String mainWindow = winHandles.next();
		switchTo().window(mainWindow);
	}
	
	/**
	 * 특정 윈도우로 포커스 전환 메소드
	 * @param 윈도우 핸들어
	 * @throws Exception
	 */
	public void selectWindow(String winHandle) throws Exception {
		
		switchTo().window(winHandle);
	}
	
	/**
	 * 특정 윈도우 close 메소드
	 * @param 윈도우 핸들어
	 * @throws Exception
	 */
	public void closeWindow(String winHandle) throws Exception {
		
		switchTo().window(winHandle).close();
	}
	
	/**
	 * 최근 오픈 윈도우 close 전환 메소드
	 * @throws Exception
	 */
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
	    
	    action.dragAndDrop(source_element, target_element).perform();  
	 }
	 	
	/**
	 * sleepSec 만큼 sleep
	 * @param sleepSec	초 단위 대기할 시간 (int, double)
	 * @throws Exception - Exception
	 */
	public void sleep(int sleepSec) throws Exception {
		Thread.sleep((long)sleepSec * 1000);
	}
	
	/**
	 * 현재 날짜 및 시간 정보 [yyyy.MM.dd_HH:mm:ss]
	 * @throws Exception - Exception
	 */
	public String getDate() {
		
		Calendar cal = Calendar.getInstance();
		//SimpleDateFormat form = new SimpleDateFormat("yyyy.MM.dd-");
		SimpleDateFormat form = new SimpleDateFormat("[yyyy.MM.dd_HH:mm:ss]");
		
		return form.format(cal.getTime());	
	}
	
	public String getTime() {
		
		Calendar cal = Calendar.getInstance();
		//SimpleDateFormat form = new SimpleDateFormat("yyyy.MM.dd-");
		SimpleDateFormat form = new SimpleDateFormat("yyyyMMddHHmmss");
		
		return form.format(cal.getTime());	
	}
	
	/**
	 * class name return 메소드
	 */
	public String printClassName(Object obj) {
        return (obj.getClass().getName());
    }

	/**
	 * method name return 메소드
	 */
	public Method[] printMethodName(Object obj) {
		return (obj.getClass().getMethods());
	}

	/**
	 * 페이지의 load complete 대기
	 * @throws Exception - Exception
	 */
	public void waitForPageToLoad() {
		try {
			startTime = System.currentTimeMillis();
			
			//Thread.sleep(500);
			waitForPageLoaded();
			waitForAjaxLoaded(this);
			
			endTime = System.currentTimeMillis();
			totalTime = endTime - startTime;
			
			if (totalTime > 1000)
				printLog ("### Total Loading Time Over 1000 : " + totalTime + " milliseconds");
			
		} catch (Exception e) {
			printLog (e.getMessage());
		}
	}
		
	/**
	 * 페이지의 load complete 대기
	 * @throws Exception - Exception
	 */
	public void waitForPageLoaded() { 

		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() { 
        	public Boolean apply(WebDriver driver) { 
        		boolean readyState =  ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
        		
        		return readyState;
        	} 
        };
         
        Wait<WebDriver> wait = new WebDriverWait(this, PAGE_LOAD_TIME_OUT).ignoring(StaleElementReferenceException.class);
        //Wait<WebDriver> wait = new WebDriverWait(this, PAGE_LOAD_TIME_OUT).ignoring(StaleElementReferenceException.class).ignoring(UnhandledAlertException.class);
        wait.until(expectation);
	} 
		
	/**
	 * 페이지 내의 ajax load complete 대기
	 * @throws Exception - Exception
	 */
	public void waitForAjaxLoaded (WebDriver driver){
	
		ElementLocatorFactory element = new AjaxElementLocatorFactory(driver, PAGE_LOAD_TIME_OUT);
		PageFactory.initElements(element, this);
	}

	/**
	 * 모든 브라우져 종료
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
		click(locator);
		clear(locator);
		element.sendKeys(inputText);
		//locator.findElement((SearchContext) this).sendKeys(inputText);
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

	/**
	 * Alert이 존재하는지 확인하는 메소드
	 * @param locator 존재 확인 할 Element를 지정
	 * @return locator에 존재하는 WebElement (element, null)
	 */
	public boolean isAlertExist(Utilities util) {

		WebDriverWait wait = new WebDriverWait(this,5);

		if (wait.until(ExpectedConditions.alertIsPresent()) == null){
			System.out.println("alert was not present");
			return false;
		}
		else{
			System.out.println("alert was present");
			return true;
			}

	}


	/**
	 * Alert이 존재하는지 확인하는 메소드
	 * @param locator 존재 확인 할 Element를 지정
	 * @return locator에 존재하는 WebElement (element, null)
	 */
	/*
	public boolean isAlertNotExist(Utilities util) {

		WebDriverWait wait = new WebDriverWait(this,5);

		try{
			wait.until(ExpectedConditions.alertIsPresent());
			System.out.println("alert was present");
			return true;
		}
		catch (Exception e){
			System.out.println("alert was not present");
			return false;
		}

	}
*/

	/**
	 * Element가 존재할때까지 대기하는 메소드
	 * @param locator 존재 확인 할 Element를 지정
	 * @return locator에 존재하는 WebElement (element, null)
	 * @throws Exception - Selenium Exception
	 */
	public boolean waitForIsElementNotPresent(By locator) throws Exception {

		int tryCnt = 0;
		WebDriverWait wait = (WebDriverWait) new WebDriverWait(this,
				PAGE_LOAD_TIME_OUT);
		while (tryCnt < MAX_TRY_COUNT) {
			try {
				WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));

				if (element != null) {
					//highlightElement(element);
					return true;
				}
				else {
					return false;
				}
			} catch (Exception e) {
				printLog(e.getMessage());
				return false;
			}
		}
		return true;
	}

	/**
	 * Alert이 존재하는지 확인하는 메소드
	 * @param  Alert이  존재 하는지 확인
	 * @return Alert의 존재 유무
	 */
	public boolean isAlertPresent(Utilities util)
	{
		try
		{
			util.switchTo().alert();
			return true;
		}   // try
		catch (NoAlertPresentException Ex)
		{
			return false;
		}
	}

	/**
	 * getMouseLocation을 위한 메소드
	 * @param locator 링크
	 * @throws Exception - Selenium Exception
	 */

	public Point getMouseLocation (By locator) throws Exception {

		Locatable elementLocation;
		Point locationPoint;

		Actions action = new Actions (this);
		WebElement element = waitForIsElementPresent(locator);

		elementLocation = (Locatable) element;
		locationPoint = elementLocation.getCoordinates().inViewPort();
		System.out.println(locationPoint);

		return locationPoint;
	}

	/**
	 * clickLocation을 위한 메소드
	 * @param X , Y 좌표
	 * @throws Exception - Selenium Exception
	 */

	public void clickLocation (int x, int y) throws Exception {

		Actions action = new Actions (this);

		action.moveByOffset(x,y);
		action.doubleClick();
		//action.click();

		waitForPageLoaded();
	}

}
