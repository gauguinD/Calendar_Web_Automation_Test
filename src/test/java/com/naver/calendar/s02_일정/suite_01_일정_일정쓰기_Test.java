package com.naver.calendar.s02_일정;

import main.TestIds;
import main.Testcase;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;


public class suite_01_일정_일정쓰기_Test extends Testcase {

    public String Title = null;
    public String URL = null;
    public String LunarDate = null;
    public String repeatStartDate;
    public String repeatEndDate;

    public String dayIndex = null;
    public String Subject = null;
    //public String NumOfEvent = null;
    public int NumOfEvent = 0;


    /*
    * Step : 로그인 > 해당 계정으로 로그인
    * Result : 해당하는 계정으로 로그인 됨
     */
    @Test
    public void TC_00_일정_로그인_Test() throws Exception {
        module.로그인(util, TestIds.CalUser.getId(), TestIds.CalUser.getPw());
    }

    /*
     * Step : 일정 > 일정,약속쓰기 클릭
     * Result : 일정쓰기 페이지로 이동됨
     */
    @Test
    public void TC_01_일정_일정쓰기_Test() throws Exception {
        util.click(By.xpath("//span[contains(text(),'약속쓰기')]"));
        assertTrue(util.isElementPresent(By.linkText("캘린더로 돌아가기")).isDisplayed());

        util.click(By.xpath("//button[@class ='btn_sys pos_save']"));
        util.waitForIsElementPresent(By.xpath("//button[contains(@class,'_go_task type_schedule todo')]"));
    }

    /*
    * Step : 일정삭제 > 시간일정 삭제
    * Result : 해당하는 일정 삭제 됨
    */
    @Test
    public void TC_02_일정_일정쓰기_일정삭제_Test() throws Exception {
        int tdValue = 0;
        int divValue = 0;

        URL = module.calURL + "#{\"sSection\":\"scheduleMain\",\"oParameter\":{\"sViewType\":\"month\",\"sDate\":\"" + module.TodayDate2() + "\"}}";
        util.get(URL);

        //선택한 날짜의 dayindex 를 가져옴
        dayIndex = util.waitForIsElementPresent(By.xpath("//strong[contains(text(),'" + module.StartDate.substring(8, 10) + "')]")).getAttribute("dayindex");
        //util.printLog(dayIndex);

        //선택한 날짜에 일정이 2개 이상일 경우 +가 노출되는것에 대한 예외상황
        //NumOfEvent = 해당 일자에 일정의 개수
        if(util.isElementPresentNotExist(By.xpath("//a[contains(@key,'"+dayIndex+"')][contains(@class,'_more_schedule')]")))
        {
            util.isElementPresent(By.xpath("//a[contains(@key,'"+dayIndex+"')][contains(@class,'_more_schedule')]")).click();
            NumOfEvent = util.getXpathCount(By.xpath("html/body/div[5]/div/ul/li"));
            //util.printLog(NumOfEvent);

            //선택한 날짜에 일정이 2개 이상일 경우 일치하는 제목 찾아서 클릭
            //제목이 동일한 일정이 있을경우???
            for(int i = 1; i < NumOfEvent+1; i++)
            {
                util.printLog(module.contents);
                Subject = util.waitForIsElementPresent(By.xpath("html/body/div[5]/div/ul/li["+i+"]/a")).getText();
                util.printLog(i+"번 일정의 제목은 : "+Subject+" 입니다.");
                //util.scrollToElement(By.xpath("html/body/div[5]/div/ul/li["+i+"]/a"));
                if(module.contents == Subject)
                {
                    util.printLog("제목 : "+Subject+" 일정을 삭제합니다");
                    util.waitForIsElementPresent(By.xpath("html/body/div[5]/div/ul/li["+i+"]/a")).click();
                }
            }
        }
        else
            //일정 간편보기 클릭
            util.click(By.xpath("//a[contains(text(),'" + module.contents + "')]"));

        //일정 간편보기에서 삭제 클릭
        util.waitForIsElementPresent(By.xpath("//button[@class='_del_btn btn_default btn_default_v1']")).click();

        //확인 클릭
        util.getAlert().accept();

    }


