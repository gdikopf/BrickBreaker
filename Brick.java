import java.awt.Color;
import java.awt.Graphics;


public class Brick extends GameObj {
	public static final int SIZE_X = 35;
	public static final int SIZE_Y = 20;
	public static final int INIT_VEL_X = 0;
	public static final int INIT_VEL_Y = 0;
	public Color color;

	/**
	 * Note that, because we don't need to do anything special when constructing
	 * a Square, we simply use the superclass constructor called with the
	 * correct parameters
	 */
	public Brick(int courtWidth, int courtHeight, int initX, int initY, Color col) {
		super(INIT_VEL_X, INIT_VEL_Y, initX, initY, SIZE_X, SIZE_Y, courtWidth,
				courtHeight);
		color = col;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(pos_x, pos_y, width, height);
		g.setColor(Color.BLACK);
		g.drawRect(pos_x, pos_y, width, height);
	}

}
