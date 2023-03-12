package hello.core.order;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    private MemberRepository memberRepository;//final - 값이 무조건 있어야한다.
    //    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();추상에도 의존하고 구체에도 의존하고있다 -> DIP위반!
    private DiscountPolicy discountPolicy;

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) { // 수정자주입은 선택적이거나 변경가능할떄 사용
        System.out.println("memberRepository = " + memberRepository);
        this.memberRepository = memberRepository;
    }

    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        System.out.println("discountPolicy = " + discountPolicy);
        this.discountPolicy = discountPolicy;
    }

    //    @Autowired
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);//파라미터로 받은 멤버ID로 저장소에서 찾는다
        int discountPrice = discountPolicy.discount(member, itemPrice);//파라미터로 받은 멤버가 VIP라면 할인

        return new Order(memberId, itemName, itemPrice, discountPrice);//주문생성
    }
}