    /*
     * Step : 일정쓰기 > 시간일정 생성
     * Result : 해당하는 날짜에 시간 일정 생성 됨
     * URL : http://calendar.naver.com/#{"sSection":"scheduleMain","oParameter":{"sViewType":"month","sDate":""+module.StartDate+"\"}}";
     */
    @Test
    public void TC_01_일정_일정쓰기_시간일정_Test() throws Exception {

        util.click(By.xpath("//span[contains(text(),'약속쓰기')]"));
        assertTrue(util.isElementPresent(By.linkText("캘린더로 돌아가기")).isDisplayed());

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

        util.click(By.xpath("//button[@class ='btn_sys pos_save']"));
        util.waitForIsElementPresent(By.xpath("//button[contains(@class,'_go_task type_schedule todo')]"));
    }

    /*
    * Step : 일정삭제 > 시간일정 삭제
    * Result : 해당하는 일정 삭제 됨
    */
    @Test
    public void TC_02_일정_일정쓰기_시간일정삭제_Test() throws Exception {
        int tdValue = 0;
        int divValue = 0;

        URL = module.calURL + "#{\"sSection\":\"scheduleMain\",\"oParameter\":{\"sViewType\":\"month\",\"sDate\":\"" + module.StartDate + "\"}}";
        util.get(URL);

        //선택한 날짜의 dayindex 를 가져옴
        dayIndex = util.waitForIsElementPresent(By.xpath("//strong[contains(text(),'" + module.StartDate.substring(8, 10) + "')]")).getAttribute("dayindex");
        util.printLog(dayIndex);

        //선택한 날짜에 일정이 2개 이상일 경우 +가 노출되는것에 대한 예외상황
        //NumOfEvent = 해당 일자에 일정의 개수
        if(util.isElementPresentNotExist(By.xpath("//a[contains(@key,'"+dayIndex+"')][contains(@class,'_more_schedule')]")))
        {
            util.isElementPresent(By.xpath("//a[contains(@key,'"+dayIndex+"')][contains(@class,'_more_schedule')]")).click();
            NumOfEvent = util.getXpathCount(By.xpath("html/body/div[5]/div/ul/li"));
            //util.printLog(NumOfEvent);

            //선택한 날짜에 일정이 2개 이상일 경우 일치하는 제목 찾아서 클릭
            //제목이 동일한 일정이 있을경우???
            for(int i = 1; i < NumOfEvent+1; i++)
            {
                util.printLog(module.contents);
                Subject = util.waitForIsElementPresent(By.xpath("html/body/div[5]/div/ul/li["+i+"]/a")).getText();
                util.printLog(i+"번 일정의 제목은 : "+Subject+" 입니다.");
                //util.scrollToElement(By.xpath("html/body/div[5]/div/ul/li["+i+"]/a"));
                if(module.contents == Subject)
                {
                    util.printLog("제목 : "+Subject+" 일정을 삭제합니다");
                    util.waitForIsElementPresent(By.xpath("html/body/div[5]/div/ul/li["+i+"]/a")).click();
                }
            }
        }
        else
            //일정 간편보기 클릭
            util.click(By.xpath("//a[contains(text(),'" + module.contents + "')]"));

        //일정 간편보기에서 삭제 클릭
        util.waitForIsElementPresent(By.xpath("//button[@class='_del_btn btn_default btn_default_v1']")).click();

        //확인 클릭
        util.getAlert().accept();

    }

    /*
    * Step : 일정쓰기 > 시간 종일 일정 생성
    * Result : 해당하는 날짜에 시간 종일 일정 생성 됨
    * URL : http://calendar.naver.com/#{"sSection":"scheduleMain","oParameter":{"sViewType":"month","sDate":""+module.StartDate+"\"}}";
    */
    @Test
    public void TC_02_일정_일정쓰기_시간종일일정_Test() throws Exception {

        util.click(By.xpath("//span[contains(text(),'약속쓰기')]"));
        assertTrue(util.isElementPresent(By.linkText("캘린더로 돌아가기")).isDisplayed());

        //종일 일정쓰기 설정 되어 있는지 확인
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
        util.isElementPresent(By.id("end_date")).sendKeys(module.EndTimeDate);

        util.click(By.xpath("//button[@class ='btn_sys pos_save']"));
        util.waitForIsElementPresent(By.xpath("//button[contains(@class,'_go_task type_schedule todo')]"));
    }

