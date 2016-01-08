import java.awt.Color;
import java.awt.Graphics;


public class Stick extends GameObj {
	public static final int SIZE_X = 80;
	public static final int SIZE_Y = 5;
	public static final int INIT_X = 210;
	public static final int INIT_Y = 500;
	public static final int INIT_VEL_X = 0;
	public static final int INIT_VEL_Y = 0;

	/**
	 * Note that, because we don't need to do anything special when constructing
	 * a Square, we simply use the superclass constructor called with the
	 * correct parameters
	 */
	public Stick(int courtWidth, int courtHeight) {
		super(INIT_VEL_X, INIT_VEL_Y, INIT_X, INIT_Y, SIZE_X, SIZE_Y, courtWidth,
				courtHeight);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(pos_x, pos_y, width, height);
	}

}
