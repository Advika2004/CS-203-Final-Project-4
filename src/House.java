import processing.core.PImage;

import java.util.List;

public class House extends Entity {

    public static final String HOUSE_KEY = "house";
    public static final int HOUSE_NUM_PROPERTIES = 0;

    public House(String id, Point position, List<PImage> images) {
        super(EntityKind.STUMP, id, position, images, 0, 0, 0, 0, 0, 0);
    }

    @Override
    public Entity create(EntityKind kind, String id, Point position, List<PImage> images, int resourceLimit, int resourceCount, double actionPeriod, double animationPeriod, int health, int healthLimit) {
        return new House(id, position, images);
    }
}



