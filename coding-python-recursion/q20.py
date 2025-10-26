def endX(s: str) -> str:
    """
    Description:
        Given a string, this function recursively computes a new string where all the lowercase 'x' 
        characters have been moved to the end of the string.

    Examples:
        endX("xxre") → "rexx"
        endX("xxhixx") → "hixxxx"
        endX("xhixhix") → "hihixxx"

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-recursion/q20.py`

    Args:
        s (str): The input string.

    Returns:
        str: A new string with all 'x' characters moved to the end.
    """
    # Base case: your implementation and comment here.
    if len(s) < 2:
        return s
    # Recursive case: your implementation and comment here.
    first_ch, rest = s[0], s[1:]
    if first_ch == "x":
        return endX(rest) + first_ch
    return first_ch + endX(rest)

# Unit tests for the endX function
import unittest

class TestEndXFunction(unittest.TestCase):
    def test_endX(self):
        self.assertEqual(endX("xxre"), "rexx")  # endX("xxre") → "rexx"
        self.assertEqual(endX("xxhixx"), "hixxxx")  # endX("xxhixx") → "hixxxx"
        self.assertEqual(endX("xhixhix"), "hihixxx")  # endX("xhixhix") → "hihixxx"
        self.assertEqual(endX("hiy"), "hiy")  # endX("hiy") → "hiy"
        self.assertEqual(endX("h"), "h")  # endX("h") → "h"
        self.assertEqual(endX("x"), "x")  # endX("x") → "x"
        self.assertEqual(endX("xx"), "xx")  # endX("xx") → "xx"
        self.assertEqual(endX(""), "")  # endX("") → ""
        self.assertEqual(endX("bxx"), "bxx")  # endX("bxx") → "bxx"
        self.assertEqual(endX("bxax"), "baxx")  # endX("bxax") → "baxx"
        self.assertEqual(endX("axaxax"), "aaaxxx")  # endX("axaxax") → "aaaxxx"
        self.assertEqual(endX("xxhxi"), "hixxx")  # endX("xxhxi") → "hixxx"

if __name__ == '__main__':
    unittest.main()
