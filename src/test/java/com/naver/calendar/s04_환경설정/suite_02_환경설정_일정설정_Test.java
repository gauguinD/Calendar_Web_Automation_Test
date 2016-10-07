package com.naver.calendar.s04_환경설정;

import main.TestIds;
import main.Testcase;
import main.Utilities;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;


public class suite_02_환경설정_일정설정_Test extends Testcase {

    public String Title = null;
    public String URL = null;


    public String basicValue;
    public String basicCalName;
    public int randomNum;
    public String subName;
    public String privateCalendarValue;

    public String tempValue = null;
    public String tempCalName = null;
    public int maxCalNum;
    public int tempNum;

    public String calEdit;
    public String calEditMemberList;
    public String calEditMemberValue = null;
    public String calEditResult;
    public String calEditName;

    public  String assertAlert;

    public void settingClick(Utilities util) throws Exception {
        util.waitForIsElementPresent(By.xpath("//a[@class='_config']"));
        util.click(By.xpath("//a[@class='_config']"));
        util.waitForIsElementPresent(By.linkText("이전으로 돌아가기"));
    }

    public void makeCalendar(Utilities util) throws Exception{
        util.waitForIsElementPresent(By.xpath("//a[@class='btn_makecal']"));
        //util.mouseOver(By.xpath("//a[@class='btn_makecal']"));
        //디자인 패치 이후에 설정,캘린더 생성 버튼이 감춰져서 상위 xpath에 마우스 오버 하고 접근
        util.mouseOver(By.xpath("//div[@class='cal_tit']/p"));
        util.click(By.xpath("//a[@class='btn_makecal']"));
        util.waitForIsElementPresent(By.xpath("//div[@class='calendar_menu_wrap']"));
    }

    /*public void makeCalendar(Utilities util) throws Exception{
        util.waitForIsElementPresent(By.xpath("//div[@class='cal_tit']/a[1]"));
        util.mouseOver(By.xpath("//div[@class='cal_tit']/a[1]"));
        util.click(By.xpath("//div[@class='cal_tit']/a[1]"));
        util.waitForIsElementPresent(By.xpath("//div[@class='calendar_menu_wrap']"));
    }*/

    /*
    * Step : 로그인 > 해당 계정으로 로그인
    * Result : 해당하는 계정으로 로그인 됨
    */

    @Test
    public void TC_00_일정설정_로그인_Test() throws Exception {
        module.로그인(util, TestIds.CalUser.getId(), TestIds.CalUser.getPw());
    }


    /*
     * Step : 일정설정 > 한주의 시작 확인
     * Result : 한주의 시작의 현재 값을 확인
     */

    @Test
    public void TC_01_일정설정_한주의시작_Test() throws Exception {

        //환경설정 이동
        //util.click(By.className("_config"));
        //util.waitForIsElementPresent(By.linkText("캘린더로 돌아가기"));
        settingClick(util);


        //환경설정 > 일정설정 이동
        util.click(By.xpath("//ul[@class='tab_setting tabs']/li[2]"));
        util.waitForIsElementPresent(By.xpath("//div[@class='_schedule tc-panel tc-selected']"));

        //환경설정 > 일정설정 > 현재 한주의 시작 값 확인
        if(util.waitForIsElementPresent(By.xpath("//input[contains(@class,'_cfg_start_day inp01')][contains(@value,'sunday')]")).isSelected()) {
            util.printLog("현재 한주의 시작은 [일요일] 로 설정되어 있습니다.");
            assertTrue(util.isElementPresent(By.xpath("//input[contains(@class,'_cfg_start_day inp01')][contains(@value,'sunday')]")).isSelected());
        }
        else {
            util.printLog("현재 한주의 시작은 [월요일] 로 설정 되어 있습니다.");
            assertTrue(util.isElementPresent(By.xpath("//input[contains(@class,'_cfg_start_day inp01')][contains(@value,'monday')]")).isSelected());
        }
    }


     /*
     * Step : 일정설정 > 시간표시 확인
     * Result : 시간표시의 현재 값을 확인
     */

    @Test
    public void TC_02_일정설정_시간표시_Test() throws Exception{

        //환경설정 > 일정설정인것 확인
        util.waitForIsElementPresent(By.xpath("//div[@class='_schedule tc-panel tc-selected']"));

        //환경설정 > 일정설정 > 시간표시의 현재 값 확인
        if(util.waitForIsElementPresent(By.xpath("//input[contains(@class,'_cfg_time_mode inp01')][contains(@value,'false')]")).isSelected()) {
            util.printLog("현재 시간표시는 [오후 01:00] 로 설정되어 있습니다.");
            assertTrue(util.isElementPresent(By.xpath("//input[contains(@class,'_cfg_time_mode inp01')][contains(@value,'false')]")).isSelected());
        }
        else {
            util.printLog("현재 시간표시는 [13:00] 로 설정 되어 있습니다.");
            assertTrue(util.isElementPresent(By.xpath("//input[contains(@class,'_cfg_time_mode inp01')][contains(@value,'true')]")).isSelected());
        }
    }


    /*
    * Step : 일정설정 > 일정표시시간 확인
    * Result : 일정표시시간의 현재 값을 확인
    */

    @Test
    public void TC_03_일정설정_일정표시시간_Test() throws Exception {

        int valueNum = 0;
        String startTime;

        //환경설정 > 일정설정인것 확인
        util.waitForIsElementPresent(By.xpath("//div[@class='_schedule tc-panel tc-selected']"));

        //일정 표시 시간 > 시작시간의 설정값의 개수를 받아옴
        valueNum = util.getXpathCount(By.xpath("//select[@class='_cfg_display_start_idx']/option"));

        //일정 표시 시간 > 시작시간의 설정값의 개수만큼 for문을 돔
        for(int i=1; i < valueNum+1; i++ ){
            //selected된 값이 있으면 그 값의 text를 반환하고 그 값이 select 된지를 assert 함
            if(util.waitForIsElementPresent(By.xpath("//select[@class='_cfg_display_start_idx']/option["+i+"]")).isSelected()){
                startTime = util.waitForIsElementPresent(By.xpath("//select[@class = '_cfg_display_start_idx']/option["+i+"]")).getText();
                util.printLog("현재 설정된 시작 시간은 "+startTime);
                assertTrue(util.waitForIsSelected((By.xpath("//select[@class = '_cfg_display_start_idx']/option["+i+"]"))));
                break;
            }
        }

        //일정 표시 시간 > 종료시간의 설정값의 개수를 받아옴
        valueNum = util.getXpathCount(By.xpath("//select[@class = '_cfg_display_end_idx']/option"));

        //일정 표시 시간 > 종료시간의 설정값의 개수만큼 for문을 돔
        for(int i=1; i < valueNum+1; i++ ){
            //selected된 값이 있으면 그 값의 text를 반환하고 그 값이 select 된지를 assert 함
            if(util.waitForIsElementPresent(By.xpath("//select[@class = '_cfg_display_end_idx']/option["+i+"]")).isSelected()){
                startTime = util.waitForIsElementPresent(By.xpath("//select[@class = '_cfg_display_end_idx']/option["+i+"]")).getText();
                util.printLog("현재 설정된 종료 시간은 "+startTime);
                assertTrue(util.waitForIsSelected((By.xpath("//select[@class = '_cfg_display_end_idx']/option["+i+"]"))));
                break;
            }
        }

    }


    /*
    * Step : 일정설정 > 기본시간대 확인
    * Result : 기본시간대의 현재 값을 확인
    */

