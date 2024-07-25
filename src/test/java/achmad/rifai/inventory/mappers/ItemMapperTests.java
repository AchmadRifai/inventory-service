package achmad.rifai.inventory.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import achmad.rifai.inventory.domains.EditItemReq;
import achmad.rifai.inventory.domains.ItemReq;
import achmad.rifai.inventory.domains.ItemRes;
import achmad.rifai.inventory.models.Item;

public class ItemMapperTests {

	@Test
	void testItemToItemRes() {
		assertEquals(ITEM_RES, ItemMapper.INSTANCE.itemToItemRes(ITEM));
	}

	@Test
	void testItemReqToItem() {
		assertEquals(ITEM, ItemMapper.INSTANCE.itemReqToItem(ITEM_REQ));
	}

	public static final ItemReq ITEM_REQ = ItemReq.builder()
			.name("Pen")
			.price(5)
			.build();

	public static final EditItemReq EDIT_ITEM_REQ = EditItemReq.builder()
			.changes(ITEM_REQ)
			.id(1L)
			.build();

	public static final Item ITEM = Item.builder()
			.name(ITEM_REQ.getName())
			.stock(0)
			.price(ITEM_REQ.getPrice())
			.build();

	public static final ItemRes ITEM_RES = ItemRes.builder()
			.id(ITEM.getId())
			.name(ITEM.getName())
			.price(ITEM.getPrice())
			.build();

	public static final List<Item> ITEMS = List.of(ITEM, ITEM, ITEM);

}
