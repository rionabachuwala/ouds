package project4.game;

import java.util.Scanner;

public abstract class Session {

	private Scanner scan = new Scanner(System.in);
	protected String name;
	private int games;
	
	public static void prtWelcome() {
        // Combined Welcome message & game instructions
        System.out.println("Welcome to 3312 Game of OVER UNDER DOUBLE SAME!\r\n"
                + "The game is played by rolling 2 dice twice.\r\n"
                + "Every player begins the game with 20 points.  The object of the game is to accumulate more points.  If your point balance falls to zero or less, the game automatically ends.\r\n"
                + "\n******* Game Instructions and Rules *******\r\n"
                + "When the first roll occurs, the Roll_1 dice values and sum are set.\r\n"
                + "After Roll_1, the player decides if the Roll_2 sum is likely to be over or under the Roll_1 sum.\r\n"
                + "The player makes a wager based on their decision of over or under. Wagers can be from 0 to 10 points.\r\n"
                + "If the player bets OVER or UNDER correctly, the player wins the amount of the wager, and that value is added to their point total.\r\n"
                + "If the player bets OVER or UNDER incorrectly, the player loses points in the amount of the wager, and that value is subtracted from the player's point total.\r\n"
                + "\n*** Special Cases ***\r\n"
                + "\nIf Roll_2 yields a double (e.g., 2,2 or 5,5), OR Roll_2 sum is the same as the Roll_1 sum, the round is lost and the amount of the wager is subtracted from the player's point total.\r\n"
                + "Note that the round is lost irrespective of the outcome of the over/under bet.\r\n"
                + "\nAnd that's the name of the game - OVER UNDER DOUBLE SAME!\n");		
        
        System.out.println("\nNow, let's get this wild and crazy game underway!");
	}

	public abstract String getName();
		
	
	public abstract void setName(String name);
	
	public String getPlayAgain() {
        // Ask if the player wants to play again
        System.out.print("Would you like to play again? Enter Y or N: ");
        return scan.next().toUpperCase();		
	}

	public int getGames() {
		return games;
	}

	public void setGames(int games) {
		this.games = games;
	}
	
}
