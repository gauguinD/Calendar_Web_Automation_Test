package main;

import org.openqa.selenium.By;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.testng.Assert.assertTrue;

public class Modules {
	
	public final String mainURL = "www.naver.com";
	public final String mainTitle = "NAVER";

	public final String meURL = "me.naver.com";
	public final String meTitle = "네이버me";

	public final String mailURL = "mail.naver.com";
	public final String mailTitle = "네이버 메일";

	public final String noteURL = "note.naver.com";
	public final String noteTitle = "받은쪽지함 : 네이버 쪽지";

	public final String memoURL = "memo.naver.com";
	public final String memoTitle = "네이버 메모";

	public final String contactURL = "contact.naver.com";
	public final String contactTitle = "네이버 주소록";

	public final String cloudURL = "photo.cloud.naver.com";
	public final String cloudTitle = "네이버 클라우드";

	public final String moneybookURL = "moneybook.naver.com";
	public final String moneybookTitle = "네이버 가계부";

	public final String officeURL = "office.naver.com";
	public final String officeTitle = "네이버 오피스";

	public final String calURL = "https://calendar.naver.com";
	public final String calTitle = "일정 : 네이버 캘린더";

	public final String taskURL = "https://calendar.naver.com/tasks.nhn";
	public final String taskTitle = "할 일 : 네이버 캘린더";

	public final String startDateURL = "http://www.convertstring.com/ko/EncodeDecode/UrlDecode";

	public final String StartDate = "2016-02-25";
	public final String StartLunaDate = "2016-02-25";
	public final String EndDate = "2016-02-25";
	public final String EndTimeDate = "2016-02-27";
	public String todayDate = null;

	public final String repeatStartDate = null;
	public final String repeatEndDate = null;

	public final String Attendee = "nvqa_4tc040";

	public final String everyDay = "매일";
	public final String everyWeekDay = "매주 월-금";
	public final String everyWeeks = "매주";
	public final String everyMonth = "매월";
	public final String everyYear = "매년";

	public String SubName;
	public String URL;
	public String Title;
	//public String CurrentDate;

	public int maxTaskNum;
	public int taskNum;
	public String taskName;
	public int newTaskNum;
	public String alertText;

	public String tasks[] ={};

	public String a,b,c,d,e,f = null;
	int year,month = 0;

	SimpleDateFormat format1 = new SimpleDateFormat("yyyy.MM.dd");
	SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
	Calendar todayCal = Calendar.getInstance();

	//현재시간 구해서 변수로 설정
	//java의 calendar 클래스 이용
	Calendar cal = Calendar.getInstance();
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM.dd");
	SimpleDateFormat sdf3 = new SimpleDateFormat("yyMMddhhmm");
	String datetime = sdf1.format(cal.getTime());

	//시스템 타이머 이용
	long systemTime = System.currentTimeMillis();
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	String datetime2 = sdf2.format(new Date(systemTime));

	public String contents = "일정 "+ datetime;
	public String subjectKey = sdf3.format(cal.getTime());



	public Modules() {
	}

	public void 로그인(Utilities util, String ID, String Password) throws Exception {

		util.click(By.className("btn_login"));
		util.type(By.id("id"), ID);
		util.type(By.id("pw"), Password);
		util.click(By.className("btn_global"));

		String currentTitle;
		currentTitle = util.getTitle();
		//연락처 입력 화면 노출시 확인 클릭

		if(currentTitle.contains("연락처 정보 업데이트 안내 : 네이버"))
		{
			util.click(By.className("btn_close"));
			util.switchTo().alert().accept();

			util.goTo(calURL);
		}

		//assertNotNull (util.waitForIsElementPresent(By.linkText("로그아웃")));
	}

