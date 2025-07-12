import pygame
import sys

def main():
    # Initialize Pygame
    pygame.init()

    # Set up display
    WIDTH, HEIGHT = 640, 480
    screen = pygame.display.set_mode((WIDTH, HEIGHT))
    pygame.display.set_caption("Pygame Project Example")

    # Set up colors
    WHITE = (255, 255, 255)
    BLUE = (0, 120, 255)

    # Set up a clock for FPS
    clock = pygame.time.Clock()

    # Main loop
    running = True
    x, y = WIDTH // 2, HEIGHT // 2
    radius = 30
    speed = 5

    while running:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                running = False

        # Handle keys
        keys = pygame.key.get_pressed()
        if keys[pygame.K_LEFT]:
            x -= speed
        if keys[pygame.K_RIGHT]:
            x += speed
        if keys[pygame.K_UP]:
            y -= speed
        if keys[pygame.K_DOWN]:
            y += speed

        # Fill the screen
        screen.fill(WHITE)

        # Draw a circle
        pygame.draw.circle(screen, BLUE, (x, y), radius)

        # Update the display
        pygame.display.flip()
        clock.tick(60)

    pygame.quit()
    sys.exit()