    @Test
    public void TC_03_일정_일정쓰기_시간일정_Assert_Test() throws Exception {
        URL = module.calURL + "#{\"sSection\":\"scheduleMain\",\"oParameter\":{\"sViewType\":\"month\",\"sDate\":\"" + module.StartDate + "\"}}";
        util.get(URL);

        util.waitForIsElementPresent(By.xpath("//a[contains(text(),'" + module.contents + "')]"));


        if (util.isElementPresent(By.xpath("//a[contains(text(),'" + module.contents + "')]")).isDisplayed()) {
            util.click(By.xpath("//a[contains(text(),'" + module.contents + "')]"));
            util.printLog("해당하는 날짜에 시간일정 \n 제목:" + module.contents + " 일정이 존재합니다");
            util.waitForIsElementPresent(By.className("_modify_text"));
        } else
            util.printLog("해당하는 날짜에 해당하는 일정이 존재하지 않습니다");
    }



    /*
     * Step : 일정쓰기 > 종일일정 생성
     * Result : 해당하는 날짜에 종일 일정 생성 됨
     * URL : http://calendar.naver.com/#{"sSection":"scheduleMain","oParameter":{"sViewType":"month","sDate":""+module.StartDate+"\"}}";
     */
    @Test
    public void TC_04_일정_일정쓰기_종일일정_Test() throws Exception {

        util.click(By.xpath("//span[contains(text(),'약속쓰기')]"));
        assertTrue(util.isElementPresent(By.linkText("캘린더로 돌아가기")).isDisplayed());

        //종일 일정쓰기 설정 되어 있는지 확인
        if (!(util.isElementPresent(By.xpath("//a[@class='_set_timezone change_time']")).isDisplayed())) {
        } else {
            util.isElementPresent(By.id("ch1_1")).click();
        }

        util.waitForIsElementPresent(By.xpath("//*[@id='holder']/div/div[1]/a[2]"));
        //제목 입력
        util.isElementPresent(By.id("tx0_0")).sendKeys(module.contents);

        //시작 날짜 입력
        util.isElementPresent(By.id("start_date")).clear();
        util.isElementPresent(By.id("start_date")).sendKeys(module.StartDate);

        //종료 날짜 입력
        util.isElementPresent(By.id("end_date")).clear();
        util.isElementPresent(By.id("end_date")).sendKeys(module.EndDate);

        //저장하기
        util.click(By.xpath("//button[@class ='btn_sys pos_save']"));
        util.waitForIsElementPresent(By.xpath("//button[contains(@class,'_go_task type_schedule todo')]"));
    }

    @Test
    public void TC_05_일정_일정쓰기_종일일정_Assert_Test() throws Exception {
        URL = module.calURL + "#{\"sSection\":\"scheduleMain\",\"oParameter\":{\"sViewType\":\"month\",\"sDate\":\"" + module.StartDate + "\"}}";
        util.get(URL);

        util.waitForIsElementPresent(By.xpath("//a[contains(text(),'" + module.contents + "')]"));


        if (util.isElementPresent(By.xpath("//a[contains(text(),'" + module.contents + "')]")).isDisplayed()) {
            util.click(By.xpath("//a[contains(text(),'" + module.contents + "')]"));
            util.printLog("해당하는 날짜에 종일일정 \n 제목:" + module.contents + " 일정이 존재합니다");
            util.waitForIsElementPresent(By.className("_modify_text"));
        } else
            util.printLog("해당하는 날짜에 해당하는 일정이 존재하지 않습니다");
    }


