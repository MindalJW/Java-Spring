package hello.core.member;

import hello.core.Appconfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    Appconfig appconfig = new Appconfig();
    MemberService memberService = appconfig.memberService();
    @Test
    void Join(){
        //given
        Member member = new Member(1L, "memberA", Grade.VIP);
        //when
        memberService.join(member);
        Member member1 = memberService.findMember(member.getId());
        System.out.println("member1 = " + member1.getName());
        //then
        Assertions.assertThat(member).isEqualTo(member1);
    }
}
