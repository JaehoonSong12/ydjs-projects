def bunnyEars2(bunnies: int) -> int:
    """
    Description:
        We have bunnies standing in a line, numbered 1, 2, ... 
        The odd bunnies (1, 3, ...) have the normal 2 ears. 
        The even bunnies (2, 4, ...) we'll say have 3 ears, because they each have a raised foot.
        Recursively return the number of "ears" in the bunny line 1, 2, ... n (without loops or multiplication).

    Examples:
        bunnyEars2(0) -> 0
        bunnyEars2(1) -> 2
        bunnyEars2(2) -> 5

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-recursion/q04.py`

    Args:
        bunnies (int): The number of bunnies.

    Returns:
        int: The total number of ears in the bunny line.
    """
    # Base case: your implementation and comment here.
    if bunnies == 0: return 0
    if bunnies == 1: return 2
    if bunnies == 2: return 5
    # Recursive case: your implementation and comment here.
    if bunnies % 2 == 0:
        return bunnyEars2(bunnies - 1) + 3
    return bunnyEars2(bunnies - 1) + 2

# Unit tests for the bunnyEars2 function
import unittest

class TestBunnyEars2Function(unittest.TestCase):
    def test_bunnyEars2(self):
        self.assertEqual(bunnyEars2(0), 0)    # bunnyEars2(0) -> 0
        self.assertEqual(bunnyEars2(1), 2)    # bunnyEars2(1) -> 2
        self.assertEqual(bunnyEars2(2), 5)    # bunnyEars2(2) -> 5
        self.assertEqual(bunnyEars2(3), 7)    # bunnyEars2(3) -> 7
        self.assertEqual(bunnyEars2(4), 10)   # bunnyEars2(4) -> 10
        self.assertEqual(bunnyEars2(5), 12)   # bunnyEars2(5) -> 12
        self.assertEqual(bunnyEars2(6), 15)   # bunnyEars2(6) -> 15
        self.assertEqual(bunnyEars2(10), 25)  # bunnyEars2(10) -> 25

if __name__ == '__main__':
    unittest.main()
