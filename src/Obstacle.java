import processing.core.PImage;

import java.util.List;

public class Obstacle extends Entity{


    public Obstacle(String id, Point position, double animationPeriod, List<PImage> images) {
        super(EntityKind.OBSTACLE, id, position, images, 0, 0, 0, animationPeriod, 0, 0);
    }

    public Entity create(EntityKind kind, String id, Point position, List<PImage> images, int resourceLimit, int resourceCount, double actionPeriod, double animationPeriod, int health, int healthLimit) {
        return new Obstacle(id, position, animationPeriod, images);
    }
}