	public void LogInWorksCal(Utilities util, String ID, String Password) throws Exception {

		util.waitForIsElementPresent(By.xpath("//input[@id='user_id']"));
		util.type(By.xpath("//input[@id='user_id']"),ID);

		util.click(By.xpath("//button[@id='loginStart']"));

		util.waitForIsElementPresent(By.id("inputId"));
		util.type(By.id("password"), Password);
		util.click(By.id("loginBtn"));

		String currentTitle;
		currentTitle = util.getTitle();
		//연락처 입력 화면 노출시 확인 클릭

		if(currentTitle.contains("연락처 정보 업데이트 안내 : 네이버"))
		{
			util.click(By.className("btn_close"));
			util.switchTo().alert().accept();

			util.goTo(calURL);
		}

		//assertNotNull (util.waitForIsElementPresent(By.linkText("로그아웃")));
	}


	public void ReLogInWorksCal(Utilities util, String ID, String Password) throws Exception {

		util.click(By.xpath("//a[@id='clearIdBtn']"));
		util.waitForIsElementPresent(By.xpath("//input[@id='user_id']"));
		util.type(By.xpath("//input[@id='user_id']"),ID);

		util.click(By.xpath("//button[@id='loginStart']"));

		util.waitForIsElementPresent(By.id("password"));
		util.type(By.id("password"), Password);
		util.click(By.id("loginBtn"));

		String currentTitle;
		currentTitle = util.getTitle();
		//연락처 입력 화면 노출시 확인 클릭

		if(currentTitle.contains("연락처 정보 업데이트 안내 : 네이버"))
		{
			util.click(By.className("btn_close"));
			util.switchTo().alert().accept();

			util.goTo(calURL);
		}

		//assertNotNull (util.waitForIsElementPresent(By.linkText("로그아웃")));
	}


	public void LogOutAndLogIn(Utilities util, String ID, String Password) throws Exception {

		util.click(By.className("gnb_name"));
		util.click(By.id("gnb_logout_button"));

		util.waitForIsElementPresent(By.className("btn_inner"));
		util.click(By.className("btn_inner"));

		util.type(By.id("id"), ID);
		util.type(By.id("pw"), Password);
		util.click(By.className("btn_global"));

		String currentTitle;
		currentTitle = util.getTitle();
		//연락처 입력 화면 노출시 확인 클릭

		util.waitForIsNotVisible(By.className("btn_inner"));

		if(currentTitle.contains("연락처 정보 업데이트 안내 : 네이버"))
		{
			util.click(By.className("btn_close"));
			util.switchTo().alert().accept();

			util.goTo(calURL);
		}

		//assertNotNull (util.waitForIsElementPresent(By.linkText("로그아웃")));
	}


	public void assertCalendarPage(Utilities util,String Title, String URL){

		String currentTitle;
		String currentURL;

		currentTitle = util.getTitle();
		currentURL = util.getCurrentUrl();

		util.printLog("[Title] : " + currentTitle);
		util.printLog("[URL] : " + currentURL);

		assertTrue(currentTitle.contains(Title));
		assertTrue(currentURL.contains(URL));

	}

