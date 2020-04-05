import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

public class SteamLoginTest {
    @Test
    public void RunTest()
    {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
        ChromeDriver chromeDriver=new ChromeDriver();
        chromeDriver.get("https://www.google.com/");
        var pageObject=new PageObject();
        var start = pageObject.new SearchPage(chromeDriver);
        start.toSteam().toLogin().loginToMyPage().toProfilePage().verify();
    }
}