    /*
    * Step : 일정쓰기 > 음력일정 생성
    * Result : 해당하는 날짜에 음력 일정 생성 됨
    * URL : http://calendar.naver.com/#{"sSection":"scheduleMain","oParameter":{"sViewType":"month","sDate":""+module.StartDate+"\"}}";
    */
    @Test
    public void TC_06_일정_일정쓰기_음력일정_Test() throws Exception {
        util.click(By.xpath("//span[contains(text(),'약속쓰기')]"));
        assertTrue(util.isElementPresent(By.linkText("캘린더로 돌아가기")).isDisplayed());

        //종일 일정쓰기 인지 확인
        if (!(util.isElementPresent(By.xpath("//a[@class='_set_timezone change_time']")).isDisplayed())) {
        } else {
            util.isElementPresent(By.id("ch1_1")).click();
        }

        //음력으로 설정 되어 있는지 확인
        if (util.isElementPresent(By.xpath("//p[@class='_converted_solar_date time_default no_view']")).isDisplayed()) {
            util.printLog("음력입니다");
        } else {
            util.isElementPresent(By.xpath("//div[@class='selectbox-label']")).click();
            util.isElementPresent(By.xpath("//li[contains(text(),'음력')]")).click();
        }

        util.waitForIsElementPresent(By.xpath("//*[@id='holder']/div/div[1]/a[2]"));

        //제목 입력
        util.isElementPresent(By.id("tx0_0")).sendKeys(module.contents);

        //시작 날짜 입력
        util.isElementPresent(By.id("start_date")).clear();
        util.isElementPresent(By.id("start_date")).sendKeys(module.StartDate);

        //종료 날짜 입력
        util.isElementPresent(By.id("end_date")).clear();
        util.isElementPresent(By.id("end_date")).sendKeys(module.EndDate);

        //음력 날짜 저장
        //지정한 양력날짜가 아닌 새로운 양력날짜로 저장 되기 때문에 LunarDate로 새로 저장
        //나중에 Assert할때도 해당 정보 전달
        String str;
        str = util.isElementPresent(By.xpath("//p[@class='_converted_solar_date time_default no_view']")).getText();
        LunarDate = str.substring(5).replace(".", "-");

        //저장하기
        util.click(By.xpath("//button[@class ='btn_sys pos_save']"));
        util.waitForIsElementPresent(By.xpath("//button[contains(@class,'_go_task type_schedule todo')]"));
    }

    @Test
    public void TC_07_일정_일정쓰기_음력일정_Assert_Test() throws Exception {
        URL = module.calURL + "#{\"sSection\":\"scheduleMain\",\"oParameter\":{\"sViewType\":\"month\",\"sDate\":\"" + LunarDate + "\"}}";
        util.get(URL);

        util.waitForIsElementPresent(By.xpath("//a[contains(text(),'" + module.contents + "')]"));


        if (util.isElementPresent(By.xpath("//a[contains(text(),'" + module.contents + "')]")).isDisplayed()) {
            util.click(By.xpath("//a[contains(text(),'" + module.contents + "')]"));
            util.printLog("해당하는 날짜에 음력일정 \n 제목:" + module.contents + " 일정이 존재합니다");
            util.waitForIsElementPresent(By.className("_modify_text"));
        } else
            util.printLog("해당하는 날짜에 해당하는 일정이 존재하지 않습니다");
    }


    /*
    * Step : 일정쓰기 > 공유숨김 일정 생성
    * Result : 해당하는 날짜에 공유숨김 일정 생성 됨
    * URL : http://calendar.naver.com/#{"sSection":"scheduleMain","oParameter":{"sViewType":"month","sDate":""+module.StartDate+"\"}}";
    */
    @Test
    public void TC_14_일정_일정쓰기_공유숨김일정_Test() throws Exception {

        util.click(By.xpath("//span[contains(text(),'약속쓰기')]"));
        assertTrue(util.isElementPresent(By.linkText("캘린더로 돌아가기")).isDisplayed());

        //제목 입력
        util.isElementPresent(By.id("tx0_0")).sendKeys(module.contents);

        //공유캘린더 선택
        util.isElementPresent(By.id("calendar_selectbox")).click();
        util.waitForIsElementPresent(By.xpath("//li[contains(text(),'공유캘린더')]")).click();

        //공개 - 비공개 설정
        util.isElementPresent(By.id("public_private")).click();

        //시작 날짜 입력
        util.isElementPresent(By.id("start_date")).clear();
        util.isElementPresent(By.id("start_date")).sendKeys(module.StartDate);

        util.click(By.xpath("//button[@class ='btn_sys pos_save']"));

        //알림메일 발송하지 않음 체크하고 확인
        util.click(By.id("_wpageTMP8_send_to_shared_nobody"));
        util.click(By.xpath("//button[@class ='_ok normal']"));

        util.waitForIsElementPresent(By.xpath("//button[contains(@class,'_go_task type_schedule todo')]"));
    }

