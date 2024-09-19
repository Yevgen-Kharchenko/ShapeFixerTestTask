import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TheShapeFixer {
    // Method to check if the shape is valid
    public boolean isValid(Shape2D shape) {
        List<Point2D> points = shape.getPoints();

        // Check if the shape is closed
        if (!points.get(0).equals(points.get(points.size() - 1))) {
            return false;
        }

        // Check for duplicate edges
        Set<String> edgesSet = new HashSet<>();
        for (int i = 0; i < points.size() - 1; i++) {
            Point2D p1 = points.get(i);
            Point2D p2 = points.get(i + 1);
            String edge = p1.toString() + "->" + p2.toString();
            if (edgesSet.contains(edge)) {
                return false; // Duplicate edge
            }
            edgesSet.add(edge);
        }

        // Check for self-intersections (skip adjacent edges)
        for (int i = 0; i < points.size() - 1; i++) {
            for (int j = i + 2; j < points.size() - 1; j++) {
                // Avoid checking adjacent edges or the edge that closes the shape
                if (i == 0 && j == points.size() - 2) {
                    continue; // Skip the comparison of the first and last edge
                }

                // Check if the two line segments intersect
                if (Line2D.linesIntersect(
                        points.get(i).x, points.get(i).y, points.get(i + 1).x, points.get(i + 1).y,
                        points.get(j).x, points.get(j).y, points.get(j + 1).x, points.get(j + 1).y)) {
                    return false; // Intersection found
                }
            }
        }

        return true;
    }

    // Method to repair an invalid shape
    public Shape2D repair(Shape2D shape) {
        List<Point2D> points = shape.getPoints();
        List<Point2D> repairedPoints = new ArrayList<>();

        // Set to track unique edges and prevent duplicates
        Set<String> edgesSet = new HashSet<>();

        // Add the first point
        repairedPoints.add(points.get(0));

        for (int i = 0; i < points.size() - 1; i++) {
            Point2D p1 = points.get(i);
            Point2D p2 = points.get(i + 1);
            String edge = p1.toString() + "->" + p2.toString();

            // Only add the edge if it hasn't been seen before
            if (!edgesSet.contains(edge)) {
                repairedPoints.add(p2);
                edgesSet.add(edge);
            }
        }

        // Ensure that the shape is closed: First point should match the last point
        if (!repairedPoints.get(0).equals(repairedPoints.get(repairedPoints.size() - 1))) {
            repairedPoints.add(repairedPoints.get(0)); // Close the shape
        }

        // Check for and fix self-intersections
        return fixSelfIntersections(new Shape2D(repairedPoints));
    }

    // Method to fix self-intersections in the shape
    private Shape2D fixSelfIntersections(Shape2D shape) {
        List<Point2D> points = shape.getPoints();
        List<Point2D> fixedPoints = new ArrayList<>(points);

        boolean intersectionFound = true;

        // Continue fixing while intersections are found
        while (intersectionFound) {
            intersectionFound = false;
            for (int i = 0; i < fixedPoints.size() - 1; i++) {
                for (int j = i + 2; j < fixedPoints.size() - 1; j++) {
                    if (i == 0 && j == fixedPoints.size() - 2) {
                        continue; // Skip the first and last edge
                    }

                    // Check if the two segments intersect
                    if (Line2D.linesIntersect(
                            fixedPoints.get(i).x, fixedPoints.get(i).y, fixedPoints.get(i + 1).x, fixedPoints.get(i + 1).y,
                            fixedPoints.get(j).x, fixedPoints.get(j).y, fixedPoints.get(j + 1).x, fixedPoints.get(j + 1).y)) {
                        // Remove the segment causing the intersection
                        fixedPoints.remove(j);
                        intersectionFound = true;
                        break;
                    }
                }
                if (intersectionFound) {
                    break;
                }
            }
        }

        return new Shape2D(fixedPoints);
    }
}