	public void oldCurrentDate(Utilities util) throws Exception {

		//case 1. 이번달이면서 미니 달력이 접혀있을경우 - YY,MM,DD 로 노출 됨
		//caes 2. 이번달이면서 미니 달력이 접혀있지 않을 경우 - YY,MM 로 노출 됨
		//case 3. 이번달이 아니면서 미니달력이 접혀있지 않을 경우 - YY,MM 로 노출 됨
		//case 4. 이번달이 아니면서 미니달력이 접혀있을경우 - 존재하지 않음

		//SimpleDateFormat format1 = new SimpleDateFormat("yyyy.MM.dd");

		if(util.getXpathCount(By.xpath("//div[@class='_title date']/span")) > 8 ) {

			//System.out.println("case 1. 이번달이면서 미니 달력이 접혀있을경우 - YY,MM,DD 로 노출 됨");
			GetDate(util);

			String g = util.waitForIsElementPresent(By.xpath("//div[@class='_title date']/span[9]")).getAttribute("class").substring(4);
			String h = util.waitForIsElementPresent(By.xpath("//div[@class='_title date']/span[10]")).getAttribute("class").substring(4);
			int date = Integer.parseInt(g + h);

			todayCal.set(Calendar.DATE, date);

			String dateFormat = format1.format(todayCal.getTime());

			//System.out.println("현재 달력 날짜는 case 1: " + dateFormat);

		}
		//미니 달력이 안접혀서 년,월만 보일때는 미니 달력에서 오늘 날짜를 가져와서 표시해준다
		else {
			//case 3. 이번달이 아니면서 미니달력이 접혀있지 않을 경우 - YY,MM 로 노출 됨
			if(!util.isElementPresentNotExist(By.xpath("//td[@class='calendar-date calendar-today select_area']")))
			{
				//SimpleDateFormat format2 = new SimpleDateFormat("yyyy.MM.dd");

				GetDate(util);

				String dateFormat1 = format1.format(todayCal.getTime());

				//System.out.println("case 3. 이번달이 아니면서 미니달력이 접혀있지 않을 경우 - YY,MM 로 노출 됨");
				//String dateFormat = format2.format(todayCal.getTime());
				//System.out.println("현재 달력 날짜는 case 3: " + dateFormat1);
			}
			//caes 2. 이번달이면서 미니 달력이 접혀있지 않을 경우 - YY,MM 로 노출 됨
			else {
				//System.out.println("caes 2. 이번달이면서 미니 달력이 접혀있지 않을 경우 - YY,MM 로 노출 됨");

				GetDate(util);

				int date = Integer.parseInt(util.waitForIsElementPresent(By.xpath("//td[@class='calendar-date calendar-today select_area']/a")).getText());
				//System.out.println(date);

				todayCal.set(Calendar.DATE, date);

				String dateFormat = format1.format(todayCal.getTime());

				//System.out.println("CurrentDate 함수에서 가져온 오늘의 날짜 :" + a + b + c + d + "." + e + f);
				//System.out.println("현재 달력 날짜는 case 2: " + dateFormat);
			}

		}
	}

	public String CurrentDate(Utilities util) throws Exception {
		String dateFormat;
		if (util.waitForIsElementPresent(By.xpath("//div[@class='_title date']")).getText().length() > 4) {
			dateFormat = util.waitForIsElementPresent(By.xpath("//div[@class='_title date']")).getText();
			return dateFormat;
		}
		//미니 달력이 안접혀서 년,월만 보일때는 미니 달력에서 오늘 날짜를 가져와서 표시해준다
		else {
			//case 3. 이번달이 아니면서 미니달력이 접혀있지 않을 경우 - YY,MM 로 노출 됨
			if(!util.isElementPresentNotExist(By.xpath("//td[@class='calendar-date calendar-today select_area']")))
			{
				util.click(By.xpath("//button[@class='today']"));

			}
			//caes 2. 이번달이면서 미니 달력이 접혀있지 않을 경우 - YY,MM 로 노출 됨
			GetDate(util);
			int date = Integer.parseInt(util.waitForIsElementPresent(By.xpath("//td[@class='calendar-date calendar-today select_area']/a")).getText());
			//System.out.println(date);
			todayCal.set(Calendar.DATE, date);
			dateFormat = format1.format(todayCal.getTime());

			return dateFormat;
		}
	}


	public String GetDate(Utilities util) throws Exception{


		SimpleDateFormat format1 = new SimpleDateFormat("yyyy.MM");
		todayDate = util.waitForIsElementPresent(By.xpath("//div[@class='_title date']")).getText();

		year = Integer.parseInt(todayDate.substring(1,4));
		System.out.println(year);
		month = Integer.parseInt(todayDate.substring(6,7));
		System.out.println(month);

		todayCal.set(Calendar.YEAR, year);
		todayCal.set(Calendar.MONTH, month-1);

		String dateFormat = format1.format(todayCal.getTime());

		return dateFormat;
	}

