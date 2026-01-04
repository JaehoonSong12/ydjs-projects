# ==============================================================================
# Dockerfile for Platformer Game Build Environment
# ==============================================================================
#
# PURPOSE:
# Provide a consistent, isolated environment for building the Java project
# and creating distribution artifacts (using jpackage) on Linux.
#
# USAGE:
# Typically run via the helper script: ./run.sh
# ==============================================================================

# [Base Image] Eclipse Temurin JDK 21 (LTS) on Ubuntu Jammy (22.04)
# WHY: 
# 1. 'eclipse-temurin' is a production-ready, open-source OpenJDK build.
# 2. JDK 21 is the current Long Term Support (LTS) version. 
#    While our project targets Java 17, using a newer JDK for the build environment 
#    ensures we have the latest tools (like jpackage) and security patches.
# 3. 'ubuntu jammy' provides a stable glibc and package repository for dependencies.
FROM eclipse-temurin:21-jdk-jammy

# [Dependencies] Install system utilities and libraries
#
# WHAT & WHY:
# 1. apt-get update: Refreshes package lists to ensure we get the latest versions.
#
# 2. dos2unix: 
#    - Fixes "CRLF" (Windows) vs "LF" (Linux) line ending issues.
#    - Crucial because if you edit 'gradlew' on Windows and mount it here, 
#      Bash will fail with "command not found" errors due to the hidden \r character.
#
# 3. binutils:
#    - Provides binary manipulation tools like 'objcopy' and 'strip'.
#    - REQUIRED by 'jpackage' on Linux to extract and minimize the JRE.
#
# 4. fakeroot:
#    - Simulates root privileges for file manipulation.
#    - Often required by packaging tools (.deb/.rpm) if we chose to build installers.
#
# 5. LWJGL Runtime Dependencies (libgl1-mesa-glx, libxrandr2, etc.):
#    - These are X11 and OpenGL libraries.
#    - Even if we only build the artifact, sometimes the test phase of the build 
#      attempts to load the LWJGL classes, which triggers a check for these shared libs.
#    - If you try to run the game 'headless' inside Docker, these are strictly necessary.
RUN apt-get update \
 && apt-get install -y \
    dos2unix \
    binutils \
    fakeroot \
    libgl1-mesa-glx \
    libxrandr2 \
    libxxf86vm1 \
    libxi6 \
 && rm -rf /var/lib/apt/lists/*

# [Workspace] Set the working directory inside the container
# All subsequent commands (and the default interactive shell) will start here.
WORKDIR /app

# [Ports] Expose port 25565
# This is the default Minecraft port, kept here if you add networking features later.
# It documents that this container *can* act as a server.
EXPOSE 25565

# [Entrypoint] Default command
# Starts an interactive Bash shell so the user can run Gradle commands manually.
CMD ["bash"]