package com.naver.calendar.s04_환경설정;

import main.TestIds;
import main.Testcase;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.assertTrue;


public class suite_04_환경설정_할일설정_Test extends Testcase {

    public String Title = null;
    public String URL = null;
    public String taskName;
    public String tempName;
    public String tasks[] = {};
    int maxTaskNum;
    public String alertText;
    public int taskNum;
    public int newTaskNum;

    /*
   * Step : 로그인 > 해당 계정으로 로그인
   * Result : 해당하는 계정으로 로그인 됨
    */
    @Test
    public void TC_00_할일설정_로그인_Test() throws Exception {
        module.로그인(util, TestIds.CalUser.getId(), TestIds.CalUser.getPw());
        util.waitForPageLoaded();
        util.goTo(module.taskURL);

    }


    /*
    * Step : 할일설정 > 할일 그룹 목록 확인
    * Result : 현재 할일 그룹 목록이 노출 됨
    */

    @Test
    public void TC_01_할일설정_할일목록_Test() throws Exception {

        util.click(By.className("_config"));
        util.waitForIsElementPresent(By.linkText("캘린더로 돌아가기")).isDisplayed();

        util.click(By.xpath("//ul[@class='tab_setting tabs']/li[4]"));
        util.waitForIsElementPresent(By.xpath("//div[@class='_task tc-panel tc-selected']"));

        module.taskOrder(util);
        //현재 할일 그룹 순서[1,2,3,4]
        util.printLog("현재 할일 순서는 다음과 같습니다. \n"+Arrays.toString(tasks));
        maxTaskNum = util.getXpathCount(By.xpath("//tbody[@class='_private_task_group']/tr"));
        //환경설정과 좌측영역의 캘린더 명을 비교함
        for(int i =1; i < maxTaskNum; i++)
        {
            taskName  = util.waitForIsElementPresent(By.xpath("//tbody[@class='_private_task_group']/tr["+i+"]/td")).getText();
            tempName = util.waitForIsElementPresent(By.xpath("//ul[@class='_groups category_list my']/li["+(i+1)+"]/a[1]")).getText();
            assertTrue(taskName.contains(tempName));
        }

        //1번 할일 그룹을 가장 하단으로 이동하고 확인[2,3,4,1]
        util.click(By.xpath("//tbody[@class='_private_task_group']/tr[1]/td[3]/div/a[4]"));
        util.click(By.xpath("//button[@class='_save normal']"));

        alertText = util.getAlert().getText();
        assertTrue(alertText.contains("환경설정의 변경 사항이 저장되었습니다."));
        util.getAlert().accept();
        util.waitForPageLoaded();
    }

    /*
    * Step : 할일설정 > 할일 그룹 추가
    * Result : 할일 그룹이 추가 됨
    */

