def blackjack(a: int, b: int) -> int:
    """
    Description:
        Given two positive integers `a` and `b`, return whichever value is closest to 21 without going over.
        If both values exceed 21, return 0.

    Examples:
        blackjack(19, 21) → 21
        blackjack(21, 19) → 21
        blackjack(19, 22) → 19

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-ap/q84.py`

    Args:
        a (int): First card value (greater than 0).
        b (int): Second card value (greater than 0).

    Returns:
        int: The value closest to 21 without going over, or 0 if both exceed 21.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# Unit tests for blackjack function
import unittest

class TestBlackjack(unittest.TestCase):
    def test_blackjack(self):
        self.assertEqual(blackjack(19, 21), 21)
        self.assertEqual(blackjack(21, 19), 21)
        self.assertEqual(blackjack(19, 22), 19)
        self.assertEqual(blackjack(22, 19), 19)
        self.assertEqual(blackjack(22, 50), 0)
        self.assertEqual(blackjack(22, 22), 0)
        self.assertEqual(blackjack(33, 1), 1)
        self.assertEqual(blackjack(1, 2), 2)
        self.assertEqual(blackjack(34, 33), 0)
        self.assertEqual(blackjack(17, 19), 19)
        self.assertEqual(blackjack(18, 17), 18)
        self.assertEqual(blackjack(16, 23), 16)
        self.assertEqual(blackjack(3, 4), 4)
        self.assertEqual(blackjack(3, 2), 3)
        self.assertEqual(blackjack(21, 20), 21)

if __name__ == "__main__":
    unittest.main()
