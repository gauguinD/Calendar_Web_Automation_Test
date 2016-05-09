package com.naver.calendar.s01_캘린더홈;

import main.TestIds;
import main.Testcase;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;


public class suite_05_캘린더홈_캘린더영역_Test extends Testcase {

    public String Title = null;
    public String URL = null;

    public String defaultCountry;
    public String defaultCity;

    public String additionalCountry;
    public String additionalCity;

    public String tempEventSubject;

    /*
   * Step : 로그인 > 해당 계정으로 로그인
   * Result : 해당하는 계정으로 로그인 됨
    */
    @Test
    public void TC_00_캘리더영역_로그인_Test() throws Exception {
        module.로그인(util, TestIds.CalUser.getId(), TestIds.CalUser.getPw());
        util.waitForIsElementPresent(By.xpath("//a[@class=_'svc_lnk pwe_home']"));
    }

    /*
    * Step : 캘린더영역 > 일간 클릭
    * Result : 일간 > 일간 클릭하면 캘린더 영역 일간으로 노출 됨
    * URL : 뷰 방식 day로 표시되는것 확인
    */

    @Test
    public void TC_01_캘린더영역_일간_Test() throws Exception{

        util.click(By.xpath("//button[contains(@class,'_day diary')]"));
        util.waitForIsElementPresent(By.xpath("//button[@class='_day diary on']"));

        //URL 검증 필요
        util.waitForIsElementPresent(By.xpath("//span[@class='date']/span[10]"));
        URL = util.getCurrentUrl();
        assertTrue(URL.contains("day"));
    }


    /*
    * Step : 캘린더영역 > 일간 > 시간대 클릭
    * Result : 일간 > 현재 시간대 노출 됨
    */

    @Test
    public void TC_02_캘린더영역_일간_시간대_Test() throws Exception{

        util.click(By.className("_set_timezone"));

        //시간대에서 현재 기본 시간대 국가,도시 확인
        util.waitForIsElementPresent(By.className("layer_add_timezone"));
        defaultCountry = util.waitForIsElementPresent(By.xpath("//div[@class='_default_country  selectbox13 nation']/div/div")).getText();
        defaultCity = util.waitForIsElementPresent(By.xpath("//div[@class='_default_city  selectbox13']/div/div")).getText();

        util.printLog("현재 기본 시간대 국가는 ["+defaultCountry+"] 도시는 ["+defaultCity+"]입니다.");

        assertTrue(defaultCountry.contains("대한민국"));
        assertTrue(defaultCity.contains("서울"));

        //시간대에서 현재 추가 시간대 국가,도시 확인
        additionalCountry = util.waitForIsElementPresent(By.xpath("//div[@class='_additional_country  selectbox13 nation']/div/div")).getText();
        additionalCity = util.waitForIsElementPresent(By.xpath("//div[@class='_additional_city  selectbox13']/div/div")).getText();

        util.printLog("현재 추가 시간대 국가는 ["+additionalCountry+"] 도시는 ["+additionalCity+"]입니다.");

        assertTrue(additionalCountry.contains("설정안함"));
        assertTrue(additionalCity.contains("설정안함"));

        util.click(By.xpath("//button[@class='_save normal2']"));
    }


    /*
    * Step : 캘린더영역 > 일간 > 할일생성
    * Result : 일간 > 할일 생성 됨
    */

    @Test
    public void TC_03_캘린더영역_일간_할일_생성_Test() throws Exception {

        util.waitForIsElementPresent(By.xpath("//div[@class='_new_task']"));
        util.click(By.xpath("//div[@class='_new_task']"));

        //일간영역_할일 제목으로 일정 생성
        util.waitForIsElementPresent(By.id("_taskForm"));
        util.type(By.className("_content"),"일간영역_할일");
        util.click(By.className("_save"));

        //일간영역_할일 제목으로 할일 생성 되었는지 확인
        tempEventSubject = util.waitForIsElementPresent(By.xpath("//td[@class='_task']/div/div/a")).getText();
        assertTrue(tempEventSubject.contains("일간영역_할일"));
    }


    /*
    * Step : 캘린더영역 > 일간 > 할일삭제
    * Result : 일간 > 할일 삭제 됨
    */

