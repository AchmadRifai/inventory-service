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

import achmad.rifai.inventory.domains.EditOrderReq;
import achmad.rifai.inventory.domains.OrderReq;
import achmad.rifai.inventory.domains.OrderRes;
import achmad.rifai.inventory.domains.PageRes;
import achmad.rifai.inventory.services.OrderService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService service;

	@GetMapping("/{id}")
	public OrderRes get(@PathVariable Long id) {
		return service.get(id);
	}

	@GetMapping
	public PageRes<OrderRes> list(@RequestParam(required = true) Integer page, @RequestParam(required = true) Integer limit) {
		return service.list(page, limit);
	}

	@PostMapping
	public OrderRes save(@RequestBody @Valid OrderReq req) {
		return service.save(req);
	}

	@PutMapping
	public OrderRes edit(@RequestBody @Valid EditOrderReq req) {
		return service.edit(req);
	}

	@DeleteMapping("/{id}")
	public OrderRes delete(@PathVariable Long id) {
		return service.delete(id);
	}

}
