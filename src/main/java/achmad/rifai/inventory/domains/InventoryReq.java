package achmad.rifai.inventory.domains;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class InventoryReq implements Serializable {

	private static final long serialVersionUID = 4401806775903923517L;

	@NotNull
	@Positive
	private Long itemId;

	@NotNull
	private InventoryType type;

	@NotNull
	@Positive
	private Integer qty;

}