    @Test
    public void TC_02_할일설정_할일그룹추가_Test() throws Exception {

        //할일 그룹 추가
        module.goTask(util);
        util.sleep(3);

        //현재 그룹 개수를 저장(실제 전체 내 할 일이 개수에 포함되어서 이게 생성한 그룹 번호가 됨)
        taskNum = util.getXpathCount(By.xpath("//ul[@class='_groups category_list my']/li"));
        util.waitForIsElementPresent(By.xpath("//a[@class='_newGroup btn_makecal']"));
        util.mouseOver(By.xpath("//a[@class='_newGroup btn_makecal']"));
        util.click(By.xpath("//a[@class='_newGroup btn_makecal']"));

        util.waitForIsElementPresent(By.xpath("//li[@class='modify']"));
        //util.type(By.xpath("//li[@class='modify']"),"새 그룹"+module.contents);

        util.click(By.xpath("//a[contains(text(),'내 할 일')]"));
        System.out.println(taskNum);

        //생성한 그룹이 노출되는지 확인하고 생성된 그룹의 이름을 저장
        util.waitForIsElementPresent(By.xpath("//ul[@class='_groups category_list my']/li["+taskNum+"]"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//ul[@class='_groups category_list my']/li["+taskNum+"]")).isDisplayed());
        taskName = util.waitForIsElementPresent(By.xpath("//ul[@class='_groups category_list my']/li["+taskNum+"]/a")).getAttribute("title");
        util.printLog(taskName);

        //그룹 개수가 1 늘었는지 확인
        newTaskNum = util.getXpathCount(By.xpath("//ul[@class='_groups category_list my']/li"));
        System.out.println(newTaskNum);
        assertTrue(newTaskNum == taskNum+1);

    }

    /*
    * Step : 할일설정 > 할일 그룹 추가
    * Result : 할일 그룹이 추가 됨
    */

    @Test
    public void TC_03_할일설정_할일그룹삭제_Test() throws Exception {

        //환경설정 > 할일 설정으로 이동
        util.click(By.className("_config"));
        util.waitForIsElementPresent(By.linkText("캘린더로 돌아가기")).isDisplayed();

        util.click(By.xpath("//ul[@class='tab_setting tabs']/li[4]"));
        util.waitForIsElementPresent(By.xpath("//div[@class='_task tc-panel tc-selected']"));

        //새그룹생긴것 확인
        util.printLog(taskName);
        util.waitForIsElementPresent(By.xpath("//tbody[@class='_private_task_group']/tr["+taskNum+"]"));
        util.waitForIsElementPresent(By.xpath("//td[contains(@class,'calendar_title') and contains(text(),'"+taskName+"')]"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//td[contains(@class,'calendar_title') and contains(text(),'"+taskName+"')]")).isDisplayed());

        util.click(By.xpath("//tr[contains(@class,'tr_28780122 _cfg_task_group_list') and ./td[contains(text(),'"+taskName+"')]]/td[2]"));

        util.waitForIsElementPresent(By.xpath("//div[@class='ly_todo_basic']"));
        util.waitForIsElementPresent(By.xpath("//button[@class='_submit btn_emphasis']"));
        util.click(By.xpath("//button[@class='_submit btn_emphasis']"));

        util.sleep(3);

        util.waitForIsElementPresent(By.xpath("//button[@class='_save normal']"));
        util.click(By.xpath("//button[@class='_save normal']"));
        util.getAlert().accept();
        util.waitForPageLoaded();

        assertTrue(util.waitForIsNotVisible(By.xpath("//tbody[@class='_private_task_group']/tr[5]")));

    }


    /*
    * Step : 할일설정 > 할일 그룹 목록 순서 변경
    * Result : 할일 그룹 목록 순서 변경 됨
    */

    @Test
    public void TC_04_할일설정_할일순서변경_Test() throws Exception {


        util.click(By.className("_config"));
        util.waitForIsElementPresent(By.linkText("캘린더로 돌아가기")).isDisplayed();

        util.click(By.xpath("//ul[@class='tab_setting tabs']/li[4]"));
        util.waitForIsElementPresent(By.xpath("//div[@class='_task tc-panel tc-selected']"));

        module.taskOrder(util);
        //현재 할일 그룹 순서[1,2,3,4]
        util.printLog("현재 할일 순서는 다음과 같습니다. \n"+Arrays.toString(tasks));

        //1번 할일 그룹을 가장 하단으로 이동하고 확인[2,3,4,1]
        util.click(By.xpath("//tbody[@class='_private_task_group']/tr[1]/td[3]/div/a[4]"));

        //기본 할일은 제일 위로 올림
        util.click(By.xpath("//tr[contains(@class,'tr_28780122 _cfg_task_group_list') and ./td/span]/td[3]/div/a[1]"));

        util.click(By.xpath("//button[@class='_save normal']"));


        alertText = util.getAlert().getText();
        assertTrue(alertText.contains("환경설정의 변경 사항이 저장되었습니다."));
        util.getAlert().accept();
        util.waitForPageLoaded();
        /*
        util.click(By.className("_config"));
        util.waitForIsElementPresent(By.linkText("캘린더로 돌아가기")).isDisplayed();

        util.click(By.xpath("//ul[@class='tab_setting tabs']/li[4]"));
        util.waitForIsElementPresent(By.xpath("//div[@class='_task tc-panel tc-selected']"));

        module.taskOrder(util);
        //현재 할일 그룹 순서[너 할 일, 우리 할 일, 쟤네 할 일, 내 할일 ]
        util.printLog("현재 할일 순서는 다음과 같습니다. \n"+Arrays.toString(tasks));
        */
    }



}
