package achmad.rifai.inventory.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import achmad.rifai.inventory.domains.EditItemReq;
import achmad.rifai.inventory.domains.ItemReq;
import achmad.rifai.inventory.domains.ItemRes;
import achmad.rifai.inventory.domains.PageRes;
import achmad.rifai.inventory.exceptions.NoDataException;
import achmad.rifai.inventory.mappers.ItemMapper;
import achmad.rifai.inventory.repositories.ItemRepository;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Service
public class ItemService {

	@Autowired
	private ItemRepository repository;

	public ItemRes get(Long id) {
		return repository.findById(id)
				.map(item -> ItemMapper.INSTANCE.itemToItemRes(item))
				.orElseThrow(() -> new NoDataException(String.format("Item %s not found", id)));
	}

	public PageRes<ItemRes> list(@NotNull @PositiveOrZero Integer page, @NotNull @Positive Integer limit) {
		final var paged = repository.findAll(PageRequest.of(page, limit));
		if (!paged.hasContent()) throw new NoDataException("Item is empty");
		return new PageRes<>(
				paged.getContent().stream().map(item -> ItemMapper.INSTANCE.itemToItemRes(item)).toList(), 
				page, 
				limit, 
				paged.getTotalPages(), 
				Integer.parseInt(String.valueOf(paged.getTotalElements()))
			);
	}

	@Transactional
	public ItemRes save(ItemReq req) {
		return ItemMapper.INSTANCE.itemToItemRes(repository.save(ItemMapper.INSTANCE.itemReqToItem(req)));
	}

	@Transactional
	public ItemRes edit(EditItemReq req) {
		final var model = repository.findById(req.getId()).orElseThrow(() -> new NoDataException(String.format("Item %s not found", req.getId())));
		final var changes = req.getChanges();
		model.setName(changes.getName());
		model.setPrice(changes.getPrice());
		return ItemMapper.INSTANCE.itemToItemRes(repository.save(model));
	}

	@Transactional
	public ItemRes delete(@NotNull @Positive Long id) {
		if (id < 1) throw new IllegalArgumentException(String.format("%s is illegal", id));
		final var model = repository.findById(id).orElseThrow(() -> new NoDataException(String.format("Item %s not found", id)));
		repository.delete(model);
		return ItemMapper.INSTANCE.itemToItemRes(model);
	}

}
