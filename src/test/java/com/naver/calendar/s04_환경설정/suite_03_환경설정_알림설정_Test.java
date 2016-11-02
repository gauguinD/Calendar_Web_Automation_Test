package com.naver.calendar.s04_환경설정;

import main.TestIds;
import main.Testcase;
import main.Utilities;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;


public class suite_03_환경설정_알림설정_Test extends Testcase {

    public String Title = null;
    public String URL = null;


    public String basicValue;
    public String basicCalName;
    public int randomNum;


    public String tempValue = null;
    public String tempCalName = null;
    public int maxCalNum;
    public int tempNum;



    public void settingClick(Utilities util) throws Exception {
        util.waitForIsElementPresent(By.xpath("//a[@class='_config']"));
        util.click(By.xpath("//a[@class='_config']"));
        util.waitForIsElementPresent(By.linkText("이전으로 돌아가기"));
    }

    /*
   * Step : 로그인 > 해당 계정으로 로그인
   * Result : 해당하는 계정으로 로그인 됨
    */
    @Test
    public void TC_00_알림설정_로그인_Test() throws Exception {
        module.로그인(util, TestIds.CalUser.getId(), TestIds.CalUser.getPw());
    }


    /*
    * Step : 알림설정 > 일정 미리 알림 수신 설정
    * Result : 일정 미리 알림 수신의 현재 값을 확인
    */

    @Test
    public void TC_01_알림설정_일정미리알림수신_Test() throws Exception {

        util.click(By.className("_config"));
        util.waitForIsElementPresent(By.linkText("이전으로 돌아가기")).isDisplayed();

        util.click(By.xpath("//ul[@class='tab_setting tabs']/li[3]"));
        util.waitForIsElementPresent(By.xpath("//div[@class='_alarm tc-panel tc-selected']"));

        //클래스 이름으로 알림 값 ON인지 OFF인지 확인
        if(util.waitForIsNotVisible(By.xpath("//button[@class='_popup btn_switch on']"))){
            util.waitForIsElementPresent(By.xpath("//button[@class='_popup btn_switch']"));
            assertTrue(util.waitForIsElementPresent(By.xpath("//button[@class='_popup btn_switch']")).isDisplayed());
            util.printLog("현재 PC웹 팝업 알림 받기 값은 OFF 입니다.");
        }
        else{
            assertTrue(util.waitForIsElementPresent(By.xpath("//button[@class='_popup btn_switch on']")).isDisplayed());
            util.printLog("현재 PC웹 팝업 알림 받기 값은 ON 입니다.");
        }

        //클래스 이름으로 알림 값 ON인지 OFF인지 확인
        if(util.waitForIsNotVisible(By.xpath("//button[@class='_mail btn_switch on']"))){
            util.waitForIsElementPresent(By.xpath("//button[@class='_mail btn_switch']"));
            assertTrue(util.waitForIsElementPresent(By.xpath("//button[@class='_mail btn_switch']")).isDisplayed());
            util.printLog("현재 메일 알림 받기 값은 OFF 입니다.");
        }
        else{
            assertTrue(util.waitForIsElementPresent(By.xpath("//button[@class='_mail btn_switch on']")).isDisplayed());
            util.printLog("현재 메일 알림 받기 값은 ON 입니다.");
        }

    }

    /*
    * Step : 알림설정 > 일정 미리 알림 수신 설정
    * Result : 일정 미리 알림 수신의 현재 값을 확인
    */

