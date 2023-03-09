package hello.core.member;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
