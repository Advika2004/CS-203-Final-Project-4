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

    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        Optional<Entity> fullTarget = world.findNearest(this.getPosition(), new ArrayList<>(List.of(House.class)));

        if (fullTarget.isPresent() && this.moveTo(world, fullTarget.get(), scheduler)) {
            this.transform(world, scheduler, imageStore);
        } else {
            scheduler.scheduleEvent(this, new Activity(this, world, imageStore), this.getActionPeriod());
        }
    }

    @Override
    public boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {

        Movable newEntity;

        if (this.isNearGarden(world, this.getPosition())) {


            // Transform into Gnome if near a garden
            Gnome anewEntity = new Gnome("gnome", this.getPosition(), imageStore.getImageList("gnome"), 0.5, 0.125, 0);
            world.removeEntity(scheduler, this);
            world.addEntity(anewEntity);
            anewEntity.scheduleActions(scheduler, world, imageStore);

        } else {

            newEntity = new DudeNotFull(this.getId(), this.getPosition(), this.getImages(),
                    this.getActionPeriod(), this.getAnimationPeriod(), this.getResourceLimit(), 0);
            world.removeEntity(scheduler, this);
            world.addEntity(newEntity);
            newEntity.scheduleActions(scheduler, world, imageStore);
        }

        return true;
    }
}


