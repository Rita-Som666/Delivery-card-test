package ru.netology.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class DeliveryCardTest {
    private String generateDate(long addDays, String pattern){
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

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
    void NegativeValidationFieldNameBlank() {
        open("http://localhost:9999/");


        $("[data-test-id='city'] .input__control").sendKeys("Калининград");
        SelenideElement element = $("[data-test-id='date'] .input__control");
        String value = $("[data-test-id='date'] .input__control").getValue();
        for (int i = 0; i < value.length(); i++) {
            element.sendKeys(Keys.BACK_SPACE);

        }
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] .input__control").sendKeys(date);
        $("[data-test-id='name'] .input__control").sendKeys("");
        $(".button").click();
        $(byText("Поле обязательно для заполнения")).shouldBe(Condition.visible);
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
    void negativeValidationFieldPhoneBlank() {
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
        $("[data-test-id='phone'] .input__control").sendKeys("");
        $(".button").click();
        $(byText("Поле обязательно для заполнения")).shouldBe(Condition.visible);
    }

    @Test
    void negativeValidationWithOutAgreement() {
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

    @Test
    void shouldSendSuccessAndCityMenu() {
        open("http://localhost:9999/");


        $("[data-test-id='city'] .input__control").sendKeys("Ек");
        $(By.cssSelector(".menu_mode_radio-check > div:nth-child(4)")).hover().click();

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
    void shouldSendSuccessAndCalendarMenu() {

        open("http://localhost:9999/");


        $("[data-test-id='city'] .input__control").sendKeys("Калининград");
        $("[data-test-id='date'] .input__control").click();



       if (!generateDate(3,"MM").equals(generateDate(7, "MM"))){
           $(".calendar__arrow_direction_right [data-step='1']").click();

       } else {
           List <SelenideElement> days = $$(".calendar__day");
          days.
       }




        $("[data-test-id='name'] .input__control").sendKeys("Иван Иванов");
        $("[data-test-id='phone'] .input__control").sendKeys("+79850001122");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='notification']").shouldBe(Condition.visible, Duration.ofSeconds(15));
    }
}
