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

		String text1 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
		String text2 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
		String text3 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
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
		System.out.println("pic width: " + pic.getWidth());
		System.out.println("pic height: " + pic.getHeight());
		pic.rescale(256, 425);
		secondHalf.add(pic);
		add(firstHalf);
		add(secondHalf);
		// grab more rectangular; set to 256, 375
		
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