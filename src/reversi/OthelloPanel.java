package reversi;

import javax.swing.*;



import java.awt.*;
import java.awt.event.*;



/**
 * draw the grid and the chess.
 * listen for the mouse click
 * 
 * @author Chuanhe Liu
 *
 */
public class OthelloPanel extends JPanel{


	private static final long serialVersionUID = 1L;
	//the x location for mouse listener
	public static int currentX = -1;	
	//the y location for mouse listener
	public static int currentY = -1;	
	//the color of current player
	static int currentPlayer = 1; 
	//whether is the players turn, if is not can not set chess
	public static boolean isTurn;
	//the init chess 
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



	//setter
	public void setChess(int[][] chess){
		this.chess = chess;
	}

	//getter
	public int[][] getChess(){
		return this.chess;
	}

	//constructor
	public OthelloPanel(int color){
		this.setBackground(Color.getHSBColor(236, 33, 1));
		mouseListen(color);
	}

	/**
	 * //mouse listener
	 * @param color
	 */
	private void mouseListen(int color){

		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//when is the turn to set chess get the location
				if(isTurn){
					int x = (e.getX() - 50) / 50;
					int y = (e.getY() - 50) / 50;

					currentX = x;		
					currentY = y;

					//when the location is in the chess setting area
					if(x>=0 && x<=7 && y>=0 && y<=7){
						//when the location is empty and can set chess here.
						if(chess[x][y] == 0 && GameFunction.canSetChess(x, y, color)){
							//do logic , change the array: chess[][].
							setChess(GameFunction.reverse(x, y, color));
							OthelloPanel.isTurn = false;
							//refresh the chess panel with chess
							drawChess();
							//after click the mouse, change the color
							if(color == -1){
							}
						}
						else
							System.out.println("out of the chess setting area");
					}
				}
			}
		});
	}


	//draw all the chess according to the array: int [8][8] 
	public void drawChess(){
		Graphics g = this.getGraphics();
		int[][] chees = GameFunction.chess;

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
			g.drawString(perpendicular[i], 40, 50 * i + 80);
		}

		//draw the initial 4 in the middle
		g.fillOval(55 + 50 * 4, 55 + 50 * 3, 40, 40);
		g.fillOval(55 + 50 * 3, 55 + 50 * 4, 40, 40);
		g.setColor(Color.WHITE);
		g.fillOval(55 + 50 * 4, 55 + 50 * 4, 40, 40);
		g.fillOval(55 + 50 * 3, 55 + 50 * 3, 40, 40);
	}
}
