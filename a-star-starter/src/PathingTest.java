
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class PathingTest {

    @Test
    public void testSingleStepSearch() {
        boolean[][] grid = {
                {true, true, true},
                {true, true, true},
                {true, true, true}
        };

        PathingStrategy strat = new SingleStepPathingStrategy();
        List<Point> path = strat.computePath(
                new Point(0, 0),
                new Point(2, 2),
                p -> isWithinBounds(p, grid) && grid[p.y][p.x],
                (p1, p2) -> p1.adjacent(p2),
                PathingStrategy.CARDINAL_NEIGHBORS
        );

        List<Point> expected = List.of(new Point(0, 1));
        assertEquals(expected, path);
    }

    @Test
    public void testSingleStepObstacle() {
        boolean[][] grid = {
                {true, true, true},
                {false, true, true},
                {true, true, true}
        };

        PathingStrategy strat = new SingleStepPathingStrategy();
        List<Point> path = strat.computePath(
                new Point(0, 0), // start
                new Point(2, 2), // end
                p -> isWithinBounds(p, grid) && grid[p.y][p.x],
                (p1, p2) -> p1.adjacent(p2),
                PathingStrategy.CARDINAL_NEIGHBORS
        );

        List<Point> expected = List.of(new Point(1, 0));
        assertEquals(expected, path);
    }


    private boolean isWithinBounds(Point p, boolean[][] grid) {
        return p.x >= 0 && p.x < grid[0].length &&
                p.y >= 0 && p.y < grid.length;
    }

    @Test
    public void testAStarObstacle1() {
        boolean[][] grid = {
                {true, true, true},
                {true, false, false},
                {true, true, true}
        };

        PathingStrategy strat = new AStarPathingStrategy();
        List<Point> path = strat.computePath(
                new Point(0, 0),
                new Point(2, 2),
                p -> isWithinBounds(p, grid) && grid[p.y][p.x],
                (p1, p2) -> p1.adjacent(p2),
                PathingStrategy.CARDINAL_NEIGHBORS
        );

        List<Point> expected = List.of(
                new Point(0, 1), new Point(0, 2), new Point(1, 2)
        );
        assertEquals(expected, path);
    }

    @Test
    public void testAStarObstacle2() {
        boolean[][] grid = {
                {true, true, true, true},
                {true, false, false, true},
                {true, true, true, true}
        };

        PathingStrategy strat = new AStarPathingStrategy();
        List<Point> path = strat.computePath(
                new Point(0, 0), // start
                new Point(3, 2), // end
                p -> isWithinBounds(p, grid) && grid[p.y][p.x],
                (p1, p2) -> p1.adjacent(p2),
                PathingStrategy.CARDINAL_NEIGHBORS
        );
        assertEquals(4, path.size());
    }

    @Test
    public void testAStarObstacle3() {
        boolean[][] grid = {
                {true, true, true},
                {false, true, true},
                {false, true, true}
        };

        PathingStrategy strat = new AStarPathingStrategy();
        List<Point> path = strat.computePath(
                new Point(0, 0), // start
                new Point(2, 2), // end
                p -> isWithinBounds(p, grid) && grid[p.y][p.x],
                (p1, p2) -> p1.adjacent(p2),
                PathingStrategy.CARDINAL_NEIGHBORS
        );
        assertEquals(3, path.size());

    }

    @Test
    public void testAStarObstacle4() {
        boolean[][] grid = {
                {true, true, true, true},
                {true, false, true, false},
                {true, false, true, true},
                {true, true, true, true}
        };

        PathingStrategy strat = new AStarPathingStrategy();
        List<Point> path = strat.computePath(
                new Point(0, 0), // start
                new Point(3, 3), // end
                p -> isWithinBounds(p, grid) && grid[p.y][p.x],
                (p1, p2) -> p1.adjacent(p2),
                PathingStrategy.CARDINAL_NEIGHBORS
        );
        assertEquals(5, path.size());
    }

    @Test
    public void testAStarObstacle5() {
        boolean[][] grid = {
                {true, true, true, true},
                {true, false, true, true},
                {true, true, false, true},
                {true, true, true, true}
        };

        PathingStrategy strat = new AStarPathingStrategy();
        List<Point> path = strat.computePath(
                new Point(0, 0), // start
                new Point(3, 3), // end
                p -> isWithinBounds(p, grid) && grid[p.y][p.x],
                (p1, p2) -> p1.adjacent(p2),
                PathingStrategy.CARDINAL_NEIGHBORS
        );
        int expectedPathLength = 5;
        assertEquals(expectedPathLength, path.size());
    }
}



