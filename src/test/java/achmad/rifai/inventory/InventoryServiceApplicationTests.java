package achmad.rifai.inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class InventoryServiceApplicationTests {

	@Test
	void contextLoads() {
		final var value = 1 + 1;
		assertEquals(2, value);
	}

}
