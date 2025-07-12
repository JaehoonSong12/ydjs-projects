import asyncio
import signal
import time
import pygame
import wave
from enum import Enum, auto
from typing import Optional

FETCH_INTERVAL = 5
API_URL = "https://jsonplaceholder.typicode.com/posts/1"

STATE_SOUNDS = {
    "Idle": "audio/idle.wav",
    "Fetching": "audio/fetching.wav",
    "Parsing": "audio/parsing.wav",
    "Done": "audio/done.wav",
    "Error": "audio/error.wav",
}

# Initialize pygame mixer
pygame.mixer.init()

def play_sound(file_path: str):
    try:
        pygame.mixer.music.load(file_path)
        pygame.mixer.music.play()
    except Exception as e:
        print(f"[WARN] Failed to play sound {file_path}: {e}")

class State(Enum):
    IDLE = auto()
    FETCHING = auto()
    PARSING = auto()
    DONE = auto()
    ERROR = auto()

class TextFSM:
    def __init__(self):
        self.state = State.IDLE

    def transition_to(self, new_state: State):
        print(f"[FSM] Transitioning from {self.state.name} to {new_state.name}")
        self.state = new_state
        self.play_state_sound()

    def play_state_sound(self):
        sound_file = STATE_SOUNDS.get(self.state.name.title())
        if sound_file:
            play_sound(sound_file)

    async def fetch_data(self, url: str) -> Optional[str]:
        self.transition_to(State.FETCHING)
        try:
            await asyncio.sleep(1)  # Simulate async HTTP request
            print("[FETCH] Data fetched successfully.")
            return '{ "title": "Sample title", "body": "Sample body" }'
        except Exception as e:
            print(f"[ERROR] Failed to fetch data: {e}")
            self.transition_to(State.ERROR)
            return None

    def parse_data(self, raw_data: str) -> Optional[dict]:
        self.transition_to(State.PARSING)
        try:
            parsed = eval(raw_data)
            print(f"[PARSE] Parsed title: {parsed.get('title')}")
            return parsed
        except Exception as e:
            print(f"[ERROR] Failed to parse data: {e}")
            self.transition_to(State.ERROR)
            return None

    async def run_once(self, url: str):
        self.transition_to(State.IDLE)
        raw_data = await self.fetch_data(url)
        if raw_data:
            parsed = self.parse_data(raw_data)
            if parsed:
                self.transition_to(State.DONE)
            else:
                self.transition_to(State.ERROR)
        else:
            self.transition_to(State.ERROR)

class Scheduler:
    def __init__(self, fsm: TextFSM, fetch_interval: int, api_url: str):
        self.fsm = fsm
        self.fetch_interval = fetch_interval
        self.api_url = api_url
        self._shutdown_requested = False

    def request_shutdown(self):
        self._shutdown_requested = True

    async def run(self):
        while not self._shutdown_requested:
            print("[SCHEDULER] Triggering FSM run...")
            await self.fsm.run_once(self.api_url)
            await asyncio.sleep(self.fetch_interval)
        print("[SCHEDULER] Scheduler shutdown complete.")

async def main_thread() -> None:
    """Main entry point for running the FSM scheduler."""
    fsm = TextFSM()
    scheduler = Scheduler(fsm=fsm, fetch_interval=FETCH_INTERVAL, api_url=API_URL)

    def signal_handler(signum, frame):
        print("[INFO] Shutdown signal received.")
        scheduler.request_shutdown()

    signal.signal(signal.SIGINT, signal_handler)
    signal.signal(signal.SIGTERM, signal_handler)

    await scheduler.run()

def main():
    try:
        asyncio.run(main_thread())
    except KeyboardInterrupt:
        print("[INFO] KeyboardInterrupt detected. Exiting...")
    except Exception as e:
        print(f"[ERROR] Unexpected exception: {e}")

if __name__ == "__main__":
    main()
