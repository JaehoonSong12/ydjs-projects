def zipZap(s: str) -> str:
    """
    Description:
        Look for patterns like "zip" and "zap" in the string — length-3 substrings 
        that start with 'z' and end with 'p'. Remove the middle letter of each such 
        substring, leaving just "zp".

    Examples:
        zipZap("zipXzap") → "zpXzp"
        zipZap("zopzop") → "zpzp"
        zipZap("zzzopzop") → "zzzpzp"

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-ap/q88.py`

    Args:
        s (str): The input string.

    Returns:
        str: The string with all "z?p" patterns reduced to "zp".
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# Unit tests for zipZap function
import unittest

class TestZipZap(unittest.TestCase):
    def test_zipZap(self):
        self.assertEqual(zipZap("zipXzap"), "zpXzp")
        self.assertEqual(zipZap("zopzop"), "zpzp")
        self.assertEqual(zipZap("zzzopzop"), "zzzpzp")
        self.assertEqual(zipZap("zibzap"), "zibzp")
        self.assertEqual(zipZap("zip"), "zp")
        self.assertEqual(zipZap("zi"), "zi")
        self.assertEqual(zipZap("z"), "z")
        self.assertEqual(zipZap(""), "")
        self.assertEqual(zipZap("zzp"), "zp")
        self.assertEqual(zipZap("abcppp"), "abcppp")
        self.assertEqual(zipZap("azbcppp"), "azbcppp")
        self.assertEqual(zipZap("azbcpzpp"), "azbcpzp")

if __name__ == "__main__":
    unittest.main()
