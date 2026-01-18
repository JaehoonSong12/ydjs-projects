# Platformer Game Project

A Java-based Platformer game built with LWJGL (Lightweight Java Game Library).

This project is configured to support cross-platform development and distribution. It uses **Gradle** for building and **jpackage** (bundled with the JDK) to create standalone, portable applications for Windows, Linux, and macOS.

## Prerequisites

You have two options for building the project:

### Option A: Local Development (Native)
*   **Operating System**: Windows 10/11, macOS, or Linux.
*   **Java**: JDK 17 or higher.
    *   *Note: Gradle will attempt to download the correct toolchain automatically, but having JDK 17+ installed is recommended.*

### Option B: Docker Environment (Linux Build)
*   **Docker Desktop** (or Engine) installed and running.
*   **Git Bash** (if on Windows) or a standard terminal.
*   This method guarantees a clean Linux build environment without installing dependencies locally.

---

## Build Instructions

### 1. Creating a Standalone Distribution (Recommended)

This command creates a "portable" folder containing the game executable and a bundled, minimized Java Runtime Environment (JRE). The user *does not* need Java installed to run the result.

**Run the following command in your terminal:**

```bash
./gradlew clean jpackage
```

*   **Output Location**: `app/build/dist/Platformer/`
*   **Artifacts**:
    *   **Windows**: `Platformer.exe` (inside the folder)
    *   **Linux/Mac**: `Platformer` binary (inside the folder)

#### How it Works:
1.  **Detects OS**: The build script checks if you are on Windows, Mac, or Linux.
2.  **Selects Natives**: It downloads the correct LWJGL native libraries (`natives-windows`, `natives-macos`, etc.).
3.  **Shadow Jar**: Bundles all code and dependencies into `app/build/libs/app-all.jar`.
4.  **JPackage**: Uses the JDK's `jpackage` tool to wrap the Jar and a custom JRE into a standalone executable folder.

---

### 2. Running from Source (Development)

To quickly compile and run the game without packaging:

```bash
./gradlew run
```

---

## Docker Build Instructions (Cross-Compiling for Linux)

If you are on Windows/Mac but want to build a **Linux** distribution of the game, use the provided Docker setup.

1.  **Run the helper script**:
    ```bash
    ./run.sh
    ```
    *(On Windows, run this via Git Bash or WSL)*

2.  **Select Option 1** ("Run").
    *   This builds the Docker image (if missing) and drops you into an interactive shell inside the container.

3.  **Inside the container**:
    ```bash
    # Ensure line endings are correct (if mounted from Windows)
    dos2unix gradlew
    chmod +x gradlew

    # Build the Linux distribution
    ./gradlew clean jpackage
    ```

4.  **Retrieve Artifacts**:
    *   Exit the container.
    *   The Linux build will be available on your **host machine** at `app/build/dist/Platformer/`.

---

## Troubleshooting

### "Unable to delete directory" / File Locking (Windows)
If `clean` fails because a file is locked:
1.  Ensure the game is not running.
2.  Close any File Explorer windows opened to the `build` folder.
3.  The build script has a "Force Clean" mechanism, so simply re-running the command often works.

### "Command not found" (Docker)
If `./gradlew` fails inside Docker with "command not found":
1.  Run `dos2unix gradlew` to fix Windows line endings.
2.  Run `chmod +x gradlew` to make it executable.

## Project Structure

*   `app/src/main/java`: Source code
*   `app/src/main/resources`: Game assets (images, shaders, etc.)
*   `app/build.gradle.kts`: Main build script (documented).
*   `Dockerfile`: Linux build environment configuration.
*   `run.sh`: Helper script for Docker lifecycle management.
