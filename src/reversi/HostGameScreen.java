package reversi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

/**
 * the program is when you chose to be a server
 * the gui and functions will include in this class
 *
 */
public class HostGameScreen extends JFrame {
	// to judge whether is pressed
	static boolean isPressed;

	private static final long serialVersionUID = 1L;
	//private JTextField host;
	private JTextField  listeningPortInput;
	
	// text fields to show the number of black and white.
	private static JTextField ta1,ta2;
	//buttons
	private JButton exitButton, listenButton, leaveButton;
	static JTextArea statusField;

	private enum ConnectionState { LISTENING, CONNECTING, CONNECTED, CLOSED }
	//default message for the connection
	private static String defaultPort = "9001";

	private ConnectionHandler connection;	

	private OthelloPanel chessPanel;

	private final static int HOSTCOLOR = 1; 
	
	//set the number of eah side.
	public static void setTa(int count1, int count2){
		ta1.setText(count1+"");
		ta2.setText(count2+"");
	}

	/**
	 * constructor of HostGameScreen
	 */
	public HostGameScreen() {
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
		ta1.setEditable(false);
		getContentPane().add(ta1);
		
		//label to show the white to show the number of black side
		ta1.setText(2+"");
		JLabel l2 = new JLabel("White:");
		l2.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		l2.setForeground(Color.WHITE);
		l2.setBounds(246, 35, 49, 16);
		getContentPane().add(l2);

		//test field to show the black to show the number of black side
		ta2 = new JTextField(2);
		ta2.setBounds(291, 31, 34, 26);
		ta2.setEditable(false);
		ta2.setText(2+"");
		getContentPane().add(ta2);

		//		add ChessPanel
		chessPanel = new OthelloPanel(1);
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

		//add the start game button
		listenButton = new JButton("Start Game");
		listenButton.setBounds(264, 66, 105, 35);
		getContentPane().add(listenButton);
		listenButton.addActionListener(actionHandler);


		//add the stop Game button
		leaveButton = new JButton("Stop Game");
		leaveButton.setBounds(387, 67, 105, 35);
		getContentPane().add(leaveButton);
		leaveButton.addActionListener(actionHandler);


		//add the game statue label
		JLabel statusLabel = new JLabel("Game status:");
		statusLabel.setForeground(Color.WHITE);
		statusLabel.setBounds(0, 568, 128, 37);
		getContentPane().add(statusLabel);

		statusField = new JTextArea(200,60);
		statusField.setEditable(false);
		statusField.setLineWrap(true);
		statusField.setEditable(false);
		statusField.setColumns(10);
		statusField.setBounds(0, 592, 500, 68);
		statusField.setCaretPosition(statusField.getDocument().getLength());
		getContentPane().add(statusField);


		listeningPortInput = new JTextField(defaultPort,5);
		//listeningPortInput.setText("enter port");
		listeningPortInput.setHorizontalAlignment(SwingConstants.CENTER);
		listeningPortInput.setForeground(new Color(102, 102, 102));
		listeningPortInput.setColumns(10);
		listeningPortInput.setBounds(135, 65, 117, 36);
		getContentPane().add(listeningPortInput);

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

			Object source = evt.getSource();
			
			//Join Game button
			if (source == listenButton) {
				statusField.setText("");
				if (connection == null ||  connection.getConnectionState() == ConnectionState.CLOSED) {
					String portString = listeningPortInput.getText();
					int port;
					try {
						//available port
						port = Integer.parseInt(portString);
						if (port < 0 || port > 65535)
							throw new NumberFormatException();
					}
					catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(HostGameScreen.this, 
								portString + "is not a legal port number.");
						return;
					}
					listenButton.setEnabled(false);
					connection = new ConnectionHandler(port);
					//GameFunction.reverse(connection.getPosCurrentX(),connection.getPosCurrentY(), 1);

				}
			}
			//exit button
			else if (source == exitButton) {
				dispose();
				//connection.close(); 
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
			else if (source == leaveButton) {
				isPressed = true;
				connection.close();
				statusField.setText("Game stopped");
				listenButton.setEnabled(true);
				JOptionPane.showMessageDialog(HostGameScreen.this, 
						"You have stopped the game");

			}

		}
	}

	/**
	 * show the data on the console.
	 * @param message
	 */
	private static void postMessage(String message) {

		statusField.append(message + '\n');
		System.out.println("host :" +message);
	}

	private class ConnectionHandler extends Thread {

		private volatile ConnectionState state;
		//private String remoteHost;
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
		ConnectionHandler(int port) {
			state = ConnectionState.LISTENING;
			this.port = port;
			postMessage("\nWaiting for other player to connect on port: " + port + "\n");
			start();
			listenButton.setEnabled(false);

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
		 * quit for the game.
		 */
		synchronized void close() {
			state = ConnectionState.CLOSED;
			try {
				if (socket != null)
					socket.close();
				else if (listener != null)
					listener.close();
			}
			catch (IOException e) {
			}
		}

		/**
		 * open the connection.
		 */
		synchronized private void connectionOpened() throws IOException {
			listener = null;
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream());
			state = ConnectionState.CONNECTED;
			statusField.setText("\nConnection made, enjoy the game! \n \n");

		}

		synchronized public void connectionClosedFromOtherSide() {
			if (state == ConnectionState.CONNECTED) {
				state = ConnectionState.CLOSED;
				JOptionPane.showMessageDialog(HostGameScreen.this,"Other player quit the game");
				statusField.setText("Game stopped \n \n");
			}
		}

		/**
		 * run method of thread
		 */
		public void run() {
			try{
				if (state == ConnectionState.LISTENING) {
					// Open a connection as a server.
					listener = new ServerSocket(port);
					socket = listener.accept();
					//listener.close();
				}

				statusField.setText("");
				connectionOpened();  // Set up to use the connection.
				JOptionPane.showMessageDialog(HostGameScreen.this,"You are black, it's your turn!");	

				OthelloPanel.isTurn = true;
				boolean flag = true;
				while (state == ConnectionState.CONNECTED) {
					//judge whether the game have done.
					if(GameFunction.hasFinish(-HOSTCOLOR) && flag){
						//if it has done, can not set chess.
						OthelloPanel.isTurn = false;
						//count for the number of each side. and judge which is winner.
						if(GameFunction.countColor(HOSTCOLOR) >= GameFunction.countColor(-HOSTCOLOR))
							JOptionPane.showMessageDialog(HostGameScreen.this,"Congratulations! You WIN!");	
						else
							JOptionPane.showMessageDialog(HostGameScreen.this,"Sorry! You LOSE!");
						flag = false;
					}
					//send the location that listen to the mouse.
					out.write(OthelloPanel.currentX+""+OthelloPanel.currentY+"\n");
					out.flush();

					String input = in.readLine();

					if (input == null || JoinGameScreen.isPressed == true || state == ConnectionState.CLOSED)
						connectionClosedFromOtherSide();
					else{
						if((input.charAt(0)-48 > -1) &&
								GameFunction.canSetChess(input.charAt(0)-48, input.charAt(1)-48, -1)){

							chessPanel.setChess(GameFunction.reverse(input.charAt(0)-48, input.charAt(1)-48, -1));
							chessPanel.drawChess();
							OthelloPanel.isTurn = true;
						}
						//receive the location and reverse.
						received(input);  // Report message to user.

						//judge whether the game have done.
						if(GameFunction.hasFinish(HOSTCOLOR) && flag){
							OthelloPanel.isTurn = false;
							if(GameFunction.countColor(HOSTCOLOR) >= GameFunction.countColor(-HOSTCOLOR))
								JOptionPane.showMessageDialog(HostGameScreen.this,"Congratulations!");	
							else
								JOptionPane.showMessageDialog(HostGameScreen.this,"Sorry! You LOSE!");
							flag = false;
						}
					}
				}
			}
			catch(IOException e){
				System.out.println(e.getMessage());
			} 
		}
	} // end nested class ConnectionHandler

	public static void main (String[] args) throws IOException {
		new HostGameScreen().setVisible(true);

	}
}
