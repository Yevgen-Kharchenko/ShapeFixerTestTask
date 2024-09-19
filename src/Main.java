import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        TheShapeFixer shapeFixer = new TheShapeFixer();

        // Test case 1: A valid square shape
        List<Point2D> squarePoints = new ArrayList<>();
        squarePoints.add(new Point2D(0, 0));
        squarePoints.add(new Point2D(1, 0));
        squarePoints.add(new Point2D(1, 1));
        squarePoints.add(new Point2D(0, 1));
        squarePoints.add(new Point2D(0, 0)); // Closed shape
        Shape2D square = new Shape2D(squarePoints);
        System.out.println("Test 1 - Valid Square: " + shapeFixer.isValid(square)); // Should print: true

        // Test case 2: An invalid shape with a self-intersection
        List<Point2D> invalidPoints = new ArrayList<>();
        invalidPoints.add(new Point2D(0, 0));
        invalidPoints.add(new Point2D(2, 0));
        invalidPoints.add(new Point2D(1, 1)); // Self-intersecting point
        invalidPoints.add(new Point2D(0, 2));
        invalidPoints.add(new Point2D(2, 2));
        invalidPoints.add(new Point2D(0, 0)); // Closed shape
        Shape2D invalidShape = new Shape2D(invalidPoints);
        System.out.println("Test 2 - Invalid Shape: " + shapeFixer.isValid(invalidShape)); // Should print: false

        // Test case 3: Open shape that needs to be repaired
        List<Point2D> openShapePoints = new ArrayList<>();
        openShapePoints.add(new Point2D(0, 0));
        openShapePoints.add(new Point2D(2, 0));
        openShapePoints.add(new Point2D(2, 2));
        openShapePoints.add(new Point2D(1, 1)); // Not properly connected to (0, 0)
        Shape2D openShape = new Shape2D(openShapePoints);
        Shape2D repairedOpenShape = shapeFixer.repair(openShape);
        System.out.println("Test 3 - Repaired Open Shape Valid: " + shapeFixer.isValid(repairedOpenShape)); // Should print: true
        System.out.println("openShape = " + openShape);
        System.out.println("repairedOpenShape = " + repairedOpenShape);

        // Test case 4: Shape with duplicate edges
        List<Point2D> duplicateEdgePoints = new ArrayList<>();
        duplicateEdgePoints.add(new Point2D(0, 0));
        duplicateEdgePoints.add(new Point2D(1, 0));
        duplicateEdgePoints.add(new Point2D(1, 1));
        duplicateEdgePoints.add(new Point2D(0, 0)); // Duplicate edge
        duplicateEdgePoints.add(new Point2D(1, 0)); // Duplicate again
        Shape2D duplicateEdgeShape = new Shape2D(duplicateEdgePoints);
        System.out.println("Test 4 - Shape with Duplicate Edge: " + shapeFixer.isValid(duplicateEdgeShape)); // Should print: false

        // Test case 5: Repairing the shape with duplicate edges
        Shape2D repairedDuplicateEdgeShape = shapeFixer.repair(duplicateEdgeShape);
        System.out.println("Test 5 - Repaired Duplicate Edge Shape Validity: " + shapeFixer.isValid(repairedDuplicateEdgeShape)); // Should print: true
        System.out.println("duplicateEdgeShape = " + duplicateEdgeShape);
        System.out.println("repairedDuplicateEdgeShape = " + repairedDuplicateEdgeShape);

        // Test case 6: Repairing a self-intersecting shape
        Shape2D repairedSelfIntersectingShape = shapeFixer.repair(invalidShape);
        System.out.println("Test 6 - Repaired Self-Intersecting Shape Validity: " + shapeFixer.isValid(repairedSelfIntersectingShape)); // Should print: true
        System.out.println("invalidShape = " + invalidShape);
        System.out.println("repairedSelfIntersectingShape = " + repairedSelfIntersectingShape);
    }


}