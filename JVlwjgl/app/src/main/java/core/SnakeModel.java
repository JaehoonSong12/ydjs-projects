package core;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * The "Model" for the Snake game. (Model-View-Controller)
 * This class encapsulates all game logic, rules, and data,
 * completely separate from any rendering or input handling.
 * (Unchanged from original, but with Javadoc added)
 */
public class SnakeModel {

    /**
     * Represents the four possible directions of movement.
     */
    public enum Direction {UP, DOWN, LEFT, RIGHT}

    private final int gridWidth, gridHeight;
    /**
     * The snake's body, represented as a Deque of [x,y] coordinates.
     * The head is at the end (peekLast), tail is at the front (peekFirst).
     */
    private final Deque<int[]> snake;
    private Direction dir = Direction.RIGHT;
    private int[] food;
    private boolean grow = false;
    private boolean gameOver = false;
    private final Random rnd = new Random();

    /**
     * Creates a new SnakeModel for a grid of the given size.
     */
    public SnakeModel(int gridWidth, int gridHeight) {
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        this.snake = new LinkedList<>();

        // Start snake in the middle-left
        int centerX = gridWidth / 3;
        int centerY = gridHeight / 2;

        // initial snake length 3
        snake.add(new int[]{centerX - 2, centerY});
        snake.add(new int[]{centerX - 1, centerY});
        snake.add(new int[]{centerX, centerY});
        dir = Direction.RIGHT; // Ensure starting direction

        placeFood();
    }

    // --- Public Getters for State (Read-only) ---

    /**
     * Gets a read-only copy of the snake's segments.
     */
    public List<int[]> getSnake() {
        return List.copyOf(snake);
    }

    public int getSnakeSize() {
        return snake.size();
    }

    /**
     * Gets a copy of the food's position.
     */
    public int[] getFood() {
        return food == null ? null : new int[]{food[0], food[1]};
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getScore() {
        return Math.max(0, snake.size() - 3);
    } // Initial length is 3

    /**
     * Sets the snake's new direction, preventing 180-degree turns.
     */
    public void setDirection(Direction d) {
        if (snake.size() > 1) {
            Direction opp = oppositeOf(dir);
            if (d == opp) {
                return; // prevent reverse
            }
        }
        dir = d;
    }

    /**
     * Helper to find the opposite of a direction.
     */
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

    /**
     * The main update tick for the game logic.
     * Moves the snake, checks for collisions, and handles food.
     */
    public void update() {
        if (gameOver) return;

        // 1. Calculate new head position
        int[] head = snake.peekLast();
        if (head == null) { // Should not happen
            gameOver = true;
            return;
        }

        int nx = head[0], ny = head[1];
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

        // 2. Check for wall collision
        if (nx < 0 || nx >= gridWidth || ny < 0 || ny >= gridHeight) {
            gameOver = true;
            return;
        }

        // 3. Check for self-collision
        // (Iterate all but the tail, which will move)
        for (int[] s : snake) {
            if (s[0] == nx && s[1] == ny) {
                // Special case: if we are moving into the tail's old spot
                // and we are not growing, it's NOT a collision.
                int[] tail = snake.peekFirst();
                if (nx == tail[0] && ny == tail[1] && !grow) {
                    // This is ok, the tail will move
                } else {
                    gameOver = true;
                    return;
                }
            }
        }

        // 4. Add the new head
        snake.addLast(new int[]{nx, ny});

        // 5. Check for food collision
        if (food != null && nx == food[0] && ny == food[1]) {
            grow = true; // Flag to grow on the *next* update
            placeFood();
        }

        // 6. Remove tail if not growing
        if (!grow) {
            snake.removeFirst();
        } else {
            // We grew, reset the flag
            grow = false;
        }
    }

    /**
     * Finds a new random location for the food that is not
     * on the snake's body.
     */
    private void placeFood() {
        // Naive placement: random location not on snake
        int attempts = 0;
        final int maxAttempts = gridWidth * gridHeight; // Max possible

        while (attempts++ < maxAttempts) {
            int fx = rnd.nextInt(gridWidth);
            int fy = rnd.nextInt(gridHeight);

            // Check if (fx, fy) is on the snake
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

        // Fallback: no food (grid is full)
        food = null;
        // This could also trigger a "win" condition
    }
}
