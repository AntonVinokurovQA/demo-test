import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.MainPage;


public class MainPageTest extends BaseTest {
    private MainPage mainPage = new MainPage();
    private final int DATE_RANGE_ONE_DAY = 1;
    private final int DATE_RANGE_TWO_DAYS = 2;
    private final int DATE_RANGE_THREE_DAYS = 3;

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
                .setDate(DATE_RANGE_THREE_DAYS)
                .clickDateTo()
                .areMonthsValid();
    }

    @Test
    @DisplayName("Check that selected period is highlighted in calendar")
    public void checkSelectedPeriodOneDayHighlighted() {
        mainPage.openMainPage()
                .setDate(DATE_RANGE_ONE_DAY)
                .isHighlightedPeriod(DATE_RANGE_ONE_DAY);
    }

    @Test
    @DisplayName("Check that selected period is highlighted in calendar")
    public void checkSelectedPeriodTwoDaysHighlighted() {
        mainPage.openMainPage()
                .setDate(DATE_RANGE_TWO_DAYS)
                .isHighlightedPeriod(DATE_RANGE_TWO_DAYS);
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
                .setDate(DATE_RANGE_THREE_DAYS)
                .clickDateFrom()
                .clearDates()
                .isSelectDateTextVisible();
    }

    @Test
    @DisplayName("Check that check-in date is shown in expanded calendar")
    public void checkCheckInDateShownInExpandedCalendar() {
        mainPage.openMainPage()
                .setDate(DATE_RANGE_THREE_DAYS)
                .clickDateFrom()
                .isCheckInDateShownInExpandedCalendar();

    }

    @Test
    @DisplayName("Check that check-in date is shown in search field")
    public void checkCheckInDateShownInSearchField() {
        mainPage.openMainPage()
                .setDate(DATE_RANGE_THREE_DAYS)
                .isCheckInDateShownInSearchField();
    }

    @Test
    @DisplayName("Check that check-out date is shown in expanded calendar")
    public void checkCheckOutDateShownInExpandedCalendar() {
        mainPage.openMainPage()
                .setDate(DATE_RANGE_THREE_DAYS)
                .clickDateFrom()
                .isCheckOutDateShownInExpandedCalendar(DATE_RANGE_THREE_DAYS);

    }

    @Test
    @DisplayName("Check that check-out date is shown in search field")
    public void checkCheckOutDateShownInSearchField() {
        mainPage.openMainPage()
                .setDate(DATE_RANGE_THREE_DAYS)
                .isCheckOutDateShownInSearchField(DATE_RANGE_THREE_DAYS);
    }

    @Test
    @DisplayName("Check that search by date had results")
    public void checkSearchByDate() {
        mainPage.openMainPage()
                .setDate(DATE_RANGE_THREE_DAYS)
                .searchClick()
                .isSearchSuccessfulMessageShown();
    }
}
