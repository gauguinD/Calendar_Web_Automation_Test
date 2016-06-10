package com.naver.calendar.s02_일정;

import main.TestIds;
import main.Testcase;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;


public class suite_03_일정_기념일쓰기_Test extends Testcase {

    public String Title = null;
    public String URL = null;

    public String annivSubject;
    public String annivStartDate;
    public String annivYear;
    public String annivMonth;
    public String annivDay;

    public String stickerTitle;
    public String stickerAlt;
    public String tempTitle;
    public String tempAlt;
    public String alertText;
    public String stickerKey;
    public String tempName;
    public String calendarName;
    public String colorName;

    public int maxSticker;
    public int stickerCategoryNum;
    public int maxColor;







    /*
    * Step : 로그인 > 해당 계정으로 로그인
    * Result : 해당하는 계정으로 로그인 됨
    */

    @Test
    public void TC_00_기념일_로그인_Test() throws Exception {
        module.로그인(util, TestIds.CalUser.getId(), TestIds.CalUser.getPw());
    }

    /*
    * Step : 일정 > 기념일관리 클릭
    * Result : 기념일관리 페이지로 이동됨
    */

    @Test
    public void TC_01_기념일_기념일이동_Test() throws Exception {
        util.waitForIsElementPresent(By.xpath("//span[contains(text(),'기념일 관리')]"));
        util.click(By.xpath("//span[contains(text(),'기념일 관리')]"));

        assertTrue(util.waitForIsElementPresent(By.linkText("캘린더로 돌아가기")).isDisplayed());
        assertTrue(util.waitForIsElementPresent(By.xpath("//h3[contains(@class,'_header') and contains(text(),'기념일 관리')]")).isDisplayed());
    }


    /*
    * Step : 기념일관리 > 기념일 간편쓰기 클릭
    * Result : 간편쓰기에서 작성한 제목으로 기념일 생성됨
    */

