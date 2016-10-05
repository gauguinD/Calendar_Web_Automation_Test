package com.naver.calendar.s05_초기화;

import main.TestIds;
import main.Testcase;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;


public class suite_01_초기화_일반설정_Test extends Testcase {

    public String Title = null;
    public String URL = null;

    /*
    * Step : 로그인 > 해당 계정으로 로그인
    * Result : 해당하는 계정으로 로그인 됨
    */
    @Test
    public void TC_00_Footer_로그인_Test() throws Exception {
        //util.goTo("https://calendar.worksmobile.com");

        module.로그인(util, TestIds.CalUser.getId(), TestIds.CalUser.getPw());
    }

    /*
    * Step : 로그인 > 해당 계정으로 로그인
    * Result : 해당하는 계정으로 로그인 됨
    */

    //@Test
    public void TC_00_Works_로그인_Test() throws Exception {
        util.goTo("https://calendar.worksmobile.com");
        module.LogInWorksCal(util, TestIds.WorksCalUser4.getId(), TestIds.WorksCalUser4.getPw());
    }

    /*
     * Step : 일반설정 > 캘린더 기본화면 확인
    * Result : 캘린더 기본 화면의 현재 값을 확인
    */

    //@Test
    public void TC_01_Works_일정쓰기_Test()throws Exception {

        for(int i=0; i<400; i++){
            util.waitForIsElementPresent(By.xpath("//a[@class='write_schedule single']"));
            util.click(By.xpath("//a[@class='write_schedule single']"));

            util.waitForIsElementPresent(By.xpath("//input[@class='_content_text input_txt pos_tit']"));
            util.type(By.xpath("//input[@class='_content_text input_txt pos_tit']"), "일정 :"+i);

            //util.click(By.xpath("//button[@class='save _save_btn']"));
            util.click(By.xpath("//button[@class='save _save_btn _save']"));

            //util.waitForIsElementPresent(By.xpath("//div[@class='layer_content']"));
            //util.click(By.xpath("//button[@class='_ok normal']"));

            util.waitForIsElementPresent(By.xpath("//a[@title='일정 :"+i+"']"));
            util.click(By.xpath("//a[@title='일정 :"+i+"']"));

            util.waitForIsElementPresent(By.xpath("//div[@class='_quick_schedule_view ly_quick_wrap long_width no_view']"));
            util.click(By.xpath("//button[@class='_del_btn btn_default btn_default_v1']"));

            //util.waitForIsElementPresent(By.xpath("//div[@class='_schedule_entry_popup schedule_entry_alarm_layer layer_popup no_view select_free_layer join_schedule_edit_layer']"));
            //util.click(By.xpath("//button[@class='_ok normal']"));

            util.getAlert().accept();
            util.waitForPageLoaded();
            util.sleep(3);
        }



        //환경설정 이동
        util.click(By.className("_config"));
        util.waitForIsElementPresent(By.linkText("캘린더로 돌아가기"));

        //환경설정 > 일정설정 이동
        util.click(By.xpath("//ul[@class='tab_setting tabs']/li[2]"));
        util.waitForIsElementPresent(By.xpath("//div[@class='_schedule tc-panel tc-selected']"));

    }


    /*
     * Step : 일반설정 > 캘린더 기본화면 확인
     * Result : 캘린더 기본 화면의 현재 값을 확인
     */
    //@Test
    public void TC_02_일반설정_캘린더기본화면_Test() throws Exception {

        //환경설정 이동
        util.click(By.className("_config"));
        util.waitForIsElementPresent(By.linkText("캘린더로 돌아가기"));

        //환경설정 > 일정설정 이동
        util.click(By.xpath("//ul[@class='tab_setting tabs']/li[2]"));
        util.waitForIsElementPresent(By.xpath("//div[@class='_schedule tc-panel tc-selected']"));

    }

       /*
    * Step : 일정설정 > 캘린더 삭제 클릭
    * Result : 해당 캘린더 목록에서 노출 안됨
    */

