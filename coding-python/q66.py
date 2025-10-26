def has12(nums: list[int]) -> bool:
    """
    Description:
        Given a list of integers, return True if there is a 1 in the list with a 2 somewhere later in the list.

    Examples:
        has12([1, 3, 2]) → True    # 1 appears at index 0, 2 appears later
        has12([3, 1, 2]) → True    # 1 at index 1, 2 at index 2
        has12([3, 1, 4, 1, 6, 2]) → True # the second 1 has a 2 after
        has12([2, 1, 4, 1, 6, 2]) → True # the 1 at index 1 has a 2 later

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-ap/q66.py`

    Args:
        nums (list[int]): The list of integers to check.

    Returns:
        bool: True if a 1 is found with a 2 somewhere after it; otherwise False.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# Unit tests for has12 function
import unittest

class TestHas12(unittest.TestCase):
    def test_has12(self):
        self.assertTrue(has12([1, 3, 2]))
        self.assertTrue(has12([3, 1, 2]))
        self.assertTrue(has12([3, 1, 4, 5, 2]))
        self.assertFalse(has12([3, 1, 4, 5, 6]))
        self.assertTrue(has12([3, 1, 4, 1, 6, 2]))
        self.assertTrue(has12([2, 1, 4, 1, 6, 2]))
        self.assertFalse(has12([2, 1, 4, 1, 6]))
        self.assertFalse(has12([1]))
        self.assertFalse(has12([2, 1, 3]))
        self.assertTrue(has12([2, 1, 3, 2]))
        self.assertFalse(has12([2]))
        self.assertFalse(has12([3, 2]))
        self.assertTrue(has12([3, 1, 3, 2]))
        self.assertFalse(has12([3, 5, 9]))
        self.assertFalse(has12([3, 5, 1]))
        self.assertFalse(has12([3, 2, 1]))
        self.assertTrue(has12([1, 2]))

if __name__ == "__main__":
    unittest.main()
