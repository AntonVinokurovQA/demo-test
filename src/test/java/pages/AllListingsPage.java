package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import tools.PropertyReader;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AllListingsPage {
    private SelenideElement header = $(By.xpath("//h2"));
    private String headerOfElement = "//h3";

    public AllListingsPage openAllListingsPage(){
        open(PropertyReader.getAllListingsUrl());

        return this;
    }

    public void checkAllListingsPageOpened(){
        header.shouldBe(Condition.visible);
        header.shouldHave(Condition.ownText("Properties"));
    }

    public AllListingsPage goToTab(String tabName){
        $(By.xpath("//h2/following::div[1]/span[contains(text(), '" + tabName + "')]")).click();

        return this;
    }

    public int getNumberOfListingsFromTabHeader(String tabName){
        String s = $(By.xpath("//h2/following::div[1]/span[contains(text(), '" + tabName + "')]")).getOwnText();
        String[] ss = s.split("\\D+");

        return Integer.parseInt(String.join("", ss));
    }

    public void isCorrectNumberOfListings(String tabName){
        int number = getNumberOfListingsFromTabHeader(tabName);
        goToTab(tabName);
        $(By.xpath(headerOfElement)).shouldBe(Condition.visible);
        assertTrue($$(By.xpath(headerOfElement)).size() == number);
    }
}
