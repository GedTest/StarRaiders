package lab;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Game {
    private final Point2D dimension;
    public Game(Point2D dimension) {
        this.dimension = dimension;
    }

    public void draw(GraphicsContext gc) {
        drawBackground(gc);
        drawBottomDisplay(gc);
        drawCrosshair(gc);
    }
    public void simulate(double deltaTime) {

    }
    private void drawBackground(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, this.dimension.getX(), this.dimension.getY());
    }
    private void drawBottomDisplay(GraphicsContext gc) {
        gc.setFill(Color.BLUE);
        double offsetY = this.dimension.getY() - this.dimension.getY() * 0.2f;
        gc.fillRect(0,offsetY , this.dimension.getX(), this.dimension.getY());

        drawUI(gc, offsetY);
    }
    private void drawCrosshair(GraphicsContext gc) {
        Point2D center = new Point2D(this.dimension.getX() /2, this.dimension.getY() / 2);

        gc.setStroke(Color.GREEN);

        double size = 50.f;
        gc.strokeLine(center.getX() + size/5, center.getY(), center.getX()+size, center.getY());
        gc.strokeLine(center.getX() - size/5, center.getY(), center.getX()-size, center.getY());
        gc.strokeLine(center.getX(), center.getY() + size/5, center.getX(), center.getY()+size);
        gc.strokeLine(center.getX(), center.getY() - size/5, center.getX(), center.getY()-size);
    }
    private void drawUI(GraphicsContext gc, double offsetY) {
        gc.setFill(Color.WHITE);
        Font f = Font.font("Aclonica Regular", FontWeight.BOLD, 30);
        gc.setFont(f);
        // width = 24
        // 5 + space + 5 + space + 7 + space + 4
        double space = this.dimension.getX() / 5;

        String[] stats = {
            "V: 12","K: 03","E: 9999","T: 0"
        };

        int i = 1;
        for(final String stat : stats) {
            gc.fillText(stat, space*i++, offsetY+ f.getSize());
        }

        String[] subsStats = {
                " : +00"," : -00","R: +014"
        };
        i = 1;
        for(final String stat : subsStats) {
            gc.fillText(stat, space*i++, offsetY+ 2*f.getSize());
        }
    }
}
