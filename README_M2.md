<!-- Improved compatibility of back to top link -->
<a name="readme-top"></a>
<!------------------------------------------------>








# Introduction
## Author
- **Full Name (OIT/Canvas Account Name):** Jaehoon Song
- **Email:** jsong421@gatech.edu
## Main Scene
The main scene of the project is the following.
```
./Assets/Scenes/demo_m2.unity
```
<p align="right">(<a href="#readme-top">back to top</a>)</p><br /><br /><br />


















# Project Requirements 
Here are the modifications check list (requirements) according to the assignment instructions. The topic is "**Physics Simulation**," aiming creation of several physics objects and exploration of some programmatic control of the physics simulation.
1. The player's name appears in the HUD.
2. A vertical stack of three blue rigidbody spheres with collision sounds is present.
3. A vertical stack of three red rigidbody spheres that do not collide with one another is present.
4. The Asset Store model "Free Japanese Mask" includes a custom compound collider that tips over on its nose, similar to the provided video. (A mesh collider should not be used for this task.)
5. A yellow jointed chain made of at least five rigidbody GameObjects is included.
6. A blue kinematic rigidbody elevator using Mecanim animation carries a red rigidbody sphere.
7. A green Weeble Wobble/Punching Bozo tilts but cannot be knocked over.
8. A purple cube with a rigidbody box that does not slide down the ramp is present.
9. A green cube with a rigidbody box that slides down the ramp is included.
10. An orange rigidbody sphere that bounces is present.
11. A Y_Bot ragdoll collapses over the hurdle GameObject (it is acceptable for the ragdoll to fall off).
12. A black click beetle or jumping bean that jumps intermittently and autonomously (not controlled by the player) is included. There is a variable/random amount of time between jumps and variable/random force magnitude and direction. Jumps occur only when grounded.
13. A pause script starts the game paused and unpauses with a "p" keypress.
## Build Observations
The followings are the observations expected by default (or given project) and by my modifications.
- Upon running the build, the player can observe vertical stack of three blue rigidbody spheres with collision sounds (**Script created**, _Unity for reference_).
- With the vertical stack of three red rigidbody spheres, the player can observe that red spheres don't collide with one another (_Unity modified, Layer Collision Matrix_).
- Asset Store model "Free Japanese Mask" will be observed (_Unity modified_).
- Yellow jointed chain made of six rigidbody Chain links will be observed (_Unity modified, Fixed and Configurable Joint_).
- Blue kinematic rigidbody elevator using Mechanism animation with red rigidbody sphere going for a ride will be observed (_Unity modified, Animation Physics_).
- Green Weeble Wobble/Punching Bozo that tilts but not being knocked over has been added (**Script applied**).
- A purple cube with rigidbody box that does not slide down the ramp has been added (_Unity modified_).
- Green cube with rigidbody box that does slide down the ramp has been added (_Unity modified, physics material_).
- Orange rigidbody sphere that bounces has been added (_Unity modified, physics material_).
- Y_Bot ragdoll done all the bones assignment has been added (_Unity modified, Ragdoll_).
- Black jumping bean that jumps autonomously with randomized jump intervals, directions, magnitudes, and ground detection for realistic jumping has been added (**Script modified**).
## Code Changes
The followings are the code changes highlighted for implementation of the modifications.
1. **HUD modification**: Check the setup in _Unity_.
2. **Collision Events**: Check the code in `BallCollisionReporter.cs` to observe collision events for blue rigidbody spheres.
3. **Physics Layers**: Check the settings in _Unity_ to observe the layer collision settings for red rigidbody spheres. <br/>`Edit > Project Settings > Physics > Layer Collision Matrix`
4. **Compound Collider**: Check the setup in _Unity_ for the compound collider of the "Free Japanese Mask" model.
5. **Joint Constraints**: Check the setup in _Unity_ for the yellow jointed chain. <br/>`Component > Fixed and Configurable Joint`
6. **Kinematic Elevator**: Check the setup in _Unity_ for the blue kinematic elevator. (Update mode was set to "animate physics" for synchronization between animations and the physics system)  <br/>`Rigidbody > isKinematic` and `Animation > Transform > Position`
7. **Center of Mass**: Check the code in `RigidbodyCenterOfMass.cs` to observe the center of mass customization for the green Weeble Wobble/Punching Bozo.
8. **Default Friction**: Check the setup in _Unity_ for the purple cube with rigidbody box.
9. **Low Friction**: Check the physics material in `./Assets/physics_materials/Slippy.physicMaterial` for the green cube with low friction.
10. **Bouncy Rigidbody**: Check the physics material in `./Assets/physics_materials/Bouncy.physicMaterial` for the orange bouncy rigidbody sphere.
11. **Ragdoll**: Check the setup in _Unity_ for the Y_Bot ragdoll. <br/>`GameObject > 3D Object > Ragdoll`
12. **Scripting Forces**: Check the code in `JumpingBean.cs` to observe the autonomous jumping behavior of the black jumping bean.
13. **Pause Script**: Check the code in `PauseGame.cs` to observe the pause functionality with the `p` keypress.

**Note**: The scripts (C# source code) for the requirements are located in the following directory.
```
./Assets/Scripts/M2/BallCollisionReporter.cs
```
```
./Assets/Scripts/M2/JumpingBean.cs
```
```
./Assets/Scripts/M2/PauseGame.cs
```
```
./Assets/Scripts/Utility/RigidbodyCenterOfMass.cs
```
## Extra Implementations
The project does not include additional implementations since the assignment has a tutorial style format.
## Known Issues or Bugs
There are no known bugs or incomplete features.
## Dependencies and External Assets
All the dependencies and external assets are resolved within the solution following the assignment tutorial format. 
- external asset, "**Japanese Mask**," has been added to the project solution.
<p align="right">(<a href="#readme-top">back to top</a>)</p><br /><br /><br />


















# Execution Instructions
The input code is listed below according to the assignment instructions.
- To run the game, open the Unity project and play the scene.
  ```
  ./Build/Windows/Song_JH_m2.exe
  ```
  ```
  ./Build/OSX/Song_JH_m2.app
  ```
- Press '**T**' to switch your control on each character in the scene.
- Use "**WASD**" or **arrow keys** to move the character chosen, dudes or minions. (Use thumbstick for analog movement of Gamepad control.)
- Use **number keys**, 1-9 (10%-90%) and 0 (100%), to adjust the max speed of your character.
- Use '**Q**' and '**E**' keys to turn left and right with adjusted turn rates to 50%.
- Walk or run around the scene as you want.
- Use '**Ctrl**' (Fire1) for button press animation near the red balloon (logical button).
- Use '**P**' to pause and unpause the game.

**Note**: The input code for control is located in the following directory.
```
./Assets/Scripts/CharacterControl/CharacterInputController.cs
```
<p align="right">(<a href="#readme-top">back to top</a>)</p><br /><br /><br />



















# Project Information
## License
This project is licensed under CS4455, Georgia Institute of Technology - see the [LICENSE.md](LICENSE.md) file for details.
## Contribution
Contributions are allowed - see the [CONTRIBUTING.md](CONTRIBUTING.md) file for details on how to get started.
## Acknowledgements
This project currently does not include an acknowledgements section as there were no contributions or funding sources to acknowledge at this stage.
<p align="right">(<a href="#readme-top">back to top</a>)</p><br /><br /><br />