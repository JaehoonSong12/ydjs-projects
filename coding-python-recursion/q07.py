def count7(n: int) -> int:
    """
    Description:
        Given a non-negative int n, return the count of the occurrences of 7 as a digit, 
        so for example 717 yields 2. (no loops). Note that mod (%) by 10 yields the rightmost 
        digit (e.g., 126 % 10 is 6), while integer division (//) by 10 removes the rightmost 
        digit (e.g., 126 // 10 is 12).

    Examples:
        count7(717) -> 2
        count7(7) -> 1
        count7(123) -> 0

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-recursion/q07.py`

    Args:
        n (int): A non-negative integer.

    Returns:
        int: The count of occurrences of the digit 7 in n.
    """
    # Base case: your implementation and comment here.
    if n == 7: return 1
    if n < 10 and n != 7: return 0
    # Recursive case: your implementation and comment here.
    return count7(n % 10) + count7(n // 10)

# Unit tests for the count7 function
import unittest

class TestCount7Function(unittest.TestCase):
    def test_count7(self):
        self.assertEqual(count7(717), 2)         # count7(717) → 2
        self.assertEqual(count7(7), 1)           # count7(7) → 1
        self.assertEqual(count7(123), 0)         # count7(123) → 0
        self.assertEqual(count7(77), 2)          # count7(77) → 2
        self.assertEqual(count7(7123), 1)        # count7(7123) → 1
        self.assertEqual(count7(771237), 3)      # count7(771237) → 3
        self.assertEqual(count7(771737), 4)      # count7(771737) → 4
        self.assertEqual(count7(47571), 2)       # count7(47571) → 2
        self.assertEqual(count7(777777), 6)      # count7(777777) → 6
        self.assertEqual(count7(70701277), 4)    # count7(70701277) → 4
        self.assertEqual(count7(777576197), 5)   # count7(777576197) → 5
        self.assertEqual(count7(99999), 0)       # count7(99999) → 0
        self.assertEqual(count7(99799), 1)       # count7(99799) → 1

if __name__ == '__main__':
    unittest.main()