    @Test
    public void TC_04_일정설정_기본시간대_Test() throws Exception {

        int valueNum = 0;
        String basicTimeCountry;
        String basicTimeCity;

        //환경설정 > 일정설정인것 확인
        util.waitForIsElementPresent(By.xpath("//div[@class='_schedule tc-panel tc-selected']"));

        //기본시간대 > 기본시간대의 설정값의 개수를 받아옴
        valueNum = util.getXpathCount(By.xpath("//select[@class='_cfg_default_country_list']/option"));

        //기본시간대 > 기본시간대의 설정값의 개수만큼 for문을 돔
        for(int i=1; i < valueNum+1; i++ ){
            //selected된 값이 있으면 그 값의 text를 반환하고 그 값이 select 된지를 assert 함
            if(util.waitForIsElementPresent(By.xpath("//select[@class='_cfg_default_country_list']/option["+i+"]")).isSelected()){
                basicTimeCountry = util.waitForIsElementPresent(By.xpath("//select[@class = '_cfg_default_country_list']/option["+i+"]")).getText();
                util.printLog("현재 설정된 기본시간대 국가는 "+basicTimeCountry);
                assertTrue(util.waitForIsSelected((By.xpath("//select[@class = '_cfg_default_country_list']/option["+i+"]"))));
                break;
            }
        }

        //기본시간대 > 기본시간대 도시 설정값의 개수를 받아옴
        valueNum = util.getXpathCount(By.xpath("//select[@class = '_cfg_default_timezone_list']/option"));

        //기본시간대 > 기본시간대 도시 설정값의 개수만큼 for문을 돔
        for(int i=1; i < valueNum+1; i++ ){
            //selected된 값이 있으면 그 값의 text를 반환하고 그 값이 select 된지를 assert 함
            if(util.waitForIsElementPresent(By.xpath("//select[@class = '_cfg_default_timezone_list']/option["+i+"]")).isSelected()){
                basicTimeCity = util.waitForIsElementPresent(By.xpath("//select[@class = '_cfg_default_timezone_list']/option["+i+"]")).getText();
                util.printLog("현재 설정된 기본시간대 도시는 "+basicTimeCity);
                assertTrue(util.waitForIsSelected((By.xpath("//select[@class = '_cfg_default_timezone_list']/option["+i+"]"))));
                break;
            }
        }

    }


    /*
    * Step : 일정설정 > 추가시간대 확인
    * Result : 추가시간대의 현재 값을 확인
    */

    @Test
    public void TC_05_일정설정_추가시간대_Test() throws Exception {

        int valueNum = 0;
        String addTimeCountry;
        String addTimeCity;

        //환경설정 > 일정설정인것 확인
        util.waitForIsElementPresent(By.xpath("//div[@class='_schedule tc-panel tc-selected']"));

        //추가시간대 > 추가시간대의 설정값의 개수를 받아옴
        valueNum = util.getXpathCount(By.xpath("//select[@class='_cfg_default_country_list']/option"));

        //추가시간대 > 추시간대의 설정값의 개수만큼 for문을 돔
        for(int i=1; i < valueNum+1; i++ ){
            //selected된 값이 있으면 그 값의 text를 반환하고 그 값이 select 된지를 assert 함
            if(util.waitForIsElementPresent(By.xpath("//select[@class='_cfg_additional_country_list']/option["+i+"]")).isSelected()){
                addTimeCountry = util.waitForIsElementPresent(By.xpath("//select[@class = '_cfg_additional_country_list']/option["+i+"]")).getText();
                util.printLog("현재 설정된 추가시간대 국가는 "+addTimeCountry);
                assertTrue(util.waitForIsSelected((By.xpath("//select[@class = '_cfg_additional_country_list']/option["+i+"]"))));
                break;
            }
            else if(!(util.isElementPresent(By.xpath("//select[@class = '_cfg_additional_country_list']/option["+i+"]"))).isSelected()){
                util.printLog("현재 설정된 추가시간대는 없습니다");
                assertTrue(!(util.isElementPresent(By.xpath("//select[@class = '_cfg_additional_country_list']/option["+i+"]"))).isSelected());
            }

        }

        //추가시간대 > 추가시간대 도시의 설정값의 개수를 받아옴
        valueNum = util.getXpathCount(By.xpath("//select[@class = '_cfg_additional_timezone_list']/option"));

        //추가시간대 > 추가시간대 도시의 설정값의 개수만큼 for문을 돔
        for(int i=1; i < valueNum+1; i++ ){
            //selected된 값이 있으면 그 값의 text를 반환하고 그 값이 select 된지를 assert 함
            if(util.waitForIsElementPresent(By.xpath("//select[@class = '_cfg_additional_timezone_list']/option["+i+"]")).isSelected()){
                addTimeCity = util.waitForIsElementPresent(By.xpath("//select[@class = '_cfg_additional_timezone_list']/option["+i+"]")).getText();
                util.printLog("현재 설정된 추가시간대 도시는 "+addTimeCity);
                assertTrue(util.waitForIsSelected((By.xpath("//select[@class = '_cfg_additional_timezone_list']/option["+i+"]"))));
            }
            else if(!(util.isElementPresent(By.xpath("//select[@class = '_cfg_additional_timezone_list']/option["+i+"]"))).isSelected()){
                util.printLog("현재 설정된 추가시간대는 없습니다");
                assertTrue(!(util.isElementPresent(By.xpath("//select[@class = '_cfg_additional_timezone_list']/option["+i+"]"))).isSelected());
            }
        }

        //환경설정 > 일정설정에서 예시보기 링크 확인
        util.click(By.xpath("//a[@class='_additional_time_guide example2']"));
        util.waitForNewWindow();

        URL = util.getCurrentUrl();
        assertTrue(URL.contains("https://calendar.naver.com/html/guide/guide_additional_time.html"));

        util.close();
        util.selectMainWindow();

        util.waitForTitle(module.calTitle);

    }


    /*
     * Step : 일정설정 > 시간표시 확인
     * Result : 시간표시의 현재 값을 확인
     */

    @Test
    public void TC_06_일정설정_초대받은일정자동등록_Test() throws Exception{

        //환경설정 > 일정설정인것 확인
        util.waitForIsElementPresent(By.xpath("//div[@class='_schedule tc-panel tc-selected']"));

        //환경설정 > 일정설정 > 초대받은 일정 자동 등록값 확인
        if(util.waitForIsSelected(By.xpath("//input[contains(@class,'inp01 _cfg_auto_create_only_aware_schedule')]"))) {
            util.printLog("현재 초대받은 일정 자동등록 설정은 [모든 초대 일정을 내 캘린더에 등록]으로 설정되어 있습니다.");
            assertTrue(util.waitForIsSelected(By.xpath("//input[contains(@class,'inp01 _cfg_auto_create_only_aware_schedule')]")));
        }
        else {
            util.printLog("현재 초대받은 일정 자동등록 설정은 [내 주소록에 등록된 사람이 보낸 초대 일정만 내 캘린더에 등록]으로 설정 되어 있습니다.");
            assertTrue(util.waitForIsSelected(By.xpath("//input[contains(@class,'_cfg_auto_create_all_schedule')]")));
        }
    }


     /*
     * Step : 일정설정 > 캘린더 초기화 방법 안내
     * Result : 캘린더 초기화 방법 안내 페이지로 이동
     * URL : http://calendar.naver.com/notice.nhn?type=read&docId=10000000000003775681&boardId=1000003514
     */

    @Test
    public void TC_07_일정설정_캘린더초기화방법안내_Test() throws Exception{

        //환경설정 > 일정설정에서 캘린더초기화방법안내 링크 확인
        util.click(By.xpath("//a[@class='link_reset']"));
        util.waitForNewWindow();

        URL = util.getCurrentUrl();
        assertTrue(URL.contains("http://calendar.naver.com/notice.nhn?type=read&docId=10000000000003775681&boardId=1000003514"));

        util.close();
        util.selectMainWindow();

        util.waitForTitle(module.calTitle);
    }


     /*
     * Step : 일정설정 > 캘린더 목록 확인
     * Result : 현재 캘린더 개수 노출
     */

    @Test
    public void TC_08_일정설정_캘린더목록_Test() throws Exception{

        int calList;
        int calNum;

        util.goRefresh();
        util.waitForPageToLoad();

        //환경설정 이동
        settingClick(util);

        //환경설정 > 일정설정 이동
        util.click(By.xpath("//ul[@class='tab_setting tabs']/li[2]"));
        util.waitForIsElementPresent(By.xpath("//div[@class='_schedule tc-panel tc-selected']"));

        //환경설정 > 일정설정 > 현재 캘린더 목록의 숫자를 확인
        calList = util.getXpathCount(By.xpath("//tbody[@class='_private_calendar_list']/tr"));
        util.printLog("현재 캘린더 개수는 "+calList+"개 입니다.");

        //좌측영역의 캘린더 목록에서 할일을 뺀 숫자와 환경설정의 캘린더 목록이 같은지 확인
        calNum = util.getXpathCount(By.xpath("//ul[@class='category_list']/li"));
        System.out.println(calNum);
        assertTrue(calList==calNum-1);
    }


     /*
     * Step : 일정설정 > 기본캘린더 확인
     * Result : 현재 기본캘린더 확인, 변경 확인
     */

