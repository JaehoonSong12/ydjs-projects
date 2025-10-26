def has77(nums: list[int]) -> bool:
    """
    Description:
        Given a list of integers, return True if the list contains two 7's next to each other,
        or if there are two 7's separated by exactly one element (e.g. [7, x, 7]).

    Examples:
        has77([1, 7, 7]) → True
        has77([1, 7, 1, 7]) → True
        has77([1, 7, 1, 1, 7]) → False

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-ap/q65.py`

    Args:
        nums (list[int]): The list of integers to check.

    Returns:
        bool: True if there is a '77' pair or a '7_x_7' pattern, False otherwise.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# Unit tests for has77 function
import unittest

class TestHas77(unittest.TestCase):
    def test_has77(self):
        self.assertTrue(has77([1, 7, 7]))
        self.assertTrue(has77([1, 7, 1, 7]))
        self.assertFalse(has77([1, 7, 1, 1, 7]))
        self.assertTrue(has77([7, 7, 1, 1, 7]))
        self.assertFalse(has77([2, 7, 2, 2, 7, 2]))
        self.assertTrue(has77([2, 7, 2, 2, 7, 7]))
        self.assertTrue(has77([7, 2, 7, 2, 2, 7]))
        self.assertFalse(has77([7, 2, 6, 2, 2, 7]))
        self.assertTrue(has77([7, 7, 7]))
        self.assertTrue(has77([7, 1, 7]))
        self.assertFalse(has77([7, 1, 1]))
        self.assertFalse(has77([1, 2]))
        self.assertFalse(has77([1, 7]))
        self.assertFalse(has77([7]))

if __name__ == "__main__":
    unittest.main()
