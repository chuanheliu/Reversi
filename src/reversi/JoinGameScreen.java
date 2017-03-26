package reversi;

import javax.swing.*;
import javax.swing.text.DefaultCaret;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

/**
 * the program is when you chose to be a client 
 * the gui and functions will include in this class
 *
 */
public class JoinGameScreen extends JFrame {

	//get the current location of the mouse when clicking
	public static int currentX;
	public static int currentY;
	
	// the connection statment
	private volatile ConnectionState state;
	
	// text fields to show the number of black and white.
	private static JTextField ta1,ta2;
	
	//buttons
	private JButton exitButton, connectButton, quitButton;
	static JTextArea statusField;
	// to judge whether is pressed
	public static boolean isPressed = false;

	private static final long serialVersionUID = 1L;
	private JTextField remotePortInput, remoteHostInput;

	private enum ConnectionState { LISTENING, CONNECTING, CONNECTED, CLOSED }
	//default message for the connection
	private static String defaultPort = "9001";
	private static String defaultHost = "localhost";
	private ConnectionHandler connection;

	private OthelloPanel chessPanel;
	private final static int JOINCOLOR = -1; 

	//set the number of eah side.
	public static void setTa(int count1, int count2){
		ta1.setText(count1+"");
		ta2.setText(count2+"");
	}

	/**
	 * constructor of JoinGameScreen
	 */
	public JoinGameScreen() {
		OthelloPanel.isTurn = false;

		setResizable(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainScreen.class.getResource("/reversi/backgroundfinal.jpg")));
		getContentPane().setLayout(null);

		ActionListener actionHandler = new ActionHandler();

		//label to show the black to show the number of black side
		JLabel l1 = new JLabel("Black:");
		l1.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		l1.setForeground(Color.WHITE);
		l1.setBounds(246, 10, 49, 16);
		getContentPane().add(l1);

		//test field to show the black to show the number of black side
		ta1 = new JTextField(2);
		ta1.setBounds(291, 6, 34, 26);
		getContentPane().add(ta1);
		ta1.setText(2+"");
		
		//label to show the white to show the number of black side
		JLabel l2 = new JLabel("White:");
		l2.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		l2.setForeground(Color.WHITE);
		l2.setBounds(246, 35, 49, 16);
		getContentPane().add(l2);

		//test field to show the black to show the number of black side
		ta2 = new JTextField(2);
		ta2.setBounds(291, 31, 34, 26);
		ta2.setText(2+"");
		getContentPane().add(ta2);

		//add ChessPanel
		chessPanel = new OthelloPanel(-1);
		chessPanel.setForeground(new Color(0, 0, 0));
		chessPanel.setBackground(new Color(0, 153, 51,0));
		chessPanel.setBounds(0, 113, 492, 481);
		getContentPane().add(chessPanel);

		//add exit button
		exitButton = new JButton("Go back");
		exitButton.setFont(new Font("Iowan Old Style", Font.PLAIN, 20));
		exitButton.setBounds(377, 6, 117, 45);
		getContentPane().add(exitButton);
		exitButton.addActionListener(actionHandler);

		//add the join game button
		connectButton = new JButton("Join Game");
		connectButton.setBounds(264, 66, 105, 35);
		getContentPane().add(connectButton);
		connectButton.addActionListener(actionHandler);

		//add the leave Game button
		quitButton = new JButton("Leave Game");
		quitButton.setBounds(387, 67, 105, 35);
		getContentPane().add(quitButton);
		quitButton.addActionListener(actionHandler);

		//add the game statue label
		JLabel statusLabel = new JLabel("Game status:");
		statusLabel.setForeground(Color.WHITE);
		statusLabel.setBounds(0, 568, 128, 37);
		getContentPane().add(statusLabel);

		
		statusField = new JTextArea(200,60);
		DefaultCaret caret = (DefaultCaret)statusField.getCaret(); 
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		statusField.setEditable(false);
		statusField.setLineWrap(true);
		statusField.setEditable(false);
		statusField.setColumns(10);
		statusField.setBounds(0, 592, 500, 39);
		getContentPane().add(statusField);

