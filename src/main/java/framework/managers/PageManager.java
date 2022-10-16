package framework.managers;

import framework.pages.CreditPage;
import framework.pages.StartPage;

public class PageManager {

    private static PageManager INSTANCE = null;

    private StartPage startPage;
    private CreditPage creditPage;
//    private ProductPage productPage;
//    private BasketPage basketPage;

    private PageManager() {

    }

    public static PageManager getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new PageManager();
        }
        return INSTANCE;
    }

    public StartPage getStartPage() {
        if (startPage == null) {
            startPage = new StartPage();
        }
        return startPage;
    }

    public CreditPage getCreditPage() {
        if (creditPage == null) {
            creditPage = new CreditPage();
        }
        return creditPage;
    }

//    public ProductPage getProductPage() {
//        if (productPage == null) {
//            productPage = new ProductPage();
//        }
//        return productPage;
//    }
//
//    public BasketPage getBasketPage() {
//        if (basketPage == null) {
//            basketPage = new BasketPage();
//        }
//        return basketPage;
//    }
}
