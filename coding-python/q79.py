def tripleUp(nums: list[int]) -> bool:
    """
    Description:
        Return True if the list contains, somewhere, three increasing adjacent numbers,
        like ...4, 5, 6... or 23, 24, 25.

    Examples:
        tripleUp([1, 4, 5, 6, 2]) → True
        tripleUp([1, 2, 3]) → True
        tripleUp([1, 2, 4]) → False

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-ap/q79.py`

    Args:
        nums (list[int]): The list of integers to check.

    Returns:
        bool: True if there is any run of three consecutive increasing ints.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# Unit tests for tripleUp function
import unittest

class TestTripleUp(unittest.TestCase):
    def test_tripleUp(self):
        self.assertTrue(tripleUp([1, 4, 5, 6, 2]))
        self.assertTrue(tripleUp([1, 2, 3]))
        self.assertFalse(tripleUp([1, 2, 4]))
        self.assertTrue(tripleUp([1, 2, 4, 5, 7, 6, 5, 6, 7, 6]))
        self.assertFalse(tripleUp([1, 2, 4, 5, 7, 6, 5, 7, 7, 6]))
        self.assertFalse(tripleUp([1, 2]))
        self.assertFalse(tripleUp([1]))
        self.assertFalse(tripleUp([]))
        self.assertTrue(tripleUp([10, 9, 8, -100, -99, -98, 100]))
        self.assertFalse(tripleUp([10, 9, 8, -100, -99, 99, 100]))
        self.assertTrue(tripleUp([-100, -99, -99, 100, 101, 102]))
        self.assertFalse(tripleUp([2, 3, 5, 6, 8, 9, 2, 3]))

if __name__ == "__main__":
    unittest.main()
