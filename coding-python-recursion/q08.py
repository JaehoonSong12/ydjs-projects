def count8(n: int) -> int:
    """
    Description:
        Given a non-negative int n, compute recursively (no loops) the count of the occurrences
        of 8 as a digit, except that an 8 with another 8 immediately to its left counts double.
        For example, count8(8818) yields 4 because the first 8 (from the right) is counted normally,
        while the 8 that immediately follows another 8 is counted twice.

    Examples:
        count8(8) -> 1
        count8(818) -> 2
        count8(8818) -> 4

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-recursion/q08.py`

    Args:
        n (int): A non-negative integer.

    Returns:
        int: The count of occurrences of 8 in n, with an 8 immediately following another 8 counted double.
    """
    # Base case: your implementation and comment here.

    # Recursive case: your implementation and comment here.


# Unit tests for the count8 function
import unittest

class TestCount8Function(unittest.TestCase):
    def test_count8(self):
        self.assertEqual(count8(8), 1)           # count8(8) → 1
        self.assertEqual(count8(818), 2)         # count8(818) → 2
        self.assertEqual(count8(8818), 4)        # count8(8818) → 4
        self.assertEqual(count8(8088), 4)        # count8(8088) → 4
        self.assertEqual(count8(123), 0)         # count8(123) → 0
        self.assertEqual(count8(81238), 2)       # count8(81238) → 2
        self.assertEqual(count8(88788), 6)       # count8(88788) → 6
        self.assertEqual(count8(8234), 1)        # count8(8234) → 1
        self.assertEqual(count8(2348), 1)        # count8(2348) → 1
        self.assertEqual(count8(23884), 3)       # count8(23884) → 3
        self.assertEqual(count8(0), 0)           # count8(0) → 0
        self.assertEqual(count8(1818188), 5)     # count8(1818188) → 5
        self.assertEqual(count8(8818181), 5)     # count8(8818181) → 5
        self.assertEqual(count8(1080), 1)        # count8(1080) → 1
        self.assertEqual(count8(188), 3)         # count8(188) → 3
        self.assertEqual(count8(88888), 9)       # count8(88888) → 9
        self.assertEqual(count8(9898), 2)        # count8(9898) → 2
        self.assertEqual(count8(78), 1)          # count8(78) → 1

if __name__ == '__main__':
    unittest.main()
