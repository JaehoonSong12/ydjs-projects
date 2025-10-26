import re

def notReplace(s: str) -> str:
    """
    Description:
        Given a string, return a new string where every appearance of the lowercase word "is"
        has been replaced with "is not". The word "is" should not be immediately preceded or
        followed by a letter — so for example the "is" in "this" does not count.

    Examples:
        notReplace("is test") → "is not test"
        notReplace("is-is") → "is not-is not"
        notReplace("This is right") → "This is not right"
        notReplace("This is isabell") → "This is not isabell"

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-ap/q96.py`

    Args:
        s (str): The input string.

    Returns:
        str: The modified string with standalone "is" replaced by "is not".
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.
    # # Use regex with negative lookbehind/lookahead to ensure "is" is not part of a larger word
    # return re.sub(r'(?<![A-Za-z])is(?![A-Za-z])', 'is not', s)


# Unit tests for notReplace function
import unittest

class TestNotReplace(unittest.TestCase):
    def test_notReplace(self):
        self.assertEqual(notReplace("is test"), "is not test")
        self.assertEqual(notReplace("is-is"), "is not-is not")
        self.assertEqual(notReplace("This is right"), "This is not right")
        self.assertEqual(notReplace("This is isabell"), "This is not isabell")
        self.assertEqual(notReplace(""), "")
        self.assertEqual(notReplace("is"), "is not")
        self.assertEqual(notReplace("isis"), "isis")
        self.assertEqual(notReplace("Dis is bliss is"), "Dis is not bliss is not")
        self.assertEqual(notReplace("is his"), "is not his")
        self.assertEqual(notReplace("xis yis"), "xis yis")
        self.assertEqual(notReplace("AAAis is"), "AAAis is not")

if __name__ == "__main__":
    unittest.main()
