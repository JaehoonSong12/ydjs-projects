def count11(s: str) -> int:
    """
    Description:
        Recursively counts the number of non-overlapping "11" substrings in the given string.

    Examples:
        count11("11abc11") → 2
        count11("abc11x11x11") → 3
        count11("111") → 1

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-recursion/q23.py`

    Args:
        s (str): The input string.

    Returns:
        int: The count of non-overlapping "11" substrings.
    """
    # Base case: your implementation and comment here.

    # Recursive case: your implementation and comment here.


# Unit tests for the count11 function
import unittest

class TestCount11Function(unittest.TestCase):
    def test_count11(self):
        self.assertEqual(count11("11abc11"), 2)  # count11("11abc11") → 2
        self.assertEqual(count11("abc11x11x11"), 3)  # count11("abc11x11x11") → 3
        self.assertEqual(count11("111"), 1)  # count11("111") → 1
        self.assertEqual(count11("1111"), 2)  # count11("1111") → 2
        self.assertEqual(count11("1"), 0)  # count11("1") → 0
        self.assertEqual(count11(""), 0)  # count11("") → 0
        self.assertEqual(count11("hi"), 0)  # count11("hi") → 0
        self.assertEqual(count11("11x111x1111"), 4)  # count11("11x111x1111") → 4
        self.assertEqual(count11("1x111"), 1)  # count11("1x111") → 1
        self.assertEqual(count11("1Hello1"), 0)  # count11("1Hello1") → 0
        self.assertEqual(count11("Hello"), 0)  # count11("Hello") → 0

if __name__ == '__main__':
    unittest.main()
