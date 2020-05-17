package org.example;

import org.example.guard.Guard;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Snake {

    public static final int SIZE = 18;
    public SnakePart root;

    public Snake(Direction direction, SnakePartPainter snakePartPainter) {
        root = new SnakePart(direction, new Point(4,5), true, snakePartPainter);
        root.init(3);
    }

    public void grow() {
        root.markForGrow();
    }

    public void draw(Direction direction, boolean move) {
        root.drawPart(direction, move);
    }

    public void isMoving(boolean moving) {
        root.setIsMoving(moving);
    }

    public void addGuard(Guard guard) {
        this.root.addGuard(guard);
    }

    /** snake part */
    public static class SnakePart {

        public boolean isRoot;
        public Point point;
        public Direction direction;
        private SnakePart nextPart;
        private SnakePartPainter snakePartPainter;
        private boolean addChild;
        private boolean isMoving;
        private List<Guard> guardList = new ArrayList<>();

        public SnakePart(Direction direction, Point point, boolean isRoot, SnakePartPainter snakePartPainter) {
            this.direction = direction;
            this.point = point;
            this.isRoot = isRoot;
            this.snakePartPainter = snakePartPainter;
        }

        public void init(int snakeSize) {
            if (snakeSize == 0) return;
            addChild();
            if (nextPart != null){
                nextPart.init(--snakeSize);
            }
        }

        private void setIsMoving(boolean isMoving) {
            this.isMoving = isMoving;
            if (nextPart != null) nextPart.setIsMoving(isMoving);
        }
        public void drawPart(Direction direction, boolean move) {
            this.direction = direction;
            drawPart(move);
        }

        public void drawPart(boolean move) {
            if (move)
                move();
            snakePartPainter.draw(point, isRoot);
            notifyGuards();
            if (nextPart != null) {
                nextPart.drawPart(move);
                nextPart.addChild = addChild;
                if (isMoving && move)
                    nextPart.direction = direction;
            }else if (addChild) {
                addChild();
            }
        }

        private void move() {
            if (!isMoving) return;
            if (direction.equals(Direction.UP) || direction.equals(Direction.DOWN)) {
                point.y += direction.getDelta();
            }else {
                point.x += direction.getDelta();
            }
        }

        public void markForGrow() {
            if (nextPart != null) {
                nextPart.markForGrow();
                return;
            }
            addChild = true;
        }

        public void addChild() {
            int dx = direction.equals(Direction.LEFT) || direction.equals(Direction.RIGHT) ? direction.getDelta() * -1 : 0;
            int dy = direction.equals(Direction.UP) || direction.equals(Direction.DOWN) ? direction.getDelta() * -1 : 0;
            nextPart = new SnakePart(direction, new Point(point.x+dx, point.y+dy), false, snakePartPainter);
            nextPart.setIsMoving(true);
            Guard[] guards = new Guard[guardList.size()];
            guardList.toArray(guards);
            nextPart.addGuard(guards);
            addChild = false;
        }

        public void addGuard(Guard... guard) {
            this.guardList.addAll(Arrays.asList(guard));
            if (nextPart != null)
                nextPart.addGuard(guard);
        }

        private void notifyGuards() {
            for (Guard guard : guardList) {
                guard.checkMove(this);
            }
        }
    }
}
