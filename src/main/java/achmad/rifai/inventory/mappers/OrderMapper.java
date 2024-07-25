package achmad.rifai.inventory.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import achmad.rifai.inventory.domains.OrderReq;
import achmad.rifai.inventory.domains.OrderRes;
import achmad.rifai.inventory.models.Order;

@Mapper
public interface OrderMapper {

	OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

	OrderRes orderToOrderRes(Order order);

	Order orderReqToOrder(OrderReq req);

}
