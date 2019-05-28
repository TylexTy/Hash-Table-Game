/**
 * 
 * @author Tyler Bulley
 * UWO ID: 250498520
 *
 */


public class BoardGame {
	
	private char[][] gameBoard; // initialize the gameBoard
	
	
	
	/**
	 * Constructor that creates the board and sets each entry to empty
	 * 
	 * @param board_size the length of the gameBoard
	 * @param empty_positions number of empty positions which cannot be played
	 * @param max_levels level of difficulty
	 */
	public BoardGame (int board_size, int empty_positions, int max_levels) {
		
		gameBoard = new char[board_size][board_size];
		
		for (int i = 0; i < board_size; i++) {
			for (int j=0; j< board_size; j++) {
				gameBoard[i][j] = 'g';
			}
		}
		
	}
	
	/**
	 * 
	 * @return returns a hash Dictionary of the size 9887
	 */
	public HashDictionary makeDictionary() {
		
		HashDictionary hashDict = new HashDictionary(9887);
		return hashDict;
	}
	
	/**
	 * Checks to see if the board configuration is already in the dictionary
	 * and returns the score.
	 * 
	 * @param dict the hash dictionary
	 * @return returns the score of the current board configuration
	 */
	
	public int isRepeatedConfig(HashDictionary dict) {
		
		String gameBoardStr = "";
		
		for (int i=0; i < gameBoard[0].length; i++) {
			for (int j=0; j < gameBoard[0].length; j++) {
				gameBoardStr += gameBoard[j][i];
			}
		}
		return dict.getScore(gameBoardStr);
		
	}
	
	/**
	 * Gets the string config of the current Game Board and attempts to put it in the dictionary
	 * 
	 * @param dict the hash dictionary
	 * @param score the score of the current game Board
	 * @throws DictionaryException is thrown when we cannot put the configuration in the dictionary
	 */
	public void putConfig(HashDictionary dict, int score) throws DictionaryException {
		
		String gameBoardStr = "";
		
		for (int i=0; i < gameBoard[0].length; i++) {
			for (int j=0; j < gameBoard[0].length; j++) {
				gameBoardStr += gameBoard[j][i];
			}
		}
		
		Configuration config = new Configuration(gameBoardStr, score);
		
		try {
		dict.put(config);
		}
		catch (DictionaryException dE) {
			throw new DictionaryException("Could not put " + config + " in the dictionary");
		}

	}
	
	/**
	 * Marks a game board entry with a new symbol
	 * 
	 * @param row row address in game board
	 * @param col column address in game board
	 * @param symbol new symbol to put in the game board entry.
	 */
	
	public void savePlay(int row, int col, char symbol) {
		gameBoard[row][col] = symbol;
	}
	
	/**
	 * Determines whether a position is empty
	 * 
	 * @param row row address in game board
	 * @param col column address in game board
	 * @return boolean representation of whether game board entry is empty
	 */
	
	public boolean positionIsEmpty(int row, int col) {
		return (gameBoard[row][col] == 'g');
	}
	
	/**
	 * Determines whether a space is occupied by the Computer
	 * 
	 * @param row row index of game board
	 * @param col column index of game board
	 * @return boolean of whether game board entry is occupied by Computer
	 */
	public boolean tileOfComputer(int row, int col) {
		return (gameBoard[row][col] == 'o');
	}
	
	/**
	 * Determines whether a space in game board is occupied by the player
	 * 
	 * @param row row index of game board
	 * @param col column index of game board
	 * @return boolean of whether game board is occupied by Computer
	 */
	
	public boolean tileOfHuman (int row, int col) {
		return (gameBoard[row][col] == 'b');
	}
	
	/**
	 * Determines whether the given symbol has won the game
	 * 
	 * @param symbol checks this symbol for whether it has won
	 * @return boolean of whether the symbol has won
	 */
	public boolean wins (char symbol) {
		
		int count = 0;
		
		// check horizontal
		for (int i = 0; i < gameBoard[0].length; i++) {
			for (int j = 0; j < gameBoard[0].length; j++) {
				
				if (gameBoard[i][j] != symbol) {
					count = 0;
					break;	
				}
				else {
					count++;
				}
				if (count == gameBoard[0].length) {
					return true;
				}
			}
		}
		
		count = 0;
		
		//check vertical
		for (int i = 0; i < gameBoard[0].length; i++) {
			for (int j = 0; j < gameBoard[0].length; j++) {
				
				if (gameBoard[j][i] != symbol) {
					count = 0;
					break;	
				}
				else {
					count++;
				}
				if (count == gameBoard[0].length) {
					return true;
				}
			}
		}
		
		count = 0;
		
		//check diagonals
		for (int i = 0; i < gameBoard[0].length; i++) {
				
			if (gameBoard[i][i] != symbol) {
				count = 0;
				break;	
			}
			else {
				count++;
			}
			if (count == gameBoard[0].length) {
				return true;
			}
		}
		count = 0;
		
		for (int i = 0; i < gameBoard[0].length; i++) {
			
			if (gameBoard[i][gameBoard[0].length - i-1] != symbol) {
				count = 0;
				break;	
			}
			else {
				count++;
			}
			if (count == gameBoard[0].length) {
				return true;
			}
		}
		
		return false; // if nothing has been returned yet, then nobody has won
		
	}
	
