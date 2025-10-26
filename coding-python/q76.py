def haveThree(nums: list[int]) -> bool:
    """
    Description:
        Given a list of integers, return True if the value 3 appears exactly three times
        and no two 3's are adjacent to each other.

    Examples:
        haveThree([3, 1, 3, 1, 3]) → True
        haveThree([3, 1, 3, 3]) → False
        haveThree([3, 4, 3, 3, 4]) → False

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-ap/q76.py`

    Args:
        nums (list[int]): The list of integers to check.

    Returns:
        bool: True if there are exactly three 3's and none are adjacent.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# Unit tests for haveThree function
import unittest

class TestHaveThree(unittest.TestCase):
    def test_haveThree(self):
        self.assertTrue(haveThree([3, 1, 3, 1, 3]))
        self.assertFalse(haveThree([3, 1, 3, 3]))
        self.assertFalse(haveThree([3, 4, 3, 3, 4]))
        self.assertFalse(haveThree([1, 3, 1, 3, 1, 2]))
        self.assertTrue(haveThree([1, 3, 1, 3, 1, 3]))
        self.assertFalse(haveThree([1, 3, 3, 1, 3]))
        self.assertFalse(haveThree([1, 3, 1, 3, 1, 3, 4, 3]))
        self.assertTrue(haveThree([3, 4, 3, 4, 3, 4, 4]))
        self.assertFalse(haveThree([3, 3, 3]))
        self.assertFalse(haveThree([1, 3]))
        self.assertFalse(haveThree([3]))
        self.assertFalse(haveThree([1]))

if __name__ == "__main__":
    unittest.main()
