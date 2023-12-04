import processing.core.PImage;

import java.util.*;

public class Gnome extends Dude {
   // private int resourceCount;


    public Gnome(String id, Point position, List<PImage> images, double actionPeriod,
                 double animationPeriod, int resourceLimit) {
        super(id, position, images, actionPeriod, animationPeriod, resourceLimit);
   //     this.resourceCount = resourceCount;

    }

    public boolean isNearGarden(WorldModel world) {
        System.out.println("is near garden");

        for (Point point : VirtualWorld.Find8SurroundingTiles(this.getPosition())) {
            if (world.getBackgroundCell(point).equals("garden")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
        System.out.println("transform");

        if (this.isNearGarden(world)) {
            Gnome gnome = new Gnome(this.getId(), this.getPosition(), this.getImages(),
                    this.getActionPeriod(), this.getAnimationPeriod(), this.getResourceLimit());
            world.removeEntity(scheduler, this);
            world.addEntity(gnome);
            gnome.scheduleActions(scheduler, world, imageStore);
            return true;
        }
        return false;
    }

    private int flowersPlanted = 0;


    public boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler) {
        System.out.println("move to");
        if (this.getPosition().adjacent(target.getPosition())) {
            ((Plant) target).decreaseHealth();
            return true;
        } else {
            return super.moveTo(world, target, scheduler);
        }
    }




    @Override
    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
           System.out.println("Dude executing activity!");


        if (flowersPlanted < 5) {
            Optional<Entity> target = world.findNearest(this.getPosition(), new ArrayList<>(Arrays.asList(Tree.class, Sapling.class)));

            // Check if the dude is near a garden and transform if needed
            if (isNearGarden(world)) {
                transform(world, scheduler, imageStore);
            }

            // Continue with the regular activity
            if (target.isPresent() && moveTo(world, target.get(), scheduler)) {
                Point nextToTree = findEmptyAdjacentPosition(world, target.get().getPosition());
                if (nextToTree != null) {
                    VirtualWorld.plantFlower(world, nextToTree, imageStore);
                    flowersPlanted++;
                }
            }

            if (flowersPlanted < 5) {
                scheduler.scheduleEvent(this, new Activity(this, world, imageStore), this.getActionPeriod());
            }
        }
    }

//    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
//        if (flowersPlanted < 5) {
//            Optional<Entity> target = world.findNearest(this.getPosition(), new ArrayList<>(Arrays.asList(Tree.class, Sapling.class)));
//            if (target.isPresent() && moveTo(world, target.get(), scheduler)){
//                Point nextToTree = findEmptyAdjacentPosition(world, target.get().getPosition());
//                if(nextToTree != null){
//                    VirtualWorld.plantFlower(world, nextToTree, imageStore);
//                    flowersPlanted++;
//                }
//            }
//
//            if (flowersPlanted < 5){
//                    scheduler.scheduleEvent(this, new Activity(this, world, imageStore), this.getActionPeriod());
//                }
//        }
//    }

    private Point findEmptyAdjacentPosition(WorldModel world, Point treePosition) {
        Point leftPosition = new Point(treePosition.x - 1, treePosition.y);
        if (!world.isOccupied(leftPosition)) {
            return leftPosition;
        }
        return null;
    }
    }




