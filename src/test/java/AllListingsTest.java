import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.AllListingsPage;

public class AllListingsTest extends BaseTest {
    AllListingsPage page = new AllListingsPage();

    @Test
    @DisplayName("Check that All Listings page was opened")
    public void checkAllListingsPageOpened() {
        page.openAllListingsPage()
                .checkAllListingsPageOpened();
    }

    @Test
    @DisplayName("Check that numbers of listings is correct")
    public void checkCorrectNumberOfListings() {
        page.openAllListingsPage()
                .isCorrectNumberOfListings("test");
    }
}
