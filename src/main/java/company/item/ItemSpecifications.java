package company.item;

import company.item.model.Item;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class ItemSpecifications {

    public static Specification<Item> similarNames(String name) {
        return (root, query, cb) -> cb.like(root.get("name"), name);
    }

    public static Specification<Item> createdAfter(LocalDateTime date) {
        return (root, query, cb) -> cb.greaterThan(root.get("dateCreated"), date);
    }

}
