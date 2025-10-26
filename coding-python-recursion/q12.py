def changeXY(s: str) -> str:
    """
    Description:
        Given a string, compute recursively (no loops) a new string where all 
        the lowercase 'x' chars have been changed to 'y' chars.

    Examples:
        changeXY("codex") -> "codey"
        changeXY("xxhixx") -> "yyhiyy"
        changeXY("xhixhix") -> "yhiyhiy"

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-recursion/q12.py`

    Args:
        s (str): The input string.

    Returns:
        str: The modified string with 'x' replaced by 'y'.
    """
    # Base case: your implementation and comment here.
    if (len(s) < 1): return ""
    if (len(s) == 1): 
        if s == "x": return "y"
        else: return s
    # Recursive case: len(s) is at least 2, so possible to divide into 1st char + the rest.
    first_ch, rest = s[0], s[1:]
    if (first_ch == "x"): first_ch = "y"
    return first_ch + changeXY(rest)


# Unit tests for the changeXY function
import unittest

class TestChangeXYFunction(unittest.TestCase):
    def test_changeXY(self):
        self.assertEqual(changeXY("codex"), "codey")        # changeXY("codex") → "codey"
        self.assertEqual(changeXY("xxhixx"), "yyhiyy")      # changeXY("xxhixx") → "yyhiyy"
        self.assertEqual(changeXY("xhixhix"), "yhiyhiy")    # changeXY("xhixhix") → "yhiyhiy"
        self.assertEqual(changeXY("hiy"), "hiy")            # changeXY("hiy") → "hiy"
        self.assertEqual(changeXY("h"), "h")                # changeXY("h") → "h"
        self.assertEqual(changeXY("x"), "y")                # changeXY("x") → "y"
        self.assertEqual(changeXY(""), "")                  # changeXY("") → ""
        self.assertEqual(changeXY("xxx"), "yyy")            # changeXY("xxx") → "yyy"
        self.assertEqual(changeXY("yyhxyi"), "yyhyyi")      # changeXY("yyhxyi") → "yyhyyi"
        self.assertEqual(changeXY("hihi"), "hihi")          # changeXY("hihi") → "hihi"

if __name__ == '__main__':
    unittest.main()
