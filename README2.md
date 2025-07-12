<!-- 
 @requires
 1. VSCode extension: null
 2. Shortcut: 'Ctrl' + 'Shift' + 'V'
 3. Split: Drag to right (->)
 -->
<!-- Anchor Tag (Object) for "back to top" -->
<a id="readme-top"></a>
















<!-- done? vvvvvvvvvvvvv -->
# Introduction
## Author
- **Full Name (OIT/Canvas Account Name):** Jaehoon Song
- **Email:** jsong421@gatech.edu
## Main Scene
The main scene of the project is the following.
```
./Assets/Scenes/demo_m4.unity
```
<p align="right">(<a href="#readme-top">back to top</a>)</p><br /><br /><br />

















<!-- done? vvvvvvvvvvv -->
# Project Requirements 
Here are the modifications checklist (requirements) according to the assignment instructions. The topic is "**AI-Controlled Minion Navigation**," aiming to enhance gameplay by implementing an AI-controlled minion using Unity’s *NavMeshAgent* and a basic state machine. The AI minion will navigate a loop of waypoints, utilizing the *NavMeshAgent* for precise steering behaviors. Additionally, you'll implement position prediction to enable the minion to intercept a specific waypoint. The task involves modifying the minion to be AI-controlled, setting up navigation with the *NavMeshAgent*, creating a state machine to manage the minion’s actions, configuring waypoint navigation, and incorporating position prediction for interception. By completing these steps, you will achieve an AI-driven minion capable of advanced pathfinding and decision-making in Unity.

<!-- done? vvvvvvvvvvvvvv -->
1. **Waypoint Navigation**: to implement AI navigation for a Minion using Unity’s NavMeshAgent
   - **Stationary Waypoints**: The AI-controlled Minion must visit *5 stationary waypoints*. These waypoints should be visible and spread across the scene. Waypoints should be made from primitive GameObjects (e.g., colored spheres or cubes) with colliders removed/disabled but meshRenderers enabled.
2. **Moving Waypoint Navigation with Prediction**
   - **Moving Waypoint**: The Minion must intercept a sixth *moving waypoint*. This waypoint should be animated to move back and forth between two points using Mecanim animation.
   - **Position Prediction**: Implement position prediction to intercept the moving waypoint. (This prediction should update every frame.) Visualize the Minion’s predicted position with a *destination tracker object*. (Ensure the Minion reaches the actual moving waypoint, not just the predicted position.)
3. **Procedural State Machine**: to control waypoint navigation
   - Implement a state machine with at least 2 states to control the Minion’s behavior: (**1**) Visit the 5 stationary waypoints, (**2**) Head towards the moving waypoint. *Repeat the pattern indefinitely*.
4. **Minion Animation**
   - The Minion must be properly animated with steps, ensuring it does not sink into the ground.










<!-- done? vvvvvvvvvvvvvvv -->
## Build Observations
The followings are the observations expected by default (or given project) and by my modifications.
- Upon running the build, the player can notice Waypoint navigation system observed with AI Minions following five visible waypoints and one moving waypoint in the scene after NavMesh baking (**Script created**, _Unity modified: NavMeshAgent, Navigation > Bake_).
- Moving waypoint with prediction is observed where a moving waypoint is animated and a velocity reporter script tracks its physical touch (**Script created**, _Unity modified_).
- With the minion properly animated, the player will never detect it sinking into the ground (**Script modified**, _Unity modified: NavMeshAgent_).



<!-- done? vvvvvvvvvvvvvvvv -->
## Code Changes
The followings are the code changes highlighted for implementation of the modifications.
1. **Waypoint Navigation**: Check the code in `MinionAI.cs` and the setup in _Unity_ for the `NavMeshAgent` component and the baked NavMesh. <br/>`Navigation > Bake`
2. **Moving Waypoint Navigation with Prediction**: Check the code in `VelocityReporter.cs` and the setup in _Unity_ for the animated moving waypoint.
3. **Procedural State Machine**: Check the modifications in `MinionAI.cs` for the state machine logic handling patrol and tracking states.
4. **Minion Animation**: Check the modifications in `MinionAI.cs` and the setup in _Unity_ for the `NavMeshAgent` component with the base offset and animator forward parameter updates. <br/>`NavMeshAgent > Base Offset`

**Note**: The scripts (C# source code) for the requirements are located in the following directory.
```
./Assets/Scripts/M4/MinionAI.cs
```
```
./Assets/Scripts/M4/VelocityReporter.cs
```




<!-- done? vvvvvvvvvvvvvvvvv -->
## Extra Implementations
The project does not include additional implementations since the assignment has a tutorial style format.
## Known Issues or Bugs
There are no known bugs or incomplete features.
## Dependencies and External Assets
All the dependencies and external assets are resolved within the solution following the assignment tutorial format. 
- external asset, "**NavMesh** for path finding," has been manually added to the project solution, `./Assets/NavMeshComponents`. (Reference: https://github.com/Unity-Technologies/NavMeshComponents.git)
<p align="right">(<a href="#readme-top">back to top</a>)</p><br /><br /><br />

















<!-- done? vvvvvvvvvvvvvvvvvvvv -->
# Execution Instructions
The input code is listed below according to the assignment instructions.
- To run the game, open the Unity project and play the scene.
  ```
  ./Build/Windows/Song_JH_m4.exe
  ```
  ```
  ./Build/OSX/Song_JH_m4.app
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


















<!-- done? vvvvvvvvvvvv -->
# Project Information
## License
This project is licensed under CS4455, Georgia Institute of Technology - see the [LICENSE.md](LICENSE.md) file for details.
## Contribution
Contributions are allowed - see the [CONTRIBUTING.md](CONTRIBUTING.md) file for details on how to get started.
## Acknowledgements
This project currently does not include an acknowledgements section as there were no contributions or funding sources to acknowledge at this stage.
<p align="right">(<a href="#readme-top">back to top</a>)</p><br /><br /><br />