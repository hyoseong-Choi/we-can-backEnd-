package omg.wecan.util.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import omg.wecan.shop.entity.Item;
import omg.wecan.user.entity.User;

@Getter
@RequiredArgsConstructor
public class BuyItemEvent {
    private final User user;
    private final Item item;

}
