def countHi(s: str) -> int:
    """
    Description:
        Given a string, compute recursively (no loops) the number of 
        times lowercase "hi" appears in the string.

    Examples:
        countHi("xxhixx") -> 1
        countHi("xhixhix") -> 2
        countHi("hi") -> 1

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-recursion/q11.py`

    Args:
        s (str): The input string.

    Returns:
        int: The count of "hi" occurrences in the string.
    """
    # Base case: your implementation and comment here.
    if len(s) < 2: return 0 # processing the whole string

    # Recursive case: your implementation and comment here.

    ### "xxhixx" -> "x" + "xhixx" 
    rest = s[1 : len(s)]
    two_ch = s[0:2]
    if (two_ch == "hi"): 
        return 1 + countHi(rest)
    else:
        return countHi(rest)

# Unit tests for the countHi function
import unittest

class TestCountHiFunction(unittest.TestCase):
    def test_countHi(self):
        self.assertEqual(countHi("xxhixx"), 1)         # countHi("xxhixx") → 1
        self.assertEqual(countHi("xhixhix"), 2)        # countHi("xhixhix") → 2
        self.assertEqual(countHi("hi"), 1)            # countHi("hi") → 1
        self.assertEqual(countHi("hihih"), 2)         # countHi("hihih") → 2
        self.assertEqual(countHi("h"), 0)             # countHi("h") → 0
        self.assertEqual(countHi(""), 0)              # countHi("") → 0
        self.assertEqual(countHi("ihihihihih"), 4)    # countHi("ihihihihih") → 4
        self.assertEqual(countHi("ihihihihihi"), 5)   # countHi("ihihihihihi") → 5
        self.assertEqual(countHi("hiAAhi12hi"), 3)    # countHi("hiAAhi12hi") → 3
        self.assertEqual(countHi("xhixhxihihhhih"), 3)  # countHi("xhixhxihihhhih") → 3
        self.assertEqual(countHi("ship"), 1)          # countHi("ship") → 1

if __name__ == '__main__':
    unittest.main()
