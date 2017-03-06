
import javax.swing.*;

public class FunctionButton extends JPanel{
	
	public FunctionButton(JFrame gui){
		JButton but1 = new JButton("Give Up");
		JButton but2 = new JButton("BUT2");
		JButton but3 = new JButton("BUT3");
		JButton but4 = new JButton("Instructions");
		
		add(but1);
		add(but2);
		add(but3);
		add(but4);
		
		but1.addActionListener(new ButtonListener(gui,1));
		but2.addActionListener(new ButtonListener(gui,2));
		but3.addActionListener(new ButtonListener(gui,3));
		but4.addActionListener(new ButtonListener(gui,4));
	}
}
