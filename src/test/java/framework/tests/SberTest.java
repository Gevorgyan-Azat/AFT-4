package framework.tests;

import org.junit.Test;

public class SberTest extends BaseTests {

    @Test
    public void sberTest() {
        pageManager.getStartPage()
                .closeCookie()
                .selectBaseMenu("Ипотека")
                .selectSubMenu("Ипотека на вторичное жильё")
                .selectField("Стоимость недвижимости", "5180000")
                .selectField("Первоначальный взнос", "3058000")
                .selectField("Срок кредита", "30")
                .selectCheckBoxInsurance(false)
                .checkCreditSum("2122000")
                .checkMonthlyPayment("20852")
                .checkRequiredIncome("35448")
                .checkPercentRate("11%");
    }

}
