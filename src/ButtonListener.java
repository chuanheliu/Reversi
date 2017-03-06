import java.awt.*;
import java.awt.event.*;

import javax.swing.JFrame;

public class ButtonListener implements ActionListener{
	
	private JFrame gui;
	private int buttonNum;

	
	public ButtonListener(JFrame gui, int buttonNum){
		this.gui = gui;
		this.buttonNum = buttonNum;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(buttonNum == 1){
			
		}
		else if(buttonNum == 2){
			
		}
		else if(buttonNum == 3){
			
		}
		else{//buttonNum == 4
			Dialog d = new Dialog(gui);
			d.setBounds(300, 320, 500, 100);
			Label lab = new Label();
			lab.setText("Author:  Chuanhe Liu, Oktay Aslantas, Kenan Koc, Ziqun Liu, Ziying Zhang, Charlie Hegarty");
			d.add(lab);
			
			d.setVisible(true);
			d.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e){
					d.setVisible(false);
				}
			});
			
			
		}
	}
}
