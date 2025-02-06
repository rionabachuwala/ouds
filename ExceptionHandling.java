package project4.game;

import java.util.concurrent.TimeUnit;

public class ExceptionHandling {
	// Used as a flag var with values 0 or 1, where value of 0 is no error is present, 
	//1 is error present
	private int errCode = -1;  
	
	// Entered value for rounds must be integer between 1 and 5
	public int ckRounds(String strRounds) {
		int rounds =0;
		try {
			// if not an integer, auto throws to catchNumberFormatException
			//String vars strRounds must be checked for NumberFormatException.
			rounds = Integer.parseInt(strRounds);

			// Numeric vars rounds must be checked for IllegalArgument exception.
			if (rounds < 1 || rounds > 5) {
				throw new IllegalArgumentException();
			}
			errCode = 0;
			
		} catch(NumberFormatException nfe) {
			System.out.println("Rounds must be an integer.");
			errCode = 1;
		} catch(IllegalArgumentException iae) {
			System.out.println("Rounds must be between 1 and 5 inclusive.");
			errCode = 2;
		}
		return errCode;
	}
	
	// Entered value for var bet must be "O" or "U"
	public int ckBet(String strBet) {
		
		try {
			//String vars strRounds must be checked for IllegalArgumentException.
			if (!strBet.equals("O") && !strBet.equals("U")) {
				throw new IllegalArgumentException();
			}
			errCode = 0;

		} catch(IllegalArgumentException iae) {
			System.out.println("Bet must be either O or U.");
			errCode = 2;
		}
		return errCode;
	}	

	// Entered value for wager must be integer between 0 and their points total
	public int ckWager(String strWager, int totalPoints) {
		int wager =0;
		try {
			// if not an integer, auto throws to catch(NFE)
			//String vars converting into number and must be checked for NumberFormatException.
			wager = Integer.parseInt(strWager);
			
			// Numeric vars rounds must be checked for IllegalArgumentException.
			if (wager < 1 || wager > totalPoints) {
				throw new IllegalArgumentException();
			}
			errCode = 0;
			
		} catch(NumberFormatException nfe) {
			System.out.println("Wager must be an integer.");
			errCode = 1;
		} catch(IllegalArgumentException iae) {
			System.out.println("Wager must be between 1 and your point total "+ totalPoints + ", inclusive.");
			errCode = 2;
		}
		return errCode;
	}	

	public void rollPause() {
		int secondsToSleep = 2;
		try { 	TimeUnit.SECONDS.sleep(secondsToSleep); 
		} 
		catch (InterruptedException ie) { 	Thread.currentThread().interrupt(); 
		}

	}	
	
}
