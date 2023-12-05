import processing.core.PImage;

import java.util.List;

public class Flower extends Plant{
    // Static variables
    public static final double FLOWER_ACTION_ANIMATION_PERIOD = 1.000; // have to be in sync since grows and gains health at same time
    public static final int FLOWER_HEALTH_LIMIT = 5;
    public static final String FLOWER_KEY = "flower";
    public static final int FLOWER_HEALTH = 0;
    public static final int FLOWER_NUM_PROPERTIES = 1;
    private final int healthLimit;

    public Flower(String id, Point position, List<PImage> images, double actionPeriod, double animationPeriod, int health, int healthLimit) {
        super(id, position, images, actionPeriod, animationPeriod, health);
        this.healthLimit = healthLimit;
    }
    @Override
    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        this.increaseHealth();
        if (!this.transform(world, scheduler, imageStore)) {
            scheduler.scheduleEvent(this, new Activity(this, world, imageStore), this.getActionPeriod());
        }
    }
}