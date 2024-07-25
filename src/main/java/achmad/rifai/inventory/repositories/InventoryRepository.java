package achmad.rifai.inventory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import achmad.rifai.inventory.models.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

}
