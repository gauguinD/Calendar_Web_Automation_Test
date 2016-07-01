package com.naver.calendar.s03_할일;

import main.TestIds;
import main.Testcase;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;


public class suite_05_할일_할일쓰기_Test extends Testcase {

    public String Title = null;
    public String URL = null;
    public String viewType;
    public String taskName;
    public int taskNum;
    public int newTaskNum;
    public String taskId;
    public String taskOption;


    /*
    * Step : 로그인 > 해당 계정으로 로그인
    * Result : 해당하는 계정으로 로그인 됨
    */
    @Test
    public void TC_00_할일쓰기_로그인_Test() throws Exception {
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
    * Step : 좌측영역 > 빠른 쓰기 클릭
    * Result : 할일 생성 됨
    */

    @Test
    public void TC_01_할일쓰기_빠른쓰기_Test() throws Exception {
        module.ifViewIsNotTask(util,module);

        if(util.waitForIsNotVisible(By.xpath("//div[@class='no_list']")))
        {
            taskNum = util.getXpathCount(By.xpath("//div[@class='_list todo_list']/div/ul/li"));
        }
        else{
            taskNum=0;
        }

        //빠른 할일쓰기 창에 [빠른 할일 쓰기] 입력하여 할일 생성
        util.waitForIsElementPresent(By.xpath("//input[contains(@class,'_content focus_txt')]"));
        util.click(By.xpath("//input[contains(@class,'_content focus_txt')]"));

        util.type(By.xpath("//input[contains(@class,'_content focus_txt')]"),"빠른 할일 쓰기");
        util.click(By.xpath("//a[@class='_submit btn_submit']"));

        //빠른 할일 쓰기라는 제목을 가진 할 일 노출 되는것 확인
        util.waitForIsElementPresent(By.xpath("//a[contains(@class,'_content') and contains(text(),'빠른 할일 쓰기')]"));
        taskId = util.waitForIsElementPresent(By.xpath("//li[contains(@class,'task') and ./div/p/a[contains(text(),'빠른 할일 쓰기')]]")).getAttribute("id");
        assertTrue(util.waitForIsElementPresent(By.xpath("//a[contains(@class,'_content') and contains(text(),'빠른 할일 쓰기')]")).isDisplayed());

        //할일의 개수를 확인해서 한개 늘어난것 확인
        newTaskNum = util.getXpathCount(By.xpath("//div[@class='_list todo_list']/div/ul/li"));
        assertTrue(newTaskNum == taskNum+1);

        module.deleteTask(util,taskId,newTaskNum);
    }


     /*
     * Step : 좌측영역 > 할일 쓰기 클릭
     * Result : 할일 생성
     */
    @Test
    public void TC_02_할일쓰기_할일쓰기_Test() throws Exception {

        if(util.waitForIsNotVisible(By.xpath("//div[@class='no_list']")))
        {
            taskNum = util.getXpathCount(By.xpath("//div[@class='_list todo_list']/div/ul/li"));
        }
        else{
            taskNum=0;
        }
        //할 일 쓰기 클릭해서 할일 쓰기로 진입
        util.click(By.xpath("//span[contains(text(),'할 일 쓰기')]"));
        util.waitForIsElementPresent(By.xpath("//div[@class='ly_todo_wrap']"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//div[@class='ly_todo_wrap']")).isDisplayed());

        util.type(By.xpath("//div[@class='input_txt']/input"),"할일 쓰기"+module.subjectKey);

        util.waitForIsElementPresent(By.xpath("//button[@class='_save btn_emphasis']"));
        util.click(By.xpath("//button[@class='_save btn_emphasis']"));

        //할일 쓰기라는 제목을 가진 할 일 노출 되는것 확인
        util.waitForIsElementPresent(By.xpath("//a[contains(@class,'_content') and contains(text(),'"+module.subjectKey+"')]"));
        taskId = util.waitForIsElementPresent(By.xpath("//li[contains(@class,'task') and ./div/p/a[contains(text(),'"+module.subjectKey+"')]]")).getAttribute("id");
        assertTrue(util.waitForIsElementPresent(By.xpath("//a[contains(@class,'_content') and contains(text(),'"+module.subjectKey+"')]")).isDisplayed());

        //할일의 개수를 확인해서 한개 늘어난것 확인
        util.sleep(3);
        newTaskNum = util.getXpathCount(By.xpath("//div[@class='_list todo_list']/div/ul/li"));
        assertTrue(newTaskNum == taskNum+1);

        module.deleteTask(util,taskId,newTaskNum);
    }


    /*
    * Step : 좌측영역 > 할일 쓰기 클릭
    * Result : 중요 할일 생성
    */

    @Test
    public void TC_03_할일쓰기_중요할일쓰기_Test() throws Exception {

        if(util.waitForIsNotVisible(By.xpath("//div[@class='no_list']")))
        {
            taskNum = util.getXpathCount(By.xpath("//div[@class='_list todo_list']/div/ul/li"));
        }
        else{
            taskNum=0;
        }
        //할 일 쓰기 클릭해서 할일 쓰기로 진입
        util.click(By.xpath("//span[contains(text(),'할 일 쓰기')]"));
        util.waitForIsElementPresent(By.xpath("//div[@class='ly_todo_wrap']"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//div[@class='ly_todo_wrap']")).isDisplayed());

        //중요 할일 쓰기 생성
        util.type(By.xpath("//div[@class='input_txt']/input"),"중요 할일 쓰기"+module.subjectKey);
        util.waitForIsElementPresent(By.xpath("//div[@class='_importanceField selectbox13']"));
        util.click(By.xpath("//div[@class='_importanceField selectbox13']"));

        util.waitForIsElementPresent(By.xpath("//div[@class='selectbox-list']/ul/li[3]"));
        util.click(By.xpath("//div[@class='selectbox-list']/ul/li[3]"));

        util.waitForIsElementPresent(By.xpath("//button[@class='_save btn_emphasis']"));
        util.click(By.xpath("//button[@class='_save btn_emphasis']"));

        //중요 할일 쓰기라는 제목을 가진 할 일 노출 되는것 확인
        util.waitForIsElementPresent(By.xpath("//a[contains(@class,'_content') and contains(text(),'"+module.subjectKey+"')]"));
        taskId = util.waitForIsElementPresent(By.xpath("//li[contains(@class,'task') and ./div/p/a[contains(text(),'"+module.subjectKey+"')]]")).getAttribute("id");
        assertTrue(util.waitForIsElementPresent(By.xpath("//a[contains(@class,'_content') and contains(text(),'"+module.subjectKey+"')]")).isDisplayed());
        assertTrue(util.waitForIsElementPresent(By.xpath("//li[@id='"+taskId+"']/div/a[@class='_importance ck_impt v3']")).isDisplayed());

        //할일의 개수를 확인해서 한개 늘어난것 확인
        newTaskNum = util.getXpathCount(By.xpath("//div[@class='_list todo_list']/div/ul/li"));
        assertTrue(newTaskNum == taskNum+1);

        module.deleteTask(util,taskId,newTaskNum);
    }


    /*
    * Step : 좌측영역 > 할일 쓰기 클릭
    * Result : 중요 할일 생성
    */

    //@Test
    public void TC_04_할일쓰기_그룹선택할일쓰기_Test() throws Exception {

        if(util.waitForIsNotVisible(By.xpath("//div[@class='no_list']")))
        {
            taskNum = util.getXpathCount(By.xpath("//div[@class='_list todo_list']/div/ul/li"));
        }
        else{
            taskNum=0;
        }
        //할 일 쓰기 클릭해서 할일 쓰기로 진입
        util.click(By.xpath("//span[contains(text(),'할 일 쓰기')]"));
        util.waitForIsElementPresent(By.xpath("//div[@class='ly_todo_wrap']"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//div[@class='ly_todo_wrap']")).isDisplayed());

        util.type(By.xpath("//div[@class='input_txt']/input"),"그룹선택 할일 쓰기"+module.subjectKey);
        util.waitForIsElementPresent(By.xpath("//div[@class='_select selectbox13']"));
        util.click(By.xpath("//div[@class='_select selectbox13']"));

        //util.waitForIsElementPresent(By.xpath("//div[@class='_select selectbox13  selectbox-open']/div[2]/div/ul/li[2]"));
        util.click(By.xpath("//div[@class='selectbox-list']/ul/li[2]"));
        taskOption = util.waitForIsElementPresent(By.xpath("//div[@class='_select selectbox13']/div[2]/div/ul/li[2]")).getText();
        util.printLog(taskOption);

        util.waitForIsElementPresent(By.xpath("//button[@class='_save btn_emphasis']"));
        util.click(By.xpath("//button[@class='_save btn_emphasis']"));

        //빠른 할일 쓰기라는 제목을 가진 할 일 노출 되는것 확인
        util.waitForIsElementPresent(By.xpath("//a[contains(@class,'_content') and contains(text(),'"+module.subjectKey+"')]"));
        taskId = util.waitForIsElementPresent(By.xpath("//li[contains(@class,'task') and ./div/p/a[contains(text(),'"+module.subjectKey+"')]]")).getAttribute("id");
        assertTrue(util.waitForIsElementPresent(By.xpath("//a[contains(@class,'_content') and contains(text(),'"+module.subjectKey+"')]")).isDisplayed());
        assertTrue(util.waitForIsElementPresent(By.xpath("//li[@id='"+taskId+"']/div/div/span/a[contains(text(),'"+taskOption+"')]")).isDisplayed());

        //할일의 개수를 확인해서 한개 늘어난것 확인
        newTaskNum = util.getXpathCount(By.xpath("//div[@class='_list todo_list']/div/ul/li"));
        assertTrue(newTaskNum == taskNum+1);

        module.deleteTask(util,taskId,newTaskNum);
    }


    /*
    * Step : 좌측영역 > 할일 쓰기 클릭
    * Result : 완료일 할일 생성
    */
    @Test
    public void TC_05_할일쓰기_완료일할일쓰기_Test() throws Exception {

        if(util.waitForIsNotVisible(By.xpath("//div[@class='no_list']")))
        {
            taskNum = util.getXpathCount(By.xpath("//div[@class='_list todo_list']/div/ul/li"));
        }
        else{
            taskNum=0;
        }
        //할 일 쓰기 클릭해서 할일 쓰기로 진입
        util.click(By.xpath("//span[contains(text(),'할 일 쓰기')]"));
        util.waitForIsElementPresent(By.xpath("//div[@class='ly_todo_wrap']"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//div[@class='ly_todo_wrap']")).isDisplayed());

        util.type(By.xpath("//div[@class='input_txt']/input"),"완료일 할일 쓰기"+module.subjectKey);
        util.waitForIsElementPresent(By.xpath("//div[@class='cont cplt']/input[1]"));

        //완료일 클릭하고 [오늘] 버튼 선택 되었는지 확인
        util.click(By.xpath("//div[@class='cont cplt']/input[1]"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//button[@class='_shortcut today on']")).isDisplayed());

        util.waitForIsElementPresent(By.xpath("//button[@class='_save btn_emphasis']"));
        util.click(By.xpath("//button[@class='_save btn_emphasis']"));


        //할일 쓰기라는 제목을 가진 할 일 노출 되는것 확인
        util.waitForIsElementPresent(By.xpath("//a[contains(@class,'_content') and contains(text(),'"+module.subjectKey+"')]"));
        taskId = util.waitForIsElementPresent(By.xpath("//li[contains(@class,'task') and ./div/p/a[contains(text(),'"+module.subjectKey+"')]]")).getAttribute("id");
        assertTrue(util.waitForIsElementPresent(By.xpath("//a[contains(@class,'_content') and contains(text(),'"+module.subjectKey+"')]")).isDisplayed());
        assertTrue(util.waitForIsElementPresent(By.xpath("//li[@id='"+taskId+"']/div/div/span[2]/a[contains(@class,'_end_date date today')]")).isDisplayed());

        //할일의 개수를 확인해서 한개 늘어난것 확인
        util.waitForPageLoaded();
        newTaskNum = util.getXpathCount(By.xpath("//div[@class='_list todo_list']/div/ul/li"));
        assertTrue(newTaskNum == taskNum+1);

        module.deleteTask(util,taskId,newTaskNum);
    }


    /*
    * Step : 좌측영역 > 할일 쓰기 클릭
    * Result : 반복 할일 생성
    */

    @Test
    public void TC_06_할일쓰기_반복할일쓰기_Test()throws Exception {

        if(util.waitForIsNotVisible(By.xpath("//div[@class='no_list']")))
        {
            taskNum = util.getXpathCount(By.xpath("//div[@class='_list todo_list']/div/ul/li"));
        }
        else{
            taskNum=0;
        }
        //할 일 쓰기 클릭해서 할일 쓰기로 진입
        util.click(By.xpath("//span[contains(text(),'할 일 쓰기')]"));
        util.waitForIsElementPresent(By.xpath("//div[@class='ly_todo_wrap']"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//div[@class='ly_todo_wrap']")).isDisplayed());

        util.type(By.xpath("//div[@class='input_txt']/input"),"반복 할일 쓰기"+module.subjectKey);
        util.waitForIsElementPresent(By.xpath("//button[@class='_recurrence btn_rpt']"));
        util.click(By.xpath("//button[@class='_recurrence btn_rpt']"));

        util.waitForIsElementPresent(By.xpath("//div[@class='ly_todo_wrap recycle']"));
        util.click(By.xpath("//button[@class='_set btn_emphasis']"));

        util.waitForIsElementPresent(By.xpath("//button[@class='_save btn_emphasis']"));
        util.click(By.xpath("//button[@class='_save btn_emphasis']"));

        //할일 쓰기라는 제목을 가진 할 일 노출 되는것 확인
        util.waitForIsElementPresent(By.xpath("//a[contains(@class,'_content') and contains(text(),'"+module.subjectKey+"')]"));
        taskId = util.waitForIsElementPresent(By.xpath("//li[contains(@class,'task') and ./div/p/a[contains(text(),'"+module.subjectKey+"')]]")).getAttribute("id");
        assertTrue(util.waitForIsElementPresent(By.xpath("//a[contains(@class,'_content') and contains(text(),'"+module.subjectKey+"')]")).isDisplayed());

        //할일의 개수를 확인해서 한개 늘어난것 확인
        util.waitForPageLoaded();
        newTaskNum = util.getXpathCount(By.xpath("//div[@class='_list todo_list']/div/ul/li"));
        assertTrue(newTaskNum == taskNum+1);

        module.deleteTask(util,taskId,newTaskNum);
    }


    /*
    * Step : 좌측영역 > 할일 쓰기 클릭
    * Result : 할일 생성
    */
    @Test
    public void TC_07_할일쓰기_시작전할일쓰기_Test() throws Exception {

        if(util.waitForIsNotVisible(By.xpath("//div[@class='no_list']")))
        {
            taskNum = util.getXpathCount(By.xpath("//div[@class='_list todo_list']/div/ul/li"));
        }
        else{
            taskNum=0;
        }
        //할 일 쓰기 클릭해서 할일 쓰기로 진입
        util.click(By.xpath("//span[contains(text(),'할 일 쓰기')]"));
        util.waitForIsElementPresent(By.xpath("//div[@class='ly_todo_wrap']"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//div[@class='ly_todo_wrap']")).isDisplayed());

        util.type(By.xpath("//div[@class='input_txt']/input"), "시작전 할일 쓰기"+module.subjectKey);
        util.click(By.xpath("//input[@id='_task_detail_progressStatus_0']"));

        util.waitForIsElementPresent(By.xpath("//button[@class='_save btn_emphasis']"));
        util.click(By.xpath("//button[@class='_save btn_emphasis']"));

        //할일 쓰기라는 제목을 가진 할 일 노출 되는것 확인
        util.waitForIsElementPresent(By.xpath("//a[contains(@class,'_content') and contains(text(),'"+module.subjectKey+"')]"));
        taskId = util.waitForIsElementPresent(By.xpath("//li[contains(@class,'task') and ./div/p/a[contains(text(),'"+module.subjectKey+"')]]")).getAttribute("id");
        assertTrue(util.waitForIsElementPresent(By.xpath("//a[contains(@class,'_content') and contains(text(),'"+module.subjectKey+"')]")).isDisplayed());
        assertTrue(util.waitForIsElementPresent(By.xpath("//li[@id='"+taskId+"']/div/a[contains(@class,'_progress_status btn_state v1')]")).isDisplayed());

        //할일의 개수를 확인해서 한개 늘어난것 확인
        util.waitForPageLoaded();
        newTaskNum = util.getXpathCount(By.xpath("//div[@class='_list todo_list']/div/ul/li"));
        assertTrue(newTaskNum == taskNum + 1);

        module.deleteTask(util, taskId, newTaskNum);
    }


    /*
    * Step : 좌측영역 > 할일 쓰기 클릭
    * Result : 할일 생성
    */
    @Test
    public void TC_08_할일쓰기_진행중할일쓰기_Test() throws Exception {

        if(util.waitForIsNotVisible(By.xpath("//div[@class='no_list']")))
        {
            taskNum = util.getXpathCount(By.xpath("//div[@class='_list todo_list']/div/ul/li"));
        }
        else{
            taskNum=0;
        }
        //할 일 쓰기 클릭해서 할일 쓰기로 진입
        util.click(By.xpath("//span[contains(text(),'할 일 쓰기')]"));
        util.waitForIsElementPresent(By.xpath("//div[@class='ly_todo_wrap']"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//div[@class='ly_todo_wrap']")).isDisplayed());

        util.type(By.xpath("//div[@class='input_txt']/input"), "진행중 할일 쓰기"+module.subjectKey);
        util.click(By.xpath("//input[@id='_task_detail_progressStatus_1']"));

        util.waitForIsElementPresent(By.xpath("//button[@class='_save btn_emphasis']"));
        util.click(By.xpath("//button[@class='_save btn_emphasis']"));

        //할일 쓰기라는 제목을 가진 할 일 노출 되는것 확인
        util.waitForIsElementPresent(By.xpath("//a[contains(@class,'_content') and contains(text(),'"+module.subjectKey+"')]"));
        taskId = util.waitForIsElementPresent(By.xpath("//li[contains(@class,'task') and ./div/p/a[contains(text(),'"+module.subjectKey+"')]]")).getAttribute("id");
        assertTrue(util.waitForIsElementPresent(By.xpath("//a[contains(@class,'_content') and contains(text(),'"+module.subjectKey+"')]")).isDisplayed());
        assertTrue(util.waitForIsElementPresent(By.xpath("//li[@id='"+taskId+"']/div/a[contains(@class,'_progress_status btn_state v2')]")).isDisplayed());

        //할일의 개수를 확인해서 한개 늘어난것 확인
        util.waitForPageLoaded();
        newTaskNum = util.getXpathCount(By.xpath("//div[@class='_list todo_list']/div/ul/li"));
        assertTrue(newTaskNum == taskNum + 1);
        module.deleteTask(util, taskId, newTaskNum);
    }


    /*
    * Step : 좌측영역 > 할일 쓰기 클릭
    * Result : 할일 생성
    */

    @Test
    public void TC_09_할일쓰기_완료할일쓰기_Test() throws Exception {


        //완료된 할 일 포함 노출 안되어 있을 경우 클릭
        if (util.waitForIsNotVisible(By.xpath("//li[@class='_task complete']/div/a[contains(@class,'_progress_status btn_state v3')]"))) {
            util.click(By.xpath("//input[@class='_includingCompleted']"));
        }

        if(util.waitForIsNotVisible(By.xpath("//div[@class='no_list']")))
        {
            taskNum = util.getXpathCount(By.xpath("//div[@class='_list todo_list']/div/ul/li"));
        }
        else{
            taskNum=0;
        }

        //할 일 쓰기 클릭해서 할일 쓰기로 진입
        util.click(By.xpath("//span[contains(text(),'할 일 쓰기')]"));
        util.waitForIsElementPresent(By.xpath("//div[@class='ly_todo_wrap']"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//div[@class='ly_todo_wrap']")).isDisplayed());

        util.type(By.xpath("//div[@class='input_txt']/input"), "완료 할일 쓰기"+module.subjectKey);
        util.click(By.xpath("//input[@id='_task_detail_progressStatus_2']"));

        util.waitForIsElementPresent(By.xpath("//button[@class='_save btn_emphasis']"));
        util.click(By.xpath("//button[@class='_save btn_emphasis']"));

        util.waitForIsElementPresent(By.xpath("//input[@class='_includingCompleted']"));
        util.click(By.xpath("//input[@class='_includingCompleted']"));

        if (util.waitForIsNotVisible(By.xpath("//li[@class='_task complete']/div/a[contains(@class,'_progress_status btn_state v3')]"))) {
            util.click(By.xpath("//input[@class='_includingCompleted']"));
        }

        //할일 쓰기라는 제목을 가진 할 일 노출 되는것 확인
        util.waitForIsElementPresent(By.xpath("//a[contains(@class,'_content') and contains(text(),'"+module.subjectKey+"')]"));
        taskId = util.waitForIsElementPresent(By.xpath("//li[contains(@class,'task') and ./div/p/a[contains(text(),'"+module.subjectKey+"')]]")).getAttribute("id");
        assertTrue(util.waitForIsElementPresent(By.xpath("//a[contains(@class,'_content') and contains(text(),'"+module.subjectKey+"')]")).isDisplayed());
        assertTrue(util.waitForIsElementPresent(By.xpath("//li[@id='" + taskId + "']/div/a[contains(@class,'_progress_status btn_state v3')]")).isDisplayed());

        //할일의 개수를 확인해서 한개 늘어난것 확인
        util.waitForPageLoaded();
        newTaskNum = util.getXpathCount(By.xpath("//div[@class='_list todo_list']/div/ul/li"));
        System.out.println(taskNum+"  "+newTaskNum);

        assertTrue(newTaskNum == taskNum + 1);

        module.deleteTask(util, taskId, newTaskNum);
    }


    /*
    * Step : 좌측영역 > 할일 쓰기 클릭
    * Result : 할일 생성
    */
    @Test
    public void TC_10_할일쓰기_알림할일쓰기_Test() throws Exception {
        if(util.waitForIsNotVisible(By.xpath("//div[@class='no_list']")))
        {
            taskNum = util.getXpathCount(By.xpath("//div[@class='_list todo_list']/div/ul/li"));
        }
        else{
            taskNum=0;
        }
        //할 일 쓰기 클릭해서 할일 쓰기로 진입
        util.click(By.xpath("//span[contains(text(),'할 일 쓰기')]"));
        util.waitForIsElementPresent(By.xpath("//div[@class='ly_todo_wrap']"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//div[@class='ly_todo_wrap']")).isDisplayed());

        util.type(By.xpath("//div[@class='input_txt']/input"),"알림 할일 쓰기"+module.subjectKey);
        util.waitForIsElementPresent(By.xpath("//div[@class='_alarmField article alarm']/div/input"));
        util.click(By.xpath("//div[@class='_alarmField article alarm']/div/input"));

        util.waitForIsElementPresent(By.xpath("//button[@class='_save btn_emphasis']"));
        util.click(By.xpath("//button[@class='_save btn_emphasis']"));

        //할일 쓰기라는 제목을 가진 할 일 노출 되는것 확인
        util.waitForIsElementPresent(By.xpath("//a[contains(@class,'_content') and contains(text(),'"+module.subjectKey+"')]"));
        taskId = util.waitForIsElementPresent(By.xpath("//li[contains(@class,'task') and ./div/p/a[contains(text(),'"+module.subjectKey+"')]]")).getAttribute("id");
        assertTrue(util.waitForIsElementPresent(By.xpath("//a[contains(@class,'_content') and contains(text(),'"+module.subjectKey+"')]")).isDisplayed());

        //할일의 개수를 확인해서 한개 늘어난것 확인
        util.waitForPageLoaded();
        newTaskNum = util.getXpathCount(By.xpath("//div[@class='_list todo_list']/div/ul/li"));
        assertTrue(newTaskNum == taskNum+1);

        module.deleteTask(util,taskId,newTaskNum);
    }


    /*
    * Step : 좌측영역 > 할일 쓰기 클릭
    * Result : 할일 생성
    */
    @Test
    public void TC_11_할일쓰기_설명할일쓰기_Test() throws Exception {
        if(util.waitForIsNotVisible(By.xpath("//div[@class='no_list']")))
        {
            taskNum = util.getXpathCount(By.xpath("//div[@class='_list todo_list']/div/ul/li"));
        }
        else{
            taskNum=0;
        }
        //할 일 쓰기 클릭해서 할일 쓰기로 진입
        util.click(By.xpath("//span[contains(text(),'할 일 쓰기')]"));
        util.waitForIsElementPresent(By.xpath("//div[@class='ly_todo_wrap']"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//div[@class='ly_todo_wrap']")).isDisplayed());

        util.type(By.xpath("//div[@class='input_txt']/input"),"설명 할일 쓰기"+module.subjectKey);
        util.waitForIsElementPresent(By.xpath("//div[@class='_memoField script_area']/div/div"));
        util.type(By.xpath("//div[@class='_memoField script_area']/div/div/textarea"),"설명 작성");

        util.waitForIsElementPresent(By.xpath("//button[@class='_save btn_emphasis']"));
        util.click(By.xpath("//button[@class='_save btn_emphasis']"));

        //할일 쓰기라는 제목을 가진 할 일 노출 되는것 확인
        util.waitForIsElementPresent(By.xpath("//a[contains(@class,'_content') and contains(text(),'"+module.subjectKey+"')]"));
        taskId = util.waitForIsElementPresent(By.xpath("//li[contains(@class,'task') and ./div/p/a[contains(text(),'"+module.subjectKey+"')]]")).getAttribute("id");
        assertTrue(util.waitForIsElementPresent(By.xpath("//a[contains(@class,'_content') and contains(text(),'"+module.subjectKey+"')]")).isDisplayed());

        //할일의 개수를 확인해서 한개 늘어난것 확인
        util.waitForPageLoaded();
        newTaskNum = util.getXpathCount(By.xpath("//div[@class='_list todo_list']/div/ul/li"));
        assertTrue(newTaskNum == taskNum+1);

        module.deleteTask(util,taskId,newTaskNum);
    }

}
