
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import com.formdev.flatlaf.FlatLightLaf;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Login_Screen extends JFrame implements ActionListener, KeyListener {

	private static final long serialVersionUID = -6976151037565376135L;

	public static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
	public static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;

	private static Login_Screen theInstance = null;

	private String fileName;
	private Scanner fileToken;

	private JTextField usernameField;
	private JPasswordField passwordField;

	private Login_Screen(String fileName) {

		this.fileName = fileName;

		setTitle("Login Screen");

		final int X_POS = SCREEN_WIDTH/3;
		final int Y_POS = SCREEN_HEIGHT/2 - 200;

		final int APPLICATION_WIDTH = SCREEN_WIDTH/3;
		final int APPLICATION_HEIGHT = SCREEN_HEIGHT/2;

		setLocation(new Point(X_POS, Y_POS));
		setSize(new Dimension(APPLICATION_WIDTH, APPLICATION_HEIGHT));
		getContentPane().setBackground(Color.BLACK);
		setResizable(false);

		try {
			setIconImage(ImageIO.read(new File("images/icons/LockIcon.png")));
		} catch (IOException e) {
			System.err.println("Can't find icon image!");
			return;
		}

		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		setLayout(null);

		JLabel titleLabel = new JLabel();
		titleLabel.setText("<html>L<br>O<br>G<br>I<br>N<br><br>P<br>O<br>R<br>T<br>A<br>L");
		titleLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
		titleLabel.setForeground(Color.BLUE);
		titleLabel.setBounds(new Rectangle(412, 25, 50, 300));
		add(titleLabel);

		JLabel userNameLabel = new JLabel();
		userNameLabel.setText("Username: ");
		userNameLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
		userNameLabel.setForeground(Color.BLUE);
		userNameLabel.setBounds(new Rectangle(25, 25, 125, 50));
		add(userNameLabel);

		usernameField = new JTextField();
		usernameField.setBounds(new Rectangle(150, 25, 200, 50));
		usernameField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
		add(usernameField);

		JLabel passwordLabel = new JLabel();
		passwordLabel.setText("Password: ");
		passwordLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
		passwordLabel.setForeground(Color.BLUE);
		passwordLabel.setBounds(new Rectangle(25, 125, 125, 50));
		add(passwordLabel);

		passwordField = new JPasswordField();
		passwordField.setBounds(new Rectangle(150, 125, 200, 50));
		passwordField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
		passwordField.addKeyListener(this);
		add(passwordField);

		Border border = BorderFactory.createLineBorder(Color.BLUE);
		JButton submitButton = new JButton();
		submitButton.setText("Submit");
		submitButton.setBounds(new Rectangle(150, 200, 200, 50));
		submitButton.setBackground(Color.BLACK);
		submitButton.setForeground(Color.BLUE);
		submitButton.setContentAreaFilled(false);
		submitButton.addActionListener(this);
		submitButton.setBorder(border);
		add(submitButton);

		JButton signupButton = new JButton();
		signupButton.setText("Sign Up");
		signupButton.setBounds(new Rectangle(150, 275, 200, 50));
		signupButton.setBackground(Color.BLACK);
		signupButton.setForeground(Color.BLUE);
		signupButton.setContentAreaFilled(false);
		signupButton.addActionListener(this);
		signupButton.setBorder(border);
		add(signupButton);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);

		if(checkIfAlreadyExist("lib/token")) {

			String token = getToken();
			if(!token.equals("")) {
				dispose();
				new Client_GUI(new User("", 0 + "", "", "", "", ""));
			}
		}
	}

	private String getToken() {//Archie

		try {
			return fileToken.nextLine();
		}catch (NoSuchElementException e) {
			return "";
		}
	}

	private boolean checkIfAlreadyExist(String string) {//Archie

		try {
			fileToken = new Scanner(new File(string));
		} catch (FileNotFoundException e) {
			return false;
		}
		return true;
	}

	public void actionPerformed(ActionEvent e) {

		String actionCommand = e.getActionCommand();
		if(actionCommand.equals("Submit")) {

			Controller accessController = Controller.Intialize(fileName);
			if(accessController.checkIfExists(usernameField.getText(), new String(passwordField.getPassword()))) {

				JOptionPane.showMessageDialog(this, "Success, you have successfully logged in!", "Success", JOptionPane.INFORMATION_MESSAGE, null);
				dispose();

				new Client_GUI(new User(usernameField.getText(), "", "", "", "", new String(passwordField.getPassword())));
			}else {

				JOptionPane.showMessageDialog(this, "Error, log in failure", "Error", JOptionPane.ERROR_MESSAGE, null);
			}
		}else if(actionCommand.equals("Sign Up")) {

			new Signup_Screen(fileName);
		}
	}

	public void keyTyped(KeyEvent e) {} //not used

	public void keyPressed(KeyEvent e) {

		if(e.getKeyCode() == KeyEvent.VK_ENTER)
			actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Submit"));
	}

	public void keyReleased(KeyEvent e) {} //not used

	public static Login_Screen Intialize(String fileName) {

		if(theInstance != null)
			return theInstance;
		else {

			theInstance = new Login_Screen(fileName);
			return theInstance;
		}
	}
}
