def changePi(s: str) -> str:
    """
    Description:
        Given a string, compute recursively (no loops) a new string where all appearances of "pi" 
        have been replaced by "3.14".

    Examples:
        changePi("xpix") -> "x3.14x"
        changePi("pipi") -> "3.143.14"
        changePi("pip") -> "3.14p"

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-recursion/q13.py`

    Args:
        s (str): The input string.

    Returns:
        str: The modified string with "pi" replaced by "3.14".
    """
    # Base case: your implementation and comment here.
    if len(s) == 2: 
        if s == "pi":
            return "3.14"
        else: return s
    if len(s) < 2:
        return s
    # Recursive case: your implementation and comment here.
    first_ch, sec_ch, rest = s[0], s[1], s[2:]
    pi_in_num = "3.14"
    if first_ch == "p" and sec_ch == "i":
        return pi_in_num + changePi(rest)
    else:
        return first_ch + changePi(sec_ch + rest)
    

# Unit tests for the changePi function
import unittest

class TestChangePiFunction(unittest.TestCase):
    def test_changePi(self):
        self.assertEqual(changePi("xpix"), "x3.14x")        # changePi("xpix") → "x3.14x"
        self.assertEqual(changePi("pipi"), "3.143.14")      # changePi("pipi") → "3.143.14"
        self.assertEqual(changePi("pip"), "3.14p")         # changePi("pip") → "3.14p"
        self.assertEqual(changePi("pi"), "3.14")           # changePi("pi") → "3.14"
        self.assertEqual(changePi("hip"), "hip")           # changePi("hip") → "hip"
        self.assertEqual(changePi("p"), "p")               # changePi("p") → "p"
        self.assertEqual(changePi("x"), "x")               # changePi("x") → "x"
        self.assertEqual(changePi(""), "")                 # changePi("") → ""
        self.assertEqual(changePi("pixx"), "3.14xx")       # changePi("pixx") → "3.14xx"
        self.assertEqual(changePi("xyzzy"), "xyzzy")       # changePi("xyzzy") → "xyzzy"

if __name__ == '__main__':
    unittest.main()
