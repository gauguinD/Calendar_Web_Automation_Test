package com.naver.calendar.s03_할일;

import main.TestIds;
import main.Testcase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;


public class suite_03_할일_상단영역_Test extends Testcase {

    public String Title = null;
    public String URL = null;

    /*
   * Step : 로그인 > 해당 계정으로 로그인
   * Result : 해당하는 계정으로 로그인 됨
    */
    @Test
    public void TC_00_상단영역_로그인_Test() throws Exception {
        module.로그인(util, TestIds.CalUser.getId(), TestIds.CalUser.getPw());

        util.waitForTitle(module.calTitle);
        util.goTo(module.taskURL);

        Title = util.getTitle();
        URL = util.getCurrentUrl();

        util.printLog("[Title] : " + Title);
        util.printLog("[URL] : " + URL);

        assertTrue(Title.contains(module.taskTitle));
        assertTrue(URL.contains(module.taskURL));
    }

    /*
     * Step : 상단영역 > 할일검색 클릭
     * Result : 해당 검색어 검색결과 노출 된다.
     */
    @Test
    public void TC_01_상단영역_할일검색_Test() throws Exception {

        String searchKeyword = "할일";
        String SearchResult;


        //검색어 입력하고 그냥 검색 클릭하면 얼럿 발생
        //검색창에서 Space 입력하고 검색버튼 클릭
        //캘린더로 돌아가기 노출되는것 확인
        //노출된 검색어가 입력한 검색어와 동일한지 확인
        util.click(By.id("searchKeyWord"));
        util.type(By.id("searchKeyWord"),searchKeyword);
        util.pressKeys(By.className("search"), Keys.SPACE);

        util.click(By.id("searchBtn"));

        util.waitForIsElementPresent(By.xpath("//a[@class='_back btn_back_calender']"));
        SearchResult = util.findElement(By.className("keyword")).getText();

        assertTrue(SearchResult.contains(searchKeyword));
        util.click(By.xpath("//a[@class='_back btn_back_calender']"));
    }

    /*
     * Step : 상단영역 > 할일검색 > 상세검색 클릭
     * Result : 할일검색 > 상세검색 옵션 노출 됨
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

        //util.printLog(util.isElementPresent(By.className("search_area")).getAttribute("style"));
        util.click(By.xpath("//a[@class='_detail detail']"));
        util.type(By.xpath("//div[@id='content_top']/div[2]/div[2]/div[1]/input"),searchTitle);
        //util.waitForIsElementPresent(By.className("btn_srch"));
        //util.printLog(util.isElementPresent(By.className("search_area")).getAttribute("style"));

        //util.type(By.id("_title"),searchTitle);
        util.click(By.className("btn_srch"));

        //캘린더로 돌아가기 노출되는것 확인
        //제목에 검색에 유지되는것 확인
        util.waitForIsElementPresent(By.xpath("//a[@class='_back btn_back_calender']"));
        util.printLog(util.waitForIsElementPresent(By.className("_title")).getText());

        SearchResult = util.waitForIsElementPresent(By.className("keyword")).getText();
        //assertTrue(SearchResult.contains(searchTitle));

        util.click(By.xpath("//a[contains(@class,'btn_back_calender')]"));
    }


    /*
    * Step : 상단영역 > 일정보기 클릭
    * Result : 일정보기 페이지로 이동됨
    * URL : https://calendar.naver.com/schedule.nhn
    */

    @Test
    public void TC_03_상단영역_일정보기_Test() throws Exception{

        //util.click(By.xpath("//button[@class='_viewSchedule type_schedule']"));
        util.click(By.xpath("//button[contains(@class,'type_schedule')]"));

        Title = util.getTitle();
        URL = util.getCurrentUrl();

        assertTrue(URL.contains(module.calURL));
        assertTrue(Title.contains(module.calTitle));

        util.click(By.xpath("//button[@class='_go_task type_schedule todo']"));
        util.waitForTitle(module.taskTitle);
    }

    /*
    * Step : 상단영역 > 완료일순 클릭
    * Result : 할일 목록 완료일 순으로 정렬됨
    */
    @Test
    public void TC_04_상단영역_완료일순_Test() throws Exception {

        String sortList;

        sortList = util.waitForIsElementPresent(By.xpath("//button[@class='_openSortLayer btn_sorting']/span[1]")).getText();
        util.printLog(sortList);

        util.click(By.xpath("//button[@class='_openSortLayer btn_sorting']"));
        util.click(By.xpath("//ul[@class='sort_lst']/li[2]"));

        sortList = util.waitForIsElementPresent(By.xpath("//button[@class='_openSortLayer btn_sorting']/span[1]")).getText();
        assertTrue(sortList.contains("완료일순"));
    }

