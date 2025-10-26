def pairStar(s: str) -> str:
    """
    Description:
        Given a string, this function recursively computes a new string where identical characters 
        that are adjacent in the original string are separated by "*".

    Examples:
        pairStar("hello") → "hel*lo"
        pairStar("xxyy") → "x*xy*y"
        pairStar("aaaa") → "a*a*a*a"

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-recursion/q19.py`

    Args:
        s (str): The input string.

    Returns:
        str: A new string where adjacent identical characters are separated by "*".
    """
    # Base case: your implementation and comment here.
    if len(s) < 2:
        return s
    # Recursive case: your implementation and comment here.
    first_ch, second_ch, rest = s[0], s[1], s[2:]
    if first_ch == second_ch:
        first_ch += "*"
    return first_ch + pairStar(second_ch + rest)

# Unit tests for the pairStar function
import unittest

class TestPairStarFunction(unittest.TestCase):
    def test_pairStar(self):
        self.assertEqual(pairStar("hello"), "hel*lo")  # pairStar("hello") → "hel*lo"
        self.assertEqual(pairStar("xxyy"), "x*xy*y")  # pairStar("xxyy") → "x*xy*y"
        self.assertEqual(pairStar("aaaa"), "a*a*a*a")  # pairStar("aaaa") → "a*a*a*a"
        self.assertEqual(pairStar("aaab"), "a*a*ab")  # pairStar("aaab") → "a*a*ab"
        self.assertEqual(pairStar("aa"), "a*a")  # pairStar("aa") → "a*a"
        self.assertEqual(pairStar("a"), "a")  # pairStar("a") → "a"
        self.assertEqual(pairStar(""), "")  # pairStar("") → ""
        self.assertEqual(pairStar("noadjacent"), "noadjacent")  # pairStar("noadjacent") → "noadjacent"
        self.assertEqual(pairStar("abba"), "ab*ba")  # pairStar("abba") → "ab*ba"
        self.assertEqual(pairStar("abbba"), "ab*b*ba")  # pairStar("abbba") → "ab*b*ba"

if __name__ == '__main__':
    unittest.main()
