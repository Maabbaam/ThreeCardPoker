import java.awt.EventQueue;
import java.util.Scanner;

public class Game {
	Player dealer = new Player("Dealer");
	Player player = new Player("Player");
	Deck d = new Deck();
	int pile = 0;
	int anteBet = 0; //antebet
	int pairPlusBet = 0; //pair plus bet
	int winner = 0;
	int orginalAnteBet = 0;

	public void play() {
		//System.out.println("Pile Value: " + pile);
		 System.out.println("Pile bf :" + pile);
		 System.out.println("The Player loses money here");
		 System.out.println("Player's Balance " + player.playerBalance);
		 
		 player.bet(anteBet);
		 System.out.println("Player's Balance " + player.playerBalance);
		 
		 winner = player.whoWins(dealer);
		 
		 //System.out.println("Pile Value: " + pile);
		 
		// int bool =  doesDealerQualify();
		 
		// if (bool == 1) pile = pile + (anteBet * 4 );
		// else pile = pile + (anteBet * 3 );
		 
		 
		
		 
		 System.out.println("Pile :" + pile);
		 //System.out.println("Pile Value: " + pile);
		 
		 //player.playerBalance = player.playerBalance - anteBet;
		 
		 //System.out.println(player.playerBalance);
		 
		 //System.out.println("asfasfsadf");
		 
		 
		 System.out.println("");
	}
	
	public void fold() {
		player.whoWins(dealer);
		winner = 2;
	}
	
	public int playerWinnings() {
		return anteWinnings() + playWinnings() + anteBonusWinnings() + pairPlusWinnings();
	}
	
	public int anteWinnings() {
		if (player.folded) {
			return 0;
		}
		if (doesDealerQualify() && player.whoWins(dealer) == 1) { //Player Wins and Dealer does qualify
			return anteBet * 2;
		}
		else if (!doesDealerQualify() && player.whoWins(dealer) == 1) {
			return anteBet;
		}
		return 0;
	}
	
	public int playWinnings() {
		if (player.folded) {
			return 0;
		}
		if (player.whoWins(dealer) == 1) { //Player Wins
			return anteBet * 2;
		}
		return 0;
	}
	
	public int anteBonusWinnings() {
		if (player.folded) {
			return 0;
		}
		if (player.cardType == 4) { //Straight
			return anteBet * 1;
		}
		else if (player.cardType == 5) { //Three of a kind
			return anteBet * 4;
		}
		else if (player.cardType == 6) { //Straight Flush
			return anteBet * 5;
		}
		return 0;
	}
	
	public int pairPlusWinnings() {
		if (player.folded) {
			return 0;
		}
		if (player.cardType == 2) { //Pair
			return pairPlusBet * 1;
		}
		else if (player.cardType == 3) { //Flush
			return pairPlusBet * 4;
		}
		else if (player.cardType == 4) { //Straight
			return pairPlusBet * 6;
		}
		else if (player.cardType == 5) { //Three of a kind
			return pairPlusBet * 30;
		}
		else if (player.cardType == 6) { //Straight Flush
			return pairPlusBet * 40;
		}
		return 0;
	}
	
	public void payPlayer() {
	
		// Base Game
		if (doesDealerQualify() && player.whoWins(dealer) == 1) { //Player Wins and Dealer does qualify
			player.playerBalance += anteBet * 4;
		}
		else if (!doesDealerQualify() && player.whoWins(dealer) == 1) {
			player.playerBalance += anteBet * 3;
		}
	
		// Ante Bonuses	
		if (player.cardType == 4) { //Straight
			player.playerBalance += anteBet * 1;
		}
		else if (player.cardType == 5) { //Three of a kind
			player.playerBalance += anteBet * 4;
		}
		else if (player.cardType == 6) { //Straight Flush
			player.playerBalance += anteBet * 5;
		}

			
		// Pair Plus Bets
		if (player.cardType == 2) { //Pair
			player.playerBalance += pairPlusBet * 1;
		}
		else if (player.cardType == 3) { //Flush
			player.playerBalance += pairPlusBet * 4;
		}
		else if (player.cardType == 4) { //Straight
			player.playerBalance += pairPlusBet * 6;
		}
		else if (player.cardType == 5) { //Three of a kind
			player.playerBalance += pairPlusBet * 30;
		}
		else if (player.cardType == 6) { //Straight Flush
			player.playerBalance += pairPlusBet * 40;
		}
		
		player.folded = false;
	}
	
