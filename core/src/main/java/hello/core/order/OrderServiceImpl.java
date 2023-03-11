package hello.core.order;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("service")
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    //    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();추상에도 의존하고 구체에도 의존하고있다 -> DIP위반!
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);//파라미터로 받은 멤버ID로 저장소에서 찾는다
        int discountPrice = discountPolicy.discount(member, itemPrice);//파라미터로 받은 멤버가 VIP라면 할인

        return new Order(memberId, itemName, itemPrice, discountPrice);//주문생성
    }
}
