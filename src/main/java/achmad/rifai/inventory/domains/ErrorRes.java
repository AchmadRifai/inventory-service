package achmad.rifai.inventory.domains;

import java.io.Serializable;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ErrorRes implements Serializable {

	private static final long serialVersionUID = 1495482123141018824L;

	private String message;
	private Map<String, String> messages;

}
