import processing.core.PImage;

import java.util.List;

public class Stump extends Entity {
    public static final String STUMP_KEY = "stump";
    public static final int STUMP_NUM_PROPERTIES = 0;
    public Stump(String id, Point position, List<PImage> images)
    {
        super(EntityKind.STUMP, id, position, images, 0, 0, 0, 0, 0, 0);
    };

    @Override
    public Entity create(EntityKind kind, String id, Point position, List<PImage> images, int resourceLimit, int resourceCount, double actionPeriod, double animationPeriod, int health, int healthLimit) {
        return new Stump(id, position, images);
    }
}
