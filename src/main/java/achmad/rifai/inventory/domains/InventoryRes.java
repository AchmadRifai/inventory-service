package achmad.rifai.inventory.domains;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class InventoryRes implements Serializable {

	private static final long serialVersionUID = 4401806775903923517L;

	private Long itemId;

	private InventoryType type;

	private Integer qty;

}
