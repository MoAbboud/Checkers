package sprint3_prod;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sprint3_test.UserValidation;

public class LoginController {
	

	@FXML
    private AnchorPane loginPane;
    
    @FXML
    private AnchorPane registerPane;

    @FXML
    private Button btn_Play;

    @FXML
    private Button btn_login1;

    @FXML
    private Button btn_login2;

    @FXML
    private Button btn_register;

    @FXML
    private ToggleGroup mode;

    @FXML
    private RadioButton radioBtn_multiplayer;

    @FXML
    private RadioButton radioBtn_computer;

    @FXML
    private TextField txtBox_Password1;

    @FXML
    private TextField txtBox_Password2;

    @FXML
    public TextField txtBox_Player1;

    @FXML
    public TextField txtBox_Player2;

    @FXML
    private TextField txtBox_email;

    @FXML
    private TextField txtBox_fname;

    @FXML
    private TextField txtBox_lname;

    @FXML
    private TextField txtBox_password;

    @FXML
    private TextField txtBox_phoneNumber;

    @FXML
    private TextField txtBox_repassword;

    @FXML
    private TextField txtBox_username;
    
    @FXML
    public void actionOnClickRegisterButton(ActionEvent event) {
    	//after clicking registration button, call registerPaneShow which will hide login section and show the registeration fields
    	registerPaneShow();
    }
    
    public boolean stateOnePlayer = false;
    public String playerOneName = "";
    public String playerTwoName = "";
    public int playerOneWin;
    public int playerOne;
    public int playerTwo;
    public int playerOneLoss;
    public int playerTwoWin;
    public int playerTwoLoss;

    @FXML
    public void selectMode(ActionEvent event) {
    	if(radioBtn_computer.isSelected()) {
    		txtBox_Password1.setVisible(true);
    		txtBox_Player1.setVisible(true);
    		btn_login1.setVisible(true);
    		txtBox_Password2.setVisible(false);
    		txtBox_Player2.setVisible(false);
    		btn_login2.setVisible(false);    		
    	}
    	else {
    		txtBox_Password1.setVisible(true);
    		txtBox_Player1.setVisible(true);
    		btn_login1.setVisible(true);
    		stateOnePlayer = true;
    		txtBox_Password2.setVisible(true);
    		txtBox_Player2.setVisible(true);
    		btn_login2.setVisible(true);
    	}    	
    }
    
    public void loginPaneShow() {
    	loginPane.setVisible(true);
    	registerPane.setVisible(false);
    	radioBtn_computer.setVisible(true);
    	radioBtn_multiplayer.setVisible(true);
    	
    }
    
    public void registerPaneShow() {
    	registerPane.setVisible(true);
    	loginPane.setVisible(false);
    	radioBtn_computer.setVisible(false);
    	radioBtn_multiplayer.setVisible(false);
    }

    
    Connection connection = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    boolean btn1_clicked, btn2_clicked = false;
    Connection connection2 = null;
    ResultSet rs2 = null;
    PreparedStatement pst2 = null;
    
    public void playersLoggedIn() {
    	if (radioBtn_computer.isSelected()) { 
    		if (btn1_clicked == true) btn_Play.setDisable(false);
    	}
    	else {
    		if( btn1_clicked == true && btn2_clicked == true) btn_Play.setDisable(false);
    	}
    }
    