    @Test
    public void TC_09_일정설정_기본캘린더_Test() throws Exception{

        randomNum = util.getRandomNum(1,util.getXpathCount(By.xpath("//tbody[@class='_private_calendar_list']/tr")));
        basicValue = util.waitForIsElementPresent(By.xpath("//li[contains(@class,'_calendar') and ./a[2]/span[contains(.,'[기본]')]]")).getAttribute("calendarid");
        basicCalName = util.waitForIsElementPresent(By.xpath("//a[contains(@data-value,'"+basicValue+"')]/strong")).getText();

        //현재 기본 캘린더 명을 가져옴
        util.printLog("현재 기본 캘린더의 이름은 : "+basicCalName);

        //기본캘린더로 설정 가능한 캘린더가 클릭 될때까지 1~캘린더개수 에서 랜덤으로 숫자를 지정
        while(util.waitForIsElementPresent(By.xpath("//tbody/tr["+randomNum+"]/td/input")).isEnabled()){
            System.out.println(randomNum);
            util.click(By.xpath("//tbody/tr["+randomNum+"]/td/input"));
            break;
        }

        //저장하고 얼럿에서 확인
        util.click(By.xpath("//button[@class='_save normal']"));
        util.getAlert().accept();
        util.waitForPageLoaded();

        //환경설정 이동
        settingClick(util);

        //환경설정 > 일정설정 이동
        util.click(By.xpath("//ul[@class='tab_setting tabs']/li[2]"));
        util.waitForIsElementPresent(By.xpath("//div[@class='_schedule tc-panel tc-selected']"));

        basicValue = util.waitForIsElementPresent(By.xpath("//li[contains(@class,'_calendar') and ./a[2]/span[contains(.,'[기본]')]]")).getAttribute("calendarid");
        basicCalName = util.waitForIsElementPresent(By.xpath("//a[contains(@data-value,'"+basicValue+"')]/strong")).getText();

        //새로 설정한 기본 캘린더의 값이 좌측 영역 캘린더 목록에 노출되는지 확인
        //util.printLog(basicValue);
        util.printLog("현재 기본 캘린더의 이름은 : "+basicCalName);
        assertTrue(util.waitForIsElementPresent(By.xpath("//li[contains(@class,'_calendar') and ./a[2]/span[contains(.,'[기본]')]]")).getAttribute("calendarid").contains(basicValue));
    }

         /*
     * Step : 일정설정 > 기본캘린더 확인
     * Result : 현재 기본캘린더 확인, 변경 확인
     */

    @Test
    public void TC_10_일정설정_기본캘린더_기본캘린더복구_Test() throws Exception{

        util.waitForIsElementPresent(By.xpath("//input[contains(@class,'_cfg_calendar_default inp01') and contains(@value,'6819065')]"));
        util.click(By.xpath("//input[contains(@class,'_cfg_calendar_default inp01') and contains(@value,'6819065')]"));

        util.click(By.xpath("//button[@class='_save normal']"));
        util.getAlert().accept();
        util.waitForPageLoaded();

        //환경설정 이동
        settingClick(util);

        //환경설정 > 일정설정 이동
        util.click(By.xpath("//ul[@class='tab_setting tabs']/li[2]"));
        util.waitForIsElementPresent(By.xpath("//div[@class='_schedule tc-panel tc-selected']"));

        basicValue = util.waitForIsElementPresent(By.xpath("//li[contains(@class,'_calendar') and ./a[2]/span[contains(.,'[기본]')]]")).getAttribute("calendarid");
        basicCalName = util.waitForIsElementPresent(By.xpath("//a[contains(@data-value,'"+basicValue+"')]/strong")).getText();

        //새로 설정한 기본 캘린더의 값이 좌측 영역 캘린더 목록에 노출되는지 확인
        //util.printLog(basicValue);
        util.printLog("현재 기본 캘린더의 이름은 : "+basicCalName);
        assertTrue(util.waitForIsElementPresent(By.xpath("//li[contains(@class,'_calendar') and ./a[2]/span[contains(.,'[기본]')]]")).getAttribute("calendarid").contains(basicValue));
    }


    /*
    * Step : 일정설정 > 캘린더 목록 확인
    * Result : 목록에 표시 체크 해제시에 노출 안되는것 확인
    */

    @Test
    public void TC_11_일정설정_목록에표시_Test() throws Exception{

        maxCalNum = util.getXpathCount(By.xpath("//tbody[@class='_private_calendar_list']/tr"));
        randomNum = util.getRandomNum(2,maxCalNum);
        String[] calName = new String[maxCalNum];

        if(maxCalNum==1)
        {
            util.printLog("현재 기본 캘린더만 있습니다.");
            assertTrue(maxCalNum==1);
        }

        else {
            //목록에 표시 체크가 가능한 값을 random으로 찾음
            while (util.waitForIsElementPresent(By.xpath("//tbody[@class='_private_calendar_list']/tr[" + randomNum + "]/td[3]/input")).isEnabled()) {
                tempValue = util.waitForIsElementPresent(By.xpath("//tbody[@class='_private_calendar_list']/tr[" + randomNum + "]/td[2]/input")).getAttribute("value");
                tempCalName = util.waitForIsElementPresent(By.xpath("//a[contains(@data-value,'" + tempValue + "')]/strong")).getText();
                util.click(By.xpath("//tbody[@class='_private_calendar_list']/tr[" + randomNum + "]/td[3]/input"));
                break;
            }

            //현재 목록에 표시한 캘린더 명을 가져옴
            util.printLog(tempValue);
            util.printLog("목록에서 삭제할 캘린더 명은 : " + tempCalName);

            //저장하고 얼럿에서 확인
            util.click(By.xpath("//button[@class='_save normal']"));
            util.getAlert().accept();

            //환경설정 재 진입
            settingClick(util);

            //환경설정 > 일정설정 진입
            util.click(By.xpath("//ul[@class='tab_setting tabs']/li[2]"));
            util.waitForIsElementPresent(By.xpath("//div[@class='_schedule tc-panel tc-selected']"));

            util.printLog("현재 목록에 있는 캘린더는 다음과 같습니다.");
            //좌측영역의 캘린더 목록과 비교해서 목록표시 해제한 챌린더 제목이 없는것을 확인한다.

            for (int i = 1; i < maxCalNum; i++) {
                calName[i] = util.waitForIsElementPresent(By.xpath("//ul[@class='category_list']/li[" + i + "]")).getAttribute("calendarid");
                util.printLog(util.waitForIsElementPresent(By.xpath("//ul[@class='category_list']/li[" + i + "]/a[2]")).getAttribute("title"));
            }
            assertFalse(Arrays.toString(calName).contains(tempValue));
        }

    }


    /*
    * Step : 일정설정 > 캘린더 목록 확인
    * Result : 목록에 표시 체크 해제했던 캘린더 다시 목록에 표시
    */

    @Test
    public void TC_12_일정설정_목록에표시_목록에표시복구_Test() throws Exception{

        maxCalNum = util.getXpathCount(By.xpath("//tbody[@class='_private_calendar_list']/tr"));
        String[] calName = new String[maxCalNum];

        if (maxCalNum == 1) {
            util.printLog("현재 기본 캘린더만 있습니다.");
            assertTrue(maxCalNum==1);
        }

        else {
            //현재 목록에 표시한 캘린더 명을 가져옴
            util.printLog("목록에서 삭제한 캘린더 명은 : " + tempCalName);
            util.click(By.xpath("//tr[contains(@class,'" + tempValue + "')]/td[3]/input"));

            //저장하고 얼럿에서 확인
            util.click(By.xpath("//button[@class='_save normal']"));
            assertAlert = util.getAlert().getText();
            assertTrue(assertAlert.contains("환경설정의 변경 사항이 저장되었습니다."));
            util.getAlert().accept();
            util.waitForPageLoaded();

            //환경설정 재 진입
            settingClick(util);

            //환경설정 > 일정설정 진입
            util.click(By.xpath("//ul[@class='tab_setting tabs']/li[2]"));
            assertTrue(util.waitForIsElementPresent(By.xpath("//div[@class='_schedule tc-panel tc-selected']")).isDisplayed());

            util.printLog("현재 목록에 있는 캘린더는 다음과 같습니다.");

            //좌측영역의 캘린더 목록과 비교해서 목록표시 복귀한 챌린더 제목이 없는것을 확인한다.
            for (int i = 1; i <= maxCalNum; i++) {
                calName[i-1] = util.waitForIsElementPresent(By.xpath("//ul[@class='category_list']/li[" + i + "]")).getAttribute("calendarid");
                util.printLog(Arrays.toString(calName));
                util.printLog(util.waitForIsElementPresent(By.xpath("//ul[@class='category_list']/li[" + i + "]/a[2]")).getAttribute("title"));
            }
            assertTrue(Arrays.toString(calName).contains(tempValue));
        }
    }


    /*
    * Step : 일정설정 > 외부일정 가져오기 확인
    * Result : 외부일정 가져오기 팝업 노출 확인
    */

