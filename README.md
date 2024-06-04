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

**Note**: The scripts (C# source code) for the requirements are located in the following directory. `BasicControlScript.cs` is a script for `SomeDude_NoRootMotion` and `RootMotionControlScript.cs` is a script for `SomeDude_RootMotion`.
```
./Assets/Scripts/CharacterControl/BasicControlScript.cs
```
```
./Assets/Scripts/CharacterControl/RootMotionControlScript.cs
```
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

**Note**: The input code for control is located in the following directory.
```
./Assets/Scripts/CharacterControl/CharacterInputController.cs
```
## Dependencies and External Assets
No dependencies and external assets are specified since the assignment has a tutorial style format.





# License
This project is licensed under CS4455, Georgia Institute of Technology - see the [LICENSE.md](LICENSE.md) file for details.
