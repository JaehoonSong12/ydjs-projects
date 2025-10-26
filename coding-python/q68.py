def prefixAgain(s: str, n: int) -> bool:
    """
    Description:
        Consider the prefix string made of the first `n` characters of `s`. 
        Return True if that prefix appears again somewhere else in `s`.
        You may assume `s` is non-empty and 1 <= n <= len(s).

    Examples:
        prefixAgain("abXYabc", 1) → True   # prefix "a" appears later
        prefixAgain("abXYabc", 2) → True   # prefix "ab" appears at index 4
        prefixAgain("Hi12345Hi6789Hi10", 3) → True   # "Hi1"
        prefixAgain("Hi12345Hi6789Hi10", 4) → False  # "Hi12" only at start

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-ap/q68.py`

    Args:
        s (str): The input string.
        n (int): The length of the prefix to check.

    Returns:
        bool: True if the prefix of length `n` appears again in `s`, False otherwise.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# Unit tests for prefixAgain function
import unittest

class TestPrefixAgain(unittest.TestCase):
    def test_prefixAgain(self):
        self.assertTrue(prefixAgain("abXYabc", 1))
        self.assertTrue(prefixAgain("abXYabc", 2))
        self.assertFalse(prefixAgain("abXYabc", 3))
        self.assertTrue(prefixAgain("xyzxyxyxy", 2))
        self.assertFalse(prefixAgain("xyzxyxyxy", 3))
        self.assertTrue(prefixAgain("Hi12345Hi6789Hi10", 1))
        self.assertTrue(prefixAgain("Hi12345Hi6789Hi10", 2))
        self.assertTrue(prefixAgain("Hi12345Hi6789Hi10", 3))
        self.assertFalse(prefixAgain("Hi12345Hi6789Hi10", 4))
        self.assertFalse(prefixAgain("a", 1))
        self.assertTrue(prefixAgain("aa", 1))
        self.assertFalse(prefixAgain("ab", 1))

if __name__ == "__main__":
    unittest.main()
