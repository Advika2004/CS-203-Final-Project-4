import processing.core.PImage;

import java.util.*;

public class Gnome extends Dude {

    public Gnome(String id, Point position, List<PImage> images, double actionPeriod, double animationPeriod, int resourceLimit) {
        super(id, position, images, actionPeriod, animationPeriod, resourceLimit);
    }

    public boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler) {
        if (this.getPosition().adjacent(target.getPosition())) {
            ((Plant) target).decreaseHealth();
            return true;
        } else {
            return super.moveTo(world, target, scheduler);
        }
    }

    @Override
    public boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
        return false;
    }

    private int flowersPlanted = 0;

    @Override
    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        if (flowersPlanted < 70) {
            Optional<Entity> target = world.findNearest(this.getPosition(), new ArrayList<>(Arrays.asList(Tree.class)));
            if (target.isPresent() && moveTo(world, target.get(), scheduler)){
                Point nextToTree = findEmptyAdjacentPosition(world, target.get().getPosition());
                if(nextToTree != null){
                    VirtualWorld.plantFlower(world, nextToTree, imageStore);
                    flowersPlanted++;
                }
            }

            if (flowersPlanted < 70){
                    scheduler.scheduleEvent(this, new Activity(this, world, imageStore), this.getActionPeriod());
                }
        }
    }

    private Point findEmptyAdjacentPosition(WorldModel world, Point treePosition) {
        Point upPosition = new Point(treePosition.x, treePosition.y + 1);
        if (!world.isOccupied(upPosition)) {
            return upPosition;
        }
        return null;
    }
    }




