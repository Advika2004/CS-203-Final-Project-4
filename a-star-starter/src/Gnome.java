import processing.core.PImage;

import java.util.List;
import java.util.Random;

public class Gnome extends Movable {

    public Gnome(String id, Point position, List<PImage> images, double actionPeriod, double animationPeriod){
        super(id, position, images, actionPeriod, animationPeriod);
    }

    @Override
    public boolean moveTo(WorldModel model, Entity target, EventScheduler scheduler) {
        return false; //change this
    }

    @Override
    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        int direction = new Random().nextInt(0,4);//change this
    }
}
