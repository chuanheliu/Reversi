
/**
 * functions about the game, 
 * all the method should be static
 * @author lch
 *
 */
public class GameFunction {


	public static int[][] chess = {
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,-1,1,0,0,0},
			{0,0,0,1,-1,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0}
	};


	//count the black chess
	public static int countBlack(){
		int count = 0;
		for(int i = 0; i < chess.length; i++){
			for(int j = 0; j < chess[i].length; j++){
				if(chess[i][j] == 1)
					count++;
			}
		}
		return count;
	}

	//count the white chess
	public static int countWhite(){
		int count = 0;
		for(int i = 0; i < chess.length; i++){
			for(int j = 0; j < chess[i].length; j++){
				if(chess[i][j] == -1)
					count++;
			}
		}
		return count;
	}


	//judge the whether the left will be changed if set a chess at a location
	private static boolean judgeLeft(int x, int y, int color){
		if(x >= 2 && chess[x-1][y] == -color){
			for(int i = x-2; i >= 0; i--){
				if(chess[i][y] == color)
					return true;
			}
		}
		return false;	
	}

	//judge the whether the right will be changed if set a chess at a location
	private static boolean judgeRight(int x, int y, int color){
		if(x <= 5 && chess[x+1][y] == -color){
			for(int i = x+2; i <= 7; i++){
				if(chess[i][y] == color)
					return true;
			}
		}
		return false;	
	}

	//judge the whether the up will be changed if set a chess at a location
	private static boolean judgeUp(int x, int y, int color){
		if(y >= 2 && chess[x][y-1] == -color){
			for(int i = y-2; i >= 0; i--){
				if(chess[x][i] == color)
					return true;
			}
		}
		return false;	
	}

	//judge the whether the down will be changed if set a chess at a location
	private static boolean judgeDown(int x, int y, int color){
		if(y <= 5 && chess[x][y+1] == -color){
			for(int i = y+2; i <= 7; i++){
				if(chess[x][i] == color)
					return true;
			}
		}
		return false;	
	}

	//judge the whether the up-left will be changed if set a chess at a location
	private static boolean judgeUpLeft(int x, int y, int color){
		if(x >= 2 && y >= 2 && chess[x-1][y-1] == -color){
			if(x < y)
				for(int i = x-2; i >= 0; i--){
					if(chess[i][i+y-x] == color)
						return true;
				}
			else
				for(int i = y-2; i >= 0; i--){
					if(chess[i+x-y][i] == color)
						return true;
				}
		}
		return false;	
	}

	//judge the whether the down-right will be changed if set a chess at a location
	private static boolean judgeDownRight(int x, int y, int color){
		if(x <= 5 && y <= 5 && chess[x+1][y+1] == -color){
			if(x > y)
				for(int i = x+2; i <= 7; i++){
					if(chess[x+2 + (i-(x+2))][y+2 + (i-(x+2))] == color)
						return true;
				}
			else
				for(int i = y+2; i <= 7; i++){
					if(chess[i+x-y][i] == color)
						return true;
				}
		}
		return false;	
	}
	//judge the whether the up-right will be changed if set a chess at a location
	private static boolean judgeUpRight(int x, int y, int color){
		if(x <= 5 && y >= 2 && chess[x+1][y-1] == -color){
			if((7-x) < y)	
				for(int i = 7-x ; i >= 2; i--){
					if(chess[x+2 + ((7-x)-i)][y-2 - ((7-x)-i)] == color)
						return true;
				}
			else
				for(int i = y-2; i >= 0; i--){
					if(chess[x+2 + ((y-2)-i)][y-2 - ((y-2)-i)] == color)
						return true;
				}
		}
		return false;	
	}

	//judge the whether the down-left will be changed if set a chess at a location
	private static boolean judgeDownLeft(int x, int y, int color){
		if(y <= 5 && x >= 2 && chess[x-1][y+1] == -color){
			if((7-y) < x)
				for(int i = 7-y ; i >= 2; i--){
					if(chess[x-2 - ((7-y)-i)][y+2 + ((7-y)-i)] == color)
						return true;
				}
			else
				for(int i = x-2; i >= 0; i--){
					if(chess[x-2 - ((x-2)-i)][y+2 + ((x-2)-i)] == color)
						return true;
				}
		}
		return false;	
	}


	/**
	 * judge whether the location can set chess
	 * @param x
	 * @param y
	 * @param color
	 * @return
	 */
	public static boolean canSetChess(int x, int y, int color){
		if(judgeUp(x, y, color) || judgeDown(x, y, color) 
				|| judgeLeft(x, y, color) || judgeRight(x, y, color) 
				|| judgeUpLeft(x, y, color) || judgeUpRight(x, y, color) 
				|| judgeDownLeft(x, y, color) || judgeDownRight(x, y, color))
			return true;
		return false;
	}


	// todo!! when click the mouse change the chess color
	public static int[][] reverse(int x, int y, int color){

		if(judgeLeft(x, y, color)){
			for(int i = x-1; i >= 0; i--){
				if(chess[i][y] == -color)
					chess[i][y] = color;
				else
					break;
			}
		}

		if(judgeRight(x, y, color)){
			for(int i = x+1; i <= 7; i++){
				if(chess[i][y] == -color)
					chess[i][y] = color;
				else
					break;
			}
		}

		if(judgeUp(x, y, color)){
			for(int i = y-1; i >= 0; i--){
				if(chess[x][i] == -color)
					chess[x][i] = color;
				else
					break;
			}
		}

		if(judgeDown(x, y, color)){
			for(int i = y+1; i <= 7; i++){
				if(chess[x][i] == -color)
					chess[x][i] = color;
				else
					break;
			}
		}

		if(judgeUpLeft(x, y, color)){
			if(x < y)
				for(int i = x-1; i >= 0; i--){
					if(chess[i][i+y-x] == -color)
						chess[i][i+y-x] = color;
					else
						break;
				}
			else
				for(int i = y-1; i >= 0; i--){
					if(chess[i+x-y][i] == -color)
						chess[i+x-y][i] = color;
					else
						break;
				}
		}

		if(judgeUpRight(x, y, color)){
			if((7-x) < y)
				for(int i = 7-x ; i >= 2; i--){
					if(chess[x+1 + ((7-x)-i)][y-1 - ((7-x)-i)] == -color)
						chess[x+1 + ((7-x)-i)][y-1 - ((7-x)-i)] = color;
					else
						break;
				}
			else
				for(int i = y-2; i >= 0; i--){
					if(chess[x+1 + ((y-2)-i)][y-1 - ((y-2)-i)] == -color)
						chess[x+1 + ((y-2)-i)][y-1 - ((y-2)-i)] = color;
					else
						break;
				}
		}

		if(judgeDownLeft(x, y, color)){

			if((7-y) < x)
				for(int i = 7-y ; i >= 2; i--){
					if(chess[x-1 - ((7-y)-i)][y+1 + ((7-y)-i)] == -color)
						chess[x-1 - ((7-y)-i)][y+1 + ((7-y)-i)] = color;
					else
						break;
				}
			else
				for(int i = x-2; i >= 0; i--){
					if(chess[x-1 - ((x-2)-i)][y+1 + ((x-2)-i)] == -color)
						chess[x-1 - ((x-2)-i)][y+1 + ((x-2)-i)] = color;
					else
						break;
				}
		}

		if(judgeDownRight(x, y, color)){

			if(x > y)
				for(int i = x+1; i <= 7; i++){
					if(chess[x+2 + (i-(x+2))][y+2 + (i-(x+2))] == -color)
						chess[x+2 + (i-(x+2))][y+2 + (i-(x+2))] = color;
					else
						break;
				}
			else
				for(int i = y+1; i <= 7; i++){
					if(chess[i+x-y][i] == -color)
						chess[i+x-y][i] = color;
					else
						break;
				}
		}


		chess[x][y] = color;

		return chess;
	}
}