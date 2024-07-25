package achmad.rifai.inventory.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import achmad.rifai.inventory.domains.EditInventoryReq;
import achmad.rifai.inventory.domains.InventoryReq;
import achmad.rifai.inventory.domains.InventoryRes;
import achmad.rifai.inventory.domains.PageRes;
import achmad.rifai.inventory.exceptions.NoDataException;
import achmad.rifai.inventory.mappers.InventoryMapper;
import achmad.rifai.inventory.repositories.InventoryRepository;
import achmad.rifai.inventory.repositories.ItemRepository;

@Service
public class InventoryService {

	@Autowired
	private InventoryRepository repository;

	@Autowired
	private ItemRepository itemRepository;

	public InventoryRes get(Long id) {
		return repository.findById(id)
				.map(inventory -> {
					final var result = InventoryMapper.INSTANCE.inventoryToInventoryRes(inventory);
					result.setItemId(inventory.getItem().getId());
					return result;
				})
				.orElseThrow(() -> new NoDataException(String.format("Inventory %d not found", id)));
	}

	public PageRes<InventoryRes> list(Integer page, Integer limit) {
		final var paged = repository.findAll(PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "time")));
		if (!paged.hasContent()) throw new NoDataException("Inventory is empty");
		return new PageRes<>(
				paged.getContent().stream().map(inventory -> {
					final var result = InventoryMapper.INSTANCE.inventoryToInventoryRes(inventory);
					result.setItemId(inventory.getItem().getId());
					return result;
				}).toList(), 
				page, 
				limit, 
				paged.getTotalPages(), 
				Integer.parseInt(String.valueOf(paged.getTotalElements()))
			);
	}

	@Transactional
	public InventoryRes save(InventoryReq req) {
		var item = itemRepository.findById(req.getItemId()).orElseThrow(() -> new NoDataException(String.format("Item %d not found", req.getItemId())));
		item.setStock(item.getStock() + req.getQty());
		final var inventory = InventoryMapper.INSTANCE.inventoryReqToInventory(req);
		inventory.setItem(item);
		item = itemRepository.save(item);
		final var result = InventoryMapper.INSTANCE.inventoryToInventoryRes(repository.save(inventory));
		result.setItemId(item.getId());
		return result;
	}

	@Transactional
	public InventoryRes edit(EditInventoryReq req) {
		final var changes = req.getChanges();
		final var inventory = repository.findById(req.getId()).orElseThrow(() -> new NoDataException(String.format("Inventory %d not found", req.getId())));
		var item = inventory.getItem();
		item.setStock((item.getStock() - inventory.getQty()) + changes.getQty());
		inventory.setQty(changes.getQty());
		inventory.setType(changes.getType());
		item = itemRepository.save(item);
		final var result = InventoryMapper.INSTANCE.inventoryToInventoryRes(repository.save(inventory));
		result.setItemId(item.getId());
		return result;
	}

	@Transactional
	public InventoryRes delete(Long id) {
		final var inventory = repository.findById(id).orElseThrow(() -> new NoDataException(String.format("Inventory %d not found", id)));
		var item = inventory.getItem();
		item.setStock(item.getStock() - inventory.getQty());
		item = itemRepository.save(item);
		repository.delete(inventory);
		final var result = InventoryMapper.INSTANCE.inventoryToInventoryRes(inventory);
		result.setItemId(item.getId());
		return result;
	}

}
