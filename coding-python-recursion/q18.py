def allStar(s: str) -> str:
    """
    Description:
        Given a string, this function recursively computes a 
        new string where all adjacent
        characters are separated by a "*".

    Examples:
        allStar("hello") → "h*e*l*l*o"
        allStar("abc") → "a*b*c"
        allStar("ab") → "a*b"
        allStar("a") → "a"
        allStar("") → ""

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-recursion/q18.py`

    Args:
        s (str): The input string.

    Returns:
        str: A new string where adjacent characters are separated by "*".
    """
    # Base case: your implementation and comment here.
    if len(s) < 2:
        return s
    # Recursive case: your implementation and comment here.
    first_ch, second_ch, rest = s[0], s[1], s[2:]
    if not second_ch == " ":
        second_ch, rest = "*", second_ch + rest
    return first_ch + second_ch + allStar(rest)

# Unit tests for the allStar function
import unittest

class TestAllStarFunction(unittest.TestCase):
    def test_allStar(self):
        self.assertEqual(allStar("hello"), "h*e*l*l*o")  # allStar("hello") → "h*e*l*l*o"
        self.assertEqual(allStar("abc"), "a*b*c")  # allStar("abc") → "a*b*c"
        self.assertEqual(allStar("ab"), "a*b")  # allStar("ab") → "a*b"
        self.assertEqual(allStar("a"), "a")  # allStar("a") → "a"
        self.assertEqual(allStar(""), "")  # allStar("") → ""
        self.assertEqual(allStar("3.14"), "3*.*1*4")  # allStar("3.14") → "3*.*1*4"
        self.assertEqual(allStar("Chocolate"), "C*h*o*c*o*l*a*t*e")  # allStar("Chocolate") → "C*h*o*c*o*l*a*t*e"
        self.assertEqual(allStar("1234"), "1*2*3*4")  # allStar("1234") → "1*2*3*4"

if __name__ == '__main__':
    unittest.main()