		remotePortInput = new JTextField(defaultPort,5);
		//remotePortInput.setText("enter port");
		remotePortInput.setHorizontalAlignment(SwingConstants.CENTER);
		remotePortInput.setForeground(new Color(102, 102, 102));
		remotePortInput.setColumns(10);
		remotePortInput.setBounds(135, 65, 117, 36);
		getContentPane().add(remotePortInput);

		remoteHostInput = new JTextField(defaultHost);
		remoteHostInput.setForeground(new Color(102, 102, 102));
		remoteHostInput.setHorizontalAlignment(SwingConstants.CENTER);
		//remoteHostInput.setText("enter host");
		remoteHostInput.setBounds(6, 65, 117, 36);
		getContentPane().add(remoteHostInput);
		remoteHostInput.setColumns(10);

		//set background.
		JLabel background = new JLabel("");
		background.setVerticalAlignment(SwingConstants.TOP);
		background.setHorizontalAlignment(SwingConstants.LEFT);
		background.setIcon(new ImageIcon(HostGameScreen.class.getResource("/reversi/backgroundfinal.jpg")));
		background.setBounds(0, 0, 500, 628);
		getContentPane().add(background);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Reversi");
		this.setSize(500, 653);      
		this.setLocationRelativeTo(null);
	}


	/**
	 * listen for the buttons
	 * @author lch
	 *
	 */
	private class ActionHandler implements ActionListener {	

		public void actionPerformed(ActionEvent evt) {
			isPressed = true;
			Object source = evt.getSource();
			//System.out.println(source);

			//Join Game button
			if (source == connectButton) {
				if (connection == null || 
						connection.getConnectionState() == ConnectionState.CLOSED) {
					//get port from the text field
					String portString = remotePortInput.getText();
					int port;
					try {
						//available port
						port = Integer.parseInt(portString);
						if (port < 0 || port > 65535)
							throw new NumberFormatException();
					}
					catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(JoinGameScreen.this, 
								portString +"is not a legal port number.");
						return;
					}
					connectButton.setEnabled(true);
					connection = new ConnectionHandler(remoteHostInput.getText(),port);
				}
			}
			//exit button
			else if (source == exitButton) {
				isPressed = true;
				dispose();
				new MainScreen().setVisible(true);
				for(int i = 0; i < 8; i++){
					for(int j = 0; j < 8; j++){
						GameFunction.chess[i][j] = 0;
					}
				}
				GameFunction.chess[3][3] = -1;
				GameFunction.chess[4][4] = -1;
				GameFunction.chess[3][4] = 1;
				GameFunction.chess[4][3] = 1;
				OthelloPanel.isTurn = false;
			}

			//quit button
			else if (source == quitButton) {
				connection.quitGame();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				connection.cleanUp();			
			}

		}

	}


	/**
	 * show the data on the console.
	 * @param message
	 */
	private static void postMessage(String message) {

		statusField.append(message + '\n');
		System.out.println("client :" +message);
	}

	/**
	 * some connection methods to operate the connection
	 * @author lch
	 *
	 */
	public class ConnectionHandler extends Thread {

		private String remoteHost;
		private int port;
		private ServerSocket listener;
		private Socket socket;
		private PrintWriter out;
		private BufferedReader in;

		/**
		 * constructor
		 * @param remoteHost
		 * @param port
		 */
		ConnectionHandler(String remoteHost, int port) {
			state = ConnectionState.CONNECTING;
			this.remoteHost = remoteHost;
			this.port = port;
			start();
		}

		synchronized ConnectionState getConnectionState() {
			return state;
		}

		/**
		 * show the data that received.
		 * @param message
		 */
		synchronized private void received(String message) {
			if (state == ConnectionState.CONNECTED)
				postMessage("RECEIVE:  " + message);
		}

		/**
		 * open the connection.
		 */
		synchronized private void connectionOpened() throws IOException {
			listener = null;
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream());
			state = ConnectionState.CONNECTED;
			postMessage("\n Connection made, enjoy the game! \n \n");
		}

		synchronized private void connectionClosedFromOtherSide() {
			if (state == ConnectionState.CONNECTED) {
				state = ConnectionState.CLOSED;
				statusField.setText("Game stopped \n \n");
				JOptionPane.showMessageDialog(JoinGameScreen.this,"Other player quit the game");
			}
		}

		/**
		 * quit for the game.
		 */
		synchronized private void quitGame() {
			if (state == ConnectionState.CONNECTED) {
				state = ConnectionState.CLOSED;
				statusField.setText("Game stopped \n \n");
				JOptionPane.showMessageDialog(JoinGameScreen.this,"You have left the game");
				connectButton.setEnabled(true);
			}
		}

		/**
		 * clean up the connections
		 */
		private void cleanUp() {
			isPressed = true;
			state = ConnectionState.CLOSED;
			connectButton.setEnabled(true);
			//postMessage("*** CONNECTION CLOSED ***");
			if (socket != null && !socket.isClosed()) {

				// Make sure that the socket, if any, is closed.
				try {
					socket.close();
				}
				catch (IOException e) {
				}
			}
			socket = null;
			in = null;
			out = null;
			listener = null;
		}

		/**
		 * run method of thread
		 */
		public void run() {

			try {
				if (state == ConnectionState.CONNECTING) {
					// Open a connection as a client.
					socket = new Socket(remoteHost,port);
				}
				statusField.setText("");

				connectionOpened();  // Set up to use the connection.
				JOptionPane.showMessageDialog(JoinGameScreen.this,"You are white, black starts first!");


				OthelloPanel.isTurn = false;
				boolean flag = true;
				while (state == ConnectionState.CONNECTED) {

					//judge whether the game have done.
					if(GameFunction.hasFinish(JOINCOLOR) && flag){
						//if it has done, can not set chess.
						OthelloPanel.isTurn = false;
						//count for the number of each side. and judge which is winner.
						if(GameFunction.countColor(JOINCOLOR) > GameFunction.countColor(-JOINCOLOR))
							JOptionPane.showMessageDialog(JoinGameScreen.this,"Congratulations! You WIN!");
						else
							JOptionPane.showMessageDialog(JoinGameScreen.this,"Sorry! You LOSE!");
						flag = false;
			
					}
					//send the location that listen to the mouse.
					out.write(OthelloPanel.currentX+""+OthelloPanel.currentY+"\n");
					out.flush();


					// Read one line of text from the other side of
					// the connection, and report it to the user.
					String input = in.readLine();
					if (input == null)
						connectionClosedFromOtherSide();
					else{
						if((input.charAt(0)-48 >-1)&&
								GameFunction.canSetChess(input.charAt(0)-48, input.charAt(1)-48, 1)){
							//receive the location and reverse.
							chessPanel.setChess(GameFunction.reverse(input.charAt(0)-48, input.charAt(1)-48, 1));
							chessPanel.drawChess();
							OthelloPanel.isTurn = true;

						}
						received(input);  // Report message to user.
						
						//judge whether the game have done.
						if(GameFunction.hasFinish(-JOINCOLOR) && flag){
							OthelloPanel.isTurn = false;
							if(GameFunction.countColor(JOINCOLOR) > GameFunction.countColor(-JOINCOLOR))
								JOptionPane.showMessageDialog(JoinGameScreen.this,"Congratulations!");
							else
								JOptionPane.showMessageDialog(JoinGameScreen.this,"Sorry! You LOSE!");
							flag = false;
						}
					}
				}
			}
			catch (IOException e) {
				System.out.println(e.getMessage());
				if (state != ConnectionState.CLOSED)
					postMessage(" ERROR:  " + e);
			} 

		}

	} // end nested class ConnectionHandler


	public static void main (String[] args) {

		new JoinGameScreen().setVisible(true);;
	}
}

