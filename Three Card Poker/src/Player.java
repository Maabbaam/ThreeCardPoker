import java.util.ArrayList;
import java.util.Collections;



public class Player extends CardHolder {
 String username;
 boolean readyToPlay = false;
 boolean drewLastTurn; // Used to track when the pile needs to be cleared
    boolean ableToPlay; // Players are only able to play on their turn
    boolean folded = false;
    int wins;
    boolean faceDown;
    int playerBalance;
    String pokerHand;
    int cardType; // 1 = High Card
         // 2 = Pair
         // 3 = Flush
         // 4 = Straight
         // 5 = 3 of a Kind
         // 6 = Straight Flush
    
    ArrayList<String> messages = new ArrayList<String>(); //When we populate this it will be sent to the correct client then removed
   
    
    // Default Constructor
    public Player(String n) {
     
     super("Hand");
     username = n;
     cardType = 0; //means you have not been dealt cards
     pokerHand = null;
     playerBalance = 1000;
     
 }
    
    public void bet(int i){
     
     playerBalance = playerBalance - i;
     
     
    }
    
    
    
    public void playerWins(int i){
     playerBalance = playerBalance  + i;
     
    }
    
    public void ante(int i){
     playerBalance = playerBalance - i;
     
    }
 
    public void addCard(Card c1, Card c2, Card c3){
     
     
     cards.add(c1);
     cards.add(c2);
     cards.add(c3);
     
     
    }
 // Draw the top card
 public void drawCard(Deck deck) {
  
   Card topCard = deck.cards.get(0);
   cards.add(topCard);
   deck.cards.remove(topCard);
   drewLastTurn = true;
  }
 
 
 public void givePlayerHand(Deck d){
  // Card 1
  Card removeCard = d.cards.get(0);
  cards.add(d.cards.get(0));
  d.cards.remove(removeCard);
   
  //Card 2
  removeCard = d.cards.get(0);
  cards.add(d.cards.get(0));
  d.cards.remove(removeCard);
  
  //Card 3
  removeCard = d.cards.get(0);
  cards.add(d.cards.get(0));
  d.cards.remove(removeCard);
  
 }
  
 
 
