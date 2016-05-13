package com.naver.calendar.s04_환경설정;

import main.TestIds;
import main.Testcase;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;


public class suite_01_환경설정_일반설정_Test extends Testcase {

    public String Title = null;
    public String URL = null;

    /*
   * Step : 로그인 > 해당 계정으로 로그인
   * Result : 해당하는 계정으로 로그인 됨
    */
    @Test
    public void TC_00_일반설정_로그인_Test() throws Exception {
        module.로그인(util, TestIds.CalUser.getId(), TestIds.CalUser.getPw());
    }

    /*
     * Step : 일반설정 > 캘린더 기본화면 확인
     * Result : 캘린더 기본 화면의 현재 값을 확인
     */
    @Test
    public void TC_01_일반설정_캘린더기본화면_Test() throws Exception {

        util.click(By.className("_config"));
        util.waitForIsElementPresent(By.linkText("캘린더로 돌아가기")).isDisplayed();

       if(util.waitForIsElementPresent(By.id("default_main_view_schedule")).isSelected()) {
            util.printLog("현재 캘린더 기본 화면은 [일정보기] 로 설정되어 있습니다.");
           assertTrue(util.isElementPresent(By.xpath("//input[@class='_cfg_default_main_view_mode inp01']")).getAttribute("value").contains("schedule_view"));
       }
        else {
            util.printLog("현재 캘린더 기본 화면은 [할일보기] 로 설정 되어 있습니다.");
           assertTrue(util.isElementPresent(By.xpath("//input[@class='_cfg_default_main_view_mode inp01']")).getAttribute("value").contains("task_view"));
        }
    }

    /*
     * Step : 일반설정 > 일정 기본 화면 상태 확인
     * Result : 일정 기본 화면의 현재 값을 확인
     */
    @Test
    public void TC_02_일반설정_일정기본화면_Test() throws Exception{

        String user;

        util.waitForIsElementPresent(By.linkText("캘린더로 돌아가기")).isDisplayed();

        if(util.waitForIsElementPresent(By.id("default_view_day")).isSelected()) {
            util.printLog("현재 일정 기본 화면은 [일별보기] 로 설정되어 있습니다.");
            assertTrue(util.isElementPresent(By.xpath("//input[@class='_cfg_default_view_mode inp01']")).getAttribute("value").contains("day"));
        }
        else if(util.waitForIsElementPresent(By.id("default_view_week")).isSelected()) {
            util.printLog("현재 캘린더 기본 화면은 [주별보기] 로 설정 되어 있습니다.");
            assertTrue(util.isElementPresent(By.xpath("//input[@class='_cfg_default_view_mode inp01']")).getAttribute("value").contains("week"));
        }
        else if(util.waitForIsElementPresent(By.id("default_view_month")).isSelected()) {
            util.printLog("현재 캘린더 기본 화면은 [월별보기] 로 설정 되어 있습니다.");
            assertTrue(util.isElementPresent(By.xpath("//input[contains(@class,'_cfg_default_view_mode inp01') and contains(@value,'month')]")).isSelected());
        }
        else if(util.waitForIsElementPresent(By.id("default_view_list")).isSelected()) {
            util.printLog("현재 캘린더 기본 화면은 [목록보기] 로 설정 되어 있습니다.");
            assertTrue(util.isElementPresent(By.xpath("//input[@class='_cfg_default_view_mode inp01']")).getAttribute("value").contains("list"));
        }
        else if(util.waitForIsElementPresent(By.id("default_view_user")).isSelected()) {
            util.printLog("현재 캘린더 기본 화면은 [사용자 설정 보기] 로 설정 되어 있습니다.");

            user = util.waitForIsElementPresent(By.xpath("//select[@class='_cfg_customdate day_select']/option")).getAttribute("value");
            util.printLog(user);

            assertTrue(util.isElementPresent(By.xpath("//input[@class='_cfg_default_view_mode inp01']")).getAttribute("value").contains("user"));
        }
    }

    /*
    * Step : 일반설정 > 단축키 사용 확인
    * Result : 단축키 사용의 현재 값을 확인
    */

    @Test
    public void TC_03_일반설정_단축키_Test() throws Exception {

        util.waitForIsElementPresent(By.linkText("캘린더로 돌아가기")).isDisplayed();

        if(util.waitForIsElementPresent(By.xpath("//input[contains(@class,'_cfg_hotkey_mode inp01')][contains(@value,'true')]")).isSelected()) {
            util.printLog("현재 단축키 사용은 [사용함] 으로 설정되어 있습니다.");
            assertTrue(util.isElementPresent(By.xpath("//label[@for='hotkey_mode']")).getText().contains("사용함"));
        }
        else {
            util.printLog("현재 단축키 사용은 [사용 안함] 로 설정 되어 있습니다.");
            assertTrue(util.isElementPresent(By.xpath("//input[contains(@class,'_cfg_hotkey_mode inp01')][contains(@value,'false')]")).isSelected());
        }
    }
}
