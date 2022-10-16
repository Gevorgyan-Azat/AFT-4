package framework.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CreditPage extends BasePage {

    @FindBy(xpath = "//iframe[@title='Основной контент']")
    private WebElement filterIframe;

    @FindBy(xpath = "//div[@class='ugyMAENZwRVFZMfAVn6H6']")
    private WebElement filterBlock;

    @FindBy(xpath = "//input[@class='dc-input__input-6-1-2']")
    private List<WebElement> creditFilterField;

    @FindBy(xpath = "//div[@class='_2cwqL96CPji6zNQD6fZqsT']//span[contains(., 'Страхование жизни и здоровья')]/../..//input")
    private WebElement checkBoxInsurance;

    @FindBy(xpath = "//div[@class = '_1aQ5fS4QO26GDWmJlBTrB6']")
    private WebElement resultBlock;

    @FindBy(xpath = "//div[@class = '_1aQ5fS4QO26GDWmJlBTrB6']//span[@class='_26BvotuEdfUqvHxVLFI4HM']/span")
    private WebElement creditSum;

    @FindBy(xpath = "//div[@class = '_1aQ5fS4QO26GDWmJlBTrB6']//span[@class='_26BvotuEdfUqvHxVLFI4HM _3CuveLJXHXSqjchPOmboU6']/span")
    private WebElement monthlyPayment;

    @FindBy(xpath = "//div[@class = '_1aQ5fS4QO26GDWmJlBTrB6']//span[text()='Необходимый доход']/following::span/span")
    private WebElement requiredIncome;

    @FindBy(xpath = "//div[@class = '_1aQ5fS4QO26GDWmJlBTrB6']//span[text()='Процентная ставка']/following::span[contains(@class, '_2r8rfcLzQ5sxPatTioE8BD')]/span")
    private WebElement percentRate;


    public CreditPage selectField(String fieldName, String priceValue) {
        waitForJavascript();
        driverManager.getDriver().switchTo().frame(filterIframe);
        for (WebElement creditFilter : creditFilterField) {
            if (creditFilter.findElement(By.xpath("./..//label")).getAttribute("textContent").contains(fieldName)) {
                fillField(creditFilter, priceValue);
            }
        }
        driverManager.getDriver().switchTo().defaultContent();
        return this;
    }

    public CreditPage selectCheckBoxInsurance(boolean expectedStatus) {
        selectCheckBox(checkBoxInsurance, expectedStatus);
        return this;
    }


    public CreditPage checkCreditSum(String creditSumValue) {
        checkResultValue(creditSum, creditSumValue, "Сумма кредита");
        return this;
    }

    public CreditPage checkMonthlyPayment(String monthlyPaymentValue) {
        checkResultValue(monthlyPayment, monthlyPaymentValue, "Ежемесячный платеж");
        return this;
    }

    public CreditPage checkRequiredIncome(String requiredIncomeValue) {
        checkResultValue(requiredIncome, requiredIncomeValue, "Необходимый доход");
        return this;
    }

    public CreditPage checkPercentRate(String percentRateValue) {
        waitForJavascript();
        driverManager.getDriver().switchTo().frame(filterIframe);
        scrollElementInCenter(resultBlock);
        Assert.assertEquals("Значение 'Процентная ставка' не совпадает с ожидаемым значением",
                percentRateValue, percentRate.getText());
        driverManager.getDriver().switchTo().defaultContent();
        return this;
    }

    private void selectCheckBox(WebElement checkBox, boolean expectedStatus) {
        waitForJavascript();
        driverManager.getDriver().switchTo().frame(filterIframe);
        if (expectedStatus) {
            if (Boolean.parseBoolean(checkBox.getAttribute("ariaChecked"))) {
                driverManager.getDriver().switchTo().defaultContent();
            } else {
                checkBoxClick(checkBox);
            }
        }
        if (!(expectedStatus)) {
            if (!(Boolean.parseBoolean(checkBox.getAttribute("ariaChecked")))) {
                driverManager.getDriver().switchTo().defaultContent();
            } else {
                checkBoxClick(checkBox);
            }
        }
    }

    private void checkBoxClick(WebElement checkBox) {
        scrollElementInCenter(checkBox);
        js.executeScript("arguments[0].click();", checkBox);
        driverManager.getDriver().switchTo().defaultContent();
    }

    private void fillField(WebElement element, String value) {
        scrollElementInCenter(element);
        js.executeScript("arguments[0].click();", element);
        element.sendKeys(Keys.CONTROL + "a");
        sendKeyArray(element, value);
        element.sendKeys(Keys.ENTER);
    }

    private void sendKeyArray(WebElement element, String value) {
        String[] strValue = value.split("");
        for (String s: strValue) {
            element.sendKeys(s);
        }
    }

    private void checkResultValue(WebElement element, String checkValue, String messageValue) {
        waitForJavascript();
        driverManager.getDriver().switchTo().frame(filterIframe);
        scrollElementInCenter(resultBlock);
        Assert.assertEquals("Значение '" + messageValue + "' не совпадает с ожидаемым значением",
                checkValue, convertToNumb(element));
        driverManager.getDriver().switchTo().defaultContent();
    }

    private String convertToNumb(WebElement element) {
        return element.getText().replaceAll("\\D", "");
    }
}
