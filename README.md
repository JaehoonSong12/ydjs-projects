# Project Name README

## Overview
This README.md file outlines the changes made to the game logic script of the Unity project.

## Changes Overview
- Added functionality for restarting the game.
- Implemented game over conditions.
- Improved player score tracking.
- Enhanced player movement controls.

## Detailed Changes

### Restart Functionality
- **Unity Technical Perspective:** Added a condition in the `Update()` method to check if the 'R' key is pressed to restart the game using `SceneManager.LoadScene()`.
- **Game Logic Perspective:** Provides players with the ability to restart the game after it ends.

### Game Over Conditions
- **Unity Technical Perspective:** Utilized a boolean variable `isPaused` to control game over state. When the player collects all pickup items, the game is paused, and a game over message is displayed.
- **Game Logic Perspective:** Marks the end of the game when all pickup items are collected, displaying a game over message and preventing further player movement.

### Player Score Tracking
- **Unity Technical Perspective:** Updated the player's score display using `TextMeshProUGUI`, modifying the text based on the number of pickup items collected.
- **Game Logic Perspective:** Provides visual feedback to the player on their progress by updating the displayed score whenever a pickup item is collected.

### Player Movement Controls
- **Unity Technical Perspective:** Implemented player movement using Unity's `Rigidbody` and `FixedUpdate()` method for physics-based movement.
- **Game Logic Perspective:** Allows players to control the player character using keyboard input, enhancing the gameplay experience.

## Usage
- To run the game, open the Unity project and play the scene.
- Use the arrow keys or WASD keys to move the player character.
- Collect all pickup items to complete the game.
- Press 'R' to restart the game after it ends.
- Press 'ESC' to quit the game.

## Dependencies
- This project requires the TextMeshPro package for displaying text elements in Unity.

## Authors
- [Your Name]

## License
This project is licensed under the [License Name] - see the [LICENSE.md](LICENSE.md) file for details.
