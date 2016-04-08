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
    //@Test
    public void TC_01_상단영역_일정검색_Test() throws Exception {

        String searchKeyword = "일정";
        String SearchResult;


        //검색어 입력하고 그냥 검색 클릭하면 얼럿 발생
        //검색창에서 Space 입력하고 검색버튼 클릭
        //캘린더로 돌아가기 노출되는것 확인
        //노출된 검색어가 입력한 검색어와 동일한지 확인
        util.clickAndWait(By.className("search"));
        util.type(By.className("search"),searchKeyword);
        util.pressKeys(By.className("search"), Keys.SPACE);

        util.clickAndWait(By.className("search_btn"));

        util.waitForIsElementPresent(By.className("btn_back_calender"));
        SearchResult = util.findElement(By.className("keyword")).getText();

        assertTrue(SearchResult.contains(searchKeyword));
        util.clickAndWait(By.className("btn_back_calender"));
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

        //상세버튼 클릭하고 제목에 검색어 입력 후 검색버튼 클릭

        //util.printLog(util.isElementPresent(By.className("search_area")).getAttribute("style"));
        util.clickAndWait(By.className("detail"));
        util.type(By.xpath("//div[@id='content_top']/div[2]/div[2]/div[1]/input"),searchTitle);
        //util.waitForIsElementPresent(By.className("btn_srch"));
        //util.printLog(util.isElementPresent(By.className("search_area")).getAttribute("style"));

        //util.type(By.id("_title"),searchTitle);
        util.clickAndWait(By.className("btn_srch"));

        //캘린더로 돌아가기 노출되는것 확인
        //제목에 검색에 유지되는것 확인
        util.waitForIsElementPresent(By.className("btn_back_calender"));
        util.printLog(util.waitForIsElementPresent(By.className("_title")).getText());

        SearchResult = util.isElementPresent(By.className("keyword")).getText();
        assertTrue(SearchResult.contains(searchTitle));


    }

    /*
    * Step : Footer > 모바일 캘린더 클릭
    * Result : 모바일캘린더 페이지로 이동됨
    * URL : https://calendar.naver.com/promotion.nhn
    */

    //@Test
    public void TC_03_Footer_모바일캘린더_Test() throws Exception {

        util.clickAndWait(By.className("_mobile_calendar"));
        util.waitForNewWindow();

        Title = util.getTitle();
        URL = util.getCurrentUrl();
        assertTrue(URL.contains("https://calendar.naver.com/promotion.nhn"));
        assertTrue(Title.contains("네이버 캘린더"));

        //util.switchTo();
        util.close();
        util.selectMainWindow();

        util.waitForTitle("일정 : 네이버 캘린더");
    }

    /*
    * Step : Footer > 인기 공개 캘린더 클릭
    * Result : 인기 공개 캘린더 페이지로 이동됨
    * URL : https://calendar.naver.com/subscribePage.nhn
    */

    //@Test
    public void TC_04_Footer_인기공개캘린더_Test() throws Exception{

        util.clickAndWait(By.className("_open_public_calendar"));
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
    * Step : Footer > 선생님우대프로그램 클릭
    * Result : 선생님우대프로그램 페이지로 이동됨
    * URL : https://calendar.naver.com/school.nhn
    */

    //@Test
    public void TC_05_Footer_선생님우대프로그램_Test() throws Exception{

        util.clickAndWait(By.className("_open_school"));
        util.waitForNewWindow();

        Title = util.getTitle();
        URL = util.getCurrentUrl();
        assertTrue(URL.contains("https://calendar.naver.com/school.nhn"));
        assertTrue(Title.contains("스승의 날 기념 선생님 5천분께, 학급 관리 패키지를 드립니다!"));

        util.close();
        util.selectMainWindow();

        util.waitForTitle("일정 : 네이버 캘린더");
    }

    /*
    * Step : Footer > 공지사항 클릭
    * Result : 공지사항 페이지로 이동됨
    * URL : https://calendar.naver.com/notice.nhn
    */

    //@Test
    public void TC_06_Footer_공지사항_Test() throws Exception{

        util.clickAndWait(By.className("_open_notice"));
        util.waitForNewWindow();

        util.switchTo();

        Title = util.getTitle();
        URL = util.getCurrentUrl();
        assertTrue(URL.contains("http://calendar.naver.com/notice.nhn"));
        assertTrue(Title.contains("네이버 캘린더"));

        util.selectMainWindow();
        util.closeNewWindow();


        util.waitForTitle("일정 : 네이버 캘린더");
    }

        /*
    * Step : Footer > 캘린더고객센터 클릭
    * Result : 캘린더고객센터 페이지로 이동됨
    * URL : https://help.naver.com/support/service/main.nhn?serviceNo=635
    */

    //@Test
    public void TC_07_Footer_캘린더고객센터_Test() throws Exception{

        util.clickAndWait(By.className("_calendar_faq"));
        util.waitForNewWindow();


        Title = util.getTitle();
        URL = util.getCurrentUrl();
        assertTrue(URL.contains("https://help.naver.com/support/service/main.nhn?serviceNo=635"));
        assertTrue(Title.contains("네이버 고객센터"));

        util.selectMainWindow();
        util.closeNewWindow();

        util.waitForTitle("일정 : 네이버 캘린더");
    }

}
