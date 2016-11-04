package com.naver.calendar.s04_환경설정;

import main.TestIds;
import main.Testcase;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;


public class suite_05_환경설정_위젯설정_Test extends Testcase {

    public String Title = null;
    public String URL = null;
    public String taskName;
    public String tempName;
    public String tasks[] = {};
    int maxTaskNum;
    public String alertText;

    /*
   * Step : 로그인 > 해당 계정으로 로그인
   * Result : 해당하는 계정으로 로그인 됨
    */
    @Test
    public void TC_00_위젯설정_로그인_Test() throws Exception {
        module.로그인(util, TestIds.CalUser.getId(), TestIds.CalUser.getPw());
        util.waitForPageLoaded();

    }


    /*
    * Step : 위젯설정 > 위젯 상태 확인
    * Result : 현재 위젯 설정 상태 확인
    */

    @Test
    public void TC_01_위젯설정_위젯상태_Test() throws Exception {

        util.sleep(3);
        util.click(By.className("_config"));
        util.waitForIsElementPresent(By.linkText("이전으로 돌아가기")).isDisplayed();

        util.click(By.xpath("//ul[@class='tab_setting tabs']/li[5]"));
        util.waitForIsElementPresent(By.xpath("//div[@class='_widget tc-panel tc-selected']"));

        if(util.waitForIsElementPresent(By.xpath("//input[@class='_on_widget inp01']")).isSelected()) {
            util.printLog("현재 위젯은 [사용함]으로 설정되어 있습니다.");
            assertTrue(util.isElementPresent(By.xpath("//input[@class='_on_widget inp01']")).isSelected());
        }
        else {
            util.printLog("현재 위젯은 [사용안함]으로 설정되어 있습니다.");
            assertTrue(util.isElementPresent(By.xpath("//input[@class='_off_widget inp01']")).isSelected());
        }

    }


    /*
    * Step : 위젯설정 > 위젯 사용으로 설정
    * Result : 위젯 설정 사용함으로 설정하면 위젯이 노출 됨
    */

    //@Test
    public void TC_02_위젯설정_위젯사용_Test() throws Exception {

        util.click(By.xpath("//ul[@class='tab_setting tabs']/li[5]"));
        util.waitForIsElementPresent(By.xpath("//div[@class='_widget tc-panel tc-selected']"));

        util.waitForIsElementPresent(By.xpath("//input[@class='_on_widget inp01']"));
        util.click(By.xpath("//input[@class='_on_widget inp01']"));

        //util.click(By.xpath("//button[@class='_save normal']"));

        util.getAlert(By.xpath("//button[@class='_save normal']"));
        util.getAlert().accept();

        util.waitForPageLoaded();
        util.waitForIsElementPresent(By.xpath("//div[@id='dh_aside_widget']"));
        util.sleep(5);
        assertTrue(util.waitForIsElementPresent(By.xpath("//div[@id='dh_aside_widget']")).isDisplayed());
    }

}
