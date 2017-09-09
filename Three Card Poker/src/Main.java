import java.util.Random;

import javax.swing.JFrame;

import java.awt.EventQueue;
import java.util.*;
public class Main extends JFrame{

	public static void main(String[] args) {
		Game game = new Game();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window(game);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		
	}
	
	
	
}