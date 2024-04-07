package ru.netology.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DeliveryCardTest {

@Test
    void shouldSendSuccess(){
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
}
