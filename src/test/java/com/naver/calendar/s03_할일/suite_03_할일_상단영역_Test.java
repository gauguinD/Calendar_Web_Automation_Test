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
    public String taskOrder;
    public String taskOrderValue;
    public int completeTask;

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
        util.waitForIsElementPresent(By.xpath("//input[@class='_keyword search']"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//input[@class='_keyword search']")).getAttribute("value").contains("할 일 검색"));

        util.click(By.xpath("//input[@class='_keyword search']"));
        util.type(By.xpath("//input[@class='_keyword search']"),searchKeyword);
        util.pressKeys(By.className("search"), Keys.SPACE);

        util.click(By.xpath("//input[@class='_search search_btn']"));

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
    public void TC_02_상단영역_할일검색_상세검색_Test() throws Exception{

        String searchTitle = "할일";
        String searchPlace = "장소";
        String searchMemo = "설명";
        String searchAttendee = "nvqa_4t040@naver.com";
        String searchResult;

        util.windowMaximize();

        //상세버튼 클릭하고 제목에 검색어 입력 후 검색버튼 클릭
        util.click(By.xpath("//a[@class='_detail detail']"));

        util.waitForIsElementPresent(By.xpath("//div[@class='_detailArea search_area todo noworks']"));
        util.type(By.xpath("//input[@class='_content']"),searchTitle);

        util.click(By.xpath("//button[@class='_detailSearch btn_srch']"));

        //캘린더로 돌아가기 노출되는것 확인
        //제목에 검색에 유지되는것 확인
        util.waitForIsElementPresent(By.className("_back btn_back_calender"));
        util.printLog(util.waitForIsElementPresent(By.xpath("//div[@class='_noenddate_set']/ul/li/div/p/a")).getText());

        searchResult = util.isElementPresent(By.xpath("//div[@class='_noenddate_set']/ul/li/div/p/a")).getText();
        util.printLog(searchResult);
        assertTrue(searchResult.contains(searchTitle));

        util.click(By.xpath("//a[@class='_back btn_back_calender']"));
    }


    /*
    * Step : 상단영역 > 일정보기 클릭
    * Result : 일정보기 페이지로 이동됨
    * URL : https://calendar.naver.com/
    */

    @Test
    public void TC_03_상단영역_일정보기_Test() throws Exception{

        util.click(By.xpath("//button[@class='_viewSchedule type_schedule']"));

        Title = util.getTitle();
        URL = util.getCurrentUrl();

        assertTrue(URL.contains("https://calendar.naver.com/schedule.nhn"));
        assertTrue(Title.contains("일정 : 네이버 캘린더"));

        util.click(By.xpath("//button[@class='_go_task type_schedule todo']"));
        util.waitForTitle("할 일 : 네이버 캘린더");
    }


    /*
    * Step : 상단영역 > 등록일순 클릭
    * Result : 등록일순에 따라 할일 노출 순서 변경됨
    */

    @Test
    public void TC_04_상단영역_등록일순_Test() throws Exception{

        //현재 정렬 상태를 확인한다.
        util.waitForIsElementPresent(By.xpath("//button[@class='_openSortLayer btn_sorting']"));
        taskOrder = util.waitForIsElementPresent(By.xpath("//button[@class='_openSortLayer btn_sorting']/span")).getText();
        util.printLog("현재 할일 정렬 상태는 ["+taskOrder+"] 입니다.");

        util.click(By.xpath("//button[@class='_openSortLayer btn_sorting']"));
        util.click(By.xpath("//ul[contains(@class,'sort_lst')]/li[contains(text(),'등록일순')]"));

        taskOrderValue = util.waitForIsElementPresent(By.xpath("//button[@class='_openSortLayer btn_sorting']")).getAttribute("data-value");
        assertTrue(taskOrderValue.contains("0"));
    }


    /*
    * Step : 상단영역 > 완료일순 클릭
    * Result : 완료일순에 따라 할일 노출 순서 변경됨
    */

    @Test
    public void TC_05_상단영역_완료일순_Test() throws Exception{

        //현재 정렬 상태를 확인한다.
        util.waitForIsElementPresent(By.xpath("//button[@class='_openSortLayer btn_sorting']"));
        taskOrder = util.waitForIsElementPresent(By.xpath("//button[@class='_openSortLayer btn_sorting']/span")).getText();
        util.printLog("현재 할일 정렬 상태는 ["+taskOrder+"] 입니다.");

        util.click(By.xpath("//button[@class='_openSortLayer btn_sorting']"));
        util.click(By.xpath("//ul[contains(@class,'sort_lst')]/li[contains(text(),'완료일순')]"));

        taskOrderValue = util.waitForIsElementPresent(By.xpath("//button[@class='_openSortLayer btn_sorting']")).getAttribute("data-value");
        assertTrue(taskOrderValue.contains("1"));
    }


    /*
    * Step : 상단영역 > 중요도순 클릭
    * Result : 중요도순에 따라 할일 노출 순서 변경됨
    */

    @Test
    public void TC_06_상단영역_중요도순_Test() throws Exception{

        //현재 정렬 상태를 확인한다.
        util.waitForIsElementPresent(By.xpath("//button[@class='_openSortLayer btn_sorting']"));
        taskOrder = util.waitForIsElementPresent(By.xpath("//button[@class='_openSortLayer btn_sorting']/span")).getText();
        util.printLog("현재 할일 정렬 상태는 ["+taskOrder+"] 입니다.");

        util.click(By.xpath("//button[@class='_openSortLayer btn_sorting']"));
        util.click(By.xpath("//ul[contains(@class,'sort_lst')]/li[contains(text(),'중요도순')]"));

        taskOrderValue = util.waitForIsElementPresent(By.xpath("//button[@class='_openSortLayer btn_sorting']")).getAttribute("data-value");
        assertTrue(taskOrderValue.contains("2"));
    }


    /*
    * Step : 상단영역 > 제목순 클릭
    * Result : 제목순에 따라 할일 노출 순서 변경됨
    */

    @Test
    public void TC_07_상단영역_제목순_Test() throws Exception{

        //현재 정렬 상태를 확인한다.
        util.waitForIsElementPresent(By.xpath("//button[@class='_openSortLayer btn_sorting']"));
        taskOrder = util.waitForIsElementPresent(By.xpath("//button[@class='_openSortLayer btn_sorting']/span")).getText();
        util.printLog("현재 할일 정렬 상태는 ["+taskOrder+"] 입니다.");

        util.click(By.xpath("//button[@class='_openSortLayer btn_sorting']"));
        util.click(By.xpath("//ul[contains(@class,'sort_lst')]/li[contains(text(),'제목순')]"));

        taskOrderValue = util.waitForIsElementPresent(By.xpath("//button[@class='_openSortLayer btn_sorting']")).getAttribute("data-value");
        assertTrue(taskOrderValue.contains("3"));
    }


    /*
    * Step : 상단영역 > 진행상태순 클릭
    * Result : 진행상태순에 따라 할일 노출 순서 변경됨
    */

    @Test
    public void TC_08_상단영역_진행상태순_Test() throws Exception{

        //현재 정렬 상태를 확인한다.
        util.waitForIsElementPresent(By.xpath("//button[@class='_openSortLayer btn_sorting']"));
        taskOrder = util.waitForIsElementPresent(By.xpath("//button[@class='_openSortLayer btn_sorting']/span")).getText();
        util.printLog("현재 할일 정렬 상태는 ["+taskOrder+"] 입니다.");

        util.click(By.xpath("//button[@class='_openSortLayer btn_sorting']"));
        util.click(By.xpath("//ul[contains(@class,'sort_lst')]/li[contains(text(),'진행상태순')]"));

        taskOrderValue = util.waitForIsElementPresent(By.xpath("//button[@class='_openSortLayer btn_sorting']")).getAttribute("data-value");
        assertTrue(taskOrderValue.contains("5"));
    }





    /*

    * Step : 상단영역 > 완료된 할 일 포함 클릭
   * Result : 완료된 할 일이 목록에 노출 됨
    */
    @Test
    public void TC_09_상단영역_완료된할일포함_Test() throws Exception{


        util.waitForIsElementPresent(By.xpath("//input[@class='_includingCompleted']"));

        if(util.waitForIsElementPresent(By.xpath("//input[@class='_includingCompleted']")).isSelected()){
            util.printLog("현재 완료된 할 일 포함해서 노출 되어 있습니다.");
            completeTask = util.getXpathCount(By.xpath("//li[@class='_task complete']"));
            util.printLog("완료된 할 일은 총 ["+completeTask+"]개 입니다");
            assertTrue(util.waitForIsElementPresent(By.xpath("//a[@class='_complete ck_complete mark']")).isDisplayed());
        }
        else{
            util.printLog("현재 완료된 할 일 노출 되지 않고 있습니다.");
            util.click(By.xpath("//input[@class='_includingCompleted']"));
            assertTrue(util.waitForIsElementPresent(By.xpath("//a[@class='_complete ck_complete mark']")).isDisplayed());
        }
    }


    /*
    * Step : 상단영역 > 새로고침 클릭
    * Result : 페이지 새로 고침 됨
    */

    @Test
    public void TC_10_상단영역_새로고침_Test() throws Exception{

        util.click(By.xpath("//a[@class='_refresh reload']"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//a[@class='_refresh reload']")).isDisplayed());
    }


    /*
    * Step : 상단영역 > 인쇄 클릭
    * Result : 인쇄 페이지로 이동 됨
    * URL : https://calendar.naver.com/printconfig.nhn
    */

    @Test
    public void TC_11_상단영역_인쇄_Test() throws Exception{
        util.click(By.xpath("//a[@class='_print print']"));
        util.waitForNewWindow();

        Title = util.getTitle();
        URL = util.getCurrentUrl();
        assertTrue(URL.contains("https://calendar.naver.com/printconfig.nhn"));
        assertTrue(Title.contains("네이버 캘린더"));

        util.close();
        util.selectMainWindow();

        util.waitForTitle("할 일 : 네이버 캘린더");

    }

}


