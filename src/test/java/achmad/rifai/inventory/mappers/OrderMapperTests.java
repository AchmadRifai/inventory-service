package achmad.rifai.inventory.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import achmad.rifai.inventory.domains.OrderReq;
import achmad.rifai.inventory.domains.OrderRes;
import achmad.rifai.inventory.models.Order;

public class OrderMapperTests {

	@Test
	void testOrderReqToOrder() {
		assertEquals(ORDER.getQty(), OrderMapper.INSTANCE.orderReqToOrder(ORDER_REQ).getQty());
	}

	@Test
	void testOrderToOrderRes() {
		assertEquals(ORDER_RES, OrderMapper.INSTANCE.orderToOrderRes(ORDER));
	}

	public static final OrderReq ORDER_REQ = OrderReq.builder()
			.itemId(1L)
			.qty(5)
			.build();

	public static final Order ORDER = Order.builder()
			.id(1L)
			.itemId(ItemMapperTests.ITEM.getId())
			.orderNo("O1")
			.qty(ORDER_REQ.getQty())
			.build();

	public static final OrderRes ORDER_RES = OrderRes.builder()
			.itemId(ItemMapperTests.ITEM.getId())
			.orderNo("O1")
			.qty(ORDER.getQty())
			.build();

}
