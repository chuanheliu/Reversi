
import java.awt.*;


import javax.swing.*;

public class ReversiGUI{

	private static JTextField ta1,ta2;
	
	public static void setTa(int count1, int count2){
		ta1.setText(count1+"");
		ta2.setText(count2+"");
	}

	public static void main(String[] args) throws Exception {

		//create the main frame 
		JFrame gui = new JFrame();
		gui.setBounds(450, 150, 510, 600);
		gui.setLayout(null);

		//draw the chess panel
		ChessPanel chessPanel = new ChessPanel();
		
		chessPanel.setBounds(0,35,510, 500);
		
		gui.add(chessPanel);

		gui.setLayout(new BorderLayout());
		
		//the top of the frame which will show the number of each side
		JPanel top = new JPanel();
		Label l1 = new Label("Black:");
		Label l2 = new Label("     White:");
		ta1 = new JTextField(2);
		ta2 = new JTextField(2);
		ta1.setText(2+"");
		ta2.setText(2+"");
		top.add(l1);
		top.add(ta1);
		top.add(l2);
		top.add(ta2);
		gui.add(top,BorderLayout.NORTH);
		
		//buttons
		FunctionButton funGUI = new FunctionButton(gui);
		gui.add(funGUI,BorderLayout.SOUTH);



		gui.setTitle("Reversi Online");
		gui.setVisible(true);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}
}
