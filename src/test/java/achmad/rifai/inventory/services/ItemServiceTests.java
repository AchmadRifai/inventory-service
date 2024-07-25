package achmad.rifai.inventory.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import achmad.rifai.inventory.domains.PageRes;
import achmad.rifai.inventory.exceptions.NoDataException;
import achmad.rifai.inventory.mappers.ItemMapperTests;
import achmad.rifai.inventory.repositories.ItemRepository;

@SpringBootTest
public class ItemServiceTests {

	@MockBean
	private ItemRepository repository;

	@Autowired
	private ItemService service;

	@Test
	void testGetSuccess() {
		when(repository.findById(anyLong())).thenReturn(Optional.of(ItemMapperTests.ITEM));
		final var actual = service.get(1L);
		assertEquals(ItemMapperTests.ITEM_RES, actual);
	}

	@Test
	void testGetNotFound() {
		when(repository.findById(anyLong())).thenReturn(Optional.empty());
		assertThrows(NoDataException.class, () -> service.get(1L));
	}

	@Test
	void testListSuccess() {
		when(repository.findAll(PageRequest.of(0, 2))).thenReturn(new PageImpl<>(ItemMapperTests.ITEMS, PageRequest.of(0, 2), ItemMapperTests.ITEMS.size()));
		assertEquals(new PageRes<>(List.of(ItemMapperTests.ITEM_RES, ItemMapperTests.ITEM_RES), 0, 2, 2, ItemMapperTests.ITEMS.size()), service.list(0, 2));
	}

	@Test
	void testListErrorNotFound() {
		when(repository.findAll(PageRequest.of(0, 2))).thenReturn(new PageImpl<>(List.of(), PageRequest.of(0, 2), 0));
		assertThrows(NoDataException.class, () -> service.list(0, 2));
	}

	@Test
	void testListErrorInvalidPage() {
		assertThrows(IllegalArgumentException.class, () -> service.list(-1, 2));
	}

	@Test
	void testListErrorInvalidLimit() {
		assertThrows(IllegalArgumentException.class, () -> service.list(0, 0));
	}

	@Test
	void testSaveInsertSuccess() {
		when(repository.save(ItemMapperTests.ITEM)).thenReturn(ItemMapperTests.ITEM);
		assertEquals(ItemMapperTests.ITEM_RES, service.save(ItemMapperTests.ITEM_REQ));
	}

	@Test
	void testEditSuccess() {
		when(repository.findById(anyLong())).thenReturn(Optional.of(ItemMapperTests.ITEM));
		when(repository.save(ItemMapperTests.ITEM)).thenReturn(ItemMapperTests.ITEM);
		assertEquals(ItemMapperTests.ITEM_RES, service.edit(ItemMapperTests.EDIT_ITEM_REQ));
	}

	@Test
	void testEditNotFound() {
		when(repository.findById(anyLong())).thenReturn(Optional.empty());
		assertThrows(NoDataException.class, () -> service.edit(ItemMapperTests.EDIT_ITEM_REQ));
	}

	@Test
	void testDeleteSuccess() {
		when(repository.findById(anyLong())).thenReturn(Optional.of(ItemMapperTests.ITEM));
		assertEquals(ItemMapperTests.ITEM_RES, service.delete(1L));
	}

	@Test
	void testDeleteNotFound() {
		when(repository.findById(anyLong())).thenReturn(Optional.empty());
		assertThrows(NoDataException.class, () -> service.delete(1L));
	}

	@Test
	void testDeleteInvalid() {
		assertThrows(IllegalArgumentException.class, () -> service.delete(0L));
	}

}
