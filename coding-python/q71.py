def sameEnds(s: str) -> str:
    """
    Description:
        Return the longest non-overlapping substring that appears at both
        the start and end of the string `s`.

    Examples:
        sameEnds("abXYab") → "ab"
        sameEnds("javaXYZjava") → "java"
        sameEnds("javajava") → "java"

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-ap/q71.py`

    Args:
        s (str): The input string.

    Returns:
        str: The longest substring appearing at both start and end without overlap.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# Unit tests for sameEnds function
import unittest

class TestSameEnds(unittest.TestCase):
    def test_sameEnds(self):
        self.assertEqual(sameEnds("abXYab"), "ab")
        self.assertEqual(sameEnds("xx"), "x")
        self.assertEqual(sameEnds("xxx"), "x")
        self.assertEqual(sameEnds("xxxx"), "xx")
        self.assertEqual(sameEnds("javaXYZjava"), "java")
        self.assertEqual(sameEnds("javajava"), "java")
        self.assertEqual(sameEnds("xavaXYZjava"), "")
        self.assertEqual(sameEnds("Hello! and Hello!"), "Hello!")
        self.assertEqual(sameEnds("x"), "")
        self.assertEqual(sameEnds(""), "")
        self.assertEqual(sameEnds("abcb"), "")
        self.assertEqual(sameEnds("mymmy"), "my")

if __name__ == "__main__":
    unittest.main()
