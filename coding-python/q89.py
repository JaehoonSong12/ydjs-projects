def post4(nums: list[int]) -> list[int]:
    """
    Description:
        Given a non-empty list of integers `nums`, return a new list containing 
        the elements that come after the last occurrence of 4 in `nums`. 
        You may assume there is at least one 4 in the list.

    Examples:
        post4([2, 4, 1, 2]) → [1, 2]
        post4([4, 1, 4, 2]) → [2]
        post4([4, 4, 1, 2, 3]) → [1, 2, 3]

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-ap/q89.py`

    Args:
        nums (list[int]): A non-empty list of integers containing at least one 4.

    Returns:
        list[int]: A new list of the elements after the last 4.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# Unit tests for post4 function
import unittest

class TestPost4(unittest.TestCase):
    def test_post4(self):
        self.assertEqual(post4([2, 4, 1, 2]), [1, 2])
        self.assertEqual(post4([4, 1, 4, 2]), [2])
        self.assertEqual(post4([4, 4, 1, 2, 3]), [1, 2, 3])
        self.assertEqual(post4([4, 2]), [2])
        self.assertEqual(post4([4, 4, 3]), [3])
        self.assertEqual(post4([4, 4]), [])
        self.assertEqual(post4([4]), [])
        self.assertEqual(post4([2, 4, 1, 4, 3, 2]), [3, 2])
        self.assertEqual(post4([4, 1, 4, 2, 2, 2]), [2, 2, 2])
        self.assertEqual(post4([3, 4, 3, 2]), [3, 2])

if __name__ == "__main__":
    unittest.main()
