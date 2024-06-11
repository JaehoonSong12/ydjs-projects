# Introduction
## Author
- **Full Name (OIT/Canvas Account Name):** Jaehoon Song
- **Email:** jsong421@gatech.edu
## Main Scene
The main scene of the project is the following.
```
./Assets/Scenes/demo_m2.unity
```





# Requirements 
Here are the modifications check list (requirements) according to the assignment instructions. The topic is "Physics Simulation," aiming creation of several physics objects and exploration of some programmatic control of the physics simulation.

1. `SomeDude_NoRootMotion` – disable turn animation by NOT passing turn input parameter to the Animator in the script (only turning programmatically and the character should still be walking during the turn)
2. `SomeDude_RootMotion` – add running animations (forward run AND turning runs) to forward blendtree and move the blend ratios around as appropriate so that the player can easily control the character. The character should continue to run while turning at full speed.
3. `SomeDude_RootMotion` – add public scalars that allow adjustment of animation speed and root motion scale (translation and rotation). Adjust the scalars slightly faster than default until you are happy with control speed and overall animation quality. The goal is for the character to have the capabilities of a super hero, but still look reasonable natural.
4. `Minion_RootMotion` – Replace/modify the forward and turn animations to include some comical hopping steps.
5. `Minion_RootMotion` – Add animation events that generate minion squeaky footstep events to your forward animation.
6. `SomeDude_RootMotion` – Match Target and Inverse Kinematics addition for button press animation.
## Build Observations
The followings are the observations expected by default (or given project) and by my modifications.
- Upon running the build, the player can observe disabled turn animation for the first dude (**Script modified**).
- With the second dude, forward run, forward left run, and forward right run animations are added (_Unity modified, Animator_). Also, a speed ratio of 60% was set for the running animation speed (**Script modified**) and match target with inverse kinematics was added for button press animation (**Script modified**, _Unity for reference_).
- With the second minion, the forward, forward left turn, and forward right turn animations were modified to include some comical hopping steps (_Unity modified, Animation Key_). Also, animation events (sound effects) generating minion squeaky footstep sound to the forward animation was added (_Unity modified, Animation Event_).
## Code Changes
The followings are the code changes highlighted for implementation of the modifications. 
1. **Turn Animation**: Check the code in `BasicControlScript.cs, line 101` to disable turn animation for `SomeDude_NoRootMotion`.
2. **Running Animations (Running)**: Check the initial setup in _Unity, Animator_ to add running animation for `SomeDude_RootMotion`.
3. **Running Animations (Speed)**: Check the code in `RootMotionControlScript.cs, line 88, 104, 261` to set the speed ratio of 60% for `SomeDude_RootMotion`.
4. **Minion Animation (Running)**: Check the initial setup in _Unity, Animation Key_ to modify running animation (hopping steps) for `Minion_RootMotion`.
5. **Minion Animation (Sound Effect)**: Check the initial setup in _Unity, Animation Event_ to add animation events that generate minion squeaky footstep (sound) events to the forward animation for `Minion_RootMotion`.
6. **Match Target and Inverse Kinematics**: Check the code in `RootMotionControlScript.cs, line 154, 211` to add match target and inverse kinematics for  for `SomeDude_RootMotion`.


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
## Known Issues (Bugs)
There are no known bugs or incomplete features.





# Instructions
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
## Dependencies and External Assets
No dependencies and external assets are specified since the assignment has a tutorial style format.





# License
This project is licensed under CS4455, Georgia Institute of Technology - see the [LICENSE.md](LICENSE.md) file for details.





























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
