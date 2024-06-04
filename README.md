# Introduction
## Author
- **Full Name (OIT/Canvas Account Name):** Jaehoon Song
- **Email:** jsong421@gatech.edu
## Main Scene
The main scene of the project is the following.
```
./Assets/Scenes/demo.unity
```



# Requirements 
Here are the modifications check list (requirements) according to the assignment instruction.
1. ```SomeDude_NoRootMotion``` – disable turn animation by NOT passing turn input parameter to the Animator in the script (only turning programmatically and the character should still be walking during the turn)
2. 
- Added functionality for exiting the game.
- Added functionality for restarting the game.
- Implemented game over conditions.
- Implemented text object control for better user experience.
- Implemented player movement restrictions after game over.
## Build Observations
The followings are the observations expected by default (or given project) and by my modifications.
- Upon running the build, the player can control a character within the game environment (**default**).
- Pickup items can be collected by the player (**default**).
- Obstacle objects are added to distract the player, it will block movements of the player.
- The game ends when all pickup items are collected, displaying a game over message.
- The player can restart the game by pressing 'R' or quit by pressing 'ESC'.
## Code Changes
The followings are the code changes highlighted for implementation of the modifications. 
- **Restart Functionality:** Check the `Update()` method in `./Assets/Objects/Controllers/Player.cs` for the implementation of restarting the game.
- **Game Over Conditions:** Look for the `isPaused` variable and collision with pickup items in `./Assets/Objects/Controllers/Player.cs` to understand game over conditions.
- **Obstacles Object Definition:** Check the `./Assets/Objects/Obstacle.prefab` to see the object settings such as `isTrigger` being `false`.
- **Obstacles Player Blocking:** Check the `OnCollisionEnter()` method in `./Assets/Objects/Controllers/Player.cs` for defining blocking actions by creating non-trigger objects logic.
- **Game Over Message:** Look for the `if (count == 7)` statement in `./Assets/Objects/Controllers/Player.cs` to see how the text object is controlled while playing the game.
- **Player Movement Restriction:** Refer to the if-statement from the movement handling method (`FixedUpdate()`) in `./Assets/Objects/Controllers/Player.cs` for player movement not controlled after game over.
## Extra Implementations
The project does not include additional implementations since the assignment has a tutorial style format.
## Known Issues (Bugs)
There are no known bugs or incomplete features.



# Instructions
The input code is listed below according to the assignment instruction.
- To run the game, open the Unity project and play the scene.
  ```
  ./Build/Windows/Song_JH_m1.exe
  ```
  ```
  ./Build/OSX/Song_JH_m1.app
  ```
- Press 'T' to switch your control on each character in the scene.
- Use "WASD" or arrow keys to move the character chosen, dudes or minions. (Use thumbstick for analog movement of Gamepad control.)
- Use number keys, 1-9 (10%-90%) and 0 (100%), to adjust the max speed of your character.
- Use 'Q' and 'E' keys to turn left and right with adjusted turn rates to 50%.
- Walk or run around the scene as you want.
- Use 'Ctrl' (Fire1) for button press animation near the red balloon (logical button).
## Dependencies and External Assets
No dependencies and external assets are specified since the assignment has a tutorial style format.



# License
This project is licensed under CS4455, Georgia Institute of Technology - see the [LICENSE.md](LICENSE.md) file for details.
