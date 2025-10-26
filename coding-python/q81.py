def shiftLeft(nums: list[int]) -> list[int]:
    """
    Description:
        Return a list that is "left shifted" by one position.
        That is, the first element moves to the end, and all other elements shift left.
        You may modify and return the given list, or return a new list.

    Examples:
        shiftLeft([6, 2, 5, 3]) → [2, 5, 3, 6]
        shiftLeft([1, 2]) → [2, 1]
        shiftLeft([]) → []
        shiftLeft([1, 1, 2, 2, 4]) → [1, 2, 2, 4, 1]

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-ap/q81.py`

    Args:
        nums (list[int]): The list of integers to shift.

    Returns:
        list[int]: The left-shifted list.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# Unit tests for shiftLeft function
import unittest

class TestShiftLeft(unittest.TestCase):
    def test_shiftLeft(self):
        self.assertEqual(shiftLeft([6, 2, 5, 3]), [2, 5, 3, 6])
        self.assertEqual(shiftLeft([1, 2]), [2, 1])
        self.assertEqual(shiftLeft([1]), [1])
        self.assertEqual(shiftLeft([]), [])
        self.assertEqual(shiftLeft([1, 1, 2, 2, 4]), [1, 2, 2, 4, 1])
        self.assertEqual(shiftLeft([1, 1, 1]), [1, 1, 1])
        self.assertEqual(shiftLeft([1, 2, 3]), [2, 3, 1])

if __name__ == "__main__":
    unittest.main()
