package achmad.rifai.inventory.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import achmad.rifai.inventory.domains.InventoryReq;
import achmad.rifai.inventory.domains.InventoryRes;
import achmad.rifai.inventory.models.Inventory;

@Mapper
public interface InventoryMapper {

	InventoryMapper INSTANCE = Mappers.getMapper(InventoryMapper.class);

	InventoryRes inventoryToInventoryRes(Inventory inventory);

	Inventory inventoryReqToInventory(InventoryReq req);

}
