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


//    public boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
//        return false;
//    }

    public boolean isNearGarden(WorldModel world) {
        for (Point point : VirtualWorld.Find8SurroundingTiles(this.getPosition())) {
            if (world.getBackgroundCell(point).equals("garden")) {
                return true;
            }
        }
        return false;
    }

//    @Override
//    public boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
//        // Check if the instance is either DudeNotFull or DudeFull and near a garden
//        if ((this instanceof DudeNotFull || this instanceof DudeFull) && this.isNearGarden(world)) {
//            Gnome gnome = new Gnome(this.getId(), this.getPosition(), this.getImages(),
//                    this.getActionPeriod(), this.getAnimationPeriod(), this.getResourceLimit());
//
//            world.removeEntity(scheduler, this); // Remove current entity (DudeNotFull/DudeFull)
//            world.addEntity(gnome); // Add new Gnome entity
//            gnome.scheduleActions(scheduler, world, imageStore); // Schedule Gnome's actions
//
//            return true;
//        }
//        return false;
//    }


    private int flowersPlanted = 0;

    @Override
    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        if (flowersPlanted < 5) {
            Optional<Entity> target = world.findNearest(this.getPosition(), new ArrayList<>(Arrays.asList(Tree.class, Sapling.class)));
            if (target.isPresent() && moveTo(world, target.get(), scheduler)){
                Point nextToTree = findEmptyAdjacentPosition(world, target.get().getPosition());
                if(nextToTree != null){
                    VirtualWorld.plantFlower(world, nextToTree, imageStore);
                    flowersPlanted++;
                }
            }

            if (flowersPlanted < 5){
                    scheduler.scheduleEvent(this, new Activity(this, world, imageStore), this.getActionPeriod());
                }
        }
    }

    private Point findEmptyAdjacentPosition(WorldModel world, Point treePosition) {
        Point leftPosition = new Point(treePosition.x - 1, treePosition.y);
        if (!world.isOccupied(leftPosition)) {
            return leftPosition;
        }
        return null;
    }
    }




