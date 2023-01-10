package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchResultPage {
    private final SelenideElement searchConfirmationMessage = $(By.xpath("//h2"));
    private final SelenideElement filter = $(By.xpath("//span[text()='Filter']/.."));
    private final SelenideElement bedsPlus = $(By.xpath("//div[text() = 'Beds']/following::button[2]"));
    private final SelenideElement bedsCounter = $(By.xpath("//div[text() = 'Beds']/following::span[1]"));
    private final SelenideElement bedroomsPlus = $(By.xpath("//div[text() = 'Bedrooms']/following::button[2]"));
    private final SelenideElement bedroomsCounter = $(By.xpath("//div[text() = 'Bedrooms']/following::span[1]"));
    private final SelenideElement bathroomsPlus = $(By.xpath("//div[text() = 'Bathrooms']/following::button[2]"));
    private final SelenideElement bathroomsCounter = $(By.xpath("//div[text() = 'Bathrooms']/following::span[1]"));
    private final SelenideElement amenitiesArea = $(By.xpath("//div[text() = 'Amenities']/following::div[1]"));
    private final SelenideElement clearAll = $(By.xpath("//b[text() = 'Clear all']"));
    private final String amenitiesCheckBoxLabel = "//input[@id='id' and @class='sc-htmcrh cglIfG']/following::span[@class='sc-dTSzeu krkYvd']";
    private final String amenitiesCheckBox = "//input[@id='id' and @class='sc-htmcrh cglIfG']";

    @Step
    public void isSearchResultPageOpened() {
        assertTrue(WebDriverRunner.getWebDriver().getCurrentUrl().contains("search"));
    }

    @Step
    public SearchResultPage clickFilterButton() {
        filter.shouldBe(Condition.visible);
        filter.click();

        return this;
    }

    @Step
    public SearchResultPage bedsPlus() {
        bedsPlus.click();
        return this;
    }

    @Step
    public SearchResultPage bedroomsPlus() {
        bedroomsPlus.click();
        return this;
    }

    @Step
    public SearchResultPage bathroomsPlus() {
        bathroomsPlus.click();
        return this;
    }

    @Step
    public void isNotRooms() {
        bedsCounter.shouldHave(Condition.ownText("0"));
        bedroomsCounter.shouldHave(Condition.ownText("0"));
        bathroomsCounter.shouldHave(Condition.ownText("0"));
    }

    @Step
    public SearchResultPage selectAllAmenities() {
        amenitiesArea.shouldBe(Condition.visible);
        List<SelenideElement> we = $$(By.xpath(amenitiesCheckBoxLabel));
        for (SelenideElement amenitie : we) {
            amenitie.click();
        }
        return this;
    }

    @Step
    public SearchResultPage roomsPlus() {
        bedsPlus();
        bedroomsPlus();
        bathroomsPlus();

        return this;
    }

    @Step
    public SearchResultPage clearAll() {
        clearAll.click();
        return this;
    }

    @Step
    public void isAllFiltersOff() {
        amenitiesArea.shouldBe(Condition.visible);
        List<SelenideElement> we = $$(By.xpath(amenitiesCheckBox));
        for (SelenideElement сheckBox : we) {
            сheckBox.shouldNotBe(Condition.checked);
        }
        isNotRooms();
    }

    @Step
    public void isSearchSuccessfulMessageShown() {
        searchConfirmationMessage.shouldBe(Condition.visible);
        searchConfirmationMessage.shouldHave(Condition.ownText("found"));
    }
}
