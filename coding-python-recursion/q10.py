def countX(s: str) -> int:
    """
    Description:
        Given a string, compute recursively (no loops) the number of lowercase 'x' chars in the string.

    Examples:
        countX("xxhixx") -> 4
        countX("xhixhix") -> 3
        countX("hi") -> 0

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-recursion/q10.py`

    Args:
        s (str): The input string.

    Returns:
        int: The count of 'x' characters in the string.
    """
    # Base case: your implementation and comment here.
    if (len(s) < 1): return 0
    if (len(s) == 1): return 1 if s == 'x' else 0
    # Recursive case: your implementation and comment here.
    left, middle_ch, right = s[:len(s)//2], s[len(s)//2], s[len(s)//2 + 1:]
    ## [Important Python Syntax]
    # s[a:b] in Python 
    # represents a slice of the sequence s, 
    # starting from index a (inclusive) and 
    # ending at index b (exclusive).
    count = 0
    if (middle_ch == 'x'): count += 1


    return countX(left) + count + countX(right) # O(n log n)

# Unit tests for the countX function
import unittest

class TestCountXFunction(unittest.TestCase):
    def test_countX(self):
        self.assertEqual(countX("xxhixx"), 4)   # countX("xxhixx") → 4
        self.assertEqual(countX("xhixhix"), 3)  # countX("xhixhix") → 3
        self.assertEqual(countX("hi"), 0)       # countX("hi") → 0
        self.assertEqual(countX("h"), 0)        # countX("h") → 0
        self.assertEqual(countX("x"), 1)        # countX("x") → 1
        self.assertEqual(countX(""), 0)         # countX("") → 0
        self.assertEqual(countX("hihi"), 0)     # countX("hihi") → 0
        self.assertEqual(countX("hiAAhi12hi"), 0)  # countX("hiAAhi12hi") → 0

if __name__ == '__main__':
    unittest.main()
