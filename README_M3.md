<!-- 
 @requires
 1. VSCode extension: null
 2. Shortcut: 'Ctrl' + 'Shift' + 'V'
 3. Split: Drag to right (->)
 -->
<!-- Anchor Tag (Object) for "back to top" -->
<a id="readme-top"></a>
















<!-- done -->
# Introduction
## Author
- **Full Name (OIT/Canvas Account Name):** Jaehoon Song
- **Email:** jsong421@gatech.edu
## Main Scene
The main scene of the project is the following.
```
./Assets/Scenes/GameMenuScene.unity
./Assets/Scenes/demo_m3.unity
```
<!-- ./Assets/Scenes/demo_m2.unity -->
<p align="right">(<a href="#readme-top">back to top</a>)</p><br /><br /><br />

















<!-- done -->
# Project Requirements 
Here are the modifications check list (requirements) according to the assignment instructions. The topic is "**Game Menu and Interactive Elements**," aiming enhanced gameplay by including a game menu with post-processed scene imagery, collectable items, and interactive animations.
1. **M2 Pause Script**
   - Ensure M2 pause script is not active in your scenes.
2. **Working Game Start Menu**
   - Create a separate scene for the game start menu.
   - The menu panel must be centered on the screen and should not completely fill the screen.
   - Include a "Start Game" button.
   - Include an "Exit" button.
   - Apply a post-processing effect to the camera for a visually appealing background.
3. **Working In-Game Menu**
   - The overlay panel must be centered on the screen and should not completely fill the screen.
   - Include a "Restart Game" button.
   - Include an "Exit" button.
   - The panel should toggle on and off with the Escape key.
   - The game should pause when the menu is enabled.
4. **Collectable Ball**
   - Implement a collectable ball that only the `SomeDude_RootMotion` character can collect.
5. **Trigger-based Animated Prefab Object**
   - Place the animated prefab object in three (3) locations within the scene.
   - The object should animate via Mecanim in a compelling way when the player gets close enough.
   - The object should reset when the player moves far enough away, provided it is in a triggered state.
   - Ensure transitions between states are smooth.



<!-- done -->
## Build Observations
The followings are the observations expected by default (or given project) and by my modifications.
- Upon running the build, the player can notice M2 pause script has been disabled (_Unity modified_).
- Game menu scene with post-processed imagery will be observed with `Panel`, `Start Game`, and `Exit` buttons (**Script created**, _Unity modified: Package Manager > Post Processing_).
- In-Game menu canvas with `Panel`, `Restart Game`, and `Exit` buttons will be observed (**Script created**, _Unity modified: Canvas Group_). The `Canvas Group` component along with `PauseMenuToggle.cs` script allows toggling the menu and pausing the game using the Escape key.
- Purple collectable ball (Sphere GameObject with a collider set as a trigger) that SomeDude_RootMotion can pick up will be observed (**Script created**, _Unity modified: Canvas Group_).
- Three `AnimatedPrefab.prefab` with proximity detection and animation triggering will be observed (**Script created**, _Unity modified: Animator Controller_).



<!-- done -->
## Code Changes
The followings are the code changes highlighted for implementation of the modifications.
1. **Pause Disabled**: Check the setup in _Unity_.
2. **Game Menu Scene**: Check the implementation in `GameMenuScene.unity` using the "Post Processing" package. <br/>`Panel`, `Start Game`, and `Exit` buttons are handled by `GameStarter.cs` and `GameQuitter.cs` scripts.
3. **In-Game Menu**: Check the in-game menu canvas GameObject implementation using the "Post Processing" package. <br/>`Panel`, `Restart Game`, and `Exit` buttons are handled by `GameRestarter.cs` and `GameQuitter.cs` scripts. <br/>`Canvas Group` component along with `PauseMenuToggle` script allows toggling the menu and pausing the game using the Escape key.
4. **Collectable Ball**: Check the implementation in the `CollectableBall` script for the purple collectable ball. <br/>`OnTriggerEnter` method allows SomeDude_RootMotion to pick it up.
5. **Interactive Game Object Animation**: Check the implementation in `AnimatedPrefab.prefab` based on the empty GameObject named `AnimatedObjectRoot` with `AnimatedGeometry.controller`, `AnimationUp.anim`, `AnimationDown.anim`, and `TriggerZone`. <br/>`AnimationTrigger.cs` handles proximity detection and animation triggering.

**Note**: The scripts (C# source code) for the requirements are located in the following directory.
```
./Assets/Scripts/M3/GameStarter.cs
```
```
./Assets/Scripts/M3/GameRestarter.cs
```
```
./Assets/Scripts/Utility/GameQuitter.cs
```
```
./Assets/Scripts/M3/PauseMenuToggle.cs
```
```
./Assets/Scripts/M3/BallCollectable.cs
```
```
./Assets/Scripts/M3/BallCollector.cs
```
```
./Assets/Scripts/M3/AnimationTrigger.cs
```




<!-- done -->
## Extra Implementations
The project does include additional implementation giving pink foggy effect around the pink collectable ball for 2 seconds when it is triggered.
- **Pink Effect**: Particle System for the pink effect has been added with `PinkEffectController.cs`.

**Note**: The scripts (C# source code) for the extra implementations are located in the following directory.
```
./Assets/Scripts/M3/PinkEffectController.cs
```
## Known Issues or Bugs
There are no known bugs or incomplete features.
## Dependencies and External Assets
All the dependencies and external assets are resolved within the solution following the assignment tutorial format. 
- external asset, "**Post Processing**," has been added to the project solution.
<p align="right">(<a href="#readme-top">back to top</a>)</p><br /><br /><br />

















<!-- done -->
# Execution Instructions
The input code is listed below according to the assignment instructions.
- To run the game, open the Unity project and play the scene.
  ```
  ./Build/Windows/Song_JH_m3.exe
  ```
  ```
  ./Build/OSX/Song_JH_m3.app
  ```
- Press '**T**' to switch your control on each character in the scene.
- Use "**WASD**" or **arrow keys** to move the character chosen, dudes or minions. (Use thumbstick for analog movement of Gamepad control.)
- Use **number keys**, 1-9 (10%-90%) and 0 (100%), to adjust the max speed of your character.
- Use '**Q**' and '**E**' keys to turn left and right with adjusted turn rates to 50%.
- Walk or run around the scene as you want.
- Use '**Ctrl**' (Fire1) for button press animation near the red balloon (logical button).
- Use '**ESC**' (Escape) to toggle the in-game menu and pause/resume the game.

**Note**: The input code for control is located in the following directory.
```
./Assets/Scripts/CharacterControl/CharacterInputController.cs
```
<p align="right">(<a href="#readme-top">back to top</a>)</p><br /><br /><br />


















<!-- done -->
# Project Information
## License
This project is licensed under CS4455, Georgia Institute of Technology - see the [LICENSE.md](LICENSE.md) file for details.
## Contribution
Contributions are allowed - see the [CONTRIBUTING.md](CONTRIBUTING.md) file for details on how to get started.
## Acknowledgements
This project currently does not include an acknowledgements section as there were no contributions or funding sources to acknowledge at this stage.
<p align="right">(<a href="#readme-top">back to top</a>)</p><br /><br /><br />