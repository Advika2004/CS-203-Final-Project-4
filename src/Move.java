import processing.core.PImage;

import java.util.List;

public abstract class Move extends Active implements MoveTo, NextPosition{

    public Move(){
    }

    protected Point position;

//    public Move(String id, Point position, double actionPeriod, double animationPeriod, int resourceLimit, List<PImage> images) {
//        super(id, position, images, resourceLimit, actionPeriod, animationPeriod);
//    }

    protected Point getPosition(){
        return position;
    }
}
