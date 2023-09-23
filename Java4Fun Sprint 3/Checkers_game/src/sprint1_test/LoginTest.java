package sprint1_test;
import junit.framework.TestCase;
import static org.junit.Assert.*;

import org.junit.Test;


import sprint1_prod.LoginController;

public class LoginTest {

	@Test
	public void SuccessfulTestLogin() {
		LoginController obj = new LoginController();
		boolean output = obj.loginUsername("Admin1");
		assertEquals(output, true);
	}
	
	@Test
	public void SuccessfulTestLoginPassword() {
		LoginController obj = new LoginController();
		boolean output = obj.loginPassword("Pass123$");
		assertEquals(output, true);
	}
	

	@Test
	public void UnsuccessfulTestLogin() {
		LoginController obj = new LoginController();
		boolean output = obj.loginUsername("@dmin1");
		assertEquals(output, false);
	}
	
	@Test
	public void UnsuccessfulTestLoginPassword() {
		LoginController obj = new LoginController();
		boolean output = obj.loginPassword("Pass1234");
		assertEquals(output, false);
	}

}
