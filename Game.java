package project4.game;

import java.util.Random;
import java.util.Scanner;

public class Game extends Session{
	private Scanner scan = new Scanner(System.in);
	private int rounds;
	private int round;
	private int rollNum = 0;
	private int r1d1;
	private int r1d2;
	private int r1Sum;
	private int r2d1;
	private int r2d2;
	private int r2Sum;
	private String bet = "X";	 // When set, it will be either O or U 
	private int wager = 0;
	private String outcome = "X";  // One of O, U, D, S
	private String eval = "X";  // Either W or L
	private int points = 20;
	private String playAgain = "N";
	private String[] history;  // New array to store history of rounds during a set of rounds
	private boolean bankrupt = false;
	private int retCode  = -1; //The variable that stores the returned value of EH methods.
	private ExceptionHandling eh = new ExceptionHandling();
	// Define methods
	//set/getPoints  // Remember to re-initialize points after each set of rounds, also
	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}	

	//set/getRounds
	public int getGameRounds() {
		return rounds;
	}
	public void getRounds() {
		retCode = -1;
		String strRounds="";
		while(retCode !=0) {
            // Prompt for and read-in the number of rounds
            System.out.print("\nHow many rounds, between 1 and 5 inclusive, do you wish to play? ");			
            strRounds = scan.next();
            //System.out.println("before retCode: " + retCode);
            retCode = eh.ckRounds(strRounds);
            //System.out.println("after retCode: " + retCode);
		}
		
		setRounds(Integer.parseInt(strRounds));
		
	}

	public void setRounds(int rounds) {
		this.rounds = rounds;
		history = new String[rounds];
	}

	//set/getRound
	
	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public int getRollNum() {
		return rollNum;
	}

	public void setRollNum(int rollNum) {
		this.rollNum = rollNum;
	}

	public int getDieValue() {
		Random rand = new Random();
		return rand.nextInt(6) + 1;
	}

	public void setRoll1(int r1d1, int r1d2) {
		System.out.println("Rolling the dice for roll_1...");
		eh.rollPause();
		this.r1d1 = r1d1;
		this.r1d2 = r1d2;
		this.r1Sum = this.r1d1 + this.r1d2;
	}

	public void setRoll2(int r2d1, int r2d2) {
		System.out.println("Rolling the dice for roll_2...");
		eh.rollPause();
		this.r2d1 = r2d1;
		this.r2d2 = r2d2;
		this.r2Sum = this.r2d1 + this.r2d2;
	}
	
	public void prtRoll() {
		System.out.println("\nRoll_"+ rollNum + " was " + r1d1 + "," + r1d2 + " for a sum of " + r1Sum);
	}
	public void getBet() {
		retCode = -1;
		String strBet="";
		while(retCode !=0) {
			 // Prompt for and read-in bet
			 System.out.print("\nEnter the letter O or U to indicate if you believe Roll_2 will be over \n(O) or under (U) Roll_1: ");		
			 strBet = scan.next().toUpperCase();
            //System.out.println("before retCode: " + retCode);
            retCode = eh.ckBet(strBet);
            //System.out.println("after retCode: " + retCode);
		}
		setBet(strBet);
	}
	public void setBet(String bet) {
		this.bet = bet;
	}
	public void getWager() {
		retCode = -1;
		String strWager="";
		while(retCode !=0) {

			 System.out.print("\nEnter the number of points, between 1 and " + points + " inclusive, that you wish to wager: ");		
            strWager = scan.next();
            //System.out.println("before retCode: " + retCode);
            retCode = eh.ckWager(strWager,points);
            //System.out.println("after retCode: " + retCode);
		}
		
		setWager(Integer.parseInt(strWager));
	}
	public void setWager(int wager) {
		this.wager = wager;
	}
	public void evalBetO() {
		if ((bet.equals("O") && r2Sum > r1Sum)) {
            outcome = "WIN"; // Player wins based on bet
            eval = "W";	
		}
	}
	public void evalBetU(){
		if ((bet.equals("U") && r2Sum < r1Sum)) {
            outcome = "WIN"; // Player wins based on bet
            eval = "W";
		}
	}
	public void evalBetDoubles() {
        if (r2d1 == r2d2) {
            outcome = "DOUBLE"; // Double, automatically lose
            eval = "L";
        }
	}
	public void evalBetSame() {
		if (r2Sum == r1Sum) {
            outcome = "SAME_SUM"; // Same sum, automatically lose
            eval = "L";
		}
	}
	public void evalBetIncorrect() {
		if (eval.equals("X")) {
            outcome = "LOSE"; // Player loses based on incorrect bet
            eval = "L";
		}
	}	
	public void prtWL() {
        // Switch for win/lose processing
        switch (eval) {
            case "W":
                System.out.println("\nCongratulations! YOU WON THIS ROUND!!!");
                points += wager;
                break;
            case "L":
                System.out.println("\nSorry, you've lost the round.");
                points -= wager;
                break;
        }
	}
	
	public void prtEndOfRound() {
        // Print game round summary using printf
        System.out.printf("\nRoll_1: %d, Roll_2: %d, Bet: %s, Wager: %d, Outcome: %s, Points: %d%n",
                r1Sum, r2Sum, bet, wager, outcome, points);
	}

	public boolean isBankrupt() {
        // Check for bankruptcy
        bankrupt = (points <= 0);
		return bankrupt;
	}

	public void setBankrupt(boolean bankrupt) {
		this.bankrupt = bankrupt;
	}
	
	public String getPlayAgain() {
		return playAgain;
	}

	public void setPlayAgain(String playAgain) {
		this.playAgain = playAgain;
	}	
	public void prtEndOfGame() {
        // Print outro
        System.out.println("\nThank you for playing OVER UNDER DOUBLE SAME.");
	}
	
	public void updateHistory(int round) {
		history[round] = r1Sum + " " + r2Sum + " " + bet + " " + wager + " " + outcome + " " + points;
	}
	
	public void prtHistory() {
		System.out.println("\nHere is your history of rounds for this game.");
		for (int i = 0; i < history.length; i++) {
			System.out.println(history[i]);
		}
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return super.name;
	}

	@Override
	public void setName(String name) {
		super.name = name;
		
	}
}


