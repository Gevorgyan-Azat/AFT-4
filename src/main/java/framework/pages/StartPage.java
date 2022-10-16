package framework.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class StartPage extends BasePage {

    @FindBy(xpath = "//div[contains(@class, 'kitt-cookie-warning_show')]")
    private WebElement cookieBlock;

    @FindBy(xpath = "//button[@class='kitt-cookie-warning__close']")
    private WebElement closeCookieBtn;

    @FindBy(xpath = "//div[@class='kitt-cookie-warning']")
    private WebElement cookieClose;

    @FindBy(xpath = "//a[@role='button' and @aria-expanded]")
    private List<WebElement> baseMenuList;

    @FindBy(xpath = "//li[contains(@class, 'kitt-top-menu__item_opened')]")
    private WebElement baseMenuOpened;

    @FindBy(xpath = "//a[contains(@class, 'kitt-top-menu__link_second')]")
    private List<WebElement> subMenuList;

    public StartPage closeCookie() {
        if(cookieBlock.isDisplayed()) {
            waitUtilElementToBeClickable(closeCookieBtn).click();
        }
        Assert.assertTrue("Блок Cookie не закрыт", cookieClose.isEnabled());
        return this;
    }

    public StartPage selectBaseMenu(String baseMenuValue) {
        for (WebElement baseMenu: baseMenuList) {
            if(baseMenu.getAttribute("textContent").contains(baseMenuValue)){
                waitUtilElementToBeVisible(baseMenu);
                waitUtilElementToBeClickable(baseMenu).click();
            }
        }
        Assert.assertTrue("Меню '"+ baseMenuValue +"' не открыто", baseMenuOpened.isEnabled());
        return this;
    }

    public CreditPage selectSubMenu(String subMenuValue) {
        for (WebElement subMenu: subMenuList) {
            if(subMenu.getAttribute("text").contains(subMenuValue)){
                waitUtilElementToBeVisible(subMenu);
                waitUtilElementToBeClickable(subMenu).click();
                return pageManager.getCreditPage();
            }
        }
        return pageManager.getCreditPage();
    }
}
