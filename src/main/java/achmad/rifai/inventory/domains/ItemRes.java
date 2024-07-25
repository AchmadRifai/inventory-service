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
public class ItemRes implements Serializable {

	private static final long serialVersionUID = -5241024881304880551L;

	private Long id;
	private String name;
	private Integer price;

}
