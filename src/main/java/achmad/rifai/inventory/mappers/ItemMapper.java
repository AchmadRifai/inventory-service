package achmad.rifai.inventory.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import achmad.rifai.inventory.domains.ItemReq;
import achmad.rifai.inventory.domains.ItemRes;
import achmad.rifai.inventory.models.Item;

@Mapper
public interface ItemMapper {

	ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);

	ItemRes itemToItemRes(Item item);

	Item itemReqToItem(ItemReq itemReq);

}
