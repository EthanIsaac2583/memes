package kz.ruanjian.memed.mapper;

import kz.ruanjian.memed.util.Item;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface ItemMapper {

  default <T> Item<T> toItem(Page<T> page) {
    Item<T> item = new Item<>();

    if (!page.getContent().isEmpty()) {
      item.setContent(page.getContent().get(0));
    }

    item.setTotalItems(page.getTotalPages());
    item.setNumber(page.getNumber() + 1);
    item.setHasPrevious(page.hasPrevious());
    item.setHasNext(page.hasNext());
    item.setLast(page.isLast());

    return item;
  }
}
