# pdf_manager/__main__.py
"""
entry-point module
__main__.py: Python will run pdf_manager/__main__.py when you do 

    ```
    python -m pdf_manager
    ```

"""

from .core import main
# from .endorser import main

if __name__ == "__main__":
    main()
