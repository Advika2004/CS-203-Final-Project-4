import processing.core.PImage;

import java.util.List;

public abstract class Move extends Active implements MoveTo, NextPosition{

    public Move(){
    }

    protected Point position;

    public Move(EntityKind kind, String id, Point position, List<PImage> images, int resourceLimit, int resourceCount, double actionPeriod, double animationPeriod, int health, int healthLimit) {
        super(kind, id, position, images, resourceLimit, resourceCount, actionPeriod, animationPeriod, health, healthLimit);
    }

    protected Point getPosition(){
        return position;
    }
}
