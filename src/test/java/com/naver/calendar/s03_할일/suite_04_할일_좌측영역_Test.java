package com.naver.calendar.s03_할일;

import main.TestIds;
import main.Testcase;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;


public class suite_04_할일_좌측영역_Test extends Testcase {

    public String Title = null;
    public String URL = null;
    public String viewType;
    public String taskName;
    public int taskNum;
    public int newTaskNum;


    /*
    * Step : 로그인 > 해당 계정으로 로그인
    * Result : 해당하는 계정으로 로그인 됨
    */
    @Test
    public void TC_00_좌측영역_로그인_Test() throws Exception {
        module.로그인(util, TestIds.CalUser.getId(), TestIds.CalUser.getPw());

        util.waitForTitle(module.calTitle);
        util.goTo(module.taskURL);

        Title = util.getTitle();
        URL = util.getCurrentUrl();

        util.printLog("[Title] : " + Title);
        util.printLog("[URL] : " + URL);

        assertTrue(Title.contains(module.taskTitle));
        assertTrue(URL.contains(module.taskURL));
    }


     /*
     * Step : 좌측영역 > 할 일쓰기 클릭
     * Result : 할 일 쓰기 창 노출 됨
     */
    @Test
    public void TC_01_좌측영역_할일쓰기_Test() throws Exception {

        util.click(By.xpath("//span[contains(text(),'할 일 쓰기')]"));
        util.waitForIsElementPresent(By.xpath("//div[@class='ly_todo_wrap']"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//div[@class='ly_todo_wrap']")).isDisplayed());

        util.waitForIsElementPresent(By.xpath("//button[@class='_layer_close']"));
        util.click(By.xpath("//button[@class='_layer_close']"));
    }


    /*
    * Step : 좌측영역 > 날짜영역 클릭
    * Result : <,> 화살표 클릭함에 따라 이전달, 다음달로 이동 됨
    */

    @Test
    public void TC_03_좌측영역_날짜영역_Test() throws Exception{

        String miniCalendar;
        miniCalendar = util.isElementPresent(By.xpath("//div[@class='_miniCalendar mini_calender']")).getAttribute("style");
        //접혀있을때 97px 안접혀있을때 233px
        module.CurrentDate(util);

        //미니 달력 펼치기 버튼 클릭
        util.click(By.xpath("//button[@class='_fold btn_fold']"));
        module.CurrentDate(util);
        util.click(By.xpath("//button[@class='_fold btn_fold']"));

        if(miniCalendar.contains("top: 97px; left: 0px;"))
        {
            util.printLog(miniCalendar);
            util.click(By.xpath("//button[@class='_fold btn_fold']"));
            module.CurrentDate(util);
        }


        //날짜 <,> 버튼 동작 확인
        //이전 달 버튼 클릭
        util.click(By.xpath("//a[@class='btn_prev rollover calendar-btn-prev-mon']"));
        module.CurrentDate(util);

        //다음 달 버튼 클릭
        util.click(By.xpath("//a[@class='btn_next rollover calendar-btn-next-mon']"));
        util.click(By.xpath("//a[@class='btn_next rollover calendar-btn-next-mon']"));
        module.CurrentDate(util);
    }


    /*
    * Step : 좌측영역 > 전체 할 일 클릭
    * Result : 전체 할 일 노출 됨
    *
    */

    @Test
    public void TC_04_좌측영역_전체할일보기_Test() throws Exception{

        util.click(By.xpath("//a[contains(@class,'_select') and contains(text(),'전체 할 일')]"));
        taskName = util.waitForIsElementPresent(By.xpath("//ul[@class='view_todo']/li[1]")).getAttribute("class");
        util.printLog(taskName);
        viewType = util.waitForIsElementPresent(By.xpath("//li[@class='_hover _selectTarget  on select']")).getAttribute("data-type");
        assertTrue(viewType.contains("all"));
    }


    /*
    * Step : 좌측영역 > 오늘 할 일 클릭
    * Result : 오늘 할 일 노출 됨
    *
    */

    @Test
    public void TC_05_좌측영역_오늘할일보기_Test() throws Exception{

        util.click(By.xpath("//a[contains(@class,'_select') and contains(text(),'오늘 할 일')]"));
        taskName = util.waitForIsElementPresent(By.xpath("//ul[@class='view_todo']/li[2]")).getAttribute("class");
        util.printLog(taskName);
        viewType = util.waitForIsElementPresent(By.xpath("//li[@class='_hover _selectTarget on select']")).getAttribute("data-type");
        assertTrue(viewType.contains("today"));
    }


    /*
    * Step : 좌측영역 > 이번주 할 일 클릭
    * Result : 이번주 할 일 노출 됨
    *
    */

    @Test
    public void TC_06_좌측영역_이번주할일보기_Test() throws Exception{

        util.click(By.xpath("//a[contains(@class,'_select') and contains(text(),'이번 주 할 일')]"));
        taskName = util.waitForIsElementPresent(By.xpath("//ul[@class='view_todo']/li[3]")).getAttribute("class");
        util.printLog(taskName);
        viewType = util.waitForIsElementPresent(By.xpath("//li[@class='_hover _selectTarget on select']")).getAttribute("data-type");
        assertTrue(viewType.contains("thisweek"));
    }


    /*
    * Step : 좌측영역 > 이번주 할 일 클릭
    * Result : 이번주 할 일 노출 됨
    *
    */

    @Test
    public void TC_07_좌측영역_중요할일보기_Test() throws Exception{

        util.click(By.xpath("//a[contains(@class,'_select') and contains(text(),'중요 할 일')]"));
        taskName = util.waitForIsElementPresent(By.xpath("//ul[@class='view_todo']/li[4]")).getAttribute("class");
        util.printLog(taskName);
        viewType = util.waitForIsElementPresent(By.xpath("//li[@class='_hover _selectTarget on select']")).getAttribute("data-type");
        assertTrue(viewType.contains("important"));
    }


    /*
    * Step : 좌측영역 > 이번주 할 일 클릭
    * Result : 이번주 할 일 노출 됨
    *
    */

    @Test
    public void TC_08_좌측영역_완료할일보기_Test() throws Exception{

        util.click(By.xpath("//a[contains(@class,'_select') and contains(text(),'완료된 할일')]"));
        taskName = util.waitForIsElementPresent(By.xpath("//ul[@class='view_todo']/li[5]")).getAttribute("class");
        util.printLog(taskName);
        viewType = util.waitForIsElementPresent(By.xpath("//li[@class='_hover _selectTarget on select']")).getAttribute("data-type");
        assertTrue(viewType.contains("completed"));
    }


    /*
    * Step : 좌측영역 > 할일 그룹 생성 클릭
    * Result : 할일 그룹 생성
    *
    */
    @Test
    public void TC_09_좌측영역_할일그룹만들기_Test() throws Exception{

        //현재 그룹 개수를 저장(실제 전체 내 할 일이 개수에 포함되어서 이게 생성한 그룹 번호가 됨)
        taskNum = util.getXpathCount(By.xpath("//ul[@class='_groups category_list my']/li"));
        util.click(By.xpath("//a[@class='_newGroup btn_makecal']"));

        //생성한 그룹이 노출되는지 확인하고 생성된 그룹의 이름을 저장
        util.waitForIsElementPresent(By.xpath("//ul[@class='_groups category_list my']/li["+taskNum+"]"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//ul[@class='_groups category_list my']/li["+taskNum+"]")).isDisplayed());
        taskName = util.waitForIsElementPresent(By.xpath("//ul[@class='_groups category_list my']/li["+taskNum+"]/a")).getAttribute("title");

        //그룹 개수가 1 늘었는지 확인
        newTaskNum = util.getXpathCount(By.xpath("//ul[@class='_groups category_list my']/li"));
        assertTrue(newTaskNum == taskNum+1);
    }


    /*
    * Step : 좌측영역 > 할일 그룹 삭제
    * Result : 할일 그룹 삭제 됨
    *
    */

    @Test
    public void TC_10_좌측영역_할일그룹삭제_Test() throws Exception{

        //생성한 그룹에 마우스 오버 하여 메뉴창 클릭
        util.mouseOver(By.xpath("//ul[@class='_groups category_list my']/li["+taskNum+"]"));
        util.waitForIsElementPresent(By.xpath("//ul[@class='_groups category_list my']/li["+taskNum+"]/a[2]"));
        util.click(By.xpath("//ul[@class='_groups category_list my']/li["+taskNum+"]/a[2]"));

        util.waitForIsElementPresent(By.xpath("//div[@class='calendar_menu_wrap']"));

        util.waitForIsElementPresent(By.xpath("//a[@class='_delete']"));
        util.click(By.xpath("//a[@class='_delete']"));

        util.waitForIsElementPresent(By.xpath("//diy[@class='ly_todo_wrap group']"));
        util.click(By.xpath("//button[@class='_submit']"));
    }


    /*
    * Step : 좌측영역 > 오늘 일정 클릭
    * Result : 일정 페이지로 이동 됨
    * URL : https://calendar.naver.com/tasks.nhn
    */

    @Test
    public void TC_20_좌측영역_오늘일정_Test() throws Exception{

        util.click(By.xpath("//a[@class ='_viewTodaySchedule']"));
        util.waitForIsElementPresent(By.xpath("//a[@class='_openNewTaskForm write_todo']"));

        Title = util.getTitle();
        URL = util.getCurrentUrl();
        assertTrue(URL.contains("https://calendar.naver.com/schedule.nhn"));
        assertTrue(Title.contains("일정 : 네이버 캘린더"));

        util.click(By.xpath("//button[@class='_go_task type_schedule todo']"));
        util.waitForIsElementPresent(By.xpath("//button[@class='_go_task type_schedule todo']"));
        util.waitForTitle("할 일 : 네이버 캘린더");
    }
}
