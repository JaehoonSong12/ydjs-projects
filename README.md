
# Introduction

## Author
- **Full Name (OIT/Canvas Account Name):** Jaehoon Song
- **Email:** jsong421@gatech.edu
- **Institution:** Georgia Tech

## Main Scene
The main scene of the project is the following.
```
./Assets/Scenes/SampleScene.unity
```









# Requirements 


## Build Observations
- Upon running the build, the player can control a character within the game environment.
- Pickup items can be collected by the player.
- The game ends when all pickup items are collected, displaying a game over message.
- The player can restart the game by pressing 'R' or quit by pressing 'ESC'.

## Code Changes
- **Restart Functionality:** Check the `Update()` method in the `Player.cs` script for the implementation of restarting the game.
- **Game Over Conditions:** Look for the `isPaused` variable and collision with pickup items in the `Player.cs` script to understand game over conditions.
- **Player Score Tracking:** Check the `OnTriggerEnter()` method in the `Player.cs` script for updating the player's score.
- **Player Movement Controls:** Refer to the movement handling methods (`OnMove()` and `FixedUpdate()`) in the `Player.cs` script for player movement implementation.



# -------------------------------





## Assignment Requirements
- All assignment requirements have been completed as specified.
- There are no known bugs or incomplete features.

## Extra Implementations
- The project includes additional features such as:
  - Improved player movement using Unity's physics engine.
  - Dynamic player score tracking.
  - Restart functionality for better user experience.

## Usage
- To run the game, open the Unity project and play the scene.
- Use the arrow keys or WASD keys to move the player character.
- Collect all pickup items to complete the game.
- Press 'R' to restart the game after it ends.
- Press 'ESC' to quit the game.

## External Assets
- The project utilizes the TextMeshPro package for displaying text elements in Unity.

## Dependencies
- Ensure that the TextMeshPro package is installed for proper functionality.
- Include the `/Packages/` folder when building or sharing the project.

## License
This project is licensed under the [License Name] - see the [LICENSE.md](LICENSE.md) file for details.






# Assignment Requirements 

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







