package hello.springmvc1service.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>(); // 멀티 쓰레드 환경에서는 ConcurrentHashMap
    private static long sequence = 0L;

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId, Item updatePraram) {
        Item findItem = findById(itemId);
        findItem.setItemName(updatePraram.getItemName());
        findItem.setPrice(updatePraram.getPrice());
        findItem.setQuantity(updatePraram.getQuantity()); // 프로젝트 커지면 아이템 파라미터 객체 따로 만드는게 좋음
    }

    public void clearStore() {
        store.clear();
    }
}
