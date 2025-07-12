# iOS Binary Distribution with Kotlin Multiplatform (KMP)

> **Note:** While traditional iOS app distribution requires Xcode on macOS, Kotlin Multiplatform (KMP) allows you to share code between Android and iOS. However, generating a distributable iOS binary (`.ipa`) still has limitations on Windows.

## What You Can Do with KMP on Windows

- **Develop shared logic:**  
  Use Kotlin Multiplatform to write and test shared code for Android and iOS in Android Studio on Windows.
- **Build iOS frameworks:**  
  You can generate iOS frameworks (`.framework` files) from Windows using Gradle tasks, which can later be integrated into an Xcode project.

## What You *Cannot* Do on Windows

- **No direct `.ipa` generation:**  
  You cannot build or sign a complete iOS app binary (`.ipa`) for distribution from Windows. Apple’s toolchain (Xcode, codesign, etc.) is required for final packaging and signing, and these tools only run on macOS.

## Typical Workflow

1. **Develop shared code in Android Studio with KMP.**
2. **Generate iOS framework:**  
   Run Gradle tasks (e.g., `assembleXCFramework`) to produce a framework for iOS.
3. **Transfer to macOS:**  
   Move the generated framework to a Mac.
4. **Integrate with Xcode:**  
   Use Xcode to create the iOS app, link the framework, and archive/export the `.ipa` for distribution.

## Alternatives

- **Cloud macOS services:**  
  Use services like MacStadium, MacInCloud, or GitHub Actions with macOS runners to automate iOS builds and distribution remotely.
- **Cross-platform frameworks:**  
  Consider Flutter or React Native, which offer some tooling for iOS builds, but final signing and distribution still require macOS.

> **Summary:**  
> You can develop and test shared code for iOS on Windows with Kotlin Multiplatform, but you still need access to a Mac to generate and distribute an iOS app binary.

---

## Part 1: Generating an iOS `.framework` (XCFramework) in Android Studio on Windows

1. **Add an iOS target in your `shared` module’s `build.gradle.kts`**  
   Make sure you have a Kotlin Multiplatform module (often named `shared`). In its Gradle script, you need an iOS target and a bit of configuration to produce an XCFramework:
   ```kotlin
   kotlin {
     iosX64()    // Simulator
     iosArm64()  // Device
     iosSimulatorArm64() // Apple Silicon simulator

     sourceSets {
       val commonMain by getting {
         dependencies {
           // your shared-deps…
         }
       }
       // … other sourceSets if needed
     }

     // Tell Gradle to bundle both simulator + device builds into an XCFramework:
     val xcf = XCFramework()
     iosX64().binaries.framework {
       baseName = "SharedLib"
       xcf.add(this)
     }
     iosArm64().binaries.framework {
       baseName = "SharedLib"
       xcf.add(this)
     }
     iosSimulatorArm64().binaries.framework {
       baseName = "SharedLib"
       xcf.add(this)
     }
     tasks.register("assembleSharedXCFramework") {
       dependsOn(xcf.linkTasks)
     }
   }
   ```

2. **Sync and build**  
   - In Android Studio, click **Sync Project with Gradle Files** (the elephant icon).  
   - Open the **Gradle** tool window (​🗂️ on the right), expand **:shared > Tasks > other**, and double‑click **assembleSharedXCFramework**.  
   - After it runs successfully, you’ll find the XCFramework in:  
     ```
     shared/build/XCFrameworks/debug/SharedLib.xcframework
     ```
   - If you want a Release build, run `assembleSharedXCFramework` with the `-Pkonan.build.type=Release` flag, or adjust your Gradle task accordingly.

3. **Locate your framework bundle**  
   The `.xcframework` folder contains all slices (device + simulator). You can also produce a single‑slice `.framework` by only adding one target’s binary, but XCFramework is recommended for universal support.

---

## Part 2: Importing the Framework into Xcode and Exporting an `.ipa`

1. **Transfer the XCFramework to your Mac**  
   Copy `SharedLib.xcframework` (the entire folder) into your iOS project directory—e.g. alongside your `.xcodeproj` or in a `Frameworks/` folder.

2. **Open or create your Xcode project**  
   - Launch Xcode (≥12).  
   - Open your existing `.xcodeproj` or `.xcworkspace`.

3. **Add the XCFramework to your project**  
   - In the Project Navigator, right‑click your project and choose **Add Files to “YourApp”…**  
   - Select `SharedLib.xcframework`.  
   - Ensure **Copy items if needed** is checked, and **Add to targets** includes your app target.

4. **Link and embed**  
   - Select your app target, then the **General** tab.  
   - Under **Frameworks, Libraries, and Embedded Content**, click **+**, choose `SharedLib.xcframework`, and set **Embed & Sign**.

5. **Import and use in Swift/Obj‑C**  
   ```swift
   // Swift
   import SharedLib

   // then call your API:
   let result = SharedClass().sharedFunction()
   ```
   If you use Objective‑C, add the generated header path to your **Build Settings > Header Search Paths**, then `#import <SharedLib/SharedLib.h>`.

6. **Configure signing & capabilities**  
   - In your app target’s **Signing & Capabilities**, set your Team, Bundle Identifier, and provisioning profiles.

7. **Archive your app**  
   - From the top menu, choose **Product > Scheme > Edit Scheme…**, select **Archive** on the left, and verify **Build Configuration** is “Release.”  
   - Close the scheme editor, then choose **Product > Archive**.  
   - Xcode will build and open the **Organizer** showing your new archive.

8. **Export the `.ipa`**  
   - In **Organizer**, select your archive and click **Distribute App**.  
   - Choose the distribution method (App Store Connect, Ad Hoc, Enterprise, Development).  
   - Follow the dialogs: Xcode will re‑sign if needed, package the `.ipa`, and let you choose an export folder.  
   - At the end, you’ll have `YourApp.ipa` ready for upload or side‑loading.

---

🎉 That’s it! On Windows you produce the XCFramework; on macOS/Xcode you embed, sign, archive, and export the `.ipa`.
