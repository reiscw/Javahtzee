import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

public class Dice {
	
	public static final int SIZE = 68;
	public static final int DOT_WIDTH = 13;
	public static final Color ON_COLOR = Color.LIGHT_GRAY;
	public static final Color OFF_COLOR = Color.BLACK;
	
	public static final int[] CENTER = {SIZE/2 - (DOT_WIDTH-1)/2-1, SIZE/2 - (DOT_WIDTH-1)/2-1, DOT_WIDTH, DOT_WIDTH};
	public static final int[] UPPER_LEFT = {SIZE/2 - SIZE/4 - (DOT_WIDTH-1)/2-1, SIZE/2 - SIZE/4 - (DOT_WIDTH-1)/2-1, DOT_WIDTH, DOT_WIDTH};
	public static final int[] LOWER_RIGHT = {SIZE/2 + SIZE/4 - (DOT_WIDTH-1)/2-1, SIZE/2 + SIZE/4 - (DOT_WIDTH-1)/2-1, DOT_WIDTH, DOT_WIDTH};
	public static final int[] UPPER_RIGHT = {SIZE/2 + SIZE/4 - (DOT_WIDTH-1)/2-1, SIZE/2 - SIZE/4 - (DOT_WIDTH-1)/2-1, DOT_WIDTH, DOT_WIDTH};
	public static final int[] LOWER_LEFT = {SIZE/2 - SIZE/4 - (DOT_WIDTH-1)/2-1, SIZE/2 + SIZE/4 - (DOT_WIDTH-1)/2-1, DOT_WIDTH, DOT_WIDTH};
	public static final int[] CENTER_LEFT = {SIZE/2 - SIZE/4 - (DOT_WIDTH-1)/2-1, SIZE/2 - (DOT_WIDTH-1)/2-1, DOT_WIDTH, DOT_WIDTH};
	public static final int[] CENTER_RIGHT = {SIZE/2 + SIZE/4 - (DOT_WIDTH-1)/2-1, SIZE/2 - (DOT_WIDTH-1)/2-1, DOT_WIDTH, DOT_WIDTH};

	public static BufferedImage init(boolean negate) {
		BufferedImage result = new BufferedImage(SIZE, SIZE, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphic = (Graphics2D)result.getGraphics();
		if (negate) {
			graphic.setColor(OFF_COLOR);
		} else {
			graphic.setColor(ON_COLOR);
		}
		int[] x = {0, SIZE, SIZE, 0};
		int[] y = {0, 0, SIZE, SIZE};
		graphic.fillPolygon(x, y, 4);
		return result;
	}

	public static BufferedImage one(boolean negate) {
		BufferedImage result = init(negate);
		Graphics2D graphic = (Graphics2D)result.getGraphics();
		if (negate) {
			graphic.setColor(ON_COLOR);
		} else {
			graphic.setColor(OFF_COLOR);
		}
		graphic.fillOval(CENTER[0], CENTER[1], CENTER[2], CENTER[3]);
		return result;
	}
	
	public static BufferedImage two(boolean negate) {
		BufferedImage result = init(negate);
		Graphics2D graphic = (Graphics2D)result.getGraphics();
		if (negate) {
			graphic.setColor(ON_COLOR);
		} else {
			graphic.setColor(OFF_COLOR);
		}
		graphic.fillOval(UPPER_LEFT[0], UPPER_LEFT[1], UPPER_LEFT[2], UPPER_LEFT[3]);
		graphic.fillOval(LOWER_RIGHT[0], LOWER_RIGHT[1], LOWER_RIGHT[2], LOWER_RIGHT[3]);
		return result;
	}

	public static BufferedImage three(boolean negate) {
		BufferedImage result = init(negate);
		Graphics2D graphic = (Graphics2D)result.getGraphics();
		if (negate) {
			graphic.setColor(ON_COLOR);
		} else {
			graphic.setColor(OFF_COLOR);
		}
		graphic.fillOval(LOWER_LEFT[0], LOWER_LEFT[1], LOWER_LEFT[2], LOWER_LEFT[3]);
		graphic.fillOval(UPPER_RIGHT[0], UPPER_RIGHT[1], UPPER_RIGHT[2], UPPER_RIGHT[3]);
		graphic.fillOval(CENTER[0], CENTER[1], CENTER[2], CENTER[3]);
		return result;
	}
	
	public static BufferedImage four(boolean negate) {
		BufferedImage result = init(negate);
		Graphics2D graphic = (Graphics2D)result.getGraphics();
		if (negate) {
			graphic.setColor(ON_COLOR);
		} else {
			graphic.setColor(OFF_COLOR);
		}
		graphic.fillOval(UPPER_LEFT[0], UPPER_LEFT[1], UPPER_LEFT[2], UPPER_LEFT[3]);
		graphic.fillOval(LOWER_RIGHT[0], LOWER_RIGHT[1], LOWER_RIGHT[2], LOWER_RIGHT[3]);
		graphic.fillOval(LOWER_LEFT[0], LOWER_LEFT[1], LOWER_LEFT[2], LOWER_LEFT[3]);
		graphic.fillOval(UPPER_RIGHT[0], UPPER_RIGHT[1], UPPER_RIGHT[2], UPPER_RIGHT[3]);
		return result;
	}	

	public static BufferedImage five(boolean negate) {
		BufferedImage result = init(negate);
		Graphics2D graphic = (Graphics2D)result.getGraphics();
		if (negate) {
			graphic.setColor(ON_COLOR);
		} else {
			graphic.setColor(OFF_COLOR);
		}
		graphic.fillOval(UPPER_LEFT[0], UPPER_LEFT[1], UPPER_LEFT[2], UPPER_LEFT[3]);
		graphic.fillOval(LOWER_RIGHT[0], LOWER_RIGHT[1], LOWER_RIGHT[2], LOWER_RIGHT[3]);
		graphic.fillOval(CENTER[0], CENTER[1], CENTER[2], CENTER[3]);
		graphic.fillOval(LOWER_LEFT[0], LOWER_LEFT[1], LOWER_LEFT[2], LOWER_LEFT[3]);
		graphic.fillOval(UPPER_RIGHT[0], UPPER_RIGHT[1], UPPER_RIGHT[2], UPPER_RIGHT[3]);
		return result;
	}	

	public static BufferedImage six(boolean negate) {
		BufferedImage result = init(negate);
		Graphics2D graphic = (Graphics2D)result.getGraphics();
		if (negate) {
			graphic.setColor(ON_COLOR);
		} else {
			graphic.setColor(OFF_COLOR);
		}
		graphic.fillOval(UPPER_LEFT[0], UPPER_LEFT[1], UPPER_LEFT[2], UPPER_LEFT[3]);
		graphic.fillOval(LOWER_RIGHT[0], LOWER_RIGHT[1], LOWER_RIGHT[2], LOWER_RIGHT[3]);
		graphic.fillOval(CENTER_LEFT[0], CENTER_LEFT[1], CENTER_LEFT[2], CENTER_LEFT[3]);
		graphic.fillOval(CENTER_RIGHT[0], CENTER_RIGHT[1], CENTER_RIGHT[2], CENTER_RIGHT[3]);
		graphic.fillOval(LOWER_LEFT[0], LOWER_LEFT[1], LOWER_LEFT[2], LOWER_LEFT[3]);
		graphic.fillOval(UPPER_RIGHT[0], UPPER_RIGHT[1], UPPER_RIGHT[2], UPPER_RIGHT[3]);
		return result;
	}	
}
