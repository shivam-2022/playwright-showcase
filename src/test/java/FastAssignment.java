import com.microsoft.playwright.*;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.Test;
@UsePlaywright
public class FastAssignment {
    @Test
    public void test(Page page) {
        page.navigate("https://www.fast.com");
        Locator speedResult = page.locator("#speed-value");
        Locator speedUnit =page.locator("#speed-units");
        do {
            System.out.println(speedResult.allInnerTexts() + " " + speedUnit.allInnerTexts());
        }
        while (!speedResult.getAttribute("class").contains("succeeded"));
        if(speedResult.getAttribute("class").contains("succeeded")){
            System.out.println("Final speed is " +speedResult.allInnerTexts()+" "+speedUnit.allTextContents());
        }
    }
}
