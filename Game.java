// imports necessary libraries for Java swing
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Game Main class that specifies the frame and widgets of the GUI
 */
public class Game implements Runnable {
	public void run() {
		// NOTE : recall that the 'final' keyword notes inmutability
		// even for local variables.

		// Top-level frame in which game components live
		// Be sure to change "TOP LEVEL FRAME" to the name of your game
		final JFrame frame = new JFrame("TOP LEVEL FRAME");
		frame.setLocation(300, 300);
		
		//Creates strings for instructions
		final String gameplayString = "Game: Brick Breaker:"
				+ "\n	GOAL: Destroy every brick on the screen by having the ball hit each brick"
				+ "\n	CONTROLS: Using the left and right arrow keys move the paddle "
				+ "on the bottom of the screen to keep the ball from hitting the ground"
				+ "\n	LIVES: You start with 3 lives. Each time the main ball hits the ground (below the paddle) "
				+ "you lose a life. When you have zero lives you lose the game and must start over"
				+ "\n	SCORE: Each brick is worth 10 points. Once it is destroyed, 10 points is added to your score "
				+ "\n	LEVELS: There are 3 Levels..."
				+ "\n		+ Easy -> Least amount of bricks. Combination of red, yellow and grey bricks"
				+ "\n		+ Medium -> More bricks than Easy Level but less than the Very Hard level. Combination of "
				+ "only yellow and red bricks"
				+ "\n		+ Very Hard -> Most amount of bricks than any other evel. Only contains red bricks."
				+ "\n	POWER-UP: Each level contains a magenta brick with the 'extra ball power-up.' When "
				+ "this brick is hit, a green ball falls from the bricks' position and functions exactly like "
				+ "\n the main ball. When both balls are in play you can only lose a life by letting the main ball hit the ground. "
				+ "\n If the green ball hits the ground it dies off and vanishes, but the main ball continues to exist."
				+ "\n	COOL FEATURES:"
				+ "\n		1. Each brick can have up to 3 lives lives"
				+ "\n		2. There are 3 different levels of difficulty which are NOT hard coded"
				+ "\n		3. There is an extra ball power-up";
		
		final String brickString = "Brick Details:"
				+ "\n	Each brick has a up to 3 lives. Each life must taken before brick is considered destroyed."
				+ "\n 		+ Red Bricks -> 3 lives. Must be hit 3 times before it is destroyed. When it is hit it "
				+ "beomes a yellow brick."
				+ "\n		+ Yellow Bricks -> 2 lives. Must be hit 2 times before it is destroyed. "
				+ "When it is hit it becomes a gray brick."
				+ "\n		+ Gray Brick -> 1 life. Must be hit once to be destroyed. When it is hit it "
				+ "dissapears, is considered destroyed, and 10 points is added to your score."
				+ "\n		+ Magenta Brick -> This brick is a power up. It contains one life and functions "
				+ "like a gray brick. ";
		
		//provides message before game starts 
		JOptionPane.showMessageDialog(frame, gameplayString);
		JOptionPane.showMessageDialog(frame, brickString);
		
		//Creates Menu with instructions
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Instructions");
		JMenuItem gameplay = new JMenuItem("Gameplay");
		JMenuItem bricks = new JMenuItem("Bricks");
		gameplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JOptionPane.showMessageDialog(frame, gameplayString);
			}
		});
		
		bricks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JOptionPane.showMessageDialog(frame, brickString);
			}
		});
		
		menu.add(gameplay);
		menu.add(bricks);
		menuBar.add(menu);
		frame.setJMenuBar(menuBar);
		
		
		// Status panel, Lives panel, Score panel
		final JPanel status_panel = new JPanel();
		frame.add(status_panel, BorderLayout.SOUTH);
		final JLabel lives = new JLabel("Lives: ");
		final JLabel status = new JLabel("Running...");
		final JLabel score = new JLabel("Score: ");
		status_panel.add(status);
		status_panel.add(lives);
		status_panel.add(score);
		// Main playing area
		final GameCourt court = new GameCourt(score, status, lives);
		frame.add(court, BorderLayout.CENTER);

		// Easy, Medium and Very Hard buttons
		final JPanel control_panel = new JPanel();
		frame.add(control_panel, BorderLayout.NORTH);

		// Note here that when we add an action listener to the reset
		// button, we define it as an anonymous inner class that is
		// an instance of ActionListener with its actionPerformed()
		// method overridden. When the button is pressed,
		// actionPerformed() will be called.
		final JButton levelOne = new JButton("EASY");
		final JButton levelTwo = new JButton("MEDIUM");
		final JButton levelThree = new JButton("VERY HARD");
		levelOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				court.reset();
			}
		});
		levelTwo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				court.reset2();
			}
		});
		levelThree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				court.reset3();
			}
		});
		control_panel.add(levelOne);
		control_panel.add(levelTwo);
		control_panel.add(levelThree);

		// Put the frame on the screen
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		// Start game
		court.reset();
	}

	/*
	 * Main method run to start and run the game Initializes the GUI elements
	 * specified in Game and runs it IMPORTANT: Do NOT delete! You MUST include
	 * this in the final submission of your game.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Game());
	}
}
