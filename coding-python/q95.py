def starOut(s: str) -> str:
    """
    Description:
        Return a version of the given string where for every star (*) in the string,
        the star and the characters immediately to its left and right are removed.
        So "ab*cd" yields "ad" and "ab**cd" also yields "ad".

    Examples:
        starOut("ab*cd") → "ad"
        starOut("ab**cd") → "ad"
        starOut("sm*eilly") → "silly"

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-ap/q95.py`

    Args:
        s (str): The input string.

    Returns:
        str: The processed string with stars and adjacent chars removed.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# Unit tests for starOut function
import unittest

class TestStarOut(unittest.TestCase):
    def test_starOut(self):
        self.assertEqual(starOut("ab*cd"), "ad")
        self.assertEqual(starOut("ab**cd"), "ad")
        self.assertEqual(starOut("sm*eilly"), "silly")
        self.assertEqual(starOut("sm*eil*ly"), "siy")
        self.assertEqual(starOut("sm**eil*ly"), "siy")
        self.assertEqual(starOut("sm***eil*ly"), "siy")
        self.assertEqual(starOut("stringy*"), "string")
        self.assertEqual(starOut("*stringy"), "tringy")
        self.assertEqual(starOut("*str*in*gy"), "ty")
        self.assertEqual(starOut("abc"), "abc")
        self.assertEqual(starOut("a*bc"), "c")
        self.assertEqual(starOut("ab"), "ab")
        self.assertEqual(starOut("a*b"), "")
        self.assertEqual(starOut("a"), "a")
        self.assertEqual(starOut("a*"), "")
        self.assertEqual(starOut("*a"), "")
        self.assertEqual(starOut("*"), "")
        self.assertEqual(starOut(""), "")

if __name__ == "__main__":
    unittest.main()
