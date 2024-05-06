package guru.qa.niffler.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class MainPage {
    private final SelenideElement loginButton = $x("//a[.='Login'] ");

    public void clickLoginButton() {
        loginButton.click();
    }
}
