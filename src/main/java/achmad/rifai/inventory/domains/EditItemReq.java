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
public class EditItemReq implements Serializable {

	private static final long serialVersionUID = -2562971998990752534L;

	@NotNull
	@Positive
	private Long id;
	@NotNull
	private ItemReq changes;

}
