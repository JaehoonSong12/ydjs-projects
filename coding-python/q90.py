def notAlone(nums: list[int], val: int) -> list[int]:
    """
    Description:
        We'll say that an element in an array is "alone" if there are values before
        and after it, and those values are different from it. Return a version of the
        given array where every instance of the given value which is alone is replaced
        by whichever of its left or right neighbors is larger.

    Examples:
        notAlone([1, 2, 3], 2) → [1, 3, 3]
        notAlone([1, 2, 3, 2, 5, 2], 2) → [1, 3, 3, 5, 5, 2]
        notAlone([3, 4], 3) → [3, 4]

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-ap/q90.py`

    Args:
        nums (list[int]): The input list of integers.
        val (int): The value to check for "alone" occurrences.

    Returns:
        list[int]: A new list where each "alone" occurrence of `val` is replaced
                   by the larger of its two neighbors.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# Unit tests for notAlone function
import unittest

class TestNotAlone(unittest.TestCase):
    def test_notAlone(self):
        self.assertEqual(notAlone([1, 2, 3], 2), [1, 3, 3])
        self.assertEqual(notAlone([1, 2, 3, 2, 5, 2], 2), [1, 3, 3, 5, 5, 2])
        self.assertEqual(notAlone([3, 4], 3), [3, 4])
        self.assertEqual(notAlone([3, 3], 3), [3, 3])
        self.assertEqual(notAlone([1, 3, 1, 2], 1), [1, 3, 3, 2])
        self.assertEqual(notAlone([3], 3), [3])
        self.assertEqual(notAlone([], 3), [])
        self.assertEqual(notAlone([7, 1, 6], 1), [7, 7, 6])
        self.assertEqual(notAlone([1, 1, 1], 1), [1, 1, 1])
        self.assertEqual(notAlone([1, 1, 1, 2], 1), [1, 1, 1, 2])

if __name__ == "__main__":
    unittest.main()
