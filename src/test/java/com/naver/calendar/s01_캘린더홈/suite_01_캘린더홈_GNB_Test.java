package com.naver.calendar.s01_캘린더홈;

import main.TestIds;
import main.Testcase;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;


public class suite_01_캘린더홈_GNB_Test extends Testcase {

    public String Title = null;
    public String URL = null;

    /*
    * Step : 로그인 > 해당 계정으로 로그인
    * Result : 해당하는 계정으로 로그인 됨
    */

    @Test
    public void TC_00_GNB_로그인_Test() throws Exception {
        module.로그인(util, TestIds.CalUser.getId(), TestIds.CalUser.getPw());
    }


    /*
    * Step : GNB > NAVER 클릭
    * Result : 네이버 페이지로 이동됨
    * URL : http://m.naver.com"
    */

    @Test
    public void TC_01_GNB_NAVER_Test() throws Exception {

        util.click(By.className("naver"));
        module.assertCalendarPage(util,module.mainTitle,module.mainURL);

        module.goBackToCalendar(util);
    }


    /*
    * Step : GNB > me,메일,쪽지,메모,주소록,클라우드,오피스,가계부 클릭
    * Result : 개별 서비스 페이지로 이동됨
    * URL : http://me.naver.com"
    */

    @Test
    public void TC_02_GNB_탭_me_Test() throws Exception{

        util.click(By.className("me"));
        util.waitForIsElementPresent(By.xpath("//a[@class='N=a:PNB.me']"));

        module.assertCalendarPage(util,module.meTitle,module.meURL);

        module.goBackToCalendar(util);
    }


    /*
    * Step : GNB > me,메일,쪽지,메모,주소록,클라우드,오피스,가계부 클릭
    * Result : 개별 서비스 페이지로 이동됨
    * URL : http://me.naver.com"
    */

    @Test
    public void TC_03_GNB_탭_메일_Test() throws Exception{

        util.click(By.className("mail"));
        module.assertCalendarPage(util,module.mailTitle,module.mailURL);
        module.goBackToCalendar(util);
    }


    /*
    * Step : GNB > me,메일,쪽지,메모,주소록,클라우드,오피스,가계부 클릭
    * Result : 개별 서비스 페이지로 이동됨
    * URL : http://me.naver.com"
    */

    //@Test
    public void TC_04_GNB_탭_메모_쪽지_Test() throws Exception{

        util.click(By.className("note"));

        module.assertCalendarPage(util,module.noteTitle,module.noteURL);

        module.goBackToCalendar(util);
    }


    /*
    * Step : GNB > me,메일,쪽지,메모,주소록,클라우드,오피스,가계부 클릭
    * Result : 개별 서비스 페이지로 이동됨
    * URL : http://me.naver.com"
    */

    @Test
    public void TC_05_GNB_탭_메모_Test() throws Exception{

        util.click(By.className("memo"));

        module.assertCalendarPage(util,module.memoTitle,module.memoURL);

        module.goBackToCalendar(util);
    }


    /*
    * Step : GNB > me,메일,쪽지,메모,주소록,클라우드,오피스,가계부 클릭
    * Result : 개별 서비스 페이지로 이동됨
    * URL : http://me.naver.com"
    */

    @Test
    public void TC_06_GNB_탭_오피스_주소록_Test() throws Exception{

        util.click(By.className("contact"));

        module.assertCalendarPage(util,module.contactTitle,module.contactURL);

        module.goBackToCalendar(util);
    }


    /*
    * Step : GNB > me,메일,쪽지,메모,주소록,클라우드,오피스,가계부 클릭
    * Result : 개별 서비스 페이지로 이동됨
    * URL : http://me.naver.com"
    */

    @Test
    public void TC_07_GNB_탭_클라우드_Test() throws Exception{

        if(util.waitForIsNotVisible(By.xpath("//a[@class='btn_more']")))
        {
            util.waitForIsElementPresent(By.xpath("//li[@class='_ndrive ndrive']"));
            util.click(By.xpath("//li[@class='_ndrive ndrive']"));
        }
        else{
            util.click(By.xpath("//a[@class='btn_more']"));
            util.waitForIsElementPresent(By.xpath("//li[@class='_ndrive ndrive']"));
            util.click(By.xpath("//li[@class='_ndrive ndrive']"));
        }

        module.assertCalendarPage(util,module.cloudTitle,module.cloudURL);

        module.goBackToCalendar(util);
    }


