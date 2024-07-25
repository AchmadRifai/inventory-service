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

import achmad.rifai.inventory.domains.EditInventoryReq;
import achmad.rifai.inventory.domains.InventoryReq;
import achmad.rifai.inventory.domains.InventoryRes;
import achmad.rifai.inventory.domains.PageRes;
import achmad.rifai.inventory.services.InventoryService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

	@Autowired
	private InventoryService service;

	@GetMapping("/{id}")
	public InventoryRes get(@PathVariable Long id) {
		return service.get(id);
	}

	@GetMapping
	public PageRes<InventoryRes> list(@RequestParam(required = true) Integer page, @RequestParam(required = true) Integer limit) {
		return service.list(page, limit);
	}

	@PostMapping
	public InventoryRes save(@RequestBody @Valid InventoryReq req) {
		return service.save(req);
	}

	@PutMapping
	public InventoryRes edit(@RequestBody @Valid EditInventoryReq req) {
		return service.edit(req);
	}

	@DeleteMapping("/{id}")
	public InventoryRes delete(@PathVariable Long id) {
		return service.delete(id);
	}

}
