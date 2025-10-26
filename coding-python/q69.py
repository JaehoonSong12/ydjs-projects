def xyzMiddle(s: str) -> bool:
    """
    Description:
        Given a string, return True if "xyz" appears in the middle of the string.
        To be in the middle, the number of characters to the left and right of "xyz"
        must differ by at most one.

    Examples:
        xyzMiddle("AAxyzBB") → True    # left="AA", right="BB" (2 vs 2)
        xyzMiddle("AxyzBB") → True     # left="A", right="BB" (1 vs 2) diff=1
        xyzMiddle("AxyzBBB") → False   # left="A", right="BBB" (1 vs 3) diff=2

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-ap/q69.py`

    Args:
        s (str): The input string.

    Returns:
        bool: True if "xyz" appears in the middle by the above definition, False otherwise.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# Unit tests for xyzMiddle function
import unittest

class TestXyzMiddle(unittest.TestCase):
    def test_xyzMiddle(self):
        self.assertTrue(xyzMiddle("AAxyzBB"))
        self.assertTrue(xyzMiddle("AxyzBB"))
        self.assertFalse(xyzMiddle("AxyzBBB"))
        self.assertFalse(xyzMiddle("AxyzBBBB"))
        self.assertFalse(xyzMiddle("AAAxyzB"))
        self.assertTrue(xyzMiddle("AAAxyzBB"))
        self.assertFalse(xyzMiddle("AAAAxyzBB"))
        self.assertFalse(xyzMiddle("AAAAAxyzBBB"))
        self.assertTrue(xyzMiddle("1x345xyz12x4"))
        self.assertTrue(xyzMiddle("xyzAxyzBBB"))
        self.assertTrue(xyzMiddle("xyzAxyzBxyz"))
        self.assertTrue(xyzMiddle("xyzxyzAxyzBxyzxyz"))
        self.assertTrue(xyzMiddle("xyzxyzxyzBxyzxyz"))
        self.assertTrue(xyzMiddle("xyzxyzAxyzxyzxyz"))
        self.assertFalse(xyzMiddle("xyzxyzAxyzxyzxy"))
        self.assertFalse(xyzMiddle("AxyzxyzBB"))
        self.assertFalse(xyzMiddle(""))
        self.assertFalse(xyzMiddle("x"))
        self.assertFalse(xyzMiddle("xy"))
        self.assertTrue(xyzMiddle("xyz"))
        self.assertTrue(xyzMiddle("xyzz"))

if __name__ == "__main__":
    unittest.main()
