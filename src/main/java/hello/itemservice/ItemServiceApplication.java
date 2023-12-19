package hello.itemservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

@SpringBootApplication
public class ItemServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemServiceApplication.class, args);
	}

/*
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasenames("messages", "errors");
		messageSource.setDefaultEncoding("utf-8");
		return messageSource;
	}
*/
	/*
	basenames : 설정 파일의 이름을 지정한다.
		messages로 지정하면 messages.properties 파일을 읽어서 사용한다.
		추가로 국제화 기능을 적용하려면 messages_en.properties , messages_ko.properties와 같이 파일명 마지막에 언어 정보를 주면된다.
		만약 찾을 수 있는 국제화 파일이 없으면 messages_properties(언어정보가 없는 파일명)를 기본으로 사용한다.
		파일의 위치는 /resources/messages.properties 에 두면 된다.
		여러 파일을 한번에 지정할 수 있다. 여기서는 messages,errors 둘을 지정했다.
	defaultEncoding : 인코딩 정보를 지정한다. utf-8을 사용하면 된다.
	*/
}

/* 악덕? 기획자가 화면에 보이는 문구가 마음에 들지 않는다고, 상품명이라는 단어를 모두 상품이름으로 고쳐달라고 하면 어떻게 해야할까?
*  여러 화면에 보이는 상품명,가격,수량 등, label에 있는 단어를 변경하려면 다음 화면들을 다 찾아가면서 모두 변경해야 한다.
*  지금처럼 화면 수가 적으면 문제가 되지 않지만 화면이 수십개 이상이라면 수십개의 파일을 모두 고쳐야 한다.
*
*  메시지와 국제화 기능을 직접 구현할 수도 있겠지만, 스프링은 기본적인 메시지와 국제화 기능을 모두 제공한다.
*  그리고 타임리프도 스프링이 제공하는 메시지와 국제화 기능을 편리하게 통합해서 제공한다.
*
*
* [스프링 메시지 소스 설정]
*
*  메시지 관리 기능을 사용하려면 스프링이 제공하는 MessageSource를 스프링 빈으로 등록하면 되는데, MessageSource는 인터페이스다.
*  따라서 구현체인 ResourceBundleMessageSource 를 스프링 빈으로 등록하면 된다. */

/*
* spring.messages.basename=messages
*
	MessageSource 를 스프링 빈으로 등록하지 않고, 스프링 부트와 관련된 별도의 설정을 하지 않으면
	messages 라는 이름으로 기본 등록된다. 따라서 messages_en.properties ,
	messages_ko.properties , messages.properties 파일만 등록하면 자동으로 인식된다
* */