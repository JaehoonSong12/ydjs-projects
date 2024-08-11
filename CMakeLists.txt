# CMakeList.txt : CMake project for bash_compiler, include source and define
# project specific logic here.
#










# Define project-wide variables for easy configuration and maintainability.
# Project Variables:
# - PROJECT_NAME: The name of your project.
# - PROJECT_VERSION: The version of your project.
# - CMAKE_VERSION_REQUIRED: The minimum required version of CMake.
# - CXX_STANDARD_REQUIRED: The C++ standard required for the project.
set(PROJECT_NAME "bash_compiler")
set(PROJECT_VERSION "1.0")
set(CMAKE_VERSION_REQUIRED "3.8")
set(CXX_STANDARD_REQUIRED "20")

cmake_minimum_required(VERSION ${CMAKE_VERSION_REQUIRED}) # Set the minimum required version of CMake to ensure compatibility.
project(${PROJECT_NAME} VERSION ${PROJECT_VERSION}) # Define the project name and version.










# This section ensures that the MSVC compiler (Visual Studio) supports
# "Edit and Continue" and "Program Database" debugging features during 
# the build process.
# Enable Hot Reload for MSVC compilers if supported.
if (POLICY CMP0141)
    cmake_policy(SET CMP0141 NEW)
    set(CMAKE_MSVC_DEBUG_INFORMATION_FORMAT "$<IF:$<AND:$<C_COMPILER_ID:MSVC>,$<CXX_COMPILER_ID:MSVC>>,$<$<CONFIG:Debug,RelWithDebInfo>:EditAndContinue>,$<$<CONFIG:Debug,RelWithDebInfo>:ProgramDatabase>>")
endif()












# Directory Variables:
# - SRC_DIR: Directory containing the source files.
# - INCLUDE_DIR: Directory containing the header files.
# - LIB_DIR: Directory where libraries would be stored.
# - TEST_DIR: Directory where tests would be stored.
set(SRC_DIR "${PROJECT_SOURCE_DIR}/src")
set(INCLUDE_DIR "${PROJECT_SOURCE_DIR}/include")
set(LIB_DIR "${PROJECT_SOURCE_DIR}/lib")
set(TEST_DIR "${PROJECT_SOURCE_DIR}/tests")
set(BIN_DIR "${PROJECT_SOURCE_DIR}/bin")  # Directory where the binary will be placed


# system variabele reference for debugging
message(STATUS "--: ${CMAKE_CURRENT_SOURCE_DIR}")   # directory of top-level CMakeLists.txt
message(STATUS "--: ${PROJECT_SOURCE_DIR}")         # directory of CMakeLists.txt recently called by project() command.




# Add source to this project's executable.

# Include directories to search for header files.
# This allows the compiler to locate the header files during the build process.
include_directories(${INCLUDE_DIR})

# Add source files to the project.
# GLOB is used to match all .c and .cpp files in the src directory.
file(GLOB CPP_SOURCES "${SRC_DIR}/*.cpp")

# Add the executable target.
# This line tells CMake to compile the source files into an executable named after the project.
add_executable(${PROJECT_NAME} ${CPP_SOURCES})

set_target_properties(${PROJECT_NAME} PROPERTIES RUNTIME_OUTPUT_DIRECTORY ${BIN_DIR})



















# Set the C++ Standard for the project. 
# If the CMake version is greater than 3.12, it will enforce the C++20 standard.
if (CMAKE_VERSION VERSION_GREATER 3.12)
    set_property(TARGET ${PROJECT_NAME} PROPERTY CXX_STANDARD ${CXX_STANDARD_REQUIRED})
endif()

# TODO: Add tests and install targets if needed.













# Advantages of using variables:
# - Maintainability: By using variables, the CMakeLists.txt is easier to maintain. 
#   You only need to update the variable values instead of searching for hardcoded strings.
# - Clarity: Variables make it clearer what each value represents, improving readability.
# 
# Further Customization:
# You can define additional variables for different build configurations (e.g., Debug, Release),
# compiler flags, specific library paths, or any other project-specific settings to make the
# CMake setup more scalable and easier to manage.