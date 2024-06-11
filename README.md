<!-- Improved compatibility of back to top link -->
<a name="readme-top"></a>

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
## Contributing
Explain how others can contribute to your project. Include guidelines for submitting issues and pull requests.
1. Fork the repository
2. Create a new branch 
```
git checkout -b feature-branch
```
3. Commit your changes 
```
git commit -am 'Add new feature'
```
4. Push to the branch 
```
git push origin feature-branch
```
5. Create a new Pull Request
## Acknowledgements
This project currently does not include an acknowledgements section as there were no contributions or funding sources to acknowledge at this stage.



<p align="right">(<a href="#readme-top">back to top</a>)</p><br /><br /><br />
























<p align="right">(<a href="#readme-top">back to top</a>)</p><br /><br /><br /><p align="right">(<a href="#readme-top">back to top</a>)</p><br /><br /><br /><p align="right">(<a href="#readme-top">back to top</a>)</p><br /><br /><br />



# Interactive Animation in Unity


## Milestones

### [#1] Turn Animation (Script modified)
- Disabled turn animation for `SomeDude_NoRootMotion`.

### [#2] Running Animations (Unity modified, Animator)
- Added run, left run, and right run animations for `SomeDude_RootMotion`.

### [#3] Running Animations (Script modified)
- Maintained a ratio of 60% for the running animations for `SomeDude_RootMotion`.
  - For example, in a 2D free directional aspect, if the forward run animation is at (0,1), then the maximum speed of the walk forward will be at (0, 0.6).
- Added public scalars to control the animation speeds in `SomeDude_RootMotion`.

### [#4] Minion Animation (Unity modified, Animation Key)
- Modified the forward, forward left turn, and forward right turn animations of `Minion_RootMotion` to include some comical hopping steps.

### [#5] Minion Animation (Unity modified, Animation Event)
- Added animation events that generate minion squeaky footstep (sound) events to the forward animation of `Minion_RootMotion`.

### [#6] Match Target and Inverse Kinematics (Script modified, Unity for game object reference)
- Added match target and inverse kinematics for button press animation in `SomeDude_RootMotion`.

## Installation and Setup

1. Clone the repository.
2. Open the project in Unity.
3. Navigate to `./Assets/Scripts/CharacterControl/CharacterInputController` to review and modify the input code.
4. Ensure that all necessary animations and scripts are properly linked in the Unity Editor.

## Contribution

Feel free to fork the repository, make modifications, and submit pull requests. For any issues or questions, open an issue in the repository.

## License

This project is licensed under the MIT License. See the LICENSE file for details.

## Acknowledgements

Thanks to all contributors and the Unity community for their support and resources.



## Code Changes

The followings are the code changes highlighted for implementation of the modifications:

- **Restart Functionality:** Check the `Update()` method in `./Assets/Objects/Controllers/Player.cs` at line 45 for the implementation of restarting the game.
- **Game Over Conditions:** Look for the `isPaused` variable and collision with pickup items in `./Assets/Objects/Controllers/Player.cs` at lines 60 and 120 to understand game over conditions.
- **Obstacles Object Definition:** Check the `./Assets/Objects/Obstacle.prefab` to see the object settings such as `isTrigger` being `false` at line 15.
- **Obstacles Player Blocking:** Check the `OnCollisionEnter()` method in `./Assets/Objects/Controllers/Player.cs` at line 85 for defining blocking actions by creating non-trigger objects logic.
- **Game Over Message:** Look for the `if (count == 7)` statement in `./Assets/Objects/Controllers/Player.cs` at line 110 to see how the text object is controlled while playing the game.
- **Player Movement Restriction:** Refer to the if-statement from the movement handling method (`FixedUpdate()`) in `./Assets/Objects/Controllers/Player.cs` at line 95 for player movement not controlled after game over.











# --------------- example

## Contributing

We welcome contributions! Please see our CONTRIBUTING.md for details on how to get started.

### Reporting Issues

If you encounter any issues, please report them here: https://github.com/your-repo/issues

### Submitting Pull Requests

1. Fork the repository.
2. Create a new branch (git checkout -b feature-branch).
3. Commit your changes (git commit -am 'Add new feature').
4. Push to the branch (git push origin feature-branch).
5. Create a new Pull Request.

Please ensure your pull request adheres to the following guidelines:
- Description of the change and its purpose.
- Proper documentation and tests for the new feature.
- Adherence to the project's coding style.

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Acknowledgements

- Thanks to Name (https://example.com) for their invaluable help with specific aspect.
- This project uses icons from Font Awesome (https://fontawesome.com).
- Inspiration and guidance were taken from the XYZ Project (https://github.com/example/xyz).
