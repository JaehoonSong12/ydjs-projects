def array220(nums: list, index: int) -> bool:
    """
    Description:
        Given an array of integers, this function recursively checks 
        if any value in the array
        is followed by a value that is exactly 10 times that value. 
        The recursion starts from 
        the given index and proceeds through the array.

    Examples:
        array220([1, 2, 20], 0) → True
        array220([3, 30], 0) → True
        array220([3], 0) → False

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-recursion/q17.py`

    Args:
        nums (list): The input list of integers.
        index (int): The current index to check.

    Returns:
        bool: True if the condition is met, otherwise False.
    """
    # Base case: your implementation and comment here.
    if len(nums) == 0:
        return False
    if len(nums) == 1:
        return False
    if len(nums) == 2:
        if nums[0] * 10 == nums[1]:
            return True
        return False
    # Recursive case: your implementation and comment here.
    first_ch, second_ch, rest = nums[index], nums[index + 1], nums[index + 2:]
    # int, int, list
    if first_ch * 10 == second_ch:
        return True
    new_lst = [second_ch] + rest
    return array220(new_lst, 0)

# Unit tests for the array220 function
import unittest

class TestArray220Function(unittest.TestCase):
    def test_array220(self):
        self.assertTrue(array220([1, 2, 20], 0))  # array220([1,2,20], 0) → True
        self.assertTrue(array220([3, 30], 0))     # array220([3,30], 0) → True
        self.assertFalse(array220([3], 0))       # array220([3], 0) → False
        self.assertFalse(array220([], 0))       # array220([], 0) → False
        self.assertTrue(array220([3, 3, 30, 4], 0))  # array220([3,3,30,4], 0) → True
        self.assertFalse(array220([2, 19, 4], 0))  # array220([2,19,4], 0) → False
        self.assertFalse(array220([20, 2, 21], 0))  # array220([20,2,21], 0) → False
        self.assertTrue(array220([20, 2, 21, 210], 0))  # array220([20,2,21,210], 0) → True
        self.assertTrue(array220([2, 200, 2000], 0))  # array220([2,200,2000], 0) → True
        self.assertTrue(array220([0, 0], 0))  # array220([0,0], 0) → True
        self.assertFalse(array220([1, 2, 3, 4, 5, 6], 0))  # array220([1,2,3,4,5,6], 0) → False
        self.assertTrue(array220([1, 2, 3, 4, 5, 50, 6], 0))  # array220([1,2,3,4,5,50,6], 0) → True
        self.assertFalse(array220([1, 2, 3, 4, 5, 51, 6], 0))  # array220([1,2,3,4,5,51,6], 0) → False
        self.assertTrue(array220([1, 2, 3, 4, 4, 50, 500, 6], 0))  # array220([1,2,3,4,4,50,500,6], 0) → True

if __name__ == '__main__':
    unittest.main()
