def closeFar(a: int, b: int, c: int) -> bool:
    """
    Description:
        Given three integers a, b, c, return True if one of b or c is "close" to a
        (differing from a by at most 1), while the other is "far" from both of the others
        (differing by 2 or more from each).

    Examples:
        closeFar(1, 2, 10) → True   # b is close to a, c is far from both
        closeFar(8, 9, 10) → False  # both b and c are close
        closeFar(8, 9, 7) → False   # b is close but c is not far from b
        closeFar(8, 6, 9) → True    # c is far, b is far from c but close to a? actually b=6 is 2 away from a=8 → far, c=9 is 1 away → close

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-ap/q70.py`

    Args:
        a (int): The reference integer.
        b (int): The first integer to compare.
        c (int): The second integer to compare.

    Returns:
        bool: True if exactly one of b or c is within 1 of a and the other differs by at least 2 from both.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# Unit tests for closeFar function
import unittest

class TestCloseFar(unittest.TestCase):
    def test_closeFar(self):
        self.assertTrue(closeFar(1, 2, 10))
        self.assertFalse(closeFar(1, 2, 3))
        self.assertTrue(closeFar(4, 1, 3))
        self.assertFalse(closeFar(4, 5, 3))
        self.assertFalse(closeFar(4, 3, 5))
        self.assertTrue(closeFar(-1, 10, 0))
        self.assertTrue(closeFar(0, -1, 10))
        self.assertTrue(closeFar(10, 10, 8))
        self.assertFalse(closeFar(10, 8, 9))
        self.assertFalse(closeFar(8, 9, 10))
        self.assertFalse(closeFar(8, 9, 7))
        self.assertTrue(closeFar(8, 6, 9))

if __name__ == "__main__":
    unittest.main()
