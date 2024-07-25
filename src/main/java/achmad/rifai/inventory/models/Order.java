package achmad.rifai.inventory.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "orders")
public class Order implements Serializable {

	private static final long serialVersionUID = 4418017868522801102L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private Long itemId;

	@Column(length = 12)
	private String orderNo;

	@Column(nullable = false)
	private Integer qty;

	@Builder.Default
	@Column(nullable = false)
	private Timestamp time = Timestamp.valueOf(LocalDateTime.now());

}