	/**
	 * Determines whether the given symbol is in a draw
	 * 
	 * @param symbol symbol to check for draw
	 * @param empty_positions number of unplayable positions on board
	 * @return boolean of whether the given symbol has won
	 */
	
	public boolean isDraw(char symbol, int empty_positions) {
		
		int gCount = 0; // number of empty spots
		boolean adjEntry; // true when there is an adjacent entry of the given symbol next to an empty position
		int gWithNoAdj = 0; // number of empty positions with no adjacent playable tiles
		
		// checks the amount of empty positions left 
		for (int i = 0; i < gameBoard[0].length; i++) {
			for (int j = 0; j < gameBoard[0].length; j++) {
				if (gameBoard[i][j] == 'g') {
					gCount++;
					adjEntry = checkAdjacent(i,j, symbol); // checks adjacent entries of empty spot for playable tiles.
					if (adjEntry == false) { // if none, add to gWithNoAdj.
						gWithNoAdj++;
					}
				}
			}
		}
		
		// if neither player or computer has won...
		
		if (wins('b') == false && wins('o') == false) {
			// if we set empty tiles to be 0..
			if (empty_positions == 0) {
				
				return (gCount == 0); // if gCount > 0, still playable tiles so return false, else draw.
			}
			
			// if empty_positions > 0 AND number of empty tiles == number of empty positions then we have to start
			// sliding tiles around and check for draw
			
			if (empty_positions > 0 && gCount == empty_positions) {
				
				// return whether the amount of empty tiles is equal to the amount of those empty tiles that have no
				/// adjacent playable tiles. If they are equal, return true, because it is a draw. else return false
				return (gCount == gWithNoAdj);
			}
			
		}
	
		return false; // if the code got here, there is no draw, return false
	}
	
	/**
	 * Determines whether there are any playable adjacent tiles from the empty tile
	 * 
	 * @param i index of the empty row position
	 * @param j index of the empty column position
	 * @param symbol symbol to check adjacent values
	 * @return boolean of whether there was any adjacent playable values found
	 */
	
	private boolean checkAdjacent(int i, int j, char symbol) {
		
		
		// check the upper left hand corner
		if (i == 0 && j == 0) {
			return (gameBoard[i][j+1] == symbol || gameBoard[i+1][j] == symbol || gameBoard[i+1][j+1] == symbol);
			
		}
		// check the upper right hand corner
		else if (i == 0 && j == gameBoard[0].length-1) {
			return (gameBoard[i][j-1] == symbol || gameBoard[i+1][j] == symbol || gameBoard[i+1][j-1] == symbol);	
		}
		// check the bottom left hand corner
		else if (i == gameBoard[0].length-1 && j == 0) {
			return (gameBoard[i][j+1] == symbol || gameBoard[i-1][j] == symbol || gameBoard[i-1][j+1] == symbol);
		}
		// check the bottom right hand corner
		else if (i == gameBoard[0].length-1 && j == gameBoard[0].length-1) {
			return (gameBoard[i][j-1] == symbol || gameBoard[i-1][j] == symbol || gameBoard[i-1][j-1] == symbol);
				
		}
		// check the top row
		else if (i == 0) {
			return (gameBoard[i+1][j+1] == symbol || gameBoard[i][j+1] == symbol || gameBoard[i][j-1] == symbol || 
				gameBoard[i+1][j] == symbol || gameBoard[i+1][j-1] == symbol); 
		}
		// check the bottom row
		else if (i == gameBoard[0].length-1) {
			return (gameBoard[i-1][j-1] == symbol || gameBoard[i][j+1] == symbol || gameBoard[i][j-1] == symbol || 
				gameBoard[i-1][j] == symbol || gameBoard[i-1][j-1] == symbol);
		}
		// check the 1st column
		else if (j == 0) {
			return (gameBoard[i-1][j] == symbol || gameBoard[i-1][j+1] == symbol || gameBoard[i][j+1] == symbol || 
				gameBoard[i+1][j+1] == symbol || gameBoard[i+1][j] == symbol);
		}
		// check the last column
		else if (j == gameBoard[0].length-1) {
			return (gameBoard[i-1][j] == symbol || gameBoard[i-1][j-1] == symbol || gameBoard[i][j-1] == symbol || 
				gameBoard[i+1][j-1] == symbol || gameBoard[i+1][j] == symbol);
		}
		// check the inner tiles
		else {
			return (gameBoard[i-1][j] == symbol || gameBoard[i][j-1] == symbol || 
				gameBoard[i+1][j] == symbol || gameBoard[i][j+1] == symbol ||
				gameBoard[i-1][j-1] == symbol || gameBoard[i+1][j+1] == symbol||
				gameBoard[i-1][j+1] == symbol || gameBoard[i+1][j-1] == symbol);
		}
	}
	
	/**
	 * Evaluates the board for whether someone wins,draws or undetermined
	 * 
	 * @param symbol symbol to check for draw
	 * @param empty_positions amount of empty unplayable positions in the game board.
	 * @return an int, the higher the number the better the move for the computer.
	 */
	
	public int evalBoard(char symbol, int empty_positions) {
		if (wins('o')) {
			return 3;
		}
		else if (wins('b')) {
			return 0;
		}
		else if (isDraw(symbol, empty_positions)) {
			return 2;
		}
		else {
			return 1;
		}
		
	}
	
	
}
