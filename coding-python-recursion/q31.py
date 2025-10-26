def groupSum(start: int, nums: list[int], target: int) -> bool:
    """
    Description:
        Given an array of integers `nums`, determine recursively whether it's 
        possible to choose a group of elements starting from index `start` 
        such that their sum equals `target`.
        
        This is a classic backtracking problem. At each index, there are two choices:
        - Include nums[start] in the group
        - Exclude nums[start] from the group

    Examples:
        groupSum(0, [2, 4, 8], 10) → True
        groupSum(0, [2, 4, 8], 14) → True
        groupSum(0, [2, 4, 8], 9) → False

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-recursion/q31.py`

    Args:
        start (int): The starting index to consider elements from `nums`.
        nums (list[int]): The list of integers.
        target (int): The sum to achieve.

    Returns:
        bool: True if a valid group exists, False otherwise.
    """
    # Base case: your implementation and comment here.

    # Recursive case: your implementation and comment here.


# Unit tests for the groupSum function
import unittest

class TestGroupSum(unittest.TestCase):
    def test_groupSum(self):
        self.assertTrue(groupSum(0, [2, 4, 8], 10))     # → True
        self.assertTrue(groupSum(0, [2, 4, 8], 14))     # → True
        self.assertFalse(groupSum(0, [2, 4, 8], 9))     # → False
        self.assertTrue(groupSum(0, [2, 4, 8], 8))      # → True
        self.assertTrue(groupSum(1, [2, 4, 8], 8))      # → True
        self.assertFalse(groupSum(1, [2, 4, 8], 2))     # → False
        self.assertTrue(groupSum(0, [1], 1))            # → True
        self.assertFalse(groupSum(0, [9], 1))           # → False
        self.assertTrue(groupSum(1, [9], 0))            # → True
        self.assertTrue(groupSum(0, [], 0))             # → True
        self.assertTrue(groupSum(0, [10, 2, 2, 5], 17)) # → True
        self.assertTrue(groupSum(0, [10, 2, 2, 5], 15)) # → True
        self.assertTrue(groupSum(0, [10, 2, 2, 5], 9))  # → True

if __name__ == '__main__':
    unittest.main()
