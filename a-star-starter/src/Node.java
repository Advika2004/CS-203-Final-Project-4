public class Node {
    private Point position;
    private double distFromStart;
    private Node previous;
    private double distanceToGoal;


    public Node(Point position) {
        this.position = position;
        this.distFromStart = Double.MAX_VALUE;
        this.previous = null;
        this.distanceToGoal = Double.MAX_VALUE;
    }

    // Getters and Setters
    public Point getPosition() {
        return position;
    }

    public double getDistFromStart() {
        return distFromStart;
    }

    public void setDistFromStart(double costFromStart) {
        this.distFromStart = costFromStart;
    }

    public Node getPrevious() {
        return previous;
    }

    public double getFCost(){
        return this.getDistFromStart() + this.getDistanceToGoal();
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public double getDistanceToGoal() {
        return distanceToGoal;
    }

    public void setDistanceToGoal(double distanceToGoal) {
        this.distanceToGoal = distanceToGoal;
    }

    // Helper method to calculate the heuristic cost to the goal (Manhattan distance, for example)
    public double calculateManhattanDistance(Point goal) {
        return Math.abs(goal.x - this.position.x) + Math.abs(goal.y - this.position.y);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Node node = (Node) obj;
        return position.equals(node.position);
    }

    @Override
    public int hashCode() {
        return position.hashCode();
    }
}