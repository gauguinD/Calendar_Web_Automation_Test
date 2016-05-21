package main;

import org.openqa.selenium.By;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.testng.Assert.assertTrue;

public class Modules {
	
	public final String mainURL = "http://www.naver.com/";
	public final String mainTitle = "NAVER";

	public final String meURL = "http://me.naver.com/";
	public final String meTitle = "네이버me";

	public final String mailURL = "https://mail.naver.com/";
	public final String mailTitle = "네이버 메일";

	public final String noteURL = "http://note.naver.com/";
	public final String noteTitle = "받은쪽지함 : 네이버 쪽지";

	public final String memoURL = "https://memo.naver.com/";
	public final String memoTitle = "네이버 메모";

	public final String contactURL = "https://contact.naver.com/";
	public final String contactTitle = "네이버 주소록";

	public final String cloudURL = "http://photo.cloud.naver.com/";
	public final String cloudTitle = "네이버 클라우드";

	public final String moneybookURL = "http://moneybook.naver.com/";
	public final String moneybookTitle = "네이버 가계부";

	public final String officeURL = "http://office.naver.com/";
	public final String officeTitle = "네이버 오피스";

	public final String calURL = "https://calendar.naver.com/";
	public final String calTitle = "일정 : 네이버 캘린더";

	public final String taskURL = "https://calendar.naver.com/tasks.nhn";
	public final String taskTitle = "할 일 : 네이버 캘린더";

	public final String startDateURL = "http://www.convertstring.com/ko/EncodeDecode/UrlDecode";

	public final String StartDate = "2016-02-25";
	public final String StartLunaDate = "2016-02-25";
	public final String EndDate = "2016-02-25";
	public final String EndTimeDate = "2016-02-27";

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
	String datetime = sdf1.format(cal.getTime());

	//시스템 타이머 이용
	long systemTime = System.currentTimeMillis();
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	String datetime2 = sdf2.format(new Date(systemTime));

	public String contents = "일정 "+ datetime;

	public Modules() {
	}