    @Test
    public void TC_15_일정_일정쓰기_공유숨김일정_Assert_Test() throws Exception {
        URL = module.calURL + "#{\"sSection\":\"scheduleMain\",\"oParameter\":{\"sViewType\":\"month\",\"sDate\":\"" + module.StartDate + "\"}}";
        util.get(URL);

        util.waitForIsElementPresent(By.xpath("//a[contains(text(),'" + module.contents + "')]"));

        if (util.isElementPresentNotExist(By.xpath("//a[contains(text(),'" + module.contents + "')]"))) {
            util.click(By.xpath("//a[contains(text(),'" + module.contents + "')]"));
            util.printLog("해당하는 날짜에 일정 \n 제목:" + module.contents + " 일정이 존재합니다");
            util.waitForIsElementPresent(By.className("_modify_text"));
        } else
            util.printLog("해당하는 날짜에 해당하는 일정이 존재하지 않습니다");
        //비공개 아이콘 노출 확인
        assertTrue(util.waitForIsElementPresent(By.className("ico_lock")).isDisplayed());

        //참가자 보기 클릭
        if(util.isElementPresentNotExist(By.xpath("//a[@class='btn_sy _view_attendee']"))) {
            util.isElementPresent(By.xpath("//a[@class='btn_sy _view_attendee']")).click();
            //해당하는 참가자 있는지 확인
            if (util.waitForIsElementPresent(By.xpath("//span[@class='txt_member ellipsis' and contains(text(),'" + module.Attendee + "')]")).isDisplayed()) {
                util.printLog("해당하는 날짜의 약속에 참가자:" + module.Attendee + " 가 있습니다.");
            } else
                util.printLog("해당하는 날짜의 약속에 참가자가 존재하지 않습니다");
        }
    }

    /*
    * Step : 일정쓰기 > 공개 일정 생성
    * Result : 해당하는 날짜에 공개 일정 생성 됨
    * URL : http://calendar.naver.com/#{"sSection":"scheduleMain","oParameter":{"sViewType":"month","sDate":""+module.StartDate+"\"}}";
    */
    @Test
    public void TC_16_일정_일정쓰기_공개일정_Test() throws Exception {

        util.click(By.xpath("//span[contains(text(),'약속쓰기')]"));
        assertTrue(util.isElementPresent(By.linkText("캘린더로 돌아가기")).isDisplayed());

        //제목 입력
        util.isElementPresent(By.id("tx0_0")).sendKeys(module.contents);

        //공유캘린더 선택
        util.isElementPresent(By.id("calendar_selectbox")).click();
        util.waitForIsElementPresent(By.xpath("//li[contains(text(),'공개캘린더')]")).click();

        //시작 날짜 입력
        util.isElementPresent(By.id("start_date")).clear();
        util.isElementPresent(By.id("start_date")).sendKeys(module.StartDate);

        util.click(By.xpath("//button[@class ='btn_sys pos_save']"));
        util.waitForIsElementPresent(By.xpath("//button[contains(@class,'_go_task type_schedule todo')]"));
    }

    @Test
    public void TC_17_일정_일정쓰기_공개일정_Assert_Test() throws Exception {
        URL = module.calURL + "#{\"sSection\":\"scheduleMain\",\"oParameter\":{\"sViewType\":\"month\",\"sDate\":\"" + module.StartDate + "\"}}";
        util.get(URL);

        util.waitForIsElementPresent(By.xpath("//a[contains(text(),'" + module.contents + "')]"));

        if (util.isElementPresentNotExist(By.xpath("//a[contains(text(),'" + module.contents + "')]"))) {
            util.click(By.xpath("//a[contains(text(),'" + module.contents + "')]"));
            util.printLog("해당하는 날짜에 일정 \n 제목:" + module.contents + " 일정이 존재합니다");
            util.waitForIsElementPresent(By.className("_modify_text"));
        } else
            util.printLog("해당하는 날짜에 해당하는 일정이 존재하지 않습니다");

        //참가자 보기 클릭
        if(util.isElementPresentNotExist(By.xpath("//a[@class='btn_sy _view_attendee']"))) {
            util.isElementPresent(By.xpath("//a[@class='btn_sy _view_attendee']")).click();
            //해당하는 참가자 있는지 확인
            if (util.waitForIsElementPresent(By.xpath("//span[@class='txt_member ellipsis' and contains(text(),'" + module.Attendee + "')]")).isDisplayed()) {
                util.printLog("해당하는 날짜의 약속에 참가자:" + module.Attendee + " 가 있습니다.");
            } else
                util.printLog("해당하는 날짜의 약속에 참가자가 존재하지 않습니다");
        }
    }

