package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);//컨테이너에 TestBean 등록
    }


    static class TestBean {

        @Autowired(required = false)
        public void setNoBean(Member member) { //자동주입으로 Member 주입, required = false로 되어있기 때문에
            // spring은 bean에서 Member를 찾지못해도 예외를 던지지 않음.
            System.out.println("member = " + member);
        }

        @Autowired
        public void setNoBean1(@Nullable Member member1) {//Bean이 없으면 null로 들어옴
            System.out.println("member1 = " + member1);
        }

        @Autowired
        public void setNoBean2(Optional<Member> member2) {//Bean이 없으면 Optional.empty로 들어옴
            System.out.println("member2 = " + member2);
        }
    }
}
