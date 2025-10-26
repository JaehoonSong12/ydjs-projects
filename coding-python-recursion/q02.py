def bunnyEars(bunnies: int) -> int:
    """
    Description:
        We have a number of bunnies and each bunny has two big floppy ears. 
        We want to compute the total number of ears across all the bunnies 
        recursively (without loops or multiplication).

    Examples:
        bunnyEars(0) -> 0
        bunnyEars(1) -> 2
        bunnyEars(2) -> 4

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-recursion/q02.py`

    Args:
        bunnies (int): The number of bunnies.

    Returns:
        int: The total number of ears across all the bunnies.
    """
    # Base case: 0 bunnies, 0 ears
    if bunnies == 0: return 0
    # Recursive case: n bunnies, n-1 bunnies + 2 ears.
    return bunnyEars(bunnies - 1) + 2 


# Unit tests for the bunnyEars function
import unittest

class TestBunnyEarsFunction(unittest.TestCase):
    def test_bunnyEars(self):
        self.assertEqual(bunnyEars(0), 0)   # bunnyEars(0) -> 0
        self.assertEqual(bunnyEars(1), 2)   # bunnyEars(1) -> 2
        self.assertEqual(bunnyEars(2), 4)   # bunnyEars(2) -> 4
        self.assertEqual(bunnyEars(3), 6)   # bunnyEars(3) -> 6
        self.assertEqual(bunnyEars(4), 8)   # bunnyEars(4) -> 8
        self.assertEqual(bunnyEars(5), 10)  # bunnyEars(5) -> 10
        self.assertEqual(bunnyEars(12), 24) # bunnyEars(12) -> 24
        self.assertEqual(bunnyEars(50), 100)  # bunnyEars(50) -> 100
        self.assertEqual(bunnyEars(234), 468) # bunnyEars(234) -> 468

if __name__ == '__main__':
    unittest.main()