    @Test
    public void TC_02_알림설정_미리알림기본값설정_Test() throws Exception{

        String calName;
        String timeAlert;
        String dayAlert;

        maxCalNum = util.getXpathCount(By.xpath("//tbody[@class='_calendar_alaram_list']/tr"));
        //maxCalNum = 2;


        int randomNum;
        randomNum = util.getRandomNum(1,12);

        String fixedTimeAlert;
        String fixedDayAlert;

        util.waitForIsElementPresent(By.xpath("//div[@class='_alarm tc-panel tc-selected']"));

        for(int i=1; i<maxCalNum+1; i++)
        {
            calName = util.waitForIsElementPresent(By.xpath("//tbody[@class='_calendar_alaram_list']/tr["+i+"]/td[1]/a/strong")).getText();
            timeAlert = util.waitForIsElementPresent(By.xpath("//tbody[@class='_calendar_alaram_list']/tr["+i+"]/td[2]/span[1]")).getText();
            dayAlert = util.waitForIsElementPresent(By.xpath("//tbody[@class='_calendar_alaram_list']/tr["+i+"]/td[2]/span[2]")).getText();

            util.printLog("현재 "+calName+"의 알림 상태는 \n시간일정 :"+timeAlert+"\n종일일정 :"+dayAlert);

            //수정버튼 클릭해서 캘린더 상세 설정으로 이동
            util.waitForIsElementPresent(By.xpath("//tbody[@class='_calendar_alaram_list']/tr["+i+"]/td[3]/button"));
            util.click(By.xpath("//tbody[@class='_calendar_alaram_list']/tr["+i+"]/td[3]/button"));

            util.waitForIsElementPresent(By.linkText("캘린더 상세 보기"));

            //캘린더 상세 설정에서 시간일정 12개중 임의의값 선택
            util.waitForIsElementPresent(By.xpath("//div[contains(@class,'_schedule_alarm_time')]"));
            util.click(By.xpath("//div[contains(@class,'_schedule_alarm_time')]"));

            util.waitForIsElementPresent(By.xpath("//div[contains(@class,'_schedule_alarm_time')]/div[2]/div/ul/li["+randomNum+"]"));
            util.sleep(1);
            fixedTimeAlert = util.waitForIsElementPresent(By.xpath("//div[contains(@class,'_schedule_alarm_time')]/div[2]/div/ul/li["+randomNum+"]")).getText();
            util.click(By.xpath("//div[contains(@class,'_schedule_alarm_time')]/div[2]/div/ul/li["+randomNum+"]"));

            util.waitForIsElementPresent(By.xpath("//div[contains(@class,'_anniversary_alarm_time')]"));
            util.click(By.xpath("//div[contains(@class,'_anniversary_alarm_time')]"));
            util.waitForPageLoaded();

            util.waitForIsElementPresent(By.xpath("//div[contains(@class,'_anniversary_alarm_time')]"));
            fixedDayAlert = util.waitForIsElementPresent(By.xpath("//div[contains(@class,'_anniversary_alarm_time')]/div[2]/div/ul/li["+randomNum/3+"]")).getText();
            util.click(By.xpath("//div[contains(@class,'_anniversary_alarm_time')]/div[2]/div/ul/li["+randomNum/3+"]"));

            //설정 저장
            util.waitForIsElementPresent(By.xpath("//button[@class='_save normal']"));
            util.click(By.xpath("//button[@class='_save normal']"));
            util.waitForPageLoaded();

            //변경된 알림을 기존 일정에도 적용하시겠습니까 관련 예외처리
            if(util.waitForIsNotVisible(By.xpath("//button[@class='normal _no']"))){
                util.sleep(1);
             }
            else
            {
                util.waitForIsElementPresent(By.xpath("//button[@class='normal _no']"));
                util.click(By.xpath("//button[@class='normal _no']"));
            }
            //환경설정 재 진입
            settingClick(util);
            util.sleep(1);

            util.waitForIsElementPresent(By.xpath("//ul[@class='tab_setting tabs']/li[3]"));
            util.click(By.xpath("//ul[@class='tab_setting tabs']/li[3]"));

            timeAlert = util.waitForIsElementPresent(By.xpath("//tbody[@class='_calendar_alaram_list']/tr["+i+"]/td[2]/span[1]")).getText();
            dayAlert = util.waitForIsElementPresent(By.xpath("//tbody[@class='_calendar_alaram_list']/tr["+i+"]/td[2]/span[2]")).getText();

            util.waitForIsElementPresent(By.xpath("//div[@class='_alarm tc-panel tc-selected']"));
            //수정한값과 현재 노출되는값이 동일한지 확인
            util.printLog("환경설정의 알림값 "+timeAlert+"\n캘린더 설정에서의 알림값 "+fixedTimeAlert);
            assertTrue(timeAlert.contains(fixedTimeAlert));
            assertTrue(dayAlert.contains(fixedDayAlert));

            /*
            for(int j=1; j<13; j++)
            {
                //미리알림 기본값 시간 알림값 변경
                util.waitForIsElementPresent(By.xpath("//select[@class='_schedule_alarm_time']/option["+i+"]"));
                util.click(By.xpath("//select[@class='_schedule_alarm_time']/option["+i+"]"));
                fixedTimeAlert = util.waitForIsElementPresent(By.xpath("//select[@class='_schedule_alarm_time']/option["+i+"]")).getText();
                for(int k=1; k<4; k++){
                    //미리알림 기본값 종일 알림값 변경
                    util.waitForIsElementPresent(By.xpath("//select[@class='_anniversary_alarm_time']/option["+k+"]"));
                    util.click(By.xpath("//select[@class='_anniversary_alarm_time']/option["+k+"]"));
                    fixedDayAlert = util.waitForIsElementPresent(By.xpath("//select[@class='_anniversary_alarm_time']/option["+k+"]")).getText();
                }

                util.waitForIsElementPresent(By.xpath("//button[@class='_save normal']"));
                util.click(By.xpath("//button[@class='_save normal']"));

                //변경된 알림을 기존 일정에도 적용하시겠습니까 관련 예외처리
                if(util.isElementPresentNoExist(By.xpath("//div[@class='dh_layer']"))){
                    //환경설정 재진입
                    util.waitForIsElementPresent(By.xpath("//a[@class='btn_settingcal']"));
                    util.click(By.xpath("//a[@class='btn_settingcal']"));

                    util.waitForIsElementPresent(By.xpath("//ul[@class='tab_setting tabs']/li[3]"));
                    util.click(By.xpath("//ul[@class='tab_setting tabs']/li[3]"));

                    util.waitForIsElementPresent(By.xpath("//div[@class='_alarm tc-panel tc-selected']"));

                    assertTrue(timeAlert.contains(fixedTimeAlert));
                    assertTrue(dayAlert.contains(fixedDayAlert));
                }
                else{
                    //변경된 알림을 기존 일정에도 적용하시겠습니까 관련 예외처리
                    util.waitForIsElementPresent(By.xpath("//button[@class='normal _no']"));
                    util.click(By.xpath("//button[@class='normal _no']"));
                }
            }
            //정시,5분전,10분전,15분전,30분전,1시간전,2시간전,3시간전,12시간전,1일전,2일전,1주전
            //당일정오,당일오전,하루전정오,일주일전정오

        }
        */



        }
    }

}
