
import sys
import os
from pathlib import Path

ENVIRON = dict(os.environ.items())

ENVIRON["APPDATA"]           # {USR}\AppData\Roaming     persistent, portable settings
ENVIRON["LOCALAPPDATA"]      # {USR}\AppData\Local       machine-specific or large caches
ENVIRON["TEMP"]              # {USR}\AppData\Local\Temp  ephemeral data



def get_resource(relative_path: str, env_key: str = None, isDebug=False) -> Path:
    """
    If env_key is given ('APPDATA', 'LOCALAPPDATA', 'TEMP'), return the full absolute path inside that special folder.
    Otherwise, return a path relative to the project root (or bundled root).
    Handles both PyInstaller bundles and normal Python execution.
    """
    if isDebug: return Path(relative_path)
    if env_key is not None:
        allowed_keys = {"APPDATA", "LOCALAPPDATA", "TEMP"}
        if env_key not in allowed_keys: raise ValueError(f"env_key must be one of {allowed_keys}, got '{env_key}'")
        base = Path(ENVIRON[env_key])
    elif getattr(sys, "_MEIPASS", None):
        base = Path(sys._MEIPASS)  # running in a PyInstaller bundle
    else:
        base = Path(sys.path[0])
        # based on execution point, command-line arguments 
        # e.g. C:\repo\00-guide\python-alpaca_tradebot
        # base = Path(os.path.dirname(os.path.abspath(__file__)))
        # # based on file location 
        # # e.g. C:\repo\00-guide\python-alpaca_tradebot\lib\pvm
    return base / Path(relative_path)
    
# def get_resource(relative_path: str) -> Path:
#     """
#     Given a path relative to the project root (or bundled root), return the full
#     absolute path you can open/read.
#     Handles both PyInstaller bundles and normal Python execution.
#     """
#     if getattr(sys, "_MEIPASS", None): base = Path(sys._MEIPASS) # running in a PyInstaller bundle
#     else: 
#         base = Path(sys.path[0])                                 
#         # based on execution point, command-line arguments 
#         # e.g. C:\repo\00-guide\python-alpaca_tradebot
#         # base = Path(os.path.dirname(os.path.abspath(__file__)))
#         # # based on file location 
#         # # e.g. C:\repo\00-guide\python-alpaca_tradebot\lib\pvm
#     return base / Path(relative_path)

# def get_resource(relative_path: str, env_key: str) -> Path:
#     """
#     Given an environment variable key ('APPDATA', 'LOCALAPPDATA', 'TEMP')
#     and a relative path, return the full absolute path inside that special folder.
#     Only these three keys are allowed.
#     """
#     allowed_keys = {"APPDATA", "LOCALAPPDATA", "TEMP"}
#     if env_key not in allowed_keys: raise ValueError(f"env_key must be one of {allowed_keys}, got '{env_key}'")
#     base = Path(ENVIRON[env_key])
#     return base / Path(relative_path)


if __name__ == "__main__":
    # Example usages:
    # Get a resource relative to the project/bundle root
    config_path = get_resource("config/settings.json")
    print("Config path:", config_path)

    # Get a resource inside APPDATA
    appdata_log_path = get_resource("APPDATA", "myapp/logs/log.txt")
    print("AppData log path:", appdata_log_path)

    # Get a resource inside LOCALAPPDATA
    local_cache_path = get_resource("LOCALAPPDATA", "myapp/cache/data.db")
    print("LocalAppData cache path:", local_cache_path)

    # Get a resource inside TEMP
    temp_file_path = get_resource("TEMP", "myapp/tmpfile.tmp")
    print("Temp file path:", temp_file_path)