    /*
    * Step : 일정쓰기 > 스티커 일정 생성
    * Result : 해당하는 날짜에 스티커 일정 생성 됨
    * URL : http://calendar.naver.com/#{"sSection":"scheduleMain","oParameter":{"sViewType":"month","sDate":""+module.StartDate+"\"}}";
    */
    @Test
    public void TC_18_일정_일정쓰기_스티커일정_Test() throws Exception {

        util.click(By.xpath("//span[contains(text(),'약속쓰기')]"));
        assertTrue(util.isElementPresent(By.linkText("캘린더로 돌아가기")).isDisplayed());

        //제목 입력
        util.isElementPresent(By.id("tx0_0")).sendKeys(module.contents);

        //큰스티커 확인
        util.click(By.className("h_cont"));
        module.StickerSelect(util, module,1);

        //작은스티커 확인
        util.click(By.xpath("//button[@class='btn_select _small']"));
        module.StickerSelect(util, module,2);

        //시작 날짜 입력
        util.isElementPresent(By.id("start_date")).clear();
        util.isElementPresent(By.id("start_date")).sendKeys(module.StartDate);

        util.click(By.xpath("//button[@class ='btn_sys pos_save']"));
        util.waitForIsElementPresent(By.xpath("//button[contains(@class,'_go_task type_schedule todo')]"));
    }

    /*
    * Step : 일정쓰기 > 범주 일정 생성
    * Result : 해당하는 날짜에 범주 설정된 일정 생성 됨
    * URL : http://calendar.naver.com/#{"sSection":"scheduleMain","oParameter":{"sViewType":"month","sDate":""+module.StartDate+"\"}}";
    */
    @Test
    public void TC_20_일정_일정쓰기_범주일정_Test() throws Exception {

        util.click(By.xpath("//span[contains(text(),'약속쓰기')]"));
        assertTrue(util.isElementPresent(By.linkText("캘린더로 돌아가기")).isDisplayed());

        //제목 입력
        util.isElementPresent(By.id("tx0_0")).sendKeys(module.contents);

        //범주 클릭
        module.eventColor(util);

        //시작 날짜 입력
        util.isElementPresent(By.id("start_date")).clear();
        util.isElementPresent(By.id("start_date")).sendKeys(module.StartDate);

        util.click(By.xpath("//button[@class ='btn_sys pos_save']"));
        util.waitForIsElementPresent(By.xpath("//button[contains(@class,'_go_task type_schedule todo')]"));
    }

    /*
    * Step : 일정쓰기 > 이미지 첨부 일정 생성
    * Result : 해당하는 날짜에 이미지 첨부된 일정 생성 됨
    * URL : http://calendar.naver.com/#{"sSection":"scheduleMain","oParameter":{"sViewType":"month","sDate":""+module.StartDate+"\"}}";
    */
    @Test
    public void TC_21_일정_일정쓰기_첨부일정_Test() throws Exception {

        util.click(By.xpath("//span[contains(text(),'약속쓰기')]"));
        assertTrue(util.isElementPresent(By.linkText("캘린더로 돌아가기")).isDisplayed());

        //제목 입력
        util.isElementPresent(By.id("tx0_0")).sendKeys(module.contents);

        //이미지 업로드 클릭
        //util.click(By.xpath("//input[@class='_input_file input_file']"));
        module.uploadImage(util);
        module.uploadFile(util);

        //시작 날짜 입력
        util.isElementPresent(By.id("start_date")).clear();
        util.isElementPresent(By.id("start_date")).sendKeys(module.StartDate);

        util.click(By.xpath("//button[@class ='btn_sys pos_save']"));
        util.waitForIsElementPresent(By.xpath("//button[contains(@class,'_go_task type_schedule todo')]"));
    }

    @Test
    public void TC_19_일정_일정쓰기_스티커일정_Assert_Test() throws Exception {
        URL = module.calURL + "#{\"sSection\":\"scheduleMain\",\"oParameter\":{\"sViewType\":\"month\",\"sDate\":\"" + module.StartDate + "\"}}";
        util.get(URL);

        util.waitForIsElementPresent(By.xpath("//a[contains(text(),'" + module.contents + "')]"));

        if (util.isElementPresentNotExist(By.xpath("//a[contains(text(),'" + module.contents + "')]"))) {
            util.click(By.xpath("//a[contains(text(),'" + module.contents + "')]"));
            util.printLog("해당하는 날짜에 일정 \n 제목:" + module.contents + " 일정이 존재합니다");
            util.waitForIsElementPresent(By.className("_modify_text"));
        } else
            util.printLog("해당하는 날짜에 해당하는 일정이 존재하지 않습니다");

        //참가자 보기 클릭
        if(util.isElementPresentNotExist(By.xpath("//a[@class='btn_sy _view_attendee']"))) {
            util.isElementPresent(By.xpath("//a[@class='btn_sy _view_attendee']")).click();
            //해당하는 참가자 있는지 확인
            if (util.waitForIsElementPresent(By.xpath("//span[@class='txt_member ellipsis' and contains(text(),'" + module.Attendee + "')]")).isDisplayed()) {
                util.printLog("해당하는 날짜의 약속에 참가자:" + module.Attendee + " 가 있습니다.");
            } else
                util.printLog("해당하는 날짜의 약속에 참가자가 존재하지 않습니다");
        }
    }