	public boolean doesDealerQualify() {
		int cardType = dealer.determineCardType();
		
		if (cardType != 1) return true;
		
		else {
			if (dealer.cards.get(0).value == 1 || dealer.cards.get(0).value == 12 || dealer.cards.get(0).value == 13 ) return true;
			if (dealer.cards.get(1).value == 1 || dealer.cards.get(1).value == 12 || dealer.cards.get(1).value == 13 ) return true;
			if (dealer.cards.get(2).value == 1 || dealer.cards.get(2).value == 12 || dealer.cards.get(2).value == 13 ) return true;
			
			return false;
		}
	}
	
	public void resetBets() {
		anteBet = 0;
		pairPlusBet = 0;
		pile = 0;
		orginalAnteBet = 0;
		
	}
	
	public void placeAnteBet(int a) {
		anteBet = anteBet + (a);
		player.playerBalance = player.playerBalance - a;
		
		System.out.println("Pile before Ante :" + pile);
		//pile = pile + anteBet;
		 System.out.println("Pile :" + pile);
	}
	
	public void placePairPlusBet(int p) {
		pairPlusBet = pairPlusBet + (p);
		player.playerBalance = player.playerBalance - p;
		 System.out.println("Pile :" + pile);
		pile = pile + pairPlusBet * 2;
		 System.out.println("Pile :" + pile);
	}
	
	public void dealCards () {
		/*dealer.drawCard(d);
		dealer.drawCard(d);
		dealer.drawCard(d);*/
		/*
		 dealer.addCard(new Card("Clubs", "5",5, false),
			 		new Card("Hearts", "4",4, false),
			 		new Card("Spades", "3",3, false));
		
		dealer.print();
		
		 //Card c  = new Card("Clubs", "Ace",1, false);
		// player.cards.add(c);
		 player.addCard(new Card("Clubs", "2",2, false),
				 		new Card("Hearts", "3",3, false),
				 		new Card("Spades", "4",4, false));
		*/
		//Card c  = new Card("Clubs", "Ace",1, false);
		 
		
		 

		dealer.drawCard(d);
		dealer.drawCard(d);
		dealer.drawCard(d);
		
		player.drawCard(d);
		player.drawCard(d);
		player.drawCard(d);
		  
		

		 
		 
		
		 player.print();
	
		 System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		 
		 System.out.println(d.cards.size());
		 System.out.println(dealer.cards.size());
		 System.out.println(player.cards.size());
	}
	
	/*public int declarewinner() {
		
		
		//int bool = doesDealerQualify();
		
		
		winner = player.whoWins(dealer);
		
		
		 System.out.println(winner);
		 
		 // 1 if the player wins
		 // 2 if the dealer wins
		  if (winner ==  1){
			  
			  System.out.println("YOU WON !!!!!!!!!!!!!!!!!!!!!!!!!!!");
			  
			  //System.out.println(player.playerBalance);
			  //player.playerWins(pile);
			  //System.out.println("Pile Value After Win: " + pile);
			   //System.out.println(player.playerBalance);
			  
		  }
		  
		  else if (winner == 2 ){
			  System.out.println("YOU LOST LOSER!!!!!");
			  System.out.println("this is how much you have left");
			  System.out.println(player.playerBalance);
			  
		  }
		  
		  else if(winner == 3){
			  
			  System.out.println("YOU TIED !!!!!!!!!!!!!!!!!");
			  System.out.println("this is how much you have left");
			 
			  
			  //player.playerWins(pile/2);
			 System.out.println(player.playerBalance);
			
			
		  }
		 
		  else  System.out.println("How embarassing this should never happen");
		  
		  
		  //anteBet = 0; //Reset the ante bet
		  //pairPlusBet = 0; // Reset the pair plus bet
		  //pile = 0;
		  
		 System.out.println("/********************END OF GAME******************************");
	}*/
	
	public void resuffleCards() {
	
		
		dealer.cards.removeAll(dealer.cards);
		player.cards.removeAll(player.cards);
		
		
		
		
		 //System.out.println(dealer.cards.size());
		 //System.out.println(player.cards.size());
		 
		 d = new Deck();
		 //System.out.println(d.cards.size());
		 
		
		
	}
	
	public Game() {
		
		
		//readstuff();
		//dealCards();
		//readMoreStuff();
		//play();
		//declarewinner();
		
	

		 
		 
		
	}
}

