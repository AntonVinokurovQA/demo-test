

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.MainPage;


public class MainPageTest extends BaseTest {
    MainPage mainPage = new MainPage();

    @Test
    public void checkMainPageIsOpened() {
        mainPage.openMainPage()
                .isMainPageOpened();
    }

    @ParameterizedTest(name = "Number of checkbox = {0}")
    @ValueSource(ints = {0, 1, 2})
        // number of checkbox
    void checkThatAmenitiesCheckBoxIsClickable(int number) {
        mainPage.openMainPage()
                .checkThatAmenitiesCheckBoxIsClickable(number);
    }

    @Test
    @DisplayName("Check that Search Result page opened")
    public void checkSearchResultsPageIsOpened() {
        mainPage.openMainPage()
                .searchClick()
                .isSearchResultPageOpened();
    }

    @Test
    @DisplayName("Check that Clear All button works")
    public void checkClearAllFilter() {
        mainPage.openMainPage()
                .searchClick()
                .clickFilterButton()
                .roomsPlus()
                .selectAllAmenities()
                .clearAll()
                .isAllFiltersOff();
    }

    @Test
    @DisplayName("Check that current and next month is shown in the filter From")
    public void checkCurrentAndNextMonthShownInFilterFrom() {
        mainPage.openMainPage()
                .clickDateFrom()
                .areMonthsValid();
    }

    @Test
    @DisplayName("Check that current and next month is shown in the filter To")
    public void checkCurrentAndNextMonthShownInFilterTo() {
        mainPage.openMainPage()
                .setDate(3)
                .clickDateTo()
                .areMonthsValid();
    }

    @Test
    @DisplayName("Check that selected period is highlighted in calendar")
    public void checkSelectedPeriodOneDayHighlighted() {
        mainPage.openMainPage()
                .setDate(1)
                .isHighlightedPeriod(1);
    }

    @Test
    @DisplayName("Check that selected period is highlighted in calendar")
    public void checkSelectedPeriodTwoDaysHighlighted() {
        mainPage.openMainPage()
                .setDate(2)
                .isHighlightedPeriod(2);
    }

    @Test
    @DisplayName("Check that previous day is disabled in calendar")
    public void checkPreviousDayIsDisabled() {
        mainPage.openMainPage()
                .clickDateTo()
                .isPreviousDayDisabled();
    }

    @Test
    @DisplayName("Check that Clear Dates button works")
    public void checkClearDatesButton() {
        mainPage.openMainPage()
                .setDate(3)
                .clickDateFrom()
                .clearDates()
                .isSelectDateTextVisible();
    }

    @Test
    @DisplayName("Check that check-in date is shown in expanded calendar")
    public void checkCheckInDateShownInExpandedCalendar() {
        mainPage.openMainPage()
                .setDate(3)
                .clickDateFrom()
                .isCheckInDateShownInExpandedCalendar();

    }

    @Test
    @DisplayName("Check that check-in date is shown in search field")
    public void checkCheckInDateShownInSearchField() {
        mainPage.openMainPage()
                .setDate(3)
                .isCheckInDateShownInSearchField();
    }

    @Test
    @DisplayName("Check that check-out date is shown in expanded calendar")
    public void checkCheckOutDateShownInExpandedCalendar() {
        mainPage.openMainPage()
                .setDate(3)
                .clickDateFrom()
                .isCheckOutDateShownInExpandedCalendar(3);

    }

    @Test
    @DisplayName("Check that check-out date is shown in search field")
    public void checkCheckOutDateShownInSearchField() {
        mainPage.openMainPage()
                .setDate(3)
                .isCheckOutDateShownInSearchField(3);
    }

    @Test
    @DisplayName("Check that search by date had results")
    public void checkSearchByDate() {
        mainPage.openMainPage()
                .setDate(3)
                .searchClick()
                .isSearchSuccessfulMessageShown();
    }
}
