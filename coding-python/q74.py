def getSandwich(s: str) -> str:
    """
    Description:
        A "sandwich" is defined by two occurrences of the substring "bread" with
        some filling in between. Return the filling between the first and last
        appearance of "bread" in the input string `s`. If there are fewer than
        two occurrences of "bread", return an empty string "".

    Examples:
        getSandwich("breadjambread") → "jam"
        getSandwich("xxbreadjambreadyy") → "jam"
        getSandwich("xxbreadbreadjambreadyy") → "breadjam"

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-ap/q74.py`

    Args:
        s (str): The input string to search for "bread".

    Returns:
        str: The substring between the first and last "bread", or "" if none.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# Unit tests for getSandwich function
import unittest

class TestGetSandwich(unittest.TestCase):
    def test_getSandwich(self):
        self.assertEqual(getSandwich("breadjambread"), "jam")
        self.assertEqual(getSandwich("xxbreadjambreadyy"), "jam")
        self.assertEqual(getSandwich("xxbreadyy"), "")
        self.assertEqual(getSandwich("xxbreadbreadjambreadyy"), "breadjam")
        self.assertEqual(getSandwich("breadAbread"), "A")
        self.assertEqual(getSandwich("breadbread"), "")
        self.assertEqual(getSandwich("abcbreaz"), "")
        self.assertEqual(getSandwich("xyz"), "")
        self.assertEqual(getSandwich(""), "")
        self.assertEqual(getSandwich("breadbreaxbread"), "breax")
        self.assertEqual(getSandwich("breaxbreadybread"), "y")
        self.assertEqual(getSandwich("breadbreadbreadbread"), "breadbread")

if __name__ == "__main__":
    unittest.main()
