package achmad.rifai.inventory.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import achmad.rifai.inventory.domains.EditItemReq;
import achmad.rifai.inventory.domains.ItemReq;
import achmad.rifai.inventory.domains.ItemRes;
import achmad.rifai.inventory.domains.PageRes;
import achmad.rifai.inventory.services.ItemService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService service;

	@GetMapping("/{id}")
	public ItemRes get(@PathVariable Long id) {
		return service.get(id);
	}

	@GetMapping
	public PageRes<ItemRes> list(@RequestParam(required = true) Integer page, @RequestParam(required = true) Integer limit) {
		return service.list(page, limit);
	}

	@PostMapping
	public ItemRes save(@RequestBody @Valid ItemReq req) {
		return service.save(req);
	}

	@PutMapping
	public ItemRes edit(@RequestBody @Valid EditItemReq req) {
		return service.edit(req);
	}

	@DeleteMapping("/{id}")
	public ItemRes delete(@PathVariable Long id) {
		return service.delete(id);
	}

}