    /*
    * Step : GNB > me,메일,쪽지,메모,주소록,클라우드,오피스,가계부 클릭
    * Result : 개별 서비스 페이지로 이동됨
    * URL : http://me.naver.com"
    */

    @Test
    public void TC_08_GNB_탭_오피스_Test() throws Exception{

        if(util.waitForIsNotVisible(By.xpath("//a[@class='btn_more']")))
        {
            util.waitForIsElementPresent(By.xpath("//li[@class='_office office']"));
            util.click(By.xpath("//li[@class='_office office']"));
        }
        else{
            util.click(By.xpath("//a[@class='btn_more']"));
            util.waitForIsElementPresent(By.xpath("//li[@class='_office office']"));
            util.click(By.xpath("//li[@class='_office office']"));
        }
        module.assertCalendarPage(util,module.officeTitle,module.officeURL);

        module.goBackToCalendar(util);
    }


    /*
    * Step : GNB > me,메일,쪽지,메모,주소록,클라우드,오피스,가계부 클릭
    * Result : 개별 서비스 페이지로 이동됨
    * URL : http://me.naver.com"
    */

    @Test
    public void TC_09_GNB_탭_가계부_Test() throws Exception{

        if(util.waitForIsNotVisible(By.xpath("//a[@class='btn_more']")))
        {
            util.waitForIsElementPresent(By.xpath("//li[@class='_moneybook moneybook']"));
            util.click(By.xpath("//li[@class='_moneybook moneybook']"));
        }
        else{
            util.click(By.xpath("//a[@class='btn_more']"));
            util.waitForIsElementPresent(By.xpath("//li[@class='_moneybook moneybook']"));
            util.click(By.xpath("//li[@class='_moneybook moneybook']"));
        }
        module.assertCalendarPage(util,module.moneybookTitle,module.moneybookURL);

        module.goBackToCalendar(util);
    }


    /*
    * Step : GNB > 프로필사진 클릭
    * Result : 프로필 레이어 노출됨
    */

    @Test
    public void TC_10_GNB_프로필_Test() throws Exception {
        // 프로필 사진 클릭
        // 사용자 메일에 사용자 아이디 포함하고 있는지 확인
        util.click(By.className("gnb_name"));
        util.waitForIsElementPresent(By.className("gnb_mail_address"));
        assertTrue(util.isElementPresent(By.className("gnb_mail_address")).getText().contains(TestIds.CalUser.getId()));

    }


    /*
    * Step : GNB > 프로필 > 내정보 클릭
    * Result : 내정보 페이지로 이동됨
    * URL : https://nid.naver.com/user2/help/myInfo.nhn?menu=home
    */

    @Test
    public void TC_11_GNB_프로필_내정보_Test() throws Exception{
        // 프로필 영역
        // 내 정보 클릭
        //util.click(By.className("gnb_name"));
        util.waitForIsElementPresent(By.className("gnb_info"));
        util.click(By.xpath("//li[@class='gnb_info']"));
        util.waitForIsElementPresent(By.className("sptxt"));

        util.waitForTitle("네이버 내정보");

        Title = util.getTitle();
        URL = util.getCurrentUrl();
        assertTrue(URL.contains("https://nid.naver.com/user2/help/myInfo.nhn?menu=home"));
        assertTrue(Title.contains("네이버 내정보"));

        util.goBack();
        util.waitForTitle(module.calTitle);
    }

    /*
    * Step : GNB > 프로필 > 보안설정 클릭
    * Result : 보안 설정 페이지로 이동 됨
    * URL : https://nid.naver.com/user2/help/myInfo.nhn?m=viewSecurity&menu=security
    */

    @Test
    public void TC_12_GNB_프로필_보안설정_Test() throws Exception{
        // 프로필 영역
        // 내 정보 클릭
        util.click(By.className("gnb_name"));
        util.click(By.className("gnb_secure"));

        util.waitForTitle("보안설정 : 네이버 내정보");

        Title = util.getTitle();
        URL = util.getCurrentUrl();

        assertTrue(URL.contains("https://nid.naver.com/user2/help/myInfo.nhn?m=viewSecurity&menu=security"));
        assertTrue(Title.contains("보안설정 : 네이버 내정보"));
        util.goBack();
        util.waitForTitle(module.calTitle);
    }

