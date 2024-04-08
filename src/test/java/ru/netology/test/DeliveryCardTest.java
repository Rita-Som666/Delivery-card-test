package ru.netology.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DeliveryCardTest {

    @Test
    void shouldSendSuccessAndPositiveValidationDatePlus3() {
        open("http://localhost:9999/");


        $("[data-test-id='city'] .input__control").sendKeys("Калининград");
        SelenideElement element = $("[data-test-id='date'] .input__control");
        String value = $("[data-test-id='date'] .input__control").getValue();
        for (int i = 0; i < value.length(); i++) {
            element.sendKeys(Keys.BACK_SPACE);

        }
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] .input__control").sendKeys(date);
        $("[data-test-id='name'] .input__control").sendKeys("Иван Иванов");
        $("[data-test-id='phone'] .input__control").sendKeys("+79850001122");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='notification']").shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldSendSuccessAndPositiveValidationDatePlus4() {
        open("http://localhost:9999/");


        $("[data-test-id='city'] .input__control").sendKeys("Калининград");
        SelenideElement element = $("[data-test-id='date'] .input__control");
        String value = $("[data-test-id='date'] .input__control").getValue();
        for (int i = 0; i < value.length(); i++) {
            element.sendKeys(Keys.BACK_SPACE);

        }
        String date = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] .input__control").sendKeys(date);
        $("[data-test-id='name'] .input__control").sendKeys("Иван Иванов");
        $("[data-test-id='phone'] .input__control").sendKeys("+79850001122");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='notification']").shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldSendSuccessAndPositiveValidationNameWithOutSpase() {
        open("http://localhost:9999/");


        $("[data-test-id='city'] .input__control").sendKeys("Калининград");
        SelenideElement element = $("[data-test-id='date'] .input__control");
        String value = $("[data-test-id='date'] .input__control").getValue();
        for (int i = 0; i < value.length(); i++) {
            element.sendKeys(Keys.BACK_SPACE);

        }
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] .input__control").sendKeys(date);
        $("[data-test-id='name'] .input__control").sendKeys("Иван");
        $("[data-test-id='phone'] .input__control").sendKeys("+79850001122");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='notification']").shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldSendSuccessAndPositiveValidationNameWithHyphen() {
        open("http://localhost:9999/");


        $("[data-test-id='city'] .input__control").sendKeys("Калининград");
        SelenideElement element = $("[data-test-id='date'] .input__control");
        String value = $("[data-test-id='date'] .input__control").getValue();
        for (int i = 0; i < value.length(); i++) {
            element.sendKeys(Keys.BACK_SPACE);

        }
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] .input__control").sendKeys(date);
        $("[data-test-id='name'] .input__control").sendKeys("Иван-Иванов");
        $("[data-test-id='phone'] .input__control").sendKeys("+79850001122");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='notification']").shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void NegativeValidationFieldCity() {
        open("http://localhost:9999/");


        $("[data-test-id='city'] .input__control").sendKeys("Балашиха");
        $(".button").click();
        $(byText("Доставка в выбранный город недоступна")).shouldBe(Condition.visible);

    }

    @Test
    void NegativeValidationFieldDate() {
        open("http://localhost:9999/");


        $("[data-test-id='city'] .input__control").sendKeys("Калининград");
        SelenideElement element = $("[data-test-id='date'] .input__control");
        String value = $("[data-test-id='date'] .input__control").getValue();
        for (int i = 0; i < value.length(); i++) {
            element.sendKeys(Keys.BACK_SPACE);

        }
        String date = LocalDate.now().plusDays(2).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] .input__control").sendKeys(date);
        $(".button").click();
        $(byText("Заказ на выбранную дату невозможен")).shouldBe(Condition.visible);


    }

    @Test
    void NegativeValidationFieldNameWithSpecSymbol() {
        open("http://localhost:9999/");


        $("[data-test-id='city'] .input__control").sendKeys("Калининград");
        SelenideElement element = $("[data-test-id='date'] .input__control");
        String value = $("[data-test-id='date'] .input__control").getValue();
        for (int i = 0; i < value.length(); i++) {
            element.sendKeys(Keys.BACK_SPACE);

        }
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] .input__control").sendKeys(date);
        $("[data-test-id='name'] .input__control").sendKeys("Иван.Иванов");
        $(".button").click();
        $(byText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.")).shouldBe(Condition.visible);

    }

    @Test
    void NegativeValidationFieldNameWithRoman() {
        open("http://localhost:9999/");


        $("[data-test-id='city'] .input__control").sendKeys("Калининград");
        SelenideElement element = $("[data-test-id='date'] .input__control");
        String value = $("[data-test-id='date'] .input__control").getValue();
        for (int i = 0; i < value.length(); i++) {
            element.sendKeys(Keys.BACK_SPACE);

        }
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] .input__control").sendKeys(date);
        $("[data-test-id='name'] .input__control").sendKeys("Иваn Иванов");
        $(".button").click();
        $(byText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.")).shouldBe(Condition.visible);

    }

    @Test
    void NegativeValidationFieldNameWithDigit() {
        open("http://localhost:9999/");


        $("[data-test-id='city'] .input__control").sendKeys("Калининград");
        SelenideElement element = $("[data-test-id='date'] .input__control");
        String value = $("[data-test-id='date'] .input__control").getValue();
        for (int i = 0; i < value.length(); i++) {
            element.sendKeys(Keys.BACK_SPACE);

        }
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] .input__control").sendKeys(date);
        $("[data-test-id='name'] .input__control").sendKeys("Иван Иванов1");
        $(".button").click();
        $(byText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.")).shouldBe(Condition.visible);
    }

    @Test
    void negativeValidationFieldPhoneWith10Digits() {
        open("http://localhost:9999/");


        $("[data-test-id='city'] .input__control").sendKeys("Калининград");
        SelenideElement element = $("[data-test-id='date'] .input__control");
        String value = $("[data-test-id='date'] .input__control").getValue();
        for (int i = 0; i < value.length(); i++) {
            element.sendKeys(Keys.BACK_SPACE);

        }
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] .input__control").sendKeys(date);
        $("[data-test-id='name'] .input__control").sendKeys("Иван Иванов");
        $("[data-test-id='phone'] .input__control").sendKeys("+7985000112");
        $(".button").click();
        $(byText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.")).shouldBe(Condition.visible);
    }

    @Test
    void negativeValidationFieldPhoneWith12Digits() {
        open("http://localhost:9999/");


        $("[data-test-id='city'] .input__control").sendKeys("Калининград");
        SelenideElement element = $("[data-test-id='date'] .input__control");
        String value = $("[data-test-id='date'] .input__control").getValue();
        for (int i = 0; i < value.length(); i++) {
            element.sendKeys(Keys.BACK_SPACE);

        }
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] .input__control").sendKeys(date);
        $("[data-test-id='name'] .input__control").sendKeys("Иван Иванов");
        $("[data-test-id='phone'] .input__control").sendKeys("+798500011222");
        $(".button").click();
        $(byText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.")).shouldBe(Condition.visible);
    }

    @Test
    void negativeValidationFieldPhoneWithOutPlus() {
        open("http://localhost:9999/");


        $("[data-test-id='city'] .input__control").sendKeys("Калининград");
        SelenideElement element = $("[data-test-id='date'] .input__control");
        String value = $("[data-test-id='date'] .input__control").getValue();
        for (int i = 0; i < value.length(); i++) {
            element.sendKeys(Keys.BACK_SPACE);

        }
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] .input__control").sendKeys(date);
        $("[data-test-id='name'] .input__control").sendKeys("Иван Иванов");
        $("[data-test-id='phone'] .input__control").sendKeys("89850001122");
        $(".button").click();
        $(byText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.")).shouldBe(Condition.visible);
    }

    @Test
    void negativeValidationFieldPhoneWithOutAgreement() {
        open("http://localhost:9999/");


        $("[data-test-id='city'] .input__control").sendKeys("Калининград");
        SelenideElement element = $("[data-test-id='date'] .input__control");
        String value = $("[data-test-id='date'] .input__control").getValue();
        for (int i = 0; i < value.length(); i++) {
            element.sendKeys(Keys.BACK_SPACE);

        }
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] .input__control").sendKeys(date);
        $("[data-test-id='name'] .input__control").sendKeys("Иван Иванов");
        $("[data-test-id='phone'] .input__control").sendKeys("+79850001122");
        $(".button").click();
        $("label.checkbox.input_invalid").shouldBe(Condition.exist);

    }
}