import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({})
public class AllTests {
	Main main = new Main();
	
	@Test
	public void testifNothingIsPassed() {
		String output=main.get_costs(null, -1, -1, -1);
		assertEquals(output, "Enter valid system Inputs");
	}
	
}
