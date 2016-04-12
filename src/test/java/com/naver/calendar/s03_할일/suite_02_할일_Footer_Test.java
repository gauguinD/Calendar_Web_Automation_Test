package com.naver.calendar.s03_할일;

import main.TestIds;
import main.Testcase;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;


public class suite_02_할일_Footer_Test extends Testcase {

    public String Title = null;
    public String URL = null;

    /*
   * Step : 로그인 > 해당 계정으로 로그인
   * Result : 해당하는 계정으로 로그인 됨
    */
    @Test
    public void TC_00_Footer_로그인_Test() throws Exception {
        module.로그인(util, TestIds.CalUser.getId(), TestIds.CalUser.getPw());
    }

    /*
     * Step : Footer > 환경설정 클릭
     * Result : 환경설정 페이지로 이동됨
     */
    @Test
    public void TC_01_Footer_환경설정_Test() throws Exception {

        util.click(By.className("_config"));
        assertTrue(util.isElementPresent(By.linkText("캘린더로 돌아가기")).isDisplayed());

        util.click(By.linkText("캘린더로 돌아가기"));
    }

    /*
     * Step : Footer > 스킨설정 클릭
     * Result : 스킨설정 레이어 노출됨
     */
    @Test
    public void TC_02_Footer_스킨설정_Test() throws Exception{

        int skinNum;
        String skinName;
        String skinColor;

        util.click(By.className("_skinLayerBtn"));
        util.waitForIsElementPresent(By.className("skin_title"));

        skinNum = util.getXpathCount(By.xpath("//ul[@class='sk_list_area']/li"));

        for(int i=1; i<skinNum+1; i++)
        {
            util.click(By.xpath("//ul[@class='sk_list_area']/li["+i+"]"));
            //ul[@class='sk_list_area']/li[1]/a/strong
            skinName = util.isElementPresent(By.xpath("//*[@id='wrap']")).getAttribute("class");
            skinColor = util.isElementPresent(By.xpath("//ul[@class='sk_list_area']/li["+i+"]/a")).getAttribute("data-name");

            util.printLog(skinName);
            util.printLog(skinColor);
            //assertTrue(skinName.contains(skinColor));
        }

        util.click(By.xpath("//button[@class='_save']"));
    }

    /*
    * Step : Footer > 모바일 캘린더 클릭
    * Result : 모바일캘린더 페이지로 이동됨
    * URL : https://calendar.naver.com/promotion.nhn
    */

    @Test
    public void TC_03_Footer_모바일캘린더_Test() throws Exception {

        util.click(By.className("_mobile_calendar"));
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

    @Test
    public void TC_04_Footer_인기공개캘린더_Test() throws Exception{

        util.click(By.className("_open_public_calendar"));
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

    @Test
    public void TC_05_Footer_선생님우대프로그램_Test() throws Exception{

        util.click(By.className("_open_school"));
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

    @Test
    public void TC_06_Footer_공지사항_Test() throws Exception{

        util.click(By.className("_open_notice"));
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

    @Test
    public void TC_07_Footer_캘린더고객센터_Test() throws Exception{

        util.click(By.className("_calendar_faq"));
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