    /*
    * Step : 상단영역 > 등록일순 클릭
    * Result : 할일 목록 등록일 순으로 정렬됨
    */
    @Test
    public void TC_05_상단영역_등록일순_Test() throws Exception {

        String sortList;

        sortList = util.waitForIsElementPresent(By.xpath("//button[@class='_openSortLayer btn_sorting']/span[1]")).getText();
        util.printLog(sortList);

        util.click(By.xpath("//button[@class='_openSortLayer btn_sorting']"));
        util.click(By.xpath("//ul[@class='sort_lst']/li[1]"));

        sortList = util.waitForIsElementPresent(By.xpath("//button[@class='_openSortLayer btn_sorting']/span[1]")).getText();
        assertTrue(sortList.contains("등록일순"));
    }

    /*
    * Step : 상단영역 > 중요도순 클릭
    * Result : 할일 목록 중요도순으로 정렬됨
    */
    @Test
    public void TC_06_상단영역_중요도순_Test() throws Exception {

        String sortList;

        sortList = util.waitForIsElementPresent(By.xpath("//button[@class='_openSortLayer btn_sorting']/span[1]")).getText();
        util.printLog(sortList);

        util.click(By.xpath("//button[@class='_openSortLayer btn_sorting']"));
        util.click(By.xpath("//ul[@class='sort_lst']/li[3]"));

        sortList = util.waitForIsElementPresent(By.xpath("//button[@class='_openSortLayer btn_sorting']/span[1]")).getText();
        assertTrue(sortList.contains("중요도"));
    }

    /*
    * Step : 상단영역 > 제목순 클릭
    * Result : 할일 목록 제목순으로 정렬됨
    */
    @Test
    public void TC_07_상단영역_제목순_Test() throws Exception {

        String sortList;

        sortList = util.waitForIsElementPresent(By.xpath("//button[@class='_openSortLayer btn_sorting']/span[1]")).getText();
        util.printLog(sortList);

        util.click(By.xpath("//button[@class='_openSortLayer btn_sorting']"));
        util.click(By.xpath("//ul[@class='sort_lst']/li[4]"));

        sortList = util.waitForIsElementPresent(By.xpath("//button[@class='_openSortLayer btn_sorting']/span[1]")).getText();
        assertTrue(sortList.contains("제목순"));
    }

    /*
 * Step : 상단영역 > 진행상태순 클릭
 * Result : 할일 목록 진행상태순으로 정렬됨
 */
    @Test
    public void TC_08_상단영역_진행상태순_Test() throws Exception {

        String sortList;

        sortList = util.waitForIsElementPresent(By.xpath("//button[@class='_openSortLayer btn_sorting']/span[1]")).getText();
        util.printLog(sortList);

        util.click(By.xpath("//button[@class='_openSortLayer btn_sorting']"));
        util.click(By.xpath("//ul[@class='sort_lst']/li[5]"));

        sortList = util.waitForIsElementPresent(By.xpath("//button[@class='_openSortLayer btn_sorting']/span[1]")).getText();
        assertTrue(sortList.contains("진행상태순"));
    }

    /*
    * Step : 상단영역 > 완료된 할 일 포함 클릭
    * Result : 할 일 목록에 완료된 할 일도 노출 됨
    */

    @Test
    public void TC_09_상단영역_완료된할일포함_Test() throws Exception{

        util.waitForIsElementPresent(By.className("no_list"));
        util.click(By.xpath("//input[@class='_includingCompleted']"));

        assertTrue(util.waitForIsElementPresent(By.xpath("//a[@class='_progress_status btn_state v3']")).isDisplayed());
    }

    /*
    * Step : 상단영역 > 새로고침 클릭
    * Result : 페이지 새로 고침 됨
    */

    @Test
    public void TC_10_상단영역_새로고침_Test() throws Exception{

        util.click(By.className("reload"));
        }

    /*
    * Step : 상단영역 > 인쇄 클릭
    * Result : 인쇄 페이지로 이동 됨
    * URL : https://calendar.naver.com/printconfig.nhn
    */

    @Test
    public void TC_11_상단영역_인쇄_Test() throws Exception{
        util.click(By.className("print"));
        util.waitForNewWindow();

        Title = util.getTitle();
        URL = util.getCurrentUrl();
        assertTrue(URL.contains("https://calendar.naver.com/printconfig.nhn"));
        assertTrue(Title.contains("네이버 캘린더"));

        //util.switchTo();
        util.close();
        util.selectMainWindow();

        util.waitForTitle(module.taskTitle);

    }

}


