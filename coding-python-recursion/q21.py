def countPairs(s: str) -> int:
    """
    Description:
        A "pair" in a string is defined as two instances of the same character separated by exactly one character.
        This function recursively computes the number of such pairs in the given string.
        Overlapping pairs are counted.

    Examples:
        countPairs("axa") → 1
        countPairs("axax") → 2
        countPairs("axbx") → 1

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-recursion/q21.py`

    Args:
        s (str): The input string.

    Returns:
        int: The number of pairs found in the string.
    """
    # Base case: your implementation and comment here.
    if len(s) < 3:
        return 0
    # Recursive case: your implementation and comment here.
    first_ch, second_ch, third_ch, rest = s[0], s[1], s[2], s[3:]
    count = 0
    if first_ch == third_ch:
         count += 1
    return count + countPairs(second_ch + third_ch + rest)


# Unit tests for the countPairs function
import unittest

class TestCountPairsFunction(unittest.TestCase):
    def test_countPairs(self):
        self.assertEqual(countPairs("axa"), 1)  # countPairs("axa") → 1
        self.assertEqual(countPairs("axax"), 2)  # countPairs("axax") → 2
        self.assertEqual(countPairs("axbx"), 1)  # countPairs("axbx") → 1
        self.assertEqual(countPairs("hi"), 0)  # countPairs("hi") → 0
        self.assertEqual(countPairs("hihih"), 3)  # countPairs("hihih") → 3
        self.assertEqual(countPairs("ihihhh"), 3)  # countPairs("ihihhh") → 3
        self.assertEqual(countPairs("ihjxhh"), 0)  # countPairs("ihjxhh") → 0
        self.assertEqual(countPairs(""), 0)  # countPairs("") → 0
        self.assertEqual(countPairs("a"), 0)  # countPairs("a") → 0
        self.assertEqual(countPairs("aa"), 0)  # countPairs("aa") → 0
        self.assertEqual(countPairs("aaa"), 1)  # countPairs("aaa") → 1

if __name__ == '__main__':
    unittest.main()