	public void 로그인(Utilities util, String ID, String Password) throws Exception {

		util.click(By.className("btn_login"));
		util.type(By.id("id"), ID);
		util.type(By.id("pw"), Password);
		util.click(By.className("btn_login"));

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
		util.click(By.className("btn_login"));

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

	public void CurrentDate(Utilities util) throws Exception {

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

	public String GetDate(Utilities util) throws Exception{


		SimpleDateFormat format1 = new SimpleDateFormat("yyyy.MM");

		a = util.waitForIsElementPresent(By.xpath("//div[@class='_title date']/span[1]")).getAttribute("class").substring(4);
		b = util.waitForIsElementPresent(By.xpath("//div[@class='_title date']/span[2]")).getAttribute("class").substring(4);
		c = util.waitForIsElementPresent(By.xpath("//div[@class='_title date']/span[3]")).getAttribute("class").substring(4);
		d = util.waitForIsElementPresent(By.xpath("//div[@class='_title date']/span[4]")).getAttribute("class").substring(4);
		e = util.waitForIsElementPresent(By.xpath("//div[@class='_title date']/span[6]")).getAttribute("class").substring(4);
		f = util.waitForIsElementPresent(By.xpath("//div[@class='_title date']/span[7]")).getAttribute("class").substring(4);

		year = Integer.parseInt(a + b + c + d);
		month = Integer.parseInt(e + f);

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
		util.click(By.xpath("//button[@class='_save normal2']"));

		util.printLog(util.waitForIsElementPresent(By.xpath("//button[@class='_user custom on']")).getText());
	}

	//iterator로 대체
	public String GetCalendarKey(Utilities util, String calName) throws Exception{
		int calNum;
		String calNameTemp;
		String calId = null;

		calNum = util.getXpathCount(By.xpath("//ul[@class='category_list']/li"));

		for(int i=1; i < calNum; i++){
			calNameTemp = util.findElement(By.xpath("//ul[@class='category_list']/li["+i+"]/a[2]")).getAttribute("title");

			if (calNameTemp == calName){
				calId = util.findElement(By.xpath("//ul[@class='category_list']/li["+i+"]")).getAttribute("calendarid");
			}
			else
			{
				System.out.println("찾는 캘린더가 목록에 없습니다 다시 확인해주세요");
			}
				}
		return calId;
	}

	public void DayIndex(String xpathValue, int Date,int dayIndex) throws Exception {
		int tdValue = 0;
		int divValue = 0;
		int tempValue = 0;


		if(Date > dayIndex)
		{
			tempValue = Date-dayIndex;
			dayIndex = tempValue+dayIndex;

			tdValue = (dayIndex / 7)+1;
			divValue = (dayIndex % 7);
		}
		if(Date < dayIndex)
		{
			tempValue = dayIndex-Date;
			dayIndex = tempValue+dayIndex;

			tdValue = (dayIndex / 7)+1;
			divValue = (dayIndex % 7);

		}
		else
		{
			tdValue = (Date / 7)+1;
			divValue = (Date % 7)+1;
		}

		xpathValue = "*[@id='month_frame']/div/div/div[1]/div["+divValue+"]/table[2]/tbody/tr/td["+tdValue+"]";

		//2월 4일
		//*[@id="month_frame"]/div/div/div[1]/div[1]/table[2]/tbody/tr/td[5]

		//2월 25일
		//*[@id="month_frame"]/div/div/div[1]/div[4]/table[2]/tbody/tr[1]/td[5]

		//3월 3일
		//*[@id="month_frame"]/div/div/div[1]/div[5]/table[2]/tbody/tr/td[5]

		//3월 23일
		//*[@id="month_frame"]/div/div/div[1]/div[4]/table[2]/tbody/tr[1]/td[4]
	}

	public String StickerName(int i) throws Exception{
		String StickerName = null;

		switch (i){
			case 1:
				 StickerName = "특정 주제";
				break;
			case 2:
				StickerName = "취미,여가";
				break;
			case 3:
				StickerName = "감정,강조";
				break;
			case 4:
				StickerName = "감정,강조";
				break;
			case 5:
				StickerName = "old_특정주제";
				break;
			case 6:
				StickerName = "old_취미,여가";
				break;
			case 7:
				StickerName = "old_감정,강조";
				break;
		}

		return StickerName;
	}

	public void removeBlindText (Utilities util, String xpath)  {

		String prefix = "$result = document.evaluate(\"";
		String suffix = "\",document.documentElement, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null)";
		//System.out.println("js command : " + prefix + xpath + suffix);
		util.executeScript(prefix + xpath + suffix);
		util.executeScript("$result.snapshotItem(0).setAttribute(\"class\",\"\")");

	}

	public void StickerSelect (Utilities util, Modules module,int i) throws Exception {
		int Sticker = 0;
		Sticker = util.getXpathCount(By.xpath("//div[@class='sticker_category']/ul["+i+"]/li"));

		System.out.println(Sticker);

		//큰스티커 카테고리 1~7
		//스티커 1 502~769
		//스티커 2 503~763
		//스티커 3 527~920
		//스티커 4 921~970
		//스티커 5 971~1053
		//스티커 6 1054~1165
		//스티커 7 1166~1196

		//작은스티커 카테고리1~3
		//스티커 1 2~269
		//스티커 2 3~263
		//스티커 3 27~112

		for(int j=1; j<Sticker+1; j++)
		{
			int MaxSticker =0;
			String NameOfStickerSet = null;

			module.removeBlindText(util,"//div[@class='sticker_category']/ul["+i+"]/li["+j+"]/button/span");
			NameOfStickerSet = util.findElement(By.xpath("//div[@class='sticker_category']/ul["+i+"]/li["+j+"]/button/span")).getText();
			System.out.println("현재 스티커 세트는 : "+NameOfStickerSet);

			util.click(By.xpath("//div[@class='sticker_category']/ul["+i+"]/li["+j+"]/button"));

			MaxSticker = util.getXpathCount(By.xpath("//div[@class='sticker_category']/ul["+i+"]/li"));
			System.out.println("전체 스티커의 개수는 :"+MaxSticker);
			/*
            //개별 스티커 클릭
            for(int j=1; j < MaxSticker+1; j++)
            {
                util.click(By.xpath("//ul[@class='_big_sticker_list']/li["+j+"]"));

                ///html/body/div[6]/div/div/div[1]/div[3]/ul/li[1]

                //util.click(By.xpath("li[@class='_sticker'][key='"+j+"']"));
                util.scrollToElement(By.xpath("//ul[@class='_big_sticker_list']/li["+j+"]"));
            }
            */
			System.out.println(NameOfStickerSet+" 스티커 세트는 정상입니다.");
		}

		util.click(By.xpath("//button[@class='normal normal_v1 _save']"));
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
		String filePath = "/Users/Naver/Desktop/index.jpg";

		//util.click(By.xpath("//*[@id='myfile']"));
		//util.executeScript("document.getElementById('fileName').value='" + filePath + "';");
		//util.executeScript("window.document.getElementById('myfile').setAttribute('value','"+filePath+"');");
		util.findElement(By.id("myfile")).sendKeys(filePath);
	}

	public void uploadFile (Utilities util) throws Exception {
		String filePath = "/Users/Naver/Desktop/index.txt";

		//util.click(By.xpath("//*[@id='myfile']"));
		//util.executeScript("document.getElementById('fileName').value='" + filePath + "';");
		//util.executeScript("window.document.getElementById('myfile').setAttribute('value','"+filePath+"');");
		util.findElement(By.id("uploadFile")).sendKeys(filePath);
	}

	public void goBackToCalendar(Utilities util) throws Exception{
		util.goBack();
		if(util.waitForIsNotVisible(By.xpath("//a[@class='_svc_lnk pwe_home']")))
			util.goTo(calURL);
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
		util.click(By.xpath("//button[@class='_delete btn_delete']"));

		util.getAlert().accept();
		util.waitForPageLoaded();

		//할일의 개수를 확인해서 한개 늘어난것 확인
		taskNum = util.getXpathCount(By.xpath("//div[@class='_list todo_list']/div/ul/li"));
		System.out.println(taskNum);
		assertTrue(taskNum == newTaskNum);
	}

}
