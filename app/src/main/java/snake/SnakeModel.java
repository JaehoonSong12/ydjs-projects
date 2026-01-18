package snake;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Model that encapsulates all Snake game rules.
 */
public class SnakeModel {

    enum Direction {UP, DOWN, LEFT, RIGHT}

    private final int gridWidth, gridHeight;
    private final Deque<int[]> snake;
    private Direction dir = Direction.RIGHT;
    private int[] food;
    private boolean grow = false;
    private boolean gameOver = false;
    private final Random rnd = new Random();

    SnakeModel(int gridWidth, int gridHeight) {
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        this.snake = new LinkedList<>();

        int centerX = gridWidth / 3;
        int centerY = gridHeight / 2;

        snake.add(new int[]{centerX - 2, centerY});
        snake.add(new int[]{centerX - 1, centerY});
        snake.add(new int[]{centerX, centerY});
        dir = Direction.RIGHT;

        placeFood();
    }

    List<int[]> getSnake() {
        return List.copyOf(snake);
    }

    int getSnakeSize() {
        return snake.size();
    }

    int[] getFood() {
        return food == null ? null : new int[]{food[0], food[1]};
    }

    boolean isGameOver() {
        return gameOver;
    }

    int getScore() {
        return Math.max(0, snake.size() - 3);
    }

    void setDirection(Direction d) {
        if (snake.size() > 1) {
            Direction opposite = oppositeOf(dir);
            if (d == opposite) {
                return;
            }
        }
        dir = d;
    }

    private Direction oppositeOf(Direction d) {
        switch (d) {
            case UP:
                return Direction.DOWN;
            case DOWN:
                return Direction.UP;
            case LEFT:
                return Direction.RIGHT;
            default:
                return Direction.LEFT;
        }
    }

    void update() {
        if (gameOver) return;
        int[] head = snake.peekLast();
        if (head == null) {
            gameOver = true;
            return;
        }

        int nx = head[0];
        int ny = head[1];
        switch (dir) {
            case UP:
                ny--;
                break;
            case DOWN:
                ny++;
                break;
            case LEFT:
                nx--;
                break;
            case RIGHT:
                nx++;
                break;
        }

        if (nx < 0 || nx >= gridWidth || ny < 0 || ny >= gridHeight) {
            gameOver = true;
            return;
        }

        for (int[] s : snake) {
            if (s[0] == nx && s[1] == ny) {
                int[] tail = snake.peekFirst();
                if (!(nx == tail[0] && ny == tail[1] && !grow)) {
                    gameOver = true;
                    return;
                }
            }
        }

        snake.addLast(new int[]{nx, ny});

        if (food != null && nx == food[0] && ny == food[1]) {
            grow = true;
            placeFood();
        }

        if (!grow) {
            snake.removeFirst();
        } else {
            grow = false;
        }
    }

    private void placeFood() {
        int attempts = 0;
        final int maxAttempts = gridWidth * gridHeight;
        while (attempts++ < maxAttempts) {
            int fx = rnd.nextInt(gridWidth);
            int fy = rnd.nextInt(gridHeight);
            boolean onSnake = false;
            for (int[] s : snake) {
                if (s[0] == fx && s[1] == fy) {
                    onSnake = true;
                    break;
                }
            }
            if (!onSnake) {
                food = new int[]{fx, fy};
                return;
            }
        }
        food = null;
    }
}
