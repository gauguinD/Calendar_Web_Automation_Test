package com.naver.calendar.s05_초기화;

import main.TestIds;
import main.Testcase;
import main.Utilities;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;


public class suite_02_초기화_약속쓰기_Test extends Testcase {

    public String Title = null;
    public String URL = null;
    public String lunarDate = null;
    public String scheduleSubject =null;
    public String alertText=null;
    public String stickerKey=null;
    public String repeatText=null;


    public int maxColor=0;
    public int maxSticker=0;
    public int stickerCategoryNum=0;

    public void writeSchedule(Utilities util, String subject) throws Exception{

        util.sleep(2);
        util.waitForIsElementPresent(By.xpath("//button[contains(@class,'_list list')]"));
        util.click(By.xpath("//button[contains(@class,'_list list')]"));

        util.click(By.xpath("//a[contains(@class,'write_schedule single')]"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//a[@class='_back btn_back_calender']")).isDisplayed());

        util.waitForIsElementPresent(By.xpath("//input[@id='tx0_0']"));
        util.type(By.xpath("//input[@id='tx0_0']"),subject);
    }


    public void saveSchedule(Utilities util, String subject) throws Exception{
        //저장버튼 클릭하여 일정 저장
        util.click(By.xpath("//button[@class ='save _save_btn _save']"));
        util.sleep(2);
        if(util.waitForIsNotVisible(By.xpath("//div[@class='layer_content']"))){
        }
        else{
            util.waitForIsElementPresent(By.xpath("//button[@class='_ok normal btn_emphasis']"));
            util.click(By.xpath("//button[@class='_ok normal btn_emphasis']"));
            util.waitForPageLoaded();
        }
        util.waitForIsElementPresent(By.xpath("//button[contains(@class,'_go_task type_schedule todo')]"));
        util.goRefresh();
        util.waitForPageLoaded();
    }


    public void deleteSchedule(Utilities util, String subject) throws Exception{

        //월뷰 목록뷰로 설정
        util.click(By.xpath("//button[contains(@class,'_list list')]"));
        util.sleep(3);
        util.waitForIsElementPresent(By.xpath("//button[@class='_list list on']"));

        util.waitForIsElementPresent(By.xpath("//a[contains(text(),'"+subject+"')]"));
        util.printLog(subject);
        util.sleep(3);

        assertTrue(util.waitForIsVisible(By.xpath("//a[contains(@class,'_schedule')][contains(text(),'"+subject+"')]")));

        util.click(By.xpath("//a[contains(@class,'_schedule')][contains(text(),'"+subject+"')]"));
        util.waitForIsElementPresent(By.xpath("//div[@class='ly_pop']"));
        util.click(By.xpath("//button[contains(@class,'_del_btn btn_default')]"));
        util.sleep(2);


        //일정이 생성된 캘린더가 공유캘린더 일 경우 예외처리
        //1. 얼럿이 노출되는지 확인 하고
        //2. 공유캘린더 인지 확인
        if(util.isAlertPresent(util)){
            alertText = util.getAlert().getText();
            assertTrue(alertText.contains("일정을 삭제하시겠습니까?"));
            util.getAlert().accept();
        }
        else if(util.waitForIsElementPresent(By.xpath("//div[@class='layer_content']")).isDisplayed()){

            if(scheduleSubject.contains("이일정만삭제")){
                util.waitForIsElementPresent(By.xpath("//div[@class='_repeat_del repeat_del']/ul/li[1]"));
                util.click(By.xpath("//div[@class='_repeat_del repeat_del']/ul/li[1]/input"));
            }
            else if(scheduleSubject.contains("이후일정모두삭제")){
                util.waitForIsElementPresent(By.xpath("//div[@class='_repeat_del repeat_del']/ul/li[2]"));
                util.click(By.xpath("//div[@class='_repeat_del repeat_del']/ul/li[2]/input"));
            }
            else if(scheduleSubject.contains("전체반복일정삭제")){
                util.waitForIsElementPresent(By.xpath("//div[@class='_repeat_del repeat_del']/ul/li[3]"));
                util.click(By.xpath("//div[@class='_repeat_del repeat_del']/ul/li[3]/input"));
            }

            util.waitForIsElementPresent(By.xpath("//button[@class='_ok normal btn_emphasis']"));
            util.click(By.xpath("//button[@class='_ok normal btn_emphasis']"));
        }

        //assertTrue(util.waitForIsNotVisible(By.xpath("//a[contains(text(),'"+subject+"')]")));
    }

    public void deleteAllSchedule(Utilities util, String subject) throws Exception{

        //월뷰 목록뷰로 설정
        util.click(By.xpath("//button[contains(@class,'_list list')]"));
        util.sleep(3);
        util.waitForIsElementPresent(By.xpath("//button[@class='_list list on']"));
        //util.mouseOver(By.xpath("//a[contains(text(),'"+subject+"')]"));
        //util.focusElement(By.xpath("//a[contains(text(),'"+subject+"')]"));
        util.waitForIsElementPresent(By.xpath("//a[contains(text(),'"+subject+"')]"));
        util.printLog(subject);
        util.sleep(3);

        assertTrue(util.waitForIsVisible(By.xpath("//a[contains(@class,'_schedule')][contains(text(),'"+subject+"')]")));
        util.click(By.xpath("//a[contains(@class,'_schedule')][contains(text(),'"+subject+"')]"));
        util.waitForIsElementPresent(By.xpath("//div[@class='ly_pop']"));
        util.click(By.xpath("//button[contains(@class,'_del_btn btn_default')]"));
        util.sleep(2);


        //일정이 생성된 캘린더가 공유캘린더 일 경우 예외처리
        //1. 얼럿이 노출되는지 확인 하고
        //2. 공유캘린더 인지 확인
        if(util.isAlertPresent(util)){
            alertText = util.getAlert().getText();
            assertTrue(alertText.contains("일정을 삭제하시겠습니까?"));
            util.getAlert().accept();
        }
        else if(util.waitForIsElementPresent(By.xpath("//div[@class='layer_content']")).isDisplayed()){

            util.waitForIsElementPresent(By.xpath("//input[@id='_wpageTMP8_repeat_del_all']"));
            util.click(By.xpath("//input[@id='_wpageTMP8_repeat_del_all']"));

            util.waitForIsElementPresent(By.xpath("//button[@class='_ok normal btn_emphasis']"));
            util.click(By.xpath("//button[@class='_ok normal btn_emphasis']"));
        }

        assertTrue(util.waitForIsNotVisible(By.xpath("//a[contains(text(),'"+subject+"')]")));
    }

    public void invite(Utilities util, String attendee)throws Exception{
        util.type(By.xpath("//input[@id='nhn_cp_entry']"),attendee);
        util.click(By.xpath("//a[@class='_btn_add on']"));
        util.sleep(2);
    }

    public void repeat(Utilities util,int i) throws Exception{
        //반복 옵션 클릭
        util.click(By.xpath("//a[@class='_repeat_btn btn_sy repeat img_ri']"));
        util.waitForIsElementPresent(By.xpath("//div[@class='_repeat_layer dh_layer']"));
        util.click(By.xpath("//div[contains(@class,'_frequency_select  selectbox13')]"));

        //반복 영역 선택
        util.waitForIsElementPresent(By.xpath("//div[contains(@class,'_frequency_select  selectbox13')]/div[2]/div/ul/li["+i+"]"));
        util.click(By.xpath("//div[contains(@class,'_frequency_select  selectbox13')]/div[2]/div/ul/li["+i+"]"));
        //무한반복 제거
        if(util.waitForIsNotVisible(By.xpath("//input[@class='_repeat_end_date input_txt']"))){
            util.waitForIsElementPresent(By.xpath("//input[@class='_infinite input_chk']"));
            util.click(By.xpath("//input[@class='_infinite input_chk']"));
            util.sleep(1);
        }
        repeatText = util.waitForIsElementPresent(By.xpath("//div[@class='_repeat_txt repeat_view_section']/p")).getText();


        util.click(By.xpath("//button[@class='_save normal btn_emphasis']"));

        util.waitForIsElementPresent(By.xpath("//div[@class='_repeat_text txt_nocycle']/p"));
        assertTrue(repeatText.contains(util.waitForIsElementPresent(By.xpath("//div[@class='_repeat_text txt_nocycle']/p")).getText()));
    }




    /*
    * Step : 로그인 > 해당 계정으로 로그인
    * Result : 해당하는 계정으로 로그인 됨
     */
    //@Test
    public void TC_00_일정_로그인_Test() throws Exception {
        module.로그인(util, TestIds.CalUser.getId(), TestIds.CalUser.getPw());
    }

    /*
    * Step : 로그인 > 해당 계정으로 로그인
    * Result : 해당하는 계정으로 로그인 됨
    */

    @Test
    public void TC_00_Works_로그인_Test() throws Exception {
        util.goTo("https://calendar.worksmobile.com");
        module.LogInWorksCal(util, TestIds.WorksCalUser1.getId(), TestIds.WorksCalUser1.getPw());
    }

    /*
     * Step : 일정 > 일정쓰기
     * Result : B,C 초대해서 약속 생성
     */

    @Test
    public void TC_01_알림개선_New_약속초대_S1_Test() throws Exception {

        //약속쓰기_case1로 일정 생성
        scheduleSubject = "New_약속초대_S1_"+module.subjectKey;
        writeSchedule(util,scheduleSubject);

        //cal05@nwetest.com 초대
        invite(util,TestIds.WorksCalUser2.getId());
        //cal07@nwetest.com 초대
        invite(util,TestIds.WorksCalUser3.getId());

        //초대된것 확인
        util.waitForIsElementPresent(By.xpath("//ul[@class='_new_invitee attendant_lst']"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//ul[@class='_new_invitee attendant_lst']")).isDisplayed());

        saveSchedule(util,scheduleSubject);
        deleteSchedule(util,scheduleSubject);
    }

        /*
     * Step : 일정 > 일정쓰기
     * Result : B,C 초대해서 반복 약속 생성
     */

    @Test
    public void TC_02_알림개선_New_반복약속초대_S2_Test() throws Exception {

        //약속쓰기_case1로 일정 생성
        scheduleSubject = "New_반복약속초대_S2_"+module.subjectKey;
        writeSchedule(util,scheduleSubject);

        //cal05@nwetest.com 초대
        invite(util,TestIds.WorksCalUser2.getId());
        //cal07@nwetest.com 초대
        invite(util,TestIds.WorksCalUser3.getId());
        //반복 설정(매일 : 1, 매주 평일 : 2, 매주 :3, 매달 : 4, 매년 : 5)
        repeat(util,1);

        //초대된것 확인
        util.waitForIsElementPresent(By.xpath("//ul[@class='_new_invitee attendant_lst']"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//ul[@class='_new_invitee attendant_lst']")).isDisplayed());

        saveSchedule(util,scheduleSubject);
        deleteAllSchedule(util,scheduleSubject);
    }


    /*
     * Step : 일정 > 일정쓰기
     * Result : B 로 로그인해서 D 초대
     */

    @Test
    public void TC_03_알림개선_New_약속2차초대_S3_Test() throws Exception {

        //약속쓰기_case1로 일정 생성
        scheduleSubject = "New_약속2차초대_S3_"+module.subjectKey;
        writeSchedule(util,scheduleSubject);

        //cal05@nwetest.com 초대
        invite(util,TestIds.WorksCalUser2.getId());
        //cal07@nwetest.com 초대
        invite(util,TestIds.WorksCalUser3.getId());

        //초대된것 확인
        util.waitForIsElementPresent(By.xpath("//ul[@class='_new_invitee attendant_lst']"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//ul[@class='_new_invitee attendant_lst']")).isDisplayed());
        saveSchedule(util,scheduleSubject);

        util.sleep(3);

        //로그아웃 하고 cal02@nwetest.com 계정으로 로그인
        util.click(By.xpath("//em[contains(@id,'emLogout')]"));
        util.waitForPageLoaded();
        module.ReLogInWorksCal(util,TestIds.WorksCalUser2.getId(),TestIds.WorksCalUser2.getPw());

        //캘린더 뷰 목록으로 변경
        util.waitForPageLoaded();
        util.sleep(3);
        util.waitForIsElementPresent(By.xpath("//button[contains(@class,'_list list')]"));
        util.click(By.xpath("//button[contains(@class,'_list list')]"));

        //"New_약속 초대_S1"+module.subjectKey 있는지 확인하고 클릭하여 일정쓰기로 이동
        util.waitForIsElementPresent(By.xpath("//a[contains(text(),'"+scheduleSubject+"')]"));
        util.printLog(scheduleSubject);
        util.sleep(2);
        assertTrue(util.waitForIsVisible(By.xpath("//a[contains(@class,'_schedule')][contains(text(),'"+scheduleSubject+"')]")));

        util.click(By.xpath("//a[contains(@class,'_schedule')][contains(text(),'"+scheduleSubject+"')]"));
        util.waitForIsElementPresent(By.xpath("//div[@class='ly_pop']"));
        util.click(By.xpath("//button[contains(@class,'_modify_btn btn_default')]"));
        util.sleep(2);

        //cal05@nwetest.com 계정 초대함
        util.type(By.xpath("//input[@id='nhn_cp_entry']"),TestIds.WorksCalUser4.getId());
        util.click(By.xpath("//a[@class='_btn_add on']"));
        util.sleep(2);

        util.waitForIsElementPresent(By.xpath("//ul[@class='_new_invitee attendant_lst']"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//ul[@class='_new_invitee attendant_lst']")).isDisplayed());

        saveSchedule(util,scheduleSubject);
    }

    /*
     * Step : 일정 > 일정쓰기
     * Result : 마스터로 로그인해서 D 초대 취소
     */

    @Test
    public void TC_04_알림개선_New_약속2차초대_S4_Test() throws Exception {

        //로그아웃 하고 다시 마스터로 로그인
        util.click(By.xpath("//em[contains(@id,'emLogout')]"));
        util.waitForPageLoaded();
        module.ReLogInWorksCal(util,TestIds.WorksCalUser1.getId(),TestIds.WorksCalUser1.getPw());
        util.waitForPageLoaded();
        util.sleep(3);

        //캘린더 뷰 목록으로 변경
        util.waitForIsElementPresent(By.xpath("//button[contains(@class,'_list list')]"));
        util.click(By.xpath("//button[contains(@class,'_list list')]"));

        //"약속쓰기_case1"+module.subjectKey 있는지 확인하고 클릭하여 일정쓰기로 이동
        util.waitForIsElementPresent(By.xpath("//a[contains(text(),'"+scheduleSubject+"')]"));
        util.printLog(scheduleSubject);
        util.sleep(3);

        assertTrue(util.waitForIsVisible(By.xpath("//a[contains(@class,'_schedule')][contains(text(),'"+scheduleSubject+"')]")));
        util.click(By.xpath("//a[contains(@class,'_schedule')][contains(text(),'"+scheduleSubject+"')]"));
        util.waitForIsElementPresent(By.xpath("//div[@class='ly_pop']"));
        util.click(By.xpath("//button[contains(@class,'_modify_btn btn_default')]"));
        util.sleep(3);

        //cal05@nwetest.com 계정 약속 초대 취소
        util.click(By.xpath("//li[@key='cal05@nwetest.com']/p/a[2]"));
        util.waitForIsElementPresent(By.xpath("//li[contains(@key,'cal05@nwetest.com')][contains(@class,'disable')]"));

        saveSchedule(util,scheduleSubject);
        deleteSchedule(util,scheduleSubject);
    }


    /*
     * Step : 일정 > 일정쓰기
     * Result : B,C 초대해서 반복 약속 생성하고 이 일정만 삭제로 취소
     */

    @Test
    public void TC_05_알림개선_New_반복약속초대_약속취소_이일정만삭제_S5_Test() throws Exception {

        //약속쓰기_case1로 일정 생성
        scheduleSubject = "New_반복약속초대_약속취소_이일정만삭제_S5_"+module.subjectKey;
        writeSchedule(util,scheduleSubject);

        //cal05@nwetest.com 초대
        invite(util,TestIds.WorksCalUser2.getId());
        //cal07@nwetest.com 초대
        invite(util,TestIds.WorksCalUser3.getId());
        //반복 설정(매일 : 1, 매주 평일 : 2, 매주 :3, 매달 : 4, 매년 : 5)
        repeat(util,1);

        //초대된것 확인
        util.waitForIsElementPresent(By.xpath("//ul[@class='_new_invitee attendant_lst']"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//ul[@class='_new_invitee attendant_lst']")).isDisplayed());
        saveSchedule(util,scheduleSubject);

        //deleteSchedule(util,scheduleSubject);
        deleteAllSchedule(util,scheduleSubject);
    }


    /*
     * Step : 일정 > 일정쓰기
     * Result : B,C 초대해서 반복 약속 생성하고 이후 일정 모두 삭제로 취소
     */

    //@Test
    public void TC_06_알림개선_New_반복약속초대_약속취소_이후일정모두삭제_S6_Test() throws Exception {

        //약속쓰기_case1로 일정 생성
        scheduleSubject = "New_반복약속초대_약속취소_이후일정모두삭제_S6_"+module.subjectKey;
        writeSchedule(util,scheduleSubject);

        //cal05@nwetest.com 초대
        invite(util,TestIds.WorksCalUser2.getId());
        //cal07@nwetest.com 초대
        invite(util,TestIds.WorksCalUser3.getId());
        //반복 설정(매일 : 1, 매주 평일 : 2, 매주 :3, 매달 : 4, 매년 : 5)
        repeat(util,1);

        //초대된것 확인
        util.waitForIsElementPresent(By.xpath("//ul[@class='_new_invitee attendant_lst']"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//ul[@class='_new_invitee attendant_lst']")).isDisplayed());
        saveSchedule(util,scheduleSubject);

        //deleteSchedule(util,scheduleSubject);
        deleteAllSchedule(util,scheduleSubject);
    }

     /*
     * Step : 일정 > 일정쓰기
     * Result : B,C 초대해서 반복 약속 생성하고 이후 일정 모두 삭제로 취소
     */

    @Test
    public void TC_07_알림개선_New_반복약속초대_약속취소_전체반복일정삭제_S7_Test() throws Exception {

        //약속쓰기_case1로 일정 생성
        scheduleSubject = "New_반복약속초대_약속취소_전체반복일정삭제_S7_"+module.subjectKey;
        writeSchedule(util,scheduleSubject);

        //cal05@nwetest.com 초대
        invite(util,TestIds.WorksCalUser2.getId());
        //cal07@nwetest.com 초대
        invite(util,TestIds.WorksCalUser3.getId());
        //반복 설정(매일 : 1, 매주 평일 : 2, 매주 :3, 매달 : 4, 매년 : 5)
        repeat(util,1);

        //초대된것 확인
        util.waitForIsElementPresent(By.xpath("//ul[@class='_new_invitee attendant_lst']"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//ul[@class='_new_invitee attendant_lst']")).isDisplayed());
        saveSchedule(util,scheduleSubject);

        deleteSchedule(util,scheduleSubject);
    }


    /*
     * Step : 일정 > 일정쓰기
     * Result : B,C 초대해서 약속 생성한 후 B로 약속 수락
     */

    //@Test
    public void TC_08_알림개선_New_약속초대수락_S8_Test() throws Exception {

        //New_약속 초대 수락_S8_로 일정 생성
        scheduleSubject = "New_약속초대수락_S8_"+module.subjectKey;
        writeSchedule(util,scheduleSubject);

        //cal05@nwetest.com 초대
        invite(util,TestIds.WorksCalUser2.getId());
        //cal07@nwetest.com 초대
        invite(util,TestIds.WorksCalUser3.getId());

        //초대된것 확인
        util.waitForIsElementPresent(By.xpath("//ul[@class='_new_invitee attendant_lst']"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//ul[@class='_new_invitee attendant_lst']")).isDisplayed());

        saveSchedule(util,scheduleSubject);

        //로그아웃 하고 cal05@nwetest.com 계정으로 로그인
        util.waitForIsElementPresent(By.xpath("//em[contains(@id,'emLogout')]"));
        util.click(By.xpath("//em[contains(@id,'emLogout')]"));
        util.waitForPageLoaded();
        module.ReLogInWorksCal(util,TestIds.WorksCalUser2.getId(),TestIds.WorksCalUser2.getPw());

        //캘린더 뷰 목록으로 변경
        util.waitForPageLoaded();
        util.sleep(3);
        util.waitForIsElementPresent(By.xpath("//button[contains(@class,'_list list')]"));
        util.click(By.xpath("//button[contains(@class,'_list list')]"));

        //"약속쓰기_case4"+module.subjectKey 있는지 확인하고 클릭하여 일정쓰기로 이동
        util.waitForIsElementPresent(By.xpath("//a[contains(text(),'"+scheduleSubject+"')]"));
        util.printLog(scheduleSubject);
        util.sleep(2);

        assertTrue(util.waitForIsVisible(By.xpath("//a[contains(@class,'_schedule')][contains(text(),'"+scheduleSubject+"')]")));
        util.click(By.xpath("//a[contains(@class,'_schedule')][contains(text(),'"+scheduleSubject+"')]"));
        util.waitForIsElementPresent(By.xpath("//div[@class='ly_pop']"));

        //간단레이어에서 "수락" 클릭
        util.waitForIsElementPresent(By.xpath("//a[contains(@class,'_accept_invite')]"));
        util.click(By.xpath("//a[contains(@class,'_accept_invite')]"));


        if(util.isAlertPresent(util))
        {
            //String alertText = util.getAlert().getText();
            util.getAlert().accept();
            util.getAlert().accept();
            util.sleep(2);
        }


        util.click(By.xpath("//a[@class='_close_layer btn_sp btn_clse']"));

        util.waitForIsElementPresent(By.xpath("//em[contains(@id,'emLogout')]"));
        util.click(By.xpath("//em[contains(@id,'emLogout')]"));
        util.waitForPageLoaded();

        //cal02@nwetest.com 계정으로 다시 로그인
        module.ReLogInWorksCal(util,TestIds.WorksCalUser1.getId(),TestIds.WorksCalUser1.getPw());
        util.waitForPageLoaded();
        util.sleep(3);

        //해당 약속 삭제
        deleteSchedule(util,scheduleSubject);
    }

    /*
     * Step : 일정 > 일정쓰기
     * Result : B,C 초대해서 약속 생성한 후 B로 약속 미정
     */

    @Test
    public void TC_09_알림개선_New_약속초대미정_S9_Test() throws Exception {

        //New_약속 초대 미정_S9_로 일정 생성
        scheduleSubject = "New_약속초대미정_S9_"+module.subjectKey;
        writeSchedule(util,scheduleSubject);

        //cal05@nwetest.com 초대
        invite(util,TestIds.WorksCalUser2.getId());
        //cal07@nwetest.com 초대
        invite(util,TestIds.WorksCalUser3.getId());

        //초대된것 확인
        util.waitForIsElementPresent(By.xpath("//ul[@class='_new_invitee attendant_lst']"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//ul[@class='_new_invitee attendant_lst']")).isDisplayed());

        saveSchedule(util,scheduleSubject);

        //로그아웃 하고 cal05@nwetest.com 계정으로 로그인
        util.waitForIsElementPresent(By.xpath("//em[contains(@id,'emLogout')]"));
        util.click(By.xpath("//em[contains(@id,'emLogout')]"));
        util.waitForPageLoaded();
        module.ReLogInWorksCal(util,TestIds.WorksCalUser2.getId(),TestIds.WorksCalUser2.getPw());

        //캘린더 뷰 목록으로 변경
        util.waitForPageLoaded();
        util.sleep(3);
        util.waitForIsElementPresent(By.xpath("//button[contains(@class,'_list list')]"));
        util.click(By.xpath("//button[contains(@class,'_list list')]"));

        //"약속쓰기_case4"+module.subjectKey 있는지 확인하고 클릭하여 일정쓰기로 이동
        util.waitForIsElementPresent(By.xpath("//a[contains(text(),'"+scheduleSubject+"')]"));
        util.printLog(scheduleSubject);
        util.sleep(2);

        assertTrue(util.waitForIsVisible(By.xpath("//a[contains(@class,'_schedule')][contains(text(),'"+scheduleSubject+"')]")));
        util.click(By.xpath("//a[contains(@class,'_schedule')][contains(text(),'"+scheduleSubject+"')]"));
        util.waitForIsElementPresent(By.xpath("//div[@class='ly_pop']"));

        //간단레이어에서 "미정" 클릭
        util.click(By.xpath("//a[contains(@class,'_tentative_invite')]"));
        util.waitForIsElementPresent(By.xpath("div[@class='layer_content']"));
        util.type(By.xpath("//textarea[@class='txtbox2 focus_txt _textarea']"),scheduleSubject+"에 미정으로 답변 합니다.");
        util.waitForIsElementPresent(By.xpath("//button[@class='normal normal_v1 btn_emphasis _confirm']"));
        util.click(By.xpath("//button[@class='normal normal_v1 btn_emphasis _confirm']"));
        if(util.isAlertPresent(util))
        {
            //String alertText = util.getAlert().getText();
            util.getAlert().accept();
            util.getAlert().accept();
            util.sleep(2);
        }
        util.click(By.xpath("//a[@class='_close_layer btn_sp btn_clse']"));

        util.click(By.xpath("//em[contains(@id,'emLogout')]"));
        util.waitForPageLoaded();

        //cal02@nwetest.com 계정으로 다시 로그인
        module.ReLogInWorksCal(util,TestIds.WorksCalUser1.getId(),TestIds.WorksCalUser1.getPw());
        util.waitForPageLoaded();
        util.sleep(3);

        //해당 약속 삭제
        deleteSchedule(util,scheduleSubject);
    }


    /*
     * Step : 일정 > 일정쓰기
     * Result : B,C 초대해서 약속 생성한 후 B로 약속 미
     */

    @Test
    public void TC_10_알림개선_New_약속초대거절_S10_Test() throws Exception {

        //New_약속 초대 거절_S10_로 일정 생성
        scheduleSubject = "New_약속초대거절_S10_"+module.subjectKey;
        writeSchedule(util,scheduleSubject);

        //cal05@nwetest.com 초대
        invite(util,TestIds.WorksCalUser2.getId());
        //cal07@nwetest.com 초대
        invite(util,TestIds.WorksCalUser3.getId());

        //초대된것 확인
        util.waitForIsElementPresent(By.xpath("//ul[@class='_new_invitee attendant_lst']"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//ul[@class='_new_invitee attendant_lst']")).isDisplayed());

        saveSchedule(util,scheduleSubject);

        //로그아웃 하고 cal05@nwetest.com 계정으로 로그인
        util.waitForIsElementPresent(By.xpath("//em[contains(@id,'emLogout')]"));
        util.click(By.xpath("//em[contains(@id,'emLogout')]"));
        util.waitForPageLoaded();
        module.ReLogInWorksCal(util,TestIds.WorksCalUser2.getId(),TestIds.WorksCalUser2.getPw());

        //캘린더 뷰 목록으로 변경
        util.waitForPageLoaded();
        util.sleep(3);
        util.waitForIsElementPresent(By.xpath("//button[contains(@class,'_list list')]"));
        util.click(By.xpath("//button[contains(@class,'_list list')]"));

        //"약속쓰기_case4"+module.subjectKey 있는지 확인하고 클릭하여 일정쓰기로 이동
        util.waitForIsElementPresent(By.xpath("//a[contains(text(),'"+scheduleSubject+"')]"));
        util.printLog(scheduleSubject);
        util.sleep(2);

        assertTrue(util.waitForIsVisible(By.xpath("//a[contains(@class,'_schedule')][contains(text(),'"+scheduleSubject+"')]")));
        util.click(By.xpath("//a[contains(@class,'_schedule')][contains(text(),'"+scheduleSubject+"')]"));
        util.waitForIsElementPresent(By.xpath("//div[@class='ly_pop']"));

        //간단레이어에서 "거절" 클릭
        util.click(By.xpath("//a[contains(@class,'_reject_invite')]"));
        util.waitForIsElementPresent(By.xpath("div[@class='layer_content']"));
        util.type(By.xpath("//textarea[@class='txtbox2 focus_txt _textarea']"),scheduleSubject+"에 거절으로 답변 합니다.");
        util.waitForIsElementPresent(By.xpath("//button[@class='normal normal_v1 btn_emphasis _confirm']"));
        util.click(By.xpath("//button[@class='normal normal_v1 btn_emphasis _confirm']"));
        if(util.isAlertPresent(util))
        {
            //String alertText = util.getAlert().getText();
            util.getAlert().accept();
            util.getAlert().accept();
            util.sleep(2);
        }
        util.click(By.xpath("//a[@class='_close_layer btn_sp btn_clse']"));

        util.click(By.xpath("//em[contains(@id,'emLogout')]"));
        util.waitForPageLoaded();

        //cal02@nwetest.com 계정으로 다시 로그인
        module.ReLogInWorksCal(util,TestIds.WorksCalUser1.getId(),TestIds.WorksCalUser1.getPw());
        util.waitForPageLoaded();
        util.sleep(3);

        //해당 약속 삭제
        deleteSchedule(util,scheduleSubject);
    }



    /*
    * Step : 일정 > 일정쓰기
    * Result : A로 약속 생성하고 B로 D 초대 후 D가 약속 수락/미정/거절 함
    */

    //@Test
    public void TC_11_알림메일개선_case7_Test() throws Exception {
        //약속쓰기_case7로 일정 생성
        scheduleSubject = "약속쓰기_case6"+module.subjectKey;
        util.waitForIsElementPresent(By.xpath("//button[contains(@class,'_list list')]"));
        util.click(By.xpath("//button[contains(@class,'_list list')]"));
        writeSchedule(util,scheduleSubject);

        //cal02@nwetest.com 초대
        util.type(By.xpath("//input[@id='nhn_cp_entry']"),TestIds.WorksCalUser2.getId());
        util.click(By.xpath("//a[@class='_btn_add on']"));

        util.sleep(2);

        //cal03@nwetest.com 초대
        util.type(By.xpath("//input[@id='nhn_cp_entry']"),TestIds.WorksCalUser3.getId());
        util.click(By.xpath("//a[@class='_btn_add on']"));

        //초대된것 확인
        util.waitForIsElementPresent(By.xpath("//ul[@class='_new_invitee attendant_lst']"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//ul[@class='_new_invitee attendant_lst']")).isDisplayed());
        saveSchedule(util,scheduleSubject);

        //로그아웃 하고 cal02@nwetest.com 계정으로 로그인
        util.click(By.xpath("//em[contains(@id,'emLogout')]"));
        util.waitForPageLoaded();
        module.ReLogInWorksCal(util,TestIds.WorksCalUser2.getId(),TestIds.WorksCalUser2.getPw());

        //캘린더 뷰 목록으로 변경
        util.waitForPageLoaded();
        util.sleep(3);
        util.waitForIsElementPresent(By.xpath("//button[contains(@class,'_list list')]"));
        util.click(By.xpath("//button[contains(@class,'_list list')]"));

        //"약속쓰기_case4"+module.subjectKey 있는지 확인하고 클릭하여 일정쓰기로 이동
        util.waitForIsElementPresent(By.xpath("//a[contains(text(),'"+scheduleSubject+"')]"));
        util.printLog(scheduleSubject);
        util.sleep(2);

        util.click(By.xpath("//a[contains(@class,'_schedule')][contains(text(),'"+scheduleSubject+"')]"));
        util.waitForIsElementPresent(By.xpath("//div[@class='ly_pop']"));
        util.click(By.xpath("//button[contains(@class,'_modify_btn btn_default btn_emphasis')]"));
        util.sleep(2);

        //cal05@nwetest.com 계정 초대함
        util.type(By.xpath("//input[@id='nhn_cp_entry']"),TestIds.WorksCalUser4.getId());
        util.click(By.xpath("//a[@class='_btn_add on']"));

        util.waitForIsElementPresent(By.xpath("//ul[@class='_new_invitee attendant_lst']"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//ul[@class='_new_invitee attendant_lst']")).isDisplayed());
        saveSchedule(util,scheduleSubject);

        //로그아웃 하고 cal05@nwetest.com 계정으로 로그인
        util.click(By.xpath("//em[contains(@id,'emLogout')]"));
        util.waitForPageLoaded();
        module.ReLogInWorksCal(util,TestIds.WorksCalUser4.getId(),TestIds.WorksCalUser4.getPw());

        //캘린더 뷰 목록으로 변경
        util.waitForPageLoaded();
        util.sleep(3);
        util.waitForIsElementPresent(By.xpath("//button[contains(@class,'_list list')]"));
        util.click(By.xpath("//button[contains(@class,'_list list')]"));

        //"약속쓰기_case4"+module.subjectKey 있는지 확인하고 클릭하여 일정쓰기로 이동
        util.waitForIsElementPresent(By.xpath("//a[contains(text(),'"+scheduleSubject+"')]"));
        util.printLog(scheduleSubject);
        util.sleep(2);

        assertTrue(util.waitForIsVisible(By.xpath("//a[contains(@class,'_schedule')][contains(text(),'"+scheduleSubject+"')]")));
        util.click(By.xpath("//a[contains(@class,'_schedule')][contains(text(),'"+scheduleSubject+"')]"));
        util.waitForIsElementPresent(By.xpath("//div[@class='ly_pop']"));

        //간단레이어에서 "수락" 클릭
        util.click(By.xpath("//a[contains(@class,'_accept_invite')]"));
        util.getAlert().accept();
        util.sleep(2);

        util.click(By.xpath("//a[contains(@class,'_tentative_invite')]"));
        util.waitForIsElementPresent(By.xpath("div[@class='layer_content']"));
        util.click(By.xpath("//button[@class='normal normal_v1 btn_emphasis _confirm']"));
        util.getAlert().accept();
        util.sleep(2);

        //간단레이어에서 "거절" 클릭
        util.click(By.xpath("//a[contains(@class,'_reject_invite')]"));
        util.waitForIsElementPresent(By.xpath("div[@class='layer_content']"));
        util.click(By.xpath("//button[@class='normal normal_v1 btn_emphasis _confirm']"));
        util.getAlert().accept();
        util.sleep(2);
        util.click(By.xpath("//a[@class='_close_layer btn_sp btn_clse']"));

        util.click(By.xpath("//em[contains(@id,'emLogout')]"));
        util.waitForPageLoaded();

        //cal02@nwetest.com 계정으로 다시 로그인
        module.ReLogInWorksCal(util,TestIds.WorksCalUser1.getId(),TestIds.WorksCalUser1.getPw());
        util.waitForPageLoaded();
        util.sleep(3);

        //해당 약속 삭제
        deleteSchedule(util,scheduleSubject);
    }


    /*
     * Step : 일정쓰기 > 일정쓰기_시간
     * Result : 해당하는 날짜에 시간 일정 생성 됨
     */

    //@Test
    public void TC_03_일정_일정쓰기_시간_Test() throws Exception {
        scheduleSubject = "일정쓰기_시간"+module.subjectKey;
        writeSchedule(util,scheduleSubject);
        if(util.waitForIsNotVisible(By.xpath("//div[@class='cont']/div[3][@class='selectbox13']")))
        {
            util.click(By.xpath("//input[@id='ch1_1']"));
        }
        saveSchedule(util,scheduleSubject);
        deleteSchedule(util,scheduleSubject);

    }


     /*
     * Step : 일정쓰기 > 일정쓰기_종일
     * Result : 해당하는 날짜에 종일 일정 생성 됨
     */

    //@Test
    public void TC_04_일정_일정쓰기_종일_Test() throws Exception {
        scheduleSubject = "일정쓰기_종일"+module.subjectKey;
        writeSchedule(util,scheduleSubject);

        //일정쓰기_종일 상태인지 확인
        if(util.waitForIsNotVisible(By.xpath("//div[@class='selectbox13 selectbox-disabled']")))
        {
            util.click(By.xpath("//input[@id='ch1_1']"));
        }
        saveSchedule(util,scheduleSubject);
        deleteSchedule(util,scheduleSubject);
    }


    /*
    * Step : 일정쓰기 > 일정쓰기_음력
    * Result : 해당하는 날짜에 음력 일정 생성 됨
    */

    //@Test
    public void TC_05_일정_일정쓰기_음력_Test() throws Exception {
        scheduleSubject = "일정쓰기_음력"+module.subjectKey;
        writeSchedule(util,scheduleSubject);

        //일정쓰기_종일 상태인지 확인
        if(util.waitForIsNotVisible(By.xpath("//div[@class='selectbox13 selectbox-disabled']")))
        {
            util.click(By.xpath("//input[@id='ch1_1']"));
        }
        //일정쓰기_종일_음력 상태인지 확인
        util.click(By.xpath("//div[@class='_lunar_type  selectbox13 lunar']"));
        util.waitForIsElementPresent(By.xpath("//div[contains(@class,'_lunar_type  selectbox13')]/div[2]/div/ul/li[2]"));
        util.click(By.xpath("//div[contains(@class,'_lunar_type  selectbox13')]/div[2]/div/ul/li[2]"));

        lunarDate = util.waitForIsElementPresent(By.xpath("//p[@class='_converted_solar_date time_default no_view']")).getText();
        lunarDate = lunarDate.substring(5).replace(".", "-");
        saveSchedule(util,scheduleSubject);

        //일정 확인하기 위해서 음력 날짜로 이동
        URL = module.calURL + "#{\"sSection\":\"scheduleMain\",\"oParameter\":{\"sViewType\":\"month\",\"sDate\":\"" + lunarDate + "\"}}";
        util.goTo(URL);
        util.waitForPageLoaded();
        util.sleep(3);

        //월뷰 목록뷰로 설정
        util.click(By.xpath("//button[contains(@class,'_day diary')]"));
        util.waitForIsElementPresent(By.xpath("//button[@class='_day diary on']"));
        util.waitForIsElementPresent(By.xpath("//td[contains(@class,'_schedule')]/div/div/a[contains(text(),'"+scheduleSubject+"')]"));
        util.sleep(3);

        assertTrue(util.waitForIsVisible(By.xpath("//td[contains(@class,'_schedule')]/div/div/a[contains(text(),'"+scheduleSubject+"')]")));
        util.click(By.xpath("//td[contains(@class,'_schedule')]/div/div/a[contains(text(),'"+scheduleSubject+"')]"));
        util.waitForIsElementPresent(By.xpath("//div[@class='ly_pop']"));
        util.click(By.xpath("//button[@class='_del_btn btn_default btn_default_v1']"));

        //일정이 생성된 캘린더가 공유캘린더 일 경우 예외처리
        //1. 얼럿이 노출되는지 확인 하고
        //2. 공유캘린더 인지 확인
        if(util.isAlertPresent(util)){
            alertText = util.getAlert().getText();
            assertTrue(alertText.contains("일정을 삭제하시겠습니까?"));
            util.getAlert().accept();
        }
        else if(util.waitForIsElementPresent(By.xpath("//div[@class='layer_content']")).isDisplayed()){
            util.waitForIsElementPresent(By.xpath("//button[@class='_ok normal btn_emphasis']"));
            util.click(By.xpath("//button[@class='_ok normal btn_emphasis']"));
        }
        assertTrue(util.waitForIsNotVisible(By.xpath("//a[contains(text(),'"+scheduleSubject+"')]")));

        //삭제 하고 다시 오늘 날짜로 복귀
        util.sleep(5);
        util.click(By.className("today"));
        util.waitForPageLoaded();
    }


    /*
    * Step : 일정쓰기 > 일정쓰기_윤달
    * Result : 해당하는 날짜에 윤달 일정 생성 됨
    */

    //@Test
    public void TC_06_일정_일정쓰기_윤달_Test() throws Exception {
        scheduleSubject = "일정쓰기_윤달"+module.subjectKey;
        writeSchedule(util,scheduleSubject);

        //일정쓰기_종일 상태인지 확인
        if(util.waitForIsNotVisible(By.xpath("//div[@class='selectbox13 selectbox-disabled']")))
        {
            util.click(By.xpath("//input[@id='ch1_1']"));
        }
        //일정쓰기_종일_음력 상태인지 확인
        util.click(By.xpath("//div[@class='_lunar_type  selectbox13 lunar']"));
        util.waitForIsElementPresent(By.xpath("//div[contains(@class,'_lunar_type  selectbox13')]/div[2]/div/ul/li[2]"));
        util.click(By.xpath("//div[contains(@class,'_lunar_type  selectbox13')]/div[2]/div/ul/li[2]"));

        //현재 날짜가 윤달이 가능한지에 관한 예외처리
        util.click(By.xpath("//input[@id='ch1_2']"));
        if(util.isAlertExist(util)){
            alertText = util.getAlert().getText();
            assertTrue(alertText.contains("선택하신 날짜는 윤달이 아닙니다. 날짜를 확인해 주세요."));
            util.printLog("현재 날짜는 윤달 설정이 불가능한 날짜입니다.");
            util.getAlert().accept();
            util.click(By.xpath("//button[@class='_footer_cancel_btn _cancel']"));
            util.getAlert().accept();
        }
        else{
            lunarDate = util.waitForIsElementPresent(By.xpath("//p[@class='_converted_solar_date time_default no_view']")).getText();
            lunarDate = lunarDate.substring(5).replace(".", "-");
            saveSchedule(util,scheduleSubject);

            //일정 확인하기 위해서 음력 날짜로 이동
            URL = module.calURL + "#{\"sSection\":\"scheduleMain\",\"oParameter\":{\"sViewType\":\"month\",\"sDate\":\"" + lunarDate + "\"}}";
            util.goTo(URL);
            util.waitForPageLoaded();
            deleteSchedule(util,scheduleSubject);

        }
        //삭제 하고 다시 오늘 날짜로 복귀
        util.sleep(3);
        util.click(By.className("today"));
        util.waitForPageLoaded();
    }

    /*
     * Step : 일정쓰기 > 일정쓰기_참석자
     * Result : 해당하는 날짜에 약속 생성 됨
     */

    //@Test
    public void TC_07_일정_일정쓰기_참석자_Test() throws Exception {
        scheduleSubject = "약속쓰기_참석자"+module.subjectKey;
        writeSchedule(util,scheduleSubject);

        util.type(By.xpath("//input[@id='nhn_cp_entry']"),TestIds.CalUser2.getId());
        util.click(By.xpath("//a[@class='_btn_add on']"));

        util.waitForIsElementPresent(By.xpath("//ul[@class='_new_invitee attendant_lst']"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//ul[@class='_new_invitee attendant_lst']")).isDisplayed());

        saveSchedule(util,scheduleSubject);
        deleteSchedule(util,scheduleSubject);
    }


    /*
    * Step : 일정쓰기 > 일정쓰기_공개
    * Result : 해당하는 날짜에 공개 일정 생성 됨
    */

    //@Test
    public void TC_08_일정_일정쓰기_공개_Test() throws Exception {
        scheduleSubject = "일정쓰기_공개"+module.subjectKey;
        writeSchedule(util,scheduleSubject);

        util.waitForIsElementPresent(By.xpath("//input[@id='public_default']"));
        util.click(By.xpath("//input[@id='public_default']"));

        saveSchedule(util,scheduleSubject);
        deleteSchedule(util,scheduleSubject);
    }


    /*
    * Step : 일정쓰기 > 일정쓰기_비공개
    * Result : 해당하는 날짜에 비공개 일정 생성 됨
    */

    //@Test
    public void TC_09_일정_일정쓰기_비공개_Test() throws Exception {
        scheduleSubject = "일정쓰기_비공개"+module.subjectKey;
        writeSchedule(util,scheduleSubject);

        util.waitForIsElementPresent(By.xpath("//input[@id='public_private']"));
        util.click(By.xpath("//input[@id='public_private']"));

        saveSchedule(util,scheduleSubject);
        deleteSchedule(util,scheduleSubject);
    }


    /*
    * Step : 일정쓰기 > 일정쓰기_캘린더
    * Result : 해당하는 날짜에 캘린더 변경된 일정 생성
    */

    //@Test
    public void TC_10_일정_일정쓰기_캘린더_Test() throws Exception {
        scheduleSubject = "일정쓰기_캘린더"+module.subjectKey;
        writeSchedule(util,scheduleSubject);

        util.waitForIsElementPresent(By.xpath("//div[@id='calendar_selectbox']"));
        util.click(By.xpath("//div[@id='calendar_selectbox']"));
        util.click(By.xpath("//div[@class='scroll selectbox-list']/ul/li[2]"));

        saveSchedule(util,scheduleSubject);
        deleteSchedule(util,scheduleSubject);
    }


    /*
    * Step : 일정쓰기 > 일정쓰기_범주
    * Result : 해당하는 날짜에 범주 변경된 일정 생성
    */

    //@Test
    public void TC_11_일정_일정쓰기_범주_Test() throws Exception {
        scheduleSubject = "일정쓰기_범주"+module.subjectKey;
        writeSchedule(util,scheduleSubject);

        //범주 옵션 변경
        maxColor = util.getXpathCount(By.xpath("//ul[@class='category_lst']/li"));

        for(int i=1; i<maxColor+1; i++ ){
            util.waitForIsElementPresent(By.xpath("//ul[@class='category_lst']"));
            util.click(By.xpath("//ul[@class='category_lst']/li["+i+"]/label"));
            assertTrue(util.waitForIsElementPresent(By.xpath("//ul[@class='category_lst']/li["+i+"]")).getAttribute("class").contains("selected"));
        }
        saveSchedule(util,scheduleSubject);
        deleteSchedule(util,scheduleSubject);
    }


        /*
    * Step : 일정쓰기 > 일정쓰기_캘린더
    * Result : 해당하는 날짜에 캘린더 변경된 일정 생성
    */

    //@Test
    public void TC_12_일정_일정쓰기_스티커_Test() throws Exception {
        scheduleSubject = "일정쓰기_스티커"+module.subjectKey;
        writeSchedule(util,scheduleSubject);

        util.click(By.xpath("//span[@class='h_cont']"));
        util.waitForIsElementPresent(By.xpath("//div[@class='layer_popup layer_add_sticker']"));
        //stickerCategoryNum = util.getXpathCount(By.xpath("//div[@class='sticker_category']/ul[contains(@style,'display: block')]/li"));
        stickerCategoryNum = 2;


        for(int i=1; i< stickerCategoryNum; i++){
            util.click(By.xpath("//div[@class='sticker_category']/ul/li["+i+"]/button"));
            //maxSticker = util.getXpathCount(By.xpath("//ul[contains(@class,'_sticker_list')]/li"));
            maxSticker = 3;
            for(int j=1; j < maxSticker; j++){
                stickerKey = util.waitForIsElementPresent(By.xpath("//ul[contains(@class,'_sticker_list')]/li["+j+"]")).getAttribute("key");
                util.waitForIsElementPresent(By.xpath("//ul[contains(@class,'_sticker_list')]/li["+j+"]"));

                util.click(By.xpath("//ul[contains(@class,'_sticker_list')]/li["+j+"]"));
                util.click(By.xpath("//button[@class='normal normal_v1 btn_emphasis _save']"));

                util.waitForIsElementPresent(By.xpath("//ul[@class='sticker_section']/li"));
                assertTrue(util.waitForIsElementPresent(By.xpath("//ul[@class='sticker_section']/li")).getAttribute("key").contains(stickerKey));
                util.click(By.xpath("//span[@class='h_cont']"));
                util.waitForIsElementPresent(By.xpath("//div[@class='layer_popup layer_add_sticker']"));
            }
        }
        util.click(By.xpath("//button[@class='normal normal_v1 btn_emphasis _save']"));

        saveSchedule(util,scheduleSubject);
        deleteSchedule(util,scheduleSubject);
    }


    /*
    * Step : 일정쓰기 > 일정쓰기_설명
    * Result : 해당하는 날짜에 설명 수정된 일정 생성
    */

    //@Test
    public void TC_13_일정_일정쓰기_설명_Test() throws Exception {
        scheduleSubject = "일정쓰기_설명"+module.subjectKey;
        writeSchedule(util,scheduleSubject);

        util.waitForIsElementPresent(By.xpath("//div[@class='txtbox focus_txt']"));
        util.click(By.xpath("//div[@class='txtbox focus_txt']"));
        util.type(By.xpath("//textarea[@class='txtbox']"),"설명");

        saveSchedule(util,scheduleSubject);
        deleteSchedule(util,scheduleSubject);
    }


    /*
    * Step : 일정쓰기 > 일정쓰기_이미지
    * Result : 해당하는 날짜에 이미지 첨부된 일정 생성
    */

    //@Test
    public void TC_14_일정_일정쓰기_이미지_Test() throws Exception {
        scheduleSubject = "일정쓰기_이미지"+module.subjectKey;
        writeSchedule(util,scheduleSubject);

        module.uploadImage(util);
        util.sleep(5);

        saveSchedule(util,scheduleSubject);
        deleteSchedule(util,scheduleSubject);
    }


    /*
    * Step : 일정쓰기 > 일정쓰기_파일
    * Result : 해당하는 날짜에 파일 첨부된 일정 생성
    */

    //@Test
    public void TC_15_일정_일정쓰기_파일_Test() throws Exception {
        scheduleSubject = "일정쓰기_파일"+module.subjectKey;
        writeSchedule(util,scheduleSubject);

        module.uploadFile(util);
        util.sleep(5);

        saveSchedule(util,scheduleSubject);
        deleteSchedule(util,scheduleSubject);
    }


    /*
    * Step : 일정쓰기 > 일정쓰기_알림
    * Result : 해당하는 날짜에 알림 수정된 일정 생성
    */

    //@Test
    public void TC_16_일정_일정쓰기_알림_Test() throws Exception {
        scheduleSubject = "일정쓰기_알림"+module.subjectKey;
        writeSchedule(util,scheduleSubject);

        util.click(By.xpath("//a[@class='_add_alarm btn_sy']"));

        saveSchedule(util,scheduleSubject);
        deleteSchedule(util,scheduleSubject);
    }


    /*
    * Step : 일정쓰기 > 일정쓰기_알림
    * Result : 해당하는 날짜에 알림 수정된 일정 생성
    */

    //@Test
    public void TC_17_일정_일정쓰기_중요_Test() throws Exception {
        scheduleSubject = "일정쓰기_중요"+module.subjectKey;
        writeSchedule(util,scheduleSubject);

        util.click(By.xpath("//input[@id='ch0_0']"));

        saveSchedule(util,scheduleSubject);
        deleteSchedule(util,scheduleSubject);
    }





}
