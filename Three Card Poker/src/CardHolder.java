import java.util.ArrayList; // Used to contain many cards

public class CardHolder {
	String cardHolderName;
	ArrayList<Card> cards = new ArrayList<Card>();
	
	public CardHolder(String s) {
		cardHolderName = s;
	}

	public void print() {
		System.out.println("Cards in the " + cardHolderName + ": ");
		
		for (int i = 0; i < cards.size(); i++) {
			cards.get(i).print();
			
			// Spacing
			if (i < cards.size() - 1) {
				System.out.print(", ");
			}
			else {
				System.out.println();
			}
		}
	}
	
	public String information() {
		String information = "Cards in the " + cardHolderName + ": ";
		
		for (int i = 0; i < cards.size(); i++) {
			information += cards.get(i).fullName();
			
			// Spacing
			if (i < cards.size() - 1) {
				information += ", ";
			}
		}
		return information;
	}
	
	public void clear() {
		cards.clear();
	}
	
	public void moveAllCardsTo(CardHolder cardHolder) {
		cardHolder.cards.addAll(cards);
		clear();
	}
	
	/*
	public void addAllCards(CardHolder cardHolder) {
		this.cards.addAll(cardHolder.cards);
	}
	
	public void moveCardTo(Card c, CardHolder cardHolder) {
		
	}
	*/
}