	public String GetDateFromURL(Utilities util) throws ParseException {

		String date;
		String URL;


		//URL에서 현재 날짜 부분만 저장
		URL = util.getCurrentUrl();
		date = URL.substring(URL.length()-19);
		//util.printLog(date);
		date = date.substring(0,10);

		Date CurrentDate = format2.parse(date);
		date = format1.format(CurrentDate.getTime());

		return date;
	}

	public String TodayDate(){
		return datetime;
	}

	public String TodayDate2(){
		return datetime2;
	}

	public void ViewType(Utilities util, String option) throws Exception {

		util.click(By.xpath("//button[@class='_user_layer custom_layer on']"));
		util.waitForIsElementPresent(By.xpath("//div[@class='period_edit_layer layer_popup']"));

		util.click(By.xpath(option));
		util.click(By.xpath("//button[@class='_save normal2 btn_emphasis']"));

		util.printLog(util.waitForIsElementPresent(By.xpath("//button[@class='_user custom on']")).getText());
	}

	public void removeBlindText (Utilities util, String xpath)  {

		String prefix = "$result = document.evaluate(\"";
		String suffix = "\",document.documentElement, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null)";
		//System.out.println("js command : " + prefix + xpath + suffix);
		util.executeScript(prefix + xpath + suffix);
		util.executeScript("$result.snapshotItem(0).setAttribute(\"class\",\"\")");

	}


	public void eventColor (Utilities util) throws Exception {
		int NumberOfEvent;
		NumberOfEvent = util.getXpathCount(By.xpath("//ul[@class='category_lst']/li"));

		for(int i=1; i<NumberOfEvent+1; i++)
		{
			util.click(By.xpath("//ul[@class='category_lst']/li["+i+"]"));
		}
		System.out.println("전체 범주의 개수는 :"+NumberOfEvent);
	}

	public void uploadImage (Utilities util) throws Exception {
		//String filePath = "/Users/Naver/Desktop/index.jpg";
		String filePath = "c:/Users/nts/Documents/Calendar/index.jpg";

		//util.click(By.xpath("//*[@id='myfile']"));
		//util.executeScript("document.getElementById('fileName').value='" + filePath + "';");
		//util.executeScript("window.document.getElementById('myfile').setAttribute('value','"+filePath+"');");
		util.findElement(By.id("myfile")).sendKeys(filePath);
	}

	public void uploadFile (Utilities util) throws Exception {
		String filePath = "c:/Users/nts/Documents/Calendar/index.txt";
		//String filePath = "c:/Users/nts/calendar/index.txt";
		//util.click(By.xpath("//*[@id='myfile']"));
		//util.executeScript("document.getElementById('fileName').value='" + filePath + "';");
		//util.executeScript("window.document.getElementById('myfile').setAttribute('value','"+filePath+"');");
		util.findElement(By.id("uploadFile")).sendKeys(filePath);
	}

	public void goBackToCalendar(Utilities util) throws Exception{
		util.goBack();
		if(util.waitForIsNotVisible(By.xpath("//button[@class='_go_task type_schedule todo']"))){
			util.goTo(calURL);
			util.waitForPageLoaded();
		}
		util.waitForTitle(calTitle);

		Title = util.getTitle();
		URL = util.getCurrentUrl();

		assertTrue(Title.contains(calTitle));
		assertTrue(URL.contains(calURL));
	}

	public void goBackToTask(Utilities util) throws Exception{
		util.goBack();
		if(util.waitForIsNotVisible(By.xpath("//a[@class='_svc_lnk pwe_home']")))
			util.goTo(taskURL);
		util.waitForTitle(taskTitle);

		Title = util.getTitle();
		URL = util.getCurrentUrl();

		assertTrue(Title.contains(taskTitle));
		assertTrue(URL.contains(taskURL));
	}


