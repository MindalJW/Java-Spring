package hello.hellospring.service;

import hello.hellospring.repository.JPAMemberRepository;
import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.MemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;
    public SpringConfig(MemberRepository memberRepository) {//스프링이 JPARepository를 자동으로 주입해줌
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);//MemberService에 memberRepository(JPARepository)를 자동으로 주입
    }
}
//내가 이해한 DI
/**어떤 인터페이스(MemberRepository)를 만들고 그 인터페이스를 상속받는 클래스(SpringDataJpaMemberRepository)를 만들고
 * 그 클래스안에서 다른클래스를 의존하여 그 클래스의 메소드를 사용하는 또 다른 메소드를 구현하면 그 인터페이스를 타입을 주입받는 클래스(ex. service)는
 * 인터페이스(SpringDataJpaMemberRepository) 를 상속받는 클래스에선 따로 그 다른 클래스를 생성할 필요없이 구현한 메소드를 쉽게 사용할수있다.
 * 또한 Service쪽 코드를 전혀 수정하지 않고도 다른 Repository구현으로 아주 쉽게 전환할수있다.**/
