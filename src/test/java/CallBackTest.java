import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CallBackTest {
    @Test
    void callBackRequest() {
        open("http://localhost:9999");
        SelenideElement form = $("[data-test-id=callback-form]");
        $("[data-test-id=name] input").setValue("Василий Теркин");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=order-success]").shouldHave(exactText(" Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void doubleNameInput() {
        open("http://localhost:9999");
        SelenideElement form = $("[data-test-id=callback-form]");
        $("[data-test-id=name] input").setValue("Жан Клод Теркин");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=order-success]").shouldHave(exactText(" Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));

    }

    @Test
    void enteringA_DoubleLastName() {
        open("http://localhost:9999");
        SelenideElement form = $("[data-test-id=callback-form]");
        $("[data-test-id=name] input").setValue("Василий Теркин-Иванов");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=order-success]").shouldHave(exactText(" Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void numberEntryFrom8() {
        open("http://localhost:9999");
        SelenideElement form = $("[data-test-id=callback-form]");
        $("[data-test-id=name] input").setValue("Василий Теркин");
        $("[data-test-id=phone] input").setValue("89270000000");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=phone] .input__sub")
                .shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }

    @Test
    void emptyFieldFirstNameLastName() {
        open("http://localhost:9999");
        SelenideElement form = $("[data-test-id=callback-form]");
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=name] .input__sub")
                .shouldHave(exactText("Поле обязательно для заполнения"));

    }

    @Test
    void  fieldIsNotFilled() {
        open("http://localhost:9999");
        SelenideElement form = $("[data-test-id=callback-form]");
        $("[data-test-id=name] input").setValue("Василий Теркин");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=phone] .input__sub")
                .shouldHave(exactText("Поле обязательно для заполнения"));
    }


    @Test
    void didNotClickOnTheCheckbox() {
        open("http://localhost:9999");
        SelenideElement form = $("[data-test-id=callback-form]");
        $("[data-test-id=name] input").setValue("Василий Теркин");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[type=button]").click();
        boolean exists = $("[data-test-id=agreement].input_invalid .checkbox__text").exists();

    }

}