    //@Test
    public void TC_03_일정설정_삭제_Test() throws Exception {

        //String calName;
        String calEdit;
        String tempCalName;
        //String tempValue;

        int maxCalNum;
        util.waitForIsElementPresent(By.xpath("//tr[contains(@class,'_cfg_calendar_list') and ./td[5]/div/a[contains(@class,'_del_del link')]]"));
        maxCalNum = util.getXpathCount(By.xpath("//tbody[@class='_private_calendar_list']/tr"));

        //기본 캘린더만 있을때 예외처리
        if (maxCalNum == 1) {
            util.printLog("현재 삭제할 캘린더가 없습니다.");
        } else {
            for (int i = 2; i < maxCalNum + 1; i++) {
                calEdit = util.waitForIsElementPresent(By.xpath("//tbody[@class='_private_calendar_list']/tr[" + i + "]/td[5]/div")).getText();
                //비공개캘린더일때 삭제 노출 됨
                if (calEdit.contains("삭제")) {
                    tempCalName = util.waitForIsElementPresent(By.xpath("//tbody[contains(@class,'_private_calendar_list')]/tr[" + i + "]/td[1]/a")).getText();
                    //tempValue = util.waitForIsElementPresent(By.xpath("//tbody[contains(@class,'_private_calendar_list')]/tr[" + i + "]")).getAttribute("data-value");

                    util.waitForIsElementPresent(By.xpath("//tbody[@class='_private_calendar_list']/tr[" + i + "]/td[5]/div/a[1]"));
                    util.click(By.xpath("//tbody[@class='_private_calendar_list']/tr[" + i + "]/td[5]/div/a[1]"));
                    util.printLog("삭제하는 캘린더 명은  [" + tempCalName + "] 입니다.");

                    String assertAlert = util.getAlert().getText();
                    assertTrue(assertAlert.contains("캘린더를 삭제하면 캘린더에 등록되어 있는 모든 일정도 영구적으로 삭제됩니다."));
                    util.getAlert().accept();
                }
                else if(calEdit.contains("폐쇄")){
                    tempCalName = util.waitForIsElementPresent(By.xpath("//tbody[contains(@class,'_private_calendar_list')]/tr[" + i + "]/td[1]/a")).getText();
                    //tempValue = util.waitForIsElementPresent(By.xpath("//tbody[contains(@class,'_private_calendar_list')]/tr[" + i + "]")).getAttribute("data-value");

                    util.waitForIsElementPresent(By.xpath("//tbody[@class='_private_calendar_list']/tr[" + i + "]/td[5]/div/a[3]"));
                    util.click(By.xpath("//tbody[@class='_private_calendar_list']/tr[" + i + "]/td[5]/div/a[3]"));
                    util.printLog("삭제하는 캘린더 명은  [" + tempCalName + "] 입니다.");

                    String assertAlert = util.getAlert().getText();
                    assertTrue(assertAlert.contains("공유 캘린더를 폐쇄하면 참여자가 모두 탈퇴 처리되고"));
                    util.getAlert().accept();

                    assertAlert = util.getAlert().getText();
                    assertTrue(assertAlert.contains("캘린더가 폐쇄되었습니다."));
                    util.getAlert().accept();
                }
                else{
                }
            }
        }

        //저장하고 얼럿에서 확인
        util.waitForIsElementPresent(By.xpath("//button[@class='_save normal']"));
        util.click(By.xpath("//button[@class='_save normal']"));
        util.getAlert().accept();

        //환경설정 재 진입
        util.waitForPageLoaded();
        util.waitForIsElementPresent(By.id("searchKeyWord"));
        util.waitForIsElementPresent(By.className("_config"));

        util.click(By.className("_config"));
        util.waitForIsElementPresent(By.linkText("캘린더로 돌아가기"));
        //환경설정 > 일정설정 진입
        util.click(By.xpath("//ul[@class='tab_setting tabs']/li[2]"));
        util.waitForIsElementPresent(By.xpath("//div[@class='_schedule tc-panel tc-selected']"));
    }

    /*
    * Step : 로그인 > 해당 계정으로 로그인
    * Result : 해당하는 계정으로 로그인 됨
    */

