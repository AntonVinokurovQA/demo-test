package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import tools.PropertyReader;
import tools.Tools;

import java.util.Calendar;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainPage {
    private SelenideElement searchGrid = $(By.xpath("//h1/following::div[1]"));
    private SelenideElement btnSearch = $(By.xpath("//button/span[text() = 'Search']"));
    private SelenideElement dateFrom = $(By.xpath("//div[text() = 'Check-in']"));
    private SelenideElement dateTo = $(By.xpath("//div[text() = 'Check-out']"));
    private SelenideElement clearDates = $(By.xpath("//span[text() = 'Clear dates']"));
    private SelenideElement selectDatesNotification = $(By.xpath("//div[text() = 'Select check-in and check-out dates']"));
    private SelenideElement datesInExpandedCalendar = $(By.xpath("//div[@class='_Popover _Popover--show']//div[contains(text(),'Check-in')]"));
    private SelenideElement checkInCalendarField = $(By.xpath("//div[text() = 'Check-in']/following::div[1]"));
    private SelenideElement checkOutExpandedCalendar = $(By.xpath("//div[@class='_Popover _Popover--show']//div[contains(text(),'Check-out')]"));
    private SelenideElement checkOutCalendarField = $(By.xpath("//div[text() = 'Check-out']/following::div[1]"));
    private String month = "//div[@class='_Popover _Popover--show']/div/div[3]/div/div[1]";
    private String activeDay = "//div[contains(@class,'CalendarDay')][not(@disabled)]";

    @Step
    public MainPage openMainPage(){
        open(PropertyReader.getBaseUrl());
        return this;
    }

    @Step
    public MainPage isMainPageOpened(){
        searchGrid.shouldBe(Condition.visible);
        return this;
    }

    @Step
    public void checkThatAmenitiesCheckBoxIsClickable(int index){
        List<SelenideElement> checkBoxes = $$(By.xpath("//input[@id='id']"));
        List<SelenideElement> checkBoxesField = $$(By.xpath("//input[@id='id']/.."));
        checkBoxesField.get(index).click();
        checkBoxes.get(index).shouldBe(Condition.checked);
    }

    @Step
    public SearchResultPage searchClick(){
        btnSearch.click();
        return new SearchResultPage();
    }

    @Step
    public MainPage clickDateFrom(){
        dateFrom.shouldBe(Condition.visible);
        dateFrom.click();
        return new MainPage();
    }

    @Step
    public MainPage clickDateTo(){
        dateTo.click();
        return new MainPage();
    }

    @Step
    public void areMonthsValid(){
        Calendar cal = Calendar.getInstance();
        List <SelenideElement> monthFields = $$(By.xpath(month));
        assertTrue(monthFields.size() == 2);

        monthFields.get(0).shouldHave(Condition.ownText(getMonthName(cal.get(Calendar.MONTH))));
        monthFields.get(1).shouldHave(Condition.ownText(getMonthName(cal.get(Calendar.MONTH)+1)));
    }

    public String getMonthName(int index){
        String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        return monthNames[index];
    }

    /* Select date number days from current day*/
    @Step
    public MainPage setDate(int number) {
        clickDateFrom();
        $$(By.xpath(activeDay)).get(0).click();
        $$(By.xpath(activeDay)).get(number-1).click();
        return new MainPage();
    }

    @Step
    public void isCheckInDateShownInExpandedCalendar(){
        datesInExpandedCalendar.shouldBe(Condition.visible);
        datesInExpandedCalendar.shouldHave(Condition.ownText(Tools.getCurrentDate(0,"MMM d")));
    }

    @Step
    public void isCheckInDateShownInSearchField(){
        checkInCalendarField.shouldBe(Condition.visible);
        checkInCalendarField.shouldHave(Condition.ownText(Tools.getCurrentDate(0, "d MMMM YYYY")));
    }

    @Step
    public void isCheckOutDateShownInExpandedCalendar(int days){
        datesInExpandedCalendar.shouldBe(Condition.visible);
        datesInExpandedCalendar.shouldHave(Condition.ownText(Tools.getCurrentDate(days,"MMM d")));
    }

    @Step
    public void isCheckOutDateShownInSearchField(int days){
        checkOutCalendarField.shouldBe(Condition.visible);
        checkOutCalendarField.shouldHave(Condition.ownText(Tools.getCurrentDate(days, "d MMMM YYYY")));
    }

    /* Check highlighted dates from current day for [number] days */
    @Step
    public void isHighlightedPeriod(int number){
        clickDateFrom();
        if(number == 1) {
            $$(By.xpath(activeDay)).get(0).getCssValue("background-color").equals("rgba(51, 122, 183, 1)");
            $$(By.xpath(activeDay)).get(number).getCssValue("background-color").equals("rgba(51, 122, 183, 1)");
        } else if(number>1){
            $$(By.xpath(activeDay)).get(0).getCssValue("background-color").equals("rgba(51, 122, 183, 1)");
            $$(By.xpath(activeDay)).get(number-1).getCssValue("background-color").equals("rgba(51, 122, 183, 0.1)");
            $$(By.xpath(activeDay)).get(number).getCssValue("background-color").equals("rgba(51, 122, 183, 1)");
        }
    }

    @Step
    public void isPreviousDayDisabled(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -1);
        $(By.xpath("//div[@class = '_Popover _Popover--show']/div/div[3]/div[1]//div[contains(@class, 'CalendarDay') and text() = '"+ cal.get(Calendar.DAY_OF_MONTH) +"'][@disabled]")).shouldBe(Condition.visible);
    }

    @Step
    public MainPage clearDates(){
        clearDates.shouldBe(Condition.visible);
        clearDates.click();

        return this;
    }

    @Step
    public MainPage isSelectDateTextVisible(){
        selectDatesNotification.shouldBe(Condition.visible);

        return this;
    }
}
