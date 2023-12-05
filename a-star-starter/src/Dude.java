import processing.core.PImage;
import java.util.List;

public abstract class Dude extends Movable{
    public static final String DUDE_KEY = "dude";
    public static final int DUDE_ACTION_PERIOD = 0;
    public static final int DUDE_ANIMATION_PERIOD = 1;
    public static final int DUDE_LIMIT = 2;
    public static final int DUDE_NUM_PROPERTIES = 3;
    private final int resourceLimit;

    public Dude(String id, Point position, List<PImage> images, double actionPeriod,
                double animationPeriod, int resourceLimit) {
        super(id, position, images, actionPeriod, animationPeriod);
        this.resourceLimit = resourceLimit;
    }

    public int getResourceLimit() {
        return this.resourceLimit;
    }

    /**
     * Dudes can move to any unoccupied point or points occupied by stumps.
     */
    @Override
    public boolean isInvalidMove(WorldModel world, Point destination) {
        return world.isOccupied(destination) &&
                world.getOccupancyCell(destination).getClass() != Stump.class;
    }


    /**
     * The default movement of the Dude assumes we haven't yet reached the target.
     * Each subclass will specify behaviours for when we reach the target.
     */
    public boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler) {
        Point nextPos = this.nextPosition(world, target.getPosition());

        if (!this.getPosition().equals(nextPos)) {
            world.moveEntity(scheduler, this, nextPos);
        }
        return false;
    }

    public Point nextPosition(WorldModel world, Point destPos){
        Point startPoint = getPosition();
        Point endPoint = destPos;
        List<Point> newPositions = new AStarPathingStrategy()
            .computePath(startPoint, endPoint,
                    p -> (world.withinBounds(p)) && (!world.isOccupied(p) || world.isStump(p) || world.isButterfly(p)),//canPassThrough
                    (p1,p2) -> p1.adjacent(p2), //withinReach
                    PathingStrategy.CARDINAL_NEIGHBORS  //potentialNeighbors
            );

        if (newPositions.isEmpty()) {
            return startPoint;
        }
        return newPositions.get(0);
    }


    public abstract boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore);

    public boolean isNearGarden(WorldModel world, Point point) {
            if (world.withinBounds(point)) {
                Background background = world.getBackgroundCell(point);
                if (background.getId().equals("garden")) {
                    return true;
                }
            }
        return false;
    }

    public boolean isGrass(WorldModel world, Point point) {
        if (world.withinBounds(point)) {
            Background background = world.getBackgroundCell(point);
            if (background.getId().equals("grass")) {
                return true;
            }
        }
        return false;
    }
}
