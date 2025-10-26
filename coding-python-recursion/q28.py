def strCount(s: str, sub: str) -> int:
    """
    Description:
        Recursively computes the number of times a non-empty substring 'sub' appears
        in the given string 's', without overlapping occurrences.

    Examples:
        strCount("catcowcat", "cat") → 2
        strCount("catcowcat", "cow") → 1
        strCount("catcowcat", "dog") → 0

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-recursion/q28.py`

    Args:
        s (str): The input string.
        sub (str): The substring to count.

    Returns:
        int: The number of non-overlapping occurrences of 'sub' in 's'.
    """
    # Base case: your implementation and comment here.

    # Recursive case: your implementation and comment here.


# Unit tests for the strCount function
import unittest

class TestStrCountFunction(unittest.TestCase):
    def test_strCount(self):
        self.assertEqual(strCount("catcowcat", "cat"), 2)  # strCount("catcowcat", "cat") → 2
        self.assertEqual(strCount("catcowcat", "cow"), 1)  # strCount("catcowcat", "cow") → 1
        self.assertEqual(strCount("catcowcat", "dog"), 0)  # strCount("catcowcat", "dog") → 0
        self.assertEqual(strCount("cacatcowcat", "cat"), 2)  # strCount("cacatcowcat", "cat") → 2
        self.assertEqual(strCount("xyx", "x"), 2)  # strCount("xyx", "x") → 2
        self.assertEqual(strCount("iiiijj", "i"), 4)  # strCount("iiiijj", "i") → 4
        self.assertEqual(strCount("iiiijj", "ii"), 2)  # strCount("iiiijj", "ii") → 2
        self.assertEqual(strCount("iiiijj", "iii"), 1)  # strCount("iiiijj", "iii") → 1
        self.assertEqual(strCount("iiiijj", "j"), 2)  # strCount("iiiijj", "j") → 2
        self.assertEqual(strCount("iiiijj", "jj"), 1)  # strCount("iiiijj", "jj") → 1
        self.assertEqual(strCount("aaabababab", "ab"), 4)  # strCount("aaabababab", "ab") → 4
        self.assertEqual(strCount("aaabababab", "aa"), 1)  # strCount("aaabababab", "aa") → 1
        self.assertEqual(strCount("aaabababab", "a"), 6)  # strCount("aaabababab", "a") → 6
        self.assertEqual(strCount("aaabababab", "b"), 4)  # strCount("aaabababab", "b") → 4

if __name__ == '__main__':
    unittest.main()
