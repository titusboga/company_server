package company.item;

import company.item.model.dto.ItemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static company.UrlMapping.*;

@RestController
@RequestMapping(ITEMS)
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public List<ItemDTO> allItems() {
        return itemService.findAll();
    }

    @PostMapping
    public ItemDTO create(@RequestBody ItemDTO item) {
        return itemService.create(item);
    }

    @PatchMapping(ENTITY)
    public ItemDTO changeName(@PathVariable Long id, @RequestBody String newName) {
        return itemService.changeName(id, newName);
    }

    @PutMapping(ENTITY)
    public ItemDTO edit(@PathVariable Long id, @RequestBody ItemDTO item) {
        return itemService.edit(id, item);
    }

    @DeleteMapping(ENTITY)
    public void delete(@PathVariable Long id) {
        itemService.delete(id);
    }


    @GetMapping(ENTITY)
    public ItemDTO getItem(@PathVariable Long id) {
        return itemService.get(id);
    }

}
