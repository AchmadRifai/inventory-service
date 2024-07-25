package achmad.rifai.inventory.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import achmad.rifai.inventory.domains.EditOrderReq;
import achmad.rifai.inventory.domains.OrderReq;
import achmad.rifai.inventory.domains.OrderRes;
import achmad.rifai.inventory.domains.PageRes;
import achmad.rifai.inventory.exceptions.InsufficientException;
import achmad.rifai.inventory.exceptions.NoDataException;
import achmad.rifai.inventory.mappers.OrderMapper;
import achmad.rifai.inventory.repositories.ItemRepository;
import achmad.rifai.inventory.repositories.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;

	@Autowired
	private ItemRepository itemRepository;

	public OrderRes get(Long id) {
		return repository.findById(id)
				.map(order -> OrderMapper.INSTANCE.orderToOrderRes(order))
				.orElseThrow(() -> new NoDataException(String.format("Order %s not found", id)));
	}

	public PageRes<OrderRes> list(Integer page, Integer limit) {
		final var paged = repository.findAll(PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "time")));
		if (!paged.hasContent()) throw new NoDataException("Inventory is empty");
		return new PageRes<>(
				paged.getContent().stream().map(order -> OrderMapper.INSTANCE.orderToOrderRes(order)).toList(), 
				page, 
				limit, 
				paged.getTotalPages(), 
				Integer.parseInt(String.valueOf(paged.getTotalElements()))
			);
	}

	@Transactional
	public OrderRes save(OrderReq req) {
		var item = itemRepository.findById(req.getItemId()).orElseThrow(() -> new NoDataException(String.format("Item %s not found", req.getItemId())));
		if (item.getStock() < req.getQty()) throw new InsufficientException("Insufficient Stock");
		var order = OrderMapper.INSTANCE.orderReqToOrder(req);
		order.setItemId(req.getItemId());
		order = repository.save(order);
		order.setOrderNo("O"+order.getId());
		item.setStock(item.getStock() - req.getQty());
		item = itemRepository.save(item);
		final var result = OrderMapper.INSTANCE.orderToOrderRes(order);
		result.setItemId(item.getId());
		return result;
	}

	@Transactional
	public OrderRes edit(EditOrderReq req) {
		final var changes = req.getChanges();
		final var order = repository.findById(req.getId()).orElseThrow(() -> new NoDataException(String.format("Order %s not found", req.getId())));
		var item = itemRepository.findById(order.getItemId()).orElseThrow(() -> new NoDataException(String.format("Item %s not found", order.getItemId())));
		if (item.getStock() + order.getQty() < changes.getQty()) throw new InsufficientException("Insufficient Stock");
		item.setStock(item.getStock() + order.getQty() - changes.getQty());
		order.setQty(changes.getQty());
		item = itemRepository.save(item);
		final var result = OrderMapper.INSTANCE.orderToOrderRes(order);
		result.setItemId(item.getId());
		return result;
	}

	@Transactional
	public OrderRes delete(Long id) {
		final var order = repository.findById(id).orElseThrow(() -> new NoDataException(String.format("Order %s not found", id)));
		var item = itemRepository.findById(order.getItemId()).orElseThrow(() -> new NoDataException(String.format("Item %s not found", order.getItemId())));
		item.setStock(item.getStock() + order.getQty());
		repository.delete(order);
		item = itemRepository.save(item);
		final var result = OrderMapper.INSTANCE.orderToOrderRes(order);
		result.setItemId(item.getId());
		return result;
	}

}
