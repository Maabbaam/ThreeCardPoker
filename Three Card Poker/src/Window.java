import java.awt.EventQueue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class Window extends JFrame implements ActionListener{
	public JFrame frame;
	private int numCards = 3;
	private int numBets = 6;
	
	private JLabel[] playerCards = new JLabel[numCards];
	private JLabel[] dealerCards = new JLabel[numCards];
	
	private JLabel playersHand = new JLabel();
	private JLabel dealersHand = new JLabel();
	//private JLabel winner = new JLabel();

	private RoundButton[] bets = new RoundButton[numBets];
	private int[] betAmounts = {1,5,10,25,50,100};
	private Color[] betColors = {Color.WHITE,Color.blue,Color.green,Color.red,Color.yellow,Color.gray};
	
	private JButton play;
	private JButton fold;
	private JButton pairPlus;
	private JButton ante;
	private JButton deal;
	private JButton continueButton;
	
	private int screenWidth = 1080;
	private int screenHeight = 720;
	
	private JLabel playerBalance;
	private JLabel anteBet;
	private JLabel pairPlusBet;
	private JLabel instructions;
	
	private int anteNum = 0;
	private int pairPlusNum = 0;
	private int betNum = 0;
	
	private int cardYPlayer = screenHeight - 300;
	private int cardYDealer = 175;
	private int cardSpacing = 50;
	private int cardWidth = 71;
	private int cardHeight = 96;
	private int cardX = (int) ((screenWidth / 2) - (cardWidth * ((double)numCards / 2)) - (cardSpacing * (((double)numCards - 1) / 2)));
	
	private int betY = 600;
	private int betSpacing = 10;
	private int betWidth = 55;
	private int betHeight = 55;
	private int betX = (int) ((screenWidth / 2) - (betWidth * ((double)numBets / 2)) - (betSpacing * (((double)numBets - 1) / 2)));
	
	private int playX = 375;
	private int playY = betY + 10;
	private int playWidth = 140;
	private int playHeight = 40;
	
	private int foldX = 575;
	private int foldY = betY + 10;
	private int foldWidth = 140;
	private int foldHeight = 40;
	
	private int dealX = 850;
	
	Game game;

	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/
	 
	public Window(Game g) {
		game = g;
		frame = new JFrame();
		frame.setTitle("Three Card Poker");
		frame.setBounds(0, 0, screenWidth, screenHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
	    
		frame.setContentPane(new JLabel(new ImageIcon(this.getClass().getResource("/images/Three Card Poker Background.png"))));
		frame.getContentPane().setLayout(null);
		

		playersHand = new JLabel("");
		playersHand.setBounds(cardX + (cardWidth * numCards) + (cardSpacing * numCards),cardYPlayer,500,100);
		playersHand.setFont(new Font("Vladimir Script", Font.BOLD, 36));
		playersHand.setForeground(Color.WHITE);
		frame.getContentPane().add(playersHand);
		
		dealersHand = new JLabel("");
		dealersHand.setBounds(cardX + (cardWidth * numCards) + (cardSpacing * numCards),cardYDealer,500,100);
		dealersHand.setFont(new Font("Vladimir Script", Font.BOLD, 35));
		dealersHand.setForeground(Color.WHITE);
		frame.getContentPane().add(dealersHand);
		
		playerBalance = new JLabel("");
		playerBalance.setBounds(110,-32,100,100);
		playerBalance.setFont(new Font("Vladimir Script", Font.BOLD, 36));
		playerBalance.setForeground(Color.WHITE);
		frame.getContentPane().add(playerBalance);
		
		anteBet = new JLabel("");
		anteBet.setBounds(460,-32,500,100);
		anteBet.setFont(new Font("Vladimir Script", Font.BOLD, 36));
		anteBet.setForeground(Color.WHITE);
		frame.getContentPane().add(anteBet);
	
		pairPlusBet = new JLabel("");
		pairPlusBet.setBounds(890,-32,500,100);
		pairPlusBet.setFont(new Font("Vladimir Script", Font.BOLD, 36));
		pairPlusBet.setForeground(Color.WHITE);
		frame.getContentPane().add(pairPlusBet);
		
		
		instructions = new JLabel("Place Your Bets!");
		instructions.setBounds(415,290,500,100);
		instructions.setFont(new Font("Vladimir Script", Font.BOLD, 36));
		instructions.setForeground(Color.WHITE);
		frame.getContentPane().add(instructions);
		
		/*
		winner = new JLabel("");
		winner.setBounds(425,290,500,100);
		winner.setFont(new Font("Vladimir Script", Font.BOLD, 55));
		winner.setForeground(Color.WHITE);
		frame.getContentPane().add(winner);
		*/
		
		
		  
		for (int i = 0; i < playerCards.length; i++) {
			playerCards[i] = new JLabel("");
			playerCards[i].setBounds(cardX + ((cardSpacing + cardWidth) * i), cardYPlayer, cardWidth, cardHeight);
			frame.getContentPane().add(playerCards[i]);
		}
		  
		for (int i = 0; i < dealerCards.length; i++) {
			dealerCards[i] = new JLabel("");
			dealerCards[i].setBounds(cardX + ((cardSpacing + cardWidth) * i), cardYDealer, cardWidth, cardHeight);
			frame.getContentPane().add(dealerCards[i]);
		}
		
		for (int i = 0; i < betAmounts.length; i++) {
			bets[i] = new RoundButton(Integer.toString(betAmounts[i]), betColors[i]);
			bets[i].addActionListener(this); 
			bets[i].setBounds(betX + ((betSpacing + betWidth) * i), betY, betWidth, betHeight);
			frame.getContentPane().add(bets[i]);
		}
		
		play = new RoundButton("Play", Color.white);
		play.setBounds(playX,playY,playWidth,playHeight);
		play.addActionListener(this);
		frame.getContentPane().add(play);
		
		fold = new RoundButton("Fold", Color.white);
		fold.setBounds(foldX,foldY,foldWidth,foldHeight);
		fold.addActionListener(this); 
		frame.getContentPane().add(fold);
		
		deal = new RoundButton("Deal", Color.white);
		deal.setBounds(dealX,foldY,foldWidth,foldHeight);
		deal.addActionListener(this);
		frame.getContentPane().add(deal);
		
		
		
		continueButton = new RoundButton("Continue", Color.white);
		continueButton.setBounds(dealX,foldY,foldWidth,foldHeight);
		continueButton.addActionListener(this);
		frame.getContentPane().add(continueButton);
		
		ante = new RoundButton("Ante", Color.white);
		ante.setBounds(playX,playY,foldWidth,foldHeight);
		ante.addActionListener(this);
		frame.getContentPane().add(ante);
		
		pairPlus = new RoundButton("Pair Plus", Color.white);
		pairPlus.setBounds(foldX,foldY,foldWidth,foldHeight);
		pairPlus.addActionListener(this);
		frame.getContentPane().add(pairPlus);
		
		// This update is only a test
		//update(new Player(),new Player(), "betting");
		
		// Button which start disabled
		play.setVisible(false);
		fold.setVisible(false);
		ante.setVisible(false);
		pairPlus.setVisible(false);
		deal.setVisible(false);
		continueButton.setVisible(false);
		//winner.setVisible(false);
		
		for (int i = 0; i < 3; i++) {
			playerCards[i].setVisible(false);
			dealerCards[i].setVisible(false);
		}
		
		playerBalance.setText("$" + "1000");
		anteBet.setText("$" + "0");
		pairPlusBet.setText("$" + "0");
		
		
		
	}

	public void update(Player player, Player dealer, String gamestate) {
		
		playerBalance.setText("$" + player.playerBalance);
		
		//System.out.println("The player's balance is" + player.playerBalance);
		
		anteBet.setText("$" + game.anteBet);
		pairPlusBet.setText("$" + game.pairPlusBet);
		
		if (gamestate == "reset") {
			for (int i = 0; i < betAmounts.length; i++) {
				bets[i].setVisible(true);
			}
			
			for (int i = 0; i < 3; i++) {
				playerCards[i].setVisible(false);
				dealerCards[i].setVisible(false);
			}
			for (int i = 0; i < player.cards.size(); i++) {
				playerCards[i].setIcon(new ImageIcon(this.getClass().getResource("/images/Card Back.png")));
				dealerCards[i].setIcon(new ImageIcon(Window.class.getResource("/images/Card Back.png")));
			}
			continueButton.setVisible(false);
			playersHand.setVisible(false);
			dealersHand.setVisible(false);
			instructions.setVisible(true);
			//winner.setVisible(false);
		}
		
		else if (gamestate == "betting") {
			for (int i = 0; i < betAmounts.length; i++) {
				bets[i].setVisible(true);
			}

			ante.setVisible(false);
			pairPlus.setVisible(false);
			deal.setVisible(true);
		}
		else if (gamestate == "betplacement") {
			
			for (int i = 0; i < betAmounts.length; i++) {
				bets[i].setVisible(false);
			}
			ante.setVisible(true);
			pairPlus.setVisible(true);
			deal.setVisible(false);
			
		}
		else if (gamestate == "dealing") {
			for (int i = 0; i < betAmounts.length; i++) {
				bets[i].setVisible(false);
			}
			
			for (int i = 0; i < player.cards.size(); i++) {
				playerCards[i].setVisible(true);
				String name = player.cards.get(i).name;
				String suit = player.cards.get(i).suit;
				//System.out.println("Are any of these null? " + "/images/" + name + " of " + suit + ".png");
				playerCards[i].setIcon(new ImageIcon(this.getClass().getResource("/images/" + name + " of " + suit + ".png")));
				}
			
			for (int i = 0; i < dealer.cards.size(); i++) {
				dealerCards[i].setVisible(true);
				dealerCards[i].setIcon(new ImageIcon(Window.class.getResource("/images/Card Back.png")));
			}
			instructions.setVisible(false);
			deal.setVisible(false);
			play.setVisible(true);
			fold.setVisible(true);
		}
		else if (gamestate == "finished") {
			
			
			/*
			if (game.winner == 1) {
				winner.setText("You Win");
			}
			else if (game.winner == 2) {
				winner.setText("You Lose");
			}
			else if (game.winner == 3) {
				winner.setText("Tie Game");
			}
			winner.setVisible(true);
			*/
			
			for (int i = 0; i < betAmounts.length; i++) {
				bets[i].setVisible(false);
			}
			
			for (int i = 0; i < player.cards.size(); i++) {
				playerCards[i].setVisible(true);
				String name = player.cards.get(i).name;
				String suit = player.cards.get(i).suit;
				playerCards[i].setIcon(new ImageIcon(this.getClass().getResource("/images/" + name + " of " + suit + ".png")));
				}
			
			for (int i = 0; i < dealer.cards.size(); i++) {
				dealerCards[i].setVisible(true);
				String name = dealer.cards.get(i).name;
				String suit = dealer.cards.get(i).suit;
				dealerCards[i].setIcon(new ImageIcon(this.getClass().getResource("/images/" + name + " of " + suit + ".png")));
			}
			
			instructions.setVisible(false);
			deal.setVisible(false);
			play.setVisible(true);
			fold.setVisible(true);
			continueButton.setVisible(true);
			play.setVisible(false);
			fold.setVisible(false);
			playersHand.setVisible(true);
			dealersHand.setVisible(true);
			playersHand.setText(player.pokerHand);
			dealersHand.setText(dealer.pokerHand);
			anteNum = 0;
			pairPlusNum = 0;
			
		}
	}
	
	public void openPopUp() {
		JOptionPane.showMessageDialog(frame,
				"Your balance                                                       " + game.player.playerBalance + "\n" +
				"--------------------------------------------------------------------\n" + 
			    "Ante Winnings                                                     " + game.anteWinnings() + "\n" +
			    "Play Winnings                                                      " + game.playWinnings() + "\n" +
			    "Ante Bonus Winnings                                        " + game.anteBonusWinnings() + "\n" +
			    "Pair Plus Winnings                                             " + game.pairPlusWinnings() + "\n" +
			    "--------------------------------------------------------------------\n" + 
			    "Total Winnings                                                     " + game.playerWinnings() + "\n" +
			    "--------------------------------------------------------------------\n" + 
			    "Your new balance                                               " + (game.player.playerBalance + game.playerWinnings()),
			    "Payout Summary",
			    JOptionPane.PLAIN_MESSAGE);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if (((JButton) arg0.getSource()).getText() == "Deal") {
			game.dealCards();
			if (game.anteBet == 0) {
				game.play();
				update(game.player, game.dealer, "finished"); // Test
				openPopUp();
				game.payPlayer();
				game.resetBets();
			}
			else {
				update(game.player,game.dealer, "dealing"); // Test
			}
		}
		else if (((JButton) arg0.getSource()).getText() == "Play") {
			game.play();
			update(game.player, game.dealer, "finished"); // Test
			openPopUp();
			game.payPlayer();
			game.resetBets();
			
		}
		else if (((JButton) arg0.getSource()).getText() == "Fold") {
			game.player.folded = true;
			game.fold();
			update(game.player, game.dealer, "finished"); // Test
			openPopUp();
			game.resetBets();
		}
		else if (((JButton) arg0.getSource()).getText() == "Ante") {
			game.placeAnteBet(betNum);
			betNum = 0;
			update(game.player,game.dealer, "betting"); // Test
		}
		else if (((JButton) arg0.getSource()).getText() == "Pair Plus") {
			game.placePairPlusBet(betNum);
			betNum = 0;
			update(game.player,game.dealer, "betting"); // Test
		}
		else if (((JButton) arg0.getSource()).getText() == "Continue") {
			game.resuffleCards();
			update(game.player,game.dealer, "reset"); // Test
		}
		else {
			betNum = Integer.parseInt(((JButton) arg0.getSource()).getText());
			update(game.player,game.dealer, "betplacement"); // Test
		}
		
	}
}