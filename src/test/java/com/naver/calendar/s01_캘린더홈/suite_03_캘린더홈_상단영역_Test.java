package com.naver.calendar.s01_캘린더홈;

import main.TestIds;
import main.Testcase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;


public class suite_03_캘린더홈_상단영역_Test extends Testcase {

    public String Title = null;
    public String URL = null;

    /*
   * Step : 로그인 > 해당 계정으로 로그인
   * Result : 해당하는 계정으로 로그인 됨
    */
    @Test
    public void TC_00_상단영역_로그인_Test() throws Exception {
        module.로그인(util, TestIds.CalUser.getId(), TestIds.CalUser.getPw());
    }

    /*
     * Step : 상단영역 > 일정검색 클릭
     * Result : 해당 검색어 검색결과 노출 된다.
     */
    @Test
    public void TC_01_상단영역_일정검색_Test() throws Exception {

        String searchKeyword = "일정";
        String SearchResult;


        //검색어 입력하고 그냥 검색 클릭하면 얼럿 발생
        //검색창에서 Space 입력하고 검색버튼 클릭
        //캘린더로 돌아가기 노출되는것 확인
        //노출된 검색어가 입력한 검색어와 동일한지 확인
        util.waitForIsElementPresent(By.xpath("//input[@id='searchKeyWord']"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//input[@id='searchKeyWord']")).getAttribute("value").contains("일정 검색"));


        util.click(By.className("search"));
        util.type(By.className("search"),searchKeyword);
        util.pressKeys(By.className("search"), Keys.SPACE);

        util.click(By.className("search_btn"));

        util.waitForIsElementPresent(By.className("btn_back_calender"));
        SearchResult = util.findElement(By.className("keyword")).getText();

        assertTrue(SearchResult.contains(searchKeyword));
        util.click(By.className("btn_back_calender"));
    }

    /*
     * Step : 상단영역 > 일정검색 > 상세검색 클릭
     * Result : 일정검색 > 상세검색 옵션 노출 됨
     */
    @Test
    public void TC_02_상단영역_일정검색_상세검색_Test() throws Exception{

        String searchKeyword = "일정";
        String searchTitle = "제목";
        String searchPlace = "장소";
        String searchMemo = "설명";
        String searchAttendee = "nvqa_4t040@naver.com";
        String SearchResult;

        util.windowMaximize();

        //상세버튼 클릭하고 제목에 검색어 입력 후 검색버튼 클릭

        util.click(By.className("detail"));
        util.waitForIsElementPresent(By.xpath("//div[@id='content_top']/div[2]/div[2]/div[1]/input"));
        util.type(By.xpath("//div[@id='content_top']/div[2]/div[2]/div[1]/input"),searchTitle);
        //util.waitForIsElementPresent(By.className("btn_srch"));
        //util.printLog(util.isElementPresent(By.className("search_area")).getAttribute("style"));

        //util.type(By.id("_title"),searchTitle);
        util.click(By.className("btn_srch"));

        //캘린더로 돌아가기 노출되는것 확인
        //제목에 검색에 유지되는것 확인
        util.waitForIsElementPresent(By.className("btn_back_calender"));
        util.printLog(util.waitForIsElementPresent(By.className("_title")).getText());

        SearchResult = util.isElementPresent(By.className("keyword")).getText();
        assertTrue(SearchResult.contains(searchTitle));

        util.click(By.className("btn_back_calender"));
    }

    /*
    * Step : 상단영역 > 공지사항 클릭
    * Result : 공지사항 페이지로 이동됨
    * URL : https://calendar.naver.com/notice.nhn
    */
    @Test
    public void TC_03_상단영역_공지사항_Test() throws Exception {

        //공지사항 제목 확인
        String noticeTitle;
        noticeTitle = util.isElementPresent(By.xpath("//span[@class='notice']/a")).getText();
        util.printLog("현재 공지사항 제목은 : "+noticeTitle);

        util.click(By.xpath("//span[@class='notice']"));
        util.waitForNewWindow();

        Title = util.getTitle();
        URL = util.getCurrentUrl();
        assertTrue(URL.contains("http://calendar.naver.com/notice.nhn"));
        assertTrue(Title.contains("네이버 캘린더"));

        //util.switchTo();
        util.close();
        util.selectMainWindow();

        util.waitForTitle("일정 : 네이버 캘린더");
    }

    /*
    * Step : 상단영역 > 할일보기 클릭
    * Result : 할일보기 페이지로 이동됨
    * URL : https://calendar.naver.com/tasks.nhn
    */

    @Test
    public void TC_04_상단영역_할일보기_Test() throws Exception{

        util.click(By.xpath("//button[@class='_go_task type_schedule todo']"));

        Title = util.getTitle();
        URL = util.getCurrentUrl();

        assertTrue(URL.contains("https://calendar.naver.com/tasks.nhn"));
        assertTrue(Title.contains("할 일 : 네이버 캘린더"));

        module.goBackToCalendar(util);
        util.waitForTitle(module.calTitle);
    }

    /*
    * Step : 상단영역 > 날짜 영역 클릭
    * Result : <,> 화살표 클릭함에 따라 이전달, 다음달로 이동됨
    */

    @Test
    public void TC_05_상단영역_날짜영역_Test() throws Exception{

        //URL에서 현재 날짜를 가져옴
        //URL에서 날짜 바뀐것을 확인
        URL = util.getCurrentUrl();
        //util.printLog(URL);

        util.printLog("\n현재 날짜는 :"+ module.GetDate(util));
        util.click(By.xpath("//button[@class='prev']"));
        util.printLog("\n이전 달을 클릭한 날짜는 : "+module.GetDate(util));


        util.click(By.xpath("//button[@class='next']"));
        util.click(By.xpath("//button[@class='next']"));
        util.printLog("\n다음 달을 두번 클릭한 날짜는 : "+module.GetDate(util));
    }

    /*
    * Step : 상단영역 > 오늘 클릭
    * Result : 미니달력, 캘린더 뷰에 오늘 날짜로 이동됨
    */
    @Test
    public void TC_06_상단영역_오늘_Test() throws Exception{


        util.printLog("\n현재 날짜는 :"+ module.GetDateFromURL(util));
        util.click(By.className("today"));

        util.printLog("\n오늘 버튼을 클릭한 후 날짜는 : "+ module.GetDateFromURL(util));
        util.waitForTitle("일정 : 네이버 캘린더");

        //시스템상의 오늘 날짜와 오늘 버튼을 눌러 이동한 날짜가 동일한지 확인
        assertTrue(module.TodayDate().contains(module.GetDateFromURL(util)));
    }

    /*
    * Step : 상단영역 > 일간 클릭
    * Result : 일간 > 일간 클릭하면 캘린더 영역 일간으로 노출 됨
    * URL : 뷰 방식 day로 표시되는것 확인
    */

    @Test
    public void TC_07_상단영역_일간_Test() throws Exception{

        util.click(By.xpath("//button[contains(@class,'_day diary')]"));
        util.waitForIsElementPresent(By.xpath("//button[@class='_day diary on']"));

        //URL 검증 필요
        util.waitForIsElementPresent(By.xpath("//span[@class='date']/span[10]"));
        URL = util.getCurrentUrl();
        assertTrue(URL.contains("day"));
     }


    /*
    * Step : 상단영역 > 주간 클릭
    * Result : 월간 > 주간 클릭하면 캘린더 영역 주간으로 노출 됨
    * URL : 뷰 방식 week로 표시되는것 확인
    */

    @Test
    public void TC_08_상단영역_주간_Test() throws Exception{

        util.click(By.xpath("//button[contains(@class,'_week  week')]"));
        util.waitForIsElementPresent(By.xpath("//button[@class='_week  week on']"));

        //URL 검증 필요
        util.waitForIsElementPresent(By.xpath("//span[@class='date']/span[11]"));
        URL = util.getCurrentUrl();
        util.printLog(URL);

        assertTrue(URL.contains("week"));
    }


    /*
    * Step : 상단영역 > 월간 클릭
    * Result : 월간 > 월간 클리하면 캘린더 영역 월간으로 노출 됨
    * URL : 뷰 방식 month로 표시되는것 확인
    */

    @Test
    public void TC_09_상단영역_월간_Test() throws Exception{

        util.click(By.xpath("//button[contains(@class,'_month month')]"));
        util.waitForIsElementPresent(By.xpath("//button[@class='_month month on']"));

        //URL 검증 필요
        util.waitForIsVisible(By.xpath("//div[@class='monthly_calendar']"));
        URL = util.getCurrentUrl();

        util.printLog(URL);
        assertTrue(URL.contains("month"));
    }

    /*
    * Step : 상단영역 > 목록 클릭
    * Result : 목록 > 목록 클리하면 캘린더 영역 목록으로 노출 됨
    * URL : 뷰 방식 list로 표시되는것 확인
    */

    @Test
    public void TC_10_상단영역_목록_Test() throws Exception{

        util.click(By.xpath("//button[contains(@class,'_list list')]"));
        util.waitForIsElementPresent(By.xpath("//button[@class='_list list on']"));


        //URL 검증 필요
        util.waitForIsVisible(By.xpath("//div[@class='list_frame']"));
        URL = util.getCurrentUrl();

        util.printLog(URL);
        assertTrue(URL.contains("list"));
    }

    /*
    * Step : 상단영역 > 평일 클릭
    * Result : 평일 > 평일 클리하면 캘린더 영역 평일으로 노출 됨
    * URL : 뷰 방식 user로 표시되는것 확인
    */

    @Test
    public void TC_11_상단영역_평일_Test() throws Exception{

        util.click(By.xpath("//button[contains(@class,'_user custom')]"));
        util.waitForIsElementPresent(By.xpath("//button[@class='_user custom on']"));

        //평일만 옵션 선택
        module.ViewType(util,"//input[@id='_user_setting_workweek']");
        //2일 옵션 선택
        module.ViewType(util,"//input[@id='_user_setting_2day']");
        //3일 옵션 선택
        module.ViewType(util,"//input[@id='_user_setting_3day']");
        //4일 옵션 선택
        module.ViewType(util,"//input[@id='_user_setting_4day']");
        //5일 옵션 선택
        module.ViewType(util,"//input[@id='_user_setting_5day']");
        //2주 옵션 선택
        module.ViewType(util,"//input[@id='_user_setting_2week']");
        //3주 옵션 선택
        module.ViewType(util,"//input[@id='_user_setting_3week']");
        //4주 옵션 선택
        module.ViewType(util,"//input[@id='_user_setting_4week']");

        URL = util.getCurrentUrl();
        util.printLog(URL);
        assertTrue(URL.contains("user"));

    }


    /*
    * Step : 상단영역 > 새로고침 클릭
    * Result : 페이지 새로 고침 됨
    */

    @Test
    public void TC_12_상단영역_새로고침_Test() throws Exception{

        util.click(By.className("reload"));
        assertTrue(util.waitForIsElementPresent(By.className("reload")).isDisplayed());
        }


    /*
    * Step : 상단영역 > 날짜계산기 클릭
    * Result : 날짜 계산기 노출 됨
    */

    @Test
    public void TC_13_상단영역_날짜계산기_Test() throws Exception{

        String year;
        String month;
        String day;

        util.click(By.className("calculator"));
        util.waitForIsElementPresent(By.xpath("//div[@class='_layer_content layer_content']"));
        //현재 날짜 계산기의 날짜 확인
        util.printLog(util.isElementPresent(By.xpath("//strong[@class='_set_date']/strong/strong")).getText());

        /*
        util.click(By.xpath("//input[@class='_basic_date input_txt']"));
        util.waitForIsElementPresent(By.id("_dateSelector"));

        //날짜 계산기의 현재 날짜 확인
        year = util.waitForIsElementPresent(By.xpath("//td[@class='calendar-date calendar-today calendar-selected']")).getAttribute("data-year");
        month = util.waitForIsElementPresent(By.xpath("//td[@class='calendar-date calendar-today calendar-selected']")).getAttribute("data-month");
        day = util.waitForIsElementPresent(By.xpath("//td[@class='calendar-date calendar-today calendar-selected']")).getAttribute("data-date");

        util.printLog(year+"."+month+"."+day);
        */

        //util.click(By.xpath("//input[@class='_basic_date input_txt']"));
        util.click(By.xpath("//a[@class='_close btn_sp btn_clse']"));

    }


    /*
    * Step : 상단영역 > 인쇄 클릭
    * Result : 인쇄 페이지로 이동 됨
    * URL : https://calendar.naver.com/printconfig.nhn
    */

    @Test
    public void TC_14_상단영역_인쇄_Test() throws Exception{
        util.click(By.className("print"));
        //util.sleep(10);

        util.waitForNewWindow();

        Title = util.getTitle();
        URL = util.getCurrentUrl();
        assertTrue(URL.contains("https://calendar.naver.com/printconfig.nhn"));
        assertTrue(Title.contains("네이버 캘린더"));

        util.switchTo();
        util.close();
        util.selectMainWindow();

        util.waitForTitle("일정 : 네이버 캘린더");
        assertTrue(Title.contains("일정 : 네이버 캘린더"));
    }

}


