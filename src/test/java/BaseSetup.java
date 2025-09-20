import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;

public class BaseSetup {
    private static Playwright playwright;
    private static Browser browser;
    BrowserContext browserContext;
    Page page;

    @BeforeAll
    public static void setUpBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(false).setSlowMo(50)
                        .setArgs(Arrays.asList("--no-sandbox", "--disable-extensions", "--disable-gpu"))
        );
    }

    public void setUp(String Url) {
        browserContext  = browser.newContext();
        if(Url.isEmpty()){
            page = browserContext.newPage();
        }
        else {
            page = browserContext.newPage();
            page.navigate(Url);
        }
    }

    @AfterAll
    static void teardown() {
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }

}
