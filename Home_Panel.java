import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JLabel;

public class Home_Panel extends JPanel{

	private static final long serialVersionUID = 1993992822141318219L;

	public Home_Panel() {
		this.setLayout(null);
		JPanel firstHalf = new JPanel();
		firstHalf.setLayout(new GridLayout(1, 3));
		firstHalf.setBounds(25, 25, 955, 350); //this must be done when layout is null
		//add headers and paragraphs as normal to boxlayout panels
		//add boxlayout panels to firstHalf

		JPanel secondHalf = new JPanel();
		secondHalf.setLayout(new GridLayout(1, 2));
		secondHalf.setBounds(25, 375, 955, 350); //this must be done when layout is null
		// grid w 1r, 3c; 3c have new jPanels w box layout set; box layout
		
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		panel1.setLayout(new BoxLayout(panel1,BoxLayout.Y_AXIS));
		panel2.setLayout(new BoxLayout(panel2,BoxLayout.Y_AXIS));
		panel3.setLayout(new BoxLayout(panel3,BoxLayout.Y_AXIS));		
		
		JTextArea textArea1 = new JTextArea(2,20);
		textArea1.setColumns(1);
		textArea1.setRows(3);
		textArea1.setWrapStyleWord(true);
		textArea1.setLineWrap(true);
		textArea1.setFont(new Font("Arial", Font.TRUETYPE_FONT, 18));
		textArea1.setEditable(false);
		
		JTextArea textArea2 = new JTextArea(2,20);
		textArea2.setColumns(1);
		textArea2.setRows(3);
		textArea2.setWrapStyleWord(true);
		textArea2.setLineWrap(true);
		textArea2.setFont(new Font("Arial", Font.TRUETYPE_FONT, 18));
		textArea2.setEditable(false);

		JTextArea textArea3 = new JTextArea(2,20);
		textArea3.setColumns(1);
		textArea3.setRows(3);
		textArea3.setWrapStyleWord(true);
		textArea3.setLineWrap(true);
		textArea3.setFont(new Font("Arial", Font.TRUETYPE_FONT, 18));
		textArea3.setEditable(false);

		JLabel label1 = new JLabel("About", JLabel.CENTER);
		label1.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 25));
		JLabel label2 = new JLabel("Purpose", JLabel.CENTER);
		label2.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 25));
		JLabel label3 = new JLabel("Description", JLabel.CENTER);
		label3.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 25));

		String text1 = "To create Project Lift America, we split into two teams: the Server team and the Graphical User Interface team. Each team was headed by a team captain that split up the workload and supervised the development of the respective parts. The GUI team divided work among tabs in the nav bar (Home Tab, Find Resources Tab, Settings Tab). The server team divided work based on functionality (signup functionality, login functionality, and changing the user properties functionality). Throughout the project, the two team captains communicated closely with each other to create the final project presented today."; 
		String text2 = "The purpose of the Project Lift America Software Development project was created with American citizens struggling through the pandemic in mind. We created Project Lift America, a fictional company created to address the need for assistance. Project Lift America understands that Americans need other resources than doctors. This is why we created a fictional COVID-19 nonprofit to address the assistance gap felt by citizens across America.";
		String text3 = "To create Project Lift America, we utilized Java Graphical User Interface so that users are able to create accounts and login to view their information in the Settings tab. The user can also apply for a loan at a bank and find a set of resources using server pull requests to get a map powered by Google. Design patterns such as the Singleton design pattern are apparent throughout our use of Graphical User Interface and the Server side. The server team also used the Observer and Adapter design patterns. Each GUI only has one instance, so this prevents others from inheriting the data of our clients for themselves. We also utilized a modern look and feel to attract our users. We used http requests to connect to the server to make communication between server and GUI as secure as possible.";
				
		
		textArea1.setText(text1);
		textArea2.setText(text2);
		textArea3.setText(text3);
		
		panel1.add(label1);
		panel2.add(label2);
		panel3.add(label3);
		panel1.add(textArea1);
		panel2.add(textArea2);
		panel3.add(textArea3);
		firstHalf.add(panel1);
		firstHalf.add(panel2);
		firstHalf.add(panel3);
		
		PicturePanel pic = new PicturePanel("images/humanResources.jpg");
		pic.rescale(256, 425);
		secondHalf.add(pic);
		
		PicturePanel pic1 = new PicturePanel("images/humanResources1.png");
		pic1.rescale(256, 425);
		secondHalf.add(pic1);
		
		add(firstHalf);
		add(secondHalf);
		
	}
	
	public class PicturePanel extends JPanel{

		private Image image;

		public PicturePanel(String fileName) {

			image = Toolkit.getDefaultToolkit().getImage(fileName);
		}

		public void rescale(int height, int width) {
			image = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		}

		public void paint(Graphics g) {

			g.drawImage(image, 0, 0, image.getWidth(this), image.getHeight(this), this);
		}
	}
}