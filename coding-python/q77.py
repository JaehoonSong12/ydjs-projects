def twoTwo(nums: list[int]) -> bool:
    """
    Description:
        Given a list of integers, return True if every 2 in the list is adjacent to
        at least one other 2. In other words, for each occurrence of 2, there must be
        another 2 either immediately before or after it.

    Examples:
        twoTwo([4, 2, 2, 3]) → True
        twoTwo([2, 2, 4]) → True
        twoTwo([2, 2, 4, 2]) → False

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-ap/q77.py`

    Args:
        nums (list[int]): The list of integers to check.

    Returns:
        bool: True if every 2 has at least one adjacent 2, False otherwise.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# Unit tests for twoTwo function
import unittest

class TestTwoTwo(unittest.TestCase):
    def test_twoTwo(self):
        self.assertTrue(twoTwo([4, 2, 2, 3]))
        self.assertTrue(twoTwo([2, 2, 4]))
        self.assertFalse(twoTwo([2, 2, 4, 2]))
        self.assertTrue(twoTwo([1, 3, 4]))
        self.assertTrue(twoTwo([1, 2, 2, 3, 4]))
        self.assertFalse(twoTwo([1, 2, 3, 4]))
        self.assertTrue(twoTwo([2, 2]))
        self.assertTrue(twoTwo([2, 2, 7]))
        self.assertFalse(twoTwo([2, 2, 7, 2, 1]))
        self.assertTrue(twoTwo([4, 2, 2, 2]))
        self.assertTrue(twoTwo([2, 2, 2]))
        self.assertFalse(twoTwo([1, 2]))
        self.assertFalse(twoTwo([2]))
        self.assertTrue(twoTwo([1]))
        self.assertTrue(twoTwo([]))
        self.assertTrue(twoTwo([5, 2, 2, 3]))
        self.assertFalse(twoTwo([2, 2, 5, 2]))

if __name__ == "__main__":
    unittest.main()
