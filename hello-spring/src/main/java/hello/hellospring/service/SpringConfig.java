package hello.hellospring.service;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());//MemberService에 memberRepository를 자동으로 주입
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();//MemoryMemberRepository 반환
    }
}