    /*
    * Step : 일정쓰기 > 기본일정 생성
    * Result : 해당하는 날짜에 기본 일정 생성 됨
    * URL : http://calendar.naver.com/#{"sSection":"scheduleMain","oParameter":{"sViewType":"month","sDate":""+module.StartDate+"\"}}";
    */
    @Test
    public void TC_16_일정_일정쓰기_기본일정_Test() throws Exception {

        util.click(By.xpath("//span[contains(text(),'약속쓰기')]"));
        assertTrue(util.isElementPresent(By.linkText("캘린더로 돌아가기")).isDisplayed());

        //제목 입력
        util.isElementPresent(By.id("tx0_0")).sendKeys(module.contents);

        //공유캘린더 선택
        util.isElementPresent(By.id("calendar_selectbox")).click();
        util.waitForIsElementPresent(By.xpath("//li[contains(text(),'공유캘린더')]")).click();

        //시작 날짜 입력
        util.isElementPresent(By.id("start_date")).clear();
        util.isElementPresent(By.id("start_date")).sendKeys(module.StartDate);

        util.click(By.xpath("//button[@class ='btn_sys pos_save']"));

        //알림메일 발송하지 않음 체크하고 확인
        util.click(By.id("_wpageTMP8_send_to_shared_nobody"));
        util.click(By.xpath("//button[@class ='_ok normal']"));

        util.waitForIsElementPresent(By.xpath("//button[contains(@class,'_go_task type_schedule todo')]"));
    }

    /*
     * Step : 일정삭제 > 시간일정 삭제
     * Result : 해당하는 일정 삭제 됨
     */
    @Test
    public void TC_97_일정_일정쓰기_시간일정삭제_Test() throws Exception {
        int tdValue = 0;
        int divValue = 0;

        URL = module.calURL + "#{\"sSection\":\"scheduleMain\",\"oParameter\":{\"sViewType\":\"month\",\"sDate\":\"" + module.StartDate + "\"}}";
        util.get(URL);

        //선택한 날짜의 dayindex 를 가져옴
        dayIndex = util.waitForIsElementPresent(By.xpath("//strong[contains(text(),'" + module.StartDate.substring(8, 10) + "')]")).getAttribute("dayindex");
        util.printLog(dayIndex);

        //선택한 날짜에 일정이 2개 이상일 경우 +가 노출되는것에 대한 예외상황
        //NumOfEvent = 해당 일자에 일정의 개수
        if(util.isElementPresentNotExist(By.xpath("//a[contains(@key,'"+dayIndex+"')][contains(@class,'_more_schedule')]")))
        {
            util.isElementPresent(By.xpath("//a[contains(@key,'"+dayIndex+"')][contains(@class,'_more_schedule')]")).click();
            NumOfEvent = util.getXpathCount(By.xpath("html/body/div[5]/div/ul/li"));
            //util.printLog(NumOfEvent);

            //선택한 날짜에 일정이 2개 이상일 경우 일치하는 제목 찾아서 클릭
            //제목이 동일한 일정이 있을경우???
            for(int i = 1; i < NumOfEvent+1; i++)
                {
                    util.printLog(module.contents);
                    Subject = util.waitForIsElementPresent(By.xpath("html/body/div[5]/div/ul/li["+i+"]/a")).getText();
                    util.printLog(i+"번 일정의 제목은 : "+Subject+" 입니다.");
                    //util.scrollToElement(By.xpath("html/body/div[5]/div/ul/li["+i+"]/a"));
                    if(module.contents == Subject)
                    {
                        util.printLog("제목 : "+Subject+" 일정을 삭제합니다");
                        util.waitForIsElementPresent(By.xpath("html/body/div[5]/div/ul/li["+i+"]/a")).click();
                    }
                }
            }
        else
            //일정 간편보기 클릭
            util.click(By.xpath("//a[contains(text(),'" + module.contents + "')]"));

        //일정 간편보기에서 삭제 클릭
        util.waitForIsElementPresent(By.xpath("//button[@class='_del_btn btn_default btn_default_v1']")).click();

        //확인 클릭
        util.getAlert().accept();

    }


