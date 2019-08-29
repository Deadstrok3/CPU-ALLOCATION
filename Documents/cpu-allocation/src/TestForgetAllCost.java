import static org.junit.Assert.*;

import org.junit.Test;

public class TestForgetAllCost {

	@Test
	public void testMain() {
		Main main = new Main();
		String output=main.get_costs(null, -1, -1, -1);
		assertEquals(output, "Enter valid system Inputs");
	}

}
