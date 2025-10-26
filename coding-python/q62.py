def linearIn(outer: list[int], inner: list[int]) -> bool:
    """
    Description:
        Given two lists of integers sorted in increasing order, `outer` and `inner`,
        return True if all of the numbers in `inner` appear in `outer`. The optimal
        solution makes a single linear pass through both lists, taking advantage
        of their sorted order.

    Examples:
        linearIn([1, 2, 4, 6], [2, 4]) → True
        linearIn([1, 2, 4, 6], [2, 3, 4]) → False
        linearIn([1, 2, 4, 4, 6], [2, 4]) → True

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-ap/q62.py`

    Args:
        outer (list[int]): A sorted list of integers (the superset).
        inner (list[int]): A sorted list of integers to check for membership in `outer`.

    Returns:
        bool: True if every element of `inner` appears in `outer`, False otherwise.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# Unit tests for linearIn function
import unittest

class TestLinearIn(unittest.TestCase):
    def test_linearIn(self):
        self.assertTrue(linearIn([1, 2, 4, 6], [2, 4]))
        self.assertFalse(linearIn([1, 2, 4, 6], [2, 3, 4]))
        self.assertTrue(linearIn([1, 2, 4, 4, 6], [2, 4]))
        self.assertTrue(linearIn([2, 2, 4, 4, 6, 6], [2, 4]))
        self.assertTrue(linearIn([2,2,2,2,2], [2,2]))
        self.assertFalse(linearIn([2,2,2,2,2], [2,4]))
        self.assertTrue(linearIn([2,2,2,2,4], [2,4]))
        self.assertTrue(linearIn([1, 2, 3], [2]))
        self.assertFalse(linearIn([1,2,3], [-1]))
        self.assertTrue(linearIn([1, 2, 3], []))
        self.assertTrue(linearIn([-1,0,3,3,3,10,12], [-1,0,3,12]))
        self.assertFalse(linearIn([-1,0,3,3,3,10,12], [0,3,12,14]))
        self.assertFalse(linearIn([-1,0,3,3,3,10,12], [-1,10,11]))

if __name__ == "__main__":
    unittest.main()
