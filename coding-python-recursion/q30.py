def strDist(s: str, sub: str) -> int:
    """
    Description:
        Recursively computes the length of the largest substring in 's' that starts 
        and ends with the given non-empty substring 'sub'.

    Examples:
        strDist("catcowcat", "cat") → 9
        strDist("catcowcat", "cow") → 3
        strDist("cccatcowcatxx", "cat") → 9

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-recursion/q30.py`

    Args:
        s (str): The input string.
        sub (str): The substring to find.

    Returns:
        int: The length of the largest substring that starts and ends with 'sub'.
    """
    # Base case: your implementation and comment here.

    # Recursive case: your implementation and comment here.


# Unit tests for the strDist function
import unittest

class TestStrDistFunction(unittest.TestCase):
    def test_strDist(self):
        self.assertEqual(strDist("catcowcat", "cat"), 9)  # strDist("catcowcat", "cat") → 9
        self.assertEqual(strDist("catcowcat", "cow"), 3)  # strDist("catcowcat", "cow") → 3
        self.assertEqual(strDist("cccatcowcatxx", "cat"), 9)  # strDist("cccatcowcatxx", "cat") → 9
        self.assertEqual(strDist("abccatcowcatcatxyz", "cat"), 12)  # strDist("abccatcowcatcatxyz", "cat") → 12
        self.assertEqual(strDist("xyx", "x"), 3)  # strDist("xyx", "x") → 3
        self.assertEqual(strDist("xyx", "y"), 1)  # strDist("xyx", "y") → 1
        self.assertEqual(strDist("xyx", "z"), 0)  # strDist("xyx", "z") → 0
        self.assertEqual(strDist("z", "z"), 1)  # strDist("z", "z") → 1
        self.assertEqual(strDist("x", "z"), 0)  # strDist("x", "z") → 0
        self.assertEqual(strDist("", "z"), 0)  # strDist("", "z") → 0
        self.assertEqual(strDist("hiHellohihihi", "hi"), 13)  # strDist("hiHellohihihi", "hi") → 13
        self.assertEqual(strDist("hiHellohihihi", "hih"), 5)  # strDist("hiHellohihihi", "hih") → 5
        self.assertEqual(strDist("hiHellohihihi", "o"), 1)  # strDist("hiHellohihihi", "o") → 1
        self.assertEqual(strDist("hiHellohihihi", "II"), 2)  # strDist("hiHellohihihi", "II") → 2

if __name__ == '__main__':
    unittest.main()
