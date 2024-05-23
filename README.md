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

- Upon running the build, the player can control a character within the game environment (default).
- Pickup items can be collected by the player (default).
- Obstacle objects are added to distract the player, it will block movements of the player.
- The game ends when all pickup items are collected, displaying a game over message.
- The player can restart the game by pressing 'R' or quit by pressing 'ESC'.
## Code Changes
- **Restart Functionality:** Check the `Update()` method in the `Player.cs` script for the implementation of restarting the game.
- **Game Over Conditions:** Look for the `isPaused` variable and collision with pickup items in the `Player.cs` script to understand game over conditions.
- **Player Score Tracking:** Check the `OnTriggerEnter()` method in the `Player.cs` script for updating the player's score.
- **Player Movement Controls:** Refer to the movement handling methods (`OnMove()` and `FixedUpdate()`) in the `Player.cs` script for player movement implementation.
## Extra Implementations
- The project includes additional features such as:
  - Improved player movement using Unity's physics engine.
  - Dynamic player score tracking.
  - Restart functionality for better user experience.
## Known Issues (Bugs)
- There are no known bugs or incomplete features.



# Instructions
Here is the usage explain of the implementation in detail including features and expected behavior.
- To run the game, open the Unity project and play the scene.
  ```
  ./Build/Windows/win.app/song_jh_m0.exe
  ```
- Use the arrow keys or arrow keys to move the player (ball).
- Collect all pickup items (rotating cubes) to complete the game.
- Press 'R' to restart the game after it ends.
- Press 'ESC' to quit the game.
## External Assets
The following is a list up of external assets and the purpose of their use in Unity.
- **TextMeshPro** package: to display text elements on the canvas object.
## Dependencies
- Ensure that the TextMeshPro package is installed for proper functionality.
