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

        util.clickAndWait(By.xpath("//span[contains(text(),'약속쓰기')]"));
        assertTrue(util.isElementPresent(By.linkText("캘린더로 돌아가기")).isDisplayed());

        util.clickAndWait(By.xpath("//a[@class='_back btn_back_calender']"));

        /*
        //시간 일정쓰기 설정 되어 있는지 확인
        if ((util.isElementPresent(By.xpath("//a[@class='_set_timezone change_time']")).isDisplayed())) {
        } else {
            util.isElementPresent(By.id("ch1_1")).click();
        }

        //제목 입력
        util.isElementPresent(By.id("tx0_0")).sendKeys(module.contents);
        //시작 날짜 입력
        util.isElementPresent(By.id("start_date")).clear();
        util.isElementPresent(By.id("start_date")).sendKeys(module.StartDate);
        //종료 날짜 입력
        util.isElementPresent(By.id("end_date")).clear();
        util.isElementPresent(By.id("end_date")).sendKeys(module.EndDate);

        util.clickAndWait(By.xpath("//button[@class ='btn_sys pos_save']"));
        util.waitForIsElementPresent(By.xpath("//button[contains(@class,'_go_task type_schedule todo')]"));
        */
    }

    /*
    * Step : 좌측영역 > 기념일 관리 클릭
    * Result : 기념일 관리 페이지로 이동
    */
    @Test
    public void TC_02_좌측영역_기념일관리_Test() throws Exception{
        util.clickAndWait(By.xpath("//span[contains(text(),'기념일 관리')]"));
        assertTrue(util.isElementPresent(By.linkText("캘린더로 돌아가기")).isDisplayed());

        util.clickAndWait(By.xpath("//a[@class='_btn_back_calender btn_back_calender']"));
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
        util.clickAndWait(By.xpath("//button[@class='_fold btn_fold']"));
        module.CurrentDate(util);
        util.clickAndWait(By.xpath("//button[@class='_fold btn_fold']"));

        if(miniCalendar.contains("top: 97px; left: 0px;"))
        {
            util.printLog(miniCalendar);
            util.clickAndWait(By.xpath("//button[@class='_fold btn_fold']"));
            module.CurrentDate(util);
        }


        //날짜 <,> 버튼 동작 확인
        //이전 달 버튼 클릭
        util.clickAndWait(By.xpath("//a[@class='btn_prev rollover calendar-btn-prev-mon']"));
        module.CurrentDate(util);

        //다음 달 버튼 클릭
        util.clickAndWait(By.xpath("//a[@class='btn_next rollover calendar-btn-next-mon']"));
        util.clickAndWait(By.xpath("//a[@class='btn_next rollover calendar-btn-next-mon']"));
        module.CurrentDate(util);
    }

    /*
    * Step : 좌측영역 > 1년보기 클릭
    * Result : 1년보기 페이지로 이동
    */
    @Test
    public void TC_04_좌측영역_1년보기_Test() throws Exception{
        // 1년 버튼 동작 확인
        util.clickAndWait(By.className("btn_1year"));
        util.waitForIsElementPresent(By.xpath("//div[@class='sub_tit']/h3"));
    }


    /*
    * Step : 좌측영역 > 미니달력 클릭
    * Result : 미니 달력에서 클릭한 날짜로 이동 됨
    */
    @Test
    public void TC_05_좌측영역_미니달력_Test() throws Exception{
        // 1년 버튼 동작 확인
        util.clickAndWait(By.className("btn_1year"));
        util.waitForIsElementPresent(By.xpath("//div[@class='sub_tit']/h3"));
    }


    /*
    * Step : 좌측영역 > 전체일정보기 클릭
    * * Result : 전체 일정 노출 됨
    */
    @Test
    public void TC_06_좌측영역_전체일정보기_Test() throws Exception{
        // 1년 버튼 동작 확인
        // 현재 월뷰 상태 확인

        //현재 스캐쥴 노출되는지 확인
        String monthFrame;
        monthFrame = util.isElementPresent(By.xpath("//div[@class='view_schedule']")).getAttribute("style");
        util.printLog(monthFrame);

        util.clickAndWait(By.xpath("//span[@class='ico ico_cal']"));

        monthFrame = util.isElementPresent(By.xpath("//div[@class='view_schedule']")).getAttribute("style");
        //assertTrue(monthFrame.contains("display: block;"));
        util.printLog(monthFrame);
    }

    /*
    * Step : 좌측영역 > 내캘린더 클릭
    * Result : 내캘린더에 해당하는 일정만 노출 됨
    *
    */
    @Test
    public void TC_07_좌측영역_내캘린더_Test() throws Exception{
        // 1년 버튼 동작 확인
        //현재 스캐쥴 노출되는지 확인
        //for문으로 전체 일정 확인하면서 내 캘린더 키값 없는 일정 노출 되는지 확인
        //6819065 - 캘린더 Key 값

        //6819065/1025098991_2016-02-09_9
        //"//div[@class='_schedule_container table_container']/div[1~5]"

        String calId;
        String eventKey;
        calId = module.GetCalendarKey(util,"내 캘린더");
        eventKey = util.isElementPresent(By.xpath("//td[@class='not_empty']/div")).getAttribute("key");
    }

    /*
    * Step : 좌측영역 > +버튼 클릭 > 내 캘린더 만들기
    * Result : 기념일 관리 페이지로 이동
    */

    @Test
    public void TC_08_좌측영역_캘린더만들기_Test() throws Exception{
        util.clickAndWait(By.className("btn_makecal"));
        util.clickAndWait(By.partialLinkText("내 캘린더 만들기"));

        Subname = util.isElementPresent(By.id("$$_calendar_name")).getAttribute("value").toString();
        util.printLog(Subname);

        util.clickAndWait(By.xpath("//button[@class ='_save normal']"));

        //캘린더 목록의 캘린더 갯수 가져오기
        int NumberOfCalender = util.waitAndGetXpathCount(By.className("cal_name"));
        System.out.print("캘린더 목록의 갯수 가져오기 :" + NumberOfCalender);

        //생성한 캘린더의 이름과 dataValue를 가져오기
        //dataValue = util.isElementPresent(By.xpath("//a[contains(text(),'"+Subname+"')]")).getAttribute("data-value").toString();
        //util.printLog("dataValue 값 입니다"+dataValue);

        //dataValue = util.isElementPresent(By.xpath("//a[@class='_calendar_name' and contains(text(),'"+Subname+"')]")).getAttribute("data-value");
        //util.printLog("dataValue 값 입니다"+dataValue);


        //생성된 캘린더가 있는지 캘린더 목록에서 확인한다
        for(int i=1; i <= NumberOfCalender; i++)
        {
            String SubTemp;
            SubTemp = util.isElementPresent(By.xpath("//*[@id='calendar_list_container']/div[2]/ul/li["+i+"]/a[2]")).getAttribute("title").toString();

            if(SubTemp.contentEquals(Subname))
            {
                util.printLog("True");
                dataValue = util.isElementPresent(By.xpath("//*[@id='calendar_list_container']/div[2]/ul/li["+i+"]")).getAttribute("calendarid");
                util.printLog(dataValue);
            }
            else
            {
                util.printLog("Fail");
            }
            //*[@id="calendar_list_container"]/div[2]/ul/li[1]
            //*[@id="calendar_list_container"]/div[2]/ul/li[5]
        }
    }

    @Test
    public void TC_09_좌측영역_캘린더삭제_Test() throws Exception{
        util.clickAndWait(By.className("btn_settingcal"));
        util.printLog(util.isElementPresent(By.xpath("//div[@class='_calendar_name' and contains(data-value, '"+dataValue+"']")).toString());

        //*[@id="holder"]/div/div[2]/div[1]/div/div[2]/table[2]/tbody/tr[4]
        //*[@id="holder"]/div/div[2]/div[1]/div/div[2]/table[2]/tbody/tr[4]/td[5]/div/a

       /* util.clickAndWait(By.xpath("//button[@class ='_save normal']"));

        for(int i=1; i<9; i++)
        {
            String SubTemp;
            SubTemp = util.isElementPresent(By.xpath("//*[@id='calendar_list_container']/div[2]/ul/li["+i+"]/a[2]")).getAttribute("title").toString();
            util.printLog(SubTemp);
            util.printLog(Subname);
            if(SubTemp.contentEquals(Subname))
            {
                util.printLog("True");
            }
            else
            {
                util.printLog("Fail");
            }
            //*[@id="calendar_list_container"]/div[2]/ul/li[1]
            //*[@id="calendar_list_container"]/div[2]/ul/li[5]
        }
        */
    }

}
