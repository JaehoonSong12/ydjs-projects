def either24(nums: list[int]) -> bool:
    """
    Description:
        Given a list of integers, return True if the list contains a 2 next to a 2
        or a 4 next to a 4, but not both.

    Examples:
        either24([1, 2, 2]) → True
        either24([4, 4, 1]) → True
        either24([4, 4, 1, 2, 2]) → False

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-ap/q63.py`

    Args:
        nums (list[int]): The list of integers to examine.

    Returns:
        bool: True if there's a 2-2 pair or a 4-4 pair (exclusively), False otherwise.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# Unit tests for either24 function
import unittest

class TestEither24(unittest.TestCase):
    def test_either24(self):
        self.assertTrue(either24([1, 2, 2]))
        self.assertTrue(either24([4, 4, 1]))
        self.assertFalse(either24([4, 4, 1, 2, 2]))
        self.assertFalse(either24([1, 2, 3, 4]))
        self.assertFalse(either24([3, 5, 9]))
        self.assertTrue(either24([1, 2, 3, 4, 4]))
        self.assertTrue(either24([2, 2, 3, 4]))
        self.assertTrue(either24([1, 2, 3, 2, 2, 4]))
        self.assertFalse(either24([1, 2, 3, 2, 2, 4, 4]))
        self.assertFalse(either24([1, 2]))
        self.assertTrue(either24([2, 2]))
        self.assertTrue(either24([4, 4]))
        self.assertFalse(either24([2]))
        self.assertFalse(either24([]))

if __name__ == "__main__":
    unittest.main()
