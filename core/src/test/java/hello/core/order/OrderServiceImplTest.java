package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    @Test
    void createOrder() {//생성자 주입으로 의존관계를 자동주입하게 되면 자바코드로 순수하게 테스트코드를 짤때 주입해야하는 의존관계가 뭐였는지 알기쉬운 장점이있다.
        //final키워드를 사용하여 생성자 누락을 막을수있다. 요약하자면 생성자 주입을 사용하면 불변, 누락의 장점이 있다.
        MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();
        memoryMemberRepository.save(new Member(1L,"Jiwon", Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(memoryMemberRepository, new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "memberA", 10000);
        System.out.println("order = " + order);
        org.assertj.core.api.Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}