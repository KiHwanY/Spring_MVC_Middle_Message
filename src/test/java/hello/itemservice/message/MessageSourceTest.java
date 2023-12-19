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
    MessageSource ms;

    @Test
    void helloMessage() {
        String result = ms.getMessage("hello", null, null);
        assertThat(result).isEqualTo("안녕");
    }
    /*
       ms.getMessage("hello",null,null)
       code : hello
       args : null
       locale : null

       가장 단순한 테스트는 메시지 코드로 hello를 입력하고 나머지 값은 null을 입력했다.
       locale 정보가 없으면 basename 에서 설정한 기본 이름 메시지 파일을 조회한다. basename으로 messages를 지정 했으므로,
       messages.properties 파일에서 데이터 조회한다.
    * */

    @Test
    void notFoundMessageCode() {
        assertThatThrownBy(() -> ms.getMessage("no_code", null, null))
                .isInstanceOf(NoSuchMessageException.class);
    }
    /*
        메시지가 없는 경우에는 NoSuchMessageException이 발생한다.
        메시지가 없어도 기본메시지(defaultMessage)를 사용하면 기본 메시지가 반환된다.
    * */

    @Test
    void notFoundMessageCodeDefaultMessage() {
        String result = ms.getMessage("no_code", null, "기본 메시지", null);
        assertThat(result).isEqualTo("기본 메시지");
    }

    @Test
    void argumentMessage() {
        String message = ms.getMessage("hello.name", new Object[]{"Spring"}, null);
        assertThat(message).isEqualTo("안녕 Spring");
    }
    /*
        다음 메시지의 {0} 부분은 매개변수를 전달해서 치환할 수 있다.
        hello.name = 안녕 {0} -> Spring 단어를 매개변수로 전달 -> 안녕 Spring
    * */

    @Test
    void defaultLang() {
        assertThat(ms.getMessage("hello", null, null)).isEqualTo("안녕");
        assertThat(ms.getMessage("hello", null, Locale.KOREA)).isEqualTo("안녕");
    }
    /*
        locale 정보를 기반으로 국제화 파일을 선택한다.
        locale이 en_US의 경우 messages_en_US -> messages_en -> messages 순서로 찾는다.
        Locale에 맞추어 구체적인 것을 찾고, 없으면 디폴트를 찾는다고 이해하면 된다.

        ms.getMessage("hello" , null , null ): locale 정보가 없는 경우,
        Locale.getDefault()을 호출해서 시스템의 기본 로케일을 사용합니다. ex) locale = null 인 경우 -> 시스템 기본 locale이 ko_KR이므로 message_ko.properties 조회 시도
        -> 조회 실패 -> message.properties 조회

        ms.getMessage("hello" , null , Locale.KOREA) : locale 정보가 있지만, message_ko 가 없으므로 messages를 사용한다.

    * */

    @Test
    void enLang() {
        assertThat(ms.getMessage("hello", null, Locale.ENGLISH)).isEqualTo("hello");
    }
}
