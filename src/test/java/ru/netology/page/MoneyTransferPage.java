package ru.netology.page;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.withText;

public class MoneyTransferPage {
    private SelenideElement heading = $(withText("Пополнение карты"));
    private final SelenideElement amountInputNew = $("[data-test-id='amount'] input");
    private final SelenideElement fromInput = $("[data-test-id='from'] input");
    private final SelenideElement transferButton = $("[data-test-id='action-transfer']");
    private final SelenideElement errorMessage = $("[data-test-id='error-message']");

    public MoneyTransferPage() {
        heading.shouldBe(visible);
    }


    public DashboardPage makeValidTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        makeTransfer(amountToTransfer, cardInfo);
        return new DashboardPage();
    }

    public DashboardPage makeTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        amountInputNew.setValue(amountToTransfer);
        fromInput.setValue(cardInfo.getCardNumber());
        transferButton.click();
        return new DashboardPage();
    }

    public void findErrorMessage(String expectedText) {
        errorMessage.shouldHave(exactText(expectedText), Duration.ofSeconds(30)).shouldBe(visible);
    }
}
