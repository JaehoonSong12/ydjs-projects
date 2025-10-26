def evenOdd(nums: list[int]) -> list[int]:
    """
    Description:
        Return a list containing the same numbers as the given list `nums`, 
        but rearranged so that all even numbers come before all odd numbers.
        The relative order among evens or among odds does not matter.

    Examples:
        evenOdd([1, 0, 1, 0, 0, 1, 1]) → [0, 0, 0, 1, 1, 1, 1]
        evenOdd([3, 3, 2]) → [2, 3, 3]
        evenOdd([2, 2, 2]) → [2, 2, 2]

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-ap/q98.py`

    Args:
        nums (list[int]): The input list of integers.

    Returns:
        list[int]: A list with evens first followed by odds.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# Unit tests for evenOdd function
import unittest

class TestEvenOdd(unittest.TestCase):
    def test_evenOdd(self):
        self.assertEqual(evenOdd([1, 0, 1, 0, 0, 1, 1]), [0, 0, 0, 1, 1, 1, 1])
        self.assertEqual(evenOdd([3, 3, 2]), [2, 3, 3])
        self.assertEqual(evenOdd([2, 2, 2]), [2, 2, 2])
        self.assertEqual(evenOdd([3, 2, 2]), [2, 2, 3])
        self.assertEqual(evenOdd([1, 1, 0, 1, 0]), [0, 0, 1, 1, 1])
        self.assertEqual(evenOdd([1]), [1])
        self.assertEqual(evenOdd([1, 2]), [2, 1])
        self.assertEqual(evenOdd([2, 1]), [2, 1])
        self.assertEqual(evenOdd([]), [])

if __name__ == "__main__":
    unittest.main()
