package achmad.rifai.inventory.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import achmad.rifai.inventory.domains.InventoryReq;
import achmad.rifai.inventory.domains.InventoryRes;
import achmad.rifai.inventory.domains.InventoryType;
import achmad.rifai.inventory.models.Inventory;

public class InventoryMapperTests {

	@Test
	void testInventoryToInventoryRes() {
		assertEquals(INVENTORY_RES, InventoryMapper.INSTANCE.inventoryToInventoryRes(INVENTORY));
	}

	@Test
	void testInventoryReqToInventory() {
		assertEquals(INVENTORY2.getQty(), InventoryMapper.INSTANCE.inventoryReqToInventory(INVENTORY_REQ).getQty());
		assertEquals(INVENTORY2.getType(), InventoryMapper.INSTANCE.inventoryReqToInventory(INVENTORY_REQ).getType());
	}

	public static final InventoryReq INVENTORY_REQ = InventoryReq.builder()
			.itemId(1L)
			.qty(5)
			.type(InventoryType.T)
			.build();

	public static final Inventory INVENTORY2 = Inventory.builder()
			.qty(INVENTORY_REQ.getQty())
			.type(INVENTORY_REQ.getType())
			.build();

	public static final Inventory INVENTORY = Inventory.builder()
			.id(1L)
			.item(ItemMapperTests.ITEM)
			.qty(5)
			.type(InventoryType.T)
			.build();

	public static final InventoryRes INVENTORY_RES = InventoryRes.builder()
			.itemId(ItemMapperTests.ITEM.getId())
			.qty(INVENTORY.getQty())
			.type(INVENTORY.getType())
			.build();

}
