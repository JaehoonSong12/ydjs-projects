# How to Distribute Your App Binary (.apk) with Android Studio

## 1. Prepare Your App for Release

- **Update app version:**  
  Edit `versionCode` and `versionName` in `app/build.gradle.kts` to reflect your release version.

- **Remove debug code:**  
  Make sure no debug logs or test code are present in your release build.

- **Check permissions:**  
  Review `AndroidManifest.xml` for only necessary permissions.

## 2. Build a Signed APK

1. **Open your project in Android Studio.**

2. **Go to the menu:**  
   `Build` → `Generate Signed Bundle / APK...`

3. **Choose APK:**  
   Select `APK` and click `Next`.

4. **Create or select a keystore:**

  > **What is a keystore?**  
  > A keystore is a secure file (`.jks`) that stores cryptographic keys used to sign your app. Signing your APK proves its authenticity and integrity to Android devices and app stores.
  >
  > **How does it work?**  
  > When you sign your app, Android Studio uses the private key from your keystore to generate a digital signature. Devices and app stores use the corresponding public key to verify that the APK hasn’t been tampered with and that it comes from you.
  >
  > **Why is it important?**  
  > - Required for publishing on Google Play and installing on most devices.  
  > - Ensures only you can update your app (you must use the same keystore for all future updates).  
  > - Losing your keystore means you cannot update your app in the future.

  - If you don’t have a keystore, click `Create new...` and fill in the details (save the `.jks` file and passwords securely!).
  - If you have one, browse and select it, then enter the password, alias, and key password.

5. **Select build variant:**  
   Choose `release` (not `debug`).

6. **Signature versions:**  
   Check both `V1 (Jar Signature)` and `V2 (Full APK Signature)` for best compatibility.

7. **Finish:**  
   Click `Finish` and wait for the build to complete.

## 3. Locate the APK

- After the build, Android Studio will show a notification with a link to the APK location.
- By default, it’s in:  
  `app/build/outputs/apk/release/app-release.apk`

## 4. Distribute the APK

- **Direct sharing:**  
  You can now send this APK file directly to users (email, website, etc.).
- **Install on device:**  
  Users must enable “Install from unknown sources” on their Android device to install the APK.
- **Google Play:**  
  If you want to publish on Google Play, use the “Generate Signed Bundle / APK...” process but select “Android App Bundle” instead of APK, and follow the Play Console upload instructions.

---

# iOS Binary Distribution (for Reference)

> **Note:** Your current project is Android/Kotlin. iOS distribution requires a separate Xcode/Swift project or a cross-platform framework (like Flutter or React Native).  
> If you want to distribute an iOS app, you must:
>
> 1. **Develop your app in Xcode** (or use a cross-platform tool).
> 2. **Archive the app:**  
>    In Xcode, go to `Product` → `Archive`.
> 3. **Export for Ad Hoc or App Store:**  
>    Use the Organizer window to export your `.ipa` file for Ad Hoc (limited devices) or App Store distribution.
> 4. **Provisioning profiles and certificates:**  
>    You need an Apple Developer account and proper provisioning profiles.
>
> For more details, see Apple’s official documentation:  
> https://developer.apple.com/documentation/xcode/distributing-your-app-for-beta-testing-and-releases

---

# Summary

- Use Android Studio’s “Generate Signed APK” wizard for Android distribution.
- Share the resulting APK file directly or upload to Google Play.
- iOS distribution requires a different toolchain (Xcode) and process. 