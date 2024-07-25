package achmad.rifai.inventory.domains;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PageRes<T> implements Serializable {

	private static final long serialVersionUID = -2548616623919179990L;

	private transient List<T> datas;
	private Integer page;
	private Integer limit;
	private Integer pageCount;
	private Integer count;

}
