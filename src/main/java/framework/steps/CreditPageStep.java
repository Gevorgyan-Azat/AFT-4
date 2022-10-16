package framework.steps;

import framework.managers.PageManager;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.И;

public class CreditPageStep {

    PageManager pageManager = PageManager.getINSTANCE();

    @И("^Заполняем поля в формате поле/значение$")
    public void selectField(DataTable dataTable) {
        dataTable.asMap(String.class, String.class).forEach((key, value) ->
                pageManager.getCreditPage().selectField((String) key, (String) value) );
    }

    @И("^Задаем состояние CheckBox 'Страхование жизни и здоровья' (.+)$")
    public void selectCheckBoxInsurance(boolean expectedStatus) {
        pageManager.getCreditPage().selectCheckBoxInsurance(expectedStatus);
    }

    @И("^Проверяем сумму кредита (.+)$")
    public void checkCreditSum(String creditSumValue){
        pageManager.getCreditPage().checkCreditSum(creditSumValue);
    }

    @И("^Проверяем ежемесячный платеж (.+)$")
    public void checkMonthlyPayment(String monthlyPaymentValue){
        pageManager.getCreditPage().checkMonthlyPayment(monthlyPaymentValue);
    }

    @И("^Проверяем необходимый доход (.+)$")
    public void checkRequiredIncome(String requiredIncomeValue){
        pageManager.getCreditPage().checkRequiredIncome(requiredIncomeValue);
    }

    @И("^Проверяем процентную ставку (.+)$")
    public void checkPercentRate(String percentRateValue){
        pageManager.getCreditPage().checkPercentRate(percentRateValue);
    }


}
