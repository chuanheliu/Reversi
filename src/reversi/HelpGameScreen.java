package reversi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * help screen gui
 * 
 *
 */
public class HelpGameScreen extends JFrame {
	
	private static final long serialVersionUID = 1L;
	/**
	 * constructor
	 */
	public HelpGameScreen() {
		setResizable(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainScreen.class.getResource("/reversi/background2.jpg")));
		getContentPane().setLayout(null);
		
		//go back button
		JButton exitButton = new JButton("Go back");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
		        new MainScreen().setVisible(true);
				}
		});
		//add the text pane
		JTextPane txtpnGoalWinnerIs = new JTextPane();
		txtpnGoalWinnerIs.setEditable(false);
		txtpnGoalWinnerIs.setText("Goal\nWinner is who has got the most stones in its own color.\n\nRules\nAt the beginning of a game there a 4 stones, 2 from each player, in the middle of the board. The black player begins. At each round one stone has to be set next to a foreign players stone. With this all foreigns stones change their color to the current players color, if the stone at the end of a line is one of the current player. The line does not have any breaks. \n\nIf this is not possible you have to pass.\n\nThe last moved stone has a small marker.");
		txtpnGoalWinnerIs.setBounds(55, 159, 416, 262);
		getContentPane().add(txtpnGoalWinnerIs);
		exitButton.setFont(new Font("Iowan Old Style", Font.PLAIN, 20));
		exitButton.setBounds(363, 427, 117, 45);
		getContentPane().add(exitButton);
		
		//add the label.
		JLabel background = new JLabel("");
		background.setVerticalAlignment(SwingConstants.TOP);
		background.setHorizontalAlignment(SwingConstants.CENTER);
		background.setIcon(new ImageIcon(HelpGameScreen.class.getResource("/reversi/background2.jpg")));
		background.setBounds(0, 0, 500, 500);
		getContentPane().add(background);
		
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setTitle("Reversi");
	    this.setSize(500, 500);      
	    this.setLocationRelativeTo(null);
        }
	
	public static void main (String[] args) {
        new HelpGameScreen().setVisible(true);;
	}
}
