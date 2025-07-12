# pvm_utils/__init__.py

"""
pvm_utils — easy utilities for PVM interaction.

Top-level imports provide your package's essential functionality:

Usages:
>>> get_resource("config/settings.json")                
# project/bundle root
# #config file

>>> get_resource("myapp/logs/log.txt", "APPDATA")       
# {USR}\AppData\Roaming
# persistent, portable settings

>>> get_resource("myapp/cache/data.db", "LOCALAPPDATA") 
# {USR}\AppData\Local
# machine-specific or large caches

>>> get_resource("myapp/tmpfile.tmp", "TEMP")           
# {USR}\AppData\Local\Temp
# ephemeral data







Import all distributions for ease of use:

>>> from pvm import get_resource



Wildcard import also supported:

>>> from pvm import *
    # imports get_resource
"""

# Expose version
__version__ = "0.1.0"

# Define what will be imported with `from pvm_utils import *`
__all__ = [
    "get_resource",
]

# Import the public symbols
from .core import get_resource