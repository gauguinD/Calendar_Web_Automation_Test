package com.naver.calendar.s02_일정;

import main.TestIds;
import main.Testcase;
import main.Utilities;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;


public class suite_01_일정_일정쓰기_Test extends Testcase {

    public String Title = null;
    public String URL = null;
    public String lunarDate = null;
    public String scheduleSubject =null;
    public String alertText=null;
    public String stickerKey=null;


    public int maxColor=0;
    public int maxSticker=0;
    public int stickerCategoryNum=0;

    public void writeSchedule(Utilities util, String subject) throws Exception{

        util.click(By.xpath("//span[contains(text(),'약속쓰기')]"));
        assertTrue(util.waitForIsElementPresent(By.linkText("캘린더로 돌아가기")).isDisplayed());

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
        }
        util.waitForIsElementPresent(By.xpath("//button[contains(@class,'_go_task type_schedule todo')]"));
    }


    public void deleteSchedule(Utilities util, String subject) throws Exception{
        //일정 삭제
        util.waitForIsElementPresent(By.xpath("//button[@class='_list list on']"));

        assertTrue(util.waitForIsElementPresent(By.xpath("//a[contains(text(),'"+subject+"')]")).isDisplayed());
        util.click(By.xpath("//a[contains(text(),'"+subject+"')]"));
        util.waitForIsElementPresent(By.xpath("//div[@class='ly_pop']"));
        util.click(By.xpath("//button[@class='_del_btn btn_default btn_default_v1']"));
        util.sleep(2);

        //일정이 생성된 캘린더가 공유캘린더 일 경우 예외처리
        //1. 얼럿이 노출되는지 확인 하고
        //2. 공유캘린더 인지 확인
        if(util.isAlertNotExist(util)){
            alertText = util.getAlert().getText();
            assertTrue(alertText.contains("일정을 삭제하시겠습니까?"));
            util.getAlert().accept();
        }
        else if(util.waitForIsElementPresent(By.xpath("//div[@class='layer_content']")).isDisplayed()){
            util.waitForIsElementPresent(By.xpath("//button[@class='_ok normal btn_emphasis']"));
            util.click(By.xpath("//button[@class='_ok normal btn_emphasis']"));
        }

        assertTrue(util.waitForIsNotVisible(By.xpath("//a[contains(text(),'"+subject+"')]")));
    }




    /*
    * Step : 로그인 > 해당 계정으로 로그인
    * Result : 해당하는 계정으로 로그인 됨
     */
    @Test
    public void TC_00_일정_로그인_Test() throws Exception {
        module.로그인(util, TestIds.CalUser.getId(), TestIds.CalUser.getPw());
    }

    /*
     * Step : 일정 > 일정쓰기
     * Result : 일정쓰기 제목으로 일정 생성 됨
     */

    @Test
    public void TC_01_일정_일정쓰기_Test() throws Exception {
        scheduleSubject = "일정쓰기"+module.subjectKey;
        util.waitForIsElementPresent(By.xpath("//button[contains(@class,'_list list')]"));
        util.click(By.xpath("//button[contains(@class,'_list list')]"));

        writeSchedule(util,scheduleSubject);
        saveSchedule(util,scheduleSubject);
        deleteSchedule(util,scheduleSubject);
    }


    /*
    * Step : 일정 > 일정쓰기_장소
    * Result : 장소 추가되어 일정 생성 됨
    */

    @Test
    public void TC_02_일정_일정쓰기_장소_Test() throws Exception {

        scheduleSubject = "일정쓰기_장소"+module.subjectKey;
        writeSchedule(util,scheduleSubject);

        util.type(By.xpath("//input[@id='tx1_0']"),"장소");

        saveSchedule(util,scheduleSubject);
        deleteSchedule(util,scheduleSubject);

    }


    /*
     * Step : 일정쓰기 > 일정쓰기_시간
     * Result : 해당하는 날짜에 시간 일정 생성 됨
     */

    @Test
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

    @Test
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

    @Test
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
        deleteSchedule(util,scheduleSubject);

        //삭제 하고 다시 오늘 날짜로 복귀
        util.click(By.className("today"));
        util.waitForPageLoaded();
    }


    /*
    * Step : 일정쓰기 > 일정쓰기_윤달
    * Result : 해당하는 날짜에 윤달 일정 생성 됨
    */

    @Test
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

            //삭제 하고 다시 오늘 날짜로 복귀
            util.click(By.className("today"));
            util.waitForPageLoaded();
        }
    }

    /*
     * Step : 일정쓰기 > 일정쓰기_참석자
     * Result : 해당하는 날짜에 약속 생성 됨
     */

    @Test
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

    @Test
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

    @Test
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

    @Test
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

    @Test
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

    @Test
    public void TC_12_일정_일정쓰기_스티커_Test() throws Exception {
        scheduleSubject = "일정쓰기_스티커"+module.subjectKey;
        writeSchedule(util,scheduleSubject);

        util.click(By.xpath("//span[@class='h_cont']"));
        util.waitForIsElementPresent(By.xpath("//div[@class='layer_popup layer_add_sticker']"));
        stickerCategoryNum = util.getXpathCount(By.xpath("//div[@class='sticker_category']/ul[contains(@style,'display: block')]/li"));
        //stickerCategoryNum = 2;


        for(int i=1; i< stickerCategoryNum; i++){
            util.click(By.xpath("//div[@class='sticker_category']/ul/li["+i+"]/button"));
            maxSticker = util.getXpathCount(By.xpath("//ul[contains(@class,'_sticker_list')]/li"));
            //maxSticker = 3;
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

    @Test
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

    @Test
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

    @Test
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

    @Test
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

    @Test
    public void TC_17_일정_일정쓰기_중요_Test() throws Exception {
        scheduleSubject = "일정쓰기_중요"+module.subjectKey;
        writeSchedule(util,scheduleSubject);

        util.click(By.xpath("//input[@id='ch0_0']"));

        saveSchedule(util,scheduleSubject);
        deleteSchedule(util,scheduleSubject);
    }





}
