import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * draw the grid and the chess.
 * listen for the mouse click
 * 
 * @author lch
 *
 */
public class ChessPanel extends JPanel{

	private int[][] chess = {
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,-1,1,0,0,0},
			{0,0,0,1,-1,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0}
	};

	static int currentPlayer = 1; 


	//setter
	public void setChess(int[][] chess){
		this.chess = chess;
	}

	//getter
	public int[][] getChess(){
		return this.chess;
	}

	//constructor
	public ChessPanel(){
		this.setBackground(Color.getHSBColor(236, 33, 1));

		//mouse listener
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int x = (e.getX() - 50) / 50;
				int y = (e.getY() - 50) / 50;

				if(chess[x][y] == 0 && GameFunction.canSetChess(x, y, currentPlayer)){
					//do logic , change the array: chess[][].
					setChess(GameFunction.reverse(x, y, currentPlayer));

					//refresh the chess panel with chess
					drawChess();
					//after click the mouse, change the color
					if(ChessPanel.currentPlayer == 1)
						ChessPanel.currentPlayer = -1 ;
					else
						ChessPanel.currentPlayer = 1;
	
				}
				
				//refresh the top field 
				ReversiGUI.setTa( GameFunction.countBlack(), GameFunction.countWhite());
			}
		});
		
		
	}

	//draw all the chess according to the array: int [8][8] 
	public void drawChess(){
		Graphics g = this.getGraphics();
		int[][] chees = this.chess;
		for(int i = 0; i < chees.length; i++)
			for(int j = 0; j < chees[i].length; j++){
				if(chees[i][j] == 1){
					g.setColor(Color.BLACK);
					g.fillOval(55 + 50 * i, 55 + 50 * j, 40, 40);
				}
				if(chees[i][j] == -1){
					g.setColor(Color.WHITE);
					g.fillOval(55 + 50 * i, 55 + 50 * j, 40, 40);
				}
			}
	}

	@Override
	//draw the grid 
	public void paint(Graphics g) {
		super.paint(g);
		// draw horizontal line
		for (int i = 0; i <= 8; i++) {
			g.drawLine(50, 50 * i + 50, 450, 50 * i + 50);
		}
		// Perpendicular line
		for (int i = 0; i <= 8; i++) {
			g.drawLine(50 * i + 50, 50, 50 * i + 50, 450);
		}
		// draw the side characters
		String[] horizontal = { "A", "B", "C", "D", "E", "F", "G", "H" };
		String[] perpendicular = { "1", "2", "3", "4", "5", "6", "7", "8" };
		for (int i = 0; i < perpendicular.length; i++) {
			g.drawString(horizontal[i], 50 * i + 72, 40);
			g.drawString(perpendicular[i], 30, 50 * i + 80);
		}

		//draw the initial 4 in the middle
		g.fillOval(55 + 50 * 4, 55 + 50 * 3, 40, 40);
		g.fillOval(55 + 50 * 3, 55 + 50 * 4, 40, 40);
		g.setColor(Color.WHITE);
		g.fillOval(55 + 50 * 4, 55 + 50 * 4, 40, 40);
		g.fillOval(55 + 50 * 3, 55 + 50 * 3, 40, 40);
	}
}
