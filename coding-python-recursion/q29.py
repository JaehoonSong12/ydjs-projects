def strCopies(s: str, sub: str, n: int) -> bool:
    """
    Description:
        Recursively determines if at least 'n' copies of a non-empty substring 'sub'
        appear in the given string 's', allowing overlapping occurrences.

    Examples:
        strCopies("catcowcat", "cat", 2) → True
        strCopies("catcowcat", "cow", 2) → False
        strCopies("catcowcat", "cow", 1) → True

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-recursion/q29.py`

    Args:
        s (str): The input string.
        sub (str): The substring to search for.
        n (int): The required number of occurrences.

    Returns:
        bool: True if 'sub' appears at least 'n' times, False otherwise.
    """
    # Base case: your implementation and comment here.

    # Recursive case: your implementation and comment here.


# Unit tests for the strCopies function
import unittest

class TestStrCopiesFunction(unittest.TestCase):
    def test_strCopies(self):
        self.assertTrue(strCopies("catcowcat", "cat", 2))  # strCopies("catcowcat", "cat", 2) → True
        self.assertFalse(strCopies("catcowcat", "cow", 2))  # strCopies("catcowcat", "cow", 2) → False
        self.assertTrue(strCopies("catcowcat", "cow", 1))  # strCopies("catcowcat", "cow", 1) → True
        self.assertTrue(strCopies("iiijjj", "i", 3))  # strCopies("iiijjj", "i", 3) → True
        self.assertFalse(strCopies("iiijjj", "i", 4))  # strCopies("iiijjj", "i", 4) → False
        self.assertTrue(strCopies("iiijjj", "ii", 2))  # strCopies("iiijjj", "ii", 2) → True
        self.assertFalse(strCopies("iiijjj", "ii", 3))  # strCopies("iiijjj", "ii", 3) → False
        self.assertFalse(strCopies("iiijjj", "x", 3))  # strCopies("iiijjj", "x", 3) → False
        self.assertTrue(strCopies("iiijjj", "x", 0))  # strCopies("iiijjj", "x", 0) → True
        self.assertTrue(strCopies("iiiiij", "iii", 3))  # strCopies("iiiiij", "iii", 3) → True
        self.assertFalse(strCopies("iiiiij", "iii", 4))  # strCopies("iiiiij", "iii", 4) → False
        self.assertTrue(strCopies("ijiiiiij", "iiii", 2))  # strCopies("ijiiiiij", "iiii", 2) → True
        self.assertFalse(strCopies("ijiiiiij", "iiii", 3))  # strCopies("ijiiiiij", "iiii", 3) → False
        self.assertTrue(strCopies("dogcatdogcat", "dog", 2))  # strCopies("dogcatdogcat", "dog", 2) → True

if __name__ == '__main__':
    unittest.main()
