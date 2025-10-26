def evenlySpaced(a: int, b: int, c: int) -> bool:
    """
    Description:
        Given three ints, one of them is small, one is medium, and one is large.
        Return true if the three values are evenly spaced, so the difference
        between small and medium is the same as the difference between medium and large.

    Examples:
        evenlySpaced(2, 4, 6) → True
        evenlySpaced(4, 6, 2) → True
        evenlySpaced(4, 6, 3) → False

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-ap/q91.py`

    Args:
        a (int): First integer.
        b (int): Second integer.
        c (int): Third integer.

    Returns:
        bool: True if the numbers are evenly spaced, False otherwise.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# Unit tests for evenlySpaced function
import unittest

class TestEvenlySpaced(unittest.TestCase):
    def test_evenlySpaced(self):
        self.assertTrue(evenlySpaced(2, 4, 6))
        self.assertTrue(evenlySpaced(4, 6, 2))
        self.assertFalse(evenlySpaced(4, 6, 3))
        self.assertTrue(evenlySpaced(6, 2, 4))
        self.assertFalse(evenlySpaced(6, 2, 8))
        self.assertTrue(evenlySpaced(2, 2, 2))
        self.assertFalse(evenlySpaced(2, 2, 3))
        self.assertTrue(evenlySpaced(9, 10, 11))
        self.assertTrue(evenlySpaced(10, 9, 11))
        self.assertFalse(evenlySpaced(10, 9, 9))
        self.assertFalse(evenlySpaced(2, 4, 4))
        self.assertFalse(evenlySpaced(2, 2, 4))
        self.assertFalse(evenlySpaced(3, 6, 12))
        self.assertFalse(evenlySpaced(12, 3, 6))

if __name__ == "__main__":
    unittest.main()
