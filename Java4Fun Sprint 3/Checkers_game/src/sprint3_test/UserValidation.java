package sprint3_test;
import java.util.regex.*;


public class UserValidation {
	
	private String username;
	private String password;
	private String fname;
	private String lname;
	private String email;
	private String phoneNumber;
	
	
	public UserValidation() {	
	}
	
	public static String checkInputError(String username, String password, String fname, String lname, String email, String phoneNumber){
		String errorMessage ="";
		if (!isUserNameValid(username))
				errorMessage += "Invalid user name.\n";
		if (!isPasswordValid(password))
				errorMessage += "Invalid password.\n";	
		if (!isFirstNameValid(fname))
			errorMessage += "Invalid first name.\n";
		if (!isLastNameValid(lname))
			errorMessage += "Invalid last name.\n";
		if (!isEmailValid(email))
			errorMessage += "Invalid email.\n";
		if (!isPhoneNumberValid(phoneNumber))
			errorMessage += "Invalid phone number.\n";
		return errorMessage;
	}
	

	public static boolean isUserNameValid(String userName){
		Pattern pattern = Pattern.compile("^[a-zA-Z][a-zA-Z0-9]*$");
		Matcher matcher = pattern.matcher(userName);
		if (!matcher.matches()){
			return false;
		}
		return true;
	}

	
	public static boolean isPasswordValid(String password){
		Pattern pattern = Pattern.compile("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}");
	 	Matcher matcher = pattern.matcher(password);
	 	if (!matcher.matches()){
			return false;
		}
		return true;
	}
    


	
	public static boolean isFirstNameValid(String firstName){
		Pattern pattern = Pattern.compile("^[a-zA-Z]*$");
		Matcher matcher = pattern.matcher(firstName);
		if (!matcher.matches()){
			return false;
		}
		return true;
	}
	

	public static boolean isLastNameValid(String lastName){
		Pattern pattern = Pattern.compile("^[a-zA-Z]*$");
		Matcher matcher = pattern.matcher(lastName);
		if (!matcher.matches()){
			return false;
		}
		return true;
	}


	public static boolean isEmailValid(String email){
		Pattern pattern = Pattern.compile("^(.+)@(.+)$");
		Matcher matcher = pattern.matcher(email);
		if (!matcher.matches()){
			return false;
		}
		return true;
	}

	

	public static boolean isPhoneNumberValid(String phone){
		Pattern pattern = Pattern.compile("^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$");
		Matcher matcher = pattern.matcher(phone);
		if (!matcher.matches()){
		return false;
		}
		return true;
	}


}
