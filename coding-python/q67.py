def modThree(nums: list[int]) -> bool:
    """
    Description:
        Given a list of integers, return True if the list contains either
        three even values in a row or three odd values in a row.

    Examples:
        modThree([2, 1, 3, 5]) → True    # 1,3,5 are three odds in a row
        modThree([2, 4, 2, 5]) → True    # 2,4,2 are three evens in a row
        modThree([9, 9, 9]) → True       # three odds
        modThree([2, 1, 2, 5]) → False
        modThree([1, 2, 1, 2, 1]) → False

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-ap/q67.py`

    Args:
        nums (list[int]): The list of integers to examine.

    Returns:
        bool: True if there exists a run of three consecutive evens or three consecutive odds.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# Unit tests for modThree function
import unittest

class TestModThree(unittest.TestCase):
    def test_modThree(self):
        self.assertTrue(modThree([2, 1, 3, 5]))
        self.assertFalse(modThree([2, 1, 2, 5]))
        self.assertTrue(modThree([2, 4, 2, 5]))
        self.assertFalse(modThree([1, 2, 1, 2, 1]))
        self.assertTrue(modThree([9, 9, 9]))
        self.assertFalse(modThree([1, 2, 1]))
        self.assertFalse(modThree([1, 2]))
        self.assertFalse(modThree([1]))
        self.assertFalse(modThree([]))
        self.assertFalse(modThree([9, 7, 2, 9]))
        self.assertFalse(modThree([9, 7, 2, 9, 2, 2]))
        self.assertTrue(modThree([9, 7, 2, 9, 2, 2, 6]))

if __name__ == "__main__":
    unittest.main()
