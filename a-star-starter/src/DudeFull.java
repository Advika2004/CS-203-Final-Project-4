import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DudeFull extends Dude {

    public DudeFull(String id, Point position, List<PImage> images, double actionPeriod,
                    double animationPeriod, int resourceLimit) {
        super(id, position, images, actionPeriod, animationPeriod, resourceLimit);
    }

    public boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler) {
        if (this.getPosition().adjacent(target.getPosition())) {
            return true;
        } else {
            return super.moveTo(world, target, scheduler);
        }
    }

//    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
//        Optional<Entity> fullTarget = world.findNearest(this.getPosition(), new ArrayList<>(List.of(House.class)));
//
//        // Try to move to the house if it's present
//        boolean moved = fullTarget.isPresent() && this.moveTo(world, fullTarget.get(), scheduler);
//
//        // Perform transformation after trying to move
//        this.transform(world, scheduler, imageStore);
//
//        // If not moved or transformed, reschedule this activity
//        if (!moved) {
//            scheduler.scheduleEvent(this, new Activity(this, world, imageStore), this.getActionPeriod());
//        }
//    }


    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        Optional<Entity> fullTarget = world.findNearest(this.getPosition(), new ArrayList<>(List.of(House.class)));

        if (fullTarget.isPresent() && this.moveTo(world, fullTarget.get(), scheduler)) {
            this.transform(world, scheduler, imageStore);
        } else {
            scheduler.scheduleEvent(this, new Activity(this, world, imageStore), this.getActionPeriod());
        }
    }


//    public boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
//        if (this.isNearGarden(world)) {
//            Gnome gnome = new Gnome(this.getId(), this.getPosition(), this.getImages(),
//                    this.getActionPeriod(), this.getAnimationPeriod(), this.getResourceLimit());
//
//            world.removeEntity(scheduler, this);
//            world.addEntity(gnome);
//            gnome.scheduleActions(scheduler, world, imageStore);
//
//            return true;
//        }
//        // Additional transformation logic specific to DudeFull
//        return false;
//    }

    //    public boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
//        if (this.isNearGarden(world)) {
//            Gnome gnome = new Gnome(this.getId(), this.getPosition(), this.getImages(),
//                    this.getActionPeriod(), this.getAnimationPeriod(), this.getResourceLimit());
//
//            world.removeEntity(scheduler, this);
//            world.addEntity(gnome);
//            gnome.scheduleActions(scheduler, world, imageStore);
//
//            return true;
//        }
    @Override
    public boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
        System.out.println("check DF");
        Movable newEntity;

        if (this.isNearGarden(world, this.getPosition())) {
            System.out.println("near garden DF");

            // Transform into Gnome if near a garden
            Gnome anewEntity = new Gnome("gnome", this.getPosition(), imageStore.getImageList("gnome"), 0.5, 0.125, 0);
            world.removeEntity(scheduler, this);
            world.addEntity(anewEntity);
            anewEntity.scheduleActions(scheduler, world, imageStore);

        } else {
            System.out.println("making New DNF");
            newEntity = new DudeNotFull(this.getId(), this.getPosition(), this.getImages(),
                    this.getActionPeriod(), this.getAnimationPeriod(), this.getResourceLimit(), 0);
            world.removeEntity(scheduler, this);
            world.addEntity(newEntity);
            newEntity.scheduleActions(scheduler, world, imageStore);
        }

        // Remove the current entity and replace it with the new entity

        return true;
    }

//    public boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
//        Movable dude = new DudeNotFull(this.getId(), this.getPosition(), this.getImages(),
//                this.getActionPeriod(), this.getAnimationPeriod(), this.getResourceLimit(), 0);
//        world.removeEntity(scheduler, this);
//
//        world.addEntity(dude);
//        dude.scheduleActions(scheduler, world, imageStore);
//
//        return true;
//    }



//    Movable dude = new DudeNotFull(this.getId(), this.getPosition(), this.getImages(),
//            this.getActionPeriod(), this.getAnimationPeriod(), this.getResourceLimit(), 0);
//}
//        world.removeEntity(scheduler, this);
//
//                world.addEntity(dude);
//                dude.scheduleActions(scheduler, world, imageStore);
//
//                return true;
//                }
//                }

//        if (this.isNearGarden(world)) {
//        Gnome gnome = new Gnome(this.getId(), this.getPosition(), this.getImages(),
//                this.getActionPeriod(), this.getAnimationPeriod(), this.getResourceLimit());
//
//        world.removeEntity(scheduler, this);
//        world.addEntity(gnome);
//        gnome.scheduleActions(scheduler, world, imageStore);
//
//        return true;
//    }
//        return false;

//    private boolean isNearGarden(WorldModel world) {
//        for (Point point : VirtualWorld.Find8SurroundingTiles(this.getPosition())) {
//            if (world.getBackgroundCell(point).equals("garden")) {
//                return true;
//            }
//        }
//        return false;
//    }
}