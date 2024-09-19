import java.util.List;

public class Shape2D {
    private List<Point2D> points;

    Shape2D(List<Point2D> points) {
        this.points = points;
    }

    public List<Point2D> getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return "Shape2D{" +
                "points=" + points +
                '}';
    }
}
