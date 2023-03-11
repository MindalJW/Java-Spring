package hello.core.order;

import org.springframework.stereotype.Component;


public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
