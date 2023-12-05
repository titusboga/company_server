package company.item;

import company.item.model.Item;
import company.item.model.dto.ItemDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    ItemDTO toDto(Item item);

    Item fromDto(ItemDTO item);

}
