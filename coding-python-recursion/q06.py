def sumDigits(n: int) -> int:
    """
    Description:
        Given a non-negative int n, return the sum of its digits recursively (no loops).
        Note that mod (%) by 10 yields the rightmost digit (e.g., 126 % 10 is 6), while integer division (//)
        by 10 removes the rightmost digit (e.g., 126 // 10 is 12).

    Examples:
        sumDigits(126) -> 9
        sumDigits(49) -> 13
        sumDigits(12) -> 3

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-recursion/q06.py`

    Args:
        n (int): A non-negative integer.

    Returns:
        int: The sum of the digits of n.
    """
    # Base case: your implementation and comment here.
    if n == 0: return 0
    if n < 10: return n
    # Recursive case: your implementation and comment here.
    return sumDigits(n % 10) + sumDigits(n // 10)

# Unit tests for the sumDigits function
import unittest

class TestSumDigitsFunction(unittest.TestCase):
    def test_sumDigits(self):
        self.assertEqual(sumDigits(126), 9)     # sumDigits(126) → 9
        self.assertEqual(sumDigits(49), 13)     # sumDigits(49) → 13
        self.assertEqual(sumDigits(12), 3)      # sumDigits(12) → 3
        self.assertEqual(sumDigits(10), 1)      # sumDigits(10) → 1
        self.assertEqual(sumDigits(1), 1)       # sumDigits(1) → 1
        self.assertEqual(sumDigits(0), 0)       # sumDigits(0) → 0
        self.assertEqual(sumDigits(730), 10)    # sumDigits(730) → 10
        self.assertEqual(sumDigits(1111), 4)    # sumDigits(1111) → 4
        self.assertEqual(sumDigits(11111), 5)   # sumDigits(11111) → 5
        self.assertEqual(sumDigits(10110), 3)   # sumDigits(10110) → 3
        self.assertEqual(sumDigits(235), 10)    # sumDigits(235) → 10

if __name__ == '__main__':
    unittest.main()
