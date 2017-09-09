
public class Card{
	public int value;
	public String name;
	public String suit;
	public boolean faceCard;
	public boolean faceUp;
	
	public Card(String s, String n, int v, boolean f) {
		suit = s;
		name = n;
		value = v;
		faceCard = f;
		faceUp = true;
	}
	public void print() {
		System.out.print(name + " of " + suit); // + ": Value = " + value); // Testing Value
	}
	public String fullName() {
		return name + " of " + suit;
		
	}
}