 public String playCard(Card cardBeingPlayed, Pile pile) {
  
  String result = pile.addCard(cardBeingPlayed);
  
  // Remove the card
  if (!result.equals("Exceeded")) {
   cards.remove(cardBeingPlayed);
  }
  
  drewLastTurn = false;
  
  return result;
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
 public void sendAllCardsToDeck(Deck deck) {
  deck.cards.addAll(cards);
  cards.clear();
 }

 public Card getCard(String command) {  
  // Adjust command to make comparing easier
  switch (command.toUpperCase()) {
   case "A": command = "ACE"; break;
   case "J": command = "JACK"; break;
   case "Q": command = "QUEEN"; break;
   case "K": command = "KING"; break;
  }
  
  // Go through the player's hand looking a card match
  for (int i = 0; i < cards.size(); i++) {
   if (command.equalsIgnoreCase(cards.get(i).name)) {
    return cards.get(i);
   }
  }
  
  // The card is not found in the hand
  return null;
 }
 
 public String hand() {
  String information = "Your Hand: ";
  
  // Add all cards to string
  for (int i = 0; i < cards.size(); i++) {
   information += cards.get(i).fullName();
   
   // Spacing between cards
   if (i < cards.size() - 1) {
    information += ", ";
   }
  }
  return information;
 }
 
 public boolean doesPlayerHaveStraightFlush(){
  Boolean t;
  
  Card c1 = cards.get(0);
  Card c2 = cards.get(1);
  Card c3 = cards.get(2);
  
 ArrayList<Integer> cardVaules = new ArrayList<Integer>();
  
  cardVaules.add(c1.value);
  cardVaules.add(c2.value);
  cardVaules.add(c3.value);
  
  Collections.sort(cardVaules);
  
  Boolean b;
  
  if (c1.suit.equals(c2.suit) && c2.suit.equals(c3.suit)){
   
   int  i = cardVaules.get(0);
   
   
   
   int  i2 = i + 1;
   int i3 = i + 2;
   
   
   
   if (c1.value == 1 && c2.value == 12 && c3.value == 13) return true; 
   
  
  
   if ( (i2 == cardVaules.get(1)) && (i3 == cardVaules.get(2)) )return true;
     
   b = false;
  }
  
  else
   return false;
  
  
  
  return b;
  
  }
 
 public boolean doesPlayerHaveThreeOfaKind(){
   Card c1 = cards.get(0);
  Card c2 = cards.get(1);
  Card c3 = cards.get(2);
  
 ArrayList<Integer> cardVaules = new ArrayList<Integer>();
  
  cardVaules.add(c1.value);
  cardVaules.add(c2.value);
  cardVaules.add(c3.value);
  
  
  
  if (c1.value == c2.value && c2.value == c3.value) return true;
  
  return false;
  
 }
 public boolean doesPlayerHaveStraight(){
  
 
  
  Card c1 = cards.get(0);
  Card c2 = cards.get(1);
  Card c3 = cards.get(2);
  
 ArrayList<Integer> cardVaules = new ArrayList<Integer>();
  
  cardVaules.add(c1.value);
  cardVaules.add(c2.value);
  cardVaules.add(c3.value);
  
  Collections.sort(cardVaules);
  
  Boolean b;
  
    
   int  i = cardVaules.get(0);
   
   int  i2 = i + 1;
   int i3 = i + 2;
   
   
   
   if (cardVaules.contains(1) && cardVaules.contains(12) &&  cardVaules.contains(13)) return true; 
   
  
  
   if ( (i2 == cardVaules.get(1)) && (i3 == cardVaules.get(2)) )return true;
   
   
     
   return false;
 }
 
 public boolean doesPlayerHaveFlush(){

  Card c1 = cards.get(0);
  Card c2 = cards.get(1);
  Card c3 = cards.get(2);
  
  if (c1.suit.equals(c2.suit) && c2.suit.equals(c3.suit)) return true;
  
  return false;
  
  
  
 }
 
 
 public boolean doesPlayerHavePair(){
  
   Card c1 = cards.get(0);
  Card c2 = cards.get(1);
  Card c3 = cards.get(2);
  
  if (c1.value == c2.value || c2.value == c3.value || c1.value == c3.value) return true;
  
  return false;
  
  
 }
 
 
 public int determineCardType(){
  
  if (this.doesPlayerHaveStraightFlush()){
   this.cardType = 6;
   this.pokerHand = "Straight Flush";
   return 6;
   
  }
  
  if (this.doesPlayerHaveThreeOfaKind()){
   this.cardType = 5;
   this.pokerHand = "Three of a Kind";
   return 5;
   
  }
  
  if (this.doesPlayerHaveStraight()){
   this.cardType = 4;
   this.pokerHand = "Straight";
   return 4;
   
  }
  if (this.doesPlayerHaveFlush()){
   this.cardType = 3;
   this.pokerHand = "Flush";
   return 3;
   
  }
  
  if (this.doesPlayerHavePair()){
   this.cardType = 2;
   this.pokerHand = "Pair";
   return 2;
   
  }
  
  this.pokerHand = "High Card";
  this.cardType = 1;
  return 1;
  
 }
 
 public int whoWins(Player dealer){
  // return 1 for player
  // return 2  for dealer
  
  this.determineCardType();
  dealer.determineCardType();
 
  
 System.out.println(this.cardType);
  System.out.println(dealer.cardType);
  
  
  if (dealer.cardType > this.cardType ) return 2;
  
 /* System.out.println(" TEst1 ");*/
  if (dealer.cardType < this.cardType ) return 1;
  
  

  
   Card c1 = cards.get(0);
  Card c2 = cards.get(1);
  Card c3 = cards.get(2);
  ArrayList<Integer> cardVaules = new ArrayList<Integer>();
  cardVaules.add(c1.value);
  cardVaules.add(c2.value);
  cardVaules.add(c3.value);
  
  Collections.sort(cardVaules);
  
   System.out.println("The player Highest care:" + cardVaules.get(2));
   
  
  int p = cardVaules.get(2);
  
  
  Card d1 = dealer.cards.get(0);
  Card d2 = dealer.cards.get(1);
  Card d3 = dealer.cards.get(2);
  ArrayList<Integer> dealCardVaules = new ArrayList<Integer>();
  dealCardVaules.add(d1.value);
  dealCardVaules.add(d2.value);
  dealCardVaules.add(d3.value);
  
  Collections.sort(dealCardVaules);
  int d= dealCardVaules.get(2);
  
  
  
  // System.out.println(d);
   //System.out.println(p);
   
   int dMiddleCard =  dealCardVaules.get(1);
   int pMid = cardVaules.get(1);
 /*  System.out.println("****************");
   System.out.println(pMid);
   System.out.println(dMiddleCard);
   System.out.println(cardType);*/
   int notDealCardPair =0 ;
   int notCardPair =0 ;
   
   if (cardType == 4 ||  cardType == 6 ){
    System.out.println("***************************");
  /* if (cardVaules.contains(1) && cardVaules.contains(13) && (!dealCardVaules.contains(1) || !dealCardVaules.contains(13) )) return 1;
   if ((!cardVaules.contains(1) || !cardVaules.contains(13)) && dealCardVaules.contains(1) && dealCardVaules.contains(13) ) return 2;*/
   if (cardVaules.contains(1) && cardVaules.contains(13) && dealCardVaules.contains(1) && dealCardVaules.contains(13) ) return 3;
   System.out.println("***************************");
   if (d > p && dealCardVaules.contains(1) ) return 2;
   if (d < p && cardVaules.contains(1) ) return 1;
   
    
   System.out.println("***************************");
     if (d > p) return 2;
    if (d < p) return 1;
    
    if ( d==p) return 3;
    
   }
   
   if (cardType != 5 && dealer.cardType != 5){
   
   if ( dealCardVaules.get(1) ==  dealCardVaules.get(2)) notDealCardPair =  dealCardVaules.get(0);
   if ( dealCardVaules.get(1) ==  dealCardVaules.get(0)) notDealCardPair =  dealCardVaules.get(2);
   
   
   if ( cardVaules.get(1) ==  cardVaules.get(2)) notCardPair =  cardVaules.get(0);
   if ( cardVaules.get(1) ==  cardVaules.get(0)) notCardPair =  cardVaules.get(2);
   
   }
   if (cardType == 2){
    
    if (cardVaules.contains(1) && !dealCardVaules.contains(1)) return 1;
    if (dealCardVaules.contains(1) && !cardVaules.contains(1)) return 2;
    
    
    
    if (dMiddleCard > pMid) return 2;
    else if (dMiddleCard < pMid) return 1;
    
    
       
   
    else{
     
     if (notDealCardPair > notCardPair) return 2;
     else if (notDealCardPair < notCardPair) return 1;
     else return 3;
     
    
    }
    
    
   }
  
   if (cardType == 5 || cardType== 2 ){
  
   //\ System.out.println("***************************");
    
    if (cardVaules.contains(1) && !dealCardVaules.contains(1)) return 1;
    if (dealCardVaules.contains(1) && !cardVaules.contains(1)) return 2;
    
    if (dMiddleCard > pMid) return 2;
    if (dMiddleCard < pMid) return 1;
      
    
   }
   
   
   if (cardType == 4 ||  cardType == 6 ){
    System.out.println("***************************");
  /* if (cardVaules.contains(1) && cardVaules.contains(13) && (!dealCardVaules.contains(1) || !dealCardVaules.contains(13) )) return 1;
   if ((!cardVaules.contains(1) || !cardVaules.contains(13)) && dealCardVaules.contains(1) && dealCardVaules.contains(13) ) return 2;*/
   if (cardVaules.contains(1) && cardVaules.contains(13) && dealCardVaules.contains(1) && dealCardVaules.contains(13) ) return 3;
   System.out.println("***************************");
   if (d > p && dealCardVaules.contains(1) ) return 2;
   if (d < p && cardVaules.contains(1) ) return 1;
   
    
   System.out.println("***************************");
     if (d > p) return 2;
    if (d < p) return 1;
    
    if ( d==p) return 3;
    
   }

    
     
     //System.out.println("**************dfdf***********");
    if (cardVaules.contains(1) && !dealCardVaules.contains(1)) return 1;
   if (dealCardVaules.contains(1) && !cardVaules.contains(1)) return 2;
  
   if (d > p) return 2;
   if (d < p) return 1;
    
    
   if (d == p){
    
   
    if ( cardVaules.get(1) > dealCardVaules.get(1) )return 1;
    else if ( cardVaules.get(1) < dealCardVaules.get(1) )return 2;
    
    else {
     
     if ( cardVaules.get(0) > dealCardVaules.get(0) )return 1;
     else if ( cardVaules.get(0) < dealCardVaules.get(0) )return 2;
     
     else return 3;
    }
    
   }
    
    return  3;
  
  
 }
}