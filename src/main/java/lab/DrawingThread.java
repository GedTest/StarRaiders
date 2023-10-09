package lab;


import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class DrawingThread extends AnimationTimer {

	private final Canvas canvas;
	private final GraphicsContext gc;
	private Game game;

	public DrawingThread(Canvas canvas) {
		this.canvas = canvas;
		this.gc = canvas.getGraphicsContext2D();
		this.game = new Game(new Point2D(this.canvas.getWidth(), this.canvas.getHeight()));
	}

	private static final double SCALING_FACTOR = 70.0;

	/**
	  * Draws objects into the canvas. Put you code here.
	 */
	@Override
	public void handle(long now) {
		double width = canvas.getWidth();
		double height = canvas.getHeight();

		double UNIT = width / SCALING_FACTOR;

		this.game.draw(this.gc);
	}


	private void drawPlayingField(double width, double height, double size) {
		// background
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, width, height);

		drawMiddleLine(width, height, size);
		drawOffsideLines(width, height, size);
	}

	private void drawMiddleLine(double width, double height, double size) {
		double spacing = size;
		int rectCount = (int) (height / (size + spacing));

		for (int i = 0; i <= rectCount; i++) {
			double y = i * (size + spacing);
			drawSquare(width / 2, y, size);
		}
	}

	private void drawOffsideLines(double width, double height, double size) {
		gc.setFill(Color.GRAY);
		gc.fillRect(0, 0, width, size);
		gc.fillRect(0, height-size, width, size);
	}

	private void drawScore(double width, int size) {
		double center = width/2;

		drawNumber(center - 4*size, 7*size, 4);
		drawNumber(center + 4*size, 7*size, 0);
	}

	private void drawNumber(double x, double y, int number) {
		gc.setFill(Color.WHITE);
		Font f = Font.font("Aclonica Regular", FontWeight.BOLD, 50);
		gc.setFont(f);
		gc.fillText(String.valueOf(number), x, y);

	}

	private void drawSquare(double x, double y, double size) {
		gc.setFill(Color.WHITE);
		gc.fillRect(x+(size/2), y, size, size);
	}
}
