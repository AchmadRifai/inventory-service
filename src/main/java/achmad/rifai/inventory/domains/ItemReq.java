package achmad.rifai.inventory.domains;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
public class ItemReq implements Serializable {

	private static final long serialVersionUID = 4459337843735056123L;

	@NotNull
	@NotEmpty
	@NotBlank
	private String name;
	@NotNull
	@Positive
	private Integer price;

}
