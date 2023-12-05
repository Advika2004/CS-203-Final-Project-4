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


            Gnome anewEntity = new Gnome("gnome", this.getPosition(), imageStore.getImageList("gnome"), 0.5, 0.125, 0);

            scheduler.unscheduleAllEvents(this);
            world.removeEntity(scheduler, this);
            world.addEntity(anewEntity);
            anewEntity.scheduleActions(scheduler, world, imageStore);

        } else if (this.resourceCount >= this.getResourceLimit() && this.isGrass(world, this.getPosition())) {


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
}




