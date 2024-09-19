public class Point2D {
    int x, y;

    Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Check if two points are equal
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point2D point = (Point2D) o;
        return Double.compare(point.x, x) == 0 &&
                Double.compare(point.y, y) == 0;
    }

    @Override
    public String toString() {
        return "{" + x + "," + y + "}";
    }
}
