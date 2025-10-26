def sameStarChar(s: str) -> bool:
    """
    Description:
        Return True if for every '*' in the string, whenever there are characters
        immediately before and after the star, those characters are the same.

    Examples:
        sameStarChar("xy*yzz") → True
        sameStarChar("xy*zzz") → False
        sameStarChar("*xa*az") → True

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-ap/q75.py`

    Args:
        s (str): The input string to check.

    Returns:
        bool: True if all '*' that have neighbors are surrounded by identical chars.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# Unit tests for sameStarChar function
import unittest

class TestSameStarChar(unittest.TestCase):
    def test_sameStarChar(self):
        self.assertTrue(sameStarChar("xy*yzz"))
        self.assertFalse(sameStarChar("xy*zzz"))
        self.assertTrue(sameStarChar("*xa*az"))
        self.assertFalse(sameStarChar("*xa*bz"))
        self.assertTrue(sameStarChar("*xa*a*"))
        self.assertTrue(sameStarChar(""))
        self.assertTrue(sameStarChar("*xa*a*a"))
        self.assertFalse(sameStarChar("*xa*a*b"))
        self.assertTrue(sameStarChar("*12*2*2"))
        self.assertFalse(sameStarChar("12*2*3*"))
        self.assertTrue(sameStarChar("abcDEF"))
        self.assertFalse(sameStarChar("XY*YYYY*Z*"))
        self.assertTrue(sameStarChar("*"))
        self.assertTrue(sameStarChar("**"))

if __name__ == "__main__":
    unittest.main()
