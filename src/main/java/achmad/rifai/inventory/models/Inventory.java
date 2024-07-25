package achmad.rifai.inventory.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import achmad.rifai.inventory.domains.InventoryType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "inventory")
public class Inventory implements Serializable {

	private static final long serialVersionUID = -1355014532591722508L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JoinColumn(name = "item_id", nullable = false)
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Item item;

	@Column(nullable = false)
	private Integer qty;

	@Column(nullable = false)
	private InventoryType type;

	@Builder.Default
	@Column(nullable = false)
	private Timestamp time = Timestamp.valueOf(LocalDateTime.now());

}
