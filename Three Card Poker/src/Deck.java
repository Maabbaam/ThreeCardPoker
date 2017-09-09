import java.util.ArrayList;
import java.util.Collections; // Used to randomize the cards in the deck

public class Deck extends CardHolder {
    // Default Constructor
	// Set cardHolderName
	// Create 52 cards and add them to the deck's arraylist
	// Shuffle the deck
    public Deck() {
    	super("Deck");
    	
		String suits[] = {"Clubs", "Diamonds", "Hearts", "Spades"};
		String name[] = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
		int value[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
	  
		
		
	    for (int i = 0; i < 4; i++) {
	        for (int j = 0; j < 13; j++) {
	        	Card newCard;
	        	
	        	
	        		newCard = new Card(suits[i], name[j], value[j], false);
	
	            cards.add(newCard);
	        }
	    }
	    this.shuffle();
	}
    
    
    
	public void shuffle() {
		Collections.shuffle(cards);
	}
	
	
	
	public void giveCardsToPlayers(Player p1){
		
		
		
		
	}
	
	public Card topCard() {
		return cards.get(0);
	}
}
