class Move {
    public static void moveRobot(Robot robot, int toX, int toY) {
        Direction currentDirection = robot.getDirection();
        // if toX is < 0 and direction is  left, move robot.stepForward() otherwise turn left until direction is left
        if (toX < robot.getX() && currentDirection.equals(Direction.LEFT)) {
            while (toX <= robot.getX()) {
                robot.stepForward();
            }
        } else {
            if (currentDirection == Direction.UP) {
                robot.turnRight();
            } else {
                robot.turnLeft();
            }
        }
        // once direction matches move toX steps (moveForwards).
        // if toX is > 0 and direction is right, move robot.stepForward() otherwise turn right until direction is right
        if (toX >= robot.getX() && currentDirection.equals(Direction.RIGHT)) {
            while (toX > robot.getX()) {
                robot.stepForward();
            }
        } else {
            if (currentDirection == Direction.DOWN) {
                robot.turnLeft();
            } else {
                robot.turnRight();
            }
        }
        if (toY <= robot.getY() && currentDirection.equals(Direction.DOWN)) {
            while (toY < robot.getY()) {
                robot.stepForward();
            }
        } else {
            if (currentDirection.equals(Direction.RIGHT)) {
                robot.turnRight();
            } else {
                robot.turnLeft();
            }
        }
        if (toY > robot.getY() && currentDirection.equals(Direction.UP)) {
            while (toY >= robot.getY()) {
                robot.stepForward();
            }
        } else {
            if (currentDirection.equals(Direction.RIGHT)) {
                robot.turnLeft();
            } else {
                robot.turnRight();
            }
        }
    }
}

//Don't change code below

enum Direction {
    UP(0, 1),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    private final int dx;
    private final int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public Direction turnLeft() {
        switch (this) {
            case UP:
                return LEFT;
            case DOWN:
                return RIGHT;
            case LEFT:
                return DOWN;
            case RIGHT:
                return UP;
            default:
                throw new IllegalStateException();
        }
    }

    public Direction turnRight() {
        switch (this) {
            case UP:
                return RIGHT;
            case DOWN:
                return LEFT;
            case LEFT:
                return UP;
            case RIGHT:
                return DOWN;
            default:
                throw new IllegalStateException();
        }
    }

    public int dx() {
        return dx;
    }

    public int dy() {
        return dy;
    }
}

class Robot {
    private int x;
    private int y;
    private Direction direction;

    public Robot(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public void turnLeft() {
        direction = direction.turnLeft();
    }

    public void turnRight() {
        direction = direction.turnRight();
    }

    public void stepForward() {
        x += direction.dx();
        y += direction.dy();
    }

    public Direction getDirection() {
        return direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}