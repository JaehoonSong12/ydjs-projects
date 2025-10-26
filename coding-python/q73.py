def squareUp(n: int) -> list[int]:
    """
    Description:
        Given a non-negative integer `n`, return a list of length `n*n` following a square-up pattern.
        For each i from 1 to n, you create a group of length n consisting of (n-i) zeros followed by the numbers
        i down to 1. Concatenate all groups in order.

    Examples:
        squareUp(3) → [0, 0, 1, 0, 2, 1, 3, 2, 1]
        squareUp(2) → [0, 1, 2, 1]
        squareUp(4) → [0, 0, 0, 1,    0, 0, 2, 1,    0, 3, 2, 1,    4, 3, 2, 1]

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-ap/q73.py`

    Args:
        n (int): The dimension of the square pattern (number of groups).

    Returns:
        list[int]: A flat list representing the square-up pattern of length n*n.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# Unit tests for squareUp function
import unittest

class TestSquareUp(unittest.TestCase):
    def test_squareUp(self):
        self.assertEqual(squareUp(3), [0, 0, 1, 0, 2, 1, 3, 2, 1])
        self.assertEqual(squareUp(2), [0, 1, 2, 1])
        self.assertEqual(
            squareUp(4),
            [0, 0, 0, 1, 0, 0, 2, 1, 0, 3, 2, 1, 4, 3, 2, 1]
        )
        self.assertEqual(squareUp(1), [1])
        self.assertEqual(squareUp(0), [])
        self.assertEqual(
            squareUp(6),
            [
                0, 0, 0, 0, 0, 1,
                0, 0, 0, 0, 2, 1,
                0, 0, 0, 3, 2, 1,
                0, 0, 4, 3, 2, 1,
                0, 5, 4, 3, 2, 1,
                6, 5, 4, 3, 2, 1
            ]
        )

if __name__ == "__main__":
    unittest.main()
