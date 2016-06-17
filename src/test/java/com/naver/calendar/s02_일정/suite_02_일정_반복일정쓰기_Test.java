package com.naver.calendar.s02_일정;

import main.TestIds;
import main.Testcase;
import main.Utilities;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;


public class suite_02_일정_반복일정쓰기_Test extends Testcase {

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


        util.click(By.xpath("//button[contains(@class,'_list list')]"));
        util.waitForIsElementPresent(By.xpath("//button[@class='_list list on']"));

        util.click(By.xpath("//span[contains(text(),'약속쓰기')]"));
        assertTrue(util.waitForIsElementPresent(By.linkText("캘린더로 돌아가기")).isDisplayed());

        util.waitForIsElementPresent(By.xpath("//input[@id='tx0_0']"));
        util.type(By.xpath("//input[@id='tx0_0']"),subject);
    }


    public void saveSchedule(Utilities util, String subject) throws Exception{
        //저장버튼 클릭하여 일정 저장
        util.click(By.xpath("//button[@class ='btn_sys pos_save']"));
        util.sleep(3);
        if(util.waitForIsNotVisible(By.xpath("//div[@class='layer_content']"))){
        }
        else{
            util.waitForIsElementPresent(By.xpath("//button[@class='_ok normal']"));
            util.click(By.xpath("//button[@class='_ok normal']"));
        }
        util.waitForIsElementPresent(By.xpath("//button[contains(@class,'_go_task type_schedule todo')]"));
    }


    public void deleteSchedule(Utilities util, String subject) throws Exception{
        //일정 삭제
        assertTrue(util.waitForIsElementPresent(By.xpath("//a[contains(text(),'"+subject+"')]")).isDisplayed());
        util.click(By.xpath("//a[contains(text(),'"+subject+"')]"));
        util.waitForIsElementPresent(By.xpath("//div[@class='ly_pop']"));
        util.click(By.xpath("//button[@class='_del_btn btn_default btn_default_v1']"));
        util.sleep(3);

        //일정이 생성된 캘린더가 공유캘린더 일 경우 예외처리
        //1. 얼럿이 노출되는지 확인 하고
        //2. 공유캘린더 인지 확인
        if(util.isAlertNotExist(util)){
            alertText = util.getAlert().getText();
            assertTrue(alertText.contains("일정을 삭제하시겠습니까?"));
            util.getAlert().accept();
        }
        else if(util.waitForIsElementPresent(By.xpath("//div[@class='layer_content']")).isDisplayed()){
            util.waitForIsElementPresent(By.xpath("//div[@class='_repeat_del repeat_del']"));
            if(util.waitForIsElementPresent(By.xpath("//div[@class='_repeat_del repeat_del']")).isDisplayed()){
                util.waitForIsElementPresent(By.xpath("//input[@id='_wpageTMP8_repeat_del_all']"));
                util.click(By.xpath("//input[@id='_wpageTMP8_repeat_del_all']"));
            }
            util.waitForIsElementPresent(By.xpath("//button[@class='_ok normal']"));
            util.click(By.xpath("//button[@class='_ok normal']"));
        }

        assertTrue(util.waitForIsNotVisible(By.xpath("//a[contains(text(),'"+subject+"')]")));
    }




    /*
    * Step : 로그인 > 해당 계정으로 로그인
    * Result : 해당하는 계정으로 로그인 됨
     */
    @Test
    public void TC_00_반복일정_로그인_Test() throws Exception {
        module.로그인(util, TestIds.CalUser.getId(), TestIds.CalUser.getPw());
    }

     /*
     * Step : 일정 > 반복일정_매일일정쓰기
     * Result : 매일 반복되는 일정 생성 됨
     */

    @Test
    public void TC_01_반복일정_매일일정쓰기_Test() throws Exception {
        scheduleSubject = "반복일정쓰기_매일마다"+module.subjectKey;
        writeSchedule(util,scheduleSubject);
        util.printLog(scheduleSubject);

        util.click(By.xpath("//a[@class='_repeat_btn btn_sy repeat img_ri']"));
        util.waitForIsElementPresent(By.xpath("//div[@class='_repeat_layer dh_layer']"));

        util.click(By.xpath("//div[contains(@class,'_frequency_select  selectbox13')]"));
        util.waitForIsElementPresent(By.xpath("//div[contains(@class,'_frequency_select  selectbox13')]/div[2]/div/ul/li[1]"));
        util.click(By.xpath("//div[contains(@class,'_frequency_select  selectbox13')]/div[2]/div/ul/li[1]"));
        repeatText = util.waitForIsElementPresent(By.xpath("//div[@class='_repeat_txt repeat_view_section']/p")).getText();
        util.click(By.xpath("//button[@class='_save normal']"));

        util.waitForIsElementPresent(By.xpath("//div[@class='_repeat_text txt_nocycle']/p"));
        assertTrue(repeatText.contains(util.waitForIsElementPresent(By.xpath("//div[@class='_repeat_text txt_nocycle']/p")).getText()));

        saveSchedule(util,scheduleSubject);
        util.printLog(scheduleSubject);
        deleteSchedule(util,scheduleSubject);
    }


    /*
     * Step : 일정 > 반복일정_매주월금일정쓰기
     * Result : 매주월금 반복되는 일정 생성 됨
     */

    @Test
    public void TC_02_반복일정_매주월금일정쓰기_Test() throws Exception {
        scheduleSubject = "반복일정쓰기_매주월금마다"+module.subjectKey;
        writeSchedule(util,scheduleSubject);

        util.click(By.xpath("//a[@class='_repeat_btn btn_sy repeat img_ri']"));
        util.waitForIsElementPresent(By.xpath("//div[@class='_repeat_layer dh_layer']"));

        util.click(By.xpath("//div[contains(@class,'_frequency_select  selectbox13')]"));
        util.waitForIsElementPresent(By.xpath("//div[contains(@class,'_frequency_select  selectbox13')]/div[2]/div/ul/li[2]"));
        util.click(By.xpath("//div[contains(@class,'_frequency_select  selectbox13')]/div[2]/div/ul/li[2]"));
        repeatText = util.waitForIsElementPresent(By.xpath("//div[@class='_repeat_txt repeat_view_section']/p")).getText();
        util.click(By.xpath("//button[@class='_save normal']"));

        util.waitForIsElementPresent(By.xpath("//div[@class='_repeat_text txt_nocycle']/p"));
        util.printLog(repeatText);
        assertTrue(repeatText.contains(util.waitForIsElementPresent(By.xpath("//div[@class='_repeat_text txt_nocycle']/p")).getText()));

        saveSchedule(util,scheduleSubject);
        deleteSchedule(util,scheduleSubject);
    }


    /*
     * Step : 일정 > 반복일정_매주일정쓰기
     * Result : 매주 반복되는 일정 생성 됨
     */

    @Test
    public void TC_03_반복일정_매주일정쓰기_Test() throws Exception {
        scheduleSubject = "반복일정쓰기_매주마다"+module.subjectKey;
        writeSchedule(util,scheduleSubject);

        util.click(By.xpath("//a[@class='_repeat_btn btn_sy repeat img_ri']"));
        util.waitForIsElementPresent(By.xpath("//div[@class='_repeat_layer dh_layer']"));

        util.click(By.xpath("//div[contains(@class,'_frequency_select  selectbox13')]"));
        util.waitForIsElementPresent(By.xpath("//div[contains(@class,'_frequency_select  selectbox13')]/div[2]/div/ul/li[3]"));
        util.click(By.xpath("//div[contains(@class,'_frequency_select  selectbox13')]/div[2]/div/ul/li[3]"));
        repeatText = util.waitForIsElementPresent(By.xpath("//div[@class='_repeat_txt repeat_view_section']/p")).getText();
        util.click(By.xpath("//button[@class='_save normal']"));


        util.waitForIsElementPresent(By.xpath("//div[@class='_repeat_text txt_nocycle']/p"));
        util.printLog(repeatText);
        assertTrue(repeatText.contains(util.waitForIsElementPresent(By.xpath("//div[@class='_repeat_text txt_nocycle']/p")).getText()));

        saveSchedule(util,scheduleSubject);
        deleteSchedule(util,scheduleSubject);
    }


    /*
     * Step : 일정 > 반복일정_매월일정쓰기
     * Result : 매월 반복되는 일정 생성 됨
     */

    @Test
    public void TC_04_반복일정_매월일정쓰기_Test() throws Exception {
        scheduleSubject = "반복일정쓰기_매월마다"+module.subjectKey;
        writeSchedule(util,scheduleSubject);

        util.click(By.xpath("//a[@class='_repeat_btn btn_sy repeat img_ri']"));
        util.waitForIsElementPresent(By.xpath("//div[@class='_repeat_layer dh_layer']"));

        util.click(By.xpath("//div[contains(@class,'_frequency_select  selectbox13')]"));
        util.waitForIsElementPresent(By.xpath("//div[contains(@class,'_frequency_select  selectbox13')]/div[2]/div/ul/li[4]"));
        util.click(By.xpath("//div[contains(@class,'_frequency_select  selectbox13')]/div[2]/div/ul/li[4]"));
        repeatText = util.waitForIsElementPresent(By.xpath("//div[@class='_repeat_txt repeat_view_section']/p")).getText();
        util.click(By.xpath("//button[@class='_save normal']"));

        util.waitForIsElementPresent(By.xpath("//div[@class='_repeat_text txt_nocycle']/p"));
        util.printLog(repeatText);
        assertTrue(repeatText.contains(util.waitForIsElementPresent(By.xpath("//div[@class='_repeat_text txt_nocycle']/p")).getText()));

        saveSchedule(util,scheduleSubject);
        deleteSchedule(util,scheduleSubject);
    }


    /*
     * Step : 일정 > 반복일정_약속쓰기
     * Result : 반복되는 약속 생성 됨
     */

    @Test
    public void TC_05_반복일정_약속쓰기_Test() throws Exception {
        scheduleSubject = "반복일정쓰기_약속"+module.subjectKey;
        writeSchedule(util,scheduleSubject);

        util.click(By.xpath("//a[@class='_repeat_btn btn_sy repeat img_ri']"));
        util.waitForIsElementPresent(By.xpath("//div[@class='_repeat_layer dh_layer']"));

        util.click(By.xpath("//div[contains(@class,'_frequency_select  selectbox13')]"));
        util.waitForIsElementPresent(By.xpath("//div[contains(@class,'_frequency_select  selectbox13')]/div[2]/div/ul/li[4]"));
        util.click(By.xpath("//div[contains(@class,'_frequency_select  selectbox13')]/div[2]/div/ul/li[4]"));
        repeatText = util.waitForIsElementPresent(By.xpath("//div[@class='_repeat_txt repeat_view_section']/p")).getText();
        util.click(By.xpath("//button[@class='_save normal']"));

        util.waitForIsElementPresent(By.xpath("//div[@class='_repeat_text txt_nocycle']/p"));
        util.printLog(repeatText);
        assertTrue(repeatText.contains(util.waitForIsElementPresent(By.xpath("//div[@class='_repeat_text txt_nocycle']/p")).getText()));

        util.type(By.xpath("//input[@id='nhn_cp_entry']"),TestIds.CalUser2.getId());
        util.click(By.xpath("//a[@class='_btn_add on']"));

        util.waitForIsElementPresent(By.xpath("//ul[@class='_new_invitee attendant_lst']"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//ul[@class='_new_invitee attendant_lst']")).isDisplayed());

        saveSchedule(util,scheduleSubject);
        deleteSchedule(util,scheduleSubject);
    }


}
