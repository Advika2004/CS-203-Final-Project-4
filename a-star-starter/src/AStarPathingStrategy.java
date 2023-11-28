import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.*;


class AStarPathingStrategy implements PathingStrategy {


    public List<Point> computePath(Point start, Point end,
                                   Predicate<Point> canPassThrough,
                                   BiPredicate<Point, Point> withinReach,
                                   Function<Point, Stream<Point>> potentialNeighbors) {


        PriorityQueue<Node> openList = new PriorityQueue<>((node1, node2) -> Double.compare(node1.getFCost(), node2.getFCost())); //open list with a comparator
        Set<Point> closedList = new HashSet<>(); // closed list to keep track of visited nodes?
        Map<Point, Node> allNodes = new HashMap<>(); //keep track of points and the corresponding nodes


        //Creating the node
        Node startNode = new Node(start); //create a start node
        Node current = null;

        startNode.setDistFromStart(0); //initialize the distance from the start node to zero
        startNode.setDistanceToGoal(startNode.calculateManhattanDistance(end)); //calculate the distance to the goal
        openList.add(startNode); //add the node to the open list
        allNodes.put(start, startNode);

        while (!openList.isEmpty()) {
            current = openList.poll(); // Get the node with the lowest F value
            if (withinReach.test(current.getPosition(), end)) {
                break;
            }
            if (current.getPosition().equals(end)) {
            return buildPath(current);
            }
        // Check if we reached the goal
        // Process current node's neighbors
        // for every neighboring position, use a stream to loop through the list of points and returns all the potential neighboring points that are then collected into a list.
        for (Point neighborPos : potentialNeighbors.apply(current.getPosition()).collect(Collectors.toList())) {
            if (canPassThrough.test(neighborPos) && !closedList.contains(neighborPos)) { //if you can't pass through that point or if that point has already been visited
                // if you can pass through that node, then calculate how good that position is.
                Node neighborNode;
                if (allNodes.containsKey(neighborPos)) {
                    // If the allNodes map already contains the neighborPos,
                    // get the corresponding Node from the map.
                    neighborNode = allNodes.get(neighborPos);
                } else {
                    // If the map does not contain neighborPos,
                    // create a new Node for this position and put it in the map.
                    neighborNode = new Node(neighborPos);
                    allNodes.put(neighborPos, neighborNode);
                }
                // double newPathCost = current.getDistFromStart() + current.calculateManhattanDistance(neighborPos);
                double newPathCost = current.getDistFromStart() + 1;
                // the new path cost is the sum of the distance from the start node + the distance from the current node to its neighbor

                if (!openList.contains(neighborNode) || newPathCost < neighborNode.getDistFromStart()) { //if the node is not in the priority Q or if the newPathCost is less than the one right now
                    neighborNode.setPrevious(current); // sets the current node to the previous node (adds it to potential path)
                    neighborNode.setDistFromStart(newPathCost); // updates all the distance calculations from the start and to the goal from this new position
                    neighborNode.setDistanceToGoal(newPathCost + neighborNode.calculateManhattanDistance(end));

                    if (!openList.contains(neighborNode)) {
                        openList.add(neighborNode);
                        allNodes.put(neighborPos, neighborNode);
                        //have to add it in the priority queue if it has not been added already
                    } else {
                        openList.remove(neighborNode);
                        openList.add(neighborNode);
                    }
                }
            }
            closedList.add(current.getPosition());  //marks it as processed
        }
    }
        if(current == null){
            return Collections.emptyList(); // Return empty path if goal is not reachable
        }
        else {
            return buildPath(current);
        }
    }


    private List<Point> buildPath(Node target) {
        LinkedList<Point> path = new LinkedList<>(); //create new linked list for the path
        Node current = target; //start at the end
        while (current != null && current.getPrevious() != null) { //while there is still a path but its not the starting position
            path.addFirst(current.getPosition());
            //add the current position to path
            current = current.getPrevious(); //go backwards
        }
        return path; //return that linked list
    }
}