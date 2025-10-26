def makeChocolate(small: int, big: int, goal: int) -> int:
    """
    Description:
        We want to make a package of `goal` kilos of chocolate. We have small bars
        (1 kilo each) and big bars (5 kilos each). Return the number of small bars
        to use, assuming we always use big bars before small bars. Return -1 if it
        can't be done.

    Examples:
        makeChocolate(4, 1, 9) → 4
        makeChocolate(4, 1, 10) → -1
        makeChocolate(4, 1, 7) → 2

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-ap/q92.py`

    Args:
        small (int): Number of small 1-kg bars available.
        big   (int): Number of big 5-kg bars available.
        goal  (int): Target weight in kilos.

    Returns:
        int: The number of small bars to use, or -1 if the goal cannot be met.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# Unit tests for makeChocolate function
import unittest

class TestMakeChocolate(unittest.TestCase):
    def test_makeChocolate(self):
        self.assertEqual(makeChocolate(4, 1, 9), 4)
        self.assertEqual(makeChocolate(4, 1, 10), -1)
        self.assertEqual(makeChocolate(4, 1, 7), 2)
        self.assertEqual(makeChocolate(6, 2, 7), 2)
        self.assertEqual(makeChocolate(4, 1, 5), 0)
        self.assertEqual(makeChocolate(4, 1, 4), 4)
        self.assertEqual(makeChocolate(5, 4, 9), 4)
        self.assertEqual(makeChocolate(9, 3, 18), 3)
        self.assertEqual(makeChocolate(3, 1, 9), -1)
        self.assertEqual(makeChocolate(1, 2, 7), -1)
        self.assertEqual(makeChocolate(1, 2, 6), 1)
        self.assertEqual(makeChocolate(1, 2, 5), 0)
        self.assertEqual(makeChocolate(6, 1, 10), 5)
        self.assertEqual(makeChocolate(6, 1, 11), 6)
        self.assertEqual(makeChocolate(6, 1, 12), -1)
        self.assertEqual(makeChocolate(6, 1, 13), -1)
        self.assertEqual(makeChocolate(6, 2, 10), 0)
        self.assertEqual(makeChocolate(6, 2, 11), 1)
        self.assertEqual(makeChocolate(6, 2, 12), 2)
        self.assertEqual(makeChocolate(60, 100, 550), 50)
        self.assertEqual(makeChocolate(1000, 1000000, 5000006), 6)
        self.assertEqual(makeChocolate(7, 1, 12), 7)
        self.assertEqual(makeChocolate(7, 1, 13), -1)
        self.assertEqual(makeChocolate(7, 2, 13), 3)

if __name__ == "__main__":
    unittest.main()
