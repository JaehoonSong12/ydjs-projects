def sumNumbers(s: str) -> int:
    """
    Description:
        Given a string, return the sum of the numbers appearing in the string,
        ignoring all other characters. A number is a series of one or more digit
        characters in a row.

    Examples:
        sumNumbers("abc123xyz") → 123
        sumNumbers("aa11b33") → 44
        sumNumbers("7 11") → 18

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-ap/q93.py`

    Args:
        s (str): The input string containing digits and other characters.

    Returns:
        int: The sum of all numbers found in the string.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# Unit tests for sumNumbers function
import unittest

class TestSumNumbers(unittest.TestCase):
    def test_sumNumbers(self):
        self.assertEqual(sumNumbers("abc123xyz"), 123)
        self.assertEqual(sumNumbers("aa11b33"), 44)
        self.assertEqual(sumNumbers("7 11"), 18)
        self.assertEqual(sumNumbers("Chocolate"), 0)
        self.assertEqual(sumNumbers("5hoco1a1e"), 7)
        self.assertEqual(sumNumbers("5$1; ;1!!"), 7)
        self.assertEqual(sumNumbers("a1234bb11"), 1245)
        self.assertEqual(sumNumbers(""), 0)
        self.assertEqual(sumNumbers("a22bbb3"), 25)

if __name__ == "__main__":
    unittest.main()
