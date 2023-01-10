import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {
    @BeforeAll
    public static void setUp() {
        Configuration.pageLoadTimeout = 60000;
        Configuration.timeout = 15000;
        Configuration.browserSize = "1920x1080";
    }
}
