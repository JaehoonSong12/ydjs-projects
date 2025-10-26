def maxBlock(s: str) -> int:
    """
    Description:
        Given a string `s`, return the length of the largest "block" in the string.
        A block is a run of adjacent characters that are the same.

    Examples:
        maxBlock("hoopla") → 2
        maxBlock("XXBBBbbxxXXXX") → 4
        maxBlock("XX2222BBBbbXX2222") → 4

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-ap/q85.py`

    Args:
        s (str): The input string.

    Returns:
        int: The length of the longest block of identical consecutive chars.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# Unit tests for maxBlock function
import unittest

class TestMaxBlock(unittest.TestCase):
    def test_maxBlock(self):
        self.assertEqual(maxBlock("hoopla"), 2)
        self.assertEqual(maxBlock("abbCCCddBBBxx"), 3)
        self.assertEqual(maxBlock(""), 0)
        self.assertEqual(maxBlock("xyz"), 1)
        self.assertEqual(maxBlock("xxyz"), 2)
        self.assertEqual(maxBlock("xyzz"), 2)
        self.assertEqual(maxBlock("abbbcbbbxbbbx"), 3)
        self.assertEqual(maxBlock("XXBBBbbxx"), 3)
        self.assertEqual(maxBlock("XXBBBBbbxx"), 4)
        self.assertEqual(maxBlock("XXBBBbbxxXXXX"), 4)
        self.assertEqual(maxBlock("XX2222BBBbbXX2222"), 4)

if __name__ == "__main__":
    unittest.main()
