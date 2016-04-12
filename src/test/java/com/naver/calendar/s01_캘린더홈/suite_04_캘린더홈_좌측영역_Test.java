package com.naver.calendar.s01_캘린더홈;

import main.TestIds;
import main.Testcase;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;


public class suite_04_캘린더홈_좌측영역_Test extends Testcase {

    public String Title = null;
    public String URL = null;
    public String Subname = null;
    public String dataValue = null;

    String privateCalendarValue = null;
    String publicCalendarValue = null;
    String openCalendarValue = null;
    String timeTableValue = null;


    /*
    * Step : 로그인 > 해당 계정으로 로그인
    * Result : 해당하는 계정으로 로그인 됨
    */
    @Test
    public void TC_00_좌측영역_로그인_Test() throws Exception {
        module.로그인(util, TestIds.CalUser.getId(), TestIds.CalUser.getPw());
    }

     /*
     * Step : 좌측영역 > 일정,약속쓰기 클릭
     * Result : 오늘 날짜, 현재 시간으로 일정쓰기창으로 이동
     */
    @Test
    public void TC_01_좌측영역_일정약속쓰기_Test() throws Exception {

        util.click(By.xpath("//span[contains(text(),'약속쓰기')]"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//a[@class='_back btn_back_calender']")).isDisplayed());

        util.click(By.xpath("//a[@class='_back btn_back_calender']"));

    }

    /*
    * Step : 좌측영역 > 기념일 관리 클릭
    * Result : 기념일 관리 페이지로 이동
    */
    @Test
    public void TC_02_좌측영역_기념일관리_Test() throws Exception{
        util.click(By.xpath("//span[contains(text(),'기념일 관리')]"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//a[@class='_btn_back_calender btn_back_calender']")).isDisplayed());

        util.click(By.xpath("//a[@class='_btn_back_calender btn_back_calender']"));
    }

    /*
    * Step : 좌측영역 > 기념일 관리 클릭
    * Result : 기념일 관리 페이지로 이동
    */
    @Test
    public void TC_03_좌측영역_날짜영역_Test() throws Exception{

        String miniCalendar;
        miniCalendar = util.isElementPresent(By.xpath("//div[@id='calendar_list_container']")).getAttribute("style");
        //접혀있을때 97px 안접혀있을때 233px
        module.CurrentDate(util);

        //미니 달력 펼치기 버튼 클릭
        util.click(By.xpath("//button[@class='_fold btn_fold']"));
        module.CurrentDate(util);
        util.click(By.xpath("//button[@class='_fold btn_fold']"));

        if(miniCalendar.contains("top: 97px; left: 0px;"))
        {
            util.printLog(miniCalendar);
            util.click(By.xpath("//button[@class='_fold btn_fold']"));
            module.CurrentDate(util);
        }


        //날짜 <,> 버튼 동작 확인
        //이전 달 버튼 클릭
        util.click(By.xpath("//a[@class='btn_prev rollover calendar-btn-prev-mon']"));
        module.CurrentDate(util);

        //다음 달 버튼 클릭
        util.click(By.xpath("//a[@class='btn_next rollover calendar-btn-next-mon']"));
        util.click(By.xpath("//a[@class='btn_next rollover calendar-btn-next-mon']"));
        module.CurrentDate(util);
    }

    /*
    * Step : 좌측영역 > 1년보기 클릭
    * Result : 1년보기 페이지로 이동
    */
    @Test
    public void TC_04_좌측영역_1년보기_Test() throws Exception{
        // 1년 버튼 동작 확인
        util.click(By.className("btn_1year"));
        util.waitForIsElementPresent(By.xpath("//div[@class='sub_tit']/h3"));
    }


    /*
    * Step : 좌측영역 > 미니달력 클릭
    * Result : 미니 달력에서 클릭한 날짜로 이동 됨
    */
    @Test
    public void TC_05_좌측영역_미니달력_Test() throws Exception{
        // 1년 버튼 동작 확인
        util.click(By.className("btn_1year"));
        util.waitForIsElementPresent(By.xpath("//div[@class='sub_tit']/h3"));
    }


    /*
    * Step : 좌측영역 > 전체일정보기 클릭
    * * Result : 전체 일정 노출 됨
    */
    @Test
    public void TC_06_좌측영역_전체일정보기_Test() throws Exception{

        String viewType;
        util.click(By.xpath("//div[@class='_all all_view select']"));
        viewType = util.waitForIsElementPresent(By.xpath("//div[@class='view_schedule']")).getAttribute("style");
        assertTrue(viewType.contains("display: block;"));
        //util.waitForIsElementPresent(By.xpath("//div[@class='_all all_view select']"));

    }

    /*
    * Step : 좌측영역 > 내캘린더 클릭
    * Result : 내캘린더에 해당하는 일정만 노출 됨
    *
    */
    @Test
    public void TC_07_좌측영역_내캘린더_Test() throws Exception{

        String calendarName;
        util.click(By.xpath("//li/a[2]/span[@class='cal_type' and contains(text(),'[기본]')]"));

        calendarName = util.isElementPresent(By.xpath("//li[@calendarid='6819065']")).getAttribute("class");
        //util.printLog(calendarName);
        assertTrue(calendarName.contains("select"));

    }


    /*
    * Step : 좌측영역 > 내캘린더 클릭
    * Result : 내캘린더에 해당하는 일정만 노출 됨
    *
    */
    @Test
    public void TC_08_좌측영역_캘린더만들기_Test() throws Exception{
        util.click(By.className("btn_makecal"));

        for(int i=1; i<util.getXpathCount(By.className("action_list")); i++)
        {
            util.printLog(util.isElementPresent(By.xpath("//ul[@class='action_list']/li["+i+"]/a")).getText());
        }
    }

    /*
    * Step : 좌측영역 > +버튼 클릭 > 내 캘린더 만들기
    * Result : 내 캘린더 생성 됨
    */

    @Test
    public void TC_08_좌측영역_내캘린더만들기_Test() throws Exception{

        util.click(By.className("btn_makecal"));
        util.click(By.partialLinkText("내 캘린더 만들기"));

        util.clearAndType(By.id("$$_calendar_name"),"내 캘린더");
        Subname = util.isElementPresent(By.id("$$_calendar_name")).getAttribute("value");
        util.printLog(Subname);

        util.click(By.xpath("//button[@class ='_save normal']"));

        //캘린더 목록의 캘린더 갯수 가져오기
        int NumberOfCalender = util.getXpathCount(By.className("cal_name"));
        util.printLog("캘린더 목록의 갯수 가져오기 :" + NumberOfCalender);

        //생성한 캘린더의 이름과 dataValue를 가져오기
        //dataValue = util.isElementPresent(By.xpath("//a[contains(text(),'"+Subname+"')]")).getAttribute("data-value").toString();
        //util.printLog("dataValue 값 입니다"+dataValue);

        //dataValue = util.isElementPresent(By.xpath("//a[@class='_calendar_name' and contains(text(),'"+Subname+"')]")).getAttribute("data-value");
        //util.printLog("dataValue 값 입니다"+dataValue);


        //생성된 캘린더가 있는지 캘린더 목록에서 확인한다
        for(int i=1; i <= NumberOfCalender; i++)
        {
            String SubTemp;
            SubTemp = util.waitForIsElementPresent(By.xpath("//*[@id='calendar_list_container']/div[2]/ul/li[" + i + "]/a[2]")).getAttribute("title");

            if(SubTemp.contentEquals(Subname))
            {
                util.printLog("True");
                privateCalendarValue = util.isElementPresent(By.xpath("//*[@id='calendar_list_container']/div[2]/ul/li["+i+"]")).getAttribute("calendarid");
                util.printLog(privateCalendarValue);
            }
            else
            {
                //util.printLog("Fail");
            }
            //*[@id="calendar_list_container"]/div[2]/ul/li[1]
            //*[@id="calendar_list_container"]/div[2]/ul/li[5]
        }
    }


    /*
    * Step : 좌측영역 > + 버튼 클릭 > 공유 캘린더 만들기
    * Result : 공유 캘린더 생성 됨
    */

    @Test
    public void TC_09_좌측영역_공유캘린더만들기_Test() throws Exception{

        util.isElementPresent(By.className("btn_makecal"));
        //util.click(By.className("btn_makecal"));
        util.click(By.xpath("//li[@class ='_share']"));

        util.waitForIsElementPresent(By.id("$$_calendar_name"));
        util.clearAndType(By.id("$$_calendar_name"),"공유캘린더");
        Subname = util.waitForIsElementPresent(By.id("$$_calendar_name")).getAttribute("value");
        util.printLog(Subname);

        util.click(By.xpath("//button[@class ='_save normal']"));

        //캘린더 목록의 캘린더 갯수 가져오기
        int NumberOfCalender = util.getXpathCount(By.className("cal_name"));
        util.printLog("캘린더 목록의 갯수 가져오기 :" + NumberOfCalender);

        for(int i=1; i <= NumberOfCalender; i++)
        {
            String SubTemp;
            SubTemp = util.isElementPresent(By.xpath("//*[@id='calendar_list_container']/div[2]/ul/li[" + i + "]/a[2]")).getAttribute("title");

            if(SubTemp.contentEquals(Subname))
            {
                util.printLog("True");
                publicCalendarValue = util.isElementPresent(By.xpath("//*[@id='calendar_list_container']/div[2]/ul/li["+i+"]")).getAttribute("calendarid");
                util.printLog(publicCalendarValue);
            }
            else {
                util.printLog("Fail");
            }
        }
    }

        /*
    * Step : 좌측영역 > +버튼 클릭 > 구독캘린더 만들기
    * Result : 구독캘린더 생성 됨
    */

    @Test
    public void TC_10_좌측영역_구독캘린더만들기_Test() throws Exception{
        util.click(By.className("btn_makecal"));
        util.click(By.xpath("//li[@class ='_subscribe']"));


        util.waitForNewWindow();

        Title = util.getTitle();
        URL = util.getCurrentUrl();
        assertTrue(URL.contains("https://calendar.naver.com/subscribePage.nhn"));
        assertTrue(Title.contains("네이버 인기 공개 캘린더"));

        util.close();
        util.selectMainWindow();

        util.waitForTitle("일정 : 네이버 캘린더");

        /*
        Subname = util.isElementPresent(By.id("$$_calendar_name")).getAttribute("value");
        util.type(By.id("$$_calendar_name"),"구독캘린더");
        util.printLog(Subname);

        util.click(By.xpath("//button[@class ='_save normal']"));

        //캘린더 목록의 캘린더 갯수 가져오기
        int NumberOfCalender = util.getXpathCount(By.className("cal_name"));
        System.out.print("캘린더 목록의 갯수 가져오기 :" + NumberOfCalender);

        //생성된 캘린더가 있는지 캘린더 목록에서 확인한다
        for(int i=1; i <= NumberOfCalender; i++)
        {
            String SubTemp;
            SubTemp = util.isElementPresent(By.xpath("//*[@id='calendar_list_container']/div[2]/ul/li[" + i + "]/a[2]")).getAttribute("title");

            if(SubTemp.contentEquals(Subname))
            {
                util.printLog("True");
                openCalendarValue = util.isElementPresent(By.xpath("//*[@id='calendar_list_container']/div[2]/ul/li["+i+"]")).getAttribute("calendarid");
                util.printLog(openCalendarValue);
            }
            else
            {
                util.printLog("Fail");
            }
        }
        */
    }

    /*
    * Step : 좌측영역 > +버튼 클릭 > 시간표만들기
    * Result : 시간표 생성 됨
    */

    @Test
    public void TC_11_좌측영역_시간표만들기_Test() throws Exception{
        util.click(By.className("btn_makecal"));
        util.click(By.xpath("//li[@class='_timetable']"));

        util.type(By.xpath("//input[contains(@class,'_calendar_name calender_name calendar_color')]"),"내시간표");
        Subname = util.isElementPresent(By.xpath("//input[contains(@class,'_calendar_name calender_name calendar_color')]")).getAttribute("value");
        util.printLog(Subname);

        util.click(By.xpath("//button[@class ='_save normal']"));

        //캘린더 목록의 캘린더 갯수 가져오기
        int NumberOfCalender = util.getXpathCount(By.className("cal_name"));
        System.out.print("캘린더 목록의 갯수 가져오기 :" + NumberOfCalender);

        //생성된 캘린더가 있는지 캘린더 목록에서 확인한다
        for(int i=1; i <= NumberOfCalender; i++)
        {
            String SubTemp;
            SubTemp = util.isElementPresent(By.xpath("//*[@id='calendar_list_container']/div[2]/ul/li[" + i + "]/a[2]")).getAttribute("title");

            if(SubTemp.contentEquals(Subname))
            {
                util.printLog("True");
                timeTableValue = util.isElementPresent(By.xpath("//*[@id='calendar_list_container']/div[2]/ul/li["+i+"]")).getAttribute("calendarid");
                util.printLog(timeTableValue);
            }
            else
            {
                util.printLog("Fail");
            }
        }
    }



    //@Test
    public void TC_09_좌측영역_캘린더삭제_Test() throws Exception{
        util.click(By.className("btn_settingcal"));
        util.printLog(util.isElementPresent(By.xpath("//div[@class='_calendar_name' and contains(data-value, '"+dataValue+"']")).toString());

    }

}
