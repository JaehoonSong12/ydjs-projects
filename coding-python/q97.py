def countClumps(nums: list[int]) -> int:
    """
    Description:
        Say that a "clump" in an array is a series of 2 or more adjacent elements of the same value.
        Return the number of clumps in the given array.

    Examples:
        countClumps([1, 2, 2, 3, 4, 4]) → 2
        countClumps([1, 1, 2, 1, 1]) → 2
        countClumps([1, 1, 1, 1, 1]) → 1

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-ap/q97.py`

    Args:
        nums (list[int]): The input list of integers.

    Returns:
        int: The number of clumps in the list.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# Unit tests for countClumps function
import unittest

class TestCountClumps(unittest.TestCase):
    def test_countClumps(self):
        self.assertEqual(countClumps([1, 2, 2, 3, 4, 4]), 2)
        self.assertEqual(countClumps([1, 1, 2, 1, 1]), 2)
        self.assertEqual(countClumps([1, 1, 1, 1, 1]), 1)
        self.assertEqual(countClumps([1, 2, 3]), 0)
        self.assertEqual(countClumps([2, 2, 1, 1, 1, 2, 1, 1, 2, 2]), 4)
        self.assertEqual(countClumps([0, 2, 2, 1, 1, 1, 2, 1, 1, 2, 2]), 4)
        self.assertEqual(countClumps([0, 0, 2, 2, 1, 1, 1, 2, 1, 1, 2, 2]), 5)
        self.assertEqual(countClumps([0, 0, 0, 2, 2, 1, 1, 1, 2, 1, 1, 2, 2]), 5)
        self.assertEqual(countClumps([]), 0)

if __name__ == "__main__":
    unittest.main()
