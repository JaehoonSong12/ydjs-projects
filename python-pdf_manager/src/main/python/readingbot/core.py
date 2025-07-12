"""
Asynchronous Text Fetcher and Finite State Machine Analyzer

This module implements a scheduler that fetches random text from a REST API at
configurable intervals. The text is analyzed using a Finite State Machine (FSM).
Upon reaching a terminal state, the FSM halts the entire system gracefully.

Features:
- Configurable fetch interval from `.env` file
- Asynchronous scheduling using asyncio
- Proper error handling and graceful shutdown
- Modular FSM design
- Well-documented with Pythonic conventions (PEP 8, PEP 257)
"""

import asyncio
import os
import signal
import sys
from dataclasses import dataclass
from typing import Optional

import requests
from dotenv import load_dotenv
import certifi

# Load environment variables
load_dotenv()
FETCH_INTERVAL: int = int(os.getenv("FETCH_INTERVAL", 10))
API_URL: str = os.getenv("API_URL", "https://zenquotes.io/api/random")


@dataclass
class TextFSM:
    """Finite State Machine for text analysis."""

    state: str = "START"

    def transition(self, text: str) -> str:
        """
        Determines the next state based on the input text.

        Args:
            text (str): The text to analyze.

        Returns:
            str: The updated FSM state.
        """
        lower_text = text.lower()

        if self.state == "START":
            if "exit" in lower_text:
                self.state = "END"
            else:
                self.state = "READING"
        elif self.state == "READING":
            if "exit" in lower_text:
                self.state = "END"

        return self.state

    def is_terminal(self) -> bool:
        """Returns True if FSM is in a terminal state."""
        return self.state == "END"


class Scheduler:
    """Handles scheduling, fetching, and coordinating FSM execution."""

    def __init__(self, fsm: TextFSM, fetch_interval: int, api_url: str) -> None:
        self.fsm = fsm
        self.fetch_interval = fetch_interval
        self.api_url = api_url
        self._shutdown_requested = False

    async def run(self) -> None:
        """Run the fetch-analyze loop until FSM terminates or shutdown is requested."""
        loop = asyncio.get_event_loop()

        while not self.fsm.is_terminal() and not self._shutdown_requested:
            try:
                print("[INFO] Fetching text from API...")
                response = await loop.run_in_executor(
                    None, lambda: requests.get(self.api_url, verify=certifi.where())
                )
                response.raise_for_status()

                content = self._extract_text(response.json())
                print(f"[INFO] Received: {content}")

                new_state = self.fsm.transition(content)
                print(f"[INFO] FSM transitioned to: {new_state}")

            except Exception as e:
                print(f"[ERROR] Fetch or analysis failed: {e}")

            await asyncio.sleep(self.fetch_interval)

        print("[INFO] FSM reached terminal state or shutdown requested. Exiting scheduler.")

    def _extract_text(self, data: dict) -> str:
        """
        Extracts quote text from API response.

        Args:
            data (dict): JSON-decoded response data.

        Returns:
            str: Extracted quote or empty string.
        """
        if isinstance(data, list) and len(data) > 0:
            return data[0].get("q", "")
        return ""

    def request_shutdown(self) -> None:
        """Signals the scheduler to shutdown on next cycle."""
        self._shutdown_requested = True


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