    @Test
    public void TC_99_일정_일정쓰기_시간일정삭제_old_Test() throws Exception {
        int tdValue = 0;
        int divValue = 0;

        URL = module.calURL + "#{\"sSection\":\"scheduleMain\",\"oParameter\":{\"sViewType\":\"month\",\"sDate\":\"" + module.StartDate + "\"}}";
        util.get(URL);
        //util.printLog("\nDate : "+module.StartDate);

        //선택한 날짜의 dayindex 를 가져옴
        dayIndex = util.waitForIsElementPresent(By.xpath("//strong[contains(text(),'" + module.StartDate.substring(8, 10) + "')]")).getAttribute("dayindex");
        util.printLog(dayIndex);

        //dayIndex값 보정
        //해당하는  xpath 값을 가져오기 위한 계산식
        //달력상에서 시작 요일이 일요일일때,월요일일때 계산식이 다름
        //이달의 1일이 일요일일경우 dayindex = date-1
        //이달의 1일이 월요일일경우 dayindex = date
        //이달의 1일이 화요일인경우 dayindex = date+1
        //이달의 1일이 수요일인경우 dayindex = date+2
        //이달의 1일이 목요일인경우 dayindex = date+3
        //이달의 1일이 금요일인경우 dayindex = date+4
        //이달의 1일이 토요일인경우 dayindex = date+5
        //divValue = (Integer.parseInt(dayIndex)/7)+1;
        //tdValue = (Integer.parseInt(dayIndex)%7)+1;


        //선택한 날짜에 일정이 2개 이상일 경우 +가 노출되는것에 대한 예외상황
        //NumOfEvent = 해당 일자에 일정의 개수

        if(util.isElementPresentNotExist(By.xpath("//a[contains(@key,'"+dayIndex+"')][contains(@class,'_more_schedule')]")))
        {
            /*NumOfEvent = util.waitForIsElementPresent(By.xpath("//a[contains(@key,'"+dayIndex+"')]")).getText().toString();
            util.printLog(NumOfEvent);
            //일정이 10개 이상일경우
            if(NumOfEvent.length() == 3)
                NumOfEvent = NumOfEvent.substring(0,2);
            //일정이 9개 이하일경우
            else
                NumOfEvent = NumOfEvent.substring(0,1);

            */

            util.isElementPresent(By.xpath("//a[contains(@key,'"+dayIndex+"')][contains(@class,'_more_schedule')]")).click();
            NumOfEvent = util.getXpathCount(By.xpath("html/body/div[5]/div/ul/li"));
            //util.printLog(NumOfEvent);

            //선택한 날짜에 일정이 2개 이상일 경우 일치하는 제목 찾아서 클릭
            //제목이 동일한 일정이 있을경우???
            //
            for(int i = 1; i < NumOfEvent; i++)
            {
                ///html/body/div[5]/div/ul/li[1]
                //*[@id="month_frame"]/div/div/div[1]/div[4]/table[2]/tbody/tr[2]/td[5]/div/div/a
                //Subject = util.isElementPresent(By.xpath("//*[@id='month_frame']/div/div/div[1]/div["+divValue+"]/table[2]/tbody/tr["+i+"]/td["+ tdValue +"]/div/div/a")).getText();
                util.printLog(module.contents);
                Subject = util.waitForIsElementPresent(By.xpath("html/body/div[5]/div/ul/li["+i+"]/a")).getText();
                util.printLog(i+"번 일정의 제목은 : "+Subject);
                if(module.contents == Subject)
                {
                    util.waitForIsElementPresent(By.xpath("html/body/div[5]/div/ul/li["+i+"]/a")).click();
                }
            }
        }
        //일정 간편보기 클릭
        util.click(By.xpath("//a[contains(text(),'" + module.contents + "')]"));

        //일정 간편보기에서 삭제 클릭
        util.waitForIsElementPresent(By.xpath("//button[@class='_del_btn btn_default btn_default_v1']")).click();

        //알림메일 발송하지 않음 체크하고 확인
        util.click(By.id("_wpageTMP8_send_to_shared_nobody"));
        util.click(By.xpath("//button[@class ='_ok normal']"));
        util.getAlert().accept();
    }
}
