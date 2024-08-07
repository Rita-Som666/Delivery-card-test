package ru.netology.test;

import com.codeborne.selenide.Condition;

import com.codeborne.selenide.SelenideElement;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.epam.reportportal.junit5.ReportPortalExtension;

import org.junit.jupiter.api.extension.ExtendWith;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

@ExtendWith(ReportPortalExtension.class)
public class DeliveryCardTest {


    private Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("reportportal.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find reportportal.properties");
                return null;
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return properties;
    }

    private String generateDate(long addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    void shouldSendSuccessAndPositiveValidationDatePlus3() {
        Properties properties = loadProperties();
        if (properties != null) {
            System.out.println("rp.endpoint: " + properties.getProperty("rp.endpoint"));
        }

        open("http://localhost:9999/");


        $("[data-test-id='city'] .input__control").sendKeys("Калининград");
        SelenideElement element = $("[data-test-id='date'] .input__control");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] .input__control").sendKeys(date);
        $("[data-test-id='name'] .input__control").sendKeys("Иван Иванов");
        $("[data-test-id='phone'] .input__control").sendKeys("+79850001122");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $(byCssSelector("[data-test-id='notification'] .notification__content")).shouldBe(Condition.visible, Duration.ofSeconds(15)).shouldHave(Condition.text(date));
    }

    @Test
    void shouldSendSuccessAndPositiveValidationDatePlus4() {
        open("http://localhost:9999/");


        $("[data-test-id='city'] .input__control").sendKeys("Калининград");
        SelenideElement element = $("[data-test-id='date'] .input__control");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] .input__control").sendKeys(date);
        $("[data-test-id='name'] .input__control").sendKeys("Иван Иванов");
        $("[data-test-id='phone'] .input__control").sendKeys("+79850001122");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $(byCssSelector("[data-test-id='notification'] .notification__content")).shouldBe(Condition.visible, Duration.ofSeconds(15)).shouldHave(Condition.text(date));
    }

    @Test
    void shouldSendSuccessAndPositiveValidationNameWithOutSpase() {
        open("http://localhost:9999/");


        $("[data-test-id='city'] .input__control").sendKeys("Калининград");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] .input__control").sendKeys(date);
        $("[data-test-id='name'] .input__control").sendKeys("Иван");
        $("[data-test-id='phone'] .input__control").sendKeys("+79850001122");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $(byCssSelector("[data-test-id='notification'] .notification__content")).shouldBe(Condition.visible, Duration.ofSeconds(15)).shouldHave(Condition.text(date));
    }

    @Test
    void shouldSendSuccessAndPositiveValidationNameWithHyphen() {
        open("http://localhost:9999/");


        $("[data-test-id='city'] .input__control").sendKeys("Калининград");
        SelenideElement element = $("[data-test-id='date'] .input__control");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] .input__control").sendKeys(date);
        $("[data-test-id='name'] .input__control").sendKeys("Иван-Иванов");
        $("[data-test-id='phone'] .input__control").sendKeys("+79850001122");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $(byCssSelector("[data-test-id='notification'] .notification__content")).shouldBe(Condition.visible, Duration.ofSeconds(15)).shouldHave(Condition.text(date));
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
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
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
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
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
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
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
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
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
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
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
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
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
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
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
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
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
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
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
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
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
        $(byText("Екатеринбург")).hover().click();

        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] .input__control").sendKeys(date);
        $("[data-test-id='name'] .input__control").sendKeys("Иван Иванов");
        $("[data-test-id='phone'] .input__control").sendKeys("+79850001122");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $(byCssSelector("[data-test-id='notification'] .notification__content")).shouldBe(Condition.visible, Duration.ofSeconds(15)).shouldHave(Condition.text(date));
    }

    @Test
    void shouldSendSuccessAndCalendarMenu() {

        open("http://localhost:9999/");


        $("[data-test-id='city'] .input__control").sendKeys("Калининград");
        $("[data-test-id='date'] .input__control").click();
        LocalDate currentDate = LocalDate.now();
        LocalDate futureDate = currentDate.plusDays(7);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String futureDateS = futureDate.format(formatter);

        if (currentDate.getMonthValue() != futureDate.getMonthValue()) {
            $(".calendar__arrow_direction_right[data-step='1']").click();
        }

        String futureDay = String.valueOf(futureDate.getDayOfMonth());
        $$(".calendar__day").find(Condition.text(futureDay)).click();
        $("[data-test-id='name'] .input__control").sendKeys("Иван Иванов");
        $("[data-test-id='phone'] .input__control").sendKeys("+79850001122");
        $("[data-test-id='agreement']").click();
        $(".button").click();

        $(byCssSelector("[data-test-id='notification'] .notification__content"))
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text(futureDateS));
    }
}

