def array6(nums: list, index: int) -> bool:
    """
    Description:
        Given an array of ints, compute recursively if the array 
        contains a 6.
        The function considers only the part of the array 
        that begins at the given index.
        A recursive call moves forward by passing index+1.

    Examples:
        array6([1, 6, 4], 0) → True
        array6([1, 4], 0) → False
        array6([6], 0) → True

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-recursion/q15.py`

    Args:
        nums (list): The input list of integers.
        index (int): The current index to check.

    Returns:
        bool: True if the array contains a 6, otherwise False.
    """
    # Base case: your implementation and comment here.
    if len(nums) < 1: return False
    if len(nums) == 1:
        if nums[0] == 6:
            return True
        return False
    # Recursive case: your implementation and comment here.
    first_ch, rest = nums[index], nums[index + 1:]
    if first_ch == 6:
        return True
    return array6(rest, index)

# Unit tests for the array6 function
import unittest

class TestArray6Function(unittest.TestCase):
    def test_array6(self):
        self.assertTrue(array6([1, 6, 4], 0))    # array6([1,6,4], 0) → True
        self.assertFalse(array6([1, 4], 0))      # array6([1,4], 0) → False
        self.assertTrue(array6([6], 0))          # array6([6], 0) → True
        self.assertFalse(array6([], 0))          # array6([], 0) → False
        self.assertTrue(array6([6, 2, 2], 0))    # array6([6, 2, 2], 0) → True
        self.assertFalse(array6([2, 5], 0))      # array6([2,5], 0) → False
        self.assertTrue(array6([1, 9, 4, 6, 6], 0))  # array6([1,9,4,6,6], 0) → True
        self.assertTrue(array6([2, 5, 6], 0))    # array6([2,5,6], 0) → True

if __name__ == '__main__':
    unittest.main()
