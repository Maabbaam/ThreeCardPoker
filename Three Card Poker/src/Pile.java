public class Pile extends CardHolder {
	int total;
	int limit;
	
    // Default Constructor
    public Pile() {
    	super("Pile");
	}
    
    public void clear() {
    	total = 0;
    	cards.clear();
    }
    
    public void print() {
    	System.out.println("The " + cardHolderName + "'s Total is " + total + ".");
    	//super.print();
    }
 
    public String addCard(Card cardBeingPlayed) {
    	// Exceeds Pile's limit
    	if (total + cardBeingPlayed.value > limit) {
    		return "Exceeded";
    	}
    	else {
    		total += cardBeingPlayed.value;
    		cards.add(cardBeingPlayed);
    	}

    	return "";
    }
    
    public String information() {
    	return "The Pile's total is " + total;
    }
    
    public void setLimit(int i) {
    	limit = i;
    }
}