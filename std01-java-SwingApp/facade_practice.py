"""
Description:
    This script demonstrates basic file I/O operations in Python.
    It performs two main tasks:
    1. Writes two lines of text to a file located at "async-jayden/sample.txt".
    2. Reads the contents of the file and prints it to the console.

The script is designed to illustrate the following concepts:
    - How to open a file for writing and add content to it.
    - The importance of properly closing files to free system resources.
    - How to open a file for reading, process its content, and handle potential exceptions using try-finally.

Instructions to run the tests via the CLI:
    1. Open your terminal or command prompt from the root directory.
    2. Execute the tests by running: `python async-jayden/facade_practice.py`
"""


# dynamically-typed language (e.g. Python, Javascript)
# _io.TextIOWrapper f;

#######################################################
# Open file in write mode
f = open("async-jayden/sample2.txt", "w")
# print(type(f))
f.write("This is the first line.\n")
f.write("This is the second line.\n")
# Manually close the file
f.close()


#######################################################
# Open file in read mode
f = open("async-jayden/sample2.txt", "r")
try:
    content = f.read()
    print("File content:")
    print(content)
finally:
    # close the file
    f.close()