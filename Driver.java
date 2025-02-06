package project4.game;

import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		//Session session = new Session();
		
		Session.prtWelcome();
		
        // Prompt for player name
        System.out.print("\nBefore we start, I need to get your name.  Please enter it here: ");
        String playerName = scan.next();
        //session.setName(scan.next());
        Game game;
        int games=0;
		// Begin playAgain loop
        do {
        	games++;
        	
        	game = new Game();

            game.setName(playerName);
        	game.getRounds();
            
            System.out.println("\nGREAT " + game.getName().toUpperCase() + "! You have selected " + game.getGameRounds() + " rounds.");

            // Begin for loop for number of rounds
            for (int round = 1; round <= game.getGameRounds(); round++) {
                System.out.println("\n******* Round " + round + " *******");

                // Initiate Roll_1
                System.out.print("\nPress and enter any character to \"roll\" the dice: ");
                Character r1Init = scan.next().charAt(0);
                //System.out.print("\n r1Init: " + r1Init);
               
                while (!Character.isLetter(r1Init)) {
                    System.out.print("Invalid input! Press and enter any character to \"roll\" the dice: ");
                    r1Init = scan.next().charAt(0);
                }

                game.setRollNum(1);
                game.setRoll1(game.getDieValue(), game.getDieValue());

                game.prtRoll();


                game.getBet();
                game.getWager();

                // Initiate Roll_2
                System.out.print("\nPress and enter any character to \"roll\" the dice: ");
                Character r2Init = scan.next().charAt(0);
                
                
                while (!Character.isLetter(r2Init)) {
                    System.out.print("Invalid input! Press and enter any character to \"roll\" the dice: ");
                    r2Init = scan.next().charAt(0);
                }
                game.setRollNum(2);
                game.setRoll2(game.getDieValue(),game.getDieValue());
                
                game.prtRoll();

                // Evaluate the rolls
                game.evalBetDoubles();
                game.evalBetSame();
                game.evalBetO();
                game.evalBetU();
                game.evalBetIncorrect();
                
                game.prtWL();
                game.updateHistory(round-1);
                
                game.prtEndOfRound();
                
                // Check for bankruptcy
                if (game.isBankrupt()) {
                	System.out.println("\n************************************************************");
                	System.out.println("\nYou are bankrupt. GAME OVER!\n");
                    break;
                }
            }
            if (!game.isBankrupt()) {
            	System.out.println("\n************************************************************\n");
            	System.out.println("Game over!  Final points were " + game.getPoints() + ". Thank you for playing, " + game.getName());
            	game.prtHistory();
            }
            // Ask if the player wants to play again
            System.out.print("\nWould you like to play again? Enter Y or N: ");

            String playAgain = scan.next().toUpperCase();
            game.setPlayAgain(playAgain);
            ///checking whether play again Y or N
            while(!playAgain.equals("Y") && !playAgain.equals("N")) {
            	System.out.print("\nSorry, you must enter a Y or N.  Try again: ");
            	playAgain = scan.next().toUpperCase();
            	game.setPlayAgain(playAgain);
            	//System.out.println("playAgain: " + playAgain);
            }
            
            
        } while (game.getPlayAgain().equals("Y"));

        // Print out
        game.setGames(games);
        game.prtEndOfGame();
        System.out.println("Have a great day, " + game.getName() + "!");
    }
}


