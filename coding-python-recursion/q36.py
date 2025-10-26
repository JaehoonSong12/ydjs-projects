def splitArray(nums: list[int]) -> bool:
    """
    Description:
        Given an array of ints, determine recursively if it is possible to divide the ints 
        into two groups, so that the sums of the two groups are the same. Every int must be 
        in one group or the other. A recursive helper method is used to try all possibilities.

    Examples:
        splitArray([2,2]) → True
        splitArray([2,3]) → False
        splitArray([5,2,3]) → True

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-recursion/q36.py`

    Args:
        nums (list[int]): The input list of integers.

    Returns:
        bool: True if it is possible to split the array into two groups with equal sum, 
              False otherwise.
    """
    # Base case: your implementation and comment here.

    # Recursive case: your implementation and comment here.


# Unit tests for the splitArray function
import unittest

class TestSplitArray(unittest.TestCase):
    def test_splitArray(self):
        self.assertTrue(splitArray([2,2]))                # → True
        self.assertFalse(splitArray([2,3]))               # → False
        self.assertTrue(splitArray([5,2,3]))              # → True
        self.assertFalse(splitArray([5,2,2]))             # → False
        self.assertTrue(splitArray([1,1,1,1,1,1]))        # → True
        self.assertFalse(splitArray([1,1,1,1,1]))         # → False
        self.assertTrue(splitArray([]))                   # → True
        self.assertFalse(splitArray([1]))                 # → False
        self.assertFalse(splitArray([3,5]))               # → False
        self.assertTrue(splitArray([5,3,2]))              # → True
        self.assertTrue(splitArray([2,2,10,10,1,1]))      # → True
        self.assertFalse(splitArray([1,2,2,10,10,1,1]))    # → False
        self.assertTrue(splitArray([1,2,3,10,10,1,1]))     # → True

if __name__ == '__main__':
    unittest.main()
