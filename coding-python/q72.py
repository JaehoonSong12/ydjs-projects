def mirrorEnds(s: str) -> str:
    """
    Description:
        Given a string, look for a mirror-image (backwards) string at both the beginning
        and end of the string. Return the longest such prefix. In other words, find the
        largest prefix of `s` which equals its reverse at the end of `s`.

    Examples:
        mirrorEnds("abXYZba") → "ab"
        mirrorEnds("abca") → "a"
        mirrorEnds("123and then 321") → "123"

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-ap/q72.py`

    Args:
        s (str): The input string.

    Returns:
        str: The longest prefix which is the reverse of the corresponding suffix.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# Unit tests for mirrorEnds function
import unittest

class TestMirrorEnds(unittest.TestCase):
    def test_mirrorEnds(self):
        self.assertEqual(mirrorEnds("abXYZba"), "ab")
        self.assertEqual(mirrorEnds("abca"), "a")
        self.assertEqual(mirrorEnds("aba"), "aba")
        self.assertEqual(mirrorEnds("abab"), "")
        self.assertEqual(mirrorEnds("xxx"), "xxx")
        self.assertEqual(mirrorEnds("xxYxx"), "xxYxx")
        self.assertEqual(mirrorEnds("Hi and iH"), "Hi")
        self.assertEqual(mirrorEnds(" x "), " x ")
        self.assertEqual(mirrorEnds(""), "")
        self.assertEqual(mirrorEnds("123and then 321"), "123")
        self.assertEqual(mirrorEnds("band andab"), "ba")

if __name__ == "__main__":
    unittest.main()
