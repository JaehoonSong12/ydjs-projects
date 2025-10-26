def splitOdd10(nums: list[int]) -> bool:
    """
    Description:
        Given an array of ints, determine recursively if it is 
        possible to divide the ints into two groups,
        such that the sum of one group is a multiple of 10 and 
        the sum of the other group is odd.
        Every int must be in one group or the other. This is 
        solved by recursively assigning each number
        to one of two groups and then checking if one group's 
        sum is divisible by 10 and the other's sum is odd.

    Examples:
        splitOdd10([5,5,5]) → True
        splitOdd10([5,5,6]) → False
        splitOdd10([5,5,6,1]) → True

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-recursion/q37.py`

    Args:
        nums (list[int]): The input list of integers.

    Returns:
        bool: True if it is possible to split the array into two 
              groups meeting the conditions, False otherwise.
    """
    return divide(nums, [], [])

def divide(nums: list[int], group1: list[int], group2: list[int]) -> bool: # helper function
    # base-case
    if (len(nums) == 0): return sum(group1) % 10 == 0 and sum(group2) % 2 == 1
    # recursive-case
    first, rest = nums[0], nums[1:]
    return divide(rest, [first] + group1, group2) or divide(rest, group1, [first] + group2)




# Unit tests for the splitOdd10 function
import unittest

class TestSplitOdd10(unittest.TestCase):
    def test_splitOdd10(self):
        self.assertTrue(splitOdd10([5, 5, 5]))           # → True
        self.assertFalse(splitOdd10([5, 5, 6]))           # → False
        self.assertTrue(splitOdd10([5, 5, 6, 1]))         # → True
        self.assertTrue(splitOdd10([6, 5, 5, 1]))         # → True
        self.assertTrue(splitOdd10([6, 5, 5, 1, 10]))     # → True
        self.assertFalse(splitOdd10([6, 5, 5, 5, 1]))     # → False
        self.assertTrue(splitOdd10([1]))                  # → True  (group1: 0, group2: 1 → 0 is multiple of 10, 1 is odd)
        self.assertFalse(splitOdd10([]))                  # → False (cannot split an empty list)
        self.assertTrue(splitOdd10([10, 7, 5, 5]))        # → True
        self.assertFalse(splitOdd10([10, 0, 5, 5]))       # → False
        self.assertTrue(splitOdd10([10, 7, 5, 5, 2]))      # → True
        self.assertFalse(splitOdd10([10, 7, 5, 5, 1]))     # → False

if __name__ == '__main__':
    unittest.main()
