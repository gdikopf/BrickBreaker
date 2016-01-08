import java.awt.Color;
import java.awt.Graphics;


public class Ball extends GameObj {

	public static final int SIZE = 10;
	public static final int INIT_V_X = 4;
	public static final int INIT_V_Y = 5;
	public Color color;

	public Ball(int courtWidth, int courtHeight, Color col, int init_x, int init_y) {
		super(INIT_V_X, INIT_V_Y, init_x, init_y, SIZE, SIZE,
				courtWidth, courtHeight);
		color = col;
	}
	

	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval(pos_x, pos_y, width, height);
	}
}
