import processing.core.PImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DudeNotFull extends Dude {

    private int resourceCount;

    public DudeNotFull(String id, Point position, List<PImage> images, double actionPeriod,
                double animationPeriod, int resourceLimit, int resourceCount) {
        super(id, position, images, actionPeriod, animationPeriod, resourceLimit);
        this.resourceCount = resourceCount;
    }

    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        Optional<Entity> target = world.findNearest(this.getPosition(), new ArrayList<>(Arrays.asList(Tree.class, Sapling.class)));

        if (target.isEmpty() || !this.moveTo(world, target.get(), scheduler) || !this.transform(world, scheduler, imageStore)) {
            scheduler.scheduleEvent(this, new Activity(this, world, imageStore), this.getActionPeriod());
        }
    }
//
//    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
//        Optional<Entity> target = world.findNearest(this.getPosition(), new ArrayList<>(Arrays.asList(Tree.class, Sapling.class)));
//
//        if (target.isPresent() && this.moveTo(world, target.get(), scheduler)) {
//            // Transformation check
//        } else {
//            scheduler.scheduleEvent(this, new Activity(this, world, imageStore), this.getActionPeriod());
//        }
//
//        this.transform(world, scheduler, imageStore);
//    }


    public boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler) {
        if (this.getPosition().adjacent(target.getPosition())) {
            this.resourceCount += 1;
            ((Plant) target).decreaseHealth();
            return true;
        } else {
            return super.moveTo(world, target, scheduler);
        }
    }

    @Override
    public boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
        Movable newEntity = null;
        if (this.isNearGarden(world, this.getPosition())) {
            System.out.println("RAHHHHHHH RAHHHHHHHHHHH");

            // Transform into Gnome if near a garden
//            Gnome g = new Gnome("gnome", pressed, this.imageStore.getImageList("gnome"), 0.5, 0.125, );
//        this.world.addEntity(g);
//        g.scheduleActions(this.scheduler, this.world, this.imageStore);
            Gnome anewEntity = new Gnome("gnome", this.getPosition(), imageStore.getImageList("gnome"), 0.5, 0.125, 0);
//        this.world.addEntity(g);
//        g.scheduleActions(this.scheduler, this.world, this.imageStore);

            scheduler.unscheduleAllEvents(this);
            world.removeEntity(scheduler, this); // Remove current entity (DudeNotFull/DudeFull)
            world.addEntity(anewEntity);
            anewEntity.scheduleActions(scheduler, world, imageStore);

        } else if (this.resourceCount >= this.getResourceLimit() && this.isGrass(world, this.getPosition())) {
            System.out.println("making New DF");

            // Transform into DudeFull if resource count is at or above the limit
            newEntity = new DudeFull(this.getId(), this.getPosition(), this.getImages(),
                    this.getActionPeriod(), this.getAnimationPeriod(), this.getResourceLimit());
            scheduler.unscheduleAllEvents(this);
            world.removeEntity(scheduler, this);
            world.addEntity(newEntity);
            newEntity.scheduleActions(scheduler, world, imageStore);
        }

        if (newEntity != null) {
            // Common transformation steps
            world.removeEntity(scheduler, this);
            scheduler.unscheduleAllEvents(this);

            world.addEntity(newEntity);
            newEntity.scheduleActions(scheduler, world, imageStore);

            return true;
        }
        return false;
    }


//    @Override
//    public boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
//        if (this.resourceCount >= this.getResourceLimit()) {
//            System.out.println("making New DF");
//
//            // Transform into DudeFull if resource count is at or above the limit
//            Movable newEntity = new DudeFull(this.getId(), this.getPosition(), this.getImages(),
//                    this.getActionPeriod(), this.getAnimationPeriod(), this.getResourceLimit());
//
//            // Common transformation steps
//            world.removeEntity(scheduler, this);
//            scheduler.unscheduleAllEvents(this);
//
//            world.addEntity(newEntity);
//            newEntity.scheduleActions(scheduler, world, imageStore);
//
//            return true;
//        }
//
//        return false;
//    }


//
//        @Override
//        public boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
//            if (this.isNearGarden(world)) {
//                Gnome gnome = new Gnome(this.getId(), this.getPosition(), this.getImages(),
//                        this.getActionPeriod(), this.getAnimationPeriod(), this.getResourceLimit());
//
//                world.removeEntity(scheduler, this);
//                world.addEntity(gnome);
//                gnome.scheduleActions(scheduler, world, imageStore);
//
//                return true;
//            }
//            if (this.resourceCount >= this.getResourceLimit()) {
//            Movable dude = new DudeFull(this.getId(), this.getPosition(), this.getImages(), this.getActionPeriod(),
//                    this.getAnimationPeriod(), this.getResourceLimit());
//
//            world.removeEntity(scheduler, this);
//            scheduler.unscheduleAllEvents(this);
//
//            world.addEntity(dude);
//            dude.scheduleActions(scheduler, world, imageStore);
//
//            return true;
//        }
////
////        return false;
//            return false;
//        }
//
//        // Rest of the DudeNotFull class...
//
//
//
//    private boolean isNearGarden(WorldModel world) {
//            for (Point point : VirtualWorld.Find8SurroundingTiles(this.getPosition())) {
//                if (world.getBackgroundCell(point).equals("garden")) {
//                    return true;
//                }
//            }
//            return false;
//        }
        }

        // Rest of the DudeNotFull class...


