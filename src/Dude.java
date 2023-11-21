import processing.core.PImage;

import java.util.List;

public abstract class Dude extends Move implements NextPosition{

//    public Dude(String id, Point position, double actionPeriod, double animationPeriod, int resourceLimit, List<PImage> images){
//        super(EntityKind.DUDE_FULL, id, position, images, resourceLimit, actionPeriod, animationPeriod);
//    }

    public Dude(){
    }

    @Override
    public Point nextPosition(WorldModel world, Point destPos) {
        Point currentPosition = getPosition();
        int horiz = Integer.signum(destPos.x - currentPosition.x);
        Point newPos = new Point(currentPosition.x + horiz, currentPosition.y);

        if (horiz == 0 || world.isOccupied(newPos) && world.getOccupancyCell(newPos).kind != EntityKind.STUMP) {
            int vert = Integer.signum(destPos.y - currentPosition.y);
            newPos = new Point(currentPosition.x, currentPosition.y + vert);

            if (vert == 0 || world.isOccupied(newPos) && world.getOccupancyCell(newPos).kind != EntityKind.STUMP) {
                newPos = currentPosition;
            }
        }

        return newPos;
    }
}
