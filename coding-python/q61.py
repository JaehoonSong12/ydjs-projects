def sumDigits(s: str) -> int:
    """
    Description:
        Given a string, return the sum of the digits 0-9 that appear in the string,
        ignoring all other characters. Return 0 if there are no digits.
        (Note: str.isdigit() tests for '0'-'9'.)

    Examples:
        sumDigits("aa1bc2d3")   → 6   # 1 + 2 + 3
        sumDigits("aa11b33")    → 8   # 1 + 1 + 3 + 3
        sumDigits("123abc123")  → 12  # 1+2+3 + 1+2+3

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-ap/q61.py`

    Args:
        s (str): The input string potentially containing digits.

    Returns:
        int: The sum of all digit characters found in the string.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# Unit tests for sumDigits function
import unittest

class TestSumDigits(unittest.TestCase):
    def test_sumDigits(self):
        self.assertEqual(sumDigits("aa1bc2d3"), 6)
        self.assertEqual(sumDigits("aa11b33"), 8)
        self.assertEqual(sumDigits("Chocolate"), 0)
        self.assertEqual(sumDigits("5hoco1a1e"), 7)
        self.assertEqual(sumDigits("123abc123"), 12)
        self.assertEqual(sumDigits(""), 0)
        self.assertEqual(sumDigits("Hello"), 0)
        self.assertEqual(sumDigits("X1z9b2"), 12)
        self.assertEqual(sumDigits("5432a"), 14)

if __name__ == "__main__":
    unittest.main()
