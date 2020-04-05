import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.*;

public class PageObject {
    class BasePage{
        protected WebDriver dr;
        protected WebDriverWait wait;
        public BasePage(WebDriver dr){
            this.dr = dr;
            PageFactory.initElements(dr, this);
            wait = new WebDriverWait(dr, 10);
        }
        protected final void waitForVisibility(WebElement element)throws Error{
            wait.until(ExpectedConditions.visibilityOf(element));
        }
        public void close()
        {
            dr.close();
        }
        void verify(){Assert.assertTrue(true);};
    }
    class SearchPage extends BasePage{
        @FindBy(className = "gLFyf")
        private WebElement searchBox;
        @FindBy(className = "gNO89b")
        private WebElement searchButton;
        @FindBy(xpath = "//h3[text()='Welcome to Steam']")
        private WebElement linkToSteam;
        public SearchPage(WebDriver dr) {
            super(dr);
        }
        public MainPage toSteam(){
            verify();
            searchBox.sendKeys("steam");
            searchButton.submit();
            waitForVisibility(linkToSteam);
            linkToSteam.click();
            return new MainPage(dr);
        }
    }
    class MainPage extends BasePage{
        @FindBy(css="a[class='global_action_link']")
        WebElement linkToLogin;
        public MainPage(WebDriver dr) {
            super(dr);
        }
        public LoginPage toLogin()
        {
            verify();
            linkToLogin.click();
            return new LoginPage(dr);
        }
        @FindBy(css="a[data-tooltip-content='.submenu_store']")
        WebElement storeButton;
        @FindBy(css="a[data-tooltip-content='.submenu_community']")
        WebElement communityButton;
        @Override
        void verify()
        {
            Assert.assertTrue(storeButton.isDisplayed());
            Assert.assertTrue(communityButton.isDisplayed());
        }
    }
    class LoginPage extends BasePage{
        String username="zxczxczxc098098098";
        String password="ZXCZXCZXCqweqweqwe098098098";
        @FindBy(css="input[name='username']")
        WebElement usernameBox;
        @FindBy(css="input[name='password']")
        WebElement passwordBox;
        @FindBy(css="button[type='submit']")
        WebElement buttonToSubmit;
        public LoginPage(WebDriver dr) {
            super(dr);
        }
        public MyPage loginToMyPage()
        {
            verify();
            usernameBox.sendKeys(username);
            passwordBox.sendKeys(password);
            buttonToSubmit.click();
            return new MyPage(dr);
        }
        @FindBy(xpath = "//h2[text()='登录']")
        WebElement loginText;
        @Override
        void verify()
        {
            Assert.assertTrue(usernameBox.isDisplayed());
            Assert.assertTrue(passwordBox.isDisplayed());
            Assert.assertTrue(buttonToSubmit.isDisplayed());
            Assert.assertTrue(loginText.isDisplayed());
        }
    }
    class MyPage extends BasePage{
        @FindBy(css="span[id='account_pulldown']")
        WebElement accountButton;
        @FindBy(xpath="//a[@class='popup_menu_item'][text()='View profile']")
        WebElement viewProfileButton;
        @FindBy(css="span[class='pulldown global_action_link focus']")
        WebElement popupMenu;
        public MyPage(WebDriver dr) {
            super(dr);
        }
        public ProfilePage toProfilePage()
        {
            waitForVisibility(accountButton);
            verify();
            accountButton.click();
            waitForVisibility(popupMenu);
            viewProfileButton.click();
            return new ProfilePage(dr);
        }
        @FindBy(css="a[data-tooltip-content='.submenu_store']")
        WebElement storeButton;
        @FindBy(css="a[data-tooltip-content='.submenu_community']")
        WebElement communityButton;
        @FindBy(css="a[data-tooltip-content='.submenu_username']")
        WebElement userButton;
        @Override
        void verify()
        {
            Assert.assertTrue(storeButton.isDisplayed());
            Assert.assertTrue(communityButton.isDisplayed());
            Assert.assertTrue(userButton.isDisplayed());
        }
    }
    class ProfilePage extends BasePage
    {
        public ProfilePage(WebDriver dr) {
            super(dr);
        }
        @FindBy(css="span[class='actual_persona_name']")
        WebElement userText;
        @FindBy(css="span[class='namehistory_link']")
        WebElement namehistoryLink;
        @FindBy(css="a[class='btn_profile_action btn_medium']")
        WebElement profileButton;
        @Override
        public void verify()
        {
            Assert.assertTrue(userText.isDisplayed());
            Assert.assertTrue(namehistoryLink.isDisplayed());
            Assert.assertTrue(profileButton.isDisplayed());
        }
    }
}