    //@Test
    public void TC_02_기념일_기념일간편쓰기_Test() throws Exception {

        annivSubject = "기념일 간편쓰기"+module.subjectKey;

        util.waitForIsElementPresent(By.xpath("//input[@class='_content_text text dc_5']"));
        util.type(By.xpath("//input[@class='_content_text text dc_5']"),annivSubject);

        //미니 달력 레이어에서 현재 날짜 선택 되어있는 레이어 노출
        util.waitForIsElementPresent(By.xpath("//input[@class='_start_date dc text']"));
        util.click(By.xpath("//input[@class='_start_date dc text']"));
        util.waitForIsElementPresent(By.xpath("//div[@class='layer_calendar layer-show']"));

        //기념일에서 현재 일자에 있는 날짜를 가져옴
        annivYear = util.waitForIsElementPresent(By.xpath("//td[@class='calendar-date calendar-today calendar-selected']")).getAttribute("data-year");
        annivMonth = util.waitForIsElementPresent(By.xpath("//td[@class='calendar-date calendar-today calendar-selected']")).getAttribute("data-month");
        annivDay = util.waitForIsElementPresent(By.xpath("//td[@class='calendar-date calendar-today calendar-selected']")).getAttribute("data-date");
        if(Integer.parseInt(annivMonth)< 10){
            annivMonth = "0"+annivMonth;
            if(Integer.parseInt(annivDay)<10)
                annivDay="0"+annivDay;
        }
        annivStartDate = annivYear+"."+annivMonth+"."+annivDay;


        //오늘 날짜 선택해서 레이어 닫기
        util.waitForIsElementPresent(By.xpath("//td[@class='calendar-date calendar-today calendar-selected']"));
        util.click(By.xpath("//td[@class='calendar-date calendar-today calendar-selected']"));

        //기념일 추가하기 클릭
        util.waitForIsElementPresent(By.xpath("//button[@class='_add small']"));
        util.click(By.xpath("//button[@class='_add small']"));

        //annivSubject, annivStartDate 가지고 있는 기념일 있는지 확인
        util.waitForIsElementPresent(By.xpath("//a[contains(@class,'_quick_view') and contains(text(),'"+annivSubject+"')]"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//a[contains(@class,'_quick_view') and contains(text(),'"+annivSubject+"')]")).isDisplayed());
        util.waitForIsElementPresent(By.xpath("//td[contains(@class,'date') and contains(text(),'"+annivStartDate+"')]"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//td[contains(@class,'date') and contains(text(),'"+annivStartDate+"')]")).isDisplayed());

        module.deleteAnniv(util,module.subjectKey);
    }



    /*
    * Step : 기념일관리 > 기념일 간편쓰기 클릭
    * Result : 간편쓰기에서 작성한 제목으로 기념일 생성됨
    */

    //@Test
    public void TC_03_기념일_기념일간편쓰기_음력_Test() throws Exception {

        annivSubject = "기념일 간편쓰기_음력"+module.subjectKey;

        util.waitForIsElementPresent(By.xpath("//input[@class='_content_text text dc_5']"));
        util.type(By.xpath("//input[@class='_content_text text dc_5']"),annivSubject);

        //양력에서 음력으로 설정
        util.click(By.xpath("//div[@class='_lunar slt_type dc selectbox1 selectbox-naked']"));
        util.waitForIsElementPresent(By.xpath("//div[@class='_lunar slt_type dc selectbox1 selectbox-naked']"));
        util.waitForIsElementPresent(By.xpath("//div[@class='selectbox-layer']/ul/ul/li[2]"));
        util.click(By.xpath("//div[@class='selectbox-layer']/ul/ul/li[2]"));

        //미니 달력 레이어에서 현재 날짜 선택 되어있는 레이어 노출
        util.click(By.xpath("//input[@class='_start_date dc text']"));
        util.waitForIsElementPresent(By.xpath("//div[@class='layer_calendar layer-show']"));

        //변환이 불가능한 양력날짜 예외처리(5월 31일)
        if(util.isAlertExist(util)){
            util.getAlert().accept();
            util.click(By.xpath("//input[@class='_start_date dc text']"));
            util.waitForIsElementPresent(By.xpath("//div[@class='layer_calendar layer-show']"));
            util.click(By.xpath("//div[@class='layer_calendar layer-show']/table/tbody/tr[1]/td[1]"));

            util.waitForIsElementPresent(By.xpath("//td[@class='calendar-date calendar-today calendar-selected']"));
            util.click(By.xpath("//td[@class='calendar-date calendar-today calendar-selected']"));
        }


        //기념일에서 현재 일자에 있는 날짜를 가져옴
        annivYear = util.waitForIsElementPresent(By.xpath("//td[@class='calendar-date calendar-today calendar-selected']")).getAttribute("data-year");
        annivMonth = util.waitForIsElementPresent(By.xpath("//td[@class='calendar-date calendar-today calendar-selected']")).getAttribute("data-month");
        annivDay = util.waitForIsElementPresent(By.xpath("//td[@class='calendar-date calendar-today calendar-selected']")).getAttribute("data-date");
        if(Integer.parseInt(annivMonth)< 10){
            annivMonth = "0"+annivMonth;
            if(Integer.parseInt(annivDay)<10)
                annivDay="0"+annivDay;
        }
        annivStartDate = annivYear+"."+annivMonth+"."+annivDay;

        //오늘 날짜 선택해서 레이어 닫기
        util.waitForIsElementPresent(By.xpath("//td[contains(@class,'calendar-selected')]"));
        util.click(By.xpath("//td[contains(@class,'calendar-selected')]"));

        //기념일 추가하기 클릭
        util.waitForIsElementPresent(By.xpath("//button[@class='_add small']"));
        util.click(By.xpath("//button[@class='_add small']"));

        //annivSubject, annivStartDate 가지고 있는 기념일 있는지 확인
        util.waitForIsElementPresent(By.xpath("//a[contains(@class,'_quick_view') and contains(text(),'"+annivSubject+"')]"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//a[contains(@class,'_quick_view') and contains(text(),'"+annivSubject+"')]")).isDisplayed());
        util.waitForIsElementPresent(By.xpath("//td[contains(@class,'date') and contains(text(),'"+annivStartDate+"')]"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//td[contains(@class,'date') and contains(text(),'"+annivStartDate+"')]")).isDisplayed());

        module.deleteAnniv(util,annivSubject);
    }


    /*
    * Step : 기념일관리 > 기념일 간편쓰기 클릭
    * Result : 간편쓰기에서 작성한 제목으로 기념일 생성됨
    */

    //@Test
    public void TC_04_기념일_기념일간편쓰기_윤달_Test() throws Exception {

        annivSubject = "기념일 간편쓰기_음력_윤달"+module.subjectKey;

        util.waitForIsElementPresent(By.xpath("//input[@class='_content_text text dc_5']"));
        util.type(By.xpath("//input[@class='_content_text text dc_5']"),annivSubject);

        //양력에서 음력으로 설정
        util.click(By.xpath("//div[@class='_lunar slt_type dc selectbox1 selectbox-naked']"));
        util.waitForIsElementPresent(By.xpath("//div[@class='_lunar slt_type dc selectbox1 selectbox-naked']"));
        util.waitForIsElementPresent(By.xpath("//div[@class='selectbox-layer']/ul/ul/li[2]"));
        util.click(By.xpath("//div[@class='selectbox-layer']/ul/ul/li[2]"));

        util.waitForIsElementPresent(By.xpath("//input[@class='_leap input_chk']"));
        util.click(By.xpath("//input[@class='_leap input_chk']"));

        //미니 달력 레이어에서 현재 날짜 선택 되어있는 레이어 노출
        util.click(By.xpath("//input[@class='_start_date dc text']"));
        util.waitForIsElementPresent(By.xpath("//div[@class='layer_calendar layer-show']"));

        /*
        //변환이 불가능한 양력날짜 예외처리(5월 31일)
        if(util.isAlertExist(util)){
            util.getAlert().accept();
            util.click(By.xpath("//input[@class='_start_date dc text']"));
            util.waitForIsElementPresent(By.xpath("//div[@class='layer_calendar layer-show']"));
            util.click(By.xpath("//div[@class='layer_calendar layer-show']/table/tbody/tr[1]/td[1]"));

            util.waitForIsElementPresent(By.xpath("//td[@class='calendar-date calendar-today calendar-selected']"));
            util.click(By.xpath("//td[@class='calendar-date calendar-today calendar-selected']"));
        }
        */

        //기념일에서 현재 일자에 있는 날짜를 가져옴
        annivYear = util.waitForIsElementPresent(By.xpath("//td[@class='calendar-date calendar-today calendar-selected']")).getAttribute("data-year");
        annivMonth = util.waitForIsElementPresent(By.xpath("//td[@class='calendar-date calendar-today calendar-selected']")).getAttribute("data-month");
        annivDay = util.waitForIsElementPresent(By.xpath("//td[@class='calendar-date calendar-today calendar-selected']")).getAttribute("data-date");
        if(Integer.parseInt(annivMonth)< 10){
            annivMonth = "0"+annivMonth;
            if(Integer.parseInt(annivDay)<10)
                annivDay="0"+annivDay;
        }
        annivStartDate = annivYear+"."+annivMonth+"."+annivDay;

        //오늘 날짜 선택해서 레이어 닫기
        util.waitForIsElementPresent(By.xpath("//td[contains(@class,'calendar-selected')]"));
        util.click(By.xpath("//td[contains(@class,'calendar-selected')]"));

        //기념일 추가하기 클릭
        util.waitForIsElementPresent(By.xpath("//button[@class='_add small']"));
        util.click(By.xpath("//button[@class='_add small']"));

        if(util.isAlertExist(util)){
            alertText = util.getAlert().getText();
            util.printLog(alertText);
            util.getAlert().accept();
            assertTrue(alertText.contains("기념일에서 지원하는 날짜가 아닙니다."));
            util.printLog("현재 날짜는 윤달을 지정할 수 없습니다.");
        }
        else{
            //annivSubject, annivStartDate 가지고 있는 기념일 있는지 확인
            util.waitForIsElementPresent(By.xpath("//a[contains(@class,'_quick_view') and contains(text(),'"+annivSubject+"')]"));
            assertTrue(util.waitForIsElementPresent(By.xpath("//a[contains(@class,'_quick_view') and contains(text(),'"+annivSubject+"')]")).isDisplayed());
            util.waitForIsElementPresent(By.xpath("//td[contains(@class,'date') and contains(text(),'"+annivStartDate+"')]"));
            assertTrue(util.waitForIsElementPresent(By.xpath("//td[contains(@class,'date') and contains(text(),'"+annivStartDate+"')]")).isDisplayed());

            module.deleteAnniv(util,annivSubject);
        }
     }


    /*
    * Step : 기념일관리 > 기념일 간편쓰기 클릭
    * Result : 간편쓰기에서 작성한 제목으로 기념일 생성됨
    */

    //@Test
    public void TC_05_기념일간편쓰기_스티커쓰기_Test() throws Exception {

        annivSubject = "기념일 간편쓰기_스티커"+module.subjectKey;

        util.waitForIsElementPresent(By.xpath("//input[@class='_content_text text dc_5']"));
        util.type(By.xpath("//input[@class='_content_text text dc_5']"),annivSubject);

        //미니 달력 레이어에서 현재 날짜 선택 되어있는 레이어 노출
        util.click(By.xpath("//input[@class='_start_date dc text']"));
        util.waitForIsElementPresent(By.xpath("//div[@class='layer_calendar layer-show']"));

        //기념일에서 현재 일자에 있는 날짜를 가져옴
        annivYear = util.waitForIsElementPresent(By.xpath("//td[@class='calendar-date calendar-today calendar-selected']")).getAttribute("data-year");
        annivMonth = util.waitForIsElementPresent(By.xpath("//td[@class='calendar-date calendar-today calendar-selected']")).getAttribute("data-month");
        if(Integer.parseInt(annivMonth)< 10)
        {
            annivMonth = "0"+annivMonth;
        }
        annivDay = util.waitForIsElementPresent(By.xpath("//td[@class='calendar-date calendar-today calendar-selected']")).getAttribute("data-date");

        annivStartDate = annivYear+"."+annivMonth+"."+annivDay;

        //오늘 날짜 선택해서 레이어 닫기
        util.waitForIsElementPresent(By.xpath("//td[@class='calendar-date calendar-today calendar-selected']"));
        util.click(By.xpath("//td[@class='calendar-date calendar-today calendar-selected']"));

        for(int i=1; i<4; i++){

            util.click(By.xpath("//button[@class='_sticker_btn sticker_btn']"));
            util.click(By.xpath("//div[@class='sticker_category']/ul/li["+i+"]/button"));
            util.click(By.xpath("//ul[contains(@class,'_sticker_list')]/li[1]"));
            maxSticker = util.getXpathCount(By.xpath("//ul[contains(@class,'_sticker_list')]/li"));
            util.click(By.xpath("//button[@class='normal normal_v1 _save']"));

            for(int j=2; j<maxSticker; j++){
                util.click(By.xpath("//button[@class='_sticker_btn sticker_btn']"));
                util.waitForIsElementPresent(By.xpath("//div[@class='layer_content']"));

                util.waitForIsElementPresent(By.xpath("//ul[contains(@class,'_sticker_list')]/li["+j+"]"));
                //stickerAlt = util.waitForIsElementPresent(By.xpath("//li[contains(@class,'_sticker') and contains(@key,'"+i+"')]/button")).getAttribute("alt");
                stickerTitle = util.waitForIsElementPresent(By.xpath("//ul[contains(@class,'_sticker_list')]/li["+j+"]/button")).getAttribute("title");
                util.click(By.xpath("//ul[contains(@class,'_sticker_list')]/li["+j+"]"));
                util.click(By.xpath("//button[@class='normal normal_v1 _save']"));

                util.waitForIsElementPresent(By.xpath("//button[@class='_sticker_btn sticker_btn']"));
                //tempAlt = util.waitForIsElementPresent(By.xpath("//button[@class='_sticker_btn sticker_btn']/img")).getAttribute("alt");
                tempTitle = util.waitForIsElementPresent(By.xpath("//button[@class='_sticker_btn sticker_btn']")).getAttribute("title");
                if(stickerTitle.contains(tempTitle)){
                }
                else{
                    util.printLog("\n스티커 이름은 :"+stickerTitle+" Temp 이름은 :"+tempTitle);
                }
            }
        }

        //기념일 추가하기 클릭
        util.waitForIsElementPresent(By.xpath("//button[@class='_add small']"));
        util.click(By.xpath("//button[@class='_add small']"));

        //annivSubject, annivStartDate 가지고 있는 기념일 있는지 확인
        util.waitForIsElementPresent(By.xpath("//a[contains(@class,'_quick_view') and contains(text(),'"+annivSubject+"')]"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//a[contains(@class,'_quick_view') and contains(text(),'"+annivSubject+"')]")).isDisplayed());
        util.waitForIsElementPresent(By.xpath("//td[contains(@class,'date') and contains(text(),'"+annivStartDate+"')]"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//td[contains(@class,'date') and contains(text(),'"+annivStartDate+"')]")).isDisplayed());

        module.deleteAnniv(util,annivSubject);
    }



    /*
    * Step : 기념일관리 > 기념일 상세쓰기 > 100일 간격으로 기념일 생성
    * Result : 100일 간격으로 기념일 생성됨
    */

    //@Test
    public void TC_06_기념일_기념일상세쓰기_100일_Test() throws Exception {

        annivSubject = "기념일 상세쓰기_100일"+module.subjectKey;

        //기념일 상세쓰기 진입
        util.click(By.xpath("//a[@class='_detail_add dc_8']"));
        util.waitForIsElementPresent(By.xpath("//div[@class='schedule_section']"));
        util.type(By.xpath("//input[@class='_contentText input_txt pos_tit']"),annivSubject);
        util.click(By.xpath("//a[@class='_editRecurrence btn_sy']"));

        //반복 옵션 진입
        util.waitForIsElementPresent(By.xpath("//div[@class='layer_content']"));
        util.click(By.xpath("//div[@class='_frequencySelectbox  selectbox13']"));

        //100일 선택
        util.waitForIsElementPresent(By.xpath("//div[contains(@class,'_frequencySelectbox  selectbox13')]/div[2]"));
        util.click(By.xpath("//div[contains(@class,'_frequencySelectbox  selectbox13')]/div[2]/div/ul/li[2]"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//div[contains(@class,'_frequencySelectbox  selectbox13')]/div[1]/div")).getText().contains("100일"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//p[@class='_preview']/span[2]")).getText().contains("100일"));

        //저장버튼 탭
        util.waitForIsElementPresent(By.xpath("//button[@class='_save normal']"));
        util.click(By.xpath("//button[@class='_save normal']"));
        //빈도 노출되는것 확인
        util.waitForIsElementPresent(By.xpath("//span[@class='_frequency']"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//span[@class='_frequency']")).getText().contains("100일"));

        util.waitForIsElementPresent(By.xpath("//button[@class='_saveBtn btn_sys pos_save']"));
        util.click(By.xpath("//button[@class='_saveBtn btn_sys pos_save']"));

        module.deleteAnniv(util,module.subjectKey);
    }

        /*
    * Step : 기념일관리 > 기념일 상세쓰기 > 1000일 간격으로 기념일 생성
    * Result : 1000일 간격으로 기념일 생성됨
    */

    //@Test
    public void TC_07_기념일_기념일상세쓰기_1000일_Test() throws Exception {

        annivSubject = "기념일 상세쓰기_1000일"+module.subjectKey;

        //기념일 상세쓰기 진입
        util.click(By.xpath("//a[@class='_detail_add dc_8']"));
        util.waitForIsElementPresent(By.xpath("//div[@class='schedule_section']"));
        util.type(By.xpath("//input[@class='_contentText input_txt pos_tit']"),annivSubject);
        util.click(By.xpath("//a[@class='_editRecurrence btn_sy']"));

        //반복 옵션 진입
        util.waitForIsElementPresent(By.xpath("//div[@class='layer_content']"));
        util.click(By.xpath("//div[@class='_frequencySelectbox  selectbox13']"));

        //100일 선택
        util.waitForIsElementPresent(By.xpath("//div[contains(@class,'_frequencySelectbox  selectbox13')]/div[2]"));
        util.click(By.xpath("//div[contains(@class,'_frequencySelectbox  selectbox13')]/div[2]/div/ul/li[3]"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//div[contains(@class,'_frequencySelectbox  selectbox13')]/div[1]/div")).getText().contains("1000일"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//p[@class='_preview']/span[2]")).getText().contains("1000일"));

        //저장버튼 탭
        util.waitForIsElementPresent(By.xpath("//button[@class='_save normal']"));
        util.click(By.xpath("//button[@class='_save normal']"));
        //빈도 노출되는것 확인
        util.waitForIsElementPresent(By.xpath("//span[@class='_frequency']"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//span[@class='_frequency']")).getText().contains("1000일"));

        util.waitForIsElementPresent(By.xpath("//button[@class='_saveBtn btn_sys pos_save']"));
        util.click(By.xpath("//button[@class='_saveBtn btn_sys pos_save']"));

        module.deleteAnniv(util,module.subjectKey);
    }


    /*
    * Step : 기념일관리 > 기념일 간편쓰기 클릭
    * Result : 간편쓰기에서 작성한 제목으로 기념일 생성됨
    */

    //@Test
    public void TC_08_기념일_기념일상세쓰기_스티커쓰기_Test() throws Exception {


        annivSubject = "기념일 상세쓰기_스티커"+module.subjectKey;

        //기념일 상세쓰기 진입
        util.click(By.xpath("//a[@class='_detail_add dc_8']"));
        util.waitForIsElementPresent(By.xpath("//div[@class='schedule_section']"));
        util.type(By.xpath("//input[@class='_contentText input_txt pos_tit']"),annivSubject);

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
                util.printLog(stickerKey);
                util.waitForIsElementPresent(By.xpath("//ul[contains(@class,'_sticker_list')]/li["+j+"]"));

                util.click(By.xpath("//ul[contains(@class,'_sticker_list')]/li["+j+"]"));
                util.click(By.xpath("//button[@class='normal normal_v1 _save']"));

                util.waitForIsElementPresent(By.xpath("//ul[@class='sticker_section']/li"));
                util.printLog(util.waitForIsElementPresent(By.xpath("//ul[@class='sticker_section']/li")).getAttribute("key"));
                assertTrue(util.waitForIsElementPresent(By.xpath("//ul[@class='sticker_section']/li")).getAttribute("key").contains(stickerKey));
                util.click(By.xpath("//span[@class='h_cont']"));
                util.waitForIsElementPresent(By.xpath("//div[@class='layer_popup layer_add_sticker']"));
            }
        }
        util.click(By.xpath("//button[@class='normal normal_v1 _save']"));

        //기념일 관리 클릭
        util.waitForIsElementPresent(By.xpath("//button[@class='_saveBtn btn_sys pos_save']"));
        util.click(By.xpath("//button[@class='_saveBtn btn_sys pos_save']"));

        util.waitForIsElementPresent(By.xpath("//span[contains(text(),'기념일 관리')]"));
        util.click(By.xpath("//span[contains(text(),'기념일 관리')]"));

        //annivSubject, annivStartDate 가지고 있는 기념일 있는지 확인
        util.waitForIsElementPresent(By.xpath("//a[contains(@class,'_quick_view') and contains(text(),'"+annivSubject+"')]"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//a[contains(@class,'_quick_view') and contains(text(),'"+annivSubject+"')]")).isDisplayed());

        module.deleteAnniv(util,annivSubject);
    }


    /*
    * Step : 기념일관리 > 기념일 간편쓰기 클릭
    * Result : 간편쓰기에서 작성한 제목으로 기념일 생성됨
    */

    //@Test
    public void TC_09_기념일_기념일상세쓰기_캘린더_Test() throws Exception {

        annivSubject = "기념일 상세쓰기_캘린더"+module.subjectKey;

        //기념일 상세쓰기 진입
        util.click(By.xpath("//a[@class='_detail_add dc_8']"));
        util.waitForIsElementPresent(By.xpath("//div[@class='schedule_section']"));
        util.type(By.xpath("//input[@class='_contentText input_txt pos_tit']"),annivSubject);

        //캘린더 옵션 변경
        util.waitForIsElementPresent(By.xpath("//div[@class='calendar_name']/div[1]/div"));
        tempName = util.waitForIsElementPresent(By.xpath("//div[@class='calendar_name']/div[1]/div")).getText();

        //저장 버튼 탭
        util.waitForIsElementPresent(By.xpath("//button[@class='_saveBtn btn_sys pos_save']"));
        util.click(By.xpath("//button[@class='_saveBtn btn_sys pos_save']"));

        //기념일 관리 클릭
        util.waitForIsElementPresent(By.xpath("//span[contains(text(),'기념일 관리')]"));
        util.click(By.xpath("//span[contains(text(),'기념일 관리')]"));

        //annivSubject 가지고 있는 기념일 확인 해당 기념일 calendarName 확인
        util.waitForIsElementPresent(By.xpath("//a[contains(@class,'_quick_view') and contains(text(),'"+annivSubject+"')]"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//a[contains(@class,'_quick_view') and contains(text(),'"+annivSubject+"')]")).isDisplayed());
        calendarName = util.waitForIsElementPresent(By.xpath("//div[contains(@class,'_memorialday_wrap_table ts scroll') and ./table/tbody/tr/td[4]/a[contains(text(),'"+annivSubject+"')]]/table/tbody/tr/td[3]")).getText();
        assertTrue(tempName.contains(calendarName));

        //해당하는 제목을 가진 캘린더 삭제
        module.deleteAnniv(util,module.subjectKey);
    }


    /*
    * Step : 기념일관리 > 기념일 간편쓰기 클릭
    * Result : 간편쓰기에서 작성한 제목으로 기념일 생성됨
    */

    @Test
    public void TC_10_기념일_기념일상세쓰기_범주_Test() throws Exception {

        annivSubject = "기념일 상세쓰기_범주"+module.subjectKey;

        //기념일 상세쓰기 진입
        util.click(By.xpath("//a[@class='_detail_add dc_8']"));
        util.waitForIsElementPresent(By.xpath("//div[@class='schedule_section']"));
        util.type(By.xpath("//input[@class='_contentText input_txt pos_tit']"),annivSubject);

        //범주 옵션 변경
        maxColor = util.getXpathCount(By.xpath("//ul[@class='category_lst']/li"));

        for(int i=1; i<maxColor+1; i++ ){
            util.waitForIsElementPresent(By.xpath("//ul[@class='category_lst']"));
            util.click(By.xpath("//ul[@class='category_lst']/li["+i+"]/label"));
            assertTrue(util.waitForIsElementPresent(By.xpath("//ul[@class='category_lst']/li["+i+"]")).getAttribute("class").contains("selected"));
        }

        //저장 버튼 탭
        util.waitForIsElementPresent(By.xpath("//button[@class='_saveBtn btn_sys pos_save']"));
        util.click(By.xpath("//button[@class='_saveBtn btn_sys pos_save']"));

        //기념일 관리 클릭
        util.waitForIsElementPresent(By.xpath("//span[contains(text(),'기념일 관리')]"));
        util.click(By.xpath("//span[contains(text(),'기념일 관리')]"));

        //annivSubject 가지고 있는 기념일 확인 해당 기념일 calendarName 확인
        util.waitForIsElementPresent(By.xpath("//a[contains(@class,'_quick_view') and contains(text(),'"+annivSubject+"')]"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//a[contains(@class,'_quick_view') and contains(text(),'"+annivSubject+"')]")).isDisplayed());

        //해당하는 제목을 가진 캘린더 삭제
        module.deleteAnniv(util,module.subjectKey);
    }


}
