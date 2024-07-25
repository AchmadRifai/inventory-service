package achmad.rifai.inventory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import achmad.rifai.inventory.models.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
