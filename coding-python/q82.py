def tenRun(nums: list[int]) -> list[int]:
    """
    Description:
        For each multiple of 10 in the given list, change all the values following it
        to be that multiple of 10, until encountering another multiple of 10.
        Values before the first multiple of 10 remain unchanged.

    Examples:
        tenRun([2, 10, 3, 4, 20, 5]) → [2, 10, 10, 10, 20, 20]
        tenRun([10, 1, 20, 2]) → [10, 10, 20, 20]
        tenRun([10, 1, 9, 20]) → [10, 10, 10, 20]

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-ap/q82.py`

    Args:
        nums (list[int]): The list of integers to process.

    Returns:
        list[int]: A new list after applying the ten-run transformation.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# Unit tests for tenRun function
import unittest

class TestTenRun(unittest.TestCase):
    def test_tenRun(self):
        self.assertEqual(tenRun([2, 10, 3, 4, 20, 5]), [2, 10, 10, 10, 20, 20])
        self.assertEqual(tenRun([10, 1, 20, 2]), [10, 10, 20, 20])
        self.assertEqual(tenRun([10, 1, 9, 20]), [10, 10, 10, 20])
        self.assertEqual(tenRun([1, 2, 50, 1]), [1, 2, 50, 50])
        self.assertEqual(tenRun([1, 20, 50, 1]), [1, 20, 50, 50])
        self.assertEqual(tenRun([10, 10]), [10, 10])
        self.assertEqual(tenRun([10, 2]), [10, 10])
        self.assertEqual(tenRun([0, 2]), [0, 0])
        self.assertEqual(tenRun([1, 2]), [1, 2])
        self.assertEqual(tenRun([1]), [1])
        self.assertEqual(tenRun([]), [])

if __name__ == "__main__":
    unittest.main()
