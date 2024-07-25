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
public class OrderRes implements Serializable {

	private static final long serialVersionUID = -6314535409833737882L;

	private String orderNo;
	private Long itemId;
	private Integer qty;

}
