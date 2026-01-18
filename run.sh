#!/bin/bash
set -euo pipefail # [Safety] Exit immediately if any command fails (e), variable is undefined (u), or pipe fails (o).

# ==============================================================================
# Docker Environment Helper Script
# ==============================================================================
#
# PURPOSE:
# Automates the setup, cleanup, and execution of the Dockerized build environment.
# It ensures that users on Linux, Mac, or Windows (via Git Bash/WSL) can 
# spin up the build container with a single command.
# ==============================================================================

# [Host OS Detection]
# WHY: We need to mount the current directory ($PWD) into the container so the 
# container can access the source code and write build artifacts back to the host.
#
# DIFFERENTIATION:
# - Linux/Mac: 'pwd' works as expected.
# - Windows (Git Bash/MinGW): 'pwd' returns a path like '/c/Users/...', which Docker 
#   doesn't understand. We need 'pwd -W' to get the Windows path 'C:\Users\...'
case "$(uname -s)" in
    Linux*)                 ROOT=$(pwd);;
    Darwin*)                ROOT=$(pwd);;
    CYGWIN*|MINGW*|MSYS*)   ROOT=$(pwd -W);;
    *)                      echo "Unknown OS. Exiting." && exit 1;;
esac

# Define the image name tag
IMAGE_NAME="platformer-dev"

# ------------------------------------------------------------------
# Function: clean_image
# Purpose:  Removes any containers or images related to this project.
#           Useful for forcing a rebuild of the environment (e.g., after changing Dockerfile).
# ------------------------------------------------------------------
clean_image() {
    local containers
    # Find all container IDs running or stopped that use our image
    containers=$(docker ps -aq --filter "ancestor=$IMAGE_NAME")
    
    if [[ -n "$containers" ]]; then                                         
        echo "Found containers using '$IMAGE_NAME'. Forcing removal..."
        docker rm -f $containers
    fi
    
    # Check if the image exists locally
    if [[ "$(docker images -q "$IMAGE_NAME" 2> /dev/null)" != "" ]]; then
        # Remove the image
        docker rmi $(docker images -q "$IMAGE_NAME")
        echo "Removed existing Docker image '$IMAGE_NAME'."
    else
        echo "No existing Docker image '$IMAGE_NAME' found."
    fi
}

# ------------------------------------------------------------------
# Function: build_and_run
# Purpose:  Ensures the image exists, then launches the interactive container.
# ------------------------------------------------------------------
build_and_run() {
    echo "Preparing to run..."

    # [Idempotency] Build the Docker image ONLY if it doesn't exist.
    if [[ "$(docker images -q "$IMAGE_NAME" 2> /dev/null)" == "" ]]; then
        echo "Image '$IMAGE_NAME' not found. Building..."
        docker build -t "$IMAGE_NAME" .
    else
        echo "Image '$IMAGE_NAME' found. Skipping build."
    fi

    echo "Starting container..."
    echo "--------------------------------------------------------"
    echo "  Welcome to the Platformer Build Environment!"
    echo "--------------------------------------------------------"
    echo "  Essential Commands (inside the container):"
    echo "  1. Fix line endings:  dos2unix gradlew"
    echo "  2. Make executable:   chmod +x gradlew"
    echo "  3. Build Distro:      ./gradlew clean jpackage"
    echo "  4. Run Headless:      ./gradlew run"
    echo ""
    echo "  NOTE: Build artifacts will appear on your HOST machine in:"
    echo "        app/build/dist/Platformer/"
    echo "--------------------------------------------------------"
    
    # [Docker Run Command Breakdown]
    # docker run
    #   --rm            : Automatically remove the container when it exits (keeps things clean).
    #   -it             : Interactive mode with a TTY (lets you type commands).
    #   -v "$ROOT:/app" : Volume Mount. Maps the HOST project folder ($ROOT) to /app inside container.
    #                     This allows changes in the container (build output) to persist on the host.
    docker run --rm -it -v "$ROOT:/app" "$IMAGE_NAME"
}

# ------------------------------------------------------------------
# Main Menu
# ------------------------------------------------------------------
echo "Platformer Game - Docker Environment"
echo "1. Run (Build if missing) - Recommended for daily use"
echo "2. Clean (Remove image) & Run - Use if you changed Dockerfile"
echo "3. Clean only & Exit"
read -p "Enter choice [1-3]: " choice

case "$choice" in
    1) build_and_run;;
    2) clean_image && build_and_run;;
    3) clean_image && exit 0;;
    *) echo "Invalid choice. Exiting." && exit 1;;
esac
