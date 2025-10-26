def split53(nums: list[int]) -> bool:
    """
    Description:
        Given an array of ints, determine recursively if it is 
        possible to divide the ints into 
        two groups such that the sum of the two groups is the same, 
        with these constraints:
          - All multiples of 5 must be in one group.
          - All multiples of 3 (that are not multiples of 5) must 
          be in the other group.
        Every int must be in one group or the other.

    Examples:
        split53([1, 1]) → True
        split53([1, 1, 1]) → False
        split53([2, 4, 2]) → True

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-recursion/q38.py`

    Args:
        nums (list[int]): The input list of integers.

    Returns:
        bool: True if the array can be split according to the conditions, otherwise False.
    """
    # Base case: your implementation and comment here.

    # Recursive case: your implementation and comment here.


# Unit tests for the split53 function
import unittest

class TestSplit53(unittest.TestCase):
    def test_split53(self):
        self.assertTrue(split53([1, 1]))                  # → True
        self.assertFalse(split53([1, 1, 1]))                # → False
        self.assertTrue(split53([2, 4, 2]))                 # → True
        self.assertFalse(split53([2, 2, 2, 1]))             # → False
        self.assertTrue(split53([3, 3, 5, 1]))              # → True
        self.assertFalse(split53([3, 5, 8]))                # → False
        self.assertTrue(split53([2, 4, 6]))                 # → True
        self.assertTrue(split53([3, 5, 6, 10, 3, 3]))       # → True

if __name__ == '__main__':
    unittest.main()
