package game;

import java.util.ArrayList;
import java.util.Collections;

public class GameTest {

	public static void main(String[] args) {
		Game game = new Game(); /** Create a new Tic Tac Toe game storage */
		// ********** Test saveMove() **********
		/** Store some value in the game storage */
		/** First row */
		game.saveMove('X', 0, 0);
		game.saveMove('O', 0, 1);
		game.saveMove('X', 0, 2);
		/** Second row */
		game.saveMove('O', 1, 0);
		game.saveMove('X', 1, 1);
		game.saveMove('O', 1, 2);
		/** Third row */
		game.saveMove('X', 2, 0);
		game.saveMove('O', 2, 1);
		game.saveMove('X', 2, 2);
		// ********** Test readMove() **********
		System.out.println("Printing game ...");
		printGame(game); /** Print the game */
		// ********** Test comparatorByFill() **********
		/** Create another Tic Tac Toe game storage */
		Game game1 = new Game();
		game1.saveMove('X', 0, 0);
		game1.saveMove('X', 0, 1);
		game1.saveMove('X', 0, 2);
		game1.saveMove('O', 1, 0);
		game1.saveMove('X', 1, 1);
		game1.saveMove('O', 1, 2);
		/** Create another Tic Tac Toe game storage */
		Game game2 = new Game();
		game2.saveMove('X', 0, 0);
		game2.saveMove('X', 0, 1);
		game2.saveMove('X', 0, 2);
		ArrayList<Game> list = new ArrayList<Game>(); /** Create an array list to store games */
		/** Add games in non-organized order */
		list.add(game1);
		list.add(game);
		list.add(game2);
		Collections.sort(list, Game.comparatorByFill()); /** Sort the list */
		/** Least filled game */
		System.out.println("Least filled game");
		printGame(list.get(0));
		/** Second least filled game */
		System.out.println("Second least filled game");
		printGame(list.get(1));
		/** Most filled game */
		System.out.println("Most filled game");
		printGame(list.get(2));
		// ********** Test saveGame() **********
		try {
			System.out.println("Saving game ...");
			GameInterface.saveGame("game.txt", game); /** Save a game */
			System.out.println("Game saved!\n");
		} catch (Exception e) {

		}
		// ********** Test loadGame() **********
		try {
			System.out.println("Retrieving game ...");
			Game retrieved = GameInterface.loadGame("game.txt"); /** Retrieve the game */
			System.out.println("Game retrieved");
			System.out.println("Printing game ...");
			printGame(retrieved); /** Print the retrieved game */
		} catch (Exception e) {

		}
		// ********** Test toString() **********
		System.out.println("Testing toString()\n" + game.toString());
		// ********** Test toString() **********
		Game x = new Game();
		x.saveMove('X', 0, 0);
		x.saveMove('X', 0, 1);
		x.saveMove('X', 0, 2);
		x.saveMove('O', 1, 0);
		x.saveMove('O', 1, 1);
		x.saveMove('O', 1, 2);
		Game y = new Game();
		y.saveMove('X', 0, 0);
		y.saveMove('X', 0, 1);
		y.saveMove('X', 0, 2);
		y.saveMove('O', 1, 0);
		y.saveMove('O', 1, 1);
		y.saveMove('O', 1, 2);
		Game z = new Game();
		z.saveMove('X', 0, 0);
		z.saveMove('X', 0, 1);
		z.saveMove('X', 0, 2);
		z.saveMove('O', 1, 0);
		z.saveMove('O', 1, 1);
		z.saveMove('O', 1, 2);
		System.out.println("Testing equals()");
		System.out.println("x.equals(x) = " + x.equals(x)); /** x.equals(x) */
		System.out.println("x.equals(y) = " + x.equals(y)); /** x.equals(y) = y.equals(x) */
		System.out.println("y.equals(x) = " + y.equals(x)); /** x.equals(y) = y.equals(x) */
		System.out.println("x.equals(z) = " + x.equals(z)); /** x.equals(y) = y.equals(z) == x.equals(z) */
		System.out.println("x.equals(null) = " + null); /** x.equals(null) = false */
	}

	public static void printGame(Game game) { /** Print out Tic Tac Toe storage */
		for (int i = 0; i < 3; i++) { /** Traverse rows */
			for (int j = 0; j < 3; j++) { /** Traverse columns */
				System.out.printf("%c", game.readMove(i, j)); /** Print the game storage */
				if (j == 0 || j == 1)
					System.out.printf("|"); /** For formatting */
			}
			if (i == 0 || i == 1)
				System.out.println("\n- - -"); /** For formatting */
		}
		System.out.println("\n"); /** For formatting */
	}
}
