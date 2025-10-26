def triangle(rows: int) -> int:
    """
    Description:
        We have a triangle made of blocks. The topmost row has 1 block, the next row down has 2 blocks,
        the next row has 3 blocks, and so on. Compute recursively (no loops or multiplication) the total
        number of blocks in such a triangle with the given number of rows.

    Examples:
        triangle(0) -> 0
        triangle(1) -> 1
        triangle(2) -> 3

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-recursion/q05.py`

    Args:
        rows (int): The number of rows in the triangle.

    Returns:
        int: The total number of blocks in the triangle.
    """
    # Base case: your implementation and comment here.
    if rows == 0: return 0
    if rows == 1: return 1
    # Recursive case: your implementation and comment here.
    return triangle(rows - 1) + rows

# Unit tests for the triangle function
import unittest

class TestTriangleFunction(unittest.TestCase):
    def test_triangle(self):
        self.assertEqual(triangle(0), 0)   # triangle(0) -> 0
        self.assertEqual(triangle(1), 1)   # triangle(1) -> 1
        self.assertEqual(triangle(2), 3)   # triangle(2) -> 3
        self.assertEqual(triangle(3), 6)   # triangle(3) -> 6
        self.assertEqual(triangle(4), 10)  # triangle(4) -> 10
        self.assertEqual(triangle(5), 15)  # triangle(5) -> 15
        self.assertEqual(triangle(6), 21)  # triangle(6) -> 21
        self.assertEqual(triangle(7), 28)  # triangle(7) -> 28

if __name__ == '__main__':
    unittest.main()
