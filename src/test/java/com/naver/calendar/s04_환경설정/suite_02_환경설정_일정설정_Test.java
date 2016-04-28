package com.naver.calendar.s04_환경설정;

import main.TestIds;
import main.Testcase;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;


public class suite_02_환경설정_일정설정_Test extends Testcase {

    public String Title = null;
    public String URL = null;

    /*
   * Step : 로그인 > 해당 계정으로 로그인
   * Result : 해당하는 계정으로 로그인 됨
    */
    @Test
    public void TC_00_Footer_로그인_Test() throws Exception {
        module.로그인(util, TestIds.CalUser.getId(), TestIds.CalUser.getPw());
    }

    /*
     * Step : 일정설정 > 한주의 시작 확인
     * Result : 한주의 시작의 현재 값을 확인
     */
    @Test
    public void TC_01_일정설정_한주의시작_Test() throws Exception {

        util.click(By.className("_config"));
        util.waitForIsElementPresent(By.linkText("캘린더로 돌아가기")).isDisplayed();

        util.click(By.xpath("//ul[@class='tab_setting tabs']/li[2]"));
        util.waitForIsElementPresent(By.xpath("//div[@class='_schedule tc-panel tc-selected']"));

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
    //@Test
    public void TC_02_일정설정_시간표시_Test() throws Exception{

        util.waitForIsElementPresent(By.xpath("//div[@class='_schedule tc-panel tc-selected']"));

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

    //@Test
    public void TC_03_일정설정_일정표시시간_Test() throws Exception {

        int valueNum = 0;
        String startTime;

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

    //@Test
    public void TC_04_일정설정_기본시간대_Test() throws Exception {

        int valueNum = 0;
        String basicTimeCountry;
        String basicTimeCity;

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

    //@Test
    public void TC_05_일정설정_추가시간대_Test() throws Exception {

        int valueNum = 0;
        String addTimeCountry;
        String addTimeCity;

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
    //@Test
    public void TC_06_일정설정_초대받은일정자동등록_Test() throws Exception{

        util.waitForIsElementPresent(By.xpath("//div[@class='_schedule tc-panel tc-selected']"));

        if(util.waitForIsElementPresent(By.xpath("//input[contains(@class,'inp01 _cfg_auto_create_only_aware_schedule')][contains(@value,'false')]")).isSelected()) {
            util.printLog("현재 초대받은 일정 자동등록 설정은 [모든 초대 일정을 내 캘린더에 등록]으로 설정되어 있습니다.");
            assertTrue(util.isElementPresent(By.xpath("//input[contains(@class,'inp01 _cfg_auto_create_only_aware_schedule')][contains(@value,'false')]")).isSelected());
        }
        else {
            util.printLog("현재 초대받은 일정 자동등록 설정은 [내 주소록에 등록된 사람이 보낸 초대 일정만 내 캘린더에 등록]으로 설정 되어 있습니다.");
            assertTrue(util.isElementPresent(By.xpath("//input[contains(@class,'inp01 _cfg_auto_create_only_aware_schedule')][contains(@value,'true')]")).isSelected());
        }
    }

    /*
     * Step : 일정설정 > 캘린더 초기화 방법 안내
     * Result : 캘린더 초기화 방법 안내 페이지로 이동
     * URL : http://calendar.naver.com/notice.nhn?type=read&docId=10000000000003775681&boardId=1000003514
     */
    //@Test
    public void TC_07_일정설정_캘린더초기화방법안내_Test() throws Exception{

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
    //@Test
    public void TC_08_일정설정_캘린더목록_Test() throws Exception{

        int calList;
        int calNum;

        calList = util.getXpathCount(By.xpath("//tbody[@class='_private_calendar_list']/tr"));
        util.printLog("현재 캘린더 개수는 "+calList+"개 입니다.");

        //좌측영역의 캘린더 목록에서 할일을 뺀 숫자와 환경설정의 캘린더 목록이 같은지 확인
        calNum = util.getXpathCount(By.xpath("//ul[@class='category_list']/li"));
        assertTrue(calList==calNum-1);
    }

    /*
     * Step : 일정설정 > 기본캘린더 확인
     * Result : 현재 기본캘린더 확인, 변경 확인
     */
    @Test
    public void TC_09_일정설정_기본캘린더_Test() throws Exception{

        String basicValue;
        String basicCalName;
        int randomNum;

        randomNum = util.getRandomNum(1,util.getXpathCount(By.xpath("//tbody[@class='_private_calendar_list']/tr")));

        basicValue = util.waitForIsElementPresent(By.xpath("//li[contains(@class,'_calendar') and ./a[2]/span[contains(.,'[기본]')]]")).getAttribute("calendarid");
        basicCalName = util.waitForIsElementPresent(By.xpath("//a[contains(@data-value,'"+basicValue+"')]/strong")).getText();

        //현재 기본 캘린더 명을 가져옴
        //util.printLog(basicValue);
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

        //환경설정 재 진입
        util.waitForIsElementPresent(By.className("_config"));
        util.click(By.className("_config"));
        util.waitForIsElementPresent(By.linkText("캘린더로 돌아가기")).isDisplayed();

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
 * Result : 현재 캘린더 개수 노출
 */
    @Test
    public void TC_10_일정설정_목록에표시_Test() throws Exception{

        int calList;
        int calNum;

        calList = util.getXpathCount(By.xpath("//tbody[@class='_private_calendar_list']/tr"));
        util.printLog("현재 캘린더 개수는 "+calList+"개 입니다.");

        //좌측영역의 캘린더 목록에서 할일을 뺀 숫자와 환경설정의 캘린더 목록이 같은지 확인
        calNum = util.getXpathCount(By.xpath("//ul[@class='category_list']/li"));
        assertTrue(calList==calNum-1);
    }





}
