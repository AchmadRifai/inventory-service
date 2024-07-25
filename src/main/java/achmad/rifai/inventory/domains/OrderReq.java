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
public class OrderReq implements Serializable {

	private static final long serialVersionUID = -484436415970280041L;

	@NotNull
	@Positive
	private Long itemId;

	@NotNull
	@Positive
	private Integer qty;

}
