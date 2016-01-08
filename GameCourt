import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


/**
 * GameCourt
 * 
 * This class holds the primary game logic for how different objects interact
 * with one another. Take time to understand how the timer interacts with the
 * different methods and how it repaints the GUI on every tick().
 * 
 */
@SuppressWarnings("serial")
public class GameCourt extends JPanel {

	// the state of the game logic
	private Stick stick; // the stick, moves with arrows to bounce ball
	private Ball ball; // the ball, bounces and breaks bricks
	private Ball bonus; //when bonus brick is hit, extra ball is added to gameplay
	private Brick[][] bWall = new Brick[10][5]; // the wall of bricks, don't move
	private int liv; // int representing the amount of lives you have
	private int points; // int representing the amount of points you have (10 for each brick destroyed)
	private int count; // int representing the count of existing bricks in wall, when 0 you win the game
	private int extra_x; // x coordinate of the bonus brick
	private int extra_y; // y coordinate of the bonus brick
	

	public boolean playing = false; // whether the game is running
	private JLabel status; // Current status text representing level and win/lose
	private JLabel score; // text showing your current score
	private JLabel lives; // text showing your current amount of lives left

	// Game constants
	public static final int COURT_WIDTH = 500;
	public static final int COURT_HEIGHT = 500;
	public static final int STICK_VELOCITY = 10;
	// Update interval for timer, in milliseconds
	public static final int INTERVAL = 35;
	
