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
public class EditOrderReq implements Serializable {

	private static final long serialVersionUID = -641459003687326355L;

	@NotNull
	@Positive
	private Long id;

	@NotNull
	private OrderReq changes;

}
