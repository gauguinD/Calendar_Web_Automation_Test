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

    int maxCalNum;

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
        util.sleep(5);
        util.waitForIsElementPresent(By.xpath("//a[@class='_back btn_back_calender']"));

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
    * Step : 좌측영역 > 날짜영역 클릭
    * Result : 해당 날짜로 일정쓰기 노출 됨
    *
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
        util.waitForIsElementPresent(By.xpath("//li/a[2]/span[@class='cal_type' and contains(text(),'[기본]')]"));
        util.click(By.xpath("//li/a[2]/span[@class='cal_type' and contains(text(),'[기본]')]"));
        calendarName = util.isElementPresent(By.xpath("//li[@calendarid='6819065']")).getAttribute("class");
        assertTrue(calendarName.contains("select"));

    }


    /*
    * Step : 좌측영역 > +버튼 클릭 > 내 캘린더 만들기
    * Result : 내 캘린더 생성 됨
    */

    @Test
    public void TC_08_좌측영역_내캘린더만들기_Test() throws Exception{

        util.waitForIsElementPresent(By.xpath("//a[@class='btn_makecal']"));
        util.mouseOver(By.xpath("//a[@class='btn_makecal']"));
        util.click(By.xpath("//a[@class='btn_makecal']"));
        util.click(By.partialLinkText("내 캘린더 만들기"));

        //til.clearAndType(By.xpath("//tr[@class='_calendar_info_area']/td/input"),"좌측영역_내캘린더만들기");
        util.clear(By.xpath("//tr[@class='_calendar_info_area']/td/input"));
        util.type(By.xpath("//tr[@class='_calendar_info_area']/td/input"),"좌측영역_내캘린더만들기");
        Subname = util.waitForIsElementPresent(By.xpath("//tr[@class='_calendar_info_area']/td/input")).getAttribute("value");
        util.printLog(Subname);

        util.click(By.xpath("//button[@class ='_save normal']"));
        util.waitForPageLoaded();


        //환경설정 이동
        util.click(By.className("_config"));
        util.waitForIsElementPresent(By.linkText("캘린더로 돌아가기"));

        //환경설정 > 일정설정 이동
        util.click(By.xpath("//ul[@class='tab_setting tabs']/li[2]"));
        util.waitForIsElementPresent(By.xpath("//div[@class='_schedule tc-panel tc-selected']"));

        //캘린더 목록의 캘린더 갯수 가져오기
        maxCalNum = util.getXpathCount(By.xpath("//tbody[@class='_private_calendar_list']/tr"));
        util.printLog("캘린더 목록의 갯수 가져오기 :" + maxCalNum);

        //생성된 캘린더가 있는지 캘린더 목록에서 확인한다
        for(int i=1; i < maxCalNum+1; i++)
        {
            System.out.println(i);
            String SubTemp;
            SubTemp = util.waitForIsElementPresent(By.xpath("//tbody[@class='_private_calendar_list']/tr["+i+"]/td/a/strong")).getText();

            if(SubTemp.contentEquals(Subname))
            {
                //util.printLog("True");
                privateCalendarValue = util.waitForIsElementPresent(By.xpath("//tbody[@class='_private_calendar_list']/tr["+i+"]")).getAttribute("data-value");
                util.printLog(privateCalendarValue);
            }
            else
            {
                //util.printLog("Fail");
            }
        }
    }


    /*
    * Step : 일정설정 > 캘린더 삭제 클릭
    * Result : 해당 캘린더 목록에서 노출 안됨
    */

    @Test
    public void TC_09_좌측영역_내캘린더만들기_삭제_Test() throws Exception {
       util.click(By.className("_config"));
        util.waitForIsElementPresent(By.linkText("캘린더로 돌아가기"));

        util.click(By.xpath("//ul[@class='tab_setting tabs']/li[2]"));
        util.waitForIsElementPresent(By.xpath("//div[@class='_schedule tc-panel tc-selected']"));

        util.waitForIsElementPresent(By.xpath("//tr[contains(@data-value,'"+privateCalendarValue+"')]/td[5]/div/a"));
        util.click(By.xpath("//tr[contains(@data-value,'"+privateCalendarValue+"')]/td[5]/div/a"));

        util.getAlert().accept();
        util.waitForPageLoaded();
        util.click(By.xpath("//button[@class='_save normal']"));

        util.getAlert().accept();
        util.waitForPageLoaded();

    }


    /*
    * Step : 좌측영역 > + 버튼 클릭 > 공유 캘린더 만들기
    * Result : 공유 캘린더 생성 됨
    */
    @Test
    public void TC_10_좌측영역_공유캘린더만들기_Test() throws Exception{

        util.waitForIsElementPresent(By.xpath("//a[@class='btn_makecal']"));
        util.mouseOver(By.xpath("//a[@class='btn_makecal']"));
        util.click(By.xpath("//a[@class='btn_makecal']"));
        util.click(By.xpath("//li[@class ='_share']"));

        util.waitForIsElementPresent(By.xpath("//tr[@class='_calendar_info_area']/td/input"));
        //util.clearAndType(By.xpath("//tr[@class='_calendar_info_area']/td/input"),"좌측영역_공유캘린더만들기");
        util.click(By.xpath("//tr[@class='_calendar_info_area']/td/input"));
        util.clear(By.xpath("//tr[@class='_calendar_info_area']/td/input"));
        util.type(By.xpath("//tr[@class='_calendar_info_area']/td/input"),"좌측영역_공유캘린더만들기");
        Subname = util.waitForIsElementPresent(By.xpath("//tr[@class='_calendar_info_area']/td/input")).getAttribute("value");
        util.printLog(Subname);

        util.click(By.xpath("//button[@class ='_save normal']"));

        //환경설정 이동
        util.click(By.className("_config"));
        util.waitForIsElementPresent(By.linkText("캘린더로 돌아가기"));

        //환경설정 > 일정설정 이동
        util.click(By.xpath("//ul[@class='tab_setting tabs']/li[2]"));
        util.waitForIsElementPresent(By.xpath("//div[@class='_schedule tc-panel tc-selected']"));

        //캘린더 목록의 캘린더 갯수 가져오기
        maxCalNum = util.getXpathCount(By.xpath("//tbody[@class='_private_calendar_list']/tr"));
        util.printLog("캘린더 목록의 갯수 가져오기 :" + maxCalNum);

        for(int i=1; i <= maxCalNum; i++)
        {
            String SubTemp;
            SubTemp = util.waitForIsElementPresent(By.xpath("//tbody[@class='_private_calendar_list']/tr["+i+"]/td/a/strong")).getText();

            if(SubTemp.contentEquals(Subname))
            {
                util.printLog("True");
                publicCalendarValue = util.waitForIsElementPresent(By.xpath("//tbody[@class='_private_calendar_list']/tr["+i+"]")).getAttribute("data-value");
                util.printLog(publicCalendarValue);
            }
            else {
                util.printLog("Fail");
            }
        }
    }


    /*
    * Step : 일정설정 > 캘린더 삭제 클릭
    * Result : 해당 캘린더 목록에서 노출 안됨
    */

    @Test
    public void TC_11_좌측영역_공유캘린더만들기_삭제_Test() throws Exception {

        util.click(By.className("_config"));
        util.waitForIsElementPresent(By.linkText("캘린더로 돌아가기"));

        util.click(By.xpath("//ul[@class='tab_setting tabs']/li[2]"));
        util.waitForIsElementPresent(By.xpath("//div[@class='_schedule tc-panel tc-selected']"));

        util.waitForIsElementPresent(By.xpath("//tr[contains(@data-value,'" + publicCalendarValue + "')]/td[5]/div/a[3]"));
        util.click(By.xpath("//tr[contains(@data-value,'" + publicCalendarValue + "')]/td[5]/div/a[3]"));

        util.getAlert().accept();
        util.waitForPageLoaded();

        util.getAlert().accept();
        util.waitForPageLoaded();
        util.click(By.xpath("//button[@class='_save normal']"));

        util.getAlert().accept();
        util.waitForPageLoaded();
    }


    /*
    * Step : 좌측영역 > +버튼 클릭 > 구독캘린더 만들기
    * Result : 구독캘린더 생성 됨
    */

    @Test
    public void TC_12_좌측영역_구독캘린더만들기_Test() throws Exception{

        util.waitForIsElementPresent(By.xpath("//a[@class='btn_makecal']"));
        util.mouseOver(By.xpath("//a[@class='btn_makecal']"));
        util.click(By.xpath("//a[@class='btn_makecal']"));
        util.click(By.xpath("//li[@class ='_subscribe']"));


        util.waitForNewWindow();

        Title = util.getTitle();
        URL = util.getCurrentUrl();
        assertTrue(URL.contains("https://calendar.naver.com/subscribePage.nhn"));
        assertTrue(Title.contains("네이버 인기 공개 캘린더"));

        util.close();
        util.selectMainWindow();

        util.waitForTitle("일정 : 네이버 캘린더");
    }


    /*
    * Step : 좌측영역 > +버튼 클릭 > 시간표만들기
    * Result : 시간표 생성 됨
    */

    @Test
    public void TC_13_좌측영역_시간표만들기_Test() throws Exception{

        util.waitForIsElementPresent(By.xpath("//a[@class='btn_makecal']"));
        util.mouseOver(By.xpath("//a[@class='btn_makecal']"));
        util.click(By.xpath("//a[@class='btn_makecal']"));
        util.click(By.xpath("//li[@class='_timetable']"));

        util.waitForIsElementPresent(By.xpath("//input[contains(@class,'_calendar_name calender_name calendar_color')]"));
        util.click(By.xpath("//input[contains(@class,'_calendar_name calender_name calendar_color')]"));
        util.clear(By.xpath("//input[contains(@class,'_calendar_name calender_name calendar_color')]"));
        util.type(By.xpath("//input[contains(@class,'_calendar_name calender_name calendar_color')]"),"좌측영역_시간표만들기");

        //util.type(By.xpath("//input[contains(@class,'_calendar_name calender_name calendar_color')]"),"내시간표");
        Subname = util.waitForIsElementPresent(By.xpath("//input[contains(@class,'_calendar_name calender_name calendar_color')]")).getAttribute("value");
        util.printLog(Subname);

        util.click(By.xpath("//button[@class ='_save normal']"));

        //환경설정 이동
        util.click(By.className("_config"));
        util.waitForIsElementPresent(By.linkText("캘린더로 돌아가기"));

        //환경설정 > 일정설정 이동
        util.click(By.xpath("//ul[@class='tab_setting tabs']/li[2]"));
        util.waitForIsElementPresent(By.xpath("//div[@class='_schedule tc-panel tc-selected']"));

        //캘린더 목록의 캘린더 갯수 가져오기
        maxCalNum = util.getXpathCount(By.xpath("//tbody[@class='_private_calendar_list']/tr"));
        System.out.print("캘린더 목록의 갯수 가져오기 :" + maxCalNum);

        //생성된 캘린더가 있는지 캘린더 목록에서 확인한다
        for(int i=1; i <= maxCalNum; i++)
        {
            String SubTemp;
            SubTemp = util.waitForIsElementPresent(By.xpath("//tbody[@class='_private_calendar_list']/tr["+i+"]/td/a/strong")).getText();

            if(SubTemp.contentEquals(Subname))
            {
                util.printLog("True");
                timeTableValue = util.waitForIsElementPresent(By.xpath("//tbody[@class='_private_calendar_list']/tr["+i+"]")).getAttribute("data-value");
                util.printLog(timeTableValue);
            }
            else
            {
                util.printLog("Fail");
            }
        }
    }


    /*
    * Step : 일정설정 > 캘린더 삭제 클릭
    * Result : 해당 캘린더 목록에서 노출 안됨
    */

    @Test
    public void TC_14_좌측영역_시간표만들기_삭제_Test() throws Exception {

        util.click(By.className("_config"));
        util.waitForIsElementPresent(By.linkText("캘린더로 돌아가기"));

        util.click(By.xpath("//ul[@class='tab_setting tabs']/li[2]"));
        util.waitForIsElementPresent(By.xpath("//div[@class='_schedule tc-panel tc-selected']"));

        util.waitForIsElementPresent(By.xpath("//tr[contains(@data-value,'"+timeTableValue+"')]/td[5]/div/a"));
        util.click(By.xpath("//tr[contains(@data-value,'"+timeTableValue+"')]/td[5]/div/a"));

        util.getAlert().accept();

        util.waitForPageLoaded();
        //util.click(By.xpath("//button[@class='_save normal']"));

        //util.getAlert().accept();
        //util.waitForPageLoaded();
    }


    /*
    * Step : 좌측영역 > 환경 설정 버튼 클릭
    * Result : 환경설정 페이지로 이동 됨
    */

   @Test
    public void TC_15_좌측영역_환경설정_Test() throws Exception{

       util.waitForIsElementPresent(By.xpath("//a[@class='btn_settingcal']"));
       util.mouseOver(By.xpath("//a[@class='btn_settingcal']"));
       util.click(By.xpath("//a[@class='btn_settingcal']"));

       util.waitForIsElementPresent(By.xpath("//h3[text()='환경설정']"));
       //assertTrue(util.waitForIsElementPresent(By.xpath("//h3[text()='환경설정']")).isDisplayed());
       assertTrue(util.waitForIsVisible(By.xpath("//h3[text()='환경설정']")));
       util.waitForIsElementPresent(By.xpath("//a[@class='_btn_back_calender btn_back_calender']"));
       util.click(By.xpath("//a[@class='_btn_back_calender btn_back_calender']"));

       //할일이나 일정일때 둘다 노출되는 버튼이 나올때까지 기다림
       util.waitForIsElementPresent(By.xpath("//a[@class='fold_widget']"));
    }



    /*
    * Step : 좌측영역 > 중요일정보기 버튼 클릭
    * Result : 중요 일정만 노출 됨
    */

    @Test
    public void TC_16_좌측영역_중요일정보기_Test() throws Exception{

        //기본 상태는 전체보기로 되어 있음
        util.waitForIsElementPresent(By.xpath("//div[contains(@class,'_all all_view')]"));


        //util.printLog(util.isElementPresent(By.xpath("//li[contains(@class,'_important important')]")).getAttribute("class"));
        util.waitForIsElementPresent(By.xpath("//li[contains(@class,'_important important')]"));
        util.click(By.xpath("//li[contains(@class,'_important important')]"));


        //util.printLog(util.isElementPresent(By.xpath("//li[contains(@class,'_important important')]")).getAttribute("class"));
        util.waitForIsElementPresent(By.xpath("//li[contains(@class,'_important important')]"));
        assertTrue(util.isElementPresent(By.xpath("//li[contains(@class,'_important important')]")).getAttribute("class").contains("select"));

        util.click(By.xpath("//div[contains(@class,'_all all_view')]"));
        util.waitForIsElementPresent(By.xpath("/div[@class='_all all_view select']"));
    }

     /*
    * Step : 좌측영역 > 범주일정보기 버튼 클릭
    * Result : 범주 일정만 노출 됨
    */

    @Test
    public void TC_17_좌측영역_범주일정보기_Test() throws Exception{

        //기본 상태는 전체보기로 되어 있음
        util.waitForIsElementPresent(By.xpath("//div[contains(@class,'_all all_view')]"));


        //util.printLog(util.isElementPresent(By.xpath("//li[contains(@class,'_category color')]")).getAttribute("class"));

        util.waitForIsElementPresent(By.xpath("//li[contains(@class,'_category color')]"));
        util.click(By.xpath("//li[contains(@class,'_category color')]"));

        //util.printLog(util.isElementPresent(By.xpath("//li[contains(@class,'_category color')]")).getAttribute("class"));

        util.waitForIsElementPresent(By.xpath("//li[contains(@class,'_category color')]"));
        assertTrue(util.isElementPresent(By.xpath("//li[contains(@class,'_category color')]")).getAttribute("class").contains("select"));

        util.click(By.xpath("//div[contains(@class,'_all all_view')]"));
        util.waitForIsElementPresent(By.xpath("//div[@class='_all all_view select']"));
    }

    /*
    * Step : 좌측영역 > 범주일정보기 버튼 클릭
    * Result : 범주 일정만 노출 됨
    */

    @Test
    public void TC_18_좌측영역_오래된일정정리하기_Test() throws Exception{

        //기본 상태는 전체보기로 되어 있음
        util.waitForIsElementPresent(By.xpath("//div[contains(@class,'_all all_view')]"));


        //util.printLog(util.isElementPresent(By.xpath("//li[contains(@class,'_cleaning delete')]")).getAttribute("class"));

        util.waitForIsElementPresent(By.xpath("//li[contains(@class,'_cleaning delete')]"));
        util.click(By.xpath("//li[contains(@class,'_cleaning delete')]"));

        util.waitForIsElementPresent(By.xpath("//div[contains(@class,'ly_delete_schedule layer_popup')]"));
        assertTrue(util.isElementPresent(By.xpath("//div[@class='layer_header']")).isDisplayed());

        util.click(By.xpath("//button[@class='_close normal']"));
        util.waitForIsNotVisible(By.xpath("//div[@class='layer_header']"));

        //util.click(By.xpath("//div[contains(@class,'_all all_view')]"));
        //util.waitForIsElementPresent(By.xpath("//div[@class='_all all_view select']"));
    }

    /*
    * Step : 좌측영역 > 배너 클릭
    * Result : 해당 배너 관련 페이지로 이동 됨
    * URL : https://www.worksmobile.com/kr/
    */

    @Test
    public void TC_19_좌측영역_배너_Test() throws Exception{

        util.click(By.xpath("//div[@class ='bn_nworks']"));
        util.waitForNewWindow();

        Title = util.getTitle();
        URL = util.getCurrentUrl();
        assertTrue(URL.contains("https://www.worksmobile.com/kr/"));
        assertTrue(Title.contains("Works Mobile 비즈니스 협업 서비스"));

        util.close();
        util.selectMainWindow();

        util.waitForTitle("일정 : 네이버 캘린더");
    }

    /*
    * Step : 좌측영역 > 오늘 할 일 클릭
    * Result : 할 일 페이지로 이동 됨
    * URL : https://calendar.naver.com/tasks.nhn
    */

    @Test
    public void TC_20_좌측영역_오늘할일_Test() throws Exception{

        util.click(By.xpath("//a[@class ='_go_task']"));
        util.waitForIsElementPresent(By.xpath("//a[@class='_openNewTaskForm write_todo']"));

        Title = util.getTitle();
        URL = util.getCurrentUrl();
        assertTrue(URL.contains("https://calendar.naver.com/tasks.nhn"));
        assertTrue(Title.contains("할 일 : 네이버 캘린더"));
        util.sleep(5);

        util.click(By.xpath("//button[@class='_viewSchedule type_schedule']"));
        util.waitForIsElementPresent(By.xpath("//button[@class='_go_task type_schedule todo']"));
        util.waitForTitle("일정 : 네이버 캘린더");
    }

}
