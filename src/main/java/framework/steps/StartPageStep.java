package framework.steps;

import framework.managers.PageManager;
import io.cucumber.java.ru.И;

public class StartPageStep {

    PageManager pageManager = PageManager.getINSTANCE();

    @И("^Закрытие окна Cookie$")
    public void closeCookie() {
        pageManager.getStartPage().closeCookie();
    }

    @И("^Выбор пункта (.+) основного меню$")
    public void selectBaseMenu(String baseMenuValue) {
        pageManager.getStartPage().selectBaseMenu(baseMenuValue);
    }

    @И("^Выбор пункта (.+) подменю$")
    public void selectSubMenu(String subMenuValue) {
        pageManager.getStartPage().selectSubMenu(subMenuValue);
    }


}
