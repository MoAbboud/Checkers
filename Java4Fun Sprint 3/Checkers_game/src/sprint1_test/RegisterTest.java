package sprint1_test;

import static org.junit.Assert.*;

import org.junit.Test;
import sprint1_prod.LoginController;

public class RegisterTest {

	@Test
	public void test() {
		
	}
	
	@Test
	public void SuccessfulRegistration() {
		LoginController obj = new LoginController();
		boolean output = obj.registerationUsername("Admin1");
		assertEquals(output, true);
	}
	
	@Test
	public void UnsuccessfulRegistration() {
		LoginController obj = new LoginController();
		boolean output = obj.registerationUsername("@dmin");
		assertEquals(output, false);
	}
	
	@Test
	public void SuccessfulRegistrationPassword() {
		LoginController obj = new LoginController();
		boolean output = obj.registerationPassword("Pass123$");
		assertEquals(output, true);
	}
	
	@Test
	public void UnsuccessfulRegistrationPassword() {
		LoginController obj = new LoginController();
		boolean output = obj.registerationPassword("Password");
		assertEquals(output, false);
	}
	
	
	@Test
	public void SuccessfulRegistrationFName() {
		LoginController obj = new LoginController();
		boolean output = obj.registerationFName("Jawa");
		assertEquals(output, true);
	}
	
	@Test
	public void UnsuccessfulRegistrationFName() {
		LoginController obj = new LoginController();
		boolean output = obj.registerationFName("1234");
		assertEquals(output, false);
	}
	
	@Test
	public void SuccessfulRegistrationLName() {
		LoginController obj = new LoginController();
		boolean output = obj.registerationLName("Enkh");
		assertEquals(output, true);
	}
	@Test
	public void UnsuccessfulRegistrationLName() {
		LoginController obj = new LoginController();
		boolean output = obj.registerationLName("1234");
		assertEquals(output, false);
	}
	
	@Test
	public void SuccessfulRegistrationEmail() {
		LoginController obj = new LoginController();
		boolean output = obj.registerationEmail("jenmh@umkc.edu");
		assertEquals(output, true);
	}
	
	@Test
	public void UnsuccessfulRegistrationEmail() {
		LoginController obj = new LoginController();
		boolean output = obj.registerationEmail("jenmh#umkc.edu");
		assertEquals(output, false);
	}
	
	@Test
	public void SuccessfulRegistrationPhonenumber() {
		LoginController obj = new LoginController();
		boolean output = obj.registerationPhonenumber("5106313627");
		assertEquals(output, true);
	}
	
	@Test
	public void UnsuccessfulRegistrationPhonenumber() {
		LoginController obj = new LoginController();
		boolean output = obj.registerationPhonenumber("510631361111127");
		assertEquals(output, false);
	}
	
	

}
