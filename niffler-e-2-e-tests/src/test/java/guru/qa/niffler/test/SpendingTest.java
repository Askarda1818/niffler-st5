package guru.qa.niffler.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import guru.qa.niffler.jupiter.annotation.Category;
import guru.qa.niffler.jupiter.annotation.Spend;
import guru.qa.niffler.jupiter.extension.CategoryExtension;
import guru.qa.niffler.jupiter.extension.SpendExtension;
import guru.qa.niffler.model.CurrencyValues;
import guru.qa.niffler.model.SpendJson;
import guru.qa.niffler.pages.BasePage;
import guru.qa.niffler.pages.LoginPage;
import guru.qa.niffler.pages.MainPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

@ExtendWith({CategoryExtension.class, SpendExtension.class})
public class SpendingTest {
    private final BasePage basePage = new BasePage();
    private final LoginPage loginPage = new LoginPage();
    private final MainPage mainPage = new MainPage();

    static {
        Configuration.browserSize = "1920x1080";
    }

    @BeforeEach
    void doLogin() {
        // createSpend
        Selenide.open("http://127.0.0.1:3000/");
        mainPage.clickLoginButton();
        loginPage.setUsername("Sima")
                .setPassword("Sima1818")
                .clickSubmitButton();
    }

    @Test
    void anotherTest() {
        Selenide.open("http://127.0.0.1:3000/");
        $("a[href*='redirect']").should(visible);
    }

    @Category(
            category = "Lesson1",
            username = "Sima"
    )
    @Spend(
            username = "Sima",
            description = "QA.GURU Advanced 5",
            amount = 65000.00,
            currency = CurrencyValues.RUB,
            category = "Lesson1"
    )
    @Test
    void spendingShouldBeDeletedAfterTableAction(SpendJson spendJson) {
        SelenideElement rowWithSpending = basePage.findSpendingRow(spendJson.description());
        basePage.chooseSpending(rowWithSpending)
                .clickDeleteSelectedButton()
                .checkSpendings(0);
    }
}