    //@Test
    public void TC_04_일정설정_일정삭제_Test() throws Exception {

        int scheduleCount =0;
        scheduleCount = util.getXpathCount(By.xpath("//dd[@class='line ']"));

        for(int i=0; i<scheduleCount; i++){

            util.waitForIsElementPresent(By.xpath("//dd[@class='line ']"));
            util.click(By.xpath("//dd[@class='line ']/"));
            util.click(By.xpath("//input[@class='_includingCompleted']"));


        }
        while (util.waitForIsElementPresent(By.xpath("//div[@class='_list todo_list']/div/ul/li")).isDisplayed()) {
            util.click(By.xpath("//div[@class='_list todo_list']/div/ul/li/div/a[3]"));

            util.waitForIsElementPresent(By.xpath("//div[@class='ly_todo_wrap']"));
            util.click(By.xpath("//button[@class='_delete btn_delete']"));

            util.getAlert().accept();
            util.sleep(3);
        }
    }


    /*
    * Step : 로그인 > 해당 계정으로 로그인
    * Result : 해당하는 계정으로 로그인 됨
    */

    //@Test
    public void TC_04_초기화_할일삭제_Test() throws Exception {
        module.로그인(util, TestIds.CalUser.getId(), TestIds.CalUser.getPw());

        util.sleep(5);

        util.waitForTitle(module.calTitle);
        util.goTo(module.taskURL);

        Title = util.getTitle();
        URL = util.getCurrentUrl();

        util.printLog("[Title] : " + Title);
        util.printLog("[URL] : " + URL);

        assertTrue(Title.contains(module.taskTitle));
        assertTrue(URL.contains(module.taskURL));

        util.waitForIsElementPresent(By.xpath("//input[@class='_includingCompleted']"));
        util.click(By.xpath("//input[@class='_includingCompleted']"));

        while (util.waitForIsElementPresent(By.xpath("//div[@class='_list todo_list']/div/ul/li")).isDisplayed()) {
            util.click(By.xpath("//div[@class='_list todo_list']/div/ul/li/div/a[3]"));

            util.waitForIsElementPresent(By.xpath("//div[@class='ly_todo_wrap']"));
            util.click(By.xpath("//button[@class='_delete btn_delete']"));

            util.getAlert().accept();
            util.sleep(3);
        }
    }

        /*
    * Step : 로그인 > 해당 계정으로 로그인
    * Result : 해당하는 계정으로 로그인 됨
    */

    @Test
    public void TC_05_초기화_일정삭제_Test() throws Exception {

        util.waitForTitle(module.calTitle);

        Title = util.getTitle();
        URL = util.getCurrentUrl();

        util.printLog("[Title] : " + Title);
        util.printLog("[URL] : " + URL);

        assertTrue(Title.contains(module.calTitle));
        assertTrue(URL.contains(module.calURL));

        util.click(By.xpath("//button[contains(@class,'_list list')]"));
        util.waitForIsElementPresent(By.xpath("//button[@class='_list list on']"));

        int numberOfSchedule = util.getXpathCount(By.xpath("//ul[@class='list_area']/li/dl/dd[@class='line ']/p[3]/a"));
        String alertText;
        System.out.println(numberOfSchedule);

        for(int i=1; i<=numberOfSchedule; i++) {
            util.waitForIsElementPresent(By.xpath("//dd[@class='line ']/p[3]"));
            util.mouseOver(By.xpath("//dd[@class='line ']/p[3]"));
            util.click(By.xpath("//dd[@class='line ']/p[3]/a"));

            System.out.println(numberOfSchedule);

            util.click(By.xpath("//button[@class='_del_btn btn_default btn_default_v1']"));
            util.sleep(1);

            if(util.waitForIsNotVisible(By.xpath("//div[@class='layer_content']"))){
                util.printLog("Delete Compelete");
            }
            else if(util.isAlertPresent(util)){
                alertText = util.getAlert().getText();
                assertTrue(alertText.contains("일정을 삭제하시겠습니까?"));
                util.getAlert().accept();
                util.sleep(2);
            }
            else if(util.waitForIsElementPresent(By.xpath("//div[@class='layer_content']")).isDisplayed()){
                util.waitForIsElementPresent(By.xpath("//button[@class='_ok normal btn_emphasis']"));
                util.click(By.xpath("//button[@class='_ok normal btn_emphasis']"));
                util.sleep(2);
            }
        }
    }

}