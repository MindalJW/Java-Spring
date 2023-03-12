package hello.core.test.scan;

import hello.core.AutoAppConfig;
import hello.core.member.MemberService;
import hello.core.order.OrderService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoAppConfigTest {

    @Test
    void basicScan() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);//싱글톤 컨테이너 관리해줌

        MemberService bean = ac.getBean(MemberService.class);
        OrderService orderService = ac.getBean(OrderService.class);
    }
}

