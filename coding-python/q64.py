def matchUp(nums1: list[int], nums2: list[int]) -> int:
    """
    Description:
        Given two lists of integers of the same length, for every element in `nums1`,
        consider the corresponding element in `nums2` at the same index. Count how many
        times the two elements differ by 2 or less, but are not equal.

    Examples:
        matchUp([1, 2, 3], [2, 3, 10]) → 2   # pairs (1,2) and (2,3)
        matchUp([1, 2, 3], [2, 3, 5]) → 3    # (1,2), (2,3), (3,5)
        matchUp([1, 2, 3], [2, 3, 3]) → 2    # (1,2), (2,3)

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-ap/q64.py`

    Args:
        nums1 (list[int]): First list of integers.
        nums2 (list[int]): Second list of integers, same length as nums1.

    Returns:
        int: The count of positions where the values differ by at most 2 but are not equal.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# Unit tests for matchUp function
import unittest

class TestMatchUp(unittest.TestCase):
    def test_matchUp(self):
        self.assertEqual(matchUp([1,2,3], [2,3,10]), 2)
        self.assertEqual(matchUp([1, 2, 3], [2, 3, 5]), 3)
        self.assertEqual(matchUp([1, 2, 3], [2, 3, 3]), 2)
        self.assertEqual(matchUp([5, 3], [5, 5]), 1)
        self.assertEqual(matchUp([5, 3], [4, 4]), 2)
        self.assertEqual(matchUp([5, 3], [3, 3]), 1)
        self.assertEqual(matchUp([5, 3], [2, 2]), 1)
        self.assertEqual(matchUp([5, 3], [1, 1]), 1)
        self.assertEqual(matchUp([5, 3], [0, 0]), 0)
        self.assertEqual(matchUp([4], [4]), 0)
        self.assertEqual(matchUp([4], [5]), 1)

if __name__ == "__main__":
    unittest.main()
