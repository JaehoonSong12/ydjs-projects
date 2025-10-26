def fibonacci(n: int) -> int:
    """
    Description:
        The fibonacci sequence is a famous bit of mathematics, and it happens to have a recursive definition.
        The first two values in the sequence are 0 and 1 (essentially 2 base cases). Each subsequent value is the sum
        of the previous two values, so the whole sequence is: 0, 1, 1, 2, 3, 5, 8, 13, 21 and so on.
        Define a recursive fibonacci(n) method that returns the nth fibonacci number, with n=0 representing the start
        of the sequence.

    Examples:
        fibonacci(0) -> 0
        fibonacci(1) -> 1
        fibonacci(2) -> 1

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-recursion/q03.py`

    Args:
        n (int): The position in the fibonacci sequence, with fibonacci(0)=0.

    Returns:
        int: The nth fibonacci number.
    """
    # Base cases: since you need at least 2 base cases, 0, 1
    if n == 0: return 0
    if n == 1: return 1
    # Recursive case: a(n) = a(n-1) + a(n-2)
    return fibonacci(n - 1) + fibonacci(n - 2)


# Unit tests for the fibonacci function
import unittest

class TestFibonacciFunction(unittest.TestCase):
    def test_fibonacci(self):
        self.assertEqual(fibonacci(0), 0)  # fibonacci(0) -> 0
        self.assertEqual(fibonacci(1), 1)  # fibonacci(1) -> 1
        self.assertEqual(fibonacci(2), 1)  # fibonacci(2) -> 1
        self.assertEqual(fibonacci(3), 2)  # fibonacci(3) -> 2
        self.assertEqual(fibonacci(4), 3)  # fibonacci(4) -> 3
        self.assertEqual(fibonacci(5), 5)  # fibonacci(5) -> 5
        self.assertEqual(fibonacci(6), 8)  # fibonacci(6) -> 8
        self.assertEqual(fibonacci(7), 13) # fibonacci(7) -> 13

if __name__ == '__main__':
    unittest.main()
