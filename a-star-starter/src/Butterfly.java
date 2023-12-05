import processing.core.PImage;

import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class Butterfly extends Movable {

    public Butterfly(String id, Point position, List<PImage> images, double actionPeriod, double animationPeriod) {
        super(id, position, images, actionPeriod, animationPeriod);
    }

    public boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler) {
        if (this.getPosition().adjacent(target.getPosition())) {
            world.removeEntity(scheduler, target);
            return true;
        } else {
            Point nextPos = this.nextPosition(world, target.getPosition());

            if (!this.getPosition().equals(nextPos)) {
                world.moveEntity(scheduler, this, nextPos);
            }
            return false;
        }
    }

    public Point nextPosition(WorldModel world, Point destPos){
        Point startPoint =  getPosition();
        Point endPoint =  destPos;
        List<Point> newPositions = new AStarPathingStrategy()
                .computePath(startPoint, endPoint,
                        p -> world.withinBounds(p) && !world.isOccupied(p),//canPassThrough
                        (p1,p2) -> p1.adjacent(p2), //withinReach
                        PathingStrategy.CARDINAL_NEIGHBORS  //potentialNeighbors
                );
        if (newPositions.isEmpty()) {
            return startPoint;
        }
        return newPositions.get(0);
    }

    @Override
    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        Optional<Entity> butterflyTarget = world.findNearest(this.getPosition(), new ArrayList<>(List.of(Flower.class)));

        if (butterflyTarget.isPresent()) {
            Point tgtPos = butterflyTarget.get().getPosition();

            if (this.moveTo(world, butterflyTarget.get(), scheduler)) {

                Flower flower = new Flower(Flower.FLOWER_KEY + "_" + butterflyTarget.get().getId(), tgtPos,
                        imageStore.getImageList(Flower.FLOWER_KEY), Flower.FLOWER_ACTION_ANIMATION_PERIOD,
                        Flower.FLOWER_ACTION_ANIMATION_PERIOD, 0, Flower.FLOWER_HEALTH_LIMIT);


                flower.scheduleActions(scheduler, world, imageStore);
            }
        }

        scheduler.scheduleEvent(this, new Activity(this, world, imageStore), this.getActionPeriod());
    }
}

















//
//    @Override
//    public boolean moveTo(WorldModel model, Entity target, EventScheduler scheduler) {
//        if (this.getPosition().adjacent(target.getPosition())) {
//            model.removeEntity(scheduler, target);
//            return true;
//        } else {
//            Point nextPos = this.nextPosition(model, target.getPosition());
//
//            if (!this.getPosition().equals(nextPos)) {
//                model.moveEntity(scheduler, this, nextPos);
//
//            return false;
//        }
//    }
//
//    @Override
//    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
//        int direction = new Random().nextInt(0, 4);//change this
//
//        Optional<Entity> butterflyTarget = world.findNearest(this.getPosition(), new ArrayList<>(List.of(Flower.class)));
//
//        if (butterflyTarget.isPresent()) {
//            Point tgtPos = butterflyTarget.get().getPosition();
//
//            if (this.moveTo(world, butterflyTarget.get(), scheduler)) {
//
//                Flower flower = new Flower(Flower.FLOWER_KEY + "_" + butterflyTarget.get().getId(), tgtPos,
//                        imageStore.getImageList(Flower.FLOWER_KEY), Flower.FLOWER_ACTION_ANIMATION_PERIOD,
//                        Flower.FLOWER_ACTION_ANIMATION_PERIOD, 0, Flower.FLOWER_HEALTH_LIMIT);
//
//                world.addEntity(flower);
//                flower.scheduleActions(scheduler, world, imageStore);
//            }
//        }
//
//        scheduler.scheduleEvent(this, new Activity(this, world, imageStore), this.getActionPeriod());
//    }
//
//
//    }
//
//        public Point nextPosition (WorldModel world, Point destPos){
//            Point startPoint = getPosition();
//            Point endPoint = destPos;
//            List<Point> newPositions = new AStarPathingStrategy()
//                    .computePath(startPoint, endPoint,
//                            p -> world.withinBounds(p) && !world.isOccupied(p),//canPassThrough
//                            (p1, p2) -> p1.adjacent(p2), //withinReach
//                            PathingStrategy.CARDINAL_NEIGHBORS  //potentialNeighbors
//                    );
//            if (newPositions.isEmpty()) {
//                return startPoint;
//            }
//            return newPositions.get(0);
//        }
//    }