    @Test
    public void TC_13_일정설정_외부일정_가져오기_Test() throws Exception{

        basicValue = util.waitForIsElementPresent(By.xpath("//li[contains(@class,'_calendar') and ./a[2]/span[contains(.,'[기본]')]]")).getAttribute("calendarid");
        basicCalName = util.waitForIsElementPresent(By.xpath("//a[contains(@data-value,'"+basicValue+"')]/strong")).getText();

        util.click(By.xpath("//a[contains(@class,'_import') and contains(@data-value,'"+basicValue+"')]"));
        util.waitForNewWindow();

        URL = util.getCurrentUrl();
        assertTrue(URL.contains("https://calendar.naver.com/popup/importAndExport.nhn?action=popup&tab=import&calendarId="+basicValue+""));

        util.waitForIsElementPresent(By.xpath("//li[@class='_import_title tc-tab tc-selected']"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//li[@class='_import_title tc-tab tc-selected']")).isDisplayed());

        util.close();
        util.selectMainWindow();

        util.waitForTitle(module.calTitle);
    }


    /*
    * Step : 일정설정 > 외부일정 내보내기 확인
    * Result : 외부일정 내보내기 팝업 노출 확인
    */

    @Test
    public void TC_14_일정설정_외부일정_내보내기_Test() throws Exception{

        util.click(By.xpath("//tr[contains(@data-value,'"+basicValue+"')]/td[4]/a[2]"));
        util.waitForNewWindow();

        URL = util.getCurrentUrl();
        assertTrue(URL.contains("https://calendar.naver.com/popup/importAndExport.nhn?action=popup&tab=export&calendarId="+basicValue+""));

        util.waitForIsElementPresent(By.xpath("//li[@class='_export_title tc-tab tc-selected']"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//li[@class='_export_title tc-tab tc-selected']")).isDisplayed());

        util.close();
        util.selectMainWindow();

        util.waitForTitle(module.calTitle);
    }


    /*
    * Step : 일정설정 > 캘린더 삭제 > 삭제할 캘린더를 생성
    * Result : 삭제할캘린더+오늘날짜 생성 됨
    */

    @Test
    public void TC_15_일정설정_삭제_삭제할캘린더생성_Test() throws Exception{

        makeCalendar(util);
        /*
        util.waitForIsElementPresent(By.xpath("//a[@class='btn_makecal']"));
        util.mouseOver(By.xpath("//a[@class='btn_makecal']"));
        util.click(By.xpath("//a[@class='btn_makecal']"));
        util.click(By.partialLinkText("내 캘린더 만들기"));

        */
        util.click(By.xpath("//li[@class='_private']"));


        util.clearAndType(By.id("$$_calendar_name"),"삭제할캘린더"+module.contents);
        subName = util.waitForIsElementPresent(By.id("$$_calendar_name")).getAttribute("value");
        util.printLog(subName);

        util.click(By.xpath("//button[@class ='_save normal']"));
        util.waitForIsElementPresent(By.className("search"));

        //캘린더 목록의 캘린더 갯수 가져오기
        int NumberOfCalender = util.getXpathCount(By.xpath("//ul[@class='category_list']/li"));
        util.printLog("캘린더 목록의 갯수 가져오기 :" + NumberOfCalender);

        //환경설정 재 진입
        settingClick(util);

        //환경설정 > 일정설정 진입
        util.click(By.xpath("//ul[@class='tab_setting tabs']/li[2]"));
        assertTrue(util.waitForIsElementPresent(By.xpath("//div[@class='_schedule tc-panel tc-selected']")).isDisplayed());

        //생성된 캘린더가 있는지 캘린더 목록에서 확인한다
        for(int i=1; i < NumberOfCalender; i++)
        {
            String subTemp;
            System.out.println(i);
            //subTemp = util.waitForIsElementPresent(By.xpath("//ul[contains(@class,'category_list')]/li["+i+"]/a[2]")).getAttribute("title");
            subTemp = util.waitForIsElementPresent(By.xpath("//tbody[@class='_private_calendar_list']/tr["+i+"]/td/a/strong")).getText();
            if(subTemp.contentEquals(subName))
            {
                util.printLog("True");
                privateCalendarValue = util.waitForIsElementPresent(By.xpath("//ul[@class='category_list']/li["+i+"]")).getAttribute("calendarid");
                util.printLog(privateCalendarValue);
            }
        }
    }


    /*
    * Step : 일정설정 > 캘린더 삭제 클릭
    * Result : 해당 캘린더 목록에서 노출 안됨
    */

    @Test
    public void TC_16_일정설정_삭제_Test() throws Exception{

        util.click(By.className("_config"));
        util.waitForIsElementPresent(By.linkText("캘린더로 돌아가기"));

        util.click(By.xpath("//ul[@class='tab_setting tabs']/li[2]"));
        util.waitForIsElementPresent(By.xpath("//div[@class='_schedule tc-panel tc-selected']"));

        String[] calName = new String[maxCalNum];
        util.waitForIsElementPresent(By.xpath("//tr[contains(@class,'_cfg_calendar_list') and ./td[5]/div/a[contains(@class,'_del_del link')]]"));
        maxCalNum = util.getXpathCount(By.xpath("//tbody[@class='_private_calendar_list']/tr"));

        //기본 캘린더만 있을때 예외처리
        if(maxCalNum == 1){
            util.printLog("현재 삭제할 캘린더가 없습니다.");
        }
        else{
            for(int i=2; i < maxCalNum+1; i++){
                calEdit = util.waitForIsElementPresent(By.xpath("//tbody[@class='_private_calendar_list']/tr["+i+"]/td[5]/div")).getText();
                //비공개캘린더일때 삭제 노출 됨
                if(calEdit.contains("삭제")){
                    tempCalName = util.waitForIsElementPresent(By.xpath("//tbody[contains(@class,'_private_calendar_list')]/tr[" + i + "]/td[1]/a")).getText();
                    tempValue = util.waitForIsElementPresent(By.xpath("//tbody[contains(@class,'_private_calendar_list')]/tr[" + i + "]")).getAttribute("data-value");

                    util.waitForIsElementPresent(By.xpath("//tbody[@class='_private_calendar_list']/tr["+i+"]/td[5]/div/a[1]"));
                    util.click(By.xpath("//tbody[@class='_private_calendar_list']/tr["+i+"]/td[5]/div/a[1]"));
                    util.printLog("삭제하는 캘린더 명은  [" + tempCalName + "] 입니다.");

                    assertAlert = util.getAlert().getText();
                    assertTrue(assertAlert.contains("캘린더를 삭제하면 캘린더에 등록되어 있는 모든 일정도 영구적으로 삭제됩니다."));
                    util.getAlert().accept();
                    break;
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
        settingClick(util);

        //환경설정 > 일정설정 진입
        util.click(By.xpath("//ul[@class='tab_setting tabs']/li[2]"));
        util.waitForIsElementPresent(By.xpath("//div[@class='_schedule tc-panel tc-selected']"));

        //좌측영역의 캘린더 목록과 비교해서 캘린더 1개가 삭제된것을 확인한다.
        for(int i=1; i < maxCalNum-1; i++) {
            System.out.println(i);
            calName[i] = util.waitForIsElementPresent(By.xpath("//ul[@class='category_list']/li[" + i + "]")).getAttribute("calendarid");
           }
        assertFalse(Arrays.toString(calName).contains(tempValue));
    }


    /*
    * Step : 일정설정 > 캘린더 삭제 > 양도할 캘린더를 생성
    * Result : 양도할캘린더+오늘날짜 생성 됨
    */

    @Test
    public void TC_17_일정설정_양도_양도할캘린더생성_Test() throws Exception{

        String subName;

        makeCalendar(util);
        util.click(By.xpath("//li[@class ='_share']"));

        util.clearAndType(By.id("$$_calendar_name"),"양도할캘린더"+module.contents);

        util.type(By.xpath("//input[@class='input_txt _input']"),TestIds.CalUser2.getId());
        util.click(By.xpath("//span[@class='add_btn']"));
        util.waitForIsElementPresent(By.xpath("//ul[@class='_member_list']/li[2]"));

        subName = util.waitForIsElementPresent(By.id("$$_calendar_name")).getAttribute("value");
        util.printLog(subName);

        util.click(By.xpath("//button[@class ='_save normal']"));
    }


    /*
    * Step : 로그인 > 해당 계정으로 로그인
    * Result : 해당하는 계정으로 로그인 됨
    */

    @Test
    public void TC_18_일정설정_양도_양도할캘린더생성_초대_Test() throws Exception {
        //양도받을 계정으로 로그인
        module.LogOutAndLogIn(util, TestIds.CalUser2.getId(), TestIds.CalUser2.getPw());
        util.sleep(3);
        //캘린더 페이지 노출되는것 대기
        //util.waitForIsElementPresent(By.xpath("//a[@class='_svc_lnk pwe_home']"));

        //캘린더 초대 수락 하기 위해서 메일로 이동
        util.waitForPageLoaded();
        util.click(By.className("mail"));
        module.assertCalendarPage(util,module.mailTitle,module.mailURL);

        util.click(By.xpath("//ol[@class='mailList  preview_none sender_context ']/li[1]"));
        util.waitForIsElementPresent(By.xpath("//*[@id='readFrame']/xmeta/xmeta/xmeta/xmeta/xmeta/table[1]/tbody/tr[2]/td/table[2]/tbody/tr/td/table[3]/tbody/tr[6]/td/a[1]"));
        util.click(By.xpath("//*[@id=\"readFrame\"]/xmeta/xmeta/xmeta/xmeta/xmeta/table[1]/tbody/tr[2]/td/table[2]/tbody/tr/td/table[3]/tbody/tr[6]/td/a[1]"));

        util.waitForNewWindow();
        util.click(By.xpath("//button[@class='normal btn_emphasis _acceptInvitation']"));
        util.waitForIsElementPresent(By.xpath("//button[@class='_acceptInvitation normal4 btn_emphasis']"));

        util.close();
        util.selectMainWindow();

        module.LogOutAndLogIn(util, TestIds.CalUser.getId(),TestIds.CalUser.getPw());
        util.sleep(3);

        //캘린더 초대 수락 하기 위해서 메일로 이동
        util.waitForPageLoaded();
        util.click(By.className("calendar"));
        module.assertCalendarPage(util,module.calTitle,module.calURL);

        settingClick(util);
        //환경설정 > 일정설정 진입
        util.click(By.xpath("//ul[@class='tab_setting tabs']/li[2]"));
        util.waitForIsElementPresent(By.xpath("//div[@class='_schedule tc-panel tc-selected']"));
    }


    /*
    * Step : 일정설정 > 캘린더 양도 클릭
    * Result : 해당 캘린더 목록에서 마스터 변경 되어 있음
    */

    @Test
    public void TC_19_일정설정_양도_Test() throws Exception {

        util.waitForIsElementPresent(By.xpath("//tr[contains(@class,'_cfg_calendar_list') and ./td[5]/div/a[contains(@class,'_del_del link')]]"));
        maxCalNum = util.getXpathCount(By.xpath("//tbody[@class='_private_calendar_list']/tr"));

        for(int i=1; i< maxCalNum+1; i++){
            calEdit = util.waitForIsElementPresent(By.xpath("//tbody[@class='_private_calendar_list']/tr["+i+"]/td[5]/div")).getText();
            //공유캘린더 참석자, 마스터일때 탈퇴 노출 됨
            if(calEdit.contains("양도")){
                //공유캘린더 마스터 일때 양도,탈퇴,폐쇄 노출 됨
                if(calEdit.contains("양도")){
                    util.waitForIsElementPresent(By.xpath("//tbody[@class='_private_calendar_list']/tr["+i+"]/td[5]/div/a[1]"));
                    util.click(By.xpath("//tbody[@class='_private_calendar_list']/tr["+i+"]/td[5]/div/a[1]"));
                    //공유캘린더 탈퇴시에 참가자가 있을경우 예외처리
                    calEditMemberList = util.waitForIsElementPresent(By.xpath("//tbody[@class='_member_list_body']")).getText();

                    if(calEditMemberList.contains("수정/삭제")){
                        //참석자에 수정/삭제 권한이 있는경우 해당 권한 사용자를 클릭
                        util.waitForIsElementPresent(By.xpath("//tbody[@class='_member_list_body']/tr/td/input"));
                        util.click(By.xpath("//tbody[@class='_member_list_body']/tr[./td[3][contains(text(),'수정/삭제')]]/td/input[@class='radio01']"));

                        //마스터를 양도하는 사용자의 정보를 받아온다.
                        calEditMemberValue = util.waitForIsElementPresent(By.xpath("//tbody[@class='_member_list_body']/tr[./td[3][contains(text(),'수정/삭제')]]/td/input[@class='radio01']")).getAttribute("value");
                        calEditName = util.waitForIsElementPresent(By.xpath("//tbody[@class='_private_calendar_list']/tr["+i+"]/td[1]/a/strong")).getText();

                        //양도 버튼을 클릭
                        util.clickAndNoWait(By.xpath("//div[@class='layer_footer']/button[1]"));
                        util.waitForPageLoaded();
                        util.sleep(3);

                        //양도 버튼을 클릭하고 다시 환경설정 > 일정설정으로 와서 해당 캘린더에서 권한이 바뀌어 탈퇴만 노출되는것을 확인한다.
                        settingClick(util);

                        util.click(By.xpath("//ul[@class='tab_setting tabs']/li[2]"));
                        util.waitForIsElementPresent(By.xpath("//div[@class='_schedule tc-panel tc-selected']"));

                        calEditResult = util.waitForIsElementPresent(By.xpath("//tbody[@class='_private_calendar_list']/tr["+i+"]/td[5]/div")).getText();
                        util.printLog("양도한 캘린더 명은 ["+calEditName+"]이며, 양도받은 참석자 명은 ["+calEditMemberValue+"] 입니다.");

                        assertTrue(calEditResult.contains("탈퇴"));
                        break;
                    }

                    else if (calEditMemberList.contains("조회")){
                        util.click(By.xpath("//tbody[@class='_member_list_body']/tr[./td[3][contains(text(),'조회')]]/td/input[@class='radio01']"));
                        calEditMemberValue = util.waitForIsElementPresent(By.xpath("//tbody[@class='_member_list_body']/tr[./td[3][contains(text(),'수정/삭제')]]/td/input[@class='radio01']")).getAttribute("value");
                        util.printLog(calEditMemberValue);
                        //조회권한일경우 취소 버튼 예외처리
                        util.click(By.xpath("//div[@class='layer_footer']/button[2]"));
                    }
                    else if (calEditMemberList.contains("모든 권한")){
                        util.click(By.xpath("//tbody[@class='_member_list_body']/tr[./td[3][contains(text(),'모든 권한')]]/td/input[@class='radio01']"));
                        calEditMemberValue = util.waitForIsElementPresent(By.xpath("//tbody[@class='_member_list_body']/tr[./td[3][contains(text(),'수정/삭제')]]/td/input[@class='radio01']")).getAttribute("value");
                        util.printLog(calEditMemberValue);
                        //모든권한일경우 취소 버튼 예외처리
                        util.click(By.xpath("//div[@class='layer_footer']/button[2]"));
                    }
                    else{
                        util.click(By.xpath("//div[@class='layer_footer']/button[2]"));
                    }

                }

            }

        }
    }


    /*
    * Step : 일정설정 > 캘린더 삭제 > 양도한 캘린더를 삭제
    * Result : 양도한 캘린더 삭제 된다.
    */

    //@Test
    public void TC_20_일정설정_양도_양도한캘린더삭제_Test() throws Exception {

        module.LogOutAndLogIn(util, TestIds.CalUser2.getId(),TestIds.CalUser2.getPw());
        util.goTo(module.calURL);
        util.waitForPageLoaded();

        settingClick(util);

        //환경설정 > 일정설정 진입
        util.click(By.xpath("//ul[@class='tab_setting tabs']/li[2]"));
        util.waitForIsElementPresent(By.xpath("//div[@class='_schedule tc-panel tc-selected']"));

        util.waitForIsElementPresent(By.xpath("//tr[contains(@class,'_cfg_calendar_list') and ./td[5]/div/a[contains(@class,'_del_del link')]]"));
        maxCalNum = util.getXpathCount(By.xpath("//tbody[@class='_private_calendar_list']/tr"));

        String[] calName = new String[maxCalNum];

        for (int i = 1; i < maxCalNum+1; i++) {
            calEdit = util.waitForIsElementPresent(By.xpath("//tbody[@class='_private_calendar_list']/tr[" + i + "]/td[5]/div")).getText();
            //공유캘린더 마스터일때 양도,탈퇴,폐쇄 노출 됨
            if (calEdit.contains("폐쇄")) {

                calEditName = util.waitForIsElementPresent(By.xpath("//tbody[@class='_private_calendar_list']/tr[" + i + "]/td[1]/a/strong")).getText();

                util.waitForIsElementPresent(By.xpath("//tbody[@class='_private_calendar_list']/tr[" + i + "]/td[5]/div/a[3]"));
                util.click(By.xpath("//tbody[@class='_private_calendar_list']/tr[" + i + "]/td[5]/div/a[3]"));


                assertAlert = util.getAlert().getText();
                assertTrue(assertAlert.contains("공유 캘린더를 폐쇄하면 참여자가 모두 탈퇴 처리되고,\n" +
                        "캘린더에 등록한 모든 일정이 삭제됩니다."));
                util.getAlert().accept();

                assertAlert = util.getAlert().getText();
                assertTrue(assertAlert.contains("캘린더가 폐쇄되었습니다."));
                util.getAlert().accept();

                util.waitForIsElementPresent(By.className("btn_settingcal"));
                util.click(By.className("btn_settingcal"));
                util.waitForIsElementPresent(By.linkText("캘린더로 돌아가기"));

                util.click(By.xpath("//ul[@class='tab_setting tabs']/li[2]"));
                util.waitForIsElementPresent(By.xpath("//div[@class='_schedule tc-panel tc-selected']"));

                //좌측영역의 캘린더 목록과 비교해서 캘린더 1개가 삭제된것을 확인한다.
                for (i = 1; i < maxCalNum - 1; i++) {
                    System.out.println(i);
                    calName[i] = util.waitForIsElementPresent(By.xpath("//ul[@class='category_list']/li[" + i + "]")).getAttribute("calendarid");
                }

                util.printLog("폐쇄한 캘린더 명은 [" + calEditName + "] 입니다.");
                assertFalse(Arrays.toString(calName).contains(calEditName));

                break;

            }
        }

        module.LogOutAndLogIn(util, TestIds.CalUser.getId(),TestIds.CalUser.getPw());
        util.goTo(module.calURL);

        settingClick(util);
        //환경설정 > 일정설정 진입
        util.click(By.xpath("//ul[@class='tab_setting tabs']/li[2]"));
        util.waitForIsElementPresent(By.xpath("//div[@class='_schedule tc-panel tc-selected']"));
    }


    /*
    * Step : 일정설정 > 캘린더 탈퇴 > 탈퇴할 캘린더를 생성
    * Result : 탈퇴할캘린더+오늘날짜 생성 됨
    */

    //@Test
    public void TC_21_일정설정_탈퇴_탈퇴할캘린더생성_Test() throws Exception{

        settingClick(util);
        util.click(By.xpath("//li[@class ='_share']"));

        util.clearAndType(By.id("$$_calendar_name"),"탈퇴할캘린더"+module.contents);

        util.type(By.xpath("//input[@class='input_txt _input']"),TestIds.CalUser2.getId());
        util.click(By.xpath("//span[@class='add_btn']"));
        util.waitForIsElementPresent(By.xpath("//ul[@class='_member_list']/li[2]"));

        subName = util.waitForIsElementPresent(By.id("$$_calendar_name")).getAttribute("value");
        util.printLog(subName);

        util.click(By.xpath("//button[@class ='_save normal']"));
    }


    /*
    * Step : 로그인 > 해당 계정으로 로그인
    * Result : 해당하는 계정으로 로그인 됨
    */

    //@Test
    public void TC_22_일정설정_탈퇴_탈퇴할캘린더생성_초대_Test() throws Exception {
        module.LogOutAndLogIn(util, TestIds.CalUser2.getId(), TestIds.CalUser2.getPw());
        util.waitForTitle(module.calTitle);

        util.goTo(module.mailURL);
        util.waitForPageLoaded();
        util.waitForTitle(module.mailTitle);

        util.click(By.xpath("//ol[@class='mailList  preview_none sender_context ']/li[1]"));
        util.waitForIsElementPresent(By.xpath("//*[@id='readFrame']/xmeta/xmeta/xmeta/xmeta/xmeta/table[1]/tbody/tr[2]/td/table[2]/tbody/tr/td/table[3]/tbody/tr[6]/td/a[1]"));
        util.click(By.xpath("//*[@id=\"readFrame\"]/xmeta/xmeta/xmeta/xmeta/xmeta/table[1]/tbody/tr[2]/td/table[2]/tbody/tr/td/table[3]/tbody/tr[6]/td/a[1]"));

        util.waitForNewWindow();
        util.click(By.xpath("//button[@class='normal _acceptInvitation']"));
        util.waitForIsElementPresent(By.xpath("//button[@class='_acceptInvitation normal4']"));

        util.close();
        util.selectMainWindow();

        module.LogOutAndLogIn(util, TestIds.CalUser.getId(), TestIds.CalUser.getPw());
        util.goTo(module.calURL);

        settingClick(util);
        //환경설정 > 일정설정 진입
        util.click(By.xpath("//ul[@class='tab_setting tabs']/li[2]"));
        util.waitForIsElementPresent(By.xpath("//div[@class='_schedule tc-panel tc-selected']"));
    }


    /*
    * Step : 일정설정 > 캘린더 탈퇴 클릭
    * Result : 해당 캘린더 목록에서 노출되지 않음
    */

    @Test
    public void TC_23_일정설정_탈퇴_Test() throws Exception {

        util.waitForIsElementPresent(By.xpath("//tr[contains(@class,'_cfg_calendar_list') and ./td[5]/div/a[contains(@class,'_del_del link')]]"));
        maxCalNum = util.getXpathCount(By.xpath("//tbody[@class='_private_calendar_list']/tr"));

        String[] calName = new String[maxCalNum];

        for (int i = 2; i <= maxCalNum; i++) {
            calEdit = util.waitForIsElementPresent(By.xpath("//tbody[@class='_private_calendar_list']/tr[" + i + "]/td[5]/div")).getText();
            //공유캘린더 참석자, 마스터일때 탈퇴 노출 됨
                //공유캘린더 마스터 일때 양도,탈퇴,폐쇄 노출 됨
                if (calEdit.contains("탈퇴") && !calEdit.contains("양도")) {
                    util.waitForIsElementPresent(By.xpath("//tbody[@class='_private_calendar_list']/tr[" + i + "]/td[5]/div/a[1]"));
                    util.click(By.xpath("//tbody[contains(@class,'_private_calendar_list')]/tr["+i+"]/td[5]/div/a[contains(text(),'탈퇴')]"));
                    //util.click(By.xpath("//tbody[@class='_private_calendar_list']/tr[" + i + "]/td[5]/div/a[1]"));
                    //공유캘린더 탈퇴시에 참가자가 있을경우 예외처리
                    //calEditMemberList = util.waitForIsElementPresent(By.xpath("//tbody[@class='_member_list_body']")).getText();

                    assertAlert = util.getAlert().getText();
                    assertTrue(assertAlert.contains("공유 캘린더를 탈퇴하면 해당 공유 캘린더에 등록한 일정이 모두 삭제됩니다."));
                    util.getAlert().accept();

                    //assertAlert = util.getAlert().getText();
                    //assertTrue(assertAlert.contains("존재하지 않는 캘린더입니다."));
                    //util.getAlert().accept();

                    //마스터가 탈퇴할 시에 마스터 권한 양도하고 탈퇴한다.
                    /*if (calEditMemberList.contains("수정/삭제")) {
                        util.waitForIsElementPresent(By.xpath("//tbody[@class='_member_list_body']/tr/td/input"));
                        util.click(By.xpath("//tbody[@class='_member_list_body']/tr[./td[3][contains(text(),'수정/삭제')]]/td/input[@class='radio01']"));

                        calEditMemberValue = util.waitForIsElementPresent(By.xpath("//tbody[@class='_member_list_body']/tr[./td[3][contains(text(),'수정/삭제')]]/td/input[@class='radio01']")).getAttribute("value");
                        calEditName = util.waitForIsElementPresent(By.xpath("//tbody[@class='_private_calendar_list']/tr[" + i + "]/td[1]/a/strong")).getText();

                        util.click(By.xpath("//div[@class='layer_footer']/button[1]"));
                        util.waitForPageLoaded();
                        util.sleep(3);

                        util.waitForIsElementPresent(By.className("btn_settingcal"));
                        util.click(By.xpath("//a[@class='btn_settingcal']"));
                        util.waitForIsElementPresent(By.linkText("캘린더로 돌아가기"));

                        util.click(By.xpath("//ul[@class='tab_setting tabs']/li[2]"));
                        util.waitForIsElementPresent(By.xpath("//div[@class='_schedule tc-panel tc-selected']"));

                        //새로고침 해서 탈퇴 노출 되는 시점 확인 필요
                        calEditResult = util.waitForIsElementPresent(By.xpath("//tbody[@class='_private_calendar_list']/tr["+i+"]/td[5]/div/a")).getText();
                        assertTrue(calEditResult.contains("탈퇴"));

                        //좌측영역의 캘린더 목록과 비교해서 캘린더 1개가 삭제된것을 확인한다.
                        for(i=1; i < maxCalNum-1; i++) {
                            System.out.println(i);
                            calName[i] = util.waitForIsElementPresent(By.xpath("//ul[@class='category_list']/li[" + i + "]")).getAttribute("calendarid");
                        }
                        util.printLog("탈퇴한 캘린더 명은 ["+calEditName+"]이며, 양도받은 참석자 명은 ["+calEditMemberValue+"] 입니다.");
                        assertFalse(Arrays.toString(calName).contains(calEditName));

                        break;

                    } else if (calEditMemberList.contains("조회")) {
                        util.click(By.xpath("//tbody[@class='_member_list_body']/tr[./td[3][contains(text(),'조회')]]/td/input[@class='radio01']"));
                        calEditMemberValue = util.waitForIsElementPresent(By.xpath("//tbody[@class='_member_list_body']/tr[./td[3][contains(text(),'수정/삭제')]]/td/input[@class='radio01']")).getAttribute("value");
                        util.printLog(calEditMemberValue);
                        util.click(By.xpath("//div[@class='layer_footer']/button[2]"));
                    } else if (calEditMemberList.contains("모든 권한")) {
                        util.click(By.xpath("//tbody[@class='_member_list_body']/tr[./td[3][contains(text(),'모든 권한')]]/td/input[@class='radio01']"));
                        calEditMemberValue = util.waitForIsElementPresent(By.xpath("//tbody[@class='_member_list_body']/tr[./td[3][contains(text(),'수정/삭제')]]/td/input[@class='radio01']")).getAttribute("value");
                        util.printLog(calEditMemberValue);
                        util.click(By.xpath("//div[@class='layer_footer']/button[2]"));
                    } else {
                        assertAlert = util.getAlert().getText();
                        assertTrue(assertAlert.contains("공유 캘린더를 폐쇄하면 참여자가 모두 탈퇴 처리되고,\n" +
                                "캘린더에 등록한 모든 일정이 삭제됩니다."));
                        util.getAlert().accept();

                        assertAlert = util.getAlert().getText();
                        assertTrue(assertAlert.contains("캘린더가 폐쇄되었습니다."));
                        util.getAlert().accept();

                    }*/

                }

            }
    }



    /*
    * Step : 일정설정 > 캘린더 탈퇴 > 탈퇴할 캘린더를 생성
    * Result : 탈퇴할캘린더+오늘날짜 생성 됨
    */

    @Test
    public void TC_24_일정설정_탈퇴_탈퇴한캘린더삭제_Test() throws Exception {

        module.LogOutAndLogIn(util, TestIds.CalUser2.getId(),TestIds.CalUser2.getPw());
        util.goTo(module.calURL);

        settingClick(util);

        //환경설정 > 일정설정 진입
        util.click(By.xpath("//ul[@class='tab_setting tabs']/li[2]"));
        util.waitForIsElementPresent(By.xpath("//div[@class='_schedule tc-panel tc-selected']"));

        util.waitForIsElementPresent(By.xpath("//tr[contains(@class,'_cfg_calendar_list') and ./td[5]/div/a[contains(@class,'_del_del link')]]"));
        maxCalNum = util.getXpathCount(By.xpath("//tbody[@class='_private_calendar_list']/tr"));

        String[] calName = new String[maxCalNum];

        for (int i = 1; i < maxCalNum+1; i++) {
            calEdit = util.waitForIsElementPresent(By.xpath("//tbody[@class='_private_calendar_list']/tr[" + i + "]/td[5]/div")).getText();
            //공유캘린더 마스터일때 양도,탈퇴,폐쇄 노출 됨
            if (calEdit.contains("폐쇄")) {

                calEditName = util.waitForIsElementPresent(By.xpath("//tbody[@class='_private_calendar_list']/tr[" + i + "]/td[1]/a/strong")).getText();

                util.waitForIsElementPresent(By.xpath("//tbody[@class='_private_calendar_list']/tr[" + i + "]/td[5]/div/a[3]"));
                util.click(By.xpath("//tbody[@class='_private_calendar_list']/tr[" + i + "]/td[5]/div/a[3]"));


                assertAlert = util.getAlert().getText();
                assertTrue(assertAlert.contains("공유 캘린더를 폐쇄하면 참여자가 모두 탈퇴 처리되고,\n" +
                        "캘린더에 등록한 모든 일정이 삭제됩니다."));
                util.getAlert().accept();

                assertAlert = util.getAlert().getText();
                assertTrue(assertAlert.contains("캘린더가 폐쇄되었습니다."));
                util.getAlert().accept();

                settingClick(util);

                util.click(By.xpath("//ul[@class='tab_setting tabs']/li[2]"));
                util.waitForIsElementPresent(By.xpath("//div[@class='_schedule tc-panel tc-selected']"));

                //좌측영역의 캘린더 목록과 비교해서 캘린더 1개가 삭제된것을 확인한다.
                for (i = 1; i < maxCalNum - 1; i++) {
                    System.out.println(i);
                    calName[i] = util.waitForIsElementPresent(By.xpath("//ul[@class='category_list']/li[" + i + "]")).getAttribute("calendarid");
                }

                util.printLog("폐쇄한 캘린더 명은 [" + calEditName + "] 입니다.");
                assertFalse(Arrays.toString(calName).contains(calEditName));

                break;

            }
        }

        module.LogOutAndLogIn(util, TestIds.CalUser.getId(),TestIds.CalUser.getPw());
        util.goTo(module.calURL);

        settingClick(util);

        //환경설정 > 일정설정 진입
        util.click(By.xpath("//ul[@class='tab_setting tabs']/li[2]"));
        util.waitForIsElementPresent(By.xpath("//div[@class='_schedule tc-panel tc-selected']"));
    }


    /*
    * Step : 일정설정 > 캘린더 폐쇄 > 폐쇄할 캘린더를 생성
    * Result : 폐쇄할캘린더+오늘날짜 생성 됨
    */

    @Test
    public void TC_25_일정설정_폐쇄_캘린더생성_Test() throws Exception{


        makeCalendar(util);
        util.click(By.xpath("//li[@class ='_share']"));

        util.clearAndType(By.id("$$_calendar_name"),"폐쇄할캘린더"+module.contents);

        util.type(By.xpath("//input[@class='input_txt _input']"),TestIds.CalUser2.getId());
        util.click(By.xpath("//span[@class='add_btn']"));
        util.waitForIsElementPresent(By.xpath("//ul[@class='_member_list']/li[2]"));

        util.type(By.xpath("//input[@class='input_txt _input']"),TestIds.CalUser3.getId());
        util.click(By.xpath("//span[@class='add_btn']"));
        util.waitForIsElementPresent(By.xpath("//ul[@class='_member_list']/li[3]"));

        subName = util.waitForIsElementPresent(By.id("$$_calendar_name")).getAttribute("value");
        util.printLog(subName);

        util.click(By.xpath("//button[@class ='_save normal']"));
    }


    /*
    * Step : 로그인 > 해당 계정으로 로그인 > 메일 이동 > 공유캘린더 초대 수락
    * Result : 공유 캘린더에 CalUser2, 3 가 추가 됨
    */

    @Test
    public void TC_26_일정설정_폐쇄_캘린더생성_초대1_Test() throws Exception {
        module.LogOutAndLogIn(util, TestIds.CalUser2.getId(), TestIds.CalUser2.getPw());
        util.waitForPageLoaded();

        //캘린더 초대 수락 하기 위해서 메일로 이동
        util.waitForPageLoaded();
        util.click(By.className("mail"));
        module.assertCalendarPage(util,module.mailTitle,module.mailURL);

        util.click(By.xpath("//ol[@class='mailList  preview_none sender_context ']/li[1]"));
        util.waitForIsElementPresent(By.xpath("//*[@id='readFrame']/xmeta/xmeta/xmeta/xmeta/xmeta/table[1]/tbody/tr[2]/td/table[2]/tbody/tr/td/table[3]/tbody/tr[6]/td/a[1]"));
        util.click(By.xpath("//*[@id=\"readFrame\"]/xmeta/xmeta/xmeta/xmeta/xmeta/table[1]/tbody/tr[2]/td/table[2]/tbody/tr/td/table[3]/tbody/tr[6]/td/a[1]"));

        util.waitForNewWindow();
        util.click(By.xpath("//button[@class='normal btn_emphasis _acceptInvitation']"));
        util.waitForIsElementPresent(By.xpath("//button[@class='_acceptInvitation normal4 btn_emphasis']"));

        util.close();
        util.selectMainWindow();
    }


    /*
    * Step : 로그인 > 해당 계정으로 로그인
    * Result : 해당하는 계정으로 로그인 됨
    */

    @Test
    public void TC_27_일정설정_폐쇄_캘린더생성_초대2_Test() throws Exception {
        module.LogOutAndLogIn(util, TestIds.CalUser3.getId(), TestIds.CalUser3.getPw());
        util.waitForPageLoaded();

        //캘린더 초대 수락 하기 위해서 메일로 이동
        util.waitForPageLoaded();
        util.click(By.className("mail"));
        module.assertCalendarPage(util,module.mailTitle,module.mailURL);

        util.click(By.xpath("//ol[@class='mailList  preview_none sender_context ']/li[1]"));
        util.waitForIsElementPresent(By.xpath("//*[@id='readFrame']/xmeta/xmeta/xmeta/xmeta/xmeta/table[1]/tbody/tr[2]/td/table[2]/tbody/tr/td/table[3]/tbody/tr[6]/td/a[1]"));
        util.click(By.xpath("//*[@id=\"readFrame\"]/xmeta/xmeta/xmeta/xmeta/xmeta/table[1]/tbody/tr[2]/td/table[2]/tbody/tr/td/table[3]/tbody/tr[6]/td/a[1]"));

        util.waitForNewWindow();
        util.click(By.xpath("//button[@class='normal btn_emphasis _acceptInvitation']"));
        util.waitForIsElementPresent(By.xpath("//button[@class='_acceptInvitation normal4 btn_emphasis']"));

        util.close();
        util.selectMainWindow();

        module.LogOutAndLogIn(util, TestIds.CalUser.getId(),TestIds.CalUser.getPw());
        util.goTo(module.calURL);

        settingClick(util);
        //환경설정 > 일정설정 진입
        util.click(By.xpath("//ul[@class='tab_setting tabs']/li[2]"));
        util.waitForIsElementPresent(By.xpath("//div[@class='_schedule tc-panel tc-selected']"));

    }



    /*
    * Step : 일정설정 > 캘린더 탈퇴 클릭
    * Result : 해당 캘린더 목록에서 노출되지 않음
    */

    @Test
    public void TC_28_일정설정_폐쇄_Test() throws Exception {

        util.waitForIsElementPresent(By.xpath("//tr[contains(@class,'_cfg_calendar_list') and ./td[5]/div/a[contains(@class,'_del_del link')]]"));
        maxCalNum = util.getXpathCount(By.xpath("//tbody[@class='_private_calendar_list']/tr"));

        String[] calName = new String[maxCalNum];

        for (int i = 1; i < maxCalNum+1; i++) {
            calEdit = util.waitForIsElementPresent(By.xpath("//tbody[@class='_private_calendar_list']/tr[" + i + "]/td[5]/div")).getText();
            //공유캘린더 마스터일때 양도,탈퇴,폐쇄 노출 됨
            if (calEdit.contains("폐쇄")) {

                calEditName = util.waitForIsElementPresent(By.xpath("//tbody[@class='_private_calendar_list']/tr[" + i + "]/td[1]/a/strong")).getText();

                util.waitForIsElementPresent(By.xpath("//tbody[@class='_private_calendar_list']/tr[" + i + "]/td[5]/div/a[3]"));
                util.click(By.xpath("//tbody[@class='_private_calendar_list']/tr[" + i + "]/td[5]/div/a[3]"));


                assertAlert = util.getAlert().getText();
                assertTrue(assertAlert.contains("공유 캘린더를 폐쇄하면 참여자가 모두 탈퇴 처리되고,\n" +
                        "캘린더에 등록한 모든 일정이 삭제됩니다."));
                util.getAlert().accept();

                assertAlert = util.getAlert().getText();
                assertTrue(assertAlert.contains("캘린더가 폐쇄되었습니다."));
                util.getAlert().accept();

                settingClick(util);

                util.click(By.xpath("//ul[@class='tab_setting tabs']/li[2]"));
                util.waitForIsElementPresent(By.xpath("//div[@class='_schedule tc-panel tc-selected']"));

                //좌측영역의 캘린더 목록과 비교해서 캘린더 1개가 삭제된것을 확인한다.
                for (i = 1; i < maxCalNum - 1; i++) {
                    System.out.println(i);
                    calName[i] = util.waitForIsElementPresent(By.xpath("//ul[@class='category_list']/li[" + i + "]")).getAttribute("calendarid");
                }

                util.printLog("폐쇄한 캘린더 명은 [" + calEditName + "] 입니다.");
                assertFalse(Arrays.toString(calName).contains(calEditName));

                break;

            }
        }
    }


    /*
    * Step : 일정설정 > 캘린더 탈퇴 클릭
    * Result : 해당 캘린더 목록에서 노출되지 않음
    */

    @Test
    public void TC_29_일정설정_구독해지_캘린더구독_Test() throws Exception {

        //util.goTo("http://me2.do/xsxu9Adl");
        util.goTo("http://me2.do/xSEexqNu");
        util.waitForTitle("일정 : 네이버 캘린더");
        util.waitForIsElementPresent(By.className("monthly_calendar"));
        util.waitForPageToLoad();

        util.waitForIsElementPresent(By.xpath("//button[contains(@class,'btn btn_sub')]"));
        util.waitForPageLoaded();
        util.click(By.xpath("//button[contains(@class,'btn btn_sub')]"));
        util.click(By.xpath("//button[contains(@class,'btn btn_sub')]"));

        util.waitForNewWindow();
        util.waitForPageToLoad();

        util.waitForIsElementPresent(By.xpath("//button[@class='normal _acceptInvitation']"));
        util.click(By.xpath("//button[@class='normal _acceptInvitation']"));

        Title = util.getTitle();
        assertTrue(Title.contains("공개캘린더 : 네이버 캘린더"));

        util.waitForIsElementPresent(By.xpath("//div[@class='event_banner2 _event_banner']"));
        util.close();

        util.selectMainWindow();
        util.goTo(module.calURL);

        util.waitForTitle(module.calTitle);

        util.click(By.className("_config"));
        util.waitForIsElementPresent(By.linkText("캘린더로 돌아가기"));
        //환경설정 > 일정설정 진입
        util.click(By.xpath("//ul[@class='tab_setting tabs']/li[2]"));
        util.waitForIsElementPresent(By.xpath("//div[@class='_schedule tc-panel tc-selected']"));

    }


    /*
    * Step : 일정설정 > 캘린더 탈퇴 클릭
    * Result : 해당 캘린더 목록에서 노출되지 않음
    */

    @Test
    public void TC_30일정설정_구독해지_Test() throws Exception {

        util.waitForIsElementPresent(By.xpath("//tr[contains(@class,'_cfg_calendar_list') and ./td[5]/div/a[contains(@class,'_del_del link')]]"));
        maxCalNum = util.getXpathCount(By.xpath("//tbody[@class='_private_calendar_list']/tr"));
        String calEdit;
        String calEditName;

        String[] calName = new String[maxCalNum];

        for (int i = 1; i < maxCalNum+1; i++) {
            calEdit = util.waitForIsElementPresent(By.xpath("//tbody[@class='_private_calendar_list']/tr[" + i + "]/td[5]/div")).getText();
            //공유캘린더 마스터일때 양도,탈퇴,폐쇄 노출 됨
            if (calEdit.contains("구독해지")) {

                calEditName = util.waitForIsElementPresent(By.xpath("//tbody[@class='_private_calendar_list']/tr[" + i + "]/td[1]/a/strong")).getText();

                util.waitForIsElementPresent(By.xpath("//tbody[@class='_private_calendar_list']/tr[" + i + "]/td[5]/div/a[1]"));
                util.click(By.xpath("//tbody[@class='_private_calendar_list']/tr[" + i + "]/td[5]/div/a[1]"));

                util.sleep(1);
                assertAlert = util.getAlert().getText();
                assertTrue(assertAlert.contains("해지하시겠습니까?"));
                util.getAlert().accept();

                settingClick(util);

                util.click(By.xpath("//ul[@class='tab_setting tabs']/li[2]"));
                util.waitForIsElementPresent(By.xpath("//div[@class='_schedule tc-panel tc-selected']"));

                //좌측영역의 캘린더 목록과 비교해서 캘린더 1개가 삭제된것을 확인한다.
                for (i = 1; i < maxCalNum - 1; i++) {
                    System.out.println(i);
                    calName[i] = util.waitForIsElementPresent(By.xpath("//ul[@class='category_list']/li[" + i + "]")).getAttribute("calendarid");
                }

                util.printLog("구독해지한 캘린더 명은 [" + calEditName + "] 입니다.");
                assertFalse(Arrays.toString(calName).contains(calEditName));

                break;

            }
        }
    }
}