	public void goTask(Utilities util) throws Exception{
		if(util.waitForIsNotVisible(By.xpath("//button[@class='_viewSchedule type_schedule']"))){
			util.click(By.xpath("//button[@class='_go_task type_schedule todo']"));
			util.sleep(3);
		}
			util.waitForTitle(taskTitle);

		Title = util.getTitle();
		URL = util.getCurrentUrl();

		assertTrue(Title.contains(taskTitle));
		assertTrue(URL.contains(taskURL));
	}

	public void ifViewIsNotTask(Utilities util,Modules module) throws Exception{

		Title = util.getTitle();
		URL = util.getCurrentUrl();

		if(Title != module.taskTitle)
		{
			util.goTo(module.taskURL);
			util.waitForPageLoaded();
		}

		Title = util.getTitle();
		URL = util.getCurrentUrl();

		assertTrue(Title.contains(module.taskTitle));
		assertTrue(URL.contains(module.taskURL));
	}

	public void ifNoTask(Utilities util) throws Exception{
		if(util.waitForIsNotVisible(By.xpath("//div[@class='no_list']")))
		{
			taskNum = util.getXpathCount(By.xpath("//div[@class='_list todo_list']/div/ul/li"));
		}
		else{
			taskNum=0;
		}
	}


	public void taskOrder(Utilities util) throws Exception{
		maxTaskNum = util.getXpathCount(By.xpath("//tbody[@class='_private_task_group']/tr"));
		tasks = new String[maxTaskNum];

		for (int i = 1; i <= maxTaskNum; i++) {
			taskName = util.waitForIsElementPresent(By.xpath("//tbody[@class='_private_task_group']/tr[" + i + "]/td")).getText();
			util.printLog(taskName);
			tasks[i-1] = (i+"."+taskName);
		}
	}

	public void deleteTask(Utilities util, String taskId, int newTaskNum) throws Exception {

		//빠른 할일 쓰기 상세보기로 진입
		util.click(By.xpath("//li[@id='"+taskId+"']/div/a[3]"));
		util.waitForIsElementPresent(By.xpath("//div[@class='ly_todo_wrap']"));
		assertTrue(util.waitForIsElementPresent(By.xpath("//div[@class='ly_todo_wrap']")).isDisplayed());

		util.waitForIsElementPresent(By.xpath("//button[@class='_delete btn_delete']"));

		//util.click(By.xpath("//button[@class='_delete btn_delete']"));
		util.getAlert(By.xpath("//button[@class='_delete btn_delete']"));
		util.getAlert().accept();
		util.waitForPageLoaded();

		//할일의 개수를 확인해서 한개 늘어난것 확인
		taskNum = util.getXpathCount(By.xpath("//div[@class='_list todo_list']/div/ul/li"));
		System.out.println(taskNum);
		assertTrue(taskNum == newTaskNum);
	}

	public void deleteAnniv(Utilities util, String subject) throws Exception {

		util.waitForIsElementPresent(By.xpath("//span[contains(text(),'기념일 관리')]"));
		util.click(By.xpath("//span[contains(text(),'기념일 관리')]"));

		if(util.waitForIsNotVisible(By.xpath("//tbody[@class='no_result']"))){
			util.waitForIsElementPresent(By.xpath("//a[contains(@class,'_quick_view') and contains(text(),'"+subject+"')]"));

			util.click(By.xpath("//div[contains(@class,'_memorialday_wrap_table ts scroll') and ./table/tbody/tr/td[4]/a[contains(text(),'"+subject+"')]]/table/tbody/tr/td[7]"));
			alertText = util.getAlert().getText();
			assertTrue(alertText.contains("일정을 삭제하시겠습니까?"));
			util.getAlert().accept();
		}
		else{
			util.printLog("현재 기념일 목록에 기념일이 없습니다.");

		}
	}

	public void checkURL(Utilities util, String url, String title) throws Exception{

		Title = util.getTitle();
		URL = util.getCurrentUrl();

		util.printLog("[Title] : " + Title);
		util.printLog("[URL] : " + URL);

		assertTrue(Title.contains(url));
		assertTrue(URL.contains(title));
	}

}