    @FXML
    private void actionOnClickLoginButton1(ActionEvent event) throws Exception{
    	
    connection = JavaConnection2SQL.ConnectDB();
    //checking user name 1 and password query from table via connection class
    String sql = "Select * from Player where P_UNAME = ? and P_PASSWORD = HASHBYTES('sha2_512', ?)";
    	try {
    		pst = connection.prepareStatement(sql);
    		pst.setString(1, txtBox_Player1.getText());
    		pst.setString(2, txtBox_Password1.getText());
    		rs = pst.executeQuery();
    		if (rs.next()) {
    			JOptionPane.showMessageDialog(null, "Username and password is correct");
    			btn_login1.setText("Logged in");
    			btn_login1.setStyle("-fx-background-color: #00FF00; ");
    			btn_login1.setDisable(true);
    			playerOneWin = rs.getInt("P_WIN");
    			playerOneLoss = rs.getInt("P_LOSS");		 
    			playerOneName = rs.getString("P_FNAME") + " " + rs.getString("P_LNAME");
    			playerOne = rs.getInt("P_ID");
    			btn1_clicked = true;
    			
    			playersLoggedIn();
    		} else {
    			JOptionPane.showMessageDialog(null, "Username or password is wrong");	
    		}
    		
    		
    		
    	} catch (Exception e) {
    		JOptionPane.showMessageDialog(null, e);
    	}
    }
    
    
    @FXML
    private void actionOnClickLoginButton2(ActionEvent event) throws Exception{
    	
    connection = JavaConnection2SQL.ConnectDB();
    //checking user name 2 and password query from table via connection class

    String sql = "Select * from Player where P_UNAME = ? and P_PASSWORD = HASHBYTES('sha2_512', ?)";
    try {
    		pst2 = connection.prepareStatement(sql);
    		pst2.setString(1, txtBox_Player2.getText());
    		pst2.setString(2, txtBox_Password2.getText());
    		rs2 = pst2.executeQuery();
    		if (rs2.next()) {
    			JOptionPane.showMessageDialog(null, "Username and password is correct");
    			btn_login2.setText("Logged in");
    			btn_login2.setStyle("-fx-background-color: #00FF00; ");
    			btn_login2.setDisable(true);
    			playerTwoWin = rs2.getInt("P_WIN");
    			playerTwoLoss = rs2.getInt("P_LOSS");		 
    			playerTwoName = rs2.getString("P_FNAME") + " " + rs2.getString("P_LNAME");
    			playerTwo = rs2.getInt("P_ID");
    			btn2_clicked = true;
    			playersLoggedIn();
    		} else {
    			JOptionPane.showMessageDialog(null, "Username or password is wrong");	
    		}
    		
    		
    		
    	} catch (Exception e) {
     		JOptionPane.showMessageDialog(null, e);
    	}
    }
  
    
    @FXML
    public void actionOnClickLoginButton(ActionEvent event) throws IOException {
    	//after logged in both players load java fx form based on multiplayer or single player
    	btn_Play.getScene().getWindow().hide();
    	//System.out.println("S");
    	//Stage stage = new Stage();
    	 FXMLLoader fxmlLoader = new FXMLLoader();
    	if (radioBtn_computer.isSelected()) {
    		fxmlLoader.setLocation(getClass().getResource("/sprint3_prod/CheckersBoardComputer.fxml"));
    		Parent root = fxmlLoader.load();
    		Stage mainStage = new Stage();
    		CheckersGUIComputer checkersGUI = fxmlLoader.getController();
    		checkersGUI.playerNames(playerOneName, playerOneWin, playerOneLoss, playerTwoName, playerTwoWin, playerTwoLoss, playerOne, playerTwo);
    		Scene scene = new Scene(root);
    		mainStage.setScene(scene);
    		mainStage.setTitle("Team project - Checkers game");
    		mainStage.show();
    	} 
    	
    	else {
    	
    		fxmlLoader.setLocation(getClass().getResource("/sprint3_prod/CheckersBoard.fxml"));
    		Parent root = fxmlLoader.load();
    		Stage mainStage = new Stage();
    		CheckersGUI checkersGUI = fxmlLoader.getController();
    		checkersGUI.playerNames(playerOneName, playerOneWin, playerOneLoss, playerTwoName, playerTwoWin, playerTwoLoss,playerOne, playerTwo);
    		Scene scene = new Scene(root);
    		mainStage.setScene(scene);
    		mainStage.setTitle("Team project - Checkers game");
    		mainStage.show();
    	} 
		
		
	
    }

	public void registerNewAccount(ActionEvent event) {
		//creating a connection and register new player for the application
    	 connection = JavaConnection2SQL.ConnectDB();
    	 String sql = "INSERT INTO PLAYER (P_FNAME, P_LNAME, P_UNAME, P_EMAIL, P_PNUMBER, P_PASSWORD) VALUES (?, ?, ?, ?, ?, hashbytes('sha2_512', ?))";
    	 try {
    		 pst = connection.prepareStatement(sql);
    		 pst.setString(1, txtBox_fname.getText());
     		 pst.setString(2, txtBox_lname.getText());
     		 pst.setString(3, txtBox_username.getText());
    		 pst.setString(4, txtBox_email.getText());
    		 pst.setString(5, txtBox_phoneNumber.getText());
    		 pst.setString(6, txtBox_password.getText());    		 
    		 pst.execute();
    		 JOptionPane.showMessageDialog(null, "Successfully registered.");	
    		 loginPaneShow();
     	
    		 
    		 
    	 } catch (Exception e) {
    			JOptionPane.showMessageDialog(null, e + " Not registered. Please try again.");
    	 }
    }
    //checking login and registration criteria via certain validation.
    public boolean loginUsername(String username) {
    	UserValidation userValidation = new UserValidation();
    		if(userValidation.isUserNameValid(username)) return true;   
    		return false;
    }
    
    public boolean loginPassword(String password) {
    	UserValidation userValidation = new UserValidation();
    		if(userValidation.isPasswordValid(password)) return true;   
    		return false;
    }
    
    public boolean registerationUsername(String userName) {
    	UserValidation userValidation = new UserValidation();
    		if(userValidation.isUserNameValid(userName)) return true;   
    		return false;
    }
    
    public boolean registerationPassword(String password) {
    	UserValidation userValidation = new UserValidation();
    		if(userValidation.isPasswordValid(password)) return true;   
    		return false;
    }
    
    public boolean registerationFName(String firstName) {
    	UserValidation userValidation = new UserValidation();
    		if(userValidation.isFirstNameValid(firstName)) return true;   
    		return false;
    }
    
    public boolean registerationLName(String lastName) {
    	UserValidation userValidation = new UserValidation();
    		if(userValidation.isLastNameValid(lastName)) return true;   
    		return false;
    }
    
    public boolean registerationEmail(String email) {
    	UserValidation userValidation = new UserValidation();
    		if(userValidation.isEmailValid(email)) return true;   
    		return false;
    }
    
    
    public boolean registerationPhonenumber(String email) {
    	UserValidation userValidation = new UserValidation();
    		if(userValidation.isPhoneNumberValid(email)) return true;   
    		return false;
    }
}
 