	/*
	 * function creating a wall for the easy level:
	 * contains one magenta brick which is the bonus brick; randomly placed
	 * in a 10 by 5 double array, each brick has a 25% chance of being created
	 * each brick has an equal chance of being Red, Yellow, or Light Gray
	 */
	int space = 30;
	public void makeWall() {
		extra_x = Math.round((int) (Math.random() * 10));
		extra_y = Math.round((int) (Math.random() * 5));
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 5; j++) {
				if (i == extra_x && j == extra_y) {
					bWall[i][j] = new Brick(COURT_WIDTH, COURT_HEIGHT, ((i * 35) + 75), ((j * 20) + 10 + (space * j)), Color.MAGENTA);
					count++;
				}
				else {
					int rand = Math.round((int) (4 * Math.random()));
					int color = Math.round((int) (3 * Math.random()));
					if (rand == 1) {
						if (color == 0) {
							bWall[i][j] = new Brick(COURT_WIDTH, COURT_HEIGHT, ((i * 35) + 75), ((j * 20) + 10 + (space * j)), Color.lightGray);
							count++;
						}
						else if (color == 1) {
							bWall[i][j] = new Brick(COURT_WIDTH, COURT_HEIGHT, ((i * 35) + 75), ((j * 20) + 10 + (space * j)), Color.RED);
							count++;
						}
						else {
							bWall[i][j] = new Brick(COURT_WIDTH, COURT_HEIGHT, ((i * 35) + 75), ((j * 20) + 10 + (space * j)), Color.YELLOW);
							count++;
						}
				}

				}
			}
		}
	}
	
	/*
	 * function creating a wall for the medium level:
	 * contains one magenta brick which is the bonus brick; randomly placed
	 * in a 10 by 5 double array, each brick has a 33.33% chance of being created
	 * each brick has a 1/3 chance of being red and a 2/3 chance of being yellow
	 */
	public void makeWall2() {
		extra_x = Math.round((int) (Math.random() * 10));
		extra_y = Math.round((int) (Math.random() * 5));
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 5; j++) {
				if (i == extra_x && j == extra_y) {
					bWall[i][j] = new Brick(COURT_WIDTH, COURT_HEIGHT, ((i * 35) + 75), ((j * 20) + 10 + (space * j)), Color.MAGENTA);
					count++;
				}
				else {
					int rand = Math.round((int) (3 * Math.random()));
					int color = Math.round((int) (3 * Math.random()));
					if (rand == 1) {
						if (color == 0) {
							bWall[i][j] = new Brick(COURT_WIDTH, COURT_HEIGHT, ((i * 35) + 75), ((j * 20) + 10 + (space * j)), Color.RED);
							count++;
						}
						else {
							bWall[i][j] = new Brick(COURT_WIDTH, COURT_HEIGHT, ((i * 35) + 75), ((j * 20) + 10 + (space * j)), Color.YELLOW);
							count++;
						}


					}
				}
			}
		}
	}
	
	/*
	 * function creating a wall for the medium level:
	 * contains one magenta brick which is the bonus brick; randomly placed
	 * in a 10 by 5 double array, each brick has a 50% chance of being created
	 * each brick brick aside from the bonus brick is red
	 */
	public void makeWall3() {
		extra_x = Math.round((int) (Math.random() * 10));
		extra_y = Math.round((int) (Math.random() * 5));
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 5; j++) {
				if (i == extra_x && j == extra_y) {
					bWall[i][j] = new Brick(COURT_WIDTH, COURT_HEIGHT, ((i * 35) + 75), ((j * 20) + 10 + (space * j)), Color.MAGENTA);
					count++;
				}
				else {
					int rand = Math.round((int) (2 * Math.random()));
					if (rand == 1) {
						bWall[i][j] = new Brick(COURT_WIDTH, COURT_HEIGHT, ((i * 35) + 75), ((j * 20) + 10 + (space * j)), Color.RED);
						count++;
					}
				}
			}
		}
	}
	
	/*
	 * function which makes every brick in the bWall null
	 * clears the array allowing you to make a new different wall of bricks
	 */
	public void emptyWall() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 5; j++) {
				bWall[i][j] = null;
			}
		}
		count = 0;
	}

	public GameCourt(JLabel score, JLabel status, JLabel lives) {
		// creates border around the court area, JComponent method
		setBorder(BorderFactory.createLineBorder(Color.BLACK));

		// The timer is an object which triggers an action periodically
		// with the given INTERVAL. One registers an ActionListener with
		// this timer, whose actionPerformed() method will be called
		// each time the timer triggers. We define a helper method
		// called tick() that actually does everything that should
		// be done in a single timestep.
		Timer timer = new Timer(INTERVAL, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tick();
			}
		});
		timer.start(); // MAKE SURE TO START THE TIMER!

		// Enable keyboard focus on the court area.
		// When this component has the keyboard focus, key
		// events will be handled by its key listener.
		setFocusable(true);

		// This key listener allows the stick (paddle) to move as long
		// as an arrow key is pressed, by changing the stick's
		// velocity accordingly. (The tick method below actually
		// moves the stick.) Also only moves left and right.
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT)
					stick.v_x = -STICK_VELOCITY;
				else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
					stick.v_x = STICK_VELOCITY;
			}

			public void keyReleased(KeyEvent e) {
				stick.v_x = 0;
				stick.v_y = 0;
			}
		});

		this.score = score;
		this.status = status;
		this.lives = lives;
	}

	/**
	 * (Re-)set the game to its initial state of level 1: Easy.
	 */
	public void reset() {
		stick = new Stick(COURT_WIDTH, COURT_HEIGHT);
		emptyWall();
		makeWall();
		liv = 3;
		points = 0;
		ball = new Ball(COURT_WIDTH, COURT_HEIGHT, Color.BLACK, 250, 480);
		bonus = null;

		playing = true;
		score.setText("Score: " + points + "    ");
		status.setText("Level: EASY    ");
		lives.setText("Lives: " + liv + "    ");

		// Make sure that this component has the keyboard focus
		requestFocusInWindow();
	}
	
	/**
	 * (Re-)set the game to its initial state of level 2: Medium.
	 */
	public void reset2() {
		stick = new Stick(COURT_WIDTH, COURT_HEIGHT);
		emptyWall();
		makeWall2();
		liv = 3;
		points = 0;
		ball = new Ball(COURT_WIDTH, COURT_HEIGHT, Color.BLACK, 250, 480);
		bonus = null;

		playing = true;
		score.setText("Score: " + points + "    ");
		status.setText("Level: MEDIUM    ");
		lives.setText("Lives: " + liv + "    ");

		// Make sure that this component has the keyboard focus
		requestFocusInWindow();
	}
	
	/**
	 * (Re-)set the game to its initial state of level 3: Very Hard.
	 */
	public void reset3() {
		stick = new Stick(COURT_WIDTH, COURT_HEIGHT);
		emptyWall();
		makeWall3();
		liv = 3;
		points = 0;
		ball = new Ball(COURT_WIDTH, COURT_HEIGHT, Color.BLACK, 250, 480);
		bonus = null;

		playing = true;
		score.setText("Score: " + points + "    ");
		status.setText("Level: VERY HARD    ");
		lives.setText("Lives: " + liv + "    ");

		// Make sure that this component has the keyboard focus
		requestFocusInWindow();
	}

	/**
	 * This method is called every time the timer defined in the constructor
	 * triggers.
	 */
	void tick() {
		if (playing) {
			// advance ball and stick in their
			// current direction.
			stick.move();
			ball.move();
			// get ball to bounce of stick and walls
			ball.bounce(ball.hitWall());
			ball.bounce(ball.hitObj(stick));
			
			// advance bonus ball in current direction
			// and get it to bounce of the stick and walls
			if (bonus != null) {
				bonus.move();
				bonus.bounce(bonus.hitWall());
				bonus.bounce(bonus.hitObj(stick));
			}

			//traverse through the bWall 
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 5; j++) {
					//check to see if bonus brick is hit
					//if hit create the bonus ball
					if (i == extra_x && j == extra_y && bWall[i][j] != null) {
						if (bWall[i][j].intersects(ball)) {
							bonus = new Ball(COURT_WIDTH, COURT_HEIGHT, Color.GREEN, bWall[i][j].pos_x, bWall[i][j].pos_y);
						}
					}
					//if ball hits a brick then update brick appropiately  
					// turn red -> yellow, yellow -> light gray
					//light gray -> destroy brick, add points, update score and count
					if (bWall[i][j] != null) {
						if (bWall[i][j].intersects(ball)) {
							ball.bounce(ball.hitObj(bWall[i][j]));
							if (bWall[i][j].color == Color.RED) {
								 bWall[i][j].color = Color.YELLOW;
							}
							else if (bWall[i][j].color == Color.YELLOW) {
								bWall[i][j].color = Color.lightGray;
							}
							else {
								bWall[i][j] = null;
								points += 10;
								count--;
								score.setText("Score: " + points + "    ");
							}
						}
					}
					// if bonus ball hits a brick then update brick appropiately
					// same as ball hitting a brick
					if (bonus != null) {
						if (bWall[i][j] != null) {
							if (bWall[i][j].intersects(bonus)) {
								bonus.bounce(bonus.hitObj(bWall[i][j]));
								if (bWall[i][j].color == Color.RED) {
									 bWall[i][j].color = Color.YELLOW;
								}
								else if (bWall[i][j].color == Color.YELLOW) {
									bWall[i][j].color = Color.lightGray;
								}
								else {
									bWall[i][j] = null;
									points += 10;
									count--;
									score.setText("Score: " + points + "    ");
								}
							}
						}
					}
				}
			}
			
			// if stick (paddle) misses ball then lose life 
			// or if last life, lose game
			if (!(ball.intersects(stick))) {
				if (ball.pos_y >= 489) {
					if (liv != 1) {
						liv--;
						lives.setText("Lives: " + liv + "    ");
						ball = new Ball(COURT_WIDTH, COURT_HEIGHT, Color.BLACK, 250, 480);
						bonus = null;
						stick = new Stick(COURT_WIDTH, COURT_HEIGHT);
					}
					else {
						playing = false;
						status.setText("YOU LOSE!   ");	
						lives.setText("Lives: 0    ");
						score.setText("Score: " + points + "    ");
					}
				}
			}
			
			// if stick (paddle) misses bonus ball then ball disappears 
			if (bonus != null) {
				if (!(bonus.intersects(stick))) {
					if (bonus.pos_y >= 489) {
						bonus = null;
					}
				}
			}

			// if all bricks on screen are destroyed, you win the game
			if(count == 0) {
				status.setText("YOU WIN!!!!!!");
				lives.setText("");
				score.setText("");
				playing = false;
			}
			repaint();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		stick.draw(g);
		ball.draw(g);
		if (bonus != null) {
			bonus.draw(g);
		}
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 5; j++) {
				if (bWall[i][j] != null) {
					bWall[i][j].draw(g);
				}
			}
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(COURT_WIDTH, COURT_HEIGHT);
	}
}
