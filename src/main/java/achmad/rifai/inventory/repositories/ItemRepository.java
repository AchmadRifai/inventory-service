package achmad.rifai.inventory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import achmad.rifai.inventory.models.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
