package reversi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import reversi.HostGameScreen;
import reversi.UserRegister;


/**
 * this is the main Gui before begin the game.
 * few options to chose.
 * 
 *
 */
public class MainScreen extends JFrame {
		
	private static final long serialVersionUID = 1L;

	public MainScreen() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainScreen.class.getResource("/reversi/background2.jpg")));
		getContentPane().setLayout(null);
		
		//Exit Reversi button
		JButton exitButton = new JButton("Exit Reversi");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				}
		});
		exitButton.setFont(new Font("Iowan Old Style", Font.PLAIN, 20));
		exitButton.setBounds(35, 415, 436, 51);
		getContentPane().add(exitButton);
		
		//Log out of Reversi button
		JButton logoutButton = new JButton("Log out of Reversi");
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {
					UserRegister.main(null);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InstantiationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnsupportedLookAndFeelException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
		});
		logoutButton.setFont(new Font("Iowan Old Style", Font.PLAIN, 20));
		logoutButton.setBounds(35, 352, 436, 51);
		getContentPane().add(logoutButton);
		
		
		//teach you how to play
		JButton helpButton = new JButton("How to play Reversi");
		helpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
		        new HelpGameScreen().setVisible(true);
			}
		});
		helpButton.setFont(new Font("Iowan Old Style", Font.PLAIN, 20));

		helpButton.setBounds(35, 289, 436, 51);
		getContentPane().add(helpButton);
		
		//join game as a client
		JButton joinButton = new JButton("Join an existing online Reversi game");
		joinButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
		        new JoinGameScreen().setVisible(true);
			}
		});
		joinButton.setFont(new Font("Iowan Old Style", Font.PLAIN, 20));
		joinButton.setBounds(35, 226, 436, 51);
		getContentPane().add(joinButton);
		
		//start a game as a server
		JButton hostButton = new JButton("Start a new online Reversi game");
		hostButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
		        new HostGameScreen().setVisible(true);
			}
		});
		hostButton.setFont(new Font("Iowan Old Style", Font.PLAIN, 20));
		hostButton.setBounds(35, 163, 436, 51);
		getContentPane().add(hostButton);
		
		//set background
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(MainScreen.class.getResource("/reversi/background2.jpg")));
		background.setBounds(0, 0, 500, 500);
		getContentPane().add(background);
		
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setTitle("Reversi");
	    this.setResizable(false);
	    this.setSize(500,500);  
	    this.setLocationRelativeTo(null);
        }
	
	public static void main (String[] args) {
        new MainScreen().setVisible(true);
	}
}
