package achmad.rifai.inventory.models;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@Entity
@NoArgsConstructor
@Table(name = "item")
public class Item implements Serializable {

	private static final long serialVersionUID = -8304942107485048992L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 10, nullable = false)
	private String name;

	@Column(nullable = false)
	private Integer price;

	@Builder.Default
	@Column(nullable = false)
	private Integer stock = 0;

	@OneToMany(mappedBy = "item", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Inventory> inventories;

}