    @Test
    public void TC_04_캘린더영역_일간_할일_삭제_Test() throws Exception{

        util.waitForIsElementPresent(By.xpath("//td[@class='_task']"));
        util.click(By.xpath("//td[@class='_task']"));

        //할일 노출 되고 할일 삭제 버튼 클릭
        util.waitForIsElementPresent(By.id("_taskForm"));
        util.click(By.xpath("//button[@class='_delete btn_delete']"));
        util.getAlert().accept();

        //할일 사라지고 _new_task 노출되는것 확인
        util.waitForIsElementPresent(By.xpath("//div[@class='_new_task']"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//div[@class='_new_task']")).isDisplayed());
    }


    /*
    * Step : 캘린더영역 > 일간 > 기념일생성
    * Result : 일간 > 기념일 생성 됨
    */

    @Test
    public void TC_05_캘린더영역_일간_기념일_생성_Test() throws Exception {

        util.waitForIsElementPresent(By.xpath("//div[@class='_new_anniversary']"));
        util.click(By.xpath("//div[@class='_new_anniversary']"));

        //일간영역_기념일 제목으로 일정 생성
        util.waitForIsElementPresent(By.xpath("//div[@class='schedule_body anniversary_edit']"));
        util.type(By.xpath("//div[@class='pos_input']/input"),"일간영역_기념일");
        util.click(By.xpath("//button[@class='_saveBtn btn_sys pos_save']"));

        //일간영역_기념일 제목으로 기념일 생성 되었는지 확인
        tempEventSubject = util.waitForIsElementPresent(By.xpath("//td[@class='_anniversary']/div/div/a")).getText();
        assertTrue(tempEventSubject.contains("일간영역_기념일"));
    }


    /*
    * Step : 캘린더영역 > 일간 > 기념일삭제
    * Result : 일간 > 기념일 삭제 됨
    */

    @Test
    public void TC_06_캘린더영역_일간_기념일_삭제_Test() throws Exception{

        util.waitForIsElementPresent(By.xpath("//td[@class='_anniversary']"));
        util.click(By.xpath("//td[@class='_anniversary']"));

        //기념일 노출 되고 기념일 삭제 버튼 클릭
        util.waitForIsElementPresent(By.xpath("//div[@class='_quick_schedule_view ly_quick_wrap  no_view']"));
        util.click(By.xpath("//button[@class='_del_btn btn_default btn_default_v1']"));

        //기념일 사라지고 _new_anniversary 노출되는것 확인
        util.waitForIsElementPresent(By.xpath("//div[@class='_new_anniversary']"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//div[@class='_new_anniversary']")).isDisplayed());
    }


    /*
    * Step : 캘린더영역 > 일간 > 종일일정 생성
    * Result : 일간 > 종일일정 생성 됨
    */

    @Test
    public void TC_07_캘린더영역_일간_종일일정_생성_Test() throws Exception {

        util.waitForIsElementPresent(By.xpath("//div[@class='_new_allday']"));
        util.click(By.xpath("//div[@class='_new_allday']"));

        //일간영역_종일 제목으로 일정 생성
        util.waitForIsElementPresent(By.xpath("//div[@id='_real_schedule_body']"));
        util.type(By.xpath("//div[@class='pos_input']/input"),"일간영역_종일일정");
        util.click(By.xpath("//button[@class='btn_sys pos_save']"));

        if(util.waitForIsVisible(By.xpath("//div[@class='_schedule_entry_popup schedule_entry_alarm_layer layer_popup no_view select_free_layer join_schedule_edit_layer']")))
        {
            util.click(By.xpath("//input[@id='_wpageTMP8_send_to_shared_nobody']"));
            util.click(By.xpath("//button[@class='_ok normal']"));
        }

        //일간영역_종일 제목으로 종일일정 생성 되었는지 확인
        tempEventSubject = util.waitForIsElementPresent(By.xpath("//td[@class='_schedule']/div/div/a")).getText();
        assertTrue(tempEventSubject.contains("일간영역_종일일정"));
    }


    /*
    * Step : 캘린더영역 > 일간 > 종일일정 삭제
    * Result : 일간 > 종일일정 삭제 됨
    */

    @Test
    public void TC_08_캘린더영역_일간_종일일정_삭제_Test() throws Exception{

        util.waitForIsElementPresent(By.xpath("//td[@class='_schedule']"));
        util.click(By.xpath("//td[@class='_schedule']"));

        //종일일정 노출 되고 종일일정 삭제 버튼 클릭
        util.waitForIsElementPresent(By.xpath("//div[@class='_quick_schedule_view ly_quick_wrap  no_view long_width']"));
        util.click(By.xpath("//button[@class='_del_btn btn_default btn_default_v1']"));
        util.getAlert().accept();

        //종일일정 사라지고 _new_allday 노출되는것 확인
        util.waitForIsElementPresent(By.xpath("//div[@class='_new_allday']"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//div[@class='_new_allday']")).isDisplayed());
    }


    /*
    * Step : 캘린더영역 > 일간 > 시간일정 생성
    * Result : 일간 > 시간일정 생성 됨
    */

    @Test
    public void TC_09_캘린더영역_일간_시간일정_생성_Test() throws Exception {

        util.waitForIsElementPresent(By.xpath("//div[@class='timeline']"));
        util.click(By.xpath("//div[@class='timeline']"));

        //일간영역_시간 제목으로 일정 생성
        util.waitForIsElementPresent(By.xpath("//div[@id='_real_schedule_body']"));
        util.type(By.xpath("//div[@class='pos_input']/input"),"일간영역_시간");
        util.click(By.xpath("//button[@class='btn_sys pos_save']"));

        //일간영역_시간 제목으로 시간일정 생성 되었는지 확인
        tempEventSubject = util.waitForIsElementPresent(By.xpath("//tr[@class='_time_schedule_container']/td/div/div/div/p/a")).getText();
        assertTrue(tempEventSubject.contains("일간영역_시간"));
    }


    /*
    * Step : 캘린더영역 > 일간 > 시간일정 삭제
    * Result : 일간 > 시간일정 삭제 됨
    */

    @Test
    public void TC_10_캘린더영역_일간_시간일정_삭제_Test() throws Exception{

        util.waitForIsElementPresent(By.xpath("//td[@class='_time_schedule_container']"));
        util.click(By.xpath("//tr[@class='_time_schedule_container']/td/div/div/div"));

        //시간일정 노출 되고 일정 삭제 버튼 클릭
        util.waitForIsElementPresent(By.xpath("//div[@class='_quick_schedule_view ly_quick_wrap  no_view long_width']"));
        util.click(By.xpath("//button[@class='_del_btn btn_default btn_default_v1']"));
        util.getAlert().accept();

        //시간일정 사라지고 _new_anniversary 노출되는것 확인
        util.waitForIsNotVisible(By.xpath("//tr[@class='_time_schedule_container']/td/div/div/div"));
        assertTrue(util.waitForIsNotVisible(By.xpath("//tr[@class='_time_schedule_container']/td/div/div/div")));
    }


    /*
    * Step : 상단영역 > 주간 클릭
    * Result : 월간 > 주간 클릭하면 캘린더 영역 주간으로 노출 됨
    * URL : 뷰 방식 week로 표시되는것 확인
    */

    @Test
    public void TC_11_캘린더영역_주간_Test() throws Exception{

        util.click(By.xpath("//button[contains(@class,'_week  week')]"));
        util.waitForIsElementPresent(By.xpath("//button[@class='_week  week on']"));

        //URL 검증 필요
        util.waitForIsElementPresent(By.xpath("//span[@class='date']/span[11]"));
        URL = util.getCurrentUrl();
        util.printLog(URL);

        assertTrue(URL.contains("week"));
    }


    /*
    * Step : 캘린더영역 > 일간 > 시간대 클릭
    * Result : 일간 > 현재 시간대 노출 됨
    */

    @Test
    public void TC_12_캘린더영역_주간_시간대_Test() throws Exception{

        util.click(By.className("_set_timezone"));

        //시간대에서 현재 기본 시간대 국가,도시 확인
        util.waitForIsElementPresent(By.className("layer_add_timezone"));
        defaultCountry = util.waitForIsElementPresent(By.xpath("//div[@class='_default_country  selectbox13 nation']/div/div")).getText();
        defaultCity = util.waitForIsElementPresent(By.xpath("//div[@class='_default_city  selectbox13']/div/div")).getText();

        util.printLog("현재 기본 시간대 국가는 ["+defaultCountry+"] 도시는 ["+defaultCity+"]입니다.");

        assertTrue(defaultCountry.contains("대한민국"));
        assertTrue(defaultCity.contains("서울"));

        //시간대에서 현재 추가 시간대 국가,도시 확인
        additionalCountry = util.waitForIsElementPresent(By.xpath("//div[@class='_additional_country  selectbox13 nation']/div/div")).getText();
        additionalCity = util.waitForIsElementPresent(By.xpath("//div[@class='_additional_city  selectbox13']/div/div")).getText();

        util.printLog("현재 추가 시간대 국가는 ["+additionalCountry+"] 도시는 ["+additionalCity+"]입니다.");

        assertTrue(additionalCountry.contains("설정안함"));
        assertTrue(additionalCity.contains("설정안함"));

        util.click(By.xpath("//button[@class='_save normal2']"));
    }


    /*
    * Step : 캘린더영역 > 주간 > 할일생성
    * Result : 주간 > 할일 생성 됨
    */

    @Test
    public void TC_13_캘린더영역_주간_할일_생성_Test() throws Exception {

        util.waitForIsElementPresent(By.xpath("//div[@class='_new_task']"));
        util.click(By.xpath("//div[@class='_new_task']"));

        //주간영역_할일 제목으로 일정 생성
        util.waitForIsElementPresent(By.id("_taskForm"));
        util.type(By.className("_content"),"주간영역_할일");
        util.click(By.className("_save"));

        //주간영역_할일 제목으로 할일 생성 되었는지 확인
        tempEventSubject = util.waitForIsElementPresent(By.xpath("//td[@class='_task']/div/div/a")).getText();
        assertTrue(tempEventSubject.contains("주간영역_할일"));
    }



    /*
    * Step : 캘린더영역 > 주간 > 할일삭제
    * Result : 주간 > 할일 삭제 됨
    */

    @Test
    public void TC_14_캘린더영역_주간_할일_삭제_Test() throws Exception{

        util.waitForIsElementPresent(By.xpath("//td[@class='_task']"));
        util.click(By.xpath("//td[@class='_task']"));

        //할일 노출 되고 할일 삭제 버튼 클릭
        util.waitForIsElementPresent(By.id("_taskForm"));
        util.click(By.xpath("//button[@class='_delete btn_delete']"));
        util.getAlert().accept();

        //할일 사라지고 _new_task 노출되는것 확인
        util.waitForIsElementPresent(By.xpath("//div[@class='_new_task']"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//div[@class='_new_task']")).isDisplayed());
    }


    /*
    * Step : 캘린더영역 > 주간 > 기념일생성
    * Result : 주간 > 기념일 생성 됨
    */

    @Test
    public void TC_15_캘린더영역_주간_기념일_생성_Test() throws Exception {

        util.waitForIsElementPresent(By.xpath("//div[@class='_new_anniversary']"));
        util.click(By.xpath("//div[@class='_new_anniversary']"));

        //주간영역_기념일 제목으로 일정 생성
        util.waitForIsElementPresent(By.xpath("//div[@class='schedule_body anniversary_edit']"));
        util.type(By.xpath("//div[@class='pos_input']/input"),"주간영역_기념일");
        util.click(By.xpath("//button[@class='_saveBtn btn_sys pos_save']"));

        //주간영역_기념일 제목으로 기념일 생성 되었는지 확인
        tempEventSubject = util.waitForIsElementPresent(By.xpath("//td[@class='_anniversary']/div/div/a")).getText();
        assertTrue(tempEventSubject.contains("주간영역_기념일"));
    }


    /*
    * Step : 캘린더영역 > 주간 > 기념일삭제
    * Result : 주간 > 기념일 삭제 됨
    */

    @Test
    public void TC_16_캘린더영역_주간_기념일_삭제_Test() throws Exception{

        util.waitForIsElementPresent(By.xpath("//td[@class='_anniversary']"));
        util.click(By.xpath("//td[@class='_anniversary']"));

        //기념일 노출 되고 기념일 삭제 버튼 클릭
        util.waitForIsElementPresent(By.xpath("//div[@class='_quick_schedule_view ly_quick_wrap  no_view']"));
        util.click(By.xpath("//button[@class='_del_btn btn_default btn_default_v1']"));

        //기념일 사라지고 _new_anniversary 노출되는것 확인
        util.waitForIsElementPresent(By.xpath("//div[@class='_new_anniversary']"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//div[@class='_new_anniversary']")).isDisplayed());
    }


    /*
    * Step : 캘린더영역 > 주간 > 종일일정 생성
    * Result : 주간 > 종일일정 생성 됨
    */

    @Test
    public void TC_17_캘린더영역_주간_종일일정_생성_Test() throws Exception {

        util.waitForIsElementPresent(By.xpath("//div[@class='_new_allday']"));
        util.click(By.xpath("//div[@class='_new_allday']"));

        //주간영역_종일 제목으로 일정 생성
        util.waitForIsElementPresent(By.xpath("//div[@id='_real_schedule_body']"));
        util.type(By.xpath("//div[@class='pos_input']/input"),"주간영역_종일일정");
        util.click(By.xpath("//button[@class='btn_sys pos_save']"));

        //주간영역_종일 제목으로 종일일정 생성 되었는지 확인
        tempEventSubject = util.waitForIsElementPresent(By.xpath("//td[@class='_schedule']/div/div/a")).getText();
        assertTrue(tempEventSubject.contains("주간영역_종일일정"));
    }


    /*
    * Step : 캘린더영역 > 주간 > 종일일정 삭제
    * Result : 주간 > 종일일정 삭제 됨
    */

    @Test
    public void TC_18_캘린더영역_주간_종일일정_삭제_Test() throws Exception{

        util.waitForIsElementPresent(By.xpath("//td[@class='_schedule']"));
        util.click(By.xpath("//td[@class='_schedule']"));

        //종일일정 노출 되고 종일일정 삭제 버튼 클릭
        util.waitForIsElementPresent(By.xpath("//div[@class='_quick_schedule_view ly_quick_wrap  no_view long_width']"));
        util.click(By.xpath("//button[@class='_del_btn btn_default btn_default_v1']"));
        util.getAlert().accept();

        //종일일정 사라지고 _new_allday 노출되는것 확인
        util.waitForIsElementPresent(By.xpath("//div[@class='_new_allday']"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//div[@class='_new_allday']")).isDisplayed());
    }


    /*
    * Step : 캘린더영역 > 주간 > 시간일정 생성
    * Result : 주간 > 시간일정 생성 됨
    */

    @Test
    public void TC_19_캘린더영역_주간_시간일정_생성_Test() throws Exception {

        util.waitForIsElementPresent(By.xpath("//div[@class='timeline']"));
        util.click(By.xpath("//div[@class='timeline']"));

        //주간영역_시간 제목으로 일정 생성
        util.waitForIsElementPresent(By.xpath("//div[@id='_real_schedule_body']"));
        util.type(By.xpath("//div[@class='pos_input']/input"),"주간영역_시간");
        util.click(By.xpath("//button[@class='btn_sys pos_save']"));

        //주간영역_시간 제목으로 시간일정 생성 되었는지 확인
        tempEventSubject = util.waitForIsElementPresent(By.xpath("//tr[@class='_time_schedule_container']/td/div/div/div/p/a")).getText();
        assertTrue(tempEventSubject.contains("주간영역_시간"));
    }


    /*
    * Step : 캘린더영역 > 주간 > 시간일정 삭제
    * Result : 주간 > 시간일정 삭제 됨
    */

    @Test
    public void TC_20_캘린더영역_주간_시간일정_삭제_Test() throws Exception{

        util.waitForIsElementPresent(By.xpath("//td[@class='_time_schedule_container']"));
        util.click(By.xpath("//tr[@class='_time_schedule_container']/td/div/div/div"));

        //시간일정 노출 되고 일정 삭제 버튼 클릭
        util.waitForIsElementPresent(By.xpath("//div[@class='_quick_schedule_view ly_quick_wrap  no_view long_width']"));
        util.click(By.xpath("//button[@class='_del_btn btn_default btn_default_v1']"));
        util.getAlert().accept();

        //시간일정 사라지고 _new_anniversary 노출되는것 확인
        util.waitForIsNotVisible(By.xpath("//tr[@class='_time_schedule_container']/td/div/div/div"));
        assertTrue(util.waitForIsNotVisible(By.xpath("//tr[@class='_time_schedule_container']/td/div/div/div")));
    }


    /*
    * Step : 상단영역 > 월간 클릭
    * Result : 월간 > 월간 클리하면 캘린더 영역 월간으로 노출 됨
    * URL : 뷰 방식 month로 표시되는것 확인
    */

    @Test
    public void TC_21_캘린더영역_월간_Test() throws Exception{

        util.click(By.xpath("//button[contains(@class,'_month month')]"));
        util.waitForIsElementPresent(By.xpath("//button[@class='_month month on']"));

        //URL 검증 필요
        util.waitForIsVisible(By.xpath("//div[@class='monthly_calendar']"));
        URL = util.getCurrentUrl();

        util.printLog(URL);
        assertTrue(URL.contains("month"));
    }


    /*
    * Step : 캘린더영역 > 월간 > 일정 생성
    * Result : 월간 > 일정 생성 됨
    */

    @Test
    public void TC_22_캘린더영역_월간_일정_생성_Test() throws Exception {

        util.waitForIsElementPresent(By.xpath("//td[@class='_month_cell month_schedule_drop today_area']"));
        util.click(By.xpath("//td[@class='_month_cell month_schedule_drop today_area']"));

        //월간영역_일정 제목으로 일정 생성
        util.waitForIsElementPresent(By.xpath("//div[@id='_real_schedule_body']"));
        util.type(By.xpath("//div[@class='pos_input']/input"),"월간영역_일정");
        util.click(By.xpath("//button[@class='btn_sys pos_save']"));

        //월간영역_일 제목으로 시간일정 생성 되었는지 확인
        tempEventSubject = util.waitForIsElementPresent(By.xpath("//a[contains(text(),'월간영역_일정')]")).getText();
        assertTrue(tempEventSubject.contains("월간영역_일정"));
    }


    /*
    * Step : 캘린더영역 > 월간 > 일정 삭제
    * Result : 월간 > 일정 삭제 됨
    */

    @Test
    public void TC_23_캘린더영역_월간_일정_삭제_Test() throws Exception{

        util.waitForIsElementPresent(By.xpath("//a[contains(text(),'월간영역_일정')]"));
        util.click(By.xpath("//a[contains(text(),'월간영역_일정')]"));

        //일정 노출 되고 일정 삭제 버튼 클릭
        util.waitForIsElementPresent(By.xpath("//div[@class='_quick_schedule_view ly_quick_wrap  no_view long_width']"));
        util.click(By.xpath("//button[@class='_del_btn btn_default btn_default_v1']"));
        util.getAlert().accept();

        //일정 사라지는것 확인
        util.waitForIsNotVisible(By.xpath("//a[contains(text(),'월간영역_일정')]"));
        assertTrue(util.waitForIsNotVisible(By.xpath("//a[contains(text(),'월간영역_일정')]")));
    }


    /*
    * Step : 캘린더영역 > 목록 클릭
    * Result : 목록 > 목록 클리하면 캘린더 영역 목록으로 노출 됨
    * URL : 뷰 방식 list로 표시되는것 확인
    */

    @Test
    public void TC_24_캘린더영역_목록_Test() throws Exception{

        util.click(By.xpath("//button[contains(@class,'_list list')]"));
        util.waitForIsElementPresent(By.xpath("//button[@class='_list list on']"));


        //URL 검증 필요
        util.waitForIsVisible(By.xpath("//div[@class='list_frame']"));
        URL = util.getCurrentUrl();

        util.printLog(URL);
        assertTrue(URL.contains("list"));
    }


    /*
    * Step : 캘린더영역 > 목록 > 일정 생성
    * Result : 목록 > 일정 생성 됨
    */

    @Test
    public void TC_25_캘린더영역_목록_일정_생성_Test() throws Exception {

        util.waitForIsElementPresent(By.xpath("//li[contains(@class,'today')]/dl/dt//span/button"));
        util.click(By.xpath("//li[contains(@class,'today')]/dl/dt//span/button"));

        //목록영역_일정 제목으로 일정 생성
        util.waitForIsElementPresent(By.xpath("//div[@id='_real_schedule_body']"));
        util.type(By.xpath("//div[@class='pos_input']/input"),"목록영역_일정");
        util.click(By.xpath("//button[@class='btn_sys pos_save']"));

        //목록영역_일 제목으로 시간일정 생성 되었는지 확인
        tempEventSubject = util.waitForIsElementPresent(By.xpath("//a[contains(text(),'목록영역_일정')]")).getText();
        assertTrue(tempEventSubject.contains("목록영역_일정"));
    }


    /*
    * Step : 캘린더영역 > 목록 > 일정 삭제
    * Result : 목록 > 일정 삭제 됨
    */

    @Test
    public void TC_26_캘린더영역_목록_일정_삭제_Test() throws Exception{

        util.waitForIsElementPresent(By.xpath("//a[contains(text(),'목록영역_일정')]"));
        util.click(By.xpath("//a[contains(text(),'목록영역_일정')]"));

        //일정 노출 되고 일정 삭제 버튼 클릭
        util.waitForIsElementPresent(By.xpath("//div[@class='_quick_schedule_view ly_quick_wrap  no_view long_width']"));
        util.click(By.xpath("//button[@class='_del_btn btn_default btn_default_v1']"));
        util.getAlert().accept();

        //일정 사라지는것 확인
        util.waitForIsNotVisible(By.xpath("//a[contains(text(),'목록영역_일정')]"));
        assertTrue(util.waitForIsNotVisible(By.xpath("//a[contains(text(),'목록영역_일정')]")));
    }


}
