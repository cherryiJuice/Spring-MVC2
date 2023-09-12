package hello.itemservice.message;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MessageSourceTest {

    @Autowired
    MessageSource messageSource;

    @Test
    void helloMessage() {
        String result = messageSource.getMessage("hello", null, null);
        assertThat(result).isEqualTo("안녕");
    }

    @Test()
    void noFoundMessageCode() {
        assertThatThrownBy(()-> messageSource.getMessage("no-code", null, null))
                .isInstanceOf(NoSuchMessageException.class);
    }

    @Test
    void noFoundMessageCodeDefaultMessage() {
        String result =  messageSource.getMessage("no-code", null, "기본 메세지", null);
        assertThat(result).isEqualTo("기본 메세지");
    }

    @Test
    void argumentMessage() {
        String result = messageSource.getMessage("hello.name", new Object[]{"spring"}, null);
        assertThat(result).isEqualTo("안녕 spring");
    }

    @Test
    void defaultLang() {
        assertThat(messageSource.getMessage("hello", null, null)).isEqualTo("안녕");
        assertThat(messageSource.getMessage("hello", null, Locale.KOREA));

    }

    @Test
    void enLang() {
        assertThat(messageSource.getMessage("hello", null, Locale.ENGLISH)).isEqualTo("hello");
    }
}