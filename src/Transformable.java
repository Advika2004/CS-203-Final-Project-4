public abstract class Transformable extends Active implements MoveTo, NextPosition{

    public boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
        return false;
    }

}

}