    /*
    * Step : GNB > 프로필 > 내 블로그 클릭
    * Result : 내 블로그로 이동 됨
    * URL : http://blog.naver.com/사용자계정
    */

    @Test
    public void TC_13_GNB_프로필_내블로그_Test() throws Exception{
        // 프로필 영역
        // 내 정보 클릭
        util.click(By.className("gnb_name"));
        util.click(By.className("gnb_blog"));

        util.waitForTitle(TestIds.CalUser.getId()+"님의블로그 : 네이버 블로그");


        Title = util.getTitle();
        URL = util.getCurrentUrl();

        assertTrue(URL.contains("http://blog.naver.com/"+TestIds.CalUser.getId()));
        assertTrue(Title.contains(TestIds.CalUser.getId()+"님의블로그 : 네이버 블로그"));

        util.goBack();
        util.waitForTitle(module.calTitle);
    }


    /*
    * Step : GNB > 프로필 > 가입한 카페 클릭
    * Result : 카페로 이동 됨
    * URL : http://section.cafe.naver.com/
    */

    @Test
    public void TC_14_GNB_프로필_가입한카페_Test() throws Exception{
        // 프로필 영역
        // 내 정보 클릭
        util.click(By.className("gnb_name"));
        util.click(By.className("gnb_cafe"));
        //util.waitForIsElementPresent(By.className("gnb_notice_all"));

        Title = util.getTitle();
        URL = util.getCurrentUrl();
        assertTrue(URL.contains("http://section.cafe.naver.com/"));
        assertTrue(Title.contains("네이버 카페"));

        util.goBack();
        util.waitForTitle(module.calTitle);
    }


    /*
    * Step : GNB > 프로필 > NPAY 클릭
    * Result : NPAY로 이동됨
    * URL : https://order.pay.naver.com/home
    */

    @Test
    public void TC_15_GNB_프로필_NPAY_Test() throws Exception{
        // 프로필 영역
        // 내 정보 클릭
        util.click(By.className("gnb_name"));
        util.click(By.className("gnb_pay"));

        Title = util.getTitle();
        URL = util.getCurrentUrl();
        assertTrue(URL.contains("https://order.pay.naver.com/home"));
        assertTrue(Title.contains("네이버페이"));

        util.goBack();
        util.waitForTitle(module.calTitle);
    }


    /*
    * Step : GNB > 프로필 > 로그아웃 클릭
    * Result : 로그아웃되고 캘린더 페이지로 이동됨
    * URL : https://calendar.naver.com/section.nhn
    */

    @Test
    public void TC_16_GNB_프로필_로그아웃_Test() throws Exception{
        // 프로필 영역
        // 내 정보 클릭
        util.click(By.className("gnb_name"));
        util.click(By.id("gnb_logout_button"));

        util.waitForTitle("네이버 캘린더 :: 친절한 나의 스케줄 매니저");

        Title = util.getTitle();
        URL = util.getCurrentUrl();
        assertTrue(URL.contains("https://calendar.naver.com/section.nhn"));
        assertTrue(Title.contains("네이버 캘린더 :: 친절한 나의 스케줄 매니저"));
    }



    /*
    * Step : GNB > 알림 아이콘 클릭
    * Result : 알림 목록 노출됨
    */

    @Test
    public void TC_17_GNB_알림_Test() throws Exception{
        //로그아웃 동작 이후에 재 로그인
        module.로그인(util, TestIds.CalUser.getId(), TestIds.CalUser.getPw());
        // 알림 목록 아이콘 확인
        util.click(By.className("gnb_notice_li"));
        // 알림 유무 확인

        // 알림이 있을때
        // 알림 개수, 삭제, 링크 정상 동작 확인
        // 읽은 알림 삭제 버튼,모두 삭제 버튼 확인

        // 알림이 없을때
        // 알림 없음 문구 노출 확인
        util.waitForIsElementPresent(By.className("svc_msg_box"));
    }

    /*
    * Step : GNB > 서비스 아이콘 클릭
    * Result : 다를 서비스 목록 노출됨
    */

    @Test
    public void TC_18_GNB_서비스아이콘_Test() throws Exception{

        // 알림 목록 아이콘 확인
        util.click(By.className("gnb_service_li"));
    }
}
