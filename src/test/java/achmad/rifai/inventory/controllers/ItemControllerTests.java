package achmad.rifai.inventory.controllers;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import achmad.rifai.inventory.domains.EditItemReq;
import achmad.rifai.inventory.domains.ErrorRes;
import achmad.rifai.inventory.domains.ItemReq;
import achmad.rifai.inventory.mappers.ItemMapperTests;
import achmad.rifai.inventory.repositories.ItemRepository;

@AutoConfigureMockMvc
@SpringBootTest
class ItemControllerTests {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ItemRepository repository;

	@Autowired
	private ObjectMapper mapper;

	@Test
	void testGetSuccess() throws Exception {
		when(repository.findById(anyLong())).thenReturn(Optional.of(ItemMapperTests.ITEM));
		mvc.perform(get("/item/1"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().json(mapper.writeValueAsString(ItemMapperTests.ITEM_RES)));
	}

	@Test
	void testGetNotFound() throws Exception {
		when(repository.findById(anyLong())).thenReturn(Optional.empty());
		mvc.perform(get("/item/1"))
			.andDo(print())
			.andExpect(status().isNotFound())
			.andExpect(content().json(mapper.writeValueAsString(ErrorRes.builder().message("Item 1 not found").build())));
	}

	@Test
	void testListSuccess() throws Exception {
		when(repository.findAll(PageRequest.of(0, 2))).thenReturn(new PageImpl<>(ItemMapperTests.ITEMS, PageRequest.of(0, 2), ItemMapperTests.ITEMS.size()));
		mvc.perform(get("/item?page=0&limit=2"))
			.andDo(print())
			.andExpect(status().isOk());
	}

	@Test
	void testListErrorNotFound() throws Exception {
		when(repository.findAll(PageRequest.of(0, 2))).thenReturn(new PageImpl<>(List.of(), PageRequest.of(0, 2), 0));
		mvc.perform(get("/item?page=0&limit=2"))
			.andDo(print())
			.andExpect(status().isNotFound());
	}

	@Test
	void testListErrorInvalidPage() throws Exception {
		mvc.perform(get("/item?page=-1&limit=2"))
			.andDo(print())
			.andExpect(status().isBadRequest());
	}

	@Test
	void testListErrorInvalidLimit() throws Exception {
		mvc.perform(get("/item?page=0&limit=0"))
			.andDo(print())
			.andExpect(status().isBadRequest());
	}

	@Test
	void testSaveInsertSuccess() throws Exception {
		when(repository.save(ItemMapperTests.ITEM)).thenReturn(ItemMapperTests.ITEM);
		mvc.perform(post("/item").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(ItemMapperTests.ITEM_REQ)))
			.andDo(print())
			.andExpect(status().isOk());
	}

	@Test
	void testSaveNameNull() throws Exception {
		mvc.perform(post("/item").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(ItemReq.builder().price(1).build())))
			.andDo(print())
			.andExpect(status().isBadRequest());
	}

	@Test
	void testSaveNameEmpty() throws Exception {
		mvc.perform(post("/item").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(ItemReq.builder().name("").price(1).build())))
			.andDo(print())
			.andExpect(status().isBadRequest());
	}

	@Test
	void testSaveNameBlank() throws Exception {
		mvc.perform(post("/item").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(ItemReq.builder().name("     ").price(1).build())))
			.andDo(print())
			.andExpect(status().isBadRequest());
	}

	@Test
	void testSavePriceNull() throws Exception {
		mvc.perform(post("/item").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(ItemReq.builder().name("Pen").build())))
			.andDo(print())
			.andExpect(status().isBadRequest());
	}

	@Test
	void testSavePriceZero() throws Exception {
		mvc.perform(post("/item").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(ItemReq.builder().name("Pen").price(0).build())))
			.andDo(print())
			.andExpect(status().isBadRequest());
	}

	@Test
	void testEditSuccess() throws Exception {
		when(repository.findById(anyLong())).thenReturn(Optional.of(ItemMapperTests.ITEM));
		when(repository.save(ItemMapperTests.ITEM)).thenReturn(ItemMapperTests.ITEM);
		mvc.perform(put("/item").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(ItemMapperTests.EDIT_ITEM_REQ)))
			.andDo(print())
			.andExpect(status().isOk());
	}

	@Test
	void testEditNotFound() throws Exception {
		when(repository.findById(anyLong())).thenReturn(Optional.empty());
		mvc.perform(put("/item").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(ItemMapperTests.EDIT_ITEM_REQ)))
			.andDo(print())
			.andExpect(status().isNotFound());
	}

	@Test
	void testEditIdNull() throws Exception {
		mvc.perform(put("/item").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(EditItemReq.builder()
				.changes(ItemMapperTests.ITEM_REQ)
				.build())))
			.andDo(print())
			.andExpect(status().isBadRequest());
	}

	@Test
	void testEditIdZero() throws Exception {
		mvc.perform(put("/item").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(EditItemReq.builder()
				.changes(ItemMapperTests.ITEM_REQ)
				.id(0L)
				.build())))
			.andDo(print())
			.andExpect(status().isBadRequest());
	}

	@Test
	void testEditChangeNull() throws Exception {
		mvc.perform(put("/item").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(EditItemReq.builder()
				.id(0L)
				.build())))
			.andDo(print())
			.andExpect(status().isBadRequest());
	}

	@Test
	void testDeleteSuccess() throws Exception {
		when(repository.findById(anyLong())).thenReturn(Optional.of(ItemMapperTests.ITEM));
		mvc.perform(delete("/item/1"))
			.andDo(print())
			.andExpect(status().isOk());
	}

	@Test
	void testDeleteNotFound() throws Exception {
		when(repository.findById(anyLong())).thenReturn(Optional.empty());
		mvc.perform(delete("/item/1"))
			.andDo(print())
			.andExpect(status().isNotFound());
	}

	@Test
	void testDeleteInvalid() throws Exception {
		mvc.perform(delete("/item/0"))
			.andDo(print())
			.andExpect(status().isBadRequest());
